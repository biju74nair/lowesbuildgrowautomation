package lowes.ak2006.com.lowesbandg.datetime;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment {

    private OnDateSetListener mDateSetListener;

    public DatePickerDialogFragment() {
        // nothing to see here, move along
    }

    public void setDateSetListener(OnDateSetListener callback) {
        mDateSetListener = (OnDateSetListener) callback;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();

        return new DatePickerDialog(getActivity(),
                mDateSetListener, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    }

}