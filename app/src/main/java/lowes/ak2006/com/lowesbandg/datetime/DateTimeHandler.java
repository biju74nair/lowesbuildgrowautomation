package lowes.ak2006.com.lowesbandg.datetime;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    boolean dateSet;
    boolean timeSet;

    public DateTimeHandler(TextView dateTxt, TextView timeTxt) {
        this.dateTxt = dateTxt;
        this.timeTxt = timeTxt;
        dateSet = false;
        timeSet = false;
    }

    public boolean isSet(){
        return dateSet && timeSet;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear,
                          int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DATE, dayOfMonth);

        dateTxt.setText(DATE_FORMAT.format(calendar.getTime()));

        dateSet = true;

     }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        timeTxt.setText(TIME_FORMAT.format(calendar.getTime()));
        timeSet = true;

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

    public static Date parseDateAndTime(String dateAndTime){
        try {
            Date date = DATE_TIME_FORMAT.parse(dateAndTime);
            return date;
        }catch(ParseException pe){

        }
        return null;
    }

 }
