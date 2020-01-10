package com.bw.movie.prentent.cinemadetails;

import com.bw.movie.base.BasePrentent;
import com.bw.movie.base.IBackCall;
import com.bw.movie.utils.IRequest;

import io.reactivex.Observable;

public class CinemaDetailPreantent extends BasePrentent {
    public CinemaDetailPreantent(IBackCall iBackCall) {
        super(iBackCall);
    }

    @Override
    protected Observable getMoudel(IRequest iRequest, Object... args) {
        return iRequest.findCinemaInfo((Integer)args[0]);
    }
}
