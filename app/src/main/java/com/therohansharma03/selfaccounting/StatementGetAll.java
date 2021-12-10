package com.therohansharma03.selfaccounting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.therohansharma03.selfaccounting.allResponses.FetchingAllSellerData;
import com.therohansharma03.selfaccounting.allResponses.FetchingAllSellerDataByFirstName;
import com.therohansharma03.selfaccounting.allResponses.Seller;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementGetAll extends AppCompatActivity {
    SessionMaintainer sessionMaintainer;
    RecyclerView recyclerView;
    List<Seller> sellerList;
    String CustomerOf;
    String UniqueId;
    String FirstName;
    String Commodity;
    String FromDate;
    String ToDate;
    String LastThreeMonths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement_get_all);
        sessionMaintainer = new SessionMaintainer(StatementGetAll.this);
        CustomerOf=sessionMaintainer.getuser().getEmailId();
//        Intent intent = getIntent();
//        UniqueId = intent.getStringExtra("UniqueID");
//        FirstName = intent.getStringExtra("FirstName");
//        Commodity = intent.getStringExtra("Commodity");
//        FromDate = intent.getStringExtra("FromDate");
//        ToDate = intent.getStringExtra("ToDate");
//        LastThreeMonths = intent.getStringExtra("LastThreeMonths");

        recyclerView = findViewById(R.id.recyclegetall);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new SellerDataAdapter(StatementGetAll.this,sellerList));
        recyclerView.setLayoutManager(new LinearLayoutManager(StatementGetAll.this));

            Call<FetchingAllSellerData> call = RetrofitClient.getInstance().getApi().ApiGetAllSellerData(CustomerOf);
            call.enqueue(new Callback<FetchingAllSellerData>() {
                @Override
                public void onResponse(Call<FetchingAllSellerData> call, Response<FetchingAllSellerData> response) {
                    FetchingAllSellerData fetchingAllUserData=response.body();
                    if(response.isSuccessful()){
                        sellerList=response.body().getSellersList();
                        recyclerView.setAdapter(new SellerDataAdapter(StatementGetAll.this,sellerList));
                    }
                    else{
                        Toast.makeText(StatementGetAll.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<FetchingAllSellerData> call, Throwable t) {
                    Toast.makeText(StatementGetAll.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


//         if(!FirstName.isEmpty() &&!CustomerOf.isEmpty()){

//        }
//        else if(!Commodity.isEmpty() &&!CustomerOf.isEmpty()){
//
//        }
//        else if(!FromDate.isEmpty() && !ToDate.isEmpty() &&!CustomerOf.isEmpty()){
//
//        }
//        else if(!LastThreeMonths.isEmpty() &&!CustomerOf.isEmpty()){
//
//        }


    }
}