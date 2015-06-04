package lowes.ak2006.com.lowesbandg.datetime;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import lowes.ak2006.com.lowesbandg.R;

/**
 * Created by binair on 6/3/15.
 */
public class DateTimeHandler implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    private static final String dFORMAT = "MM-dd-yyyy";
    private static final String tFORMAT = "hh:mm a";
    private static final String dtFORMAT = dFORMAT + " "+ tFORMAT;


    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(dFORMAT);
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(tFORMAT);

    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(dtFORMAT);

    Calendar calendar = new GregorianCalendar();

    TextView dateTxt;
    TextView timeTxt;

    boolean isdateSet;
    boolean isTimeSet;

    public DateTimeHandler(TextView dateTxt, TextView timeTxt) {
        this.dateTxt = dateTxt;
        this.timeTxt = timeTxt;
    }

    public boolean isSet(){
        return isdateSet && isTimeSet;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear,
                          int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DATE, dayOfMonth);

        dateTxt.setText(DATE_FORMAT.format(calendar.getTime()));

        isdateSet = true;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        timeTxt.setText(TIME_FORMAT.format(calendar.getTime()));

        isTimeSet = true;
    }

    public String getDateAsString(){
        return DATE_FORMAT.format(calendar.getTime());
    }

    public String getTimeAsString(){
        return TIME_FORMAT.format(calendar.getTime());
    }

    public long getTime(){
        return calendar.getTime().getTime();
    }

    public static long getTimeFromShared(Context context, SharedPreferences sharedPreferences){
        String dateStr =  sharedPreferences.getString(context.getString(R.string.scheduledDate), "");
        String timeStr =  sharedPreferences.getString(context.getString(R.string.scheduledTime), "");

        String dt = dateStr +" "+timeStr;
        long ltime = -1;
        try {
            Date date = DATE_TIME_FORMAT.parse(dt);
            ltime = date.getTime();
        }catch(ParseException pe){

        }
        return ltime;
    }

}
