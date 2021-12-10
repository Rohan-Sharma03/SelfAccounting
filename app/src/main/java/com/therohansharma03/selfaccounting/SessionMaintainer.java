package com.therohansharma03.selfaccounting;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.therohansharma03.selfaccounting.allResponses.Seller;
import com.therohansharma03.selfaccounting.allResponses.User;

public class SessionMaintainer {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    Context context;

    public SessionMaintainer(Context context) {
        this.context = context;
    }

    public void savedata(User user){
        sharedPreferences=context.getSharedPreferences("SelfAccouting",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putInt("SrNo",user.getSrNo());
        editor.putString("Name",user.getName());
        editor.putString("EmailId", user.getEmailId());
        editor.putBoolean("logged",true);
        editor.apply();
    }
    public void savesellerdata(Seller seller){
        sharedPreferences=context.getSharedPreferences("SelfAccouting",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putInt("SrNo",seller.getSrNo());
        editor.putString("FirstName",seller.getFirstName());
        editor.putString("LastName", seller.getLastName());
        editor.putString("Commodity",seller.getCommodity());
        editor.putFloat("BasicRates", (float) seller.getBasicRates());
        editor.putFloat("Weight", (float) seller.getWeight());
        editor.putFloat("BeneficiaryAmount", (float) seller.getBeneficiaryAmount());
        editor.putString("PaymentMethod", seller.getPaymentMethod());
        editor.putString("AccountNumber",seller.getAccountNumber());
        editor.putString("IFSCode",seller.getIFSCode());
        editor.putString("PurchaseDate", String.valueOf(seller.getPurchaseDate()));
        editor.putString("MobileNumber",seller.getMobileNumber());
        editor.putString("ResidentialAddress", seller.getResidentialAddress());
        editor.putString("CustomerOf",seller.getCustomerOf());
//        editor.putBoolean("logged",true);
        editor.apply();
    }
    boolean isloggedIn(){
        sharedPreferences =context.getSharedPreferences("SelfAccouting",MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged",false);
    }
    public  User getuser(){
        sharedPreferences =context.getSharedPreferences("SelfAccouting",MODE_PRIVATE);
        return new User(sharedPreferences.getInt("SrNo",-1),
                sharedPreferences.getString("Name",null),
                sharedPreferences.getString("EmailId",null),
                sharedPreferences.getString("MobileNumber",null));
    }
    public Seller getseller(Seller seller){
        sharedPreferences =context.getSharedPreferences("SelfAccouting",MODE_PRIVATE);
        return new Seller(sharedPreferences.getInt("SrNo",0),
                sharedPreferences.getString("FirstName",null),
                sharedPreferences.getString("LastName",null),
                sharedPreferences.getString("Commodity",null),
                sharedPreferences.getFloat("BasicRates",0),
                sharedPreferences.getFloat("Weight",0),
                sharedPreferences.getFloat("BeneficiaryAmount",0),
                sharedPreferences.getString("PaymentMethod",null),
                sharedPreferences.getString("AccountNumber",null),
                sharedPreferences.getString("IFSCode",null),
                sharedPreferences.getString("PurchaseDate",null),
                sharedPreferences.getString("MobileNumber",null),
                sharedPreferences.getString("ResidentialAddress",null),
                sharedPreferences.getString("CustomerOf",null));

    }
    void logout(){
        sharedPreferences =context.getSharedPreferences("SelfAccouting",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
