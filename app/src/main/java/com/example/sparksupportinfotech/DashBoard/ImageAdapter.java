package com.example.sparksupportinfotech.DashBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sparksupportinfotech.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.myviewholder> {


    private List<DashboardResponse> imageList;
    private Context context;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<DashboardResponse> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_image,parent,false);
       return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.myviewholder holder, int position) {

        DashboardResponse imageItem = imageList.get(position);

        // Load the image using Glide
        Glide.with(context)
                .load(imageItem.getImage_link())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return imageList != null ? imageList.size() : 0;
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView1);
        }
    }
}
