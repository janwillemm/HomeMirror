package com.morristaedt.mirror.utils;

import java.util.Calendar;

/**
 * Created by HannahMitt on 8/23/15.
 */
public class WeekUtil {

    public static boolean isWeekday() {
        return getCurrentDay() != Calendar.SATURDAY && getCurrentDay() != Calendar.SUNDAY;
    }

    public static boolean isMonday(){
        return getCurrentDay() == Calendar.MONDAY;
    }

    public static boolean isWorkDay(){
        return getCurrentDay() == Calendar.WEDNESDAY || getCurrentDay() == Calendar.THURSDAY;
    }

    public static boolean isFriday(){
        return getCurrentDay() == Calendar.FRIDAY;
    }

    private static int getCurrentDay(){
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    private static int getCurrentHour(){
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
}
