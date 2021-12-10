package com.therohansharma03.selfaccounting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.therohansharma03.selfaccounting.allResponses.User;

import java.util.List;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.ViewHolder> {

    Context context;
    List<User> userList;

    public UserDataAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.seller_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDataAdapter.ViewHolder holder, int position) {
        try {

            holder.srno.setText(userList.get(position).getSrNo());
            holder.name.setText(userList.get(position).getName());
            holder.emialid.setText(userList.get(position).getEmailId());
            holder.mobilenumber.setText(userList.get(position).getMobileNumber());
        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        int x = 0;
        try{
            x= userList.size();
        }catch (Exception e){
            
        }
        return x;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView srno,name,emialid,mobilenumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            srno=itemView.findViewById(R.id.srnumber);
            name=itemView.findViewById(R.id.name);
            emialid=itemView.findViewById(R.id.emailid);
            mobilenumber=itemView.findViewById(R.id.mobilenumber);
        }
    }
}
