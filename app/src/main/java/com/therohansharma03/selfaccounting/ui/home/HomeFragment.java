package com.therohansharma03.selfaccounting.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.therohansharma03.selfaccounting.Billing;
import com.therohansharma03.selfaccounting.R;
import com.therohansharma03.selfaccounting.RetrofitClient;
import com.therohansharma03.selfaccounting.SessionMaintainer;
import com.therohansharma03.selfaccounting.allResponses.BillingResponse;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeFragment extends Fragment {
    SessionMaintainer sessionMaintainer;
    Intent i;
    String PurchaseDate;
    String CustomerOf;
    String FirstName;
    String LastName;
    String Commodity;
    float BasicRates;
    float Weight;
    float BeneficiaryAmount;
    String ResidentialAddress;
    String AccountNumber;
    String IFSCode;
    String PhoneNumber;
    String PaymentMethod;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);

        EditText hFirstName = root.findViewById(R.id.hFirstName);
        EditText hLastName = root.findViewById(R.id.hLastName);
        EditText hCommodity = root.findViewById(R.id.hCommodity);
        EditText hBasicRate = root.findViewById(R.id.hBasicRates);
        EditText hWeight = root.findViewById(R.id.hWeight);
        EditText hModeOfPayment = root.findViewById(R.id.hModePayment);
        EditText hAccountNumber = root.findViewById(R.id.hAccountNumber);
        EditText hMobileNumber = root.findViewById(R.id.hMobileNumber);
        EditText hIfsc = root.findViewById(R.id.hIfscCode);
        TextView hBankname = root.findViewById(R.id.hBankName);
        TextView hBranchname = root.findViewById(R.id.hBranchName);
        EditText hResidentialAddress = root.findViewById(R.id.hRsidentialAddress);
        Button ProccedToPayment = root.findViewById(R.id.hProceedToBillBtn);
        Button hCheckIfsc = root.findViewById(R.id.hCheckIfsc);
        Switch localcustomer = root.findViewById(R.id.hf_switch_shift_to_local);
        sessionMaintainer = new SessionMaintainer(getContext());
        PurchaseDate=String.valueOf(java.time.LocalDate.now());

        hCheckIfsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue;
                requestQueue = Volley.newRequestQueue(getContext());
                String urlifsc ="https://ifsc.razorpay.com"+"/"+hIfsc.getText().toString();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,urlifsc, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            hBankname.setText(response.getString("BANK"));
                            hBranchname.setText(response.getString("BRANCH")+" "+response.getString("ADDRESS"));
                            Log.d("myapp",response.getString("BANK"));
                            Log.d("myapp",response.getString("BRANCH"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Something went wrong : Check Internet Connection or Enter correct value !", Toast.LENGTH_SHORT).show();
                        Log.d("myapp","Something went worng");
                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });

        ProccedToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hFirstName.getText().toString().isEmpty() && hLastName.getText().toString().isEmpty() && hCommodity.getText().toString().isEmpty() && hBasicRate.getText().toString().isEmpty() && hWeight.getText().toString().isEmpty() && hMobileNumber.getText().toString().isEmpty()&& hModeOfPayment.getText().toString().isEmpty() && hResidentialAddress.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Please fill the Required fields to Procced for Bill !", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(hModeOfPayment.getText().toString().equals("Cash") || hModeOfPayment.getText().toString().equals("cash") || hModeOfPayment.getText().toString().equals("CASH")){
                        hAccountNumber.setText("Purchase In cash ");
                        hIfsc.setText("NR");

                        FirstName =hFirstName.getText().toString();
                        LastName =hLastName.getText().toString();
                        Commodity =hCommodity.getText().toString();
                        AccountNumber =hAccountNumber.getText().toString();
                        IFSCode =hIfsc.getText().toString();
                        PaymentMethod =hModeOfPayment.getText().toString();
                        ResidentialAddress =hResidentialAddress.getText().toString();
                        BasicRates = Float.parseFloat(hBasicRate.getText().toString());
                        Weight =Float.parseFloat(hWeight.getText().toString());
                        PhoneNumber =hMobileNumber.getText().toString();
                        CustomerOf=sessionMaintainer.getuser().getEmailId();
                        BeneficiaryAmount = Float.parseFloat(hBasicRate.getText().toString())*Float.parseFloat(hWeight.getText().toString());
                        BeneficiaryAmount=(float)Math.round(BeneficiaryAmount*100)/100;

                        i = new Intent(getActivity(), Billing.class);
                        i.putExtra("keyfirstname",hFirstName.getText().toString());
                        i.putExtra("keylastname",hLastName.getText().toString());
                        i.putExtra("keyweight",hWeight.getText().toString());
                        i.putExtra("keybasicrate",hBasicRate.getText().toString());
                        i.putExtra("keyaccountnumber","Not required paid in cash !");
                        i.putExtra("keyifsccode","NR");
                        i.putExtra("keyphonenumber",hMobileNumber.getText().toString());
                        i.putExtra("keyresidentialaddress",hResidentialAddress.getText().toString());
                        i.putExtra("keycommodity",hCommodity.getText().toString());
                        i.putExtra("keypurchasedate",PurchaseDate);
                        i.putExtra("keymodeofpayment",hModeOfPayment.getText().toString());
                        i.putExtra("keybeneficiaryamount",String.valueOf(BeneficiaryAmount));
                        billing();
                    }else{

                        FirstName =hFirstName.getText().toString();
                        LastName =hLastName.getText().toString();
                        Commodity =hCommodity.getText().toString();
                        AccountNumber =hAccountNumber.getText().toString();
                        IFSCode =hIfsc.getText().toString();
                        PaymentMethod =hModeOfPayment.getText().toString();
                        PhoneNumber =hMobileNumber.getText().toString();
                        ResidentialAddress =hResidentialAddress.getText().toString();
                        BasicRates = Float.parseFloat(hBasicRate.getText().toString());
                        Weight =Float.parseFloat(hWeight.getText().toString());
                        CustomerOf=sessionMaintainer.getuser().getEmailId();
                        BeneficiaryAmount = Float.parseFloat(hBasicRate.getText().toString())*Float.parseFloat(hWeight.getText().toString());
                        BeneficiaryAmount=(float)Math.round(BeneficiaryAmount*100)/100;

                        i = new Intent(getActivity(), Billing.class);
                        i.putExtra("keyfirstname",hFirstName.getText().toString());
                        i.putExtra("keylastname",hLastName.getText().toString());
                        i.putExtra("keyweight",hWeight.getText().toString());
                        i.putExtra("keybasicrate",hBasicRate.getText().toString());
                        i.putExtra("keyaccountnumber",hAccountNumber.getText().toString());
                        i.putExtra("keyifsccode",hIfsc.getText().toString());
                        i.putExtra("keyresidentialaddress",hResidentialAddress.getText().toString());
                        i.putExtra("keycommodity",hCommodity.getText().toString());
                        i.putExtra("keymodeofpayment",hModeOfPayment.getText().toString());
                        i.putExtra("keyphonenumber",hMobileNumber.getText().toString());
                        i.putExtra("keypurchasedate",PurchaseDate);
                        i.putExtra("keyuniqueid",sessionMaintainer.getuser().getSrNo());
                        i.putExtra("keybeneficiaryamount",String.valueOf(BeneficiaryAmount));
                        billing();
                    }
                }

            }
        });

        localcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(localcustomer.isChecked()==true ){
                    Toast.makeText(getContext(), "Local mode ON", Toast.LENGTH_SHORT).show();
                        hAccountNumber.setText("Not required !");
                        hIfsc.setText("Not required !");
                        hFirstName.setText("Local");
                        hLastName.setText("NA");
                        hResidentialAddress.setText("Purchased at shop");
                        hModeOfPayment.setText("Paid in cash !");
                        hMobileNumber.setText("Not Available !");

                        FirstName =hFirstName.getText().toString();
                        LastName =hLastName.getText().toString();
                        Commodity =hCommodity.getText().toString();
                        AccountNumber =hAccountNumber.getText().toString();
                        IFSCode =hIfsc.getText().toString();
                        PaymentMethod =hModeOfPayment.getText().toString();
                        ResidentialAddress =hResidentialAddress.getText().toString();
                        try {
                            BasicRates = Float.parseFloat(hBasicRate.getText().toString());
                            Weight = Float.parseFloat(hWeight.getText().toString());
                            BeneficiaryAmount = Float.parseFloat(hBasicRate.getText().toString())*Float.parseFloat(hWeight.getText().toString());
                            BeneficiaryAmount=(float)Math.round(BeneficiaryAmount*100)/100;

                        }catch (Exception e){
//                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        PhoneNumber =hMobileNumber.getText().toString();
                        CustomerOf=sessionMaintainer.getuser().getEmailId();

                        i = new Intent(getActivity(), Billing.class);
                        i.putExtra("keyfirstname","Local");
                        i.putExtra("keylastname","NA");
                        i.putExtra("keyweight",hWeight.getText().toString());
                        i.putExtra("keybasicrate",hBasicRate.getText().toString());
                        i.putExtra("keyaccountnumber","Not required !");
                        i.putExtra("keyifsccode","Not required !");
                        i.putExtra("keyresidentialaddress","Purchased at shop");
                        i.putExtra("keycommodity",hCommodity.getText().toString());
                        i.putExtra("keymodeofpayment","Paid in cash !");
                        i.putExtra("keyphonenumber","Not Available");
                        i.putExtra("keybeneficiaryamount",String.valueOf(BeneficiaryAmount));


                    if( hCommodity.getText().toString().length()>0 && hBasicRate.getText().toString().length()>0  && hWeight.getText().toString().length()>0 ){
                            billing();
                            startActivity(i);
                        }else{
                            Toast.makeText(getContext(), "Enter the values to proceed !", Toast.LENGTH_SHORT).show();
                        }

                }else{
                    Toast.makeText(getContext(), "Local mode OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    private void billing(){
        Call<BillingResponse> call = RetrofitClient.getInstance().getApi().uploadbilldata(FirstName,LastName,Commodity,BasicRates,Weight,BeneficiaryAmount,PaymentMethod,AccountNumber,IFSCode,PurchaseDate,PhoneNumber,ResidentialAddress,CustomerOf);
        call.enqueue(new Callback<BillingResponse>() {
            @Override
            public void onResponse(Call<BillingResponse> call, retrofit2.Response<BillingResponse> response) {
                BillingResponse billingResponse =response.body();
                if(response.isSuccessful()){
                    if(billingResponse.getError().equals("502")){
                        Toast.makeText(getContext(), billingResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }
                    else if (billingResponse.getError().equals("503")){
                        Toast.makeText(getContext(), billingResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BillingResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}