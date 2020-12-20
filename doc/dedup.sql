use hmmr_mu;
drop table if exists tmp;
create temporary table tmp as (
select id from hmmr_work_plan group by concat(PM_Num, Due_Date) having count(*) > 1);

delete from hmmr_work_plan where id in (select id from tmp);

#
alter table hmmr_work_plan add unique (PM_Num, Due_Date)

#
use hmmr_mu;
drop table if exists tmp;
create table tmp as (
select * from hmmr_action_plan group by concat(PM_Num, Due_Date) having count(*) > 1);
drop table if exists tmpap;
create table if not exists tmpap as (
select * from hmmr_action_plan where concat(PM_Num, Due_Date) in (select concat(PM_Num, Due_Date) from tmp)
);

#
use hmmr_mu;
SET SQL_SAFE_UPDATES=0;
delete from tmpap where id in (select id from tmp);
SET SQL_SAFE_UPDATES=1;

#
delete from hmmr_work_recording where ap_num in (select id from tmpap);

#
delete from hmmr_action_plan where id in (select id from tmpap);

#
alter table hmmr_action_plan add unique (PM_Num, Due_Date);

