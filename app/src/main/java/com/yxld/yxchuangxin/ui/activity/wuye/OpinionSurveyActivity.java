package com.yxld.yxchuangxin.ui.activity.wuye;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.OpinionSurveyEntity;
import com.yxld.yxchuangxin.entity.YwhMember;
import com.yxld.yxchuangxin.ui.activity.main.WebviewActivity;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerOpinionSurveyComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.OpinionSurveyContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.OpinionSurveyModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.OpinionSurveyPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.OpinionSurveyAdapter;
import com.yxld.yxchuangxin.ui.adapter.ywh.YwhMemberShowAdapter;
import com.yxld.yxchuangxin.view.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2018/11/12 18:08:34
 */

public class OpinionSurveyActivity extends BaseActivity implements OpinionSurveyContract.View, SwipeRefreshLayout
        .OnRefreshListener {

    @Inject
    OpinionSurveyPresenter mPresenter;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.swip_refresh)
    SwipeRefreshLayout swipRefresh;

    private OpinionSurveyAdapter opinionSurveyAdapter;
    private List<OpinionSurveyEntity.DataBean> surveyList = new ArrayList<>();
    private int page;//分页数
    private int rows = 6;//每页加载数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        needFront = true;
        setContentView(R.layout.activity_opinionsurvey);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        UIUtils.configSwipeRefreshLayoutColors(swipRefresh);
        swipRefresh.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        opinionSurveyAdapter = new OpinionSurveyAdapter();
        opinionSurveyAdapter.setLoadMoreView(new CustomLoadMoreView());
        opinionSurveyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getListData(false);
            }
        }, recyclerView);

        opinionSurveyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                OpinionSurveyEntity.DataBean dataBean = (OpinionSurveyEntity.DataBean) baseQuickAdapter.getData().get
                        (position);
                Intent intent = new Intent(OpinionSurveyActivity.this, WebviewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", "投票");
                String url = "http://192.168.8.130:8020/research/index.html?id=" + dataBean.getId() +
                        "&uuid=" + Contains.uuid + "&expect=" +stringToUnicode(Contains.appYezhuFangwus.get(Contains.curFangwu)
                        .getXiangmuLoupan())  + "&building=" + stringToUnicode(Contains.appYezhuFangwus.get(Contains.curFangwu)
                        .getFwLoudong()) + "&unit=" + stringToUnicode(Contains.appYezhuFangwus.get(Contains.curFangwu).getFwDanyuan())
                        + "&room_number=" + stringToUnicode(Contains.appYezhuFangwus.get(Contains.curFangwu).getFwFanghao());
                bundle.putString("address", url);
                Log.e("wh", url);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(opinionSurveyAdapter);//绑定适配器
    }

    @Override
    protected void initData() {
        page = 1;
        swipRefresh.setRefreshing(true);//显示加载进度条.要在主线程中执行
        opinionSurveyAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载，设置为true就是自动加载更多
        getListData(true);
    }

    private void getListData(boolean isRefresh) {
        //网络请求 获得数据
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("uuid", Contains.uuid);
        map.put("page", page + "");
        map.put("rows", rows + "");
        map.put("subjectType", "1");

        mPresenter.getData(map, isRefresh);
    }

    @Override
    public void setData(boolean isRefresh, OpinionSurveyEntity data) {
        page++;
        surveyList = data.getData();
        final int size = surveyList == null ? 0 : surveyList.size();

        if (isRefresh) {
            if (size > 0) {

            } else {
//                rimOrderListAdapter.setEmptyView(notDataView);
                opinionSurveyAdapter.setNewData(new ArrayList<>());
            }
            opinionSurveyAdapter.setNewData(surveyList);//将首次数据塞入适配器的方法
        } else {
            if (size > 0) {
                opinionSurveyAdapter.addData(surveyList);//加载更多时直接将更多数据塞入适配器
            }
        }

        if (size < rows) {
            //第一页如果不够一页就不显示没有更多数据布局
            opinionSurveyAdapter.loadMoreEnd(isRefresh);//不传参数默认false,表示数据全部加载完毕没有更多数据  加载结束
            // 这里设置为false可用来显示没有更多数据item
        } else {
            opinionSurveyAdapter.loadMoreComplete();//加载完成（注意不是加载结束，而是本次数据加载结束并且还有下页数据）
        }

        opinionSurveyAdapter.setEnableLoadMore(true);//自动加载更多
        swipRefresh.setRefreshing(false);//加载完成,不显示进度条
    }

    @Override
    public void setError() {
        opinionSurveyAdapter.setEnableLoadMore(true);//自动加载更多
        swipRefresh.setRefreshing(false);//加载完成,不显示进度条
//        fkyjListAdapter.setEmptyView(notDataView);
        opinionSurveyAdapter.setNewData(new ArrayList<>());
        ToastUtil.show(this, "加载失败");
    }

    @Override
    protected void setupActivityComponent() {
        DaggerOpinionSurveyComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .opinionSurveyModule(new OpinionSurveyModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(OpinionSurveyContract.OpinionSurveyContractPresenter presenter) {
        mPresenter = (OpinionSurveyPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void onRefresh() {
        initData();
    }

    /**
     * 将utf-8的汉字转换成unicode格式汉字码
     * @param string
     * @return
     */
    private String stringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            unicode.append("\\u" + Integer.toHexString(c));
        }
        String str = unicode.toString();
        return str.replaceAll("\\\\", "0x");
    }
}