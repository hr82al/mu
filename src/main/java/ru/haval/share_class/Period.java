package ru.haval.share_class;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Period implements Comparable<Period> {
    private int period = 0;
    private String periodId = "";
    private String pmGroup;
    private LocalDate beginDate;
    private int eqID;

    private final static Map<String, Integer> namePeriod = new HashMap<>();

    static {
        namePeriod.put("TO1",10);
        namePeriod.put("TO3", 30);
        namePeriod.put("TO4", 60);
        namePeriod.put("MP1", 90);
        namePeriod.put("CP1", 180);
        namePeriod.put("KP1", 360);
    }

    public Period() {

    }

    public Period(int eqID, String pmGroup, LocalDate beginDate, String period) {
        this.eqID = eqID;
        this.pmGroup = pmGroup;
        this.beginDate =beginDate;
        this.period = namePeriod.get(period);
        this.periodId = period;
    }

    public int getPeriod() {
        return period;
    }

    public String getPmGroup() {
        return pmGroup;
    }

    public String getPeriodId() {
        return periodId;
    }

    public int getEqID() {
        return eqID;
    }

    @Override
    public int compareTo(Period o) {
        return o.period - this.period;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public static long getPeriodByName(String periodName) {
        if (namePeriod.get(periodName) == null) {
            return  0;
        } else {
            return namePeriod.get(periodName);
        }
    }
}
