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
import com.therohansharma03.selfaccounting.allResponses.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private EditText lemial,lpassword;
    private Button llogin;
    private TextView Directingtosiguppage;
    private ProgressBar LprogressBar;
    SessionMaintainer sessionMaintainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lemial =findViewById(R.id.LEmail_enter);
        lpassword =findViewById(R.id.LPassword_enter);
        llogin =findViewById(R.id.Blogin);
        Directingtosiguppage=findViewById(R.id.Direct_to_Sigup);
        LprogressBar=findViewById(R.id.LprogressBar);
        sessionMaintainer= new SessionMaintainer(getApplicationContext());

        llogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginL();
            }
        });

        Directingtosiguppage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View view){
                startActivity(new Intent(getApplicationContext(), Register.class));
                finish();
            }
            });
        }

        private void loginL(){
            if( lemial.getText().toString().isEmpty() && lpassword.getText().toString().isEmpty()){
                Toast.makeText(Login.this, "Please fill the fields to Procced !", Toast.LENGTH_SHORT).show();
            }
            else {
                String emaill = lemial.getText().toString().trim();
                String paswordl = lpassword.getText().toString().trim();
                if (emaill.isEmpty()) {
                    lemial.requestFocus();
                    lemial.setError("Email is Required !");
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(emaill).matches()) {
                    lemial.requestFocus();
                    lemial.setError("Email is not correct !");
                }
                if (paswordl.isEmpty()) {
                    lpassword.requestFocus();
                    lpassword.setError("Password is required !");
                }
                if (paswordl.length() < 8) {
                    lpassword.requestFocus();
                    lpassword.setError("Password length should be grater than 8 character !");
                }
                LprogressBar.setVisibility(View.VISIBLE);

                Call<LoginResponse> call= RetrofitClient.getInstance().getApi().login(emaill,paswordl);
                call.enqueue(new Callback<LoginResponse>() {

                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        LoginResponse loginResponse =response.body();
                        if(response.isSuccessful()){
                            if(loginResponse.getError().equals("400")){
                                sessionMaintainer.savedata(loginResponse.getUser());
                                Intent intent = new Intent(getApplicationContext(),MainMenu.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(Login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                LprogressBar.setVisibility(View.INVISIBLE);
                            }
                            else if(loginResponse.getError().equals("401")){
                                Toast.makeText(Login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                LprogressBar.setVisibility(View.INVISIBLE);
                            }else if(loginResponse.getError().equals("200")){
                                Toast.makeText(Login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                LprogressBar.setVisibility(View.INVISIBLE);
                            }
                        }else{ Toast.makeText(Login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                          LprogressBar.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        LprogressBar.setVisibility(View.INVISIBLE);
                    }
            });
        }}

        @Override
        protected void onStart(){
            super.onStart();
            if(sessionMaintainer.isloggedIn()){
                Intent intent = new Intent(getApplicationContext(),MainMenu.class);
                startActivity(intent);
                finish();
            }
        }
}