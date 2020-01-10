package com.bw.movie.prentent.area;

import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.findCinemaByRegion.CinemaByRegion;
import com.bw.movie.bean.findHotMovieList.HomeShow;
import com.bw.movie.utils.IRequest;
import com.bw.movie.utils.WorkUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AreaPrantent {
    IBackCall iBackCall;

    public AreaPrantent(IBackCall iBackCall) {
        this.iBackCall = iBackCall;
    }

    public void getData(Object...args){
        IRequest iRequest = WorkUtil.getInstance().create(IRequest.class);
        iRequest.findCinemaByRegion((Integer)args[0])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeShow homeShow) {
                        String status = homeShow.getStatus();
                        if (status.equals("0000")) {
                            iBackCall.onSuccess(homeShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iBackCall.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
