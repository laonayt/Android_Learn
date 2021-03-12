package com.we.androidwezhibo.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.we.androidwezhibo.R;

import butterknife.ButterKnife;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        mPresenter = getPresenter();
        initData();
        return view;
    }

    //子类实现的
    protected abstract int getLayoutId();
    protected abstract T getPresenter();
    protected abstract void initData();

}
