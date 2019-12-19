package com.bw.movie.prentent;

import com.bw.movie.base.BasePrentent;
import com.bw.movie.base.IBackCall;
import com.bw.movie.utils.IRequest;

import io.reactivex.Observable;

public class HomeHotPrentent extends BasePrentent {
    public HomeHotPrentent(IBackCall iBackCall) {
        super(iBackCall);
    }

    @Override
    protected Observable getMoudel(IRequest iRequest, Object... args) {
        return iRequest.findHotMovieList((Integer) args[0], (Integer) args[1]);
    }
}
