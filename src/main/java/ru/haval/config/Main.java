package ru.haval.config;

import java.util.Base64;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> env = System.getenv();
        String s  = env.get("APPDATA");
        System.out.println(s);
    }
}
