package com.bawei.movie.prentent;

import com.bawei.movie.base.IBackCall;
import com.bawei.movie.bean.loginbean.LoginShow;
import com.bawei.movie.utils.IRequest;
import com.bawei.movie.utils.WorkUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CodePrentent {
    IBackCall iBackCall;

    public CodePrentent(IBackCall iBackCall) {
        this.iBackCall = iBackCall;
    }

    public void getData(Object...args){
        IRequest iRequest = WorkUtil.getInstance().create(IRequest.class);
        iRequest.sendOutEmailCode((String)args[0])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginShow homeShow) {
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
}
