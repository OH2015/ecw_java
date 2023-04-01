-- 課題#03 商品名の最後にnullと表示されているものがある
-- 課題#05 ブランド名でキーワード検索できるようにする
-- 課題#07 受注数量が多い順に表示する
-- 課題#08 単価が\000と表示されている商品がある
-- 課題#09 税抜下代単価の上限・下限を指定して絞り込み可能にする

-- 商品リスト取得
with keyword_likequery as (
-- memo
-- キーワードが含まれる文字列（％はワイルドカード、||は文字列連結、/* */はDomaでの2way表記）
-- from句の省略？-> val from keyword_likequey という名前で後から参照するためだけに定義している
	select ('%' || /* keyword */'' || '%') as val
)

-- 直近1000件の受注
,recent_1000_orders as (
	select order_no,order_date
	from tt_order_history
	group by order_no,order_date
	order by order_date desc
	limit 1000
)

-- 上で抽出したテーブルを元に商品毎の合計注文数量を算出する。
,order_sum_by_product as(
	select
		TT.item_cd
		,sum(order_quantity) as order_quantity_sum

--	受注履歴トランから抽出
	from tt_order_history TT

--	受注履歴トラン + 直近1000件の受注(上で定義） 条件:受注番号
	inner join recent_1000_orders RO
	on TT.order_no = RO.order_no

--	受注トラン + 商品マスタ 条件:商品コード
	left outer join tm_item I
	on TT.item_cd = I.item_cd

--  商品マスタ + 商品在庫マスタ 条件:商品コード&在庫あり
	inner join tm_item_stock STO
	on I.item_cd = STO.item_cd
	and STO.stock_num > 0

--	商品マスタ + 商品ステータスマスタ 条件:ステータスコード&取扱中
	inner join tm_item_status STA
	on I.status_cd = STA.status_cd
	and STA.status_cd <> '99'

--	商品コード毎にレコード分け
	group by TT.item_cd
)

select
-- memo
-- count() 引数の列のnullを含まない行数を数える
-- 		   count(1)はcount(*)と同じで全てのレコード数を取得する
-- over()句 集計範囲を指定、引数なしで全範囲
	 count(1)over() as count
	,I.item_cd as item_cd
	,coalesce(I.item_name1, '') as item_name1
	,coalesce(I.item_name2,'') as item_name2
	,coalesce(I.item_image1, 'noimage.png') as item_image1
	,coalesce(B.brand_name,'') as brand_name
	,IP.no_tax_wholesale_price as wholesale_price
	,ISTK.stock_num as stock_num
	,(case
		when coalesce(ISTK.stock_num, 0) <= 0 then '在庫なし'
		when coalesce(ISTK.stock_num, 0) < 10 then '在庫残少'
		else '在庫あり'
	 end) as stock_status
	 ,ISTS.status_cd as status_cd
	 ,ISTS.status_name as status_name

from
	tm_item I
left join
	tm_brand B
	on B.brand_cd = I.brand_cd
left join
	tm_item_price IP
	on IP.item_cd = I.item_cd
left join
	tm_item_stock ISTK
	on ISTK.item_cd = I.item_cd
left join
	tm_item_status ISTS
	on ISTS.status_cd = I.status_cd
-- 共通テーブル式を結合
left outer join order_sum_by_product OS
	on I.item_cd = OS.item_cd

where

-- exists句＆Iを外部参照の相関サブクエリ
-- memo
-- サブクエリ内と外部クエリでテーブルが行ったり来たりするのが相関サブクエリ？
-- サブクエリ内で外部クエリのテーブルを参照したい時などに使う
	exists (
		select * from tm_item_price IP
		where I.item_cd = IP.item_cd
	)

	and I.disp_flg = '1'

-- カテゴリー１が空なら非表示
/*%if itemCategory1Cd != null && itemCategory1Cd != "" */
	and I.item_category1_cd = /* itemCategory1Cd */''
/*%end*/

-- カテゴリー２が空なら非表示
/*%if itemCategory2Cd != null && itemCategory2Cd != ""*/
	and I.item_category2_cd = /* itemCategory2Cd */''
/*%end*/

--	keywordが空の時は非表示になる
/*%if keyword != null && keyword != "" */
	and (coalesce(I.item_cd, '') || coalesce(I.item_name1, '') || coalesce(I.item_name2, '')) || coalesce(B.brand_name, '') || coalesce(B.brand_name_kana) ilike (select val from keyword_likequery)
/*%end*/

-- 価格帯指定
/*%if min_price != null*/
	and IP.no_tax_wholesale_price >= /* min_price */0
/*%end*/

/*%if max_price != null*/
	and IP.no_tax_wholesale_price <= /* max_price */9999999999
/*%end*/

-- 在庫切れ商品だけを抽出したい時(オプション)
/*%if stockNum != null*/
	and ISTK.stock_num <= /* stockNum */999999999
/*%end*/

order by
-- 1,最近の合計受注数量が多い商品から降順（nullは最後）
-- 2,商品コード昇順
	 OS.order_quantity_sum desc NULLS LAST
	 ,I.item_cd asc

offset /* offset */0 limit /* limit */20
;



