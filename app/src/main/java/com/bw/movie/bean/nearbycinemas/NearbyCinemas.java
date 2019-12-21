package com.bw.movie.bean.nearbycinemas;

import java.util.List;

public class NearbyCinemas {
    private List<NearbyCinemasResult> result ;

    private String message;

    private String status;

    public List<NearbyCinemasResult> getResult() {
        return result;
    }

    public void setResult(List<NearbyCinemasResult> result) {
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
