package com.yxld.yxchuangxin.ui.activity.wuye;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.PopWindowUtil;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.ui.activity.base.ViewPagerActivity;
import com.yxld.yxchuangxin.ui.activity.index.util.Bimp;
import com.yxld.yxchuangxin.ui.activity.wuye.component.DaggerWuyeJianyiComponent;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.WuyeJianyiContract;
import com.yxld.yxchuangxin.ui.activity.wuye.module.WuyeJianyiModule;
import com.yxld.yxchuangxin.ui.activity.wuye.presenter.WuyeJianyiPresenter;
import com.yxld.yxchuangxin.ui.adapter.wuye.GridAdapter;
import com.yxld.yxchuangxin.view.CustomPopWindow;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: $description
 * @date 2017/06/20 11:11:36
 */

public class WuyeJianyiFragment extends BaseFragment implements WuyeJianyiContract.View {

    @Inject
    WuyeJianyiPresenter mPresenter;
    @Inject
    GridLayoutManager gridLayoutManager;
    @Inject
    GridAdapter gridAdapter;

    @BindView(R.id.tv_tousu_neirong)
    TextView tvTousuNeirong;
    @BindView(R.id.et_tousu_content)
    EditText etTousuContent;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.tv_enter_lsit)
    TextView tvEnterLsit;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wuye_jianyi, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        StringUitl.setEditTextInhibitInputSpeOnlyChnese50(etTousuContent);
        StringUitl.setEditTextInhibitInputSpeOnlyChnese50(etTousuContent);
        SpannableStringBuilder ss = new SpannableStringBuilder  ("添加图片\n(最多可添加3张)");
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(40);
        ss.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_646464)), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        AbsoluteSizeSpan span1 = new AbsoluteSizeSpan(25);
        ss.setSpan(span1, 4, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_e84141)), 4, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDesc.setText(ss);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(gridAdapter);
        gridAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (Bimp.tempJianyiSelectBitmap.get(position).isSelected()) {
                    Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
                    intent.putExtra("item", position);
                    intent.putExtra("url", "");
                    intent.putExtra("url1", "2");
                    startActivity(intent);
                } else {
                    showImgPickPop(recyclerView, position);
                }
            }
        });
        gridAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bimp.tempJianyiSelectBitmap.get(position).setSelected(false);
                gridAdapter.notifyDataSetChanged();
            }
        });
        mPresenter.init();
        return view;
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerWuyeJianyiComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .wuyeJianyiModule(new WuyeJianyiModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(WuyeJianyiContract.WuyeJianyiContractPresenter presenter) {
        mPresenter = (WuyeJianyiPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void onJianYiBack() {
        ToastUtil.show(getActivity(), "建议提交成功，我们会尽快处理");
        closeProgressDialog();
        etTousuContent.setText("");
        Bimp.tempJianyiSelectBitmap.clear();
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        mPresenter.unsubscribe();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

    @OnClick({R.id.bt_submit, R.id.tv_enter_lsit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                int imgCount = 0;
                if (!StringUitl.isNotEmpty(getActivity(), etTousuContent.getText().toString(), "建议内容为空")) {
                    return;
                }
                for (int i = 0; i < 3; i++) {
                    if (Bimp.tempJianyiSelectBitmap.get(i).isSelected()) {
                        imgCount++;
                    }
                }
                if (imgCount != 0) {
                    //上传逻辑
                    Bimp.imgJianyiCount = imgCount;
                    mPresenter.upLoadImg();
                } else {
                    Map<String, String> map = new HashMap<>();
                    map.put("tousujianyitype", "2");
                    map.put("yezhuid", Contains.user.getYezhuId() + "");
                    map.put("tousuXiangmuId", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoupanId() + "");
                    map.put("tsjyNeirong", etTousuContent.getText().toString());
                    mPresenter.saveJianYi(map);
                }
                break;
            case R.id.tv_enter_lsit:
                startActivity(ComplainListActivity.class);
                break;
        }
    }

    private void showImgPickPop(View v, int position) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_pick_image, null);
        Button item_popupwindows_camera = (Button) view.findViewById(R.id.item_popupwindows_camera);
        Button item_popupwindows_Photo = (Button) view.findViewById(R.id.item_popupwindows_Photo);
        Button item_popupwindows_cancel = (Button) view.findViewById(R.id.item_popupwindows_cancel);
        item_popupwindows_camera.setOnClickListener(v12 -> {
            mPresenter.fromCameraUpLoad(position);
            CustomPopWindow.onBackPressed();
        });
        item_popupwindows_Photo.setOnClickListener(v13 -> {
            mPresenter.fromAlbumUpLoad(position);
            CustomPopWindow.onBackPressed();
        });
        item_popupwindows_cancel.setOnClickListener(v1 -> CustomPopWindow.onBackPressed());
        AutoLinearLayout ll_popup = (AutoLinearLayout) view.findViewById(R.id.ll_popup);

        PopWindowUtil.showFullScreenPopWindow(getActivity(), v, view, ll_popup);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPickImgBack() {
        gridAdapter.notifyDataSetChanged();
    }

    @Override
    public void onUploadOVer(String url) {
        Map<String, String> map = new HashMap<>();
        map.put("tousujianyitype", "2");
        map.put("yezhuid", Contains.user.getYezhuId() + "");
        map.put("tousuXiangmuId", Contains.appYezhuFangwus.get(Contains.curFangwu).getFwLoupanId() + "");
        map.put("tsjyNeirong", etTousuContent.getText().toString());
        map.put("tousuTupianUrl", url);
        mPresenter.saveJianYi(map);
    }
}