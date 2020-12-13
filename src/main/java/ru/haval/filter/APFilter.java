package ru.haval.filter;

public class APFilter extends APWRFilter implements IFilter{
    private static APFilter singleton = null;

    private APFilter(){}

    public static APFilter getInstance() {
        if (singleton == null) {
            singleton = new APFilter();
        }
        return singleton;
    }
}
