package com.example.androidbarberbooking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidbarberbooking.Model.Salon;
import com.example.androidbarberbooking.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MySalonAdapter extends RecyclerView.Adapter<MySalonAdapter.MyViewHolder> {

    Context context;
    List<Salon> salonList;

    public MySalonAdapter(Context context, List<Salon> salonList) {
        this.context = context;
        this.salonList = salonList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup viewGroup = null;
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_salon,viewGroup,false);
        return new  MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyViewHolder myViewHolder = null;
        int i = 0;
        myViewHolder.txt_salon_name.setText(salonList.get(i).getName());
        myViewHolder.txt_salon_address.setText(salonList.get(i).getAddress());
    }

    @Override
    public int getItemCount() {
        return salonList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_salon_name, txt_salon_address;
        public MyViewHolder(View itemView) {
            super(itemView);

            txt_salon_name = (TextView) itemView.findViewById(R.id.txt_salon_name);
            txt_salon_address = (TextView) itemView.findViewById(R.id.txt_salon_address);
        }
    }
}
