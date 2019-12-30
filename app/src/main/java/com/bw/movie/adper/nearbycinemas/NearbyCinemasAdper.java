package com.bw.movie.adper.nearbycinemas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.nearbycinemas.NearbyCinemasResult;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class NearbyCinemasAdper extends RecyclerView.Adapter<NearbyCinemasAdper.NearbyCinemasViewHolder> {
   private List<NearbyCinemasResult> result =new ArrayList<>();
    public void addAll(List<NearbyCinemasResult> result){
        this.result.addAll(result);
    }
    @NonNull
    @Override
    public NearbyCinemasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nearby_item, parent, false);
        return new NearbyCinemasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NearbyCinemasViewHolder holder, int position) {
        String logo = result.get(position).getLogo();
        String name = result.get(position).getName();
        int distance = result.get(position).getDistance();
        String address = result.get(position).getAddress();
        holder.nearby_logo.setImageURI(logo);
        holder.tv_nearby_name.setText(name);
        holder.tv_nearby_address.setText(address);
        holder.nearby_distance.setText(distance+"m");
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class NearbyCinemasViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView nearby_logo;
        TextView tv_nearby_name, tv_nearby_address,nearby_distance;
        public NearbyCinemasViewHolder(@NonNull View itemView) {
            super(itemView);
            nearby_logo = itemView.findViewById(R.id.nearby_logo);
            tv_nearby_name = itemView.findViewById(R.id.tv_nearby_name);
            tv_nearby_address = itemView.findViewById(R.id.tv_nearby_address);
            nearby_distance = itemView.findViewById(R.id.nearby_distance);
        }
    }
}
