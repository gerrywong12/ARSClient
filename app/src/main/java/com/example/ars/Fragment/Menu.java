package com.example.ars.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ars.Interface.FoodInterface;
import com.example.ars.MenuAdapter;
import com.example.ars.R;
import com.example.ars.Response.FoodResponse;
import com.example.ars.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Menu extends Fragment {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MenuAdapter menuAdapter;

    public Menu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);;
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        int []arr = {R.drawable.logo, R.drawable.ic_launcher_background};


        FoodInterface retrofitInterface = RetrofitInstance.getRetrofitInstance().create(FoodInterface.class);
        Call<List<FoodResponse>> listCall = retrofitInterface.getAllFood();
        listCall.enqueue(new Callback<List<FoodResponse>>() {
            @Override
            public void onResponse(Call<List<FoodResponse>> call, Response<List<FoodResponse>> response) {
                System.out.println("PRINTTTT");
                System.out.println(response.body().get(0));
                try {
                    parseData(response.body());
                } catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<List<FoodResponse>> call, Throwable t) {
                System.out.println(call);
            }
        });

        return view;
    }
    private void parseData(List<FoodResponse> body){
        MenuAdapter rAdapter = new MenuAdapter(getContext(), body);
        recyclerView.setAdapter(rAdapter);
        recyclerView.setHasFixedSize(true);
    }

}
