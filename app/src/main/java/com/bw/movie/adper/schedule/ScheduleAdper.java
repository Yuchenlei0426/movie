package com.bw.movie.adper.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;

import com.bw.movie.bean.findHotMovieList.MovieResult;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ScheduleAdper extends RecyclerView.Adapter<ScheduleAdper.ScheduleViewHolder> {
    private List<MovieResult> result;
    public void addAll(List<MovieResult> result){
        this.result=result;
    }
    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        String name = result.get(position).getName();
        holder.tv_sch_name.setText(name);
        String imageUrl = result.get(position).getImageUrl();
        holder.iv_sch_imageUrl.setImageURI(imageUrl);
        String director = result.get(position).getDirector();
        holder.tv_sch_director.setText("导演:"+director);
        String starring = result.get(position).getStarring();
        holder.tv_sch_starring.setText("演员:"+starring);
        double score = result.get(position).getScore();
        holder.tv_sch_score.setText("评分:"+score);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView iv_sch_imageUrl;
        TextView tv_sch_name;
        TextView tv_sch_director;
        TextView tv_sch_starring;
        TextView tv_sch_score;
        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_sch_imageUrl = itemView.findViewById(R.id.iv_sch_imageUrl);
            tv_sch_name = itemView.findViewById(R.id.tv_sch_name);
            tv_sch_director = itemView.findViewById(R.id.tv_sch_director);
            tv_sch_starring = itemView.findViewById(R.id.tv_sch_starring);
            tv_sch_score = itemView.findViewById(R.id.tv_sch_score);
        }
    }
}
