package com.me.familybookkeepingbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdpter extends RecyclerView.Adapter <MyAdpter.MyViewHolder>{
    List<AccountRecord> allAccountRecords = new ArrayList<>();
    public void setAllAccountRecords(List<AccountRecord> allAccountRecords) {
        this.allAccountRecords = allAccountRecords;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cost_itme_c_1,parent,false);
        MyViewHolder holder = new MyViewHolder(itemView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AccountRecord accountRecord = allAccountRecords.get(position);
        holder.textViewId.setText(String.valueOf(position+1));
        holder.textViewMoney.setText(String.valueOf(accountRecord.getCostMoney()));
        holder.textViewTime.setText(accountRecord.getCostTime());
        holder.textViewType.setText(accountRecord.getCostType());
    }

    @Override
    public int getItemCount() {
        return allAccountRecords.size() ;
    }

    static class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewId,textViewType,textViewMoney,textViewTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewid);
            textViewType = itemView.findViewById(R.id.textViewtype);
            textViewTime = itemView.findViewById(R.id.textViewtime);
            textViewMoney = itemView.findViewById(R.id.textViewmoney);
        }
    }

}
