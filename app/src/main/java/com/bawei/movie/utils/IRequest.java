package com.bawei.movie.utils;

import com.bawei.movie.bean.findHotMovieList.HomeShow;
import com.bawei.movie.bean.banner.BannerBean;
import com.bawei.movie.bean.findComingSoonMovieList.ComingSoonShow;
import com.bawei.movie.bean.findReleaseMovieList.ReleaseShow;
import com.bawei.movie.bean.loginbean.LoginShow;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRequest {
    //    热门电影
    @GET("movie/v2/findHotMovieList")
    Observable<HomeShow> findHotMovieList(@Query("page") Integer page, @Query("count") Integer count);

    //    banner
    @GET("tool/v2/banner")
    Observable<BannerBean> banner();

    //    正在热映
    @GET("movie/v2/findReleaseMovieList")
    Observable<ReleaseShow> findReleaseMovieList(@Query("page") Integer page, @Query("count") Integer count);

    //    即将上映
    @GET("movie/v2/findComingSoonMovieList")
    Observable<ComingSoonShow> findComingSoonMovieList(@Query("page") Integer page, @Query("count") Integer count);
//Login
    @POST("user/v2/login")
    @FormUrlEncoded
    Observable<LoginShow> login(@Field("email") String email,@Field("pwd") String pwd);
    @POST("user/v2/register")
    @FormUrlEncoded
    Observable<LoginShow> register(@Field("nickName") String nickName,@Field("pwd") String pwd,@Field("email") String email,@Field("code") String code);
    @POST("user/v2/sendOutEmailCode")
    @FormUrlEncoded
    Observable<LoginShow>  sendOutEmailCode(@Field("email") String email);
}
