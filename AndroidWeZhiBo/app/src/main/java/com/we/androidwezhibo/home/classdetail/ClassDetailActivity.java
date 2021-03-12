package com.we.androidwezhibo.home.classdetail;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.we.androidwezhibo.R;
import com.we.androidwezhibo.login.LoginBean;
import com.we.androidwezhibo.network.HttpResponse;
import com.we.androidwezhibo.network.RetrofitTool;
import com.we.androidwezhibo.utils.HelpTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JzvdStd;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassDetailActivity extends AppCompatActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.jz_video)
    JzvdStd jzVideo;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.et_content)
    EditText etContent;

    private String curriculumId;
    private boolean isLive;
    private List<CommentListBean.CommentListItem> mCommentList = new ArrayList<>();
    private CommentAdapter mCommentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);
        ButterKnife.bind(this);

        commentListRequest();

        initData();
    }

    //初始化数据
    private void initData() {
        //传参
        curriculumId = getIntent().getStringExtra("curriculumId");
        isLive = getIntent().getBooleanExtra("isLive",false);

        tvTitle.setText("课堂详情");

        //
        jzVideo.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
                , "");
        jzVideo.posterImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));
        jzVideo.startVideo();

        //
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rvList.setLayoutManager(lm);

        mCommentAdapter = new CommentAdapter(mCommentList);
        rvList.setAdapter(mCommentAdapter);
    }

    @OnClick({R.id.iv_back,R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bt_submit:
                addCommentRequest();
                break;
        }
    }

    //评论列表
    private void commentListRequest() {
        String type = isLive ? "1" : "0";

        Map<String, Object> keywordsP = new HashMap<>();
        keywordsP.put("resourceId","");
        keywordsP.put("code", "");
        keywordsP.put("type", type);

        Map<String, Object> pageP = new HashMap<>();
        pageP.put("limit", 100);
        pageP.put("offset", "1");

        Map<String, Object> params = new HashMap<>();
        params.put("keywords", keywordsP);
        params.put("page", pageP);

        Call<HttpResponse<CommentListBean>> responseCall = RetrofitTool.getInstance().retrofitService.commentListRequest(params);
        responseCall.enqueue(new Callback<HttpResponse<CommentListBean>>() {
            @Override
            public void onResponse(Call<HttpResponse<CommentListBean>> call, Response<HttpResponse<CommentListBean>> response) {
                List<CommentListBean.CommentListItem> list = response.body().data.list;
                mCommentList.addAll(list);
                mCommentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<HttpResponse<CommentListBean>> call, Throwable t) {

            }
        });

    }

    //添加评论
    private void addCommentRequest() {
        String content = etContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this,"请输入评论内容", Toast.LENGTH_SHORT).show();
            return;
        }
        LoginBean loginBean = HelpTool.getInstance().loginBean;
        String type = isLive ? "1" : "0";

        Map<String, Object> params = new HashMap<>();
        params.put("code", loginBean.code);
        params.put("resourceId", curriculumId);
        params.put("type", type);
        params.put("content", content);
        params.put("flag", "0");
        params.put("parentId", "0");

        Call<HttpResponse> responseCall = RetrofitTool.getInstance().retrofitService.addCommentRequest(params);
        responseCall.enqueue(new Callback<HttpResponse>() {
            @Override
            public void onResponse(Call<HttpResponse> call, Response<HttpResponse> response) {
                if (response.body().code == 0) {
                    etContent.setText("");
                    //请求
                    commentListRequest();
//                    KeyBoardUtil.closeKeyBoard(this);
                } else {

                }
            }

            @Override
            public void onFailure(Call<HttpResponse> call, Throwable t) {

            }
        });
    }

    //删除评论
    private void deleteCommentRequest() {

    }

}
