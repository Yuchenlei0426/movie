package com.bw.movie.bean.recommendcinemas;

import java.util.List;

public class RecommendCinemasShow {
    private List<RecommendResult> result ;

    private String message;

    private String status;

    public List<RecommendResult> getResult() {
        return result;
    }

    public void setResult(List<RecommendResult> result) {
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
