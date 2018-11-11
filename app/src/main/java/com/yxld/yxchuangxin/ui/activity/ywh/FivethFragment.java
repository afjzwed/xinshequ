package com.yxld.yxchuangxin.ui.activity.ywh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.ui.activity.main.WebviewActivity;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerFivethComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.FivethContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.FivethModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.FivethPresenter;
import com.zhy.autolayout.AutoLinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/08 14:11:35
 */

public class FivethFragment extends BaseFragment implements FivethContract.View {

    @Inject
    FivethPresenter mPresenter;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.tv_content_head)
    TextView tvContentHead;
    @BindView(R.id.autoll_data0)
    AutoLinearLayout autollData0;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time1)
    TextView tvTime1;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_click_name1)
    TextView tvClickName1;
    @BindView(R.id.tv_click_name2)
    TextView tvClickName2;
    @BindView(R.id.auto_click)
    AutoLinearLayout autoClick;
    @BindView(R.id.autoll_data1)
    AutoLinearLayout autollData1;
    @BindView(R.id.autoll_data2)
    AutoLinearLayout autollData2;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.tv_download1)
    TextView tvDownload1;
    @BindView(R.id.tv_download2)
    TextView tvDownload2;
//    @BindView(R.id.et)
//    EditText et;

    private int status = 0;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ywh_fiveth, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();

        initData();
        return view;
    }

    private void initData() {
        YeWeiHuiActivity activity = (YeWeiHuiActivity) getActivity();
        int currentPosition = activity.getCurrentPosition();

        Log.e("wh", "FivethFragment" + currentPosition);
//        mPresenter.getFivethData();
        setFivethData(null);
    }

    @Override
    public void setFivethData(BaseEntity baseEntity) {
        switch (status) {
            case 0:
                ivNoData.setVisibility(View.VISIBLE);
                autollData0.setVisibility(View.GONE);
                autollData1.setVisibility(View.GONE);
                autoClick.setVisibility(View.GONE);
                autollData2.setVisibility(View.GONE);
                tvStatus.setText("业主大会阶段-未开始");
                tvStatus.setTextColor(getResources().getColor(R.color.color_ff9e04));
                break;
            case 1:
                ivNoData.setVisibility(View.GONE);
                autollData0.setVisibility(View.VISIBLE);
                autollData1.setVisibility(View.VISIBLE);
                autoClick.setVisibility(View.VISIBLE);
                autollData2.setVisibility(View.GONE);
                tvStatus.setText("业主大会阶段-进行中");
                tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
                ivImg.setImageResource(R.mipmap.ic_ywh_vote);
                tvTitle.setText("线上投票");
                tvTime1.setVisibility(View.VISIBLE);
                tvTime1.setText("2018-07-08至2018-07-08");
                tvContent.setVisibility(View.VISIBLE);
                tvContent.setText("卷首卷首卷首卷首卷首卷首卷首卷首卷首卷首卷首卷首卷首卷首卷首卷首卷首卷首卷首卷首卷首卷首");
                line.setVisibility(View.GONE);
                autoClick.setBackgroundColor(getResources().getColor(R.color.color_ffefea));
                tvClickName1.setText("进入投票");
                tvClickName1.setTextColor(getResources().getColor(R.color.color_ea3006));
                tvClickName2.setText("未投票");
                tvClickName2.setVisibility(View.VISIBLE);
                tvClickName2.setTextColor(getResources().getColor(R.color.color_ea3006));
                ivArrow.setImageResource(R.mipmap.ic_jt_red);
//                et.setText(Contains.uuid);
                break;
            case 2:
                ivNoData.setVisibility(View.GONE);
                autollData0.setVisibility(View.GONE);
                autollData1.setVisibility(View.VISIBLE);
                autoClick.setVisibility(View.VISIBLE);
                autollData2.setVisibility(View.GONE);
                tvStatus.setText("业主大会阶段-进行中");
                tvStatus.setTextColor(getResources().getColor(R.color.color_2d97ff));
                ivImg.setImageResource(R.mipmap.ic_ywh_material);
                tvTitle.setText("业主大会结果公示");
                tvTime1.setText("2018-08-07");
                tvContent.setVisibility(View.VISIBLE);
                tvContent.setText("消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容");
                autoClick.setBackgroundColor(getResources().getColor(R.color.white));
                tvClickName1.setText("查看业主大会结果公示");
                tvClickName1.setTextColor(getResources().getColor(R.color.color_2d97ff));
                tvClickName2.setVisibility(View.GONE);
                line.setVisibility(View.VISIBLE);
                ivArrow.setImageResource(R.mipmap.ic_jt_blue);
                break;
            case 3:
                ivNoData.setVisibility(View.GONE);
                autollData0.setVisibility(View.GONE);
                autollData1.setVisibility(View.VISIBLE);
                autoClick.setVisibility(View.VISIBLE);
                autollData2.setVisibility(View.VISIBLE);
                tvStatus.setText("业主大会阶段-已完成");
                tvStatus.setTextColor(getResources().getColor(R.color.color_00b404));
                ivImg.setImageResource(R.mipmap.ic_ywh_start3);
                tvTitle.setText("业主委员会成员已确定");
                tvContent.setVisibility(View.GONE);
                tvClickName1.setText("查看业主委员会成员信息");
                break;
        }
    }

    @Override
    protected void setupFragmentComponent() {
        DaggerFivethComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .fivethModule(new FivethModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(FivethContract.FivethContractPresenter presenter) {
        mPresenter = (FivethPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

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
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.tv_status, R.id.auto_click,R.id.tv_download1,R.id.tv_download2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_status:
                if (status == 0) {
                    status = 1;
                } else if (status == 1) {
                    status = 2;
                } else if (status == 2) {
                    status = 3;
                } else {
                    status = 0;
                }
                setFivethData(null);
                break;
            case R.id.auto_click:
                Intent intent;
                switch (status) {
                    case 1:
//                        intent = new Intent(getActivity(), RecommendMemberActivity.class);
//                        startActivity(intent);
                        intent = new Intent(getActivity(), WebviewActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name", "投票");
//                        bundle.putString("address", "http://m.kuaidihelp.com/" + Contains.uuid);
                        bundle.putString("address", "http://192.168.8.130:8020/research/index.html?uid=" + Contains.uuid);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), ResultShowActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getActivity(), YwhMemberShowActivity.class);
                        startActivity(intent);
                        break;
                }
                break;
            case R.id.tv_download1:
                Toast.makeText(getActivity(), "点击1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_download2:
                Toast.makeText(getActivity(), "点击2", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}