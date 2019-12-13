package com.bawei.movie.adper;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawei.movie.R;
import com.bawei.movie.bean.banner.BannerShow;
import com.bawei.movie.bean.findComingSoonMovieList.FindComingSoonMovieList;
import com.bawei.movie.bean.findHotMovieList.MovieResult;
import com.bawei.movie.bean.findReleaseMovieList.FindReleaseMovieList;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class HomeView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //    类型
//    banner类型
    private final int TYPE_BANNER = 0;
    //    热映
    private final int TYPE_HOT = 1;
    //    即将
    private final int TYPE_COMING_SOON = 2;
    //    热门
    private final int TYPE_RELEASE = 3;

    //传集合
//    banner
    List<BannerShow> bannerShows = new ArrayList<>();


    public void addAll(List<BannerShow> bannerShows) {
        this.bannerShows.addAll(bannerShows);
    }

    //热映
    List<MovieResult> hotResult = new ArrayList<>();

    public void hotAddAll(List<MovieResult> hotResult) {
        this.hotResult.addAll(hotResult);
    }

    //即将
    List<FindComingSoonMovieList> comingSoonResult = new ArrayList<>();

    public void ComingSoonAddAll(List<FindComingSoonMovieList> comingSoonResult) {
        this.comingSoonResult.addAll(comingSoonResult);
    }

    //热门
    List<FindReleaseMovieList> releaseResult = new ArrayList<>();

    public void releaseAddAll(List<FindReleaseMovieList> releaseResult) {
        this.releaseResult.addAll(releaseResult);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = null;
        RecyclerView.ViewHolder viewHolder = null;
//        引入布局

//        banner
        if (type == TYPE_BANNER) {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.banner_item, viewGroup, false);
            viewHolder = new BannerViewHolder(itemView);
        }
//        热映
        if (type == TYPE_HOT) {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.release1_item, viewGroup, false);
            viewHolder = new HotItemViewHolder(itemView);
        }
//        即将
        if (type == TYPE_COMING_SOON) {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.coming_soon_item, viewGroup, false);
            viewHolder = new ComingSoonItemViewHolder(itemView);
        }
//        热门
        if (type == TYPE_RELEASE) {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hot_item, viewGroup, false);
            viewHolder = new ReleaseViewHolder1(itemView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder releaseViewHolder, int i) {
        int type = getItemViewType(i);
        if (type == TYPE_BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) releaseViewHolder;
            //banner
            bannerViewHolder.x_banner.setData(bannerShows, null);
            bannerViewHolder.x_banner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, View view, int position) {
                    Glide.with(view.getContext())
                            .load(bannerShows
                                    .get(position)
                                    .getImageUrl()).
                            into((ImageView) view);
                }
            });
            bannerViewHolder.x_banner.setmAutoPalyTime(3000);
            bannerViewHolder.x_banner.startAutoPlay();
        }

//        热映
        if (type == TYPE_HOT) {
            HotItemViewHolder hotItemViewHolder = (HotItemViewHolder) releaseViewHolder;
            hotItemViewHolder.hotItemAdper.addAll(hotResult);

        }
//        即将
        if (type == TYPE_COMING_SOON) {
            ComingSoonItemViewHolder comingSoonItemViewHolder = (ComingSoonItemViewHolder) releaseViewHolder;
            comingSoonItemViewHolder.comingSoonAdper.addAll(comingSoonResult);
        }
//        热门
        if (type == TYPE_RELEASE) {
            ReleaseViewHolder1 releaseViewHolder1 = (ReleaseViewHolder1) releaseViewHolder;
            releaseViewHolder1.releaseAdper.addAll(releaseResult);
            String imageUrl = releaseResult.get(i).getImageUrl();
            releaseViewHolder1.iv_pic.setImageURI(imageUrl);
        }
    }

    @Override
    public int getItemCount() {
        int flag = 0;
        if (bannerShows.size() > 0) {
            flag++;
        }
        if (hotResult.size() > 0) {
            flag++;
        }

        if (comingSoonResult.size() > 0) {
            flag++;
        }
        if (releaseResult.size() > 0) {
            flag++;
        }
        return flag;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        }
        if (position == 1) {
            return TYPE_HOT;
        }
        if (position == 2) {
            return TYPE_COMING_SOON;
        }
        if (position == 3) {
            return TYPE_RELEASE;
        }
        return super.getItemViewType(position);
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        XBanner x_banner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            x_banner = itemView.findViewById(R.id.x_banner);
        }
    }

    class HotItemViewHolder extends RecyclerView.ViewHolder {
        HotItemAdper hotItemAdper;

        public HotItemViewHolder(@NonNull View itemView) {
            super(itemView);
            RecyclerView rv_hot = itemView.findViewById(R.id.rv_hot);
            hotItemAdper = new HotItemAdper();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv_hot.setLayoutManager(linearLayoutManager);
            rv_hot.setAdapter(hotItemAdper);
            notifyDataSetChanged();
        }
    }

    class ComingSoonItemViewHolder extends RecyclerView.ViewHolder {
        ComingSoonAdper comingSoonAdper;

        public ComingSoonItemViewHolder(@NonNull View itemView) {
            super(itemView);
            RecyclerView rv_coming_soon = itemView.findViewById(R.id.rv_coming_soon);
            comingSoonAdper = new ComingSoonAdper();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false);
            rv_coming_soon.setLayoutManager(linearLayoutManager);
            rv_coming_soon.setAdapter(comingSoonAdper);
            notifyDataSetChanged();
        }
    }

    class ReleaseViewHolder1 extends RecyclerView.ViewHolder {
        ReleaseAdper releaseAdper;
        SimpleDraweeView iv_pic;

        public ReleaseViewHolder1(@NonNull View itemView) {
            super(itemView);
            RecyclerView rv_release = itemView.findViewById(R.id.rv_release);
            iv_pic = itemView.findViewById(R.id.iv_pic);
            //热门
            releaseAdper = new ReleaseAdper();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv_release.setLayoutManager(linearLayoutManager);
            rv_release.setAdapter(releaseAdper);
            notifyDataSetChanged();
        }
    }
}
