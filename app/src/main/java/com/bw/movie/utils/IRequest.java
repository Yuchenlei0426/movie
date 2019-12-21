package com.bw.movie.utils;

import com.bw.movie.bean.banner.BannerBean;
import com.bw.movie.bean.detail.DetailShow;
import com.bw.movie.bean.findComingSoonMovieList.ComingSoonShow;
import com.bw.movie.bean.findHotMovieList.HomeShow;
import com.bw.movie.bean.findReleaseMovieList.ReleaseShow;
import com.bw.movie.bean.findregion.FindRegionShow;
import com.bw.movie.bean.loginbean.LoginShow;
import com.bw.movie.bean.nearbycinemas.NearbyCinemas;
import com.bw.movie.bean.recommendcinemas.RecommendCinemasShow;

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
//登录
    @POST("user/v2/login")
    @FormUrlEncoded
    Observable<LoginShow> login(@Field("email") String email,@Field("pwd") String pwd);
//    注册
    @POST("user/v2/register")
    @FormUrlEncoded
    Observable<LoginShow> register(@Field("nickName") String nickName,@Field("pwd") String pwd,@Field("email") String email,@Field("code") String code);
//    获取验证码
    @POST("user/v2/sendOutEmailCode")
    @FormUrlEncoded
    Observable<LoginShow>  sendOutEmailCode(@Field("email") String email);

//    详情
    @GET("movie/v2/findMoviesDetail")
    Observable<DetailShow> findMoviesDetail(@Query("movieId") Integer movieId);

//查询区域列表
    @GET("tool/v2/findRegionList")
    Observable<FindRegionShow> findRegionList();

//查询推荐影院信息
    @GET("cinema/v1/findRecommendCinemas")
    Observable<RecommendCinemasShow> findRecommendCinemas(@Query("page")Integer page, @Query("count") Integer count);

    @GET("cinema/v1/findNearbyCinemas")
    Observable<NearbyCinemas> findNearbyCinemas(@Query("longitude")String longitude,@Query("latitude") String latitude,@Query("page")Integer page,@Query("count") Integer count);
}
