package lowes.ak2006.com.lowesbandg;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by binair on 6/2/15.
 */
public class LowesWebViewClient extends WebViewClient {

    Context context;
    WebCallBack webCallBack;

    boolean defaultPageAccessed = false;

    LowesWebViewClient(Context context){
        this.context = context;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
    @JavascriptInterface
    public void log(String data){
        Log.i("JSLOG",data);
    }

    @JavascriptInterface
    public void notify(String data, boolean closeApp){

        Notification.notify(context,data);
        if(closeApp){
            closeApp();
        }

    }

    public void closeApp(){
        ((Activity)context).finish();
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        Log.i("TEST",url);

        String javascript = null;

        if(url.startsWith(context.getString(R.string.ZIPCODE_PAGE))) {
            if(defaultPageAccessed){
                //After signout, close the app
                closeApp();
            } else {
                javascript = context.getString(R.string.enterZipCode);
                defaultPageAccessed = true;
            }
        } else if(url.startsWith(context.getString(R.string.LOCAL_CLINICS))) {
            javascript = context.getString(R.string.selectLocation);
        }else if(url.startsWith(context.getString(R.string.SIGNIN_PAGE))) {
            javascript = context.getString(R.string.enterCredentials);
        }else if(url.startsWith(context.getString(R.string.SELECT_KIDS))) {
            javascript = context.getString(R.string.selectKids);
        } else if(url.startsWith(context.getString(R.string.CONFIRMATION_PAGE))) {
            notify("Successfully Registered", true);
            webCallBack.afterRegistration(context);
        } else if(url.startsWith(context.getString(R.string.MYCLINICS))) {
            notify("Successfully Logged-in", true);
            webCallBack.afterLogin(context);
        }

        if(javascript != null){
            view.loadUrl("javascript:(function() { " +
                    javascript + "})()");
        }
    }

    public void setWebCallBack(WebCallBack webCallBack) {
        this.webCallBack = webCallBack;
    }
}