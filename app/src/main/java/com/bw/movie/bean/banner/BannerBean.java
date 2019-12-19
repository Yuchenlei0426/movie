package com.bw.movie.bean.banner;

import java.util.List;

public class BannerBean {
    private List<BannerShow> result ;

    private String message;

    private String status;

    public List<BannerShow> getResult() {
        return result;
    }

    public void setResult(List<BannerShow> result) {
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
