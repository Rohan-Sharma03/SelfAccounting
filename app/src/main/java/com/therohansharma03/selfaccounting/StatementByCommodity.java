package com.therohansharma03.selfaccounting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.therohansharma03.selfaccounting.allResponses.FetchingAllSellerData;
import com.therohansharma03.selfaccounting.allResponses.FetchingAllSellerDataByCommodity;
import com.therohansharma03.selfaccounting.allResponses.Seller;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementByCommodity extends AppCompatActivity {
    SessionMaintainer sessionMaintainer;
    RecyclerView recyclerView;
    List<Seller> sellerList;
    String CustomerOf;
    String Commodity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement_by_commodity);
        sessionMaintainer = new SessionMaintainer(StatementByCommodity.this);
        CustomerOf=sessionMaintainer.getuser().getEmailId();
        recyclerView = findViewById(R.id.recyclecommodity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new SellerDataAdapter(StatementByCommodity.this,sellerList));
        recyclerView.setLayoutManager(new LinearLayoutManager(StatementByCommodity.this));
        Intent intent = getIntent();
        Commodity =intent.getStringExtra("Commodity");
        Call<FetchingAllSellerDataByCommodity> call = RetrofitClient.getInstance().getApi().ApiGetAllSellerDataByCommodity(CustomerOf,Commodity);
        call.enqueue(new Callback<FetchingAllSellerDataByCommodity>() {
            @Override
            public void onResponse(Call<FetchingAllSellerDataByCommodity> call, Response<FetchingAllSellerDataByCommodity> response) {
                FetchingAllSellerDataByCommodity fetchingAllSellerDataByCommodity =response.body();
                if(response.isSuccessful()){
                    if(fetchingAllSellerDataByCommodity.getError().equals("201")){
                        sellerList=response.body().getSellersList();
                        recyclerView.setAdapter(new SellerDataAdapter(StatementByCommodity.this,sellerList));
                        Toast.makeText(StatementByCommodity.this, fetchingAllSellerDataByCommodity.getMessage(), Toast.LENGTH_SHORT).show();
                    }else if(fetchingAllSellerDataByCommodity.getError().equals("200")){
                        Toast.makeText(StatementByCommodity.this, fetchingAllSellerDataByCommodity.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(StatementByCommodity.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<FetchingAllSellerDataByCommodity> call, Throwable t) {
                Toast.makeText(StatementByCommodity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}