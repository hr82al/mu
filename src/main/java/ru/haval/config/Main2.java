package ru.haval.config;

import ru.haval.db._connect;
import ru.haval.share_class.s_class;

import java.time.LocalDate;

public class Main2 {



    public static void main(String[] args) {
        _connect.init();
        s_class.updatePmYearDates("4356", LocalDate.parse("2020-12-21"), "SAV", "KP1");

    }
}
