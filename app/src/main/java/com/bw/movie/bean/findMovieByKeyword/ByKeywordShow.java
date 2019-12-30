package com.bw.movie.bean.findMovieByKeyword;

import java.util.List;

public class ByKeywordShow {
    private List<ByKeywordResult> result ;

    private String message;

    private String status;

    public List<ByKeywordResult> getResult() {
        return result;
    }

    public void setResult(List<ByKeywordResult> result) {
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
