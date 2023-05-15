package com.example.androidbarberbooking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.androidbarberbooking.Model.Banner;
import com.example.androidbarberbooking.R;
import com.squareup.picasso.Picasso;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class LookbookAdapter extends RecyclerView.Adapter<LookbookAdapter.MyViewHolder> {

    Context context;
    List<Banner> lookbook;

    public LookbookAdapter(Context context, List<Banner> lookbook) {
        this.context = context;
        this.lookbook = lookbook;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_look_book,viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.get().load(lookbook.get(i).getImage()).into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return lookbook.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image_look_book);
        }
    }
}
