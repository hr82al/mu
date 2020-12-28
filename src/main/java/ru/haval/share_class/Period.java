package ru.haval.share_class;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Period implements Comparable<Period> {
    private int period = 0;
    private String periodId = "";
    private String pmGroup;
    private LocalDate beginDate;

    private Map<String, Integer> namePeriod = new HashMap<>();

    {
        namePeriod.put("TO1",10);
        namePeriod.put("TO3", 30);
        namePeriod.put("MP1", 90);
        namePeriod.put("CP1", 180);
        namePeriod.put("KP1", 360);
    }

    public final static String[] NAME_PERIODS = {"TO1", "TO3", "MP1", "CP1", "KP1"};
    private final static int [] PERIODS = {10, 30, 90, 180, 360};

    public Period() {

    }

    public Period(String[] period) {

        Pattern pattern = Pattern.compile("-.{3,5}$");
        Matcher matcher = pattern.matcher(period[2]);
        if (matcher.find()) {
            periodId = matcher.group().substring(1);
            for (int i = 0; i < NAME_PERIODS.length; i++) {
                if (NAME_PERIODS[i].equals(periodId)) {
                    this.period = PERIODS[i];
                }
            }
            pmGroup = period[4];
            beginDate = LocalDate.parse(period[9]);
        }
    }

    public boolean isPeriodNumber(int number) {
        return NAME_PERIODS[number].equals(periodId);
    }

    public static int getPeriods() {
        return NAME_PERIODS.length;
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

    @Override
    public int compareTo(@NotNull Period o) {
        return o.period - this.period;
    }

    public void setPeriod(String period) {
        this.period = namePeriod.get(period);
        this.periodId = period;
    }

    public void setPmGroup(String pmGroup) {
        this.pmGroup = pmGroup;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }
}
