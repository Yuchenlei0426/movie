package com.bw.movie.bean.cinemas;
/**
 *@describe(描述)：影院日期下的排期
 *@data（日期）: 2020/1/9
 *@time（时间）: 14:55
 *@author（作者）: 于晨雷
 **/
public class CinemasInfoByDate {
    private String address;

    private int cinemaId;

    private String logo;

    private String name;

    private double price;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
