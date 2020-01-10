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
import com.bw.movie.bean.findHotMovieList.MovieResult;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class HotItemAdper extends RecyclerView.Adapter<HotItemAdper.HotViewHolder> {
    List<MovieResult> result;
    public  void addAll(List<MovieResult> result){
        this.result=result;
    }
    @NonNull
    @Override
    public HotViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hot_child_item, viewGroup, false);
        return new HotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotViewHolder hotViewHolder, int i) {
        if (result!=null){
            SimpleDraweeView iv_imageUrl = hotViewHolder.iv_imageUrl;
            iv_imageUrl.setImageURI(result.get(i).getImageUrl());
            hotViewHolder.tv_score.setText(result.get(i).getScore()+"");
            hotViewHolder.name.setText(result.get(i).getName());
            hotViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                    int movieId = result.get(i).getMovieId();
                    String name = result.get(i).getName();
                    EventBus.getDefault().postSticky(new MovieBus(movieId,name));
                    v.getContext().startActivity(intent);

                }
            });
        }else{

        }

    }

    @Override
    public int getItemCount() {
        if(result !=null){
            return result.size();
        }else{
            return 0;
        }

    }

    class HotViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView iv_imageUrl;
        TextView tv_score,name;
        public HotViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_imageUrl = itemView.findViewById(R.id.iv_imageUrl);
            tv_score = itemView.findViewById(R.id.tv_score);
            name = itemView.findViewById(R.id.name);
        }
    }

}
