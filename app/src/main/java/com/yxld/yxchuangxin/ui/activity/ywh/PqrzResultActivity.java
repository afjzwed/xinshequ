package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.entity.YwhSmrzResult;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerPqrzResultComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.PqrzResultContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.PqrzResultModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.PqrzResultPresenter;
import com.yxld.yxchuangxin.ui.adapter.ywh.ImgAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/09 14:52:55
 */

public class PqrzResultActivity extends BaseActivity implements PqrzResultContract.View {

    @Inject
    PqrzResultPresenter mPresenter;
    @BindView(R.id.rv) RecyclerView rv;
    private ImgAdapter adapter;
    private List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        needFront = true;
        setContentView(R.layout.activity_pqrz_result);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initRv();
    }

    private void initRv() {
        rv.setLayoutManager(new GridLayoutManager(this, 2));
//        rv.addItemDecoration(new GridLayoutSpace(2,20,true));
        dataList = new ArrayList<>();
        adapter = new ImgAdapter(dataList);
        rv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mPresenter.getDetail();

    }

    @Override
    protected void setupActivityComponent() {
        DaggerPqrzResultComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .pqrzResultModule(new PqrzResultModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(PqrzResultContract.PqrzResultContractPresenter presenter) {
        mPresenter = (PqrzResultPresenter) presenter;
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
    public void getDetailSuccess(YwhSmrzResult baseEntity) {
        if (baseEntity.getCode() == 200) {
            dataList.clear();
            dataList.add(baseEntity.getData().getCardFront());
            dataList.add(baseEntity.getData().getCardReverse());
            if (baseEntity.getData().getDeedFront() != null && baseEntity.getData().getPaperwork() != null
                    && baseEntity.getData().getDeedFront().size() == baseEntity.getData().getPaperwork().size()) {
                for (int i = 0; i < baseEntity.getData().getDeedFront().size(); i++) {
                    dataList.add(baseEntity.getData().getDeedFront().get(i));
                    dataList.add(baseEntity.getData().getPaperwork().get(i));

                }
            }
            adapter.setHeaderView(getHeadView(baseEntity.getData().getStatusX(),baseEntity));
            adapter.setNewData(dataList);
        } else {
            onError(baseEntity.msg);
        }
    }

    @Override
    public void getStatusSuccess(BaseEntity baseEntity) {
        if (baseEntity.getMsg().equals("操作成功")) {
            EventBus.getDefault().post(ThirdFragment.EVEBUS_MSG);
            finish();
        } else {
            onError(baseEntity.msg);
        }
    }

    private View getHeadView(int type,YwhSmrzResult data) {
        View view;
        if (type == 2) {
            view = LayoutInflater.from(this).inflate(R.layout.head_ywh_smrz_sussess, (ViewGroup) rv.getParent(), false);
            TextView tvMj = view.findViewById(R.id.tv_mj);
            tvMj.setText("恭喜您 用于一张票权：\n您的专属物业面积为"+data.getData().getArea()+"㎡");
        } else if (type == -1) {
            view = LayoutInflater.from(this).inflate(R.layout.head_ywh_smrz_fail, (ViewGroup) rv.getParent(), false);
            TextView tvCommit = view.findViewById(R.id.tv_cimmit);
            tvCommit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetCommit();
                }
            });
        } else {
            view = LayoutInflater.from(this).inflate(R.layout.head_ywh_smrz_ing, (ViewGroup) rv.getParent(), false);
        }
        return view;
    }

    /**
     * 重新提交 改变申请状态
     */
    private void resetCommit() {
        mPresenter.getChangeStatus();
    }
}