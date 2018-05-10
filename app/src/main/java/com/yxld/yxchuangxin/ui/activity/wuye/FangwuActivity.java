package com.yxld.yxchuangxin.ui.activity.wuye;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerFangwuComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.FangwuContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.FangwuModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.FangwuPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/06
 */

public class FangwuActivity extends BaseActivity implements FangwuContract.View {

    @Inject
    FangwuPresenter mPresenter;
    @BindView(R.id.tv_xiangmu)
    MaterialSpinner tvXiangmu;
    @BindView(R.id.yezhu_name)
    TextView yezhuName;
    @BindView(R.id.yezhu_sex)
    TextView yezhuSex;
    @BindView(R.id.loupan)
    TextView loupan;
    @BindView(R.id.loudong)
    TextView loudong;
    @BindView(R.id.danyuan)
    TextView danyuan;
    @BindView(R.id.fanghao)
    TextView fanghao;
    @BindView(R.id.dianhua)
    TextView dianhua;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.huxing)
    TextView huxing;
    @BindView(R.id.mianji)
    TextView mianji;
    @BindView(R.id.shenfenzheng)
    TextView shenfenzheng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        needFront = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_fangwu);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvXiangmu.getPopupWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_material_spinner));
    }

    @Override
    protected void initData() {
        if (Contains.user != null && Contains.appYezhuFangwus != null) {
            String[] fc = {};
            fc = new String[Contains.appYezhuFangwus.size()];
            for (int i = 0; i < Contains.appYezhuFangwus.size(); i++) {
                String fcxx = Contains.appYezhuFangwus.get(i).getXiangmuLoupan()
                        + Contains.appYezhuFangwus.get(i).getFwLoudong()
                        + "栋 "
                        + Contains.appYezhuFangwus.get(i).getFwDanyuan()
                        + "单元 "
                        + Contains.appYezhuFangwus.get(i).getFwFanghao()
                        .toString() + "号";
                fc[i] = fcxx;
            }
            tvXiangmu.setItems(fc);
            setRoomInfo(Contains.curFangwu);
            tvXiangmu.setSelectedIndex(Contains.curFangwu);
            tvXiangmu.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
                @Override
                public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    setRoomInfo(position);
                }
            });
        }
    }

    @Override
    protected void setupActivityComponent() {
        DaggerFangwuComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .fangwuModule(new FangwuModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(FangwuContract.FangwuContractPresenter presenter) {
        mPresenter = (FangwuPresenter) presenter;
    }

    /**
     * 设置房屋信息
     * @param position
     */
    public void setRoomInfo(int position) {
//        yezhuName.setText(Contains.user.getYezhuId().toString());
        KLog.i(Contains.user.getYezhuSex());
        if (Contains.user.getYezhuSex() != null && !"".equals(Contains.user.getYezhuSex())) {
            if (Contains.user.getYezhuSex().equals("0")) {
                //男
                yezhuSex.setText("男");
            } else {
                yezhuSex.setText("女");
            }
        }
        loupan.setText(Contains.appYezhuFangwus.get(position).getXiangmuLoupan());
        loudong.setText(Contains.appYezhuFangwus.get(position).getFwLoudong());
        danyuan.setText(Contains.appYezhuFangwus.get(position).getFwDanyuan());
        fanghao.setText(Contains.appYezhuFangwus.get(position).getFwFanghao()
                .toString());
        dianhua.setText(Contains.user.getYezhuPhone());
        phone.setText(Contains.user.getYezhuShouji());
        huxing.setText(Contains.appYezhuFangwus.get(position).getFwHuxing());
        mianji.setText(Contains.appYezhuFangwus.get(position).getFwMainji());
        //如果是房东(产权人)展示完整身份证信息
        if (Contains.appYezhuFangwus.get(position).getFwyzType() < 1) {
            String shenfenzheng = Contains.user.getYezhuCardNum();
            if (!TextUtils.isEmpty(shenfenzheng) && shenfenzheng.length() >= 18) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < shenfenzheng.length(); i++) {
                    char c = shenfenzheng.charAt(i);
                    if (i >= 6 && i <= 18) {
                        sb.append('*');
                    } else {
                        sb.append(c);
                    }
                }
                this.shenfenzheng.setText(sb.toString());
            }
        } else {
            shenfenzheng.setText("******************");
            phone.setText("************");
        }
        yezhuName.setText(Contains.appYezhuFangwus.get(position).getYezhuName());
        Contains.curSelectXiaoQuId = Contains.appYezhuFangwus.get(position).getFwLoupanId();
        Contains.curSelectXiaoQuName = Contains.appYezhuFangwus.get(position).getXiangmuLoupan();
        Contains.curFangwu = position;
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
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }
}