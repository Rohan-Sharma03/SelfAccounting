package com.therohansharma03.selfaccounting;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.therohansharma03.selfaccounting.allResponses.RegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    private EditText remial,rpassword,rusername,rmobile;
    private TextView Directingtologinpage;
    private Button rsignup;
    private FirebaseAuth firebaseAuth;
    private ProgressBar RprogressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        remial = findViewById(R.id.REmail_enter);
        rpassword = findViewById(R.id.RPassword_enter);
        rusername = findViewById(R.id.RFull_Name_enter);
        rmobile = findViewById(R.id.Rmobile_enter);
        rsignup = findViewById(R.id.Bsigup);
        RprogressBar = findViewById(R.id.RprogressBar);
        Directingtologinpage = findViewById(R.id.Direct_to_Login);
        firebaseAuth = FirebaseAuth.getInstance();

        rsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerR();
            }
         });

        Directingtologinpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

    }

    private void registerR(){

        if( remial.getText().toString().isEmpty() && rpassword.getText().toString().isEmpty() && rusername.getText().toString().isEmpty() && rmobile.getText().toString().isEmpty()){
            Toast.makeText(Register.this, "Please fill the fields to Procced !", Toast.LENGTH_SHORT).show();
        }

        else {
            String usernamer = rusername.getText().toString().trim();
            String emailr = remial.getText().toString().trim();
            String paswordr = rpassword.getText().toString().trim();
            String mobiler = rmobile.getText().toString().trim();
            if (usernamer.isEmpty()) {
                rusername.requestFocus();
                rusername.setError("Name is Required !");
            }
            if (emailr.isEmpty()) {
                remial.requestFocus();
                remial.setError("Email is Required !");
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(emailr).matches()) {
                remial.requestFocus();
                remial.setError("Email is not correct !");
            }
            if (paswordr.isEmpty()) {
                rpassword.requestFocus();
                rpassword.setError("Password is required !");
            }
            if (paswordr.length() < 8) {
                rpassword.requestFocus();
                rpassword.setError("Password length should be grater than 8 character !");

            }
            if (mobiler.length() < 10) {
                rmobile.requestFocus();
                rmobile.setError("Mobile Number is not vaild !");
            }
            RprogressBar.setVisibility(View.VISIBLE);

            Call<RegisterResponse> call = RetrofitClient.getInstance().getApi().register(usernamer, emailr, paswordr, mobiler);
            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    RegisterResponse registerResponse = response.body();
                    if (response.isSuccessful()) {
                        if(registerResponse.getError().equals("502")){
                        Toast.makeText(Register.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Login.class));
                        finish();
                        RprogressBar.setVisibility(View.INVISIBLE);
                        }else if(registerResponse.getError().equals("501")){
                            Toast.makeText(Register.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            RprogressBar.setVisibility(View.INVISIBLE);
                        }else if(registerResponse.getError().equals("503")){
                            Toast.makeText(Register.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            RprogressBar.setVisibility(View.INVISIBLE);
                        }
                    }else {
                        Toast.makeText(Register.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        RprogressBar.setVisibility(View.INVISIBLE);
                    }
                }
                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    RprogressBar.setVisibility(View.INVISIBLE);
                }
            });
        }
    }
}