package com.we.androidwezhibo.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.we.androidwezhibo.R;
import com.we.androidwezhibo.login.LoginBean;
import com.we.androidwezhibo.utils.HelpTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MineFragment extends Fragment {

    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_role)
    TextView tvUserRole;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);

        LoginBean loginBean = HelpTool.getInstance().loginBean;
        tvUserName.setText(loginBean.loginName);

        if (loginBean.isVip()) {
            tvUserRole.setText("会员");
        } else {
            tvUserRole.setText("普通人");
        }

        return view;
    }

    @OnClick({R.id.iv_set, R.id.tv_info_edit, R.id.ll_info, R.id.rl_record, R.id.rl_favort, R.id.rl_download, R.id.rl_clean})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_set:
                intent = new Intent(getContext(), SetActivity.class);
                break;
            case R.id.tv_info_edit:
                break;
            case R.id.ll_info:
                break;
            case R.id.rl_record:
                break;
            case R.id.rl_favort:
                break;
            case R.id.rl_download:
                break;
            case R.id.rl_clean:
                break;
        }
        startActivity(intent);
    }
}
