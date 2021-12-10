package com.therohansharma03.selfaccounting;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.therohansharma03.selfaccounting.allResponses.UserUpdateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUser extends AppCompatActivity {

    EditText NewName;
    EditText NewMobileNumber;
    Button updateUser;
    ProgressBar UUProgressbar;
    SessionMaintainer sessionMaintainer;
    int userSrNo;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        NewName = findViewById(R.id.uu_et_New_Name);
        NewMobileNumber = findViewById(R.id.uu_et_New_Emailid);
        updateUser = findViewById(R.id.uu_btn_Updatedata);
        UUProgressbar = findViewById(R.id.uu_progressBar);
        sessionMaintainer = new SessionMaintainer(UpdateUser.this);
        userSrNo = sessionMaintainer.getuser().getSrNo();
        UUProgressbar.setVisibility(View.INVISIBLE);

        updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserdata();
                UUProgressbar.setVisibility(View.VISIBLE);
            }
        });

    }
    private void updateUserdata() {
        if (NewName.getText().toString().isEmpty() && NewMobileNumber.getText().toString().isEmpty()) {
            Toast.makeText(UpdateUser.this, "Please fill the fields to Procced !", Toast.LENGTH_SHORT).show();
            UUProgressbar.setVisibility(View.INVISIBLE);
        }else{
            sessionMaintainer = new SessionMaintainer(UpdateUser.this);
            String newN = NewName.getText().toString().trim();
            String newMN = NewMobileNumber.getText().toString().trim();
            if (newN.isEmpty()) {
                NewName.requestFocus();
                NewName.setError("Enter new name !");
            }
            if (newMN.isEmpty()) {
                NewMobileNumber.requestFocus();
                NewMobileNumber.setError("Enter new emailid !");
            }
            UUProgressbar.setVisibility(View.VISIBLE);
            Call<UserUpdateResponse> call = RetrofitClient.getInstance().getApi().updateuser(userSrNo,newN,newMN);
            call.enqueue(new Callback<UserUpdateResponse>() {
                @Override
                public void onResponse(Call<UserUpdateResponse> call, Response<UserUpdateResponse> response) {
                    UserUpdateResponse updateResponse =response.body();
                    if(response.isSuccessful()){
                        if(updateResponse.getError().equals("601")){
                            Toast.makeText(UpdateUser.this, updateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            UUProgressbar.setVisibility(View.INVISIBLE);
                        }else if(updateResponse.getError().equals("602")){
                            Toast.makeText(UpdateUser.this, updateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            UUProgressbar.setVisibility(View.INVISIBLE);
                        }

                    }else{
                        Toast.makeText(UpdateUser.this, "failed to Update data ", Toast.LENGTH_SHORT).show();
                        UUProgressbar.setVisibility(View.INVISIBLE);
                    }
                }
                @Override
                public void onFailure(Call<UserUpdateResponse> call, Throwable t) {
                    Toast.makeText(UpdateUser.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}