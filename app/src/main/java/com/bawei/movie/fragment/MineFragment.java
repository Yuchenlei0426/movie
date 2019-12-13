package com.bawei.movie.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.movie.activtiy.LoginActivity;
import com.bawei.movie.R;
import com.bawei.movie.base.BaseFragment;
import com.bawei.movie.bean.eventbean.EvenentBean;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MineFragment extends BaseFragment {


    @BindView(R.id.iv_headPic)
    ImageView ivHeadPic;
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
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onData() {
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.POSTING)
    public void Show(EvenentBean bean){
        String nickName = bean.getNickName();
        tvNickName.setText(nickName);
        Glide.with(getActivity()).load(bean.getHeadPic()).into(ivHeadPic);
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
        EventBus.getDefault().unregister(this);
    }
}
