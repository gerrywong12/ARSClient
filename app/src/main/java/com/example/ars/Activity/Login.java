package com.example.ars.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ars.Interface.LoginInterface;
import com.example.ars.Model.LoginModel;
import com.example.ars.R;
import com.example.ars.Response.LoginResponse;
import com.example.ars.Response.UserDataResponse;
import com.example.ars.RetrofitInstance;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    Button btnToRegister;
    Button btnToLogin;

    TextInputLayout username_tf;
    TextInputLayout password_tf;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnToRegister = (Button)findViewById(R.id.btnSignUp);
        btnToLogin = (Button)findViewById(R.id.btnLogin);

        username_tf = (TextInputLayout) findViewById(R.id.tf_loginUsername);
        password_tf = (TextInputLayout) findViewById(R.id.tf_loginPassword);


        btnToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postLogin();
            }
        });
    }

    private void postLogin(){
        System.out.println("USERNAME");
        username = username_tf.getEditText().getText().toString();
        password = password_tf.getEditText().getText().toString();
        try {
            LoginInterface loginInterface = RetrofitInstance.getRetrofitInstance().create(LoginInterface.class);
            LoginModel loginModel = new LoginModel(username, password);
            Call<LoginResponse> res = loginInterface.Login(loginModel);

            res.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    try{
                        String response_param = response.body().getMessage().toString();
                        Log.v("RESPONSE_CALLED", "ON_RESPONSE_CALLED");
                        String didItWork = String.valueOf(response.isSuccessful());
                        System.out.println("RESPONSEE" + response.body());
                        Toast toast = Toast.makeText(Login.this, response_param, Toast.LENGTH_LONG);
                        toast.show();
                        try {
                            UserDataResponse user_data = response.body().getUserDataResponse();
                            System.out.println("INI-- = " + user_data);
                            String user_name = user_data.getUser_name();
                            int user_id = user_data.getUser_id();
                            System.out.println("USER_ID--------------" + user_id);
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            intent.putExtra("USER_ID", user_id);
                            intent.putExtra("USER_NAME", user_data.getUser_name());
                            startActivity(intent);
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
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    System.out.println("ERROR");
                    System.out.println(t.getMessage());
                    Log.d("Error", t.getMessage());
                }
            });
        } catch (Exception e){

        }

    }

}
