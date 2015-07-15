package lowes.ak2006.com.lowesbandg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by binair on 6/3/15.
 */
public class AlarmBootReceiver extends BroadcastReceiver {

    AlarmReceiver alarmReceiver = new AlarmReceiver();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

              if(SharedPreferenceCalendarUtil.isScheduled(context)){
                alarmReceiver.setAlarm(context, SharedPreferenceCalendarUtil.getScheduledTimeAsLong(context));
            }

         }
    }
}
