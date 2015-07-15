package lowes.ak2006.com.lowesbandg;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

/**
 * Created by binair on 7/15/15.
 */
public class Notification {

    private static final int NOTIFICATION_ID = 1234;

    public static void notify(Context context, String message){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Build & Grow Notification")
                        .setContentText(message);


        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());


    }
}
