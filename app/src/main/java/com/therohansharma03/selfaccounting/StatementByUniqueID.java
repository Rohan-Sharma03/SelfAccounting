package com.therohansharma03.selfaccounting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.therohansharma03.selfaccounting.allResponses.FetchingAllSellerData;
import com.therohansharma03.selfaccounting.allResponses.FetchingAllSellerDataByUniqueId;
import com.therohansharma03.selfaccounting.allResponses.FetchingSellerDataBySrNoResponse;
import com.therohansharma03.selfaccounting.allResponses.Seller;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementByUniqueID extends AppCompatActivity {
    SessionMaintainer sessionMaintainer;
    String SrNoS;
    String UniqueId;
    String FirstName;
    String LastName;
    String Commodity;
    String BasicRates;
    String Weight;
    String BeneficiaryAmount;
    String PaymentMethod;
    String AccountNumber;
    String IFSCode;
    String PurchaseDate;
    String MobileNumber;
    String ResidentialAddress;
    String CustomerOf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        sessionMaintainer = new SessionMaintainer(StatementByUniqueID.this);
        CustomerOf=sessionMaintainer.getuser().getEmailId();
        TextView bfullname= findViewById(R.id.hbFullName);
        TextView bbasicrate = findViewById(R.id.hbBasicRates);
        TextView bweight= findViewById(R.id.hbWeight);
        TextView baccountnumber = findViewById(R.id.hbAccountNumber);
        TextView bifsccode = findViewById(R.id.hbIfscNumber);
        TextView bresidentialaddress= findViewById(R.id.hbAddress);
        TextView bphonenumber = findViewById(R.id.hbPhoneNumber);
        TextView bcommodity = findViewById(R.id.hbCommodity);
        TextView bmethodeofpayment= findViewById(R.id.hbPaymentMethod);
        TextView btotalamount= findViewById(R.id.hbTotalAmount);
        TextView buniqueid= findViewById(R.id.hbUniqueId);
        TextView bpurchasedate= findViewById(R.id.hbDateOfPurchase);
        Intent intent = getIntent();
        UniqueId=intent.getStringExtra("UniqueID");
        Call<FetchingSellerDataBySrNoResponse> call = RetrofitClient.getInstance().getApi().ApiUpDateSellerDataBySrNo(CustomerOf,UniqueId);
        call.enqueue(new Callback<FetchingSellerDataBySrNoResponse>() {
            @Override
            public void onResponse(Call<FetchingSellerDataBySrNoResponse> call, Response<FetchingSellerDataBySrNoResponse> response) {
                FetchingSellerDataBySrNoResponse fetchingSellerDataBySrNoResponse =response.body();
                if(response.isSuccessful()){
                    if(fetchingSellerDataBySrNoResponse.getError().equals("201")){
                        sessionMaintainer.savesellerdata(fetchingSellerDataBySrNoResponse.getSeller());
                        Toast.makeText(StatementByUniqueID.this, fetchingSellerDataBySrNoResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        FirstName=fetchingSellerDataBySrNoResponse.getSeller().getFirstName();
                        LastName=fetchingSellerDataBySrNoResponse.getSeller().getLastName();
                        BasicRates=String.valueOf(fetchingSellerDataBySrNoResponse.getSeller().getBasicRates());
                        Weight=String.valueOf(fetchingSellerDataBySrNoResponse.getSeller().getWeight());
                        AccountNumber=fetchingSellerDataBySrNoResponse.getSeller().getAccountNumber();
                        Commodity=fetchingSellerDataBySrNoResponse.getSeller().getCommodity();
                        MobileNumber=fetchingSellerDataBySrNoResponse.getSeller().getMobileNumber();
                        ResidentialAddress=fetchingSellerDataBySrNoResponse.getSeller().getResidentialAddress();
                        PurchaseDate=fetchingSellerDataBySrNoResponse.getSeller().getPurchaseDate();
                        IFSCode=fetchingSellerDataBySrNoResponse.getSeller().getIFSCode();
                        PaymentMethod=fetchingSellerDataBySrNoResponse.getSeller().getPaymentMethod();
                        SrNoS=String.valueOf(fetchingSellerDataBySrNoResponse.getSeller().getSrNo());
                        CustomerOf=sessionMaintainer.getuser().getEmailId();
                        BeneficiaryAmount=String.valueOf(fetchingSellerDataBySrNoResponse.getSeller().getBeneficiaryAmount());

                        bfullname.setText(FirstName+" "+LastName);
                        bbasicrate.setText(String.valueOf(BasicRates));
                        bweight.setText(String.valueOf(Weight));
                        bresidentialaddress.setText(ResidentialAddress);
                        bphonenumber.setText(MobileNumber);
                        bcommodity.setText(Commodity);
                        bmethodeofpayment.setText(PaymentMethod);
                        btotalamount.setText(String.valueOf(BeneficiaryAmount));
                        baccountnumber.setText(AccountNumber);
                        bifsccode.setText(IFSCode);
                        buniqueid.setText(SrNoS);
                        bpurchasedate.setText(String.valueOf(PurchaseDate));
                        btotalamount.setText(String.valueOf(BeneficiaryAmount));
                    }else if (fetchingSellerDataBySrNoResponse.getError().equals("200")){
                        Toast.makeText(StatementByUniqueID.this, fetchingSellerDataBySrNoResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(StatementByUniqueID.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<FetchingSellerDataBySrNoResponse> call, Throwable t) {
                Toast.makeText(StatementByUniqueID.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    }