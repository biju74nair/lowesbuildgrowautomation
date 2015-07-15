package lowes.ak2006.com.lowesbandg;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by binair on 6/3/15.
 */
public class SchedulingService extends IntentService {

    public SchedulingService() {
        super("SchedulingService");
    }

    public static final String TAG = "Scheduling LB&G";


    @Override
    protected void onHandleIntent(Intent intent) {

        doLogin();

            // Release the wake lock provided by the BroadcastReceiver.
        AlarmReceiver.completeWakefulIntent(intent);
    }

    private void doLogin() {
        Intent login = new Intent(this, LoginActivity.class);
        login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(login);
    }


}
