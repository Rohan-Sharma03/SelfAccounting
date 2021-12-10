package com.therohansharma03.selfaccounting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.therohansharma03.selfaccounting.allResponses.DeleteAccountResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountSetting extends AppCompatActivity {

    private TextView DirectingtoPasswordChange;
    private TextView DirectingtoAccountDelete;
    private TextView DirectingtoUpdateuser;
    SessionMaintainer sessionMaintainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        DirectingtoAccountDelete = findViewById(R.id.acDeleteAccount);
        DirectingtoPasswordChange = findViewById(R.id.acUpdate_Password);
        DirectingtoUpdateuser = findViewById(R.id.ac_Update_User);
        sessionMaintainer = new SessionMaintainer(AccountSetting.this);
        DirectingtoPasswordChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountSetting.this, UpdatePassword.class);
                startActivity(i);
                finish();
            }
        });

        DirectingtoUpdateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountSetting.this, UpdateUser.class);
                startActivity(i);
                finish();
            }
        });

        DirectingtoAccountDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 new AlertDialog.Builder( AccountSetting.this)
                         .setIcon(R.drawable.ic_baseline_warning_24)
                         .setTitle("Delete Account")
                         .setMessage("Deleting account will be result into earse of all data related to user")
                         .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialogInterface, int i) {
                                 deleteAccount();
                             }
                         }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         finish();
                     }
                 }).show();


            }
        });

    }
    private void deleteAccount() {
        Call<DeleteAccountResponse> call = RetrofitClient.getInstance().getApi().deleteUser(sessionMaintainer.getuser().getSrNo());
        int str =sessionMaintainer.getuser().getSrNo();
        call.enqueue(new Callback<DeleteAccountResponse>() {
            @Override
            public void onResponse(Call<DeleteAccountResponse> call, Response<DeleteAccountResponse> response) {
                DeleteAccountResponse deleteAccountResponse =response.body();
                if(response.isSuccessful()){
                    if(deleteAccountResponse.getError().equals("200")){
                        logoutUser();
                        Toast.makeText(AccountSetting.this, deleteAccountResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }else if(deleteAccountResponse.getError().equals("201")){
                        Toast.makeText(AccountSetting.this, deleteAccountResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(AccountSetting.this, "Failed to delete :(", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DeleteAccountResponse> call, Throwable t) {
                Toast.makeText(AccountSetting.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logoutUser() {
        sessionMaintainer.logout();
        Toast.makeText(AccountSetting.this, "Logouted successfully, Directing to login page", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

}