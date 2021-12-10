package com.therohansharma03.selfaccounting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

public class Billing extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

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
        String hbfullname = intent.getStringExtra("keyfirstname");
        String hblastname = intent.getStringExtra("keylastname");
        String hbbasicrate = intent.getStringExtra("keybasicrate");
        String hbweigth = intent.getStringExtra("keyweight");
        String hbmethodeofpayment = intent.getStringExtra("keymodeofpayment");
        String hbaccountnumber = intent.getStringExtra("keyaccountnumber");
        String hbifsccodde = intent.getStringExtra("keyifsccode");
        String hbcommodity = intent.getStringExtra("keycommodity");
        String hbresidentialaddress = intent.getStringExtra("keyresidentialaddress");
        String hbphonenumber = intent.getStringExtra("keyphonenumber");
        String hbuniqueid = intent.getStringExtra("keyuniqueid");
        String hbtotalamount = intent.getStringExtra("keybeneficiaryamount");

        bfullname.setText(hbfullname+" "+hblastname);
        bbasicrate.setText(hbbasicrate);
        bweight.setText(hbweigth);
        bresidentialaddress.setText(hbresidentialaddress);
        bphonenumber.setText(hbphonenumber);
        bcommodity.setText(hbcommodity);
        bmethodeofpayment.setText(hbmethodeofpayment);
        btotalamount.setText(String.valueOf(hbtotalamount));
        baccountnumber.setText(hbaccountnumber);
        bifsccode.setText(hbifsccodde);
        buniqueid.setText(hbuniqueid);
        String date=String.valueOf(java.time.LocalDate.now());
        bpurchasedate.setText(date);

    }

}