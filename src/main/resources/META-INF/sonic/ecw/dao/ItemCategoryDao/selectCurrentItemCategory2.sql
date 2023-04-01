-- 商品分類2情報取得
select
	 IC2.item_category2_cd as item_category2_cd
	,IC2.item_category2_name as item_category2_name
from
	tm_item_category2 IC2
where
	true
and IC2.item_category1_cd = coalesce(/* itemCategory1Cd */'', '')
and IC2.item_category2_cd = coalesce(/* itemCategory2Cd */'', '')
;