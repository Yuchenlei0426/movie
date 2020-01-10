package com.bw.movie.utils;

import com.bw.movie.bean.banner.BannerBean;
import com.bw.movie.bean.banner.BannerShow;
import com.bw.movie.bean.cinemadetails.CinemaDetails;
import com.bw.movie.bean.cinemas.CinemasInfoByDate;
import com.bw.movie.bean.datelist.DateList;
import com.bw.movie.bean.detail.DetailShow;
import com.bw.movie.bean.findCinemaByRegion.CinemaByRegion;
import com.bw.movie.bean.findCinemaByRegion.CinemaByRegionResult;
import com.bw.movie.bean.findComingSoonMovieList.FindComingSoonMovieList;
import com.bw.movie.bean.findHotMovieList.HomeShow;
import com.bw.movie.bean.findHotMovieList.MovieResult;
import com.bw.movie.bean.findMovieByKeyword.ByKeywordShow;
import com.bw.movie.bean.findReleaseMovieList.FindReleaseMovieList;
import com.bw.movie.bean.findregion.FindRegionResult;
import com.bw.movie.bean.findregion.FindRegionShow;
import com.bw.movie.bean.loginbean.LoginShow;
import com.bw.movie.bean.nearbycinemas.NearbyCinemas;
import com.bw.movie.bean.recommendcinemas.RecommendCinemasShow;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRequest {
    //    热门电影
    @GET("movie/v2/findHotMovieList")
    Observable<HomeShow<List<MovieResult>>> findHotMovieList(@Query("page") Integer page, @Query("count") Integer count);

    //    banner
    @GET("tool/v2/banner")
    Observable<HomeShow<List<BannerShow>>> banner();

    //    正在上映
    @GET("movie/v2/findReleaseMovieList")
    Observable<HomeShow<List<FindReleaseMovieList>>> findReleaseMovieList(@Query("page") Integer page, @Query("count") Integer count);

    //    即将上映
    @GET("movie/v2/findComingSoonMovieList")
    Observable<HomeShow<List<FindComingSoonMovieList>>> findComingSoonMovieList(@Query("page") Integer page, @Query("count") Integer count);

    //登录
    @POST("user/v2/login")
    @FormUrlEncoded
    Observable<LoginShow> login(@Field("email") String email, @Field("pwd") String pwd);

    //    注册
    @POST("user/v2/register")
    @FormUrlEncoded
    Observable<LoginShow> register(@Field("nickName") String nickName, @Field("pwd") String pwd, @Field("email") String email, @Field("code") String code);

    //    获取验证码
    @POST("user/v2/sendOutEmailCode")
    @FormUrlEncoded
    Observable<LoginShow> sendOutEmailCode(@Field("email") String email);

    //    详情
    @GET("movie/v2/findMoviesDetail")
    Observable<DetailShow> findMoviesDetail(@Query("movieId") Integer movieId);

    //查询区域列表
    @GET("tool/v2/findRegionList")
    Observable<HomeShow<List<FindRegionResult>>> findRegionList();

    //根据区域查询影院
    @GET("cinema/v2/findCinemaByRegion")
    Observable<HomeShow<List<CinemaByRegionResult>>> findCinemaByRegion(@Query("regionId") Integer regionId);


    //查询推荐影院信息
    @GET("cinema/v1/findRecommendCinemas")
    Observable<RecommendCinemasShow> findRecommendCinemas(@Query("page") Integer page, @Query("count") Integer count);


    //    查询附近影院
    @GET("cinema/v1/findNearbyCinemas")
    Observable<NearbyCinemas> findNearbyCinemas(@Query("longitude") double longitude, @Query("latitude") double latitude, @Query("page") Integer page, @Query("count") Integer count);

    @GET("movie/v2/findMovieByKeyword")
    Observable<ByKeywordShow> findMovieByKeyword(@Query("keyword") String keyword, @Query("page")Integer page, @Query("count") Integer count);
//日期
    @GET("tool/v2/findDateList")
    Observable<HomeShow<List<String>>> findDateList();
//    根据电影id，时间 查询播放影院信息
    @GET("movie/v2/findCinemasInfoByDate")
    Observable<HomeShow<List<CinemasInfoByDate>>> findCinemasInfoByDate(@Query("movieId")Integer movieId, @Query("date")String date, @Query("page")Integer page, @Query("count")Integer count);

//    影院详情
    @GET("cinema/v1/findCinemaInfo")
    Observable<HomeShow<CinemaDetails>> findCinemaInfo(@Query("cinemaId")Integer cinemaId);

}
