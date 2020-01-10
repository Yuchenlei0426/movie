package com.bw.movie.prentent;

import com.bw.movie.base.BasePrentent;
import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.banner.BannerBean;
import com.bw.movie.bean.findHotMovieList.HomeShow;
import com.bw.movie.bean.findregion.FindRegionShow;
import com.bw.movie.bean.loginbean.LoginShow;
import com.bw.movie.utils.IRequest;
import com.bw.movie.utils.WorkUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeBannerPrentent extends BasePrentent {


    public HomeBannerPrentent(IBackCall iBackCall) {
        super(iBackCall);
    }

    @Override
    protected Observable getMoudel(IRequest iRequest, Object... args) {
        return iRequest.banner();
    }
}
