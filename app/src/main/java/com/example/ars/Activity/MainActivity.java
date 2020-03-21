package com.example.ars.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ars.Fragment.MakeReservation;
import com.example.ars.Fragment.Menu;
import com.example.ars.Fragment.Profile;
import com.example.ars.Fragment.Reservations;
import com.example.ars.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    private static final String TAG = "MainActivity";
//    Bundle extras = getIntent().getExtras();
//    Bundle mBundle = new Bundle();
//    int user_id = extras.getInt("USER_ID");

    int user_id;
    Bundle bundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_make_reservation);
        user_id = getIntent().getIntExtra("USER_ID", 1);

        bundle.putInt("USER_ID", user_id);
    }

    Reservations reservationFragment = new Reservations();
    MakeReservation makeReservationFragment = new MakeReservation();
    Menu menuFragment = new Menu();
    Profile profileFragment = new Profile();



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.navigation_reservations:
                reservationFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, reservationFragment).commit();
                return true;
            case R.id.navigation_make_reservation:
                makeReservationFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, makeReservationFragment).commit();
                return true;
            case R.id.navigation_menu:
                menuFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, menuFragment).commit();
                return true;
            case R.id.navigation_profile:
                profileFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, profileFragment).commit();
                return true;
        }

        return false;
    }
}
