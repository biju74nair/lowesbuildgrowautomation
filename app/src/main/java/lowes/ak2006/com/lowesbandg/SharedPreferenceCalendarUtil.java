package lowes.ak2006.com.lowesbandg;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Date;

import lowes.ak2006.com.lowesbandg.datetime.DateTimeHandler;

/**
 * Created by binair on 6/4/15.
 */
public class SharedPreferenceCalendarUtil {

    public static boolean isScheduled(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.sharedPrefFile),Context.MODE_PRIVATE);
        boolean isscheduled = sharedPreferences.getBoolean(context.getString(R.string.isscheduled),false);
        return isscheduled;
    }


    public static long getScheduledTimeAsLong(Context context){
        Calendar calendar = getScheduledTimeAsCalendar(context);
         return calendar.getTime().getTime();
    }

    public static Calendar getScheduledTimeAsCalendar(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.sharedPrefFile),Context.MODE_PRIVATE);
       String dateStr =  sharedPreferences.getString(context.getString(R.string.scheduledDate), "");
        String timeStr =  sharedPreferences.getString(context.getString(R.string.scheduledTime), "");

        String dt = dateStr +" "+timeStr;

        Date parsedDate = DateTimeHandler.parseDateAndTime(dt);

        if(parsedDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);
            return calendar;
        }

        return null;
    }


}
