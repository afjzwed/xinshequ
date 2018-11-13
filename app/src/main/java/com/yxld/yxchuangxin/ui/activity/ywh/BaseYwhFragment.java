package com.yxld.yxchuangxin.ui.activity.ywh;

import com.orhanobut.logger.Logger;
import com.yxld.yxchuangxin.base.BaseFragment;

/**
 * Created by Administrator on 2018/11/12.
 */

public abstract class BaseYwhFragment extends BaseFragment {
    //Fragment的View加载完毕的标记
    public boolean isViewCreated;

    //Fragment对用户可见的标记
    public boolean isUIVisible;

    protected abstract void setupFragmentComponent();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        if (isViewCreated && isUIVisible) {
            initDataFromLocal();
            isViewCreated = false;
            isUIVisible = false;
            Logger.i("可见,加载数据");
        }
    }
}
