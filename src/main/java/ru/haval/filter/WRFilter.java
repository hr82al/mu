package ru.haval.filter;

public class WRFilter extends APWRFilter implements IFilter {

    private static WRFilter singleton = null;

    private WRFilter(){
    }

    public static WRFilter getInstance() {
        if (singleton == null) {
            singleton = new WRFilter();
        }
        return singleton;
    }

}
