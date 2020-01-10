package com.bw.movie.adper;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.activtiy.CinemaDetailsActivity;
import com.bw.movie.bean.cinemas.CinemasInfoByDate;
import com.bw.movie.bean.eventbean.CinemaIdEven;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class InfoByDateAdper extends RecyclerView.Adapter<InfoByDateAdper.InfoByDateViewHolder> {
    private List<CinemasInfoByDate> result;
    public void addAll(List<CinemasInfoByDate> result){
        this.result=result;
    }
    @NonNull
    @Override
    public InfoByDateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_item, parent, false);
        return new InfoByDateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoByDateViewHolder holder, int position) {
        String logo = result.get(position).getLogo();
        if (logo!=null) {
            holder.iv_logo.setImageURI(logo);
        }
        String name = result.get(position).getName();
        if (name!=null) {
            holder.tv_by_name.setText(name);
        }
        String address = result.get(position).getAddress();
        if (address!=null) {
            holder.tv_by_address.setText(address);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                影院详情 CinemaDetails
                Intent intent = new Intent(holder.itemView.getContext(), CinemaDetailsActivity.class);
                int cinemaId = result.get(position).getCinemaId();
                EventBus.getDefault().postSticky(new CinemaIdEven(cinemaId));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class InfoByDateViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView iv_logo;
        TextView tv_by_name,tv_by_address;
        public InfoByDateViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_logo = itemView.findViewById(R.id.iv_logo);
            tv_by_name = itemView.findViewById(R.id.tv_by_name);
            tv_by_address = itemView.findViewById(R.id.tv_by_address);
        }
    }
}
