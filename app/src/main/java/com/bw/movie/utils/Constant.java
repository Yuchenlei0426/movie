package com.bw.movie.utils;

import android.os.Environment;

/**
 *@describe(描述)：Constant
 *@data（日期）: 2019/11/13
 *@time（时间）: 19:17
 *@author（作者）: xin
 **/
public class Constant {
    public final static String APP_ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + App.getInstance().getPackageName();
    public final static String DOWNLOAD_DIR = "/downlaod/";


}
