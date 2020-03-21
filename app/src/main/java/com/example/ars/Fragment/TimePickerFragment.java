package com.example.ars.Fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ars.R;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    TextInputLayout selectedTime;
    String startOrEnd;

    public TimePickerFragment(String startOrEnd) {
        this.startOrEnd = startOrEnd;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    System.out.println(i);
                    System.out.println(i1);
                    timePicker.setHour(i);
                    timePicker.setMinute(i1);

                    DecimalFormat df = new DecimalFormat("00");
                    String c = df.format(i);
                    String a = df.format(i1);

                    if (startOrEnd.equalsIgnoreCase("start")){
                        TextInputLayout textInputLayout = (TextInputLayout) getActivity().findViewById(R.id.tf_bookingTime);
                        textInputLayout.getEditText().setText(c+":"+a);
                    } else {
                        TextInputLayout textInputLayout = (TextInputLayout) getActivity().findViewById(R.id.tf_end_bookingTime);
                        textInputLayout.getEditText().setText(c+":"+a);
                    }


            }
        }, hour, minute, true);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

    }
}
