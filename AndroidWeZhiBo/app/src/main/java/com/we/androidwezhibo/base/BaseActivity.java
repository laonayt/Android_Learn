package com.we.androidwezhibo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

//<T extends BasePresenter> 有点意思, 也是个抽象类
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPresenter = getPresenter();
        initData();
    }

    //子类实现的
    protected abstract int getLayoutId();
    protected abstract T getPresenter();
    protected abstract void initData();
}
