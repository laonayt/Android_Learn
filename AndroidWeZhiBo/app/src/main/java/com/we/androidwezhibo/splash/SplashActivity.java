package com.we.androidwezhibo.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.we.androidwezhibo.MainActivity;
import com.we.androidwezhibo.R;
import com.we.androidwezhibo.login.IPSetActivity;
import com.we.androidwezhibo.login.LoginActivity;
import com.we.androidwezhibo.login.LoginPresenter;
import com.we.androidwezhibo.login.LoginView;
import com.we.androidwezhibo.utils.SpTool;

public class SplashActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter mLoginPresenter;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initData();

        mHandler.sendEmptyMessageDelayed(555,2000);
    }

    private void initData() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Intent intent;
                switch (msg.what) {
                    case 111://登录页面
                        intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 222://引导页
                        intent = new Intent(SplashActivity.this, GuideActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 333://首页
                        intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 444://自动登录
                        autoLogin();
                        break;
                    case 555://判断
                        if (SpTool.getBool("notFirst")) {
                            if (SpTool.getBool("autoLogin")) {
                                mHandler.sendEmptyMessage(444);
                            } else {
                                mHandler.sendEmptyMessage(111);
                            }
                        } else {
                            mHandler.sendEmptyMessage(222);
                        }
                        break;
                }
            }
        };
    }

    private void autoLogin() {
        mLoginPresenter = new LoginPresenter(this);
        mLoginPresenter.loginRequest(SpTool.getString("name"), SpTool.getString("pwd"));
    }

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeMessages(111);
            mHandler.removeMessages(222);
            mHandler.removeMessages(333);
            mHandler.removeMessages(444);
            mHandler.removeMessages(555);
        }
    }
}