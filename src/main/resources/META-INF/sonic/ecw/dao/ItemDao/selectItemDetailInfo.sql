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

	 ,I.item_category1_cd as item_category1_cd
	 ,IC1.item_category1_name as item_category1_name
	 ,I.item_category2_cd as item_category2_cd
	 ,IC2.item_category2_name as item_category2_name
	 ,I.model_number as model_number
	 ,I.jan_cd as jan_cd
	 ,I.description as description

from
	tm_item I
-- 商品単価マスタ
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
-- 商品分類１マスタ
left outer join
	tm_item_category1 IC1
	on I.item_category1_cd = IC1.item_category1_cd
-- 商品分類２マスタ
left outer join
	tm_item_category2 IC2
	on I.item_category1_cd = IC2.item_category1_cd
	and I.item_category2_cd = IC2.item_category2_cd

where
	I.item_cd = /* item_cd */''
and
	I.disp_flg = '1'
;




