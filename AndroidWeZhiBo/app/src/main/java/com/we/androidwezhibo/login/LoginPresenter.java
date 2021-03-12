package com.we.androidwezhibo.login;

import android.widget.Toast;

import com.we.androidwezhibo.base.BasePresenter;
import com.we.androidwezhibo.network.HttpResponse;
import com.we.androidwezhibo.utils.HelpTool;
import com.we.androidwezhibo.utils.SpTool;
import com.we.androidwezhibo.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView view) {
        super(view);
    }

    public void loginRequest(String name, String pwd) {
        Map<String,Object> params = new HashMap<>();
        params.put("loginName", name);
        params.put("passWord", pwd);

        Call<HttpResponse<LoginBean>> responseCall = retrofitService().loginRequest(params);
        responseCall.enqueue(new Callback<HttpResponse<LoginBean>>() {
            @Override
            public void onResponse(Call<HttpResponse<LoginBean>> call, Response<HttpResponse<LoginBean>> response) {
                if (response.body().code == 0) {
                    SpTool.saveString(name, "name");
                    SpTool.saveString(pwd, "pwd");
                    SpTool.saveBool(true, "autoLogin");

                    HelpTool.getInstance().loginBean = response.body().data;
                    mView.onLoginSuccess();
                } else {
                    ToastUtils.showToast("登录失败!");
                }
            }

            @Override
            public void onFailure(Call<HttpResponse<LoginBean>> call, Throwable t) {
                ToastUtils.showToast("网络请求失败!");
            }
        });
    }

}
