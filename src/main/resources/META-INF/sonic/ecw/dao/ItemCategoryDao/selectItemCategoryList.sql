-- 商品分類リスト取得
with icat1 as (
	select
		 cast('1' as text) as item_category_num
		,IC1.item_category1_cd as item_category1_cd
		,cast('' as text) as item_category2_cd
		,IC1.item_category1_name as item_category1_name
		,cast('' as text) as item_category2_name
	from
		tm_item_category1 IC1
)
,icat2 as (
	select
		 cast('2' as text) as item_category_num
		,IC2.item_category1_cd as item_category1_cd
		,IC2.item_category2_cd as item_category2_cd
		,cast('' as text) as item_category1_name
		,IC2.item_category2_name as item_category2_name
	from
		tm_item_category2 IC2
)
,icats as (
	(
	 select
	 	 item_category_num
		,item_category1_cd
		,item_category1_name
		,item_category2_cd
		,item_category2_name
	 from
	 	icat1
	 )
	union all
	(
	 select
	 	 item_category_num
		,item_category1_cd
		,item_category1_name
		,item_category2_cd
		,item_category2_name
	 from
	 	icat2
	 )
)
select
 	 item_category_num
	,item_category1_cd
	,item_category1_name
	,item_category2_cd
	,item_category2_name
from
	icats ICS
order by
	 ICS.item_category1_cd asc
	,ICS.item_category2_cd asc
;