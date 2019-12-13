package com.bawei.movie.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onLayout());
        onView();
        onData();

    }

    protected abstract int onLayout();

    protected abstract void onView();

    protected abstract void onData();
}
