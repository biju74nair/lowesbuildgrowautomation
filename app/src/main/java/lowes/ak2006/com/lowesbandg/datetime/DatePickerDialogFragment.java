package lowes.ak2006.com.lowesbandg.datetime;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import java.util.Calendar;

import lowes.ak2006.com.lowesbandg.SharedPreferenceCalendarUtil;

public class DatePickerDialogFragment extends DialogFragment {

    private OnDateSetListener mDateSetListener;
    private Context context;

    public DatePickerDialogFragment(){

    }
    public void setContext(Context context) {
        this.context = context;
    }
    public void setDateSetListener(OnDateSetListener callback) {
        mDateSetListener = (OnDateSetListener) callback;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar cal = SharedPreferenceCalendarUtil.getScheduledTimeAsCalendar(context);
        if(cal == null) cal = Calendar.getInstance();

        return new DatePickerDialog(getActivity(),
                mDateSetListener, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    }

}