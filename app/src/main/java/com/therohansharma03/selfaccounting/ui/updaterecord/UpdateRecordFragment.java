package com.therohansharma03.selfaccounting.ui.updaterecord;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.therohansharma03.selfaccounting.AccountSetting;
import com.therohansharma03.selfaccounting.R;
import com.therohansharma03.selfaccounting.RetrofitClient;
import com.therohansharma03.selfaccounting.SessionMaintainer;
import com.therohansharma03.selfaccounting.allResponses.BillingResponse;
import com.therohansharma03.selfaccounting.allResponses.DeleteSellerDataResponse;
import com.therohansharma03.selfaccounting.allResponses.FetchingSellerDataBySrNoResponse;
import com.therohansharma03.selfaccounting.allResponses.RebillingResponse;
import com.therohansharma03.selfaccounting.ui.home.HomeFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateRecordFragment extends Fragment {

    SessionMaintainer sessionMaintainer;
    String SrNoS;
    String FirstName;
    String LastName;
    String Commodity;
    float BasicRates;
    float Weight;
    float BeneficiaryAmount;
    String PaymentMethod;
    String AccountNumber;
    String IFSCode;
    String PurchaseDate;
    String MobileNumber;
    String ResidentialAddress;
    String CustomerOf;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_update_record,container,false);

        Button button1 = root.findViewById(R.id.test5);
        Button button2 = root.findViewById(R.id.test6);
        EditText UniqueId= root.findViewById(R.id.UR_New_Unique_Id);
        EditText firstname= root.findViewById(R.id.UR_New_First_Name);
        EditText lastname= root.findViewById(R.id.UR_New_Last_Name);
        EditText basicrates= root.findViewById(R.id.UR_New_Basic_Rates);
        EditText weight= root.findViewById(R.id.UR_New_Weight);
        EditText accountnumber= root.findViewById(R.id.UR_New_Account_Number);
        TextView deletesellerdata=root.findViewById(R.id.UR_Delete_Seller_Data);
        SrNoS=UniqueId.getText().toString();
        sessionMaintainer=new SessionMaintainer(getContext());
        CustomerOf=sessionMaintainer.getuser().getEmailId();

        deletesellerdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder( getContext())
                        .setIcon(R.drawable.ic_baseline_warning_24)
                        .setTitle("Delete Data")
                        .setMessage("Deleting data will be result into earse of all data related to Seller")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteAccount();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UniqueId.getText().toString().isEmpty() ){
                    Toast.makeText(getContext(), "Please fill the UniqueID to Proceed !", Toast.LENGTH_SHORT).show();
                }else{
                Call<FetchingSellerDataBySrNoResponse> call = RetrofitClient.getInstance().getApi().ApiUpDateSellerDataBySrNo(CustomerOf,UniqueId.getText().toString());
                call.enqueue(new Callback<FetchingSellerDataBySrNoResponse>() {
                    @Override
                    public void onResponse(Call<FetchingSellerDataBySrNoResponse> call, Response<FetchingSellerDataBySrNoResponse> response) {
                        FetchingSellerDataBySrNoResponse fetchingSellerDataBySrNoResponse =response.body();
                        if(response.isSuccessful()){
                            if(fetchingSellerDataBySrNoResponse.getError().equals("201")){
                                sessionMaintainer.savesellerdata(fetchingSellerDataBySrNoResponse.getSeller());
                                Toast.makeText(getContext(), fetchingSellerDataBySrNoResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                firstname.setText(fetchingSellerDataBySrNoResponse.getSeller().getFirstName());
                                lastname.setText(fetchingSellerDataBySrNoResponse.getSeller().getLastName());
                                basicrates.setText(String.valueOf(fetchingSellerDataBySrNoResponse.getSeller().getBasicRates()));
                                weight.setText(String.valueOf(fetchingSellerDataBySrNoResponse.getSeller().getWeight()));
                                accountnumber.setText(fetchingSellerDataBySrNoResponse.getSeller().getAccountNumber());
                                Commodity=fetchingSellerDataBySrNoResponse.getSeller().getCommodity();
                                MobileNumber=fetchingSellerDataBySrNoResponse.getSeller().getMobileNumber();
                                ResidentialAddress=fetchingSellerDataBySrNoResponse.getSeller().getResidentialAddress();
                                PurchaseDate=fetchingSellerDataBySrNoResponse.getSeller().getPurchaseDate();
                                IFSCode=fetchingSellerDataBySrNoResponse.getSeller().getIFSCode();
                                PaymentMethod=fetchingSellerDataBySrNoResponse.getSeller().getPaymentMethod();
                                SrNoS=UniqueId.getText().toString();
                                CustomerOf=sessionMaintainer.getuser().getEmailId();

                                Toast.makeText(getContext(), CustomerOf, Toast.LENGTH_SHORT).show();
                            }else if (fetchingSellerDataBySrNoResponse.getError().equals("200")){
                                Toast.makeText(getContext(), fetchingSellerDataBySrNoResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getContext(), "Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FetchingSellerDataBySrNoResponse> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstname.getText().toString().isEmpty() && lastname.getText().toString().isEmpty() && basicrates.getText().toString().isEmpty() &&weight.getText().toString().isEmpty()&&accountnumber.getText().toString().isEmpty() ){
                    Toast.makeText(getContext(), "Please fill the Required Filled to Proceed !", Toast.LENGTH_SHORT).show();
                }else{
                FirstName =firstname.getText().toString();
                LastName =lastname.getText().toString();
                AccountNumber =accountnumber.getText().toString();
                BasicRates = Float.parseFloat(basicrates.getText().toString());
                Weight =Float.parseFloat(weight.getText().toString());
                BeneficiaryAmount = Float.parseFloat(basicrates.getText().toString())*Float.parseFloat(weight.getText().toString());
                BeneficiaryAmount=(float)Math.round(BeneficiaryAmount*100)/100;
                Toast.makeText(getContext(), SrNoS+" "+FirstName+" "+LastName+" "+Commodity+" "+BasicRates+" "+Weight+" "+BeneficiaryAmount+" "+PaymentMethod+" "+AccountNumber+" "+IFSCode+" "+PurchaseDate+" "+MobileNumber+" "+ResidentialAddress+" "+CustomerOf, Toast.LENGTH_SHORT).show();
                Call<RebillingResponse> call = RetrofitClient.getInstance().getApi().ApiReuploadBillData(SrNoS,FirstName,LastName,Commodity,BasicRates,Weight,BeneficiaryAmount, PaymentMethod,AccountNumber,IFSCode,PurchaseDate,MobileNumber,ResidentialAddress,CustomerOf);
                call.enqueue(new Callback<RebillingResponse>() {
                    @Override
                    public void onResponse(Call<RebillingResponse> call, Response<RebillingResponse> response) {
                        RebillingResponse rebillingResponse = response.body();
                        if(response.isSuccessful()){
                            if(rebillingResponse.getError().equals("502")){
                                Toast.makeText(getContext(), rebillingResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }else if (rebillingResponse.getError().equals("503")) {
                                Toast.makeText(getContext(), rebillingResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(getContext(), "Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RebillingResponse> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                }
            }
        });

        return root;
    }

    private void deleteAccount() {
        Call<DeleteSellerDataResponse> call = RetrofitClient.getInstance().getApi().ApiDeleteSeller(SrNoS);
        call.enqueue(new Callback<DeleteSellerDataResponse>() {
            @Override
            public void onResponse(Call<DeleteSellerDataResponse> call, Response<DeleteSellerDataResponse> response) {
                DeleteSellerDataResponse deleteSellerDataResponse =response.body();
                if(response.isSuccessful()){
                    if(deleteSellerDataResponse.getError().equals("200")){
                        Toast.makeText(getContext(), deleteSellerDataResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }else if(deleteSellerDataResponse.getError().equals("201")){
                        Toast.makeText(getContext(), deleteSellerDataResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteSellerDataResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
