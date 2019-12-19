package com.bw.movie.activtiy;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bw.movie.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private int recLen = 3;

    @BindView(R.id.tv_introduction)
    TextView tvIntroduction;
    Timer timer = new Timer();
    private Runnable runnable;
    private Handler handler;
    private boolean farg =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        getWindow().setFlags(flag, flag);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        timer.schedule(task, 1000, 1000);//等待时间一秒，停顿时间一秒
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                if (farg) {
                    //从闪屏界面跳转到首界面
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 5000);


    }
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(runnable=new Runnable() { // UI thread
                @Override
                public void run() {
                    recLen--;
                    tvIntroduction.setText("跳过 " + recLen);
                    if (recLen < 0) {
                        timer.cancel();
                        tvIntroduction.setVisibility(View.GONE);//倒计时到0隐藏字体

                    }
                }
            });
        }
    };

    @OnClick(R.id.tv_introduction)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_introduction :
                farg=false;
                //从闪屏界面跳转到首界面
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
                handler.removeCallbacks(runnable);

                ;break;
        }
    }
}
