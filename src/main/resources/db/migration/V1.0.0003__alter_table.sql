----------------------------------------------------------------------------------------------------
-- ECWINDOW ALTER TABLE
----------------------------------------------------------------------------------------------------

alter table tm_item drop column if exists description;
alter table tm_item add column description varchar(1000);
comment on column tm_item.description is '商品説明';

-- サンプルデータを入力
update tm_item set description =
'
【画面サイズ】13インチFHD(1920×1080)<br>
【主な付属品】ACアダプター、電源ケーブル<br>
【カラー】Silver
' where item_cd like '00000010%';