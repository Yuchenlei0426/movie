package com.bw.movie.prentent;

import com.bw.movie.base.BasePrentent;
import com.bw.movie.base.IBackCall;
import com.bw.movie.utils.IRequest;

import io.reactivex.Observable;

public class InfoByDatePreantent extends BasePrentent {
    public InfoByDatePreantent(IBackCall iBackCall) {
        super(iBackCall);
    }

    @Override
    protected Observable getMoudel(IRequest iRequest, Object... args) {
        return iRequest.findCinemasInfoByDate((Integer)args[0],(String)args[1],(Integer)args[2],(Integer)args[3]);
    }
}
