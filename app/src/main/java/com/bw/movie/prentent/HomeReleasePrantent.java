package com.bw.movie.prentent;

import com.bw.movie.base.BasePrentent;
import com.bw.movie.base.IBackCall;
import com.bw.movie.utils.IRequest;


import io.reactivex.Observable;


public class HomeReleasePrantent extends BasePrentent {


    public HomeReleasePrantent(IBackCall iBackCall) {
        super(iBackCall);
    }

    @Override
    protected Observable getMoudel(IRequest iRequest, Object... args) {
        return iRequest.findReleaseMovieList((Integer)args[0],(Integer)args[1]);
    }
}
