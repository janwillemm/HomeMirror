package com.morristaedt.mirror.modules;

import android.content.res.Resources;
import android.os.AsyncTask;

import com.morristaedt.mirror.R;
import com.morristaedt.mirror.requests.PublicTransitRequest;
import com.morristaedt.mirror.requests.PublicTransitResponse;
import com.morristaedt.mirror.utils.DayUtil;
import com.morristaedt.mirror.utils.WeekUtil;

import java.util.Calendar;
import java.util.Date;

import retrofit.RestAdapter;

/**
 * Created by jw on 29/09/15.
 */
public class PublicTransitModule {
    public interface PublicTransitListener {
        void onTransitUpdate(String delft, String pijnacker);
    }

    public static void getTrainTimes(final Resources resources, final PublicTransitListener publicTransitListener) {
        new AsyncTask<Void, Void, String[]>() {

            @Override
            public void onPostExecute(String[] s){
                if(s != null) {
                    publicTransitListener.onTransitUpdate(s[0], s[1]);
                }
            }

            @Override
            protected String[] doInBackground(Void... params) {

                String arrivalId = "";
                int arrivalTime;
                String[] result = new String[2];

                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint("https://maps.googleapis.com/maps/api/directions")
                        .build();
                PublicTransitRequest service = restAdapter.create(PublicTransitRequest.class);
                String key = resources.getString(R.string.google_directions_key);
                String mode = "transit";
                String language = "nl";

                Date dNow = new Date( ); // Instantiate a Date object
                Calendar cal = Calendar.getInstance();
                cal.setTime(dNow);

                if((WeekUtil.isMonday() && DayUtil.isNowBetween(11, 14))){
                    // Going to Erasmus
                    arrivalId = resources.getString(R.string.locatie_erasmus);
                    cal.set(Calendar.HOUR_OF_DAY,14);
                    cal.set(Calendar.MINUTE, 00);
                }
                else if(WeekUtil.isWorkDay() && DayUtil.isNowBetween(7,9)){
                    // Going to RTM Media
                    arrivalId = resources.getString(R.string.locatie_rtmmedia);
                    cal.set(Calendar.HOUR_OF_DAY,9);
                    cal.set(Calendar.MINUTE,00);
                }
                else if(WeekUtil.isFriday() && DayUtil.isNowBetween(7,9)){
                    // Going to Magnet.me
                    arrivalId = resources.getString(R.string.locatie_magnetme);
                    cal.set(Calendar.HOUR_OF_DAY,8);
                    cal.set(Calendar.MINUTE,55);

                }
                else {
                    return null;
                }
                long time = cal.getTimeInMillis()/1000L;

                PublicTransitResponse delft = service.getTransitData(resources.getString(R.string.locatie_delft_station), arrivalId, mode, key, time, language);
                PublicTransitResponse pijnacker = service.getTransitData(resources.getString(R.string.locatie_pijnacker_station), arrivalId, mode, key, time, language);

                System.out.println(delft.toString());


                result[0] = delft.getDepartureTime();
                result[1] = pijnacker.getDepartureTime();

                return result;
            }

        }.execute();
    }

}
