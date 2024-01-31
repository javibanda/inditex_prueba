package com.inditex.zarachallenge.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilDate {

    public static Timestamp parseTimeStamp(String stringDate){
        return Timestamp.valueOf(
                LocalDateTime.parse(stringDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")));
    }
}
