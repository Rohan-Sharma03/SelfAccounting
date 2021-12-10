package com.therohansharma03.selfaccounting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.therohansharma03.selfaccounting.allResponses.FetchingAllSellerDataByFirstName;
import com.therohansharma03.selfaccounting.allResponses.Seller;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementByFirstName extends AppCompatActivity {
    SessionMaintainer sessionMaintainer;
    RecyclerView recyclerView;
    List<Seller> sellerList;
    String CustomerOf;
    String FirstName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement_by_first_name);

        sessionMaintainer = new SessionMaintainer(StatementByFirstName.this);
        CustomerOf=sessionMaintainer.getuser().getEmailId();

        recyclerView = findViewById(R.id.recyclefirstname);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new SellerDataAdapter(StatementByFirstName.this,sellerList));
        recyclerView.setLayoutManager(new LinearLayoutManager(StatementByFirstName.this));

        Intent intent = getIntent();
        FirstName=intent.getStringExtra("FirstName");

        Call<FetchingAllSellerDataByFirstName> call = RetrofitClient.getInstance().getApi().ApiGetAllSellerDataByFirstName(CustomerOf,FirstName);
        call.enqueue(new Callback<FetchingAllSellerDataByFirstName>() {
            @Override
            public void onResponse(Call<FetchingAllSellerDataByFirstName> call, Response<FetchingAllSellerDataByFirstName> response) {
                FetchingAllSellerDataByFirstName fetchingAllSellerDataByFirstName = response.body();
                if(response.isSuccessful()){
                    if(fetchingAllSellerDataByFirstName.getError().equals("201")){
                        sellerList=response.body().getSellersList();
                        recyclerView.setAdapter(new SellerDataAdapter(StatementByFirstName.this,sellerList));
                        Toast.makeText(StatementByFirstName.this, fetchingAllSellerDataByFirstName.getMessage(), Toast.LENGTH_SHORT).show();
                    }else if(fetchingAllSellerDataByFirstName.getError().equals("200")){
                        Toast.makeText(StatementByFirstName.this, fetchingAllSellerDataByFirstName.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(StatementByFirstName.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FetchingAllSellerDataByFirstName> call, Throwable t) {
                Toast.makeText(StatementByFirstName.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}