package ru.haval.config;

import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        System.out.println(Config.getInstance().getDbPassword());
    }
}
