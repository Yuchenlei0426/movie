package com.bw.movie.prentent;

import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.detail.DetailShow;
import com.bw.movie.utils.IRequest;
import com.bw.movie.utils.WorkUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RecommendPreantent  {
    IBackCall iBackCall;

    public RecommendPreantent(IBackCall iBackCall) {
        this.iBackCall = iBackCall;
    }

    public void getData(Object...args){
        IRequest iRequest = WorkUtil.getInstance().create(IRequest.class);
        iRequest.findMoviesDetail((Integer) args[0])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailShow homeShow) {
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
