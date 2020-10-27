package ru.haval.db;

import java.time.LocalDate;
import java.util.Calendar;

public class tmp {
    public static void main(String[] argc) {

    }
    public static void createWRRecord(String id) {
        String st = "INSERT INTO hmmr_work_recording (ap_num, user_number, FL_WSH, FL_Group, FL_Line, FL_Station, FL_Equipment, Equipment_Full, Record_Type, CM_Work_Time, Task_Resp_ID, _Resp2, _Resp3, _Resp4, WR_Executor_Confirmed, Task_Description, Task_Report, WR_Begin_Date, _Actual_Date_2, _Actual_Date_3, _Actual_Date_4, CM_DownTime, WR_End_Date, _Actual_Date2, _Actual_Date3, _Actual_Date4, WR_Work_Time, _Actual_Time2, _Actual_Time3, _Actual_Time4, WR_Work_Time_Begin, _Hours1_2, _Hours1_3, _Hours1_4, WR_Work_Time_End, _Hours2_2, _Hours2_3, _Hours2_4, CM_Date_Begin, CM_Date_End, CM_Time_Begin, CM_Time_End, Activity_Type, _Resp5, _Resp6, _Resp7, _Resp8, _Resp9, _Actual_Date_5, _Actual_Date_6, _Actual_Date_7, _Actual_Date_8, _Actual_Date_9, _Actual_Date5, _Actual_Date6, _Actual_Date7, _Actual_Date8, _Actual_Date9, _Actual_Time5, _Actual_Time6, _Actual_Time7, _Actual_Time8, _Actual_Time9, _Hours1_5, _Hours1_6, _Hours1_7, _Hours1_8, _Hours1_9, _Hours2_5, _Hours2_6, _Hours2_7, _Hours2_8, _Hours2_9) VALUES (" +
                "'" + id +
                "','120','J','SB','W','CHB027-W162','CHB027-030-KQ04-W162-Q1','J.SB.W.CHB027-W162.CHB027-030-KQ04-W162-Q1','PM','0.0','DDV','0','0','0','Confirmed WR','Плановые измерения пинов','Выполнено','2020-10-23','2020-10-23','2020-10-23','2020-10-23','0','2020-10-23','2020-10-23','2020-10-23','2020-10-23','60','60','60','60','10:00','10:00','10:00','10:00','11:00','11:00','11:00','11:00','0000-00-00','0000-00-00','00:00','00:00','C','0','0','0','0','0','2020-10-23','2020-10-23','2020-10-23','2020-10-23','2020-10-23','2020-10-23','2020-10-23','2020-10-23','2020-10-23','2020-10-23','60','60','60','60','60','10:00','10:00','10:00','10:00','10:00','11:00','11:00','11:00','11:00','11:00');";

    }

    public static void tcal() {
        LocalDate ld = LocalDate.of(2020, 1, 1);
        ld = ld.plusDays(1);
        ld.getMonthValue();
        //println ld;
        //date_cur.getDayOfWeek().toString().equals("SATURDAY") && !date_cur.getDayOfWeek().toString().equals("SUNDAY")
    }
}
