package com.bw.movie.bean.datelist;

import java.util.List;
/**
 *@describe(描述)：一周的日期
 *@data（日期）: 2020/1/9
 *@time（时间）: 14:56
 *@author（作者）: 于晨雷
 **/
public class DateList {
    private List<String> result ;

    private String message;

    private String status;

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
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
