package com.morristaedt.mirror.modules;

import java.util.Calendar;

/**
 * Created by rik on 28/09/15.
 */
public class DimModule  {

    public interface DimListener {
        void onDim(float brightnessValue);
    }

    private DimListener listener;

    public DimModule()
    {

    }


    public void getScreenBrightness(DimListener listener) {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        if(hour > 22 || hour < 7) {
            listener.onDim(0.1f);
        }
        else
        {
            listener.onDim(1.0f);
        }
    }
}
