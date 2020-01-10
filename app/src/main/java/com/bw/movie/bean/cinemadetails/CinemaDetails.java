package com.bw.movie.bean.cinemadetails;
/**
 *@describe(描述)：影院详情
 *@data（日期）: 2020/1/9
 *@time（时间）: 14:55
 *@author（作者）: 于晨雷
 **/
public class CinemaDetails {
    private String address;

    private String businessHoursContent;

    private int commentTotal;

    private int distance;

    private int followCinema;

    private int id;

    private String label;

    private String logo;

    private String name;

    private String phone;

    private String vehicleRoute;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessHoursContent() {
        return businessHoursContent;
    }

    public void setBusinessHoursContent(String businessHoursContent) {
        this.businessHoursContent = businessHoursContent;
    }

    public int getCommentTotal() {
        return commentTotal;
    }

    public void setCommentTotal(int commentTotal) {
        this.commentTotal = commentTotal;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getFollowCinema() {
        return followCinema;
    }

    public void setFollowCinema(int followCinema) {
        this.followCinema = followCinema;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVehicleRoute() {
        return vehicleRoute;
    }

    public void setVehicleRoute(String vehicleRoute) {
        this.vehicleRoute = vehicleRoute;
    }
}
