package com.bw.movie.bean.findComingSoonMovieList;
/**
 *@describe(描述)：即将上映
 *@data（日期）: 2019/12/6
 *@time（时间）: 19:50
 *@author（作者）: 于晨雷
 **/
public class FindComingSoonMovieList {

    private String imageUrl;

    private int movieId;

    private String name;

    private long releaseTime;

    private int wantSeeNum;

    private int whetherReserve;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getWantSeeNum() {
        return wantSeeNum;
    }

    public void setWantSeeNum(int wantSeeNum) {
        this.wantSeeNum = wantSeeNum;
    }

    public int getWhetherReserve() {
        return whetherReserve;
    }

    public void setWhetherReserve(int whetherReserve) {
        this.whetherReserve = whetherReserve;
    }
}
