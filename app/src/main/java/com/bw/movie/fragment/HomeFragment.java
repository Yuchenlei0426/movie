package com.bw.movie.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Movie;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.activtiy.move.MoreActivity;
import com.bw.movie.activtiy.search.SearchActivity;
import com.bw.movie.adper.ComingSoonAdper;
import com.bw.movie.adper.HotItemAdper;
import com.bw.movie.adper.ReleaseAdper;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.IBackCall;

import com.bw.movie.bean.banner.BannerShow;
import com.bw.movie.bean.eventbean.District;
import com.bw.movie.bean.findComingSoonMovieList.FindComingSoonMovieList;
import com.bw.movie.bean.findHotMovieList.HomeShow;
import com.bw.movie.bean.findHotMovieList.MovieResult;
import com.bw.movie.bean.findReleaseMovieList.FindReleaseMovieList;
import com.bw.movie.prentent.HomeBannerPrentent;
import com.bw.movie.prentent.HomeComingSoonPrantent;
import com.bw.movie.prentent.HomeHotPrentent;
import com.bw.movie.prentent.HomeReleasePrantent;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private int GPS_REQUEST_CODE = 10;
    Unbinder unbinder;
    @BindView(R.id.x_banner)
    XBanner xBanner;
    @BindView(R.id.iv_xin)
    ImageView ivXin;
    @BindView(R.id.re_move1)
    TextView reMove1;
    @BindView(R.id.rv_hot)
    RecyclerView rvHot;
    @BindView(R.id.iv_hotre)
    ImageView ivHotre;
    @BindView(R.id.re_move3)
    TextView reMove3;
    @BindView(R.id.iv_pic)
    SimpleDraweeView ivPic;
    @BindView(R.id.rv_release)
    RecyclerView rvRelease;
    @BindView(R.id.iv_hot)
    ImageView ivHot;
    @BindView(R.id.re_move2)
    TextView reMove2;
    @BindView(R.id.rv_coming_soon)
    RecyclerView rvComingSoon;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.iv_Positioning)
    ImageView ivPositioning;
    @BindView(R.id.tv_city)
    TextView tvCity;

    @Override
    protected int onLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void onData() {
        //banner
        HomeBannerPrentent homeBannerPrentent = new HomeBannerPrentent(new BannerCall());
        homeBannerPrentent.getData();
        //热门电影
        HomeHotPrentent homePrentent = new HomeHotPrentent(new HomehotCall());
        homePrentent.getData(1, 5);

        //    即将上映
        HomeComingSoonPrantent homeComingSoonPrantent = new HomeComingSoonPrantent(new ComingSoonCall());
        homeComingSoonPrantent.getData(1, 5);
        //    正在上映
        HomeReleasePrantent homeReleasePrantent = new HomeReleasePrantent(new ReleaseCall());
        homeReleasePrantent.getData(1, 5);

    }

    @OnClick({R.id.re_move1, R.id.re_move2, R.id.re_move3, R.id.iv_search, R.id.iv_Positioning})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.re_move1:
                Intent intent1 = new Intent(getActivity(), MoreActivity.class);
//                intent1.putExtra("fragment_flag", 1);
                startActivity(intent1);
                break;
            case R.id.re_move2:
                Intent intent2 = new Intent(getActivity(), MoreActivity.class);
//                intent2.putExtra("fragment_flag", 2);
                startActivity(intent2);
                ;
                break;
            case R.id.re_move3:
                Intent intent3 = new Intent(getActivity(), MoreActivity.class);
//                intent3.putExtra("fragment_flag", 3);
                startActivity(intent3);
                break;
            case R.id.iv_search:
                Intent intent4 = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent4);
                break;
            case R.id.iv_Positioning:
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
                    //开启定位权限,200是标识码
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
                } else {
                    MyLocation(getActivity());//开始定位
                }
                break;

        }
    }

    //banner
    private class BannerCall implements IBackCall<HomeShow<List<BannerShow>>> {


        @Override
        public void onSuccess(HomeShow<List<BannerShow>> homeShow) {
            List<BannerShow> result = homeShow.getResult();
            xBanner.setData(result, null);
            xBanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, View view, int position) {
                    Glide.with(view.getContext()).load(result.get(position).getImageUrl()).into((ImageView) view);
                }
            });
        }

        @Override
        public void onFail(String mes) {
            Log.e(TAG, "onFail: " + mes);

        }
    }


    //正在热映
    private class HomehotCall implements IBackCall<HomeShow<List<MovieResult>>> {


        @Override
        public void onSuccess(HomeShow<List<MovieResult>> homeShow) {
            List<MovieResult> result = homeShow.getResult();
            String imageUrl = result.get(0).getHorizontalImage();
            ivPic.setImageURI(imageUrl);
            HotItemAdper hotItemAdper = new HotItemAdper();
            hotItemAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

            rvHot.setLayoutManager(linearLayoutManager);
            rvHot.setAdapter(hotItemAdper);

        }

        @Override
        public void onFail(String mes) {

        }
    }

    //    即将上映
    private class ComingSoonCall implements IBackCall<HomeShow<List<FindComingSoonMovieList>>> {


        @Override
        public void onSuccess(HomeShow<List<FindComingSoonMovieList>> homeShow) {
            Log.d("yclmovie", homeShow.getMessage() + "");
            List<FindComingSoonMovieList> comingSoonResult = homeShow.getResult();
            ComingSoonAdper comingSoonAdper = new ComingSoonAdper();
            comingSoonAdper.addAll(comingSoonResult);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            rvComingSoon.setLayoutManager(linearLayoutManager);
            rvComingSoon.setAdapter(comingSoonAdper);

        }

        @Override
        public void onFail(String mes) {
            Log.d("yclmovie", mes);
        }
    }

//    正在上映
    private class ReleaseCall implements IBackCall<HomeShow<List<FindReleaseMovieList>>> {


        @Override
        public void onSuccess(HomeShow<List<FindReleaseMovieList>> homeShow) {
            List<FindReleaseMovieList> result = homeShow.getResult();
            Log.e(TAG, "onSuccess: " + homeShow.getMessage());
            ReleaseAdper releaseAdper = new ReleaseAdper();
            releaseAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            rvRelease.setLayoutManager(linearLayoutManager);
            rvRelease.setAdapter(releaseAdper);

        }

        @Override
        public void onFail(String mes) {
            Log.e(TAG, "onFail: " + mes);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200://刚才的识别码
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
                    MyLocation(getActivity());//开始定位
                } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                    Toast.makeText(getActivity(), "未开启定位权限,请手动到设置去开启权限", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }


    public void MyLocation(Context context) {
        mlocationClient = new AMapLocationClient(context);
        mLocationOption = new AMapLocationClientOption();
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                try {
                    if (amapLocation != null) {
                        if (amapLocation.getErrorCode() == 0) {
                            //定位成功回调信息，设置相关消息

                            String city = amapLocation.getCity();
                            String district = amapLocation.getDistrict();
                            EventBus.getDefault().postSticky(new District(district));
                            //获取当前定位结果来源，如网络定位结果，详见定位类型表
//                            Log.e("定位类型", amapLocation.getLocationType() + "");
//                            Log.e("获取精度信息", amapLocation.getAccuracy() + "");
//                            //如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                            Log.e("地址", amapLocation.getAddress());
//                            Log.e("国家信息", amapLocation.getCountry());
//                            Log.e("省信息", amapLocation.getProvince());
//                            Log.e("城市信息",city );
//                            Log.e("城区信息", amapLocation.getDistrict());
//                            Log.e("街道信息", amapLocation.getStreet());
//                            Log.e("街道门牌号信息", amapLocation.getStreetNum());
//                            Log.e("城市编码", amapLocation.getCityCode());
//                            Log.e("地区编码", amapLocation.getAdCode());
//                            Log.e("获取当前定位点的AOI信息", amapLocation.getAoiName());
//                            Log.e("获取当前室内定位的建筑物Id", amapLocation.getBuildingId());
//                            Log.e("获取当前室内定位的楼层", amapLocation.getFloor());
//                            Log.e("获取GPS的当前状态", amapLocation.getGpsAccuracyStatus() + "");
                            //获取定位时间
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(amapLocation.getTime());
                            Log.i("获取定位时间", df.format(date));
                            String s = amapLocation.getStreet() + " " + amapLocation.getStreetNum();
                            tvCity.setText(city);
//                            edit.putString("district", district).commit();
                            // 停止定位
                            mlocationClient.stopLocation();
                        } else {
                            //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                            Log.e("AmapError", "location Error, ErrCode:"
                                    + amapLocation.getErrorCode() + ", errInfo:"
                                    + amapLocation.getErrorInfo());
                            Toast.makeText(getActivity(), "没有权限，请打开权限...", Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("定位服务未开启")
                                    .setMessage("请在系统设置中开启定位服务\n" +
                                            "以便为您提供更好的服务")
                                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                            startActivityForResult(intent, GPS_REQUEST_CODE);
                                        }
                                    })
                                    .show();
                        }
                    }
                } catch (Exception e) {
                }

            }
        });
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(5000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        //启动定位
        mlocationClient.startLocation();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);

        } else {
            MyLocation(getActivity());//开始定位
            //Toast.makeText(getActivity(),"已开启定位权限",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // 停止定位
        if (null != mlocationClient) {
            mlocationClient.stopLocation();
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
