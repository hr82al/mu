package ru.haval.filter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLFilter {
    private final String variablesAndTheirValues;
    private ObservableList<String> vars;
    private Map<String, String> variableValues;
    private String query;

    private SQLFilter(String variablesAndTheirValues) {
        this.variablesAndTheirValues = variablesAndTheirValues;
    }

    public static SQLFilter setSqlFilter(String query) {
        Pattern pattern = Pattern.compile("set(.+);");
        Matcher matcher = pattern.matcher(query);
        if (matcher.find()) {
            SQLFilter result = new SQLFilter(matcher.group(1));
            //init vars
            result.vars = FXCollections.observableArrayList();
            result.variableValues = new HashMap<>();

            result.query = query;

            result.updateVariablesAndTheirValues();
            return result;
        }
        return null;

    }

    private void updateVariablesAndTheirValues() {
        vars.clear();
        variableValues.clear();
//FIXME if severals words
        Pattern pattern = Pattern.compile("@([^\\s]+)\\s*=\\W*(\\w+)");
        Matcher matcher = pattern.matcher(variablesAndTheirValues);
        while (matcher.find()) {
            vars.add(matcher.group(1));
            variableValues.put(matcher.group(1), matcher.group(2));
        }
    }

    public ObservableList<String> getVars() {
        return vars;
    }

    public String getVariableValueByName(String name) {
        return variableValues.get(name);
    }

    public String changeVariable(String variable_name, String newValue) {
        Pattern pattern = Pattern.compile("@" + variable_name + "\\s*=.*?'?%?([^%';]*?)%?'?[,;]");
        Matcher matcher = pattern.matcher(query);
        if (matcher.find()) {
            variableValues.put(variable_name, newValue);
            query = query.substring(0, matcher.start(1)) + newValue + query.substring(matcher.end(1));
        }
        return query;
    }

    private String getFullValueByName(String name) {
        Pattern pattern = Pattern.compile("@" + name + "\\s*=.*?('?%?[^%';]*?%?'?)[,;]");
        Matcher matcher = pattern.matcher(query);
        while (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public String getQuery() {
        String outQuery = query;
        Pattern pattern = Pattern.compile("set.+?;");
        Matcher matcher = pattern.matcher(outQuery);
        if (matcher.find()) {
            outQuery = matcher.replaceFirst("");
        }
        else {
            return query;
        }
        for (Map.Entry<String, String> entry: variableValues.entrySet()) {
            outQuery = outQuery.replace("@" + entry.getKey(), getFullValueByName(entry.getKey()));
        }
        return outQuery;
    }
}
