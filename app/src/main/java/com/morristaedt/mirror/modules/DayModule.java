package com.morristaedt.mirror.modules;

import android.text.Html;
import android.text.Spanned;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by HannahMitt on 8/23/15.
 */
public class DayModule {

    public static String getDay() {
        SimpleDateFormat formatDayOfMonth = new SimpleDateFormat("EEE d MMMM", new Locale("nl"));
        Calendar now = Calendar.getInstance();
        return formatDayOfMonth.format(now.getTime());
    }

}
