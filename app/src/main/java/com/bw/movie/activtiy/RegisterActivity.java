package com.bw.movie.activtiy;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.loginbean.LoginShow;
import com.bw.movie.prentent.CodePrentent;
import com.bw.movie.prentent.RegisterPrantent;
import com.bw.movie.utils.Base64;
import com.bw.movie.utils.EncryptUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    private static final String TAG = "RegisterActivity";
    @BindView(R.id.et_nickName)
    EditText etNickName;
    @BindView(R.id.et_re_email)
    EditText etReEmail;
    @BindView(R.id.et_re_pwd)
    EditText etRePwd;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.but_code)
    Button butCode;
    @BindView(R.id.but_xin_register)
    Button butXinRegister;
    @BindView(R.id.but_display)
    CheckBox butDisplay;
    private RegisterPrantent registerPrantent;
    private CodePrentent codePrentent;
    private MyCountDownTimer myCountDownTimer;


    @Override
    protected int onLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void onView() {
        ButterKnife.bind(this);

    }

    @Override
    protected void onData() {
        registerPrantent = new RegisterPrantent(new Register());
        codePrentent = new CodePrentent(new CodeCall());

        butDisplay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //如果选中，显示密码
                    etRePwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //否则隐藏密码
                    etRePwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        myCountDownTimer = new MyCountDownTimer(60000,1000);

    }



    @OnClick({R.id.but_code, R.id.but_xin_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_code:
                String email = etReEmail.getText().toString();
                codePrentent.getData(email);
                myCountDownTimer.start();
                break;
            case R.id.but_xin_register:
                String nickName = etNickName.getText().toString();
                String reEmail = etReEmail.getText().toString();
                String rePwd = etRePwd.getText().toString();
                String code = etCode.getText().toString();
                String encode = Base64.encode(rePwd.getBytes());
                String encrypt = EncryptUtil.encrypt(encode);
                registerPrantent.getData(nickName,encrypt, reEmail, code);
                break;
        }
    }

    private class Register implements IBackCall<LoginShow> {
        @Override
        public void onSuccess(LoginShow homeShow) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onFail(String mes) {
            Log.i(TAG, "onFail: "+mes);

        }
    }

    private class CodeCall implements IBackCall<LoginShow> {
        @Override
        public void onSuccess(LoginShow homeShow) {

        }

        @Override
        public void onFail(String mes) {
            Log.i(TAG, "onFail: "+mes);
        }
    }


    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            butCode.setClickable(false);
            butCode.setText(l/1000+"秒");

        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            butCode.setText("重新获取");
            //设置可点击
            butCode.setClickable(true);
        }
    }

}
