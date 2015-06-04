package lowes.ak2006.com.lowesbandg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import lowes.ak2006.com.lowesbandg.datetime.DatePickerDialogFragment;
import lowes.ak2006.com.lowesbandg.datetime.DateTimeHandler;
import lowes.ak2006.com.lowesbandg.datetime.TimePickerFragment;


public class FirstActivity extends Activity implements View.OnClickListener{

    AlarmReceiver alarm = new AlarmReceiver();
    DateTimeHandler dateTimeHandler;

    Button manualStartBtn;
    Button scheduleButton;
    Button cancelSchButton;
    Button dateButton;
    Button timeButton;
    TextView dateTxt;
    TextView timeTxt;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        manualStartBtn = (Button)findViewById(R.id.manualStartBtn);
        scheduleButton = (Button)findViewById(R.id.scheduleBtn);
        cancelSchButton = (Button)findViewById(R.id.cancelSchBtn);
        dateButton = (Button)findViewById(R.id.dateBtn);
        timeButton = (Button)findViewById(R.id.timeBtn);

        dateTxt = (TextView)findViewById(R.id.dateTxt);
        timeTxt = (TextView)findViewById(R.id.timeTxt);
        status= (TextView)findViewById(R.id.status);

        manualStartBtn.setOnClickListener(this);
        scheduleButton.setOnClickListener(this);
        cancelSchButton.setOnClickListener(this);
        dateButton.setOnClickListener(this);
        timeButton.setOnClickListener(this);

        dateTimeHandler = new DateTimeHandler(dateTxt,timeTxt);


        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.sharedPrefFile), Context.MODE_PRIVATE);

        boolean isscheduled = sharedPref.getBoolean(getString(R.string.isscheduled),false);
        if(isscheduled){
            status.setText("Already scheduled at displayed date/time");
        }

        dateTxt.setText(sharedPref.getString(getString(R.string.scheduledDate), ""));
        timeTxt.setText(sharedPref.getString(getString(R.string.scheduledTime), ""));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.manualStartBtn:
                Intent lbgIntent = new Intent(this, MainActivity.class);
                lbgIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(lbgIntent);
                break;
             case R.id.scheduleBtn:
                if(dateTimeHandler.isSet()){
                    if (alarm.setAlarm(this, dateTimeHandler.getTime())) {
                        Toast.makeText(this, "Scheduled", Toast.LENGTH_LONG).show();
                        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.sharedPrefFile), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putBoolean(getString(R.string.isscheduled), true);
                        editor.putString(getString(R.string.scheduledDate), dateTimeHandler.getDateAsString());
                        editor.putString(getString(R.string.scheduledTime), dateTimeHandler.getTimeAsString());
                        editor.commit();
                    }
                } else {
                    Toast.makeText(this, "Date/Time not selected or changed", Toast.LENGTH_LONG).show();
                }
                 break;
             case R.id.cancelSchBtn:
                alarm.cancelAlarm(this);
                 Toast.makeText(this, "Canceled", Toast.LENGTH_LONG).show();
                 break;
            case R.id.dateBtn :
                DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
                datePickerDialogFragment.setDateSetListener(dateTimeHandler);
                datePickerDialogFragment.show(getFragmentManager(), "datePicker");
                break;
            case R.id.timeBtn :
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setOnTimeSetListener(dateTimeHandler);
                timePickerFragment.show(getFragmentManager(), "timePicker");
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
