package com.bw.movie.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activtiy.LoginActivity;
import com.bw.movie.base.BaseFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MineFragment extends BaseFragment {


    @BindView(R.id.iv_headPic)
    SimpleDraweeView ivHeadPic;
    @BindView(R.id.tv_nickName)
    TextView tvNickName;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    Unbinder unbinder;

    @Override
    protected int onLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void onView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void onData() {

    }

    @OnClick(R.id.ll_login)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }
}
