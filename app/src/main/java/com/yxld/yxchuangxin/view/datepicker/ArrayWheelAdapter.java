package com.yxld.yxchuangxin.view.datepicker;


import android.content.Context;

/**
 * The simple Array wheel adapter
 * 
 * @param <T>
 *            the element type
 */
public class ArrayWheelAdapter<T> extends AbstractWheelTextAdapter implements WheelAdapter{

	/**榛樿item闀垮害*/
	public static final int DEFAULT_LENGTH = -1;
	
	/**items*/
	private T items[];
	
	/**length*/
	private int length;
	
	
	/**涓や釜鍙傛暟鐨勬瀯閫犲嚱鏁�/
	public ArrayWheelAdapter(Context context,T items[],int length){
		super(context);
		this.items = items;
		this.length = length;
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 *            the current context
	 * @param items
	 *            the items
	 */
	public ArrayWheelAdapter(Context context, T items[]) {
		super(context);
		// setEmptyItemResource(TEXT_VIEW_ITEM_RESOURCE);
		this.items = items;
	}
	
	
	

	@Override
	public CharSequence getItemText(int index) {
		if (index >= 0 && index < items.length) {
			T item = items[index];
			if (item instanceof CharSequence) {
				return (CharSequence) item;
			}
			return item.toString();
		}
		return null;
	}
	@Override
	public int getItemsCount() {
		return items.length;
	}
	
	@Override
	public String getItem(int index) {
		if(index >= 0 && index<items.length){
			return items[index].toString();
		}
		return null;
	}

	@Override
	public int getMaximumLength() {
		return length;
	}

}