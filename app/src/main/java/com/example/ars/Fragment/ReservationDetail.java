package com.example.ars.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ars.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationDetail extends Fragment {

    int clickedReservationId = 1;
    String clickedReservationCode = "RS000000";

    int clickedReservationUserId = 2;
    String clickedEndBookingDate = "";
    int clickedNumberOfPax = 1;
    String clickedStartBookingDate = "";
    int clickedTableType = 1;

    TextView reservation_id_tv;
    TextView reservation_code_tv;
    TextView reservation_numberOfPax_tv;
    TextView reservation_startBookingDate_tv;
    TextView reservation_startTime_tv;
    TextView reservation_endTime_tv;

    public ReservationDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservation_detail, container, false);
        if (getArguments() != null) {
            clickedReservationId = getArguments().getInt("clickedReservationId");
            clickedReservationCode = getArguments().getString("clickedReservationCode");
            clickedReservationUserId = getArguments().getInt("clickedReservationUserId");
            clickedEndBookingDate = getArguments().getString("clickedEndBookingDate");
            clickedNumberOfPax = getArguments().getInt("clickedNumberOfPax");
            clickedStartBookingDate = getArguments().getString("clickedStartBookingDate");
            clickedTableType = getArguments().getInt("clickedTableType");
        }




//        reservation_id_tv.setText(Integer.toString(clickedReservationId) + " Details");
        reservation_code_tv = (TextView) view.findViewById(R.id.reservation_code);
        reservation_endTime_tv = (TextView) view.findViewById(R.id.reservation_EndTime);
        reservation_startTime_tv = (TextView) view.findViewById(R.id.reservation_StartTime);
        reservation_startBookingDate_tv = (TextView) view.findViewById(R.id.reservation_startDate);
        reservation_numberOfPax_tv = (TextView) view.findViewById(R.id.reservation_number_of_pax);

        reservation_code_tv.setText(clickedReservationCode);
        reservation_numberOfPax_tv.setText(Integer.toString(clickedNumberOfPax));

        try{
            String displayDate;
            String startTime;
            String endTime;

            Date date1=new SimpleDateFormat("yyyy-mm-dd").parse(clickedStartBookingDate.substring(0, 10));
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd MMM yyyy");
            displayDate = formatter.format(date1);

            Date time1=new SimpleDateFormat("hh:mm").parse(clickedStartBookingDate.substring(11, 16));
            System.out.println(clickedStartBookingDate.substring(11, 16));
            SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm a");
            startTime = formatterTime.format(time1);

            Date time2=new SimpleDateFormat("hh:mm").parse(clickedEndBookingDate.substring(11, 16));
            System.out.println(clickedStartBookingDate.substring(11, 16));
            SimpleDateFormat formatterTime2 = new SimpleDateFormat("hh:mm a");
            endTime = formatterTime2.format(time2);

            System.out.println("DATEE");
            System.out.println(displayDate);
            reservation_startBookingDate_tv.setText(displayDate);
            reservation_startTime_tv.setText(startTime);
            reservation_endTime_tv.setText(endTime);
        } catch (Exception e) {
            System.out.println("ADA ERROR = " + e);
        }



        // Inflate the layout for this fragment
        return view;
    }

}
