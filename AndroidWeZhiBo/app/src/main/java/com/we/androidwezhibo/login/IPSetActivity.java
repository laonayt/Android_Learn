package com.we.androidwezhibo.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.we.androidwezhibo.R;
import com.we.androidwezhibo.network.HttpResponse;
import com.we.androidwezhibo.network.RetrofitTool;
import com.we.androidwezhibo.utils.HelpTool;
import com.we.androidwezhibo.utils.SpTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import retrofit2.Call;
import retrofit2.Callback;

public class IPSetActivity extends AppCompatActivity {

    @BindView(R.id.et_ip)
    EditText etIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipset);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        etIp.setText(SpTool.getString("ip"));
    }

    @OnClick({R.id.bt_save, R.id.bt_cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_save:
                saveIpAction();
                break;
            case R.id.bt_cancle:
                finish();
                break;
        }
    }

    private void saveIpAction() {
        String ipStr = etIp.getText().toString();
        if (TextUtils.isEmpty(ipStr)) {
            Toast.makeText(this,"请输入IP", Toast.LENGTH_LONG).show();
        } else {
            RetrofitTool.getInstance().initRetrofit(ipStr, 8200);

            Call<HttpResponse> responseCall = RetrofitTool.getInstance().retrofitService.checkIpRequest();
            responseCall.enqueue(new Callback<HttpResponse>() {
                @Override
                public void onResponse(Call<HttpResponse> call, retrofit2.Response<HttpResponse> response) {
                    if (response.body().code == 0) {
                        SpTool.saveString(ipStr, "ip");
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<HttpResponse> call, Throwable t) {
                    Log.e("err","dddd");
                }
            });
        }
    }
}