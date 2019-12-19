package com.bw.movie.bean.findHotMovieList;
/**
 *@describe(描述)：热门电影
 *@data（日期）: 2019/12/6
 *@time（时间）: 19:51
 *@author（作者）: 于晨雷
 **/
public class MovieResult {
    private String director;

    private String horizontalImage;

    private String imageUrl;

    private int movieId;

    private String name;

    private double score;

    private String starring;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getHorizontalImage() {
        return horizontalImage;
    }

    public void setHorizontalImage(String horizontalImage) {
        this.horizontalImage = horizontalImage;
    }

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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }
}
