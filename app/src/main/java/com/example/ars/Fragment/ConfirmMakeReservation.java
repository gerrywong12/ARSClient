package com.example.ars.Fragment;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ars.Activity.Login;
import com.example.ars.Activity.SignUp;
import com.example.ars.Interface.MakeReservationInterface;
import com.example.ars.Interface.SignUpInterface;
import com.example.ars.Model.MakeReservationModel;
import com.example.ars.Model.SignUpModel;
import com.example.ars.Model.UserModel;
import com.example.ars.Model.UserProfileModel;
import com.example.ars.R;
import com.example.ars.Response.MakeReservationResponse;
import com.example.ars.Response.ReservationsDataSet;
import com.example.ars.Response.SignUpResponse;
import com.example.ars.RetrofitInstance;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmMakeReservation extends Fragment {

    ArrayList<String> startBooking;
    ArrayList<String> endBooking;
    List<ReservationsDataSet> reservations = new ArrayList<>();
    Button btnSelectTime;
    Button btnSelectEndTime;
    TextInputLayout selectedTime;
    TextInputLayout selectedEndTime;
    Button btnMakeReservation;
    Bundle bundle = new Bundle();
    public ConfirmMakeReservation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirm_make_reservation, container, false);
        selectedTime = (TextInputLayout) view.findViewById(R.id.tf_bookingTime);
        btnSelectTime = (Button) view.findViewById(R.id.btn_selectTime);
        selectedEndTime = (TextInputLayout) view.findViewById(R.id.tf_end_bookingTime);
        btnSelectEndTime = (Button) view.findViewById(R.id.btn_selectEndTime);
        btnMakeReservation = (Button) view.findViewById(R.id.btn_make_reservation);
        int user_id = getArguments().getInt("USER_ID");
        bundle.putInt("USER_ID", user_id);
        if (getArguments() != null) {
            startBooking = getArguments().getStringArrayList("startBooking");
            endBooking = getArguments().getStringArrayList("endBooking");
            System.out.println(startBooking);
            System.out.println(endBooking);

            for(int i = 0; i<startBooking.size(); i++){
                ReservationsDataSet reservationsDataSet = new ReservationsDataSet(startBooking.get(i), endBooking.get(i));
                reservations.add(reservationsDataSet);
            }

            showTimeDialog("start");
        }

        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String start = "start";
                showTimeDialog(start);
            }
        });

        btnSelectEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String end = "end";
                showTimeDialog(end);
            }
        });
        btnMakeReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeReservation();
            }
        });

        return view;
    }

    public void showTimeDialog(String startOrEnd){
        DialogFragment timePicker = new TimePickerFragment(startOrEnd);

        timePicker.show(getFragmentManager(), "Time Picker");

    }
    public void makeReservation(){
        int table_type_id;
        String endBookingDateTime;
        String startBookingDateTime;
        int user_profile_id_id;
        int number_of_pax;

        String start = selectedTime.getEditText().getText().toString();
        String end = selectedEndTime.getEditText().getText().toString();



        if (getArguments() != null) {
            table_type_id = getArguments().getInt("table_type_id");
            endBookingDateTime = getArguments().getString("selected_date");
            startBookingDateTime = getArguments().getString("selected_date");
            table_type_id = getArguments().getInt("table_type_id");
            number_of_pax = getArguments().getInt("number_of_pax");
            user_profile_id_id = getArguments().getInt("USER_ID");


            endBookingDateTime = endBookingDateTime + " " + end + ":00";
            startBookingDateTime = startBookingDateTime + " " + start + ":00";

            System.out.println(table_type_id);
            System.out.println(endBookingDateTime);
            System.out.println(startBookingDateTime);
            System.out.println(user_profile_id_id);
            System.out.println(number_of_pax);


//            if (){
                MakeReservationInterface makeReservationInterface = RetrofitInstance.getRetrofitInstance().create(MakeReservationInterface.class);

                MakeReservationModel model = new MakeReservationModel(table_type_id, endBookingDateTime, startBookingDateTime, user_profile_id_id, number_of_pax);
                Call<MakeReservationResponse> res = makeReservationInterface.Reserve(model);

                res.enqueue(new Callback<MakeReservationResponse>() {
                    @Override
                    public void onResponse(Call<MakeReservationResponse> call, Response<MakeReservationResponse> response) {
                        try{
                            String response_param = response.body().getMessage().toString();
                            System.out.println("RESPONSE = " + response_param);
                            Toast toast = Toast.makeText(getContext(), response_param, Toast.LENGTH_LONG);
                            toast.show();
                        }catch (Exception e){

                        }

                        Log.v("RESPONSE_CALLED", "ON_RESPONSE_CALLED");
                        String didItWork = String.valueOf(response.isSuccessful());
                        Log.v("SUCCESS?", didItWork);
                        Log.v("RESPONSE_CODE", String.valueOf(response.code()));
                        Log.v("ERROR_BODY", String.valueOf(response.errorBody()));
                        Log.v("RAW", String.valueOf(response.raw()));
                        Log.v("MESSAGE", String.valueOf(response.message()));

                        if (String.valueOf(response.code()).equals("200")){
                            MakeReservation makeReservationFragment = new MakeReservation();
                            makeReservationFragment.setArguments(bundle);
                            getFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, makeReservationFragment).commit();
                        }

                    }

                    @Override
                    public void onFailure(Call<MakeReservationResponse> call, Throwable t) {
                        System.out.println("ERROR");
                        System.out.println(t.getMessage());
                        Log.d("Error", t.getMessage());
                    }
                });
//            }

        }
    }

}
