package com.example.ars.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ars.Interface.GetReservationsForBookingInterface;
import com.example.ars.Model.PostReservationDateModel;
import com.example.ars.R;
import com.example.ars.Response.GetReservationsForBookingResponse;
import com.example.ars.Response.ReservationsDataSet;
import com.example.ars.RetrofitInstance;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MakeReservation extends Fragment {

    TextInputLayout bookingDate;
    Button datePicker;
    TextInputLayout numberOfPax;
    Button btnNumberOfPax;

    private String selectedDate;
    private String selectedPax;
    private int table_id;
    private List<ReservationsDataSet> reservationsDataSetForFragment;
    Bundle bundle = new Bundle();
    private int user_id;



    public MakeReservation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_make_reservation, container, false);

        if (getArguments() != null) {
            user_id = getArguments().getInt("USER_ID");
        }

        bookingDate = (TextInputLayout) view.findViewById(R.id.tf_bookingDate);
        datePicker = (Button) view.findViewById(R.id.btn_selectDate);
        numberOfPax = (TextInputLayout) view.findViewById(R.id.tf_number_of_pax);
        btnNumberOfPax = (Button) view.findViewById(R.id.btnConfirmPax);

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC+8"));
        calendar.clear();

        Long today = MaterialDatePicker.todayInUtcMilliseconds();

        calendar.setTimeInMillis(today);

        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        Long january = calendar.getTimeInMillis();

        int now = Calendar.getInstance().get(Calendar.MONTH);

        calendar.set(Calendar.MONTH, now+2);
        Long december = calendar.getTimeInMillis();

//        Calendar date = Calendar.getInstance();
//        date.set(Calendar.YEAR, 1999);
//        Long dateToShow = date.getTimeInMillis();


        //constraints
        CalendarConstraints.Builder constraintBuilder = new CalendarConstraints.Builder();
        constraintBuilder.setStart(january);
        constraintBuilder.setEnd(december);
        constraintBuilder.setValidator(DateValidatorPointForward.now());

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select Your Birth Date");
//        builder.setSelection(dateToShow);
        builder.setCalendarConstraints(constraintBuilder.build());
        final MaterialDatePicker materialDatePicker = builder.build();

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getFragmentManager(), "DATE_PICKER");
            }
        });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                bookingDate.getEditText().setText(materialDatePicker.getHeaderText());
            }
        });

        btnNumberOfPax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    selectedDate = bookingDate.getEditText().getText().toString();
                    selectedPax = numberOfPax.getEditText().getText().toString();


                    Date date1=new SimpleDateFormat("dd MMM yyy").parse(selectedDate);
                    System.out.println(selectedDate+"\t"+date1);

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    final String selectedDate1 = formatter.format(date1);

                    if (Integer.parseInt(selectedPax)>= 4 && Integer.parseInt(selectedPax) > 0) {
                        table_id=1;
                    } else if (Integer.parseInt(selectedPax) > 4 && Integer.parseInt(selectedPax)<= 8){
                        table_id=2;
                    } else {
                        table_id=3;
                    }

                    try {
                        GetReservationsForBookingInterface getReservationsForBookingInterface = RetrofitInstance.getRetrofitInstance().create(GetReservationsForBookingInterface.class);
                        PostReservationDateModel postReservationDateModel = new PostReservationDateModel(table_id, selectedDate1);

                        Call<GetReservationsForBookingResponse> res = getReservationsForBookingInterface.GetReservations(postReservationDateModel);

                        res.enqueue(new Callback<GetReservationsForBookingResponse>() {
                            @Override
                            public void onResponse(Call<GetReservationsForBookingResponse> call, Response<GetReservationsForBookingResponse> response) {
                                try{
                                    String response_param = response.body().toString();
                                    Log.v("RESPONSE_CALLED", "ON_RESPONSE_CALLED");
                                    String didItWork = String.valueOf(response.isSuccessful());
                                    System.out.println("RESPONSEE" + response.body());
                                    try {
                                        List<ReservationsDataSet> reservationsDataSet = response.body().getReservationsDataSet();
                                        int numberOfTable = response.body().getNumberOfTable();
                                        System.out.println(reservationsDataSet);
                                        System.out.println(numberOfTable);

                                        reservationsDataSetForFragment = reservationsDataSet;

                                        ArrayList<String> startBookingTime = new ArrayList<String>();
                                        ArrayList<String> endBookingTime = new ArrayList<String>();

                                        for(ReservationsDataSet reservationsDataSet1:reservationsDataSet){
                                            startBookingTime.add(reservationsDataSet1.getStartBookingDateTime());
                                            endBookingTime.add(reservationsDataSet1.getEndBookingDateTime());
                                        }

                                        bundle.putStringArrayList("startBooking", startBookingTime);
                                        bundle.putStringArrayList("endBooking", endBookingTime);
                                        bundle.putInt("table_type_id", table_id);
                                        bundle.putString("selected_date", selectedDate1);
                                        bundle.putInt("number_of_pax", Integer.parseInt(selectedPax));
                                        bundle.putInt("USER_ID", user_id);



                                        ConfirmMakeReservation confirmMakeReservation = new ConfirmMakeReservation();
                                        confirmMakeReservation.setArguments(bundle);
                                        getFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, confirmMakeReservation).commit();

                                    }
                                    catch (Exception e){
                                        System.out.println("ADA ERROR = " + e);
                                    }
//                        }

                                }catch (Exception e){
                                    System.out.println("ADA ERROR = " + e);
                                }


                            }

                            @Override
                            public void onFailure(Call<GetReservationsForBookingResponse> call, Throwable t) {
                                System.out.println("ERROR");
                                System.out.println(t.getMessage());
                                Log.d("Error", t.getMessage());
                            }
                        });
                    }catch (Exception e){

                    }


                } catch (Exception e) {

                }
            }
        });

        return view;
    }


}
