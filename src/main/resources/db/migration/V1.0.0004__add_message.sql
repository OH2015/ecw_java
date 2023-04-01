with sign as (select 'ecwinit' as name)
insert into tm_message (msg_cd, msg_val, description, update_by, insert_by)
select v.*, sign.name, sign.name
from
(values
	 ('item_detail_no_result',	'指定された商品は見つかりませんでした。',	'[商品詳細]検索結果が0件だった時の文言'	)
) as v, sign
;