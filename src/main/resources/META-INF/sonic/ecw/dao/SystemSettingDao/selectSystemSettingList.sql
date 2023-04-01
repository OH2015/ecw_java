-- システム設定リスト取得
select
	 SS.ss_cd as ss_cd
	,SS.ss_name as ss_name
	,SS.ss_val as ss_val
from
	tm_system_setting SS
;