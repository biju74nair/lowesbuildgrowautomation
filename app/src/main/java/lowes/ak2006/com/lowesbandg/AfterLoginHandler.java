package lowes.ak2006.com.lowesbandg;

import android.content.Context;
import android.content.Intent;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by binair on 7/15/15.
 */
public class AfterLoginHandler {
    private static final String REGISTER_HTML = "registerButtonClick(this);return false;";

    private final ScheduledExecutorService registrationLookupService =
            Executors.newScheduledThreadPool(1);
    private ScheduledFuture registrationLookupHandle = null;

    long startTime = 0;

    long TIME_TO_TRY = TimeUnit.MINUTES.toMillis(10);

    Context context;

    AfterLoginHandler(Context context){
        this.context = context;
    }

    Runnable registrationLookup = new Runnable(){
        @Override
        public void run() {
            if(System.currentTimeMillis() - startTime >= TIME_TO_TRY){
                if(registrationLookupHandle != null)
                    registrationLookupHandle.cancel(true);
                Notification.notify(context,"Exiting after max try of registration status");
                return;
            }

            boolean didRegistrationStart = checkForRegistration();
            if(didRegistrationStart){
                if(registrationLookupHandle != null)
                    registrationLookupHandle.cancel(true);
                startRegistration();
            }
        }
    };

    public void doAfterLogin(){
        startTime = System.currentTimeMillis();
        registrationLookupHandle =
                registrationLookupService.scheduleAtFixedRate(registrationLookup, 1, 1, TimeUnit.SECONDS);
    }
    private void startRegistration() {
        Intent lbgIntent = new Intent(context, MainActivity.class);
        lbgIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(lbgIntent);
    }

    private boolean checkForRegistration() {

        boolean registerBtnPresent = false;

        HttpURLConnection urlConnection = null;

        try{

            URL url = new URL(context.getString(R.string.ZIPCODE_PAGE));

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setChunkedStreamingMode(0);

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            registerBtnPresent = readStream(in);
        } catch (Throwable t){

        }  finally {
            urlConnection.disconnect();
        }

        return registerBtnPresent;

    }

    private boolean readStream(InputStream in) {
        boolean registerBtnPresent = false;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                if(line.indexOf(REGISTER_HTML) != -1){
                    registerBtnPresent = true;
                    break;
                }
            }
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
        return registerBtnPresent;
    }
}
