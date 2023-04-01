----------------------------------------------------------------------------------------------------
-- ECWINDOW テーブル作成
----------------------------------------------------------------------------------------------------

-- 商品マスタ
drop table if exists tm_item;
create table if not exists tm_item (
	 item_cd varchar(10) not null
	,item_name1 varchar(30) not null default ''
	,item_name2 varchar(30)
	,item_category1_cd varchar(4)
	,item_category2_cd varchar(4)
	,brand_cd varchar(4)
	,model_number varchar(40)
	,jan_cd varchar(13)
	,item_image_ref_div char(1) not null default '1'
	,item_image1 varchar(100)
	,disp_flg char(1) not null default '1'
	,status_cd char(2) not null default '00'
	,update_by varchar(20)
	,update_time timestamp without time zone not null default now()
	,insert_by varchar(20)
	,insert_time timestamp without time zone not null default now()
	,constraint tm_item_item_cd primary key(item_cd)
);
comment on table tm_item is '商品マスタ';
comment on column tm_item.item_cd is '商品コード';
comment on column tm_item.item_name1 is '商品名1';
comment on column tm_item.item_name2 is '商品名2';
comment on column tm_item.item_category1_cd is '商品分類1コード';
comment on column tm_item.item_category2_cd is '商品分類2コード';
comment on column tm_item.brand_cd is 'ブランドコード';
comment on column tm_item.model_number is '型番';
comment on column tm_item.jan_cd is 'JANコード';
comment on column tm_item.item_image_ref_div is '商品画像参照区分';
comment on column tm_item.item_image1 is '商品画像1';
comment on column tm_item.disp_flg is '表示フラグ';
comment on column tm_item.status_cd is 'ステータスコード';
comment on column tm_item.update_by is '更新者';
comment on column tm_item.update_time is '更新日時';
comment on column tm_item.insert_by is '登録者';
comment on column tm_item.insert_time is '登録日時';

-- 商品分類1マスタ
drop table if exists tm_item_category1;
create table if not exists tm_item_category1 (
	 item_category1_cd varchar(4) not null
	,item_category1_name varchar(30) not null default ''
	,update_by varchar(20)
	,update_time timestamp without time zone not null default now()
	,insert_by varchar(20)
	,insert_time timestamp without time zone not null default now()
	,constraint tm_item_category1_item_category1_cd primary key(item_category1_cd)
);
comment on table tm_item_category1 is '商品分類1マスタ';
comment on column tm_item_category1.item_category1_cd is '商品分類コード';
comment on column tm_item_category1.item_category1_name is '商品分類名';
comment on column tm_item_category1.update_by is '更新者';
comment on column tm_item_category1.update_time is '更新日時';
comment on column tm_item_category1.insert_by is '登録者';
comment on column tm_item_category1.insert_time is '登録日時';

-- 商品分類2マスタ
drop table if exists tm_item_category2;
create table if not exists tm_item_category2 (
	 item_category1_cd varchar(4) not null
	,item_category2_cd varchar(4) not null
	,item_category2_name varchar(30) not null default ''
	,update_by varchar(20)
	,update_time timestamp without time zone not null default now()
	,insert_by varchar(20)
	,insert_time timestamp without time zone not null default now()
	,constraint tm_item_category2_item_category1_cd_item_category2_cd primary key(item_category1_cd, item_category2_cd)
);
comment on table tm_item_category2 is '商品分類2マスタ';
comment on column tm_item_category2.item_category1_cd is '商品分類1コード';
comment on column tm_item_category2.item_category2_cd is '商品分類2コード';
comment on column tm_item_category2.item_category2_name is '商品分類名';
comment on column tm_item_category2.update_by is '更新者';
comment on column tm_item_category2.update_time is '更新日時';
comment on column tm_item_category2.insert_by is '登録者';
comment on column tm_item_category2.insert_time is '登録日時';

-- 商品単価マスタ
drop table if exists tm_item_price;
create table if not exists tm_item_price (
	 item_cd varchar(20) not null
	,no_tax_retail_price numeric(19,4) not null default 0
	,no_tax_wholesale_price numeric(19,4) not null default 0
	,tax_div_cd varchar(2) not null default '00'
	,update_by varchar(20)
	,update_time timestamp without time zone not null default now()
	,insert_by varchar(20)
	,insert_time timestamp without time zone not null default now()
	,constraint tm_item_price_item_cd primary key(item_cd)
);
comment on table tm_item_price is '商品単価マスタ';
comment on column tm_item_price.item_cd is '商品コード';
comment on column tm_item_price.no_tax_retail_price is '税抜上代単価';
comment on column tm_item_price.no_tax_wholesale_price is '税抜下代単価';
comment on column tm_item_price.tax_div_cd is '消費税率区分コード';
comment on column tm_item_price.update_by is '更新者';
comment on column tm_item_price.update_time is '更新日時';
comment on column tm_item_price.insert_by is '登録者';
comment on column tm_item_price.insert_time is '登録日時';

-- 商品ステータスマスタ
drop table if exists tm_item_status;
create table if not exists tm_item_status (
	 status_cd varchar(2) not null
	,status_name varchar(30) not null default ''
	,update_by varchar(20)
	,update_time timestamp without time zone not null default now()
	,insert_by varchar(20)
	,insert_time timestamp without time zone not null default now()
	,constraint tm_item_status_status_cd primary key(status_cd)
);
comment on table tm_item_status is '商品ステータスマスタ';
comment on column tm_item_status.status_cd is 'ステータスコード';
comment on column tm_item_status.status_name is 'ステータス名';
comment on column tm_item_status.update_by is '更新者';
comment on column tm_item_status.update_time is '更新日時';
comment on column tm_item_status.insert_by is '登録者';
comment on column tm_item_status.insert_time is '登録日時';

-- 商品在庫マスタ
drop table if exists tm_item_stock;
create table if not exists tm_item_stock (
	 item_cd varchar(20) not null
	,stock_num numeric(19,4) not null default 0
	,update_by varchar(20)
	,update_time timestamp without time zone not null default now()
	,insert_by varchar(20)
	,insert_time timestamp without time zone not null default now()
	,constraint tm_item_stock_item_cd primary key(item_cd)
);
comment on table tm_item_stock is '商品在庫マスタ';
comment on column tm_item_stock.item_cd is '商品コード';
comment on column tm_item_stock.stock_num is '在庫数量';
comment on column tm_item_stock.update_by is '更新者';
comment on column tm_item_stock.update_time is '更新日時';
comment on column tm_item_stock.insert_by is '登録者';
comment on column tm_item_stock.insert_time is '登録日時';

-- ブランドマスタ
drop table if exists tm_brand;
create table if not exists tm_brand (
	 brand_cd varchar(4) not null
	,brand_name varchar(20) not null default ''
	,brand_name_kana varchar(30)
	,update_by varchar(20)
	,update_time timestamp without time zone not null default now()
	,insert_by varchar(20)
	,insert_time timestamp without time zone not null default now()
	,constraint tm_brand_brand_cd primary key(brand_cd)
);
comment on table tm_brand is 'ブランドマスタ';
comment on column tm_brand.brand_cd is 'ブランドコード';
comment on column tm_brand.brand_name is 'ブランド名';
comment on column tm_brand.brand_name_kana is 'ブランド名カナ';
comment on column tm_brand.update_by is '更新者';
comment on column tm_brand.update_time is '更新日時';
comment on column tm_brand.insert_by is '登録者';
comment on column tm_brand.insert_time is '登録日時';

-- メッセージマスタ
drop table if exists tm_message;
create table if not exists tm_message (
	 msg_cd varchar(80) not null
	,msg_val varchar(200) not null default ''
	,description varchar(200)
	,update_by varchar(20)
	,update_time timestamp without time zone not null default now()
	,insert_by varchar(20)
	,insert_time timestamp without time zone not null default now()
	,constraint tm_message_m_id primary key(msg_cd)
);
comment on table tm_message is 'メッセージマスタ';
comment on column tm_message.msg_cd is 'メッセージコード';
comment on column tm_message.msg_val is '文言';
comment on column tm_message.description is '説明';
comment on column tm_message.update_by is '更新者';
comment on column tm_message.update_time is '更新日時';
comment on column tm_message.insert_by is '登録者';
comment on column tm_message.insert_time is '登録日時';

-- システム設定マスタ
drop table if exists tm_system_setting;
create table if not exists tm_system_setting (
	 ss_id varchar(6) not null
	,ss_cd varchar(80) not null
	,ss_name varchar(80) not null
	,ss_val varchar(4000)
	,description varchar(200)
	,update_by varchar(20)
	,update_time timestamp without time zone not null default now()
	,insert_by varchar(20)
	,insert_time timestamp without time zone not null default now()
	,constraint tm_system_setting_ss_id primary key(ss_id)
	,unique(ss_cd)
);
comment on table tm_system_setting is 'システム設定マスタ';
comment on column tm_system_setting.ss_id is 'システム設定ID';
comment on column tm_system_setting.ss_cd is 'システム設定コード';
comment on column tm_system_setting.ss_name is 'システム設定名';
comment on column tm_system_setting.ss_val is 'システム設定値';
comment on column tm_system_setting.description is '説明';
comment on column tm_system_setting.update_by is '更新者';
comment on column tm_system_setting.update_time is '更新日時';
comment on column tm_system_setting.insert_by is '登録者';
comment on column tm_system_setting.insert_time is '登録日時';

-- 消費税率区分マスタ
drop table if exists tm_tax_div;
create table if not exists tm_tax_div (
	 tax_div_cd varchar(2) not null
	,tax_rate numeric(19,4) not null default 0
	,tax_name varchar(20) not null default ''
	,update_by varchar(20)
	,update_time timestamp without time zone not null default now()
	,insert_by varchar(20)
	,insert_time timestamp without time zone not null default now()
	,constraint tm_tax_div_tax_div_cd primary key(tax_div_cd)
);
comment on table tm_tax_div is '消費税率区分マスタ';
comment on column tm_tax_div.tax_div_cd is '消費税率区分コード';
comment on column tm_tax_div.tax_rate is '消費税率';
comment on column tm_tax_div.tax_name is '消費税率区分名';
comment on column tm_tax_div.update_by is '更新者';
comment on column tm_tax_div.update_time is '更新日時';
comment on column tm_tax_div.insert_by is '登録者';
comment on column tm_tax_div.insert_time is '登録日時';

-- 受注履歴トラン
drop table if exists tt_order_history;
create table if not exists tt_order_history (
	 order_no varchar(10) not null
	,order_date timestamp without time zone not null
	,record_no int not null
	,item_cd varchar(20) not null
	,order_quantity numeric(19,4) not null default 0
	,update_by varchar(20)
	,update_time timestamp without time zone not null default now()
	,insert_by varchar(20)
	,insert_time timestamp without time zone not null default now()
	,constraint tt_order_history_order_no_record_no primary key(order_no, record_no)
);
comment on table tt_order_history is '受注履歴トラン';
comment on column tt_order_history.order_no is '注文番号';
comment on column tt_order_history.order_date is '注文日付';
comment on column tt_order_history.record_no is '明細番号';
comment on column tt_order_history.item_cd is '商品コード';
comment on column tt_order_history.order_quantity is '注文数量';
comment on column tt_order_history.update_by is '更新者';
comment on column tt_order_history.update_time is '更新日時';
comment on column tt_order_history.insert_by is '登録者';
comment on column tt_order_history.insert_time is '登録日時';
