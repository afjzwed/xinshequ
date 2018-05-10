package com.yxld.yxchuangxin.view.datepicker;

/**滚轮适配器接口*/
public interface WheelAdapter {
	/**
	 * 得到items的数目
	 * @return item的数目
	 */
    int getItemsCount();
	
	/**
	 * 通过index得到对应的item
	 * @param index item的index
	 * @return item或者null
	 */
    String getItem(int index);
	
	
	/**
	 * 得到item的最大长度，用来决定滚轮的宽度
	 * 若返回-1，则将使用默认滚轮宽度
	 * @return
	 */
    int getMaximumLength();

}
