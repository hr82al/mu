package ru.haval.config;

import net.sf.jasperreports.engine.util.FileBufferedWriter;

import java.io.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import ru.haval.action.apwr_controller;

import static ru.haval.action.apwr_controller.getLastDateFromFile;
import static ru.haval.action.apwr_controller.writeDate;

public class Main2 {
    public static void main(String[] args) {
        System.out.println(getLastDateFromFile());
        Map<String, String> env = System.getenv();
        String localLastDateFile = env.get("APPDATA")  + "\\last_date.txt";
        String remoteLastDateFile = "\\\\10.168.150.74\\mu\\updates\\last_date.txt";

        //writeDate("2020-09-23", remoteLastDateFile);

    }

}
