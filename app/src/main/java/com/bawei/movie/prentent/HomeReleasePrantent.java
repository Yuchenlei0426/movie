package com.bawei.movie.prentent;

import com.bawei.movie.base.IBackCall;
import com.bawei.movie.bean.findReleaseMovieList.ReleaseShow;
import com.bawei.movie.utils.IRequest;
import com.bawei.movie.utils.WorkUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeReleasePrantent {
    IBackCall iBackCall;

    public HomeReleasePrantent(IBackCall iBackCall) {
        this.iBackCall = iBackCall;
    }

    public void getData(Object...args){
        IRequest iRequest = WorkUtil.getInstance().create(IRequest.class);
        iRequest.findReleaseMovieList((Integer)args[0],(Integer)args[1])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReleaseShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReleaseShow homeShow) {
                        String status = homeShow.getStatus();
                        if (status.equals("0000")) {
                            iBackCall.onSuccess(homeShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


//    iRequest.findReleaseMovieList((Integer)args[0],(Integer)args[1])
}
