package com.bw.movie.bean.findReleaseMovieList;

import java.util.List;

public class ReleaseShow {
    private List<FindReleaseMovieList>result ;

    private String message;

    private String status;

    public List<FindReleaseMovieList> getResult() {
        return result;
    }

    public void setResult(List<FindReleaseMovieList> result) {
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
