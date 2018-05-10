package com.yxld.yxchuangxin.view.sinaview;

/**
 * Created by HanHailong on 16/2/18.
 */
public interface PopMenuItemListener {
    /**
     * Item点击事件
     *
     * @param popMenu
     * @param position
     */
    public void onItemClick(PopMenu popMenu, int position);
    public void onhideOver(PopMenu popMenu, int position);
}
