package com.bw.movie.utils;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {
    public static Context context;
//    public static UserInfoDao userInfoDao;
private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        Fresco.initialize(context);
        MultiDex.install(this);
//        DaoSession daoSession = DaoMaster.newDevSession(this, UserInfoDao.TABLENAME);
//        userInfoDao = daoSession.getUserInfoDao();
    }
    public static Context getInstance() {
        return sInstance;
    }
}
