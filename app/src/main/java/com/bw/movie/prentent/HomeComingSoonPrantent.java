package com.bw.movie.prentent;



import com.bw.movie.base.BasePrentent;
import com.bw.movie.base.IBackCall;
import com.bw.movie.utils.IRequest;
import io.reactivex.Observable;

public class HomeComingSoonPrantent extends BasePrentent {
    public HomeComingSoonPrantent(IBackCall iBackCall) {
        super(iBackCall);
    }

    @Override
    protected Observable getMoudel(IRequest iRequest, Object... args) {
        return iRequest.findComingSoonMovieList((Integer)args[0],(Integer)args[1]);
    }
}
