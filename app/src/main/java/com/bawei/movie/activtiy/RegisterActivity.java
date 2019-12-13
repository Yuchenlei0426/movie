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

import com.bawei.movie.R;
import com.bawei.movie.base.BaseActivity;
import com.bawei.movie.base.IBackCall;
import com.bawei.movie.bean.loginbean.LoginShow;
import com.bawei.movie.prentent.CodePrentent;
import com.bawei.movie.prentent.RegisterPrantent;
import com.bawei.movie.utils.Base64;
import com.bawei.movie.utils.EncryptUtil;

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
    }

    @OnClick({R.id.but_code, R.id.but_xin_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_code:
                String email = etReEmail.getText().toString();
                codePrentent.getData(email);
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
}
