-- 商品分類1情報取得
select
	 IC1.item_category1_cd as item_category1_cd
	,IC1.item_category1_name as item_category1_name
from
	tm_item_category1 IC1
where
	true
and IC1.item_category1_cd = coalesce(/* itemCategory1Cd */'', '')
;