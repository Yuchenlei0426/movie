package com.bw.movie.prentent;

import android.util.Log;

import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.findComingSoonMovieList.ComingSoonShow;
import com.bw.movie.utils.IRequest;
import com.bw.movie.utils.WorkUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeComingSoonPrantent{
    IBackCall iBackCall;

    public HomeComingSoonPrantent(IBackCall iBackCall) {
        this.iBackCall = iBackCall;
    }

    public void getData(Object...args){
        IRequest iRequest = WorkUtil.getInstance().create(IRequest.class);
        iRequest.findComingSoonMovieList((Integer)args[0],(Integer)args[1])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ComingSoonShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ComingSoonShow homeShow) {
                        String status = homeShow.getStatus();
                        Log.d("yclmovie",homeShow.getMessage());
                        if (status.equals("0000")) {
                            iBackCall.onSuccess(homeShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iBackCall.onFail(e.getMessage());
                        Log.d("yclmovie",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


//    iRequest.findComingSoonMovieList((Integer)args[0],(Integer)args[1])
}
