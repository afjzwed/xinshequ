package com.yxld.yxchuangxin.ui.activity.rim.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.SJComplain;
import com.yxld.yxchuangxin.ui.activity.rim.contract.RimComplainListContract;
import com.yxld.yxchuangxin.ui.adapter.rim.RimComplainAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: presenter of RimComplainListActivity
 * @date 2017/06/16
 */
public class RimComplainListPresenter implements RimComplainListContract.RimComplainListContractPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final RimComplainListContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    public  RimComplainAdapter mAdapter;
    private List<SJComplain> sjComplainsList = new ArrayList<>();
//    private int lastUserId = 1;

    private int preEndIndex;

    @Inject
    public RimComplainListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull RimComplainListContract.View view) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
    }
    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
             mCompositeDisposable.dispose();
        }
    }

    @Override
    public void getComplanData(Map map,boolean pullToRefresh) {
        if (mAdapter == null) {
            mAdapter = new RimComplainAdapter(sjComplainsList);
            mView.setAdapter(mAdapter);//设置Adapter
        }

        if (pullToRefresh) {
            mView.showLoading();  //显示上拉刷新的进度条
        }else{
            mView.startLoadMore();//显示下拉加载更多的进度条
        }

        Disposable disposable = httpAPIWrapper.getRimComplain(map)
                .subscribe(new Consumer<SJComplain>() {
                    @Override
                    public void accept(SJComplain sjComplain) throws Exception {
//                        Log.d("geek", "accept: size"+sjComplain.getData().size());
//                        Log.d("geek", "accept: total"+sjComplain.getTotal());
                        mView.hideLoading();
                        //isSuccesse
                        if (pullToRefresh) sjComplainsList.clear();//如果是上拉刷新则清空列表

                        //加载更多
                        if(sjComplain.getTotal() <= sjComplainsList.size()){
                            mView.endLoadMore();
                        }else {
                            mView.startLoadMore();
                        }

                        preEndIndex = sjComplainsList.size();//更新之前列表总长度,用于确定加载更多的起始位置
                        sjComplainsList.addAll(sjComplain.getData());
                        if (pullToRefresh) {
                            mAdapter.notifyDataSetChanged();
                        } else {
                            mAdapter.notifyItemRangeInserted(preEndIndex, sjComplain.getData()
                                    .size());
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("getRimComplain onError");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //onMiaoshaComplete
                        KLog.i("getRimComplain onMiaoshaComplete");
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getRimComplainDetail(LinkedHashMap<String, String> data) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.getRimComplainDetail(data)
                .subscribe(new Consumer<SJComplain>() {
                    @Override
                    public void accept(SJComplain data) throws Exception {
                        mView.closeProgressDialog();
                        if (data.getSuccess()==1) {
                            //这里接收数据项
                            if (null == data.getData()||data.getData().size()==0) {//未投诉
                                mView.getRimComplainDetailFinish(false, null);
                            } else {//已投诉
                                mView.getRimComplainDetailFinish(true, data);
                            }
                            KLog.i("成功的回调" + data.toString());
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.closeProgressDialog();
                        //这里接收onError
                        KLog.i("onError的回调");
//                        mView.getRimComplainDetailFinish("确认订单失败",false);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.closeProgressDialog();
                        //这里接收onComplete。
                        KLog.i("run的回调");
                    }
                });
        mCompositeDisposable.add(disposable);
    }

}