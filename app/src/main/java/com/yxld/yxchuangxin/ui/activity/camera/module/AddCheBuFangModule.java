package com.yxld.yxchuangxin.ui.activity.camera.module;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.FangquList;
import com.yxld.yxchuangxin.ui.activity.base.ActivityScope;
import com.yxld.yxchuangxin.ui.activity.camera.AddCheBuFangActivity;
import com.yxld.yxchuangxin.ui.activity.camera.contract.AddCheBuFangContract;
import com.yxld.yxchuangxin.ui.activity.camera.presenter.AddCheBuFangPresenter;
import com.yxld.yxchuangxin.ui.adapter.camera.CheckFangQuAdapter;
import com.yxld.yxchuangxin.ui.adapter.camera.SevenDayAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author hzp
 * @Package com.yxld.yxchuangxin.ui.activity.camera
 * @Description: The moduele of AddCheBuFangActivity, provide field for AddCheBuFangActivity
 * @date 2017/09/05 18:10:45
 */
@Module
public class AddCheBuFangModule {
    private final AddCheBuFangContract.View mView;


    public AddCheBuFangModule(AddCheBuFangContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    public AddCheBuFangPresenter provideAddCheBuFangPresenter(HttpAPIWrapper httpAPIWrapper, AddCheBuFangActivity mActivity) {
        return new AddCheBuFangPresenter(httpAPIWrapper, mView, mActivity);
    }

    @Provides
    @ActivityScope
    public AddCheBuFangActivity provideAddCheBuFangActivity() {
        return (AddCheBuFangActivity) mView;
    }

    @Provides
    @ActivityScope
    public List<SevenDayAdapter.xingqi> provideList() {
        List<SevenDayAdapter.xingqi> list = new ArrayList<>();
        list.add(new SevenDayAdapter.xingqi("星期一"));
        list.add(new SevenDayAdapter.xingqi("星期二"));
        list.add(new SevenDayAdapter.xingqi("星期三"));
        list.add(new SevenDayAdapter.xingqi("星期四"));
        list.add(new SevenDayAdapter.xingqi("星期五"));
        list.add(new SevenDayAdapter.xingqi("星期六"));
        list.add(new SevenDayAdapter.xingqi("星期天"));
        return list;
    }

    @Provides
    @ActivityScope
    public SevenDayAdapter provideSevenDayAdapter(List<SevenDayAdapter.xingqi> list) {
        return new SevenDayAdapter(list);
    }

    @Provides
    @ActivityScope
    public List<FangquList> provideCheckFangquEntity() {
//        List<FangquList> list = new CheckFangquEntity().getFangQuList();
//        for (int i = 0; i < Contains.fangquEntity.getData().size(); i++) {
//            for (int j = 0; j < list.size(); j++) {
//                if (Contains.fangquEntity.getData().get(i).getShebeiFangquLeixin().equals(list.get(j).getFangQuName())) {
//                    list.get(j).getFangQuList().add(Contains.fangquEntity.getData().get(i));
//                    continue;
//                }
//                if (j == list.size() - 1) {
//                    FangquList fangquList = new FangquList();
//                    fangquList.setFangQuName(Contains.fangquEntity.getData().get(i).getShebeiFangquLeixin());
//                    FangquEntity.DataBean dataBean = Contains.fangquEntity.getData().get(i);
//                    List<FangquEntity.DataBean> list1 = new ArrayList<>();
//                    list1.add(dataBean);
//                    fangquList.setFangQuList(list1);
//                }
//            }
//        }
        return new ArrayList<FangquList>();
    }

    @Provides
    @ActivityScope
    public CheckFangQuAdapter provideCheckFangQuAdapter(List<FangquList> list) {
//        List<FangquList> list = new ArrayList<>();
//        for (int i = 0; i < Contains.fangquEntity.getData().size(); i++) {
//            for (int j = 0; j < list.size(); j++) {
//                if (Contains.fangquEntity.getData().get(i).getShebeiFangquLeixin().equals(list.get(j).getFangQuName())) {
//                    list.get(j).getFangQuList().add(Contains.fangquEntity.getData().get(i));
//                    KLog.i("有了，添加就行");
//                    continue;
//                }
//                if (j == list.size() - 1) {
//                    KLog.i("没有，需要new一个");
//                    FangquList fangquList = new FangquList();
//                    fangquList.setFangQuName(Contains.fangquEntity.getData().get(i).getShebeiFangquLeixin());
//                    FangquEntity.DataBean dataBean = Contains.fangquEntity.getData().get(i);
//                    List<FangquEntity.DataBean> list1 = new ArrayList<>();
//                    list1.add(dataBean);
//                    fangquList.setFangQuList(list1);
//                    list.add(fangquList);
//                }
//            }
//        }
        return new CheckFangQuAdapter(list);
    }
}