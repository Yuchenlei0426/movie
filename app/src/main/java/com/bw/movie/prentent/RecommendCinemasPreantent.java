package com.bw.movie.prentent;

import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.recommendcinemas.RecommendCinemasShow;
import com.bw.movie.utils.IRequest;
import com.bw.movie.utils.WorkUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RecommendCinemasPreantent {
    IBackCall iBackCall;

    public RecommendCinemasPreantent(IBackCall iBackCall) {
        this.iBackCall = iBackCall;
    }

    public void getData(Object...args){
        IRequest iRequest = WorkUtil.getInstance().create(IRequest.class);
        iRequest.findRecommendCinemas((Integer)args[0],(Integer)args[1])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecommendCinemasShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RecommendCinemasShow homeShow) {
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
