package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.AppYezhuFangwu;
import com.yxld.yxchuangxin.entity.BaseBack2;
import com.yxld.yxchuangxin.entity.MenJinShareMemberBean;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.MenJinShareMemberContract;
import com.yxld.yxchuangxin.ui.activity.wuye.MenJinShareMemberActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author xlei
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of MenJinShareMemberActivity
 * @date 2018/06/11 14:53:04
 */
public class MenJinShareMemberPresenter implements MenJinShareMemberContract.MenJinShareMemberContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private final MenJinShareMemberContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private MenJinShareMemberActivity mActivity;
    private List<AppYezhuFangwu> mAppYezhuFangwuList;
    private List<MenJinShareMemberBean> mMenJinShareMemberBeanList;

    @Inject
    public MenJinShareMemberPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MenJinShareMemberContract.View
            view, MenJinShareMemberActivity activity) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.mActivity = activity;
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
    public void getAllLiveMember(Map map) {
        Disposable disposable = httpAPIWrapper.getAllLiveMember(map).subscribe(new Consumer<AppYezhuFangwu>() {
            @Override
            public void accept(AppYezhuFangwu data) throws Exception {
                //isSuccesse
                KLog.i("onSuccesse");
                //  mView.setMember(data);
                if (data.status == 0) {
                    if (data != null && data.getRows() != null) {
                        mAppYezhuFangwuList = new ArrayList<AppYezhuFangwu>();
                        mAppYezhuFangwuList.clear();
                        if (Contains.yeZhuVo != null) {
                            AppYezhuFangwu appYezhuFangwu = new AppYezhuFangwu();
                            appYezhuFangwu.setFwDanyuan(Contains.yeZhuVo.getYezhu_name());
                            appYezhuFangwu.setFwLoudong(Contains.yeZhuVo.getYezhu_shouji());
                            appYezhuFangwu.setFwFanghao("******************");
                            appYezhuFangwu.setFwId(Contains.yeZhuVo.getFwyzType());
                            appYezhuFangwu.setYezhuId(Contains.yeZhuVo.getYezhu_id());
                            appYezhuFangwu.setSex(Integer.parseInt(Contains.yeZhuVo.getYezhu_sex()));
                            mAppYezhuFangwuList.add(appYezhuFangwu);
                        }
                        mAppYezhuFangwuList.addAll(data.getRows());

                    }
                    mView.getMembered();
                    //   mView.setMember(checkList(mAppYezhuFangwuList, mMenJinShareMemberBeanList));
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //onError
                KLog.i("onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //onComplete
                KLog.i("onComplete");
            }
        });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getDoorShareMember(Map map) {
        Disposable disposable = httpAPIWrapper.getDoorShareMember(map).subscribe(new Consumer<MenJinShareMemberBean>() {
            @Override
            public void accept(MenJinShareMemberBean data) throws Exception {
                //isSuccesse
                KLog.i("onSuccesse");
                // mView.setMember(data);
                if (data != null && data.getData() != null) {
                    mMenJinShareMemberBeanList = data.getData();
                }
                mView.setMember(checkList(mAppYezhuFangwuList, mMenJinShareMemberBeanList));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //onError
                KLog.i("onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //onComplete
                KLog.i("onComplete");
            }
        });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void saveDoorShareMember(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.saveDoorShareMember(map).subscribe(new Consumer<BaseEntity>() {
            @Override
            public void accept(BaseEntity data) throws Exception {
                //isSuccesse
                KLog.i("onSuccesse");
                // mView.setMember(data);
                mView.closeProgressDialog();
                mView.saveSuccess(data);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //onError
                KLog.i("onError");
                mView.closeProgressDialog();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //onComplete
                KLog.i("onComplete");
            }
        });
        mCompositeDisposable.add(disposable);
    }

    /**
     * 比对数据 重新组装
     *
     * @param list1
     * @param list2
     * @return
     */

    private List<AppYezhuFangwu> checkList(List<AppYezhuFangwu> list1, List<MenJinShareMemberBean> list2) {
        if (list1 == null || list2 == null) {
            return null;
        }
        for (int i = 0; i < list1.size(); i++) {
            for (int i1 = 0; i1 < list2.size(); i1++) {
                if (list1.get(i).getYezhuId() == list2.get(i1).getYezhuId()) {
                    list1.get(i).setMenjinSave(true);
                }
            }
        }
        return list1;
    }
}