package com.therohansharma03.selfaccounting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.therohansharma03.selfaccounting.allResponses.PasswordUpdateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePassword extends AppCompatActivity {

    EditText CurrentPassword;
    EditText NewPassword;
    Button updatePassword;
    ProgressBar UPProgressbar;
    SessionMaintainer sessionMaintainer;
    String useremailidup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        CurrentPassword = findViewById(R.id.up_et_Current_Password);
        NewPassword = findViewById(R.id.up_et_New_Password);
        updatePassword = findViewById(R.id.up_btn_Update_Password);
        UPProgressbar = findViewById(R.id.up_progressBar);
        sessionMaintainer = new SessionMaintainer(UpdatePassword.this);
        useremailidup = sessionMaintainer.getuser().getEmailId();
        UPProgressbar.setVisibility(View.INVISIBLE);
        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatepassword();
            }
        });
    }
    void updatepassword() {

        if (CurrentPassword.getText().toString().isEmpty() && NewPassword.getText().toString().isEmpty()) {
            Toast.makeText(UpdatePassword.this, "Please fill the fields to Procced !", Toast.LENGTH_SHORT).show();
            UPProgressbar.setVisibility(View.INVISIBLE);
        } else {
            useremailidup = sessionMaintainer.getuser().getEmailId();
            String currentpaswordup = CurrentPassword.getText().toString().trim();
            String newpaswordup = NewPassword.getText().toString().trim();
            if (currentpaswordup.isEmpty()) {
                CurrentPassword.requestFocus();
                CurrentPassword.setError("Current password is required !");
            }
            if (currentpaswordup.length() < 8) {
                CurrentPassword.requestFocus();
                CurrentPassword.setError("Current Password need at least 8 chanracter!");
            }
            if (newpaswordup.isEmpty()) {
                NewPassword.requestFocus();
                NewPassword.setError("New password is required !");
            }
            if (newpaswordup.length() < 8) {
                NewPassword.requestFocus();
                NewPassword.setError("New Password need at least 8 chanracter!");
            }
            UPProgressbar.setVisibility(View.VISIBLE);
            Call<PasswordUpdateResponse> call = RetrofitClient.getInstance().getApi().updatepassword(useremailidup, currentpaswordup, newpaswordup);
            call.enqueue(new Callback<PasswordUpdateResponse>() {
                @Override
                public void onResponse(Call<PasswordUpdateResponse> call, Response<PasswordUpdateResponse> response) {
                    PasswordUpdateResponse passwordUpdateResponse = response.body();
                    if (response.isSuccessful()) {
                        if (passwordUpdateResponse.getError().equals("102")) {
                            Toast.makeText(UpdatePassword.this, passwordUpdateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            UPProgressbar.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(getApplicationContext(),AccountSetting.class);
                            startActivity(intent);
                            finish();
                        } else if (passwordUpdateResponse.getError().equals("103")) {
                            Toast.makeText(UpdatePassword.this, passwordUpdateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            UPProgressbar.setVisibility(View.INVISIBLE);
                        } else if (passwordUpdateResponse.getError().equals("101")) {
                            Toast.makeText(UpdatePassword.this, passwordUpdateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            UPProgressbar.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        Toast.makeText(UpdatePassword.this, "failed to update your password", Toast.LENGTH_SHORT).show();
                        UPProgressbar.setVisibility(View.INVISIBLE);
                    }
                }
                @Override
                public void onFailure(Call<PasswordUpdateResponse> call, Throwable t) {
                    Toast.makeText(UpdatePassword.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    UPProgressbar.setVisibility(View.INVISIBLE);
                }
            });
    }
}
}