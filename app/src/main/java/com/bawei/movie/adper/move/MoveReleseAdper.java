package com.bawei.movie.adper.move;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.movie.R;
import com.bawei.movie.bean.findReleaseMovieList.FindReleaseMovieList;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MoveReleseAdper extends RecyclerView.Adapter<MoveReleseAdper.MoveReleseViewHolder> {

    List<FindReleaseMovieList> result =new ArrayList<>();
    public void addAll(List<FindReleaseMovieList> result){
        this.result.addAll(result);
    }

    @NonNull
    @Override
    public MoveReleseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.move_relese_item, viewGroup, false);
        return new MoveReleseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoveReleseViewHolder moveReleseViewHolder, int i) {
        String imageUrl = result.get(i).getImageUrl();
        moveReleseViewHolder.move_sv.setImageURI(imageUrl);
        moveReleseViewHolder.tv_move_re_name.setText(result.get(i).getName());
        moveReleseViewHolder.tv_move_re_director.setText("导演:"+result.get(i).getDirector());
        moveReleseViewHolder.tv_move_re_starring.setText("主演:"+result.get(i).getStarring());
        double score = result.get(i).getScore();
        moveReleseViewHolder.tv_move_re_score.setText("评分:"+score+"分");
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MoveReleseViewHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView move_sv;
        TextView tv_move_re_name, tv_move_re_director,tv_move_re_starring,tv_move_re_score;
        public MoveReleseViewHolder(@NonNull View itemView) {
            super(itemView);
            move_sv = itemView.findViewById(R.id.move_sv);
            tv_move_re_name = itemView.findViewById(R.id.tv_move_re_name);
            tv_move_re_director = itemView.findViewById(R.id.tv_move_re_director);
            tv_move_re_starring = itemView.findViewById(R.id.tv_move_re_starring);
            tv_move_re_score = itemView.findViewById(R.id.tv_move_re_score);
        }
    }
}
