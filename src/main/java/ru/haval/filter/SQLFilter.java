package ru.haval.filter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLFilter {
    private ObservableList<String> vars = FXCollections.observableArrayList();
    private Map<String, String> variableValues = new HashMap<>();
    private String query;
    private final static String[] MARKERS = {"=", "LIKE"};

    private SQLFilter() {}

    public static SQLFilter setSqlFilter(String query) {
        Matcher matcher;
        SQLFilter sqlFilter = new SQLFilter();
        sqlFilter.query = query;
        for (String mark : MARKERS) {
            matcher = getGetMatcherByMark(query, mark);
            while (matcher.find()) {
                sqlFilter.vars.add(matcher.group(1));
                sqlFilter.variableValues.put(matcher.group(1), matcher.group(2));
            }
        }
        return sqlFilter;

    }

    public ObservableList<String> getVars() {
        return vars;
    }

    public String getVariableValueByName(String name) {
        return variableValues.get(name);
    }

    public String changeVariable(String variable_name, String newValue) {
        query = setVar(query, variable_name, newValue);
        variableValues.put(variable_name, newValue);
        return query;
    }

    private static Matcher getGetMatcherByMark(String query, String mark) {
        Pattern pattern = Pattern.compile("(\\S+?) " + mark + " '(.*?)'");
        Matcher matcher = pattern.matcher(query);
        return matcher;
    }

    private static Matcher getSetMatcherByMark(String query, String mark, String var) {
        Pattern pattern = Pattern.compile(var + " " + mark + " '(.*?)'");
        Matcher matcher = pattern.matcher(query);
        return matcher;
    }

    private static String setVar(String query, String var, String val) {
        String out = "";
        Matcher matcher;

        for (String mark : MARKERS) {
            //find position of variable
            matcher = getSetMatcherByMark(query, mark, var);
            if (matcher.find()) {
                out = query.substring(0, matcher.start(1)) + val + query.substring(matcher.end(1));
            }
        }
        return out;
    }
}
