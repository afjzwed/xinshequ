package com.yxld.yxchuangxin.ui.adapter.wuye;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Anroid on 2017/3/20.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) %2==0) {
            outRect.left = space;
            outRect.right = space / 2;
        } else {
            outRect.left = space / 2;
            outRect.right = space;
        }
//        if (parent.getChildLayoutPosition(view) - 1 == parent.getChildCount()) {
//            outRect.left = 0;
//            outRect.right = space / 2;
//            outRect.bottom = space;
//        }
//        if (parent.getChildLayoutPosition(view) == parent.getChildCount()) {
//            outRect.left = space / 2;
//            outRect.right = 0;
//            outRect.top = space;
//        }
    }
}
