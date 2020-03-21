package com.example.ars;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ars.Response.FoodResponse;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {


    List<FoodResponse> models;
    Context context;

    public MenuAdapter(Context context, List<FoodResponse> models) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);


        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.imageView.setImageResource(arr[position]);
//        holder.textView.setText("Image No " + position);
        String base64String1 = models.get(position).getFoodImg();
        String base64String = base64String1.substring(base64String1.indexOf(",") + 1);
        final byte[] decodedBytes = Base64.decode(base64String, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        holder.imageView.setImageBitmap(decodedBitmap);
        holder.foodName.setText(models.get(position).getFoodName());
        holder.foodDesc.setText(models.get(position).getFoodDesc());
        holder.price.setText(models.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView foodName;
        TextView foodDesc;
        TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.menuImageView);
            foodName = itemView.findViewById(R.id.foodName);
            foodDesc = itemView.findViewById(R.id.foodDesc);
            price = itemView.findViewById(R.id.price);
        }
    }

}
