with recommended_candidate as (
	select
	I.item_cd as item_cd
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
	 ,random() as rd

	from
		tm_item I
	--商品単価マスタ
	inner join
		tm_item_price IP
		on I.item_cd = IP.item_cd
	-- ブランドマスタ
	left outer join
		tm_brand B
		on I.brand_cd = B.brand_cd
	-- 商品在庫マスタ
	left outer join
		tm_item_stock ISTK
		on I.item_cd = ISTK.item_cd
	-- 商品ステータスマスタ
	left outer join
		tm_item_status ISTS
		on I.status_cd = ISTS.status_cd

	where
	-- 商品分類１が同じ物だけ（商品情報取得の際に取得したデータを使用）
		I.item_category1_cd = /* category1_cd */''
	-- 表示フラグ１
		and I.disp_flg = '1'
	-- 販売中のみ
		and ISTS.status_cd = '00'
	-- 在庫１以上
		and ISTK.stock_num > 0
	-- 詳細画面に表示している商品を除外
		and I.item_cd <> /* item_cd */''
)

select
	RC.item_cd
	,RC.item_name1
	,RC.item_name2
	,RC.item_image1
	,RC.brand_name
	,RC.wholesale_price
	,RC.stock_num
	,RC.stock_status
	,RC.status_cd
	,RC.status_name

from
	recommended_candidate RC

order by
	RC.rd desc

limit 6
;


