package lowes.ak2006.com.lowesbandg;

import android.content.Context;

/**
 * Created by binair on 7/15/15.
 */
public class WebCallbackHandler implements WebCallBack{


    @Override
    public void afterLogin(Context context) {
        AfterLoginHandler afterLoginHandler = new AfterLoginHandler(context);
        afterLoginHandler.doAfterLogin();
    }

    @Override
    public void afterRegistration(Context context) {

    }
}
