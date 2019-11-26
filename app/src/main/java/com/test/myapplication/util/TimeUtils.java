package com.test.myapplication.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static String timestampToDate(long timestamp) {
        Date date = new Date(timestamp * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM");
        return simpleDateFormat.format(date);
    }

    public static String timestampToDay(long timestamp) {
        Date date = new Date(timestamp * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        return simpleDateFormat.format(date);
    }

    public static String timestampToTime(long timestamp) {
        Date date = new Date(timestamp * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        return simpleDateFormat.format(date);
    }

    public static String timestampToHour(long timestamp) {
        Date date = new Date(timestamp * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h a");
        return simpleDateFormat.format(date);
    }
}
