package com.bawei.movie.bean.findHotMovieList;

import java.util.List;

public class HomeShow {
    private List<MovieResult> result ;

    private String message;

    private String status;

    public List<MovieResult> getResult() {
        return result;
    }

    public void setResult(List<MovieResult> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
