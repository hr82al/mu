import re
    q = "INSERT INTO hmmr_work_recording (ap_num, user_number, FL_WSH, FL_Group, FL_Line, FL_Station, FL_Equipment, Equipment_Full, Record_Type, CM_Work_Time, Task_Resp_ID, _Resp2, _Resp3, _Resp4, WR_Executor_Confirmed, Task_Description, Task_Report, WR_Begin_Date, _Actual_Date_2, _Actual_Date_3, _Actual_Date_4, CM_DownTime, WR_End_Date, _Actual_Date2, _Actual_Date3, _Actual_Date4, WR_Work_Time, _Actual_Time2, _Actual_Time3, _Actual_Time4, WR_Work_Time_Begin, _Hours1_2, _Hours1_3, _Hours1_4, WR_Work_Time_End, _Hours2_2, _Hours2_3, _Hours2_4, CM_Date_Begin, CM_Date_End, CM_Time_Begin, CM_Time_End, Activity_Type, _Resp5, _Resp6, _Resp7, _Resp8, _Resp9, _Actual_Date_5, _Actual_Date_6, _Actual_Date_7, _Actual_Date_8, _Actual_Date_9, _Actual_Date5, _Actual_Date6, _Actual_Date7, _Actual_Date8, _Actual_Date9, _Actual_Time5, _Actual_Time6, _Actual_Time7, _Actual_Time8, _Actual_Time9, _Hours1_5, _Hours1_6, _Hours1_7, _Hours1_8, _Hours1_9, _Hours2_5, _Hours2_6, _Hours2_7, _Hours2_8, _Hours2_9) VALUES ('84054','131','A','PBS','PBS03','OP#03','PRB#72','A.PBS.PBS03.OP#03.PRB#72','PM','0.0','DAD','0','0','0','Confirmed WR','Проверка приводного устройства','Выполнено.','2021-03-15','2021-03-17','2021-03-17','2021-03-17','0','2021-03-16','2021-03-17','2021-03-17','2021-03-17','1650','60','60','60','08:30','10:12','10:12','10:12','12:00','11:12','11:12','11:12','0000-00-00','0000-00-00','00:00','00:00','C','0','0','0','0','0','2021-03-17','2021-03-17','2021-03-17','2021-03-17','2021-03-17','2021-03-17','2021-03-17','2021-03-17','2021-03-17','2021-03-17','60','60','60','60','60','10:12','10:12','10:12','10:12','10:12','11:12','11:12','11:12','11:12','11:12');"

INSERT INTO hmmr_work_recording (ap_num, user_number, FL_WSH, FL_Group, FL_Line, FL_Station, FL_Equipment, Equipment_Full, Record_Type, CM_Work_Time, Task_Resp_ID, _Resp2, _Resp3, _Resp4, WR_Executor_Confirmed, Task_Description, Task_Report, WR_Begin_Date, _Actual_Date_2, _Actual_Date_3, _Actual_Date_4, CM_DownTime, WR_End_Date, _Actual_Date2, _Actual_Date3, _Actual_Date4, WR_Work_Time, _Actual_Time2, _Actual_Time3, _Actual_Time4, WR_Work_Time_Begin, _Hours1_2, _Hours1_3, _Hours1_4, WR_Work_Time_End, _Hours2_2, _Hours2_3, _Hours2_4, CM_Date_Begin, CM_Date_End, CM_Time_Begin, CM_Time_End, Activity_Type, _Resp5, _Resp6, _Resp7, _Resp8, _Resp9, _Actual_Date_5, _Actual_Date_6, _Actual_Date_7, _Actual_Date_8, _Actual_Date_9, _Actual_Date5, _Actual_Date6, _Actual_Date7, _Actual_Date8, _Actual_Date9, _Actual_Time5, _Actual_Time6, _Actual_Time7, _Actual_Time8, _Actual_Time9, _Hours1_5, _Hours1_6, _Hours1_7, _Hours1_8, _Hours1_9, _Hours2_5, _Hours2_6, _Hours2_7, _Hours2_8, _Hours2_9) VALUES ('84054','131','A','PBS','PBS03','OP#03','PRB#72','A.PBS.PBS03.OP#03.PRB#72','PM','0.0','DAD','0','0','0','Confirmed WR','Проверка приводного устройства','Выполнено.','2021-03-15','2021-03-17','2021-03-17','2021-03-17','0','2021-03-16','2021-03-17','2021-03-17','2021-03-17','1650','60','60','60','08:30','10:12','10:12','10:12','12:00','11:12','11:12','11:12','0000-00-00','0000-00-00','00:00','00:00','C','0','0','0','0','0','2021-03-17','2021-03-17','2021-03-17','2021-03-17','2021-03-17','2021-03-17','2021-03-17','2021-03-17','2021-03-17','2021-03-17','60','60','60','60','60','10:12','10:12','10:12','10:12','10:12','11:12','11:12','11:12','11:12','11:12');


def gd(q):
  rez = re.search("\((.*?)\).*?\((.*?)\)",q)
  return (rez[1].split(","), rez[2].split(","))

def gi(ar, s):
  rez = []
  for i in range(len(ar)):
    if s in ar[i]:
      rez.append(i)
  return rez

k, v = gd(q)

ca = ",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,"

def fs(s, poss, v):
  ls = s.split(",")
  for i in poss:
    ls[i] = v
  return ",".join(ls)


