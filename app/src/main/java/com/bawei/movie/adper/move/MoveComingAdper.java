package com.bawei.movie.adper.move;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.movie.R;
import com.bawei.movie.bean.findComingSoonMovieList.FindComingSoonMovieList;
import com.facebook.drawee.view.SimpleDraweeView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoveComingAdper extends RecyclerView.Adapter<MoveComingAdper.MoveComingViewHolder> {

    List<FindComingSoonMovieList> result = new ArrayList<>();

    public void addAll(List<FindComingSoonMovieList> result) {
        this.result.addAll(result);
    }

    @NonNull
    @Override
    public MoveComingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.move_coming_item, viewGroup, false);
        return new MoveComingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoveComingViewHolder moveComingViewHolder, int i) {
//图片
        String imageUrl = result.get(i).getImageUrl();
        moveComingViewHolder.move_imageUrl.setImageURI(imageUrl);
        String name = result.get(i).getName();
        moveComingViewHolder.tv_move_com_name.setText(name);
//        上映时间
        Date date = new Date(result.get(i).getReleaseTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        moveComingViewHolder.tv_move_con_releaseTime.setText(dateFormat.format(date) + "上映");
//        想看人数
        int wantSeeNum = result.get(i).getWantSeeNum();
        moveComingViewHolder.tv_move_con_wantSeeNum.setText(wantSeeNum+"人想看");
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MoveComingViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView move_imageUrl;
        TextView tv_move_com_name;
        TextView tv_move_con_wantSeeNum;
        TextView tv_move_con_releaseTime;
        public MoveComingViewHolder(@NonNull View itemView) {
            super(itemView);
            move_imageUrl = itemView.findViewById(R.id.move_imageUrl);
            tv_move_com_name = itemView.findViewById(R.id.tv_move_com_name);
            tv_move_con_wantSeeNum = itemView.findViewById(R.id.tv_move_com_wantSeeNum);
            tv_move_con_releaseTime = itemView.findViewById(R.id.tv_move_con_releaseTime);
        }
    }
}
