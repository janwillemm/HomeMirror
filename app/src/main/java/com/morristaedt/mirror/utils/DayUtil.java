package com.morristaedt.mirror.utils;

import java.util.Calendar;

/**
 * Created by jw on 29/09/15.
 */
public class DayUtil {

    private static int getCurrentHour(){
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public static boolean isNowBetween(int low, int high){
        return getCurrentHour() >= low && getCurrentHour() <= high;
    }

    public static boolean afterFive(){
        return getCurrentHour() >= 17;
    }
}
