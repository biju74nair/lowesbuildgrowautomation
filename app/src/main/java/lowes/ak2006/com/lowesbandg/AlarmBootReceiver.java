package lowes.ak2006.com.lowesbandg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import lowes.ak2006.com.lowesbandg.datetime.DateTimeHandler;

/**
 * Created by binair on 6/3/15.
 */
public class AlarmBootReceiver extends BroadcastReceiver {

    AlarmReceiver alarmReceiver = new AlarmReceiver();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.sharedPrefFile),Context.MODE_PRIVATE);

            boolean isscheduled = sharedPref.getBoolean(context.getString(R.string.isscheduled),false);
            if(isscheduled){
                alarmReceiver.setAlarm(context, DateTimeHandler.getTimeFromShared(context,sharedPref));
            }

         }
    }
}
