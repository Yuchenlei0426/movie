package com.bawei.movie.adper.move;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.movie.R;
import com.bawei.movie.bean.findHotMovieList.MovieResult;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MoveHotAdper extends RecyclerView.Adapter<MoveHotAdper.MoveViewHolder> {

    List<MovieResult> result =new ArrayList<>();
    public void addAll(List<MovieResult> result){
        this.result.addAll(result);
    }

    @NonNull
    @Override
    public MoveViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.move_hot_item, viewGroup, false);
        return new MoveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoveViewHolder moveViewHolder, int i) {
        String imageUrl = result.get(i).getImageUrl();
        moveViewHolder.move_sv.setImageURI(imageUrl);
        moveViewHolder.tv_move_name.setText(result.get(i).getName());
        moveViewHolder.tv_move_director.setText("导演:"+result.get(i).getDirector());
        moveViewHolder.tv_move_starring.setText("主演:"+result.get(i).getStarring());
        moveViewHolder.tv_move_score.setText("评分:"+result.get(i).getScore()+"分");
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MoveViewHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView move_sv;
        TextView tv_move_name, tv_move_director,tv_move_starring,tv_move_score;
        public MoveViewHolder(@NonNull View itemView) {
            super(itemView);
            move_sv = itemView.findViewById(R.id.move_sv);
            tv_move_name = itemView.findViewById(R.id.tv_move_name);
            tv_move_director = itemView.findViewById(R.id.tv_move_director);
            tv_move_starring = itemView.findViewById(R.id.tv_move_starring);
            tv_move_score = itemView.findViewById(R.id.tv_move_score);
        }
    }
}
