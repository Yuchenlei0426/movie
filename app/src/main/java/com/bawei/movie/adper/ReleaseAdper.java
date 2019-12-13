package com.bawei.movie.adper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.movie.R;
import com.bawei.movie.bean.findReleaseMovieList.FindReleaseMovieList;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ReleaseAdper extends RecyclerView.Adapter<ReleaseAdper.ReleaseViewHolder> {
    List<FindReleaseMovieList> releaseResult;

    public void addAll(List<FindReleaseMovieList> releaseResult) {
        this.releaseResult=releaseResult;
    }

    @NonNull
    @Override
    public ReleaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.release_child_item, viewGroup, false);
        return new ReleaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReleaseViewHolder releaseViewHolder, int i) {
        releaseViewHolder.iv_re_imageUrl.setImageURI(releaseResult.get(i).getImageUrl());
        releaseViewHolder.tv_re_score.setText(releaseResult.get(i).getScore()+"");
        releaseViewHolder.re_name.setText(releaseResult.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return releaseResult.size();
    }

    class ReleaseViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView iv_re_imageUrl;
        TextView tv_re_score,re_name;
        public ReleaseViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_re_imageUrl = itemView.findViewById(R.id.iv_re_imageUrl);
            tv_re_score = itemView.findViewById(R.id.tv_re_score);
            re_name = itemView.findViewById(R.id.re_name);
        }
    }
}
