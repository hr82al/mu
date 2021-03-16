set @from = '10.168.150.74', @to = '192.168.1.215';

update hmmr_mu.hmmr_activity_type set Icon = REPLACE(Icon, @from, @to) where id > 0;
update hmmr_mu.hmmr_mu_prior set Icon = REPLACE(Icon, @from, @to)  where id > 0;

CREATE TABLE IF NOT EXISTS hmmr_filters (
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL UNIQUE,
filter TEXT) CHARSET=utf8;

#Поисик пм по оборудованию в action hmmr_action_plan
SELECT * FROM hmmr_action_plan hap WHERE hap.PM_Num in (SELECT hp.id FROM pm_inst pi INNER JOIN hmmr_pm hp ON pi.num_instruction = hp.Instruction_num WHERE hp.Eq_ID in (SELECT id FROM `hmmr_plant_structure` hps WHERE Description_RUS LIKE '%IP%')) AND Due_Date LIKE '2021-01%';

SELECT * FROM hmmr_work_recording hwr WHERE hwr.ap_num in (SELECT id FROM hmmr_action_plan hap WHERE hap.PM_Num in (SELECT hp.id FROM pm_inst pi INNER JOIN hmmr_pm hp ON pi.num_instruction = hp.Instruction_num WHERE hp.Eq_ID in (SELECT id FROM `hmmr_plant_structure` hps WHERE Description_RUS LIKE '%IP%')) AND Due_Date LIKE '2021-01%');


#Существущие в ap и несуществующие в wp установить флаги

DROP table tmp;
CREATE TEMPORARY TABLE IF NOT EXISTS tmp as (


select hap.id as id from hmmr_action_plan hap left join hmmr_work_recording hwr on hwr.ap_num = hap.id  where (hap.Otv_For_Task = 'ASH' or hap.Otv_For_Task = 'SAV') and hap.Due_Date like '2020-11-%' and user_number IS NULL
);
UPDATE hmmr_action_plan set 
del_rec = 0, flag_otv = 0, flag_tm = 0, flag_oft = 0 WHERE id IN (select id from tmp);

#######################
UPDATE hmmr_work_recording hwr
SET Record_Type = (
SELECT Type FROM hmmr_action_plan WHERE id = hwr.ap_num
)
WHERE Record_Type IS NULL AND WR_Begin_Date LIKE '2020-%';
#####################

use hmmr_mu;

CREATE TEMPORARY TABLE IF NOT EXISTS tmp as (


select hap.id as id from hmmr_action_plan hap  left join hmmr_work_recording hwr on hwr.ap_num = hap.id  where hap.Due_Date like '2020-11-%' and hap.Otv = 'need select' and (hap.Otv_For_Task = 'DVO' OR hap.Otv_For_Task = 'SRA') and hwr.id IS NULL
);

UPDATE hmmr_action_plan set 
Otv = 'DGA' WHERE id IN (select id from tmp)
################


CREATE TEMPORARY TABLE IF NOT EXISTS tmp as (
SELECT id FROM hmmr_action_plan WHERE Due_Date like '2020-11-%' and Otv = 'need select' and Otv_For_Task ='RKa'
);
UPDATE hmmr_action_plan set
Otv = 'RN' WHERE id in (SELECT * FROM tmp);
############################################


SELECT * from hmmr_action_plan hap WHERE id NOT IN (SELECT ap_num FROM hmmr_work_recording) and Due_Date BETWEEN '2020-01-01' and '2020-10-31' GROUP BY CONCAT(hap.PM_Num,hap.Due_Date) HAVING COUNT(CONCAT(hap.PM_Num,hap.Due_Date)) > 1 ORDER BY hap.id



CREATE TEMPORARY TABLE IF NOT EXISTS tmp AS (
SELECT id from hmmr_action_plan hap WHERE  id NOT IN (SELECT ap_num FROM hmmr_work_recording) AND  Due_Date BETWEEN '2020-01-01' and '2020-10-31' GROUP BY CONCAT(hap.PM_Num,hap.Due_Date) HAVING COUNT(CONCAT(hap.PM_Num,hap.Due_Date)) > 1 ORDER BY hap.id
);

DELETE FROM hmmr_action_plan WHERE id IN (SELECT * FROM tmp);


SELECT * FROM hmmr_action_plan hap WHERE Otv = 'need select' and Due_Date BETWEEN '2020-01-01' AND '2020-10-31' ORDER BY hap.Otv_For_Task;
#mysql param
?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true

#Set pm executor by last hmmr_work_plan state
UPDATE hmmr_pm hp
SET PM_Executor = (SELECT hwp.Otv FROM hmmr_work_plan hwp INNER JOIN hmmr_mu_staff hms ON hwp.Otv = hms.ID WHERE hwp.PM_Num = hp.id and hwp.Otv != 'need select' AND hms.user_del = 0 ORDER BY hwp.Due_Date DESC limit 1 )
WHERE id > 0 AND hp.id in (SELECT hwp.PM_Num FROM hmmr_work_plan hwp INNER JOIN hmmr_mu_staff hms ON hwp.Otv = hms.ID WHERE hwp.Otv != 'need select' AND hms.user_del = 0 GROUP BY hwp.PM_Num) and hp.PM_Executor = 'need select';

#Set pm executor by last hmmr_action_plan state
UPDATE hmmr_pm hp
SET PM_Executor = (
SELECT hap.Otv FROM hmmr_action_plan hap INNER JOIN hmmr_mu_staff hms ON hap.Otv = hms.ID WHERE hap.PM_Num = hp.id AND hms.user_del = 0 AND hap.Otv != 'need select' ORDER BY hap.Due_Date DESC limit 1
)
WHERE id > 0 AND hp.id in (
SELECT hap.PM_Num FROM hmmr_action_plan hap INNER JOIN hmmr_mu_staff hms ON hap.Otv = hms.ID WHERE hms.user_del = 0 AND hap.Otv != 'need select' GROUP BY PM_Num
) and hp.PM_Executor = 'need select';


update hmmr_mu.hmmr_work_plan
set
	Instruction = REPLACE(Instruction, '10.168.150.74', '192.168.1.215') where
Instruction like '%10.168.150.74%' and id > 0;

hap.Due_Date like '2020-11-%'  and (flag_otv != 2 or flag_oft !=2 or flag_tm != 2)  and (hap.Tsk_maker = 'SAV' or hap.Tsk_maker ='ASH')

hap.Due_date like '2020-11-%' and hap.shop = 'A'

hap.Due_date like '2020-11-%' and (hap.Tsk_maker = 'SAV' or hap.Tsk_maker ='ASH')

select * from hmmr_action_plan hap  left join hmmr_work_recording hwr on hwr.ap_num = hap.id  where hap.shop = 'A' and hap.Due_Date like '2020-11-%' and hwr.id IS NULL

1. ap
select hap.id,hap.PM_Num,hap.Type,hap.Description,hap.Due_Date,hap.Equipment,hap.Instruction,hap.Otv_For_Task,hap.Otv,hap.Tsk_maker,hap.flag_otv,hap.flag_oft,hap.flag_tm,hap.Icon,hap.Icon_AT, hap.user_id, hmp.Icon, hat.Icon, hmp.Description, hat.Description
from hmmr_action_plan hap INNER JOIN hmmr_mu_prior hmp ON hap.icon =  hmp.ID_TSK INNER JOIN hmmr_activity_type hat ON hap.Icon_AT = hat.Name  INNER JOIN hmmr_mu_staff hms ON hap.Otv = hms.ID

 where (Otv_For_Task = 'SAV' OR Otv = 'SAV' OR Tsk_maker = 'SAV') AND hap.del_rec = 0 ORDER BY FIELD(hap.Icon, '1S', '2Q', '3P', '4M', '1') ASC;


3. all shop
ap query
select hap.id,hap.PM_Num,hap.Type,hap.Description,hap.Due_Date,hap.Equipment,hap.Instruction,hap.Otv_For_Task,hap.Otv,hap.Tsk_maker,hap.flag_otv,hap.flag_oft,hap.flag_tm,hap.Icon,hap.Icon_AT, hap.user_id, hmp.Icon, hat.Icon, hmp.Description, hat.Description
from hmmr_action_plan hap INNER JOIN hmmr_mu_prior hmp ON hap.icon =  hmp.ID_TSK INNER JOIN hmmr_activity_type hat ON hap.Icon_AT = hat.Name  INNER JOIN hmmr_mu_staff hms ON hap.Otv = hms.ID

 where  hap.del_rec = 0 AND if( 'A'='S' || 'A'='W', hms.Group_S='S,W', hms.Group_S='A') ORDER BY FIELD(hap.Icon, '1S', '2Q', '3P', '4M', '1') ASC;


4. ap shop
ap query
select hap.id,hap.PM_Num,hap.Type,hap.Description,hap.Due_Date,hap.Equipment,hap.Instruction,hap.Otv_For_Task,hap.Otv,hap.Tsk_maker,hap.flag_otv,hap.flag_oft,hap.flag_tm,hap.Icon,hap.Icon_AT, hap.user_id, hmp.Icon, hat.Icon, hmp.Description, hat.Description
from hmmr_action_plan hap INNER JOIN hmmr_mu_prior hmp ON hap.icon =  hmp.ID_TSK INNER JOIN hmmr_activity_type hat ON hap.Icon_AT = hat.Name  INNER JOIN hmmr_mu_staff hms ON hap.Otv = hms.ID

 where hap.del_rec = 0 AND (if( 'A'='S' || 'A'='W', hms.Group_S='S,W', hms.Group_S='A') OR Otv_For_Task = 'SAV' OR Otv = 'SAV' OR Tsk_maker = 'SAV') ORDER BY FIELD(hap.Icon, '1S', '2Q', '3P', '4M', '1') ASC;


5. ex check
ap query
select hap.id,hap.PM_Num,hap.Type,hap.Description,hap.Due_Date,hap.Equipment,hap.Instruction,hap.Otv_For_Task,hap.Otv,hap.Tsk_maker,hap.flag_otv,hap.flag_oft,hap.flag_tm,hap.Icon,hap.Icon_AT, hap.user_id, hmp.Icon, hat.Icon, hmp.Description, hat.Description
from hmmr_action_plan hap INNER JOIN hmmr_mu_prior hmp ON hap.icon =  hmp.ID_TSK INNER JOIN hmmr_activity_type hat ON hap.Icon_AT = hat.Name  INNER JOIN hmmr_mu_staff hms ON hap.Otv = hms.ID

  WHERE hap.flag_otv = 2 AND hap.flag_oft = 2 AND hap.flag_tm = 2 AND if( 'A'='S' || 'A'='W', hms.Group_S='S,W', hms.Group_S='A');

6. without otv
ap query
select hap.id,hap.PM_Num,hap.Type,hap.Description,hap.Due_Date,hap.Equipment,hap.Instruction,hap.Otv_For_Task,hap.Otv,hap.Tsk_maker,hap.flag_otv,hap.flag_oft,hap.flag_tm,hap.Icon,hap.Icon_AT, hap.user_id, hmp.Icon, hat.Icon, hmp.Description, hat.Description
from hmmr_action_plan hap INNER JOIN hmmr_mu_prior hmp ON hap.icon =  hmp.ID_TSK INNER JOIN hmmr_activity_type hat ON hap.Icon_AT = hat.Name  INNER JOIN hmmr_mu_staff hms ON hap.Tsk_maker = hms.ID

 WHERE hap.Otv = 'need select' AND hap.del_rec = 0 AND if( 'A,L'='S' || 'A,L'='W', hms.Group_S='S,W', hms.Group_S='A,L');



 **********************************************************************************************************************


 INSERT INTO hmmr_work_recording (ap_num, user_number, FL_WSH, FL_Group, FL_Line, FL_Station, FL_Equipment, Equipment_Full, Record_Type, CM_Work_Time, Task_Resp_ID, _Resp2, _Resp3, _Resp4, WR_Executor_Confirmed, Task_Description, Task_Report, WR_Begin_Date, _Actual_Date_2, _Actual_Date_3, _Actual_Date_4, CM_DownTime, WR_End_Date, _Actual_Date2, _Actual_Date3, _Actual_Date4, WR_Work_Time, _Actual_Time2, _Actual_Time3, _Actual_Time4, WR_Work_Time_Begin, _Hours1_2, _Hours1_3, _Hours1_4, WR_Work_Time_End, _Hours2_2, _Hours2_3, _Hours2_4, CM_Date_Begin, CM_Date_End, CM_Time_Begin, CM_Time_End, Activity_Type, _Resp5, _Resp6, _Resp7, _Resp8, _Resp9, _Actual_Date_5, _Actual_Date_6, _Actual_Date_7, _Actual_Date_8, _Actual_Date_9, _Actual_Date5, _Actual_Date6, _Actual_Date7, _Actual_Date8, _Actual_Date9, _Actual_Time5, _Actual_Time6, _Actual_Time7, _Actual_Time8, _Actual_Time9, _Hours1_5, _Hours1_6, _Hours1_7, _Hours1_8, _Hours1_9, _Hours2_5, _Hours2_6, _Hours2_7, _Hours2_8, _Hours2_9) VALUES ('62556','30','A','TRIM','GlassGluing','---','MD#02','A.TRIM.GlassGluing.---.MD#02','PM','0.0','DI','0','0','0','Confirmed WR','Проверка конвейерных ремней','Выполнено.','2020-11-27','2020-12-13','2020-12-13','2020-12-13','0','2020-11-27','2020-12-13','2020-12-13','2020-12-13','20','60','60','60','14:00','18:46','18:46','18:46','14:20','19:46','19:46','19:46','0000-00-00','0000-00-00','00:00','00:00','C','0','0','0','0','0','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','60','60','60','60','60','18:46','18:46','18:46','18:46','18:46','19:46','19:46','19:46','19:46','19:46');



 String query = "INSERT INTO hmmr_work_recording (ap_num, user_number, FL_WSH, FL_Group, FL_Line, FL_Station, FL_Equipment, Equipment_Full, Record_Type, CM_Work_Time, Task_Resp_ID, _Resp2, _Resp3, _Resp4, WR_Executor_Confirmed, Task_Description, Task_Report, WR_Begin_Date, _Actual_Date_2, _Actual_Date_3, _Actual_Date_4, CM_DownTime, WR_End_Date, _Actual_Date2, _Actual_Date3, _Actual_Date4, WR_Work_Time, _Actual_Time2, _Actual_Time3, _Actual_Time4, WR_Work_Time_Begin, _Hours1_2, _Hours1_3, _Hours1_4, WR_Work_Time_End, _Hours2_2, _Hours2_3, _Hours2_4, CM_Date_Begin, CM_Date_End, CM_Time_Begin, CM_Time_End, Activity_Type, _Resp5, _Resp6, _Resp7, _Resp8, _Resp9, _Actual_Date_5, _Actual_Date_6, _Actual_Date_7, _Actual_Date_8, _Actual_Date_9, _Actual_Date5, _Actual_Date6, _Actual_Date7, _Actual_Date8, _Actual_Date9, _Actual_Time5, _Actual_Time6, _Actual_Time7, _Actual_Time8, _Actual_Time9, _Hours1_5, _Hours1_6, _Hours1_7, _Hours1_8, _Hours1_9, _Hours2_5, _Hours2_6, _Hours2_7, _Hours2_8, _Hours2_9) VALUES (" + "'" + ap_num + "'" + "," + "'" + userid + "'" + "," + "'" + WSH + "'" + "," + "'" + Group_EQ + "'" + "," + "'" + Line + "'" + "," + "'" + Station + "'" + "," + "'" + Equip + "'" + "," + "'" + equip + "'" + "," + "'" + record_type + "'" + "," + "'" + wt + "'" + "," + "'" + resp + "'" + "," + "'" + resp1 + "'" + "," + "'" + resp2 + "'" + "," + "'" + resp3 + "'" + "," + "'" + status_wr + "'" + "," + "'" + shift_report + "'" + "," + "'" + req_action + "'" + "," + "'" + actual_date + "'" + "," + "'" + actual_date_2 + "'" + "," + "'" + actual_date_3 + "'" + "," + "'" + actual_date_4 + "'" + "," + "'" + actual_time + "'" + "," + "'" + actual_date1 + "'" + "," + "'" + actual_date2 + "'" + "," + "'" + actual_date3 + "'" + "," + "'" + actual_date4 + "'" + "," + "'" + actual_time1 + "'" + "," + "'" + actual_time2 + "'" + "," + "'" + actual_time3 + "'" + "," + "'" + actual_time4 + "'" + "," + "'" + hours1 + "'" + "," + "'" + hours1_2 + "'" + "," + "'" + hours1_3 + "'" + "," + "'" + hours1_4 + "'" + "," + "'" + hours2 + "'" + "," + "'" + hours2_2 + "'" + "," + "'" + hours2_3 + "'" + "," + "'" + hours2_4 + "'" + "," + "'" + b_gdw + "'" + "," + "'" + e_gdw + "'" + "," + "'" + b_gtw + "'" + "," + "'" + e_gtw + "'" + "," + "'" + icon_at + "'" + "," + "'" + _Resp4 + "'" + "," + "'" + _Resp5 + "'" + "," + "'" + _Resp6 + "'" + "," + "'" + _Resp7 + "'" + "," + "'" + _Resp8 + "'" + "," + "'" + _Actual_Date_5 + "'" + "," + "'" + _Actual_Date_6 + "'" + "," + "'" + _Actual_Date_7 + "'" + "," + "'" + _Actual_Date_8 + "'" + "," + "'" + _Actual_Date_9 + "'" + "," + "'" + _Actual_Date5 + "'" + "," + "'" + _Actual_Date6 + "'" + "," + "'" + _Actual_Date7 + "'" + "," + "'" + _Actual_Date8 + "'" + "," + "'" + _Actual_Date9 + "'" + "," + "'" + _Actual_Time5 + "'" + "," + "'" + _Actual_Time6 + "'" + "," + "'" + _Actual_Time7 + "'" + "," + "'" + _Actual_Time8 + "'" + "," + "'" + _Actual_Time9 + "'" + "," + "'" + _Hours1_5 + "'" + "," + "'" + _Hours1_6 + "'" + "," + "'" + _Hours1_7 + "'" + "," + "'" + _Hours1_8 + "'" + "," + "'" + _Hours1_9 + "'" + "," + "'" + _Hours2_5 + "'" + "," + "'" + _Hours2_6 + "'" + "," + "'" + _Hours2_7 + "'" + "," + "'" + _Hours2_8 + "'" + "," + "'" + _Hours2_9 + "'" + ");";




Что сделано: Выполнено.

Дата начала 2020-11-27
Дата конца 2020-11-27

Время начала 9:10
Время конца 9:40

вычисляемые поля
длительность 0:30



INSERT INTO hmmr_work_recording (ap_num, user_number, FL_WSH, FL_Group, FL_Line, FL_Station, FL_Equipment, Equipment_Full, Record_Type, CM_Work_Time, Task_Resp_ID, _Resp2, _Resp3, _Resp4, WR_Executor_Confirmed, Task_Description, Task_Report, WR_Begin_Date, _Actual_Date_2, _Actual_Date_3, _Actual_Date_4, CM_DownTime, WR_End_Date, _Actual_Date2, _Actual_Date3, _Actual_Date4, WR_Work_Time, _Actual_Time2, _Actual_Time3, _Actual_Time4, WR_Work_Time_Begin, _Hours1_2, _Hours1_3, _Hours1_4, WR_Work_Time_End, _Hours2_2, _Hours2_3, _Hours2_4, CM_Date_Begin, CM_Date_End, CM_Time_Begin, CM_Time_End, Activity_Type, _Resp5, _Resp6, _Resp7, _Resp8, _Resp9, _Actual_Date_5, _Actual_Date_6, _Actual_Date_7, _Actual_Date_8, _Actual_Date_9, _Actual_Date5, _Actual_Date6, _Actual_Date7, _Actual_Date8, _Actual_Date9, _Actual_Time5, _Actual_Time6, _Actual_Time7, _Actual_Time8, _Actual_Time9, _Hours1_5, _Hours1_6, _Hours1_7, _Hours1_8, _Hours1_9, _Hours2_5, _Hours2_6, _Hours2_7, _Hours2_8, _Hours2_9) VALUES ('62564','30','A','TRIM','GlassGluing','---','OP_ABB#01','A.TRIM.GlassGluing.---.OP_ABB#01','PM','0.0','DI','0','0','0','Confirmed WR','Процедура снятия резервной копии робота ABB','Выполнено.','2020-11-27','2020-12-13','2020-12-13','2020-12-13','0','2020-11-27','2020-12-13','2020-12-13','2020-12-13','30','60','60','60','09:10','13:59','13:59','13:59','09:40','14:59','14:59','14:59','0000-00-00','0000-00-00','00:00','00:00','C','0','0','0','0','0','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','60','60','60','60','60','13:59','13:59','13:59','13:59','13:59','14:59','14:59','14:59','14:59','14:59');



INSERT INTO hmmr_work_recording (ap_num, user_number, FL_WSH, FL_Group, FL_Line, FL_Station, FL_Equipment, Equipment_Full, Record_Type, CM_Work_Time, Task_Resp_ID, _Resp2, _Resp3, _Resp4, WR_Executor_Confirmed, Task_Description, Task_Report, WR_Begin_Date, _Actual_Date_2, _Actual_Date_3, _Actual_Date_4, CM_DownTime, WR_End_Date, _Actual_Date2, _Actual_Date3, _Actual_Date4, WR_Work_Time, _Actual_Time2, _Actual_Time3, _Actual_Time4, WR_Work_Time_Begin, _Hours1_2, _Hours1_3, _Hours1_4, WR_Work_Time_End, _Hours2_2, _Hours2_3, _Hours2_4, CM_Date_Begin, CM_Date_End, CM_Time_Begin, CM_Time_End, Activity_Type, _Resp5, _Resp6, _Resp7, _Resp8, _Resp9, _Actual_Date_5, _Actual_Date_6, _Actual_Date_7, _Actual_Date_8, _Actual_Date_9, _Actual_Date5, _Actual_Date6, _Actual_Date7, _Actual_Date8, _Actual_Date9, _Actual_Time5, _Actual_Time6, _Actual_Time7, _Actual_Time8, _Actual_Time9, _Hours1_5, _Hours1_6, _Hours1_7, _Hours1_8, _Hours1_9, _Hours2_5, _Hours2_6, _Hours2_7, _Hours2_8, _Hours2_9) VALUES ('62564','30','A','TRIM','GlassGluing','---','OP_ABB#01','A.TRIM.GlassGluing.---.OP_ABB#01','PM','0.0','DI','0','0','0','Confirmed WR','Процедура снятия резервной копии робота ABB','Выполнено.','2020-11-27','2020-12-13','2020-12-13','2020-12-13','0','2020-11-27','2020-12-13','2020-12-13','2020-12-13','30','60','60','60','09:10','13:59','13:59','13:59','09:40','14:59','14:59','14:59','0000-00-00','0000-00-00','00:00','00:00','C','0','0','0','0','0','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','2020-12-13','60','60','60','60','60','13:59','13:59','13:59','13:59','13:59','14:59','14:59','14:59','14:59','14:59');



