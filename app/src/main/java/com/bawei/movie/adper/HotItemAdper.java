package com.bawei.movie.adper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.movie.R;
import com.bawei.movie.bean.findHotMovieList.MovieResult;
import com.facebook.drawee.view.SimpleDraweeView;

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
