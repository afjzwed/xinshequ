package com.yxld.yxchuangxin.ui.adapter.camera;

import android.support.annotation.IdRes;
import android.widget.RadioGroup;

/**
 * 作者：Android on 2017/9/7
 * 邮箱：365941593@qq.com
 * 描述：
 */

public interface CheckChangeListener {
    void onCheckChange(RadioGroup radioGroup, @IdRes int checkedId, String mac);
}
