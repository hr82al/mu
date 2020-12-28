package ru.haval.share_class;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Period {
    private int period = 0;
    private String periodId = "";
    private String pmGroup;

    public final static String[] NAME_PERIODS = {"TO1", "CP1", "MP1", "KP1"};
    private final static int [] PERIODS = {10, 90, 180, 360};

    public Period(String[] period) {

        Pattern pattern = Pattern.compile("-.{3,4}$");
        Matcher matcher = pattern.matcher(period[2]);
        if (matcher.find()) {
            periodId = matcher.group().substring(1);
            for (int i = 0; i < NAME_PERIODS.length; i++) {
                if (NAME_PERIODS[i].equals(periodId)) {
                    this.period = PERIODS[i];
                }
            }
            pmGroup = period[4];
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
}
