package com.bawei.movie.bean.findComingSoonMovieList;

import java.util.List;

public class ComingSoonShow {
    private List<FindComingSoonMovieList> result ;

    private String message;

    private String status;

    public List<FindComingSoonMovieList> getResult() {
        return result;
    }

    public void setResult(List<FindComingSoonMovieList> result) {
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
