package com.bw.movie.bean.detail;

import com.bw.movie.bean.detail.show.MovieActor;
import com.bw.movie.bean.detail.show.MovieDirector;
import com.bw.movie.bean.detail.show.ShortFilmList;

import java.util.List;

public class DetailResult {
    private int commentNum;

    private String duration;

    private String imageUrl;

    private List<MovieActor> movieActor ;

    private List<MovieDirector> movieDirector ;

    private int movieId;

    private String movieType;

    private String name;

    private String placeOrigin;

    private List<String> posterList ;

    private Long releaseTime;

    private double score;

    private List<ShortFilmList> shortFilmList ;

    private String summary;

    private int whetherFollow;

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<MovieActor> getMovieActor() {
        return movieActor;
    }

    public void setMovieActor(List<MovieActor> movieActor) {
        this.movieActor = movieActor;
    }

    public List<MovieDirector> getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(List<MovieDirector> movieDirector) {
        this.movieDirector = movieDirector;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceOrigin() {
        return placeOrigin;
    }

    public void setPlaceOrigin(String placeOrigin) {
        this.placeOrigin = placeOrigin;
    }

    public List<String> getPosterList() {
        return posterList;
    }

    public void setPosterList(List<String> posterList) {
        this.posterList = posterList;
    }

    public Long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<ShortFilmList> getShortFilmList() {
        return shortFilmList;
    }

    public void setShortFilmList(List<ShortFilmList> shortFilmList) {
        this.shortFilmList = shortFilmList;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getWhetherFollow() {
        return whetherFollow;
    }

    public void setWhetherFollow(int whetherFollow) {
        this.whetherFollow = whetherFollow;
    }
}
