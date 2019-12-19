package com.bw.movie.base;

public interface IBackCall<T> {
    void onSuccess(T homeShow);
    void onFail(String mes);
}
