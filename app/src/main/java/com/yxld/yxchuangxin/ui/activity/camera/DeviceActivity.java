package com.yxld.yxchuangxin.ui.activity.camera;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemChildLongClickListener;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.SpUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseActivity;
import com.yxld.yxchuangxin.contain.ContainValue;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.entity.AppCameraList;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerDeviceComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.DeviceContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.DeviceModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.DevicePresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.CameraListAdapter;
import com.yxld.yxchuangxin.view.CameraDialog;
import com.yxld.yxchuangxin.view.ConfirmDialog;
import com.zaaach.toprightmenu.TopRightMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yxld.yxchuangxin.contain.ContainValue.ISSHOWXIEYI;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description
 * @date 2017/06/20 17:26:32
 */

public class DeviceActivity extends BaseActivity implements DeviceContract.View {

    @Inject
    DevicePresenter mPresenter;

//    @Inject
//    CameraListAdapter cameraListAdapter;

    //    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
//    @BindView(R.id.refreshLayout)
//    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Menu menu;
    private TopRightMenu mTopRightMenu;
    private View notDataView;

    int currentPage = 0;
    private boolean isShow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_device);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        EventBus.getDefault().register(this);
//        notDataView = getLayoutInflater().inflate(R.layout.layout_empty_data, (ViewGroup) recyclerView.getParent(),
// false);
//        cameraListAdapter.setEmptyView(notDataView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new MyItemDecoration());
//        recyclerView.setLayoutManager(new LinearLayoutManager(DeviceActivity.this));
//        recyclerView.setAdapter(cameraListAdapter);
//        recyclerView.addOnItemTouchListener(LongClickListener);
//        recyclerView.addOnItemTouchListener(ClickListener);
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mPresenter.getAllCamera();
//            }
//        });
        if (ISSHOWXIEYI) {
            showXieyi();
        }
    }

    @Override
    protected void initData() {
        ArrayList<String> titles = new ArrayList<>();
        titles.add("报警器");
        titles.add("摄像头");
        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    AlarmListFragment fragment = new AlarmListFragment();
                    Bundle bundle = new Bundle();
                    fragment.setArguments(bundle);
                    return fragment;
                } else {
                    DeviceListFragment fragment = new DeviceListFragment();
                    Bundle bundle = new Bundle();
                    fragment.setArguments(bundle);
                    return fragment;
                }
            }

            @Override
            public int getCount() {
                return titles.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }

        };
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                KLog.i(position);
                currentPage = position;
                if (menu != null) {
                    onCreateOptionsMenu(menu);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onResume() {
//        P2PHandler.getInstance().p2pDisconnect();
//        mPresenter.Login();

        super.onResume();
    }

//    //获取设备状态
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void DeviceStatus(FriendStatus msg) {
//        mPresenter.DeviceStatus(msg);
//    }

    @Override
    protected void setupActivityComponent() {
        DaggerDeviceComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .deviceModule(new DeviceModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(DeviceContract.DeviceContractPresenter presenter) {
        mPresenter = (DevicePresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
//        refreshLayout.setRefreshing(false);
        progressDialog.hide();
    }

    @Override
    public void setCameraList(AppCameraList list) {
//        cameraListAdapter.setNewData(list.getData());
    }

    @Override
    public void deletOne(int position) {
//        cameraListAdapter.getData().remove(position);
//        cameraListAdapter.notifyDataSetChanged();
    }

    // TODO: 2018/1/12 注释掉添加摄像头的入口 改到在欣助手添加
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        this.menu = menu;
        if (currentPage == 0) {
            // getMenuInflater().inflate(R.menu.menu_xiangce, menu);
        } else {
            // getMenuInflater().inflate(R.menu.menu_camera_device, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View tianjia = findViewById(R.id.add);
        switch (item.getItemId()) {
//            case R.id.add:
//                CameraDialog.showDialog(DeviceActivity.this, new CameraDialog.OnAutoListener() {
//                    @Override
//                    public void onAutoOne() {
//                        Intent config = new Intent(DeviceActivity.this, CameraConfigActivity.class);
//                        startActivity(config);
//                    }
//
//                    @Override
//                    public void onAutoTwo() {
//                        Intent add = new Intent(DeviceActivity.this, CameraAddActivity.class);
//                        add.putExtra("ishasContactId", false);
//                        startActivity(add);
//                    }
//                });
//            break;
             /*   mTopRightMenu = new TopRightMenu(DeviceActivity.this);
                mTopRightMenu
                        .setHeight(RecyclerView.LayoutParams.WRAP_CONTENT)     //默认高度480
                        .setWidth(RecyclerView.LayoutParams.WRAP_CONTENT)      //默认宽度wrap_content
//                        .showIcon(true)     //显示菜单图标，默认为true
                        .dimBackground(true)           //背景变暗，默认为true
                        .needAnimationStyle(true)   //显示动画，默认为true
                        .setAnimationStyle(R.style.TRM_ANIM_STYLE)  //默认为R.style.TRM_ANIM_STYLE
                        .addMenuItem(new com.zaaach.toprightmenu.MenuItem(R.mipmap.icon_peiwang, "设备配网"))
                        .addMenuItem(new com.zaaach.toprightmenu.MenuItem(R.mipmap.icon_add_device, "添加设备"))
                        .setOnMenuItemClickListener(new TopRightMenu.OnMenuItemClickListener() {
                            @Override
                            public void onMenuItemClick(int position) {
                                switch (position) {
                                    case 0:
                                        Intent config = new Intent(DeviceActivity.this, CameraConfigActivity.class);
                                        startActivity(config);
                                        break;
                                    case 1:
                                        Intent add = new Intent(DeviceActivity.this, CameraAddActivity.class);
                                        add.putExtra("ishasContactId", false);
                                        startActivity(add);
                                        break;
                                }
                            }
                        })
                        .showAsDropDown(tianjia, 0, 0);*/

            case android.R.id.home:
                finish();
                System.gc();
                break;
//            case R.id.xieyi:
//                showXieyi();
//                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showXieyi() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_xieyi, null);
        TextView confirm = (TextView) view.findViewById(R.id.confirm);
        CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);
        checkbox.setChecked(SpUtil.getBoolean(DeviceActivity.this, ContainValue.NOSHOWXIEYI, false));
        WebView webView = (WebView) view.findViewById(R.id.webview);
//        webView.loadDataWithBaseURL("about:blank", "协议内容哈哈哈哈哈哈哈哈", "text/html", "utf-8", null);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDefaultFontSize(16);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setDefaultTextEncodingName("UTF -8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.loadUrl(API.URL_XIEYI);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                SpUtil.putBoolean(DeviceActivity.this, ContainValue.NOSHOWXIEYI, isChecked);
                if (isChecked){
                    ISSHOWXIEYI = false;
                }else {
                    ISSHOWXIEYI = true;
                }
            }
        });
        ISSHOWXIEYI = false;
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
//        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    //item长按事件
    private OnItemChildLongClickListener LongClickListener = new OnItemChildLongClickListener() {
        @Override
        public void onSimpleItemChildLongClick(BaseQuickAdapter adapter, View view, final int position) {
            showDeletCameraDalog(position);
//            positions = position;
//            if (cameraController == null) {
//                cameraController = new CameraControllerImpl();
//            }
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("sb_ipc_id", list.get(position).getSb_ipc_id());//设备ID
//            map.put("apptoken", Contains.uuid);//用户TOKEN
//            cameraController.getCameraDel(mRequestQueue, map, dellistener);
        }
    };

    private void showDeletCameraDalog(int position) {
        ConfirmDialog.showDialog(this, "提示", "删除摄像头", new ConfirmDialog.OnConfirmListener() {
            @Override
            public void onCancel() {

            }

            @Override
            public void onConfirm() {
                Map<String, String> map = new HashMap<String, String>();
//                map.put("sb_ipc_id", cameraListAdapter.getData().get(position).getSb_ipc_id());//设备ID
                map.put("apptoken", Contains.uuid);//用户TOKEN
                mPresenter.deletCamera(map, position);
            }
        });
    }

    ;

    //item点击事件
    private OnItemChildClickListener ClickListener = new OnItemChildClickListener() {
        @Override
        public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            CameraListAdapter listAdapter = (CameraListAdapter) adapter;
            switch (view.getId()) {
                case R.id.camera_image:
                    Intent device = new Intent(DeviceActivity.this, CameraActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("deviceId", listAdapter.getData().get(position).getSb_ipc_id());
                    bundle.putString("devicePwd", listAdapter.getData().get(position).getSb_ipc_pwd());
                    bundle.putString("deviceName", listAdapter.getData().get(position).getSb_name());
                    bundle.putString("deviceId", listAdapter.getData().get(position).getSb_ipc_id());
                    bundle.putString("devicePwd", listAdapter.getData().get(position).getSb_ipc_pwd());
                    bundle.putString("deviceName", listAdapter.getData().get(position).getSb_name());
                    device.putExtras(bundle);
                    startActivity(device);
                    break;
                case R.id.camera_video:
//                    Intent video = new Intent(DeviceActivity.this, RecordFilesActivity.class);
//                    Bundle bundle1 = new Bundle();
//                    bundle1.putString("deviceId", adapter.getData().get(position).getSb_ipc_id());
//                    bundle1.putString("devicePwd", adapter.getData().get(position).getSb_ipc_pwd());
//                    video.putExtras(bundle1);
//                    startActivity(video);
                    Intent video = new Intent(DeviceActivity.this, RecordFilesActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("deviceId", listAdapter.getData().get(position).getSb_ipc_id());
                    bundle1.putString("devicePwd", listAdapter.getData().get(position).getSb_ipc_pwd());
                    video.putExtras(bundle1);
                    startActivity(video);
                    break;
            }

        }
    };
}