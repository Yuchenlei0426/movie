package com.bw.movie.adper;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.activtiy.moviedetails.DetailsActivity;
import com.bw.movie.bean.eventbean.MovieBus;
import com.bw.movie.bean.findComingSoonMovieList.FindComingSoonMovieList;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ComingSoonAdper extends RecyclerView.Adapter<ComingSoonAdper.ComingSoonViewHolder> {
    List<FindComingSoonMovieList> comingSoonResult;
    public void addAll(List<FindComingSoonMovieList> comingSoonResult){
        this.comingSoonResult=comingSoonResult;
    }


    @NonNull
    @Override
    public ComingSoonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.coming_soon_child_item, viewGroup, false);
        return new ComingSoonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComingSoonViewHolder comingSoonViewHolder, int i) {
        comingSoonViewHolder.sv_con_imageUrl.setImageURI(comingSoonResult.get(i).getImageUrl());
        comingSoonViewHolder.tv_con_name.setText(comingSoonResult.get(i).getName());
        comingSoonViewHolder.tv_con_wantSeeNum.setText(comingSoonResult.get(i).getWantSeeNum()+"人想看");
        Date date = new Date(comingSoonResult.get(i).getReleaseTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        comingSoonViewHolder.tv_con_releaseTime.setText(dateFormat.format(date)+"上映");
        comingSoonViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),DetailsActivity.class);
                int movieId = comingSoonResult.get(i).getMovieId();
                String name = comingSoonResult.get(i).getName();
                EventBus.getDefault().postSticky(new MovieBus(movieId,name));
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return comingSoonResult.size();
    }

    class  ComingSoonViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView sv_con_imageUrl;
        TextView tv_con_name,tv_con_releaseTime,tv_con_wantSeeNum;
        public ComingSoonViewHolder(@NonNull View itemView) {
            super(itemView);
            sv_con_imageUrl = itemView.findViewById(R.id.sv_con_imageUrl);
            tv_con_name= itemView.findViewById(R.id.tv_con_name);
            tv_con_releaseTime= itemView.findViewById(R.id.tv_con_releaseTime);
            tv_con_wantSeeNum = itemView.findViewById(R.id.tv_con_wantSeeNum);
        }
    }
}
