package com.example.ars.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ars.Interface.RecyclerViewClickListener;
import com.example.ars.Model.ReservationModel;
import com.example.ars.R;
import com.example.ars.ReservationsRecyclerViewAdapter;
import com.example.ars.RetrofitInstance;
import com.example.ars.Interface.RetrofitReservationInterface;
import com.example.ars.Model.User_profile_id_DATASET;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Reservations extends Fragment {

    RecyclerView rvMain;
//    int user_profile_id = getArguments().getInt("USER_ID");

    int user_profile_id = 1;
    int clickedReservationId;
    int clickedReservationUserId;
    String clickedEndBookingDate;
    int clickedNumberOfPax;
    String clickedStartBookingDate;
    int clickedTableType;
    String clickedReservationCode;
    Bundle bundle = new Bundle();
    public Reservations() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_reservations, container, false);
        rvMain = view.findViewById(R.id.rvMain);
        rvMain.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        if (getArguments() != null) {
            user_profile_id = getArguments().getInt("USER_ID");
        }
        postUserId();


        // Inflate the layout for this fragment
        return view;
    }
    private void parseData(List<ReservationModel> body){
        final ReservationsRecyclerViewAdapter rAdapter = new ReservationsRecyclerViewAdapter(this.getActivity(), body, new RecyclerViewClickListener() {
            @Override
            public void onPositionClicked(int position) {
                System.out.println("CLICKEDD");
//                View row = rvMain.getLayoutManager().findViewByPosition(position);
//                System.out.println(row);
//                rvMain.getAdapter().getItemCount()

                clickedReservationId = ((ReservationsRecyclerViewAdapter) rvMain.getAdapter()).getList().get(position).getReservation_id();
                clickedReservationUserId = ((ReservationsRecyclerViewAdapter) rvMain.getAdapter()).getList().get(position).getUser_profile_id_id();
                clickedNumberOfPax = ((ReservationsRecyclerViewAdapter) rvMain.getAdapter()).getList().get(position).getNumber_of_pax();
                clickedEndBookingDate = ((ReservationsRecyclerViewAdapter) rvMain.getAdapter()).getList().get(position).getEndBookingDateTime();
                clickedStartBookingDate = ((ReservationsRecyclerViewAdapter) rvMain.getAdapter()).getList().get(position).getStartBookingDateTime();
                clickedTableType = ((ReservationsRecyclerViewAdapter) rvMain.getAdapter()).getList().get(position).getTable_type_id();
                clickedReservationCode = ((ReservationsRecyclerViewAdapter) rvMain.getAdapter()).getList().get(position).getReservation_code();

                bundle.putInt("clickedReservationId", clickedReservationId);
                bundle.putString("clickedReservationCode", clickedReservationCode);
                bundle.putInt("clickedReservationUserId", clickedReservationUserId);
                bundle.putInt("clickedNumberOfPax", clickedNumberOfPax);
                bundle.putInt("clickedTableType", clickedTableType);
                bundle.putString("clickedEndBookingDate", clickedEndBookingDate);
                bundle.putString("clickedStartBookingDate", clickedStartBookingDate);
                ReservationDetail reservationDetail = new ReservationDetail();
                reservationDetail.setArguments(bundle);
                getFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, reservationDetail).commit();


            }

            @Override
            public void onLongClicked(int position) {

            }
        });
        rvMain.setAdapter(rAdapter);
    }
    private void postUserId(){
        RetrofitReservationInterface retrofitReservationInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitReservationInterface.class);

        User_profile_id_DATASET user_profile_id_dataset = new User_profile_id_DATASET(user_profile_id);
        ReservationModel reservationModel = new ReservationModel(user_profile_id);
        Call<List<ReservationModel>> call = retrofitReservationInterface.PostData(reservationModel);

        call.enqueue(new Callback<List<ReservationModel>>() {
            @Override
            public void onResponse(Call<List<ReservationModel>> call, Response<List<ReservationModel>> response) {
                parseData(response.body());
                System.out.println(response.body());
                System.out.println("SUCCESS");
            }

            @Override
            public void onFailure(Call<List<ReservationModel>> call, Throwable t) {
                System.out.println("PRINTTT FAIL");
            }
        });
    }

}
