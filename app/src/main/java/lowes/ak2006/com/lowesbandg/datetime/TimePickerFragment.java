package lowes.ak2006.com.lowesbandg.datetime;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;

import java.util.Calendar;

import lowes.ak2006.com.lowesbandg.SharedPreferenceCalendarUtil;

/**
 * Created by binair on 6/3/15.
 */
public class TimePickerFragment extends DialogFragment{


    TimePickerDialog.OnTimeSetListener onTimeSetListener;

    private Context context;

    public TimePickerFragment(){

    }
    public void setContext(Context context) {
        this.context = context;
    }
    public void setOnTimeSetListener(TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        this.onTimeSetListener = onTimeSetListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        Calendar c = SharedPreferenceCalendarUtil.getScheduledTimeAsCalendar(context);
        if(c == null) c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this.onTimeSetListener, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

}
