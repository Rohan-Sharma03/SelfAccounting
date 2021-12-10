package com.therohansharma03.selfaccounting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.therohansharma03.selfaccounting.allResponses.FetchingAllSellerDataByCommodity;
import com.therohansharma03.selfaccounting.allResponses.FetchingAllSellerDataByDate;
import com.therohansharma03.selfaccounting.allResponses.FetchingAllSellerDataByFirstName;
import com.therohansharma03.selfaccounting.allResponses.Seller;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementByDate extends AppCompatActivity {
    SessionMaintainer sessionMaintainer;
    RecyclerView recyclerView;
    List<Seller> sellerList;
    String CustomerOf;
    String Fromdate;
    String Todate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement_by_date);

        sessionMaintainer = new SessionMaintainer(StatementByDate.this);
        CustomerOf=sessionMaintainer.getuser().getEmailId();

        recyclerView = findViewById(R.id.recycledate);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new SellerDataAdapter(StatementByDate.this,sellerList));
        recyclerView.setLayoutManager(new LinearLayoutManager(StatementByDate.this));

        Intent intent = getIntent();
        Fromdate = intent.getStringExtra("Fromdate");
        Todate = intent.getStringExtra("Todate");

//        Call<FetchingAllSellerDataByFirstName> call = RetrofitClient.getInstance().getApi().ApiGetAllSellerDataByFirstName(CustomerOf,"rohan");
//        call.enqueue(new Callback<FetchingAllSellerDataByFirstName>() {
//            @Override
//            public void onResponse(Call<FetchingAllSellerDataByFirstName> call, Response<FetchingAllSellerDataByFirstName> response) {
//                FetchingAllSellerDataByFirstName fetchingAllSellerDataByFirstName = response.body();
//                if(response.isSuccessful()){
//                    if(fetchingAllSellerDataByFirstName.getError().equals("201")){
//                        sellerList=response.body().getSellersList();
//                        recyclerView.setAdapter(new SellerDataAdapter(StatementByDate.this,sellerList));
//                        Toast.makeText(StatementByDate.this, fetchingAllSellerDataByFirstName.getMessage(), Toast.LENGTH_SHORT).show();
//                    }else if(fetchingAllSellerDataByFirstName.getError().equals("200")){
//                        Toast.makeText(StatementByDate.this, fetchingAllSellerDataByFirstName.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    Toast.makeText(StatementByDate.this, response.body().getError(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<FetchingAllSellerDataByFirstName> call, Throwable t) {
//                Toast.makeText(StatementByDate.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

        Call<FetchingAllSellerDataByDate> call = RetrofitClient.getInstance().getApi().ApiGetAllSellerDataByDate(CustomerOf,Fromdate,Todate);
        call.enqueue(new Callback<FetchingAllSellerDataByDate>() {
            @Override
            public void onResponse(Call<FetchingAllSellerDataByDate> call, Response<FetchingAllSellerDataByDate> response) {
                FetchingAllSellerDataByDate fetchingAllSellerDataByDate =response.body();
                if(response.isSuccessful()){
                    Toast.makeText(StatementByDate.this, "clicked", Toast.LENGTH_SHORT).show();
                    if(fetchingAllSellerDataByDate.getError().equals("201")){
                        sellerList=response.body().getSellersList();
                        recyclerView.setAdapter(new SellerDataAdapter(StatementByDate.this,sellerList));
                        Toast.makeText(StatementByDate.this, fetchingAllSellerDataByDate.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else if(fetchingAllSellerDataByDate.getError().equals("200")){
                        Toast.makeText(StatementByDate.this, fetchingAllSellerDataByDate.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(StatementByDate.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<FetchingAllSellerDataByDate> call, Throwable t) {
                Toast.makeText(StatementByDate.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}