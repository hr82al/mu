package ru.haval.config;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.haval.action.hmmr_ap_model;
import ru.haval.db._connect;
import ru.haval.db._query;
import ru.haval.share_class.s_class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Main {
    private static Statement stmt12;
    private static ResultSet rs12;
    _connect cn = new _connect();
    static _query qr = new _query();

    public static void main(String[] args) {
        Random rand = new Random();
        Config.getInstance().setAddress("10.168.150.74");
        String query = "select hap.id as ap_num, \n" +
                "  hms.user_id as user_number, \n" +
                "\tSUBSTRING_INDEX(hap.Equipment,'.',1) as FL_WSH,\n" +
                "  SUBSTRING_INDEX(SUBSTRING_INDEX(hap.Equipment,'.',2),'.',-1) AS FL_Group,\n" +
                "\tSUBSTRING_INDEX(SUBSTRING_INDEX(hap.Equipment,'.',3),'.',-1) AS FL_Line,\n" +
                "\tSUBSTRING_INDEX(SUBSTRING_INDEX(hap.Equipment,'.',4),'.',-1) AS FL_Station,\n" +
                "\tSUBSTRING_INDEX(SUBSTRING_INDEX(hap.Equipment,'.',5),'.',-1) AS FL_Equipment,\n" +
                "\thap.Equipment AS Equipment_Full,\n" +
                "\thap.Type AS Record_Type,\n" +
                "\t0 AS CM_Work_Time,\n" +
                "\thap.Otv as Task_Resp_ID,\n" +
                "\t0 as _Resp2, \n" +
                "\t0 as_Resp3, \n" +
                "\t0 as_Resp4,\n" +
                "\t'Done' AS WR_Executor_Confirmed, \n" +
                "\thap.Description as Task_Description, \n" +
                "\t'Выполнено.' as Task_Report, \n" +
                "\thap.Due_Date As Due_Date" +
                "\t from hmmr_action_plan hap INNER JOIN hmmr_mu_prior hmp ON hap.icon =  hmp.ID_TSK INNER JOIN hmmr_activity_type hat ON hap.Icon_AT = hat.Name  INNER JOIN hmmr_mu_staff hms ON hap.Otv = hms.ID where  " +
                "hap.del_rec = 0 AND  hap.shop ='A' and flag_otv = 0 AND Due_Date < '2020-11-09' ORDER BY FIELD(hap.Icon, '1S', '2Q', '3P', '4M', '1') ASC;";
                Main m = new Main();
        _connect.init();
        LocalDate WR_Begin_Date;
        int counter = 0;
        for (ArrayList<String> i : m.fillAPModel(query)) {
            counter++;
            WR_Begin_Date = LocalDate.parse(i.get(17)).minusDays(3);
            while (WR_Begin_Date.getDayOfWeek() == DayOfWeek.SATURDAY || WR_Begin_Date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                WR_Begin_Date = WR_Begin_Date.plusDays(1);
            };
            String duration = Integer.toString(rand.nextInt(11) * 5 + 10);
            int tmpTime = rand.nextInt(93) * 5 + 520;
            int tmpEndTime = tmpTime + Integer.parseInt(duration);
            String beginTime = String.format("%02d", tmpTime / 60) + ":" + String.format("%02d",tmpTime % 60);
            String endTime = String.format("%02d", tmpEndTime / 60) + ":" + String.format("%02d",tmpEndTime % 60);
            qr._insert_wr(
                    i.get(0),
                    i.get(1),
                    i.get(2),
                    i.get(3),
                    i.get(4),
                    i.get(5),
                    i.get(6),
                    i.get(7),
                    i.get(8),
                   Double.parseDouble(i.get(9)),
                    i.get(10),
                    i.get(11),
                    i.get(12),
                    i.get(13),
                    i.get(14),
                    i.get(15),
                    i.get(16),
                    WR_Begin_Date,
                    WR_Begin_Date,
                    WR_Begin_Date,
                    WR_Begin_Date,
                    "0",
                    WR_Begin_Date,
                    WR_Begin_Date,
                    WR_Begin_Date,
                    WR_Begin_Date,
                    duration,
                    duration,
                    duration,
                    duration,
                    LocalTime.parse(beginTime),
                    LocalTime.parse(beginTime),
                    LocalTime.parse(beginTime),
                    LocalTime.parse(beginTime),
                    LocalTime.parse(endTime),
                    LocalTime.parse(endTime),
                    LocalTime.parse(endTime),
                    LocalTime.parse(endTime),
                    "0000-00-00",
                    "0000-00-00",
                    "00:00",
                    "00:00",
                    "C","0","0","0","0","0",
                    WR_Begin_Date,
                    WR_Begin_Date,
                    WR_Begin_Date,
                    WR_Begin_Date,
                    WR_Begin_Date,
                    WR_Begin_Date,
                    WR_Begin_Date,
                    WR_Begin_Date,
                    WR_Begin_Date,
                    WR_Begin_Date,
                    duration,
                    duration,
                    duration,
                    duration,
                    duration,
                    LocalTime.parse(beginTime),
                    LocalTime.parse(beginTime),
                    LocalTime.parse(beginTime),
                    LocalTime.parse(beginTime),
                    LocalTime.parse(beginTime),
                    LocalTime.parse(endTime),
                    LocalTime.parse(endTime),
                    LocalTime.parse(endTime),
                    LocalTime.parse(endTime),
                    LocalTime.parse(endTime)
                    );
        }
        System.out.println(counter);
    }
    private List<ArrayList<String>> fillAPModel(String query) {

        synchronized (_query.class) {
            List<ArrayList<String>> list = new ArrayList<>();
            try {
                cn.ConToDb();
                stmt12 = cn.con.createStatement();
                rs12 = stmt12.executeQuery(query);

                while (rs12.next()) {
                    ArrayList<String> tmp = new ArrayList<>();
                    if (rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
                        for (int i = 1; i <= 18; i++) {
                            tmp.add(rs12.getString(i));
                        }


                        list.add(tmp);
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                //close connection ,stmt and resultset here
                try {
                    cn.con.close();
                } catch (SQLException se) { /*can't do anything */ }
                try {
                    stmt12.close();
                } catch (SQLException se) { /*can't do anything */ }
                try {
                    rs12.close();
                } catch (SQLException se) { /*can't do anything */ }
            }
            return list;
        }
    }
}
