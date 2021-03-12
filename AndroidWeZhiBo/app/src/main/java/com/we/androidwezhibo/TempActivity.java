package com.we.androidwezhibo;

import android.os.Bundle;
import android.widget.TextView;

import com.we.androidwezhibo.base.BaseActivity;
import com.we.androidwezhibo.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TempActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.des_tv)
    TextView desTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_temp;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}