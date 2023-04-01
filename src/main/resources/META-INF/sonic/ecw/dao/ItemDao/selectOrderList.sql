with month_table as(
    select
   
    to_char(CAST(OH.order_date as DATE),'YYYY/MM') as order_month
    ,sum(order_quantity)                           as order_quantity_monthly
    ,sum(order_quantity*IP.no_tax_wholesale_price) as earnings_monthly
    
from tt_order_history OH

left join tm_item_price IP
on OH.item_cd = IP.item_cd

group by
to_char(CAST(OH.order_date as DATE),'YYYY/MM')

)
,date_table as(
    select
    to_char(CAST(OH.order_date as DATE),'YYYY/MM/DD') as order_date
    ,to_char(CAST(OH.order_date as DATE),'YYYY/MM')   as order_month
    
    ,sum(order_quantity)                              as order_quantity_daily
    ,sum(order_quantity*IP.no_tax_wholesale_price)    as earnings_daily
    
from tt_order_history OH

left join tm_item_price IP
on OH.item_cd = IP.item_cd

group by
CAST(OH.order_date as DATE)

)
select
    count(1)over() as count
    ,DT.order_month
    ,MT.order_quantity_monthly
    ,MT.earnings_monthly
    ,DT.order_date
    ,DT.order_quantity_daily
    ,DT.earnings_daily

from date_table DT

left join month_table MT
on DT.order_month = MT.order_month



order by
CAST(DT.order_date as DATE)
;

