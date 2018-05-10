package com.yxld.yxchuangxin.ui.activity.rim;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerRimCommentAddComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimCommentAddContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.RimCommentAddModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.RimCommentAddPresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: 欣周边 评论列表
 * @date 2017/06/17
 */

public class RimCommentAddActivity extends BaseActivity implements RimCommentAddContract.View {

    @Inject
    RimCommentAddPresenter mPresenter;
    @BindView(R.id.iv_story_img)
    ImageView ivStoryImg;
    @BindView(R.id.tv_comment_tg)
    TextView tvCommentTg;
    @BindView(R.id.rim_list_ratingBar)
    RatingBar rimListRatingBar;
    @BindView(R.id.edit_comment)
    EditText editComment;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    private String orderNumber; //订单编号
    private String businessLogo;//商家logo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.rim_comment_save_layout);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        orderNumber = getIntent().getStringExtra("orderNumber");
        businessLogo = getIntent().getStringExtra("business_logo");

        Glide.with(this)
                .load(API.PIC + businessLogo)
//                .placeholder(R.drawable.default_recommed_icon)
//                .error(R.drawable.default_recommed_icon)
                .crossFade()//渐入渐出
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(ivStoryImg);

        StringUitl.forbidEmoji(editComment,70,this);//限制输入字数和表情
//
//        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
//                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
//
//        InputFilter lengthFilter = new InputFilter.LengthFilter(70);
//        editComment.setFilters(new InputFilter[]{
//                new InputFilter() {
//                    @Override
//                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//                        Matcher emojiMatcher = emoji.matcher(source);
//                        if (emojiMatcher.find()) {
//                            Toast.makeText(RimCommentAddActivity.this, "不支持输入表情", Toast.LENGTH_SHORT).show();
//                            return "";
//                        }
//                        return null;
//                    }
//                },lengthFilter
//        });
    }

    @Override
    protected void setupActivityComponent() {
        DaggerRimCommentAddComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .rimCommentAddModule(new RimCommentAddModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(RimCommentAddContract.RimCommentAddContractPresenter presenter) {
        mPresenter = (RimCommentAddPresenter) presenter;
    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        if (StringUitl.isNotEmpty(this, editComment, "请输入评价内容")) {
            if (rimListRatingBar.getRating() > 0) {
                Map map = new HashMap();
                map.put("uuId", Contains.uuid);
                map.put("orderNumber", orderNumber);
                map.put("orderEvaluateEvaLevel", (int) rimListRatingBar.getRating() + "");
                map.put("evaluateContent", editComment.getText().toString());
                mPresenter.addCommentData(map);
            } else {
                ToastUtil.show(this, "星级最低为1星");
            }
        } else {
            ToastUtil.show(this, "请输入评价内容");
        }
    }

    @Override
    public void requestSuccess() {
//        Intent intent = new Intent();
//        setResult(RESULT_OK, intent);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void requestFail(String msg) {
        ToastUtil.show(this, msg);
    }
}