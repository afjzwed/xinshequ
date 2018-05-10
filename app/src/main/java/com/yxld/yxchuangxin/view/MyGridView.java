package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @ClassName: MyGridView 
 * @Description: 自定义无滑动gridview
 * @author wwx
 * @date 2016年3月9日 下午3:00:08 
 */
public class MyGridView extends GridView {
	 public MyGridView(Context context, AttributeSet attrs) {   
	        super(context, attrs);   
	    }   
	  
	    public MyGridView(Context context) {   
	        super(context);   
	    }   
	  
	    public MyGridView(Context context, AttributeSet attrs, int defStyle) {   
	        super(context, attrs, defStyle);   
	    }   
	    @Override   
	    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {   
	        int expandSpec = MeasureSpec.makeMeasureSpec(   
	                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);   
	        super.onMeasure(widthMeasureSpec, expandSpec);   
	    }   
}
