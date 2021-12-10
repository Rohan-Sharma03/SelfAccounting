package com.therohansharma03.selfaccounting;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.therohansharma03.selfaccounting.allResponses.Seller;

import java.util.List;

public class SellerDataAdapter extends RecyclerView.Adapter<SellerDataAdapter.ViewHolder> {

    Context context;
    List<Seller> sellersList;

    public SellerDataAdapter(Context context, List<Seller> sellersList) {
        this.context = context;
        this.sellersList = sellersList;
    }

    @NonNull
    @Override
    public SellerDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.seller_item,parent,false);
        return new SellerDataAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SellerDataAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

            holder.srno.setText(String.valueOf(sellersList.get(position).getSrNo()));
            holder.name.setText(sellersList.get(position).getFirstName());
            holder.emialid.setText(sellersList.get(position).getLastName());
            holder.mobilenumber.setText(sellersList.get(position).getMobileNumber());
            holder.dateofpurchase.setText(sellersList.get(position).getPurchaseDate());
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context.getApplicationContext(),Billing.class);
                    intent.putExtra("keyfirstname",sellersList.get(position).getFirstName());
                    intent.putExtra("keylastname",sellersList.get(position).getLastName());
                    intent.putExtra("keyweight",String.valueOf(sellersList.get(position).getWeight()));
                    intent.putExtra("keybasicrate",String.valueOf(sellersList.get(position).getBasicRates()));
                    intent.putExtra("keyaccountnumber",sellersList.get(position).getAccountNumber());
                    intent.putExtra("keyifsccode",sellersList.get(position).getIFSCode());
                    intent.putExtra("keyresidentialaddress",sellersList.get(position).getResidentialAddress());
                    intent.putExtra("keycommodity",sellersList.get(position).getCommodity());
                    intent.putExtra("keymodeofpayment",sellersList.get(position).getPaymentMethod());
                    intent.putExtra("keyphonenumber",sellersList.get(position).getMobileNumber());
                    intent.putExtra("keypurchasedate",sellersList.get(position).getPurchaseDate());
                    intent.putExtra("keyuniqueid",String.valueOf(sellersList.get(position).getSrNo()));
                    intent.putExtra("keybeneficiaryamount",String.valueOf(sellersList.get(position).getBeneficiaryAmount()));
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        int x = 0;
        try{
            x= sellersList.size();
        }catch (Exception e){

        }
        return x;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView srno,name,emialid,mobilenumber,dateofpurchase;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            srno=itemView.findViewById(R.id.srnumber);
            name=itemView.findViewById(R.id.name);
            emialid=itemView.findViewById(R.id.emailid);
            mobilenumber=itemView.findViewById(R.id.mobilenumber);
            dateofpurchase=itemView.findViewById(R.id.dateofpurchase);
            linearLayout=itemView.findViewById(R.id.user_item_layout_id);
        }
    }
}
