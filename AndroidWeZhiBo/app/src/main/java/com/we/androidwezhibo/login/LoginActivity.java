package com.we.androidwezhibo.login;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.we.androidwezhibo.MainActivity;
import com.we.androidwezhibo.R;
import com.we.androidwezhibo.base.BaseActivity;
import com.we.androidwezhibo.network.HttpResponse;
import com.we.androidwezhibo.network.RetrofitTool;
import com.we.androidwezhibo.utils.HelpTool;
import com.we.androidwezhibo.utils.SpTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Query;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView, EasyPermissions.PermissionCallbacks {
    @BindView(R.id.ev_name)
    EditText evName;
    @BindView(R.id.ev_pwd)
    EditText evPwd;
    private String[] permList = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initData() {
        if (TextUtils.isEmpty(SpTool.getString("ip"))){
            Intent intent = new Intent(LoginActivity.this, IPSetActivity.class);
            startActivity(intent);
        } else {
            requestPermission();
        }
    }

    @OnClick({R.id.bt_ip, R.id.bt_login})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.bt_ip) {
            Intent intent = new Intent(this, IPSetActivity.class);
            startActivity(intent);
        } else {
            String name = evName.getText().toString();
            String pwd = evPwd.getText().toString();
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                Toast.makeText(this, "用户名或密码不能为空",Toast.LENGTH_LONG).show();
            } else {
                mPresenter.loginRequest(name,pwd);
            }
        }
    }

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void requestPermission() {
        if (!EasyPermissions.hasPermissions(this,permList)) {
            ActivityCompat.requestPermissions(LoginActivity.this,permList,0);

//            QMUIDialog.MessageDialogBuilder msgBuilder = new QMUIDialog.MessageDialogBuilder(this);
//            msgBuilder.setTitle("授权");
//            msgBuilder.setMessage("请授予app必须的权限");
//            msgBuilder.addAction("开始授权", new QMUIDialogAction.ActionListener() {
//                @Override
//                public void onClick(QMUIDialog dialog, int index) {
//                    dialog.dismiss();
//
//                }
//            });
//            QMUIDialog qmuiDialog = msgBuilder.create();
//            qmuiDialog.setCancelable(false);
//            qmuiDialog.setCanceledOnTouchOutside(false);
//            qmuiDialog.show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        for (int i = 0; i < permList.length; i++) {
            if (!perms.contains(permList[i])) {
                ActivityCompat.requestPermissions(LoginActivity.this,permList,0);
            }
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}