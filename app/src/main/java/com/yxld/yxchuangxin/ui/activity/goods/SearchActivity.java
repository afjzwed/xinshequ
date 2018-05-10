package com.yxld.yxchuangxin.ui.activity.goods;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.yxld.yxchuangxin.Utils.UIUtils;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.db.DBUtil;
import com.yxld.yxchuangxin.entity.SearchHistoryEntityWh;
import com.yxld.yxchuangxin.ui.activity.goods.component.DaggerSearchComponent;
import com.yxld.yxchuangxin.ui.activity.goods.contract.SearchContract;
import com.yxld.yxchuangxin.ui.activity.goods.module.SearchModule;
import com.yxld.yxchuangxin.ui.activity.goods.presenter.SearchPresenter;
import com.yxld.yxchuangxin.view.GoodsListSearchView;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: $description
 * @date 2017/06/24 10:26:33
 */

public class SearchActivity extends BaseActivity implements SearchContract.View {
    public static final String KEY_IN_TYPE = "key_in_type";
    public static final int IN_TYPE_MAIN = 0x000010;
    public static final int IN_TYPE_GOODS_LIST = 0x000011;

    @Inject
    SearchPresenter mPresenter;
    @BindView(R.id.goods_list_search_view)
    GoodsListSearchView goodsListSearchView;
    @BindView(R.id.rl_clear_search_history)
    AutoRelativeLayout rlClearSearchHistory;
    @BindView(R.id.goods_list_status_bar)
    View goodsListStatusBar;
    @BindView(R.id.flowlayout)
    TagFlowLayout flowlayout;
    @BindView(R.id.flowlayout_rensou)
    TagFlowLayout flowlayoutRensou;

    /**
     * 获取保存在数据库中的搜索数据
     */
    private List<SearchHistoryEntityWh> mSearchedHistories;
    private ArrayList<SearchHistoryEntityWh> sortSearchedHistories = new ArrayList<SearchHistoryEntityWh>();//用于排序搜索数据的集合

    private int mCurrentInType;
    private ArrayList<String> value = new ArrayList<>();
    private ArrayList<String> reSouList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentInType = getIntent().getExtras().getInt(KEY_IN_TYPE, IN_TYPE_MAIN);
        getSearchHistory("1");

        if (mSearchedHistories == null || mSearchedHistories.size() == 0) {
            rlClearSearchHistory.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        toolbar.setVisibility(View.GONE);
        initStatus();

        goodsListSearchView.setOnViewClickListener(new GoodsListSearchView.OnViewClickListener() {
            @Override
            public void onBackClick() {
                finish();
            }

            @Override
            public void onSearchTextClick() {
                if (TextUtils.isEmpty(goodsListSearchView.getSearchContent())) {
                    Toast.makeText(SearchActivity.this, "嘿，客观，想要啥？", Toast.LENGTH_SHORT).show();
                    return;
                }

                backToSearch(goodsListSearchView.getSearchContent(), true);
            }

            @Override
            public void onSearchRecClick() {

            }
        });

        flowlayout.setMaxSelectCount(1);
        flowlayoutRensou.setMaxSelectCount(1);
    }

    private void backToSearch(String text, boolean add2db) {

        if (add2db && dbUtil != null) {
            SearchHistoryEntityWh entity = new SearchHistoryEntityWh("1", text, System.currentTimeMillis());
            long result = dbUtil.insert(entity, entity.getU_id());
            KLog.i("Insert db ", "result : " + result);
        }
        if (mCurrentInType == IN_TYPE_MAIN) {
            Intent intent = new Intent(SearchActivity.this, MallGoodsListActivity.class);
            intent.putExtra(MallGoodsListActivity.PRODUCT_NAME, text);
            startActivity(intent);
        } else {
            Intent intent = new Intent();
            intent.putExtra(MallGoodsListActivity.PRODUCT_NAME, text);
            setResult(MallGoodsListActivity.CODE_SEARCH, intent);
        }
        finish();
    }

    private void initStatus() {
        AutoLinearLayout.LayoutParams lp = new AutoLinearLayout.LayoutParams(UIUtils.getDisplayWidth(this), (int) (UIUtils.getStatusBarHeight(this) * 2));
        goodsListSearchView.setLayoutParams(lp);
        AutoLinearLayout.LayoutParams params = (AutoLinearLayout.LayoutParams) goodsListStatusBar.getLayoutParams();
        params.height = UIUtils.getStatusBarHeight(UIUtils.getContext());
        goodsListStatusBar.setLayoutParams(params);
    }

    @Override
    protected void initData() {
        reSouList = new ArrayList<>();
        reSouList.add("奶茶");
        reSouList.add("方便面");
        reSouList.add("槟榔");
        reSouList.add("辣条");
        reSouList.add("矿泉水");
        reSouList.add("哇哈哈");
        reSouList.add("可口可乐");
        flowlayoutRensou.setAdapter(new TagAdapter<String>(reSouList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = new TextView(SearchActivity.this);
                tv.setText(s);
                tv.setBackground(getResources().getDrawable(R.drawable.flower_bg));
                tv.setTextColor(getResources().getColor(R.color.flower_text_selector));
                tv.setPadding(20, 5, 20, 5);
                tv.setGravity(Gravity.CENTER);
                return tv;
            }
        });
        flowlayoutRensou.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                goodsListSearchView.setSearchContent(reSouList.get(position));
                return true;
            }
        });
    }


    /**
     * 查询搜索历史
     */

    public void getSearchHistory(String uId) {
       /* if (VersionUtil.getAppVersionCode(this) >= 78) {
            dbUtil = new DBUtil(this);
        }*/
        if (dbUtil == null) {
            dbUtil = new DBUtil(this);
        }
        /*List<SearchHistoryEntityWh> query = dbUtil.query(SearchHistoryEntityWh.class, uId);
        if (null == query) {
            Log.e("wh", "slfjlskdjf");
        } else {
            Log.e("wh", query.toString());
        }*/
        mSearchedHistories = dbUtil.query(SearchHistoryEntityWh.class, uId);
        if (mSearchedHistories != null) {
            StringUitl.removeDuplicate(mSearchedHistories);
            for (SearchHistoryEntityWh entity : mSearchedHistories) {  //循环改为限制10次,非ArrayList,不能用普通for限制次数
                sortSearchedHistories.add(entity);
            }
            Collections.sort(sortSearchedHistories);//排序
//            Log.e("wh","集合"+ mSearchedHistories.toString());
            int i = 0;
            for (SearchHistoryEntityWh entity : sortSearchedHistories) {
                i++;
                if (i == 11) {
                    break;
                } else {
                    value.add(entity.getU_search());

                }
                Log.e("whh", "id " + entity.getU_id() + " search " + entity.getU_search() + " time " + entity.getU_time());
            }
            flowlayout.setAdapter(new TagAdapter<String>(value) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = new TextView(SearchActivity.this);
                    tv.setText(s);
                    tv.setBackground(getResources().getDrawable(R.drawable.flower_bg));
                    tv.setTextColor(getResources().getColor(R.color.flower_text_selector));
                    tv.setPadding(20, 5, 20, 5);
                    tv.setGravity(Gravity.CENTER);
                    return tv;
                }
            });
            flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    goodsListSearchView.setSearchContent(value.get(position));
                    return true;
                }
            });
        }
    }

    @Override
    protected void setupActivityComponent() {
        DaggerSearchComponent
                .builder()
                .appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .searchModule(new SearchModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(SearchContract.SearchContractPresenter presenter) {
        mPresenter = (SearchPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @OnClick(R.id.rl_clear_search_history)
    public void onViewClicked() {
        mSearchedHistories.clear();
        dbUtil.clearData(SearchHistoryEntityWh.class);
        rlClearSearchHistory.setVisibility(View.GONE);
        value.clear();
        sortSearchedHistories.clear();
        flowlayout.setAdapter(new TagAdapter<String>(value) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = new TextView(SearchActivity.this);
                tv.setText(s);
                tv.setBackground(getResources().getDrawable(R.drawable.flower_bg));
                tv.setTextColor(getResources().getColor(R.color.flower_text_selector));
                tv.setPadding(20, 5, 20, 5);
                tv.setGravity(Gravity.CENTER);
                return tv;
            }
        });
        flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                goodsListSearchView.setSearchContent(value.get(position));
                return true;
            }
        });
        if (mSearchedHistories == null || mSearchedHistories.size() == 0) {
            rlClearSearchHistory.setVisibility(View.GONE);
        }
    }
}