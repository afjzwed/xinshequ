package com.yxld.yxchuangxin.ui.activity.camera;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemChildLongClickListener;
import com.p2p.core.P2PHandler;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.entity.AppCameraList;
import com.yxld.yxchuangxin.entity.DefenceStates;
import com.yxld.yxchuangxin.ui.activity.camera.component.DaggerDeviceListComponent;
import com.yxld.yxchuangxin.ui.activity.camera.contract.DeviceListContract;
import com.yxld.yxchuangxin.ui.activity.camera.module.DeviceListModule;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.DeviceListPresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.CameraListAdapter;
import com.yxld.yxchuangxin.ui.adapter.camera.MyItemDecoration;
import com.yxld.yxchuangxin.view.ConfirmDialog;
import com.yxld.yxchuangxin.yoosee.FriendStatus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: $description
 * @date 2017/09/04 15:11:28
 */

public class DeviceListFragment extends BaseFragment implements DeviceListContract.View {

    @Inject
    CameraListAdapter cameraListAdapter;

    @Inject
    DeviceListPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private View notDataView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device_list, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        Bundle mBundle = getArguments();
        notDataView = getActivity().getLayoutInflater().inflate(R.layout.layout_empty_data, (ViewGroup) recyclerView.getParent(), false);
        cameraListAdapter.setEmptyView(notDataView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new MyItemDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cameraListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (view.getId()) {
                    case R.id.camera_image:
                        Intent device = new Intent(getActivity(), CameraActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("deviceId", cameraListAdapter.getData().get(i).getSb_ipc_id());
                        bundle.putString("devicePwd", cameraListAdapter.getData().get(i).getSb_ipc_pwd());
                        bundle.putString("deviceName", cameraListAdapter.getData().get(i).getSb_name());
                        bundle.putString("defenceState", cameraListAdapter.getData().get(i).getDefenceState() + "");
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
                        Intent video = new Intent(getActivity(), RecordFilesActivity.class);
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("deviceId", cameraListAdapter.getData().get(i).getSb_ipc_id());
                        bundle1.putString("devicePwd", cameraListAdapter.getData().get(i).getSb_ipc_pwd());
                        video.putExtras(bundle1);
                        startActivity(video);
                        break;
                    case R.id.share:
                        Intent share = new Intent(getActivity(), CameraInformActivity.class);
                        share.putExtra("deviceId", cameraListAdapter.getData().get(i).getSb_ipc_id());
                        startActivity(share);
                        break;
                }
            }
        });
        recyclerView.setAdapter(cameraListAdapter);
        recyclerView.addOnItemTouchListener(LongClickListener);
//        recyclerView.addOnItemTouchListener(ClickListener);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getAllCamera();
            }
        });
        return view;
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerDeviceListComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .deviceListModule(new DeviceListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        P2PHandler.getInstance().p2pDisconnect();
        mPresenter.Login();
        if (isVisible) {

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void GetDefenceStates(DefenceStates defenceStates) {
        mPresenter.getDefenceStates(defenceStates);
    }

    //获取设备状态
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void DeviceStatus(FriendStatus msg) {
        mPresenter.DeviceStatus(msg);
    }

    @Override
    public void setPresenter(DeviceListContract.DeviceListContractPresenter presenter) {
        mPresenter = (DeviceListPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        refreshLayout.setRefreshing(false);
        progressDialog.hide();
    }

    @Override
    public void setCameraList(AppCameraList list) {
        cameraListAdapter.setNewData(list.getData());
    }

    @Override
    public void deletOne(int position) {
        cameraListAdapter.getData().remove(position);
        cameraListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //item长按事件
    private OnItemChildLongClickListener LongClickListener = new OnItemChildLongClickListener() {
        @Override
        public void onSimpleItemChildLongClick(BaseQuickAdapter adapter, View view, final int position) {
            showDeletCameraDalog(position);
        }
    };

    private void showDeletCameraDalog(int position) {
        ConfirmDialog.showDialog(getActivity(), "提示", "删除摄像头", new ConfirmDialog.OnConfirmListener() {
            @Override
            public void onCancel() {

            }

            @Override
            public void onConfirm() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("sb_ipc_id", cameraListAdapter.getData().get(position).getSb_ipc_id());//设备ID
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
                    Intent device = new Intent(getActivity(), CameraActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("deviceId", listAdapter.getData().get(position).getSb_ipc_id());
                    bundle.putString("devicePwd", listAdapter.getData().get(position).getSb_ipc_pwd());
                    bundle.putString("deviceName", listAdapter.getData().get(position).getSb_name());
                    bundle.putString("defenceState", listAdapter.getData().get(position).getDefenceState() + "");
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
                    Intent video = new Intent(getActivity(), RecordFilesActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("deviceId", listAdapter.getData().get(position).getSb_ipc_id());
                    bundle1.putString("devicePwd", listAdapter.getData().get(position).getSb_ipc_pwd());
                    video.putExtras(bundle1);
                    startActivity(video);
                    break;
                case R.id.share:
                    Intent share = new Intent(getActivity(), CameraInformActivity.class);
                    share.putExtra("deviceId", listAdapter.getData().get(position).getSb_ipc_id());
                    startActivity(share);
                    break;
            }

        }
    };
}