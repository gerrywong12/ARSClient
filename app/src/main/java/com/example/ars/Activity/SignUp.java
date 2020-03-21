package com.example.ars.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ars.Model.SignUpModel;
import com.example.ars.Model.UserModel;
import com.example.ars.Model.UserProfileModel;
import com.example.ars.R;
import com.example.ars.Response.SignUpResponse;
import com.example.ars.RetrofitInstance;
import com.example.ars.Interface.SignUpInterface;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    private Button datePicker;
    private Button btnSignUp;
    private TextView dateTF;

    private TextInputLayout username_tf;
    private TextInputLayout phoneNo_tf;
    private TextInputLayout email_tf;
    private TextInputLayout password_tf;
    private TextInputLayout confirm_password_tf;

    private RadioGroup gender_rb;
    private RadioButton rb;

    private String username;
    private String email;
    private String password;
    private String confirm_password;
    private String birthDate;
    private int userRole;
    private String phoneNo;
    private int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        datePicker = (Button) findViewById(R.id.btnDatePicker);
        dateTF = (TextView) findViewById(R.id.tf_signUpBirthDate);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        username_tf = (TextInputLayout) findViewById(R.id.tf_signUpUsername);
        phoneNo_tf = (TextInputLayout) findViewById(R.id.tf_signUpPhoneNo);
        email_tf = (TextInputLayout) findViewById(R.id.tf_signUpEmail);
        password_tf = (TextInputLayout) findViewById(R.id.tf_signUpPassword);
        confirm_password_tf = (TextInputLayout) findViewById(R.id.tf_signUpConfirmPassword);

        gender_rb = (RadioGroup) findViewById(R.id.rb_gender);


        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC+8"));
        calendar.clear();

        Long today = MaterialDatePicker.todayInUtcMilliseconds();

        calendar.setTimeInMillis(today);

        calendar.set(Calendar.YEAR, 1945);
        Long january = calendar.getTimeInMillis();

        int now = Calendar.getInstance().get(Calendar.YEAR);

        calendar.set(Calendar.YEAR, now-1);
        Long december = calendar.getTimeInMillis();

        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, 1999);
        Long dateToShow = date.getTimeInMillis();


        //constraints
        CalendarConstraints.Builder constraintBuilder = new CalendarConstraints.Builder();
        constraintBuilder.setStart(january);
        constraintBuilder.setEnd(december);
//        constraintBuilder.setValidator(DateValidatorPointForward.now());
        //material date picker
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select Your Birth Date");
        builder.setSelection(dateToShow);
        builder.setCalendarConstraints(constraintBuilder.build());

        final MaterialDatePicker materialDatePicker = builder.build();



        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                dateTF.setText(materialDatePicker.getHeaderText());
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });


    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private void signUp(){
        try{
            boolean isEmail = isEmailValid(email_tf.getEditText().getText().toString());
            if (isEmail == true){
                userRole = 2;
                username = username_tf.getEditText().getText().toString();
                email = email_tf.getEditText().getText().toString();
                password = password_tf.getEditText().getText().toString();
                confirm_password = confirm_password_tf.getEditText().getText().toString();
                username = username_tf.getEditText().getText().toString();
                birthDate = dateTF.getText().toString();
                phoneNo = phoneNo_tf.getEditText().getText().toString();
                System.out.println(birthDate);

                Date date1=new SimpleDateFormat("dd MMM yyy").parse(birthDate);
                System.out.println(birthDate+"\t"+date1);

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String birthDate1 = formatter.format(date1);

                int selected = gender_rb.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(selected);


                if (rb.getText() == "Male"){
                    gender = 1;
                } else {
                    gender = 2;
                }

                if (password.equals(confirm_password)){
                    SignUpInterface signUpInterface = RetrofitInstance.getRetrofitInstance().create(SignUpInterface.class);

                    UserModel user = new UserModel(email, username, password);
                    UserProfileModel userProfile = new UserProfileModel(2, Long.parseLong(phoneNo), 21, birthDate1, 1);
                    SignUpModel signUpModel = new SignUpModel(user, userProfile);
                    Call<SignUpResponse> res = signUpInterface.Register(signUpModel);

                    res.enqueue(new Callback<SignUpResponse>() {
                        @Override
                        public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                            try{
                                String response_param = response.body().getResponse().toString();
                                System.out.println("RESPONSE = " + response_param);
                                Toast toast = Toast.makeText(SignUp.this, response_param, Toast.LENGTH_LONG);
                                toast.show();

                                Intent intent = new Intent(SignUp.this, Login.class);
                                startActivity(intent);

                            }catch (Exception e){

                            }

                            Log.v("RESPONSE_CALLED", "ON_RESPONSE_CALLED");
                            String didItWork = String.valueOf(response.isSuccessful());
                            Log.v("SUCCESS?", didItWork);
                            Log.v("RESPONSE_CODE", String.valueOf(response.code()));
                            Log.v("ERROR_BODY", String.valueOf(response.errorBody()));
                            Log.v("RAW", String.valueOf(response.raw()));
                            Log.v("MESSAGE", String.valueOf(response.message()));

                        }

                        @Override
                        public void onFailure(Call<SignUpResponse> call, Throwable t) {
                            System.out.println("ERROR");
                            System.out.println(t.getMessage());
                            Log.d("Error", t.getMessage());
                        }
                    });
                } else {
                    Toast toast = Toast.makeText(this, "Password must match", Toast.LENGTH_LONG);
                    toast.show();
                }

            } else {
                Toast toast = Toast.makeText(this, "Please use a correct email address", Toast.LENGTH_LONG);
                toast.show();
            }
        } catch(Exception e){
            Toast toast = Toast.makeText(this, "Please input correct data to the fields", Toast.LENGTH_LONG);
            toast.show();
        }

    }





}
