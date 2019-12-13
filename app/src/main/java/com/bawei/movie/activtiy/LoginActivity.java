package com.bawei.movie.activtiy;

import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bawei.movie.base.BaseActivity;
import com.bawei.movie.base.IBackCall;
import com.bawei.movie.bean.eventbean.EvenentBean;
import com.bawei.movie.bean.loginbean.LoginShow;
import com.bawei.movie.bean.loginbean.UserInfo;
import com.bawei.movie.prentent.LoginPrantent;
import com.bawei.movie.utils.Base64;
import com.bawei.movie.utils.EncryptUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.but_login)
    Button butLogin;
    @BindView(R.id.iv_we_chat)
    ImageView ivWeChat;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.but_display)
    CheckBox butDisplay;

    private LoginPrantent loginPrantent;

    @Override
    protected int onLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onView() {
        ButterKnife.bind(this);

    }

    @Override
    protected void onData() {
        loginPrantent = new LoginPrantent(new LoginCall());
        butDisplay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //如果选中，显示密码
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //否则隐藏密码
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @OnClick({R.id.but_login, R.id.iv_we_chat, R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_login:
                String email = etEmail.getText().toString();
                String pwd = etPwd.getText().toString();
                if (email != null && pwd != null) {
                    String encode = Base64.encode(pwd.getBytes());
                    String encrypt = EncryptUtil.encrypt(encode);
                    Log.i(TAG, "onClick: " + encrypt);
                    loginPrantent.getData(email, encrypt);
                }
                break;
            case R.id.iv_we_chat:
                break;
            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;

        }
    }

    private class LoginCall implements IBackCall<LoginShow> {
        @Override
        public void onSuccess(LoginShow homeShow) {
            EvenentBean evenentBean = new EvenentBean();
            UserInfo userInfo = homeShow.getResult().getUserInfo();
            String nickName = userInfo.getNickName();
            evenentBean.setNickName(nickName);
            String headPic = userInfo.getHeadPic();
            evenentBean.setHeadPic(headPic);
            EventBus.getDefault().postSticky(evenentBean);
            finish();
        }

        @Override
        public void onFail(String mes) {

        }
    }
}
