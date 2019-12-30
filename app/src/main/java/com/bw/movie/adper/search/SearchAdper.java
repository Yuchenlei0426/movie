package com.bw.movie.adper.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.findMovieByKeyword.ByKeywordResult;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class SearchAdper extends RecyclerView.Adapter<SearchAdper.SearchViewHolder> {
    private List<ByKeywordResult> result;
    public void addAll(List<ByKeywordResult> result){
        this.result=result;
    }
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        String imageUrl = result.get(position).getImageUrl();
        holder.sv_imageUrl.setImageURI(imageUrl);
        String name = result.get(position).getName();
        holder.search_name.setText(name);
        String director = result.get(position).getDirector();
        holder.search_director.setText("导演:"+director);
        String starring = result.get(position).getStarring();
        holder.search_starring.setText("演员:"+starring);
        double score = result.get(position).getScore();
        holder.search_score.setText("评分:"+score);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView sv_imageUrl;
        TextView search_name,search_director,search_starring,search_score;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            sv_imageUrl = itemView.findViewById(R.id.sv_imageUrl);
            search_name = itemView.findViewById(R.id.search_name);
            search_director= itemView.findViewById(R.id.search_director);
            search_starring= itemView.findViewById(R.id.search_starring);
            search_score = itemView.findViewById(R.id.search_score);
        }
    }
}
