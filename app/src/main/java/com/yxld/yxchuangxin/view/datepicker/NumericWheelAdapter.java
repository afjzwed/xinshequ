package com.yxld.yxchuangxin.view.datepicker;


import android.content.Context;

import java.util.ArrayList;

/**
 * Numeric Wheel adapter.
 */
public class NumericWheelAdapter extends AbstractWheelTextAdapter implements WheelAdapter {

	/** The default min value */
	public static final int DEFAULT_MAX_VALUE = 5;

	/** The default max value */
	private static final int DEFAULT_MIN_VALUE = 0;

	// Values
	private int minValue;
	private int maxValue;

	// format
	private String format;

	private ArrayList<String> list;

	/**
	 * Constructor
	 *
	 * @param context
	 *            the current context
	 */
	public NumericWheelAdapter(Context context) {
		this(context, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
	}

	/**
	 * Constructor
	 *
	 * @param context
	 *            the current context
	 * @param minValue
	 *            the wheel min value
	 * @param maxValue
	 *            the wheel max value
	 */
	public NumericWheelAdapter(Context context, int minValue, int maxValue) {
		this(context, minValue, maxValue, null);
	}

	/**
	 * Constructor
	 *
	 * @param context
	 *            the current context
	 * @param minValue
	 *            the wheel min value
	 * @param maxValue
	 *            the wheel max value
	 * @param format
	 *            the format string
	 */
	public NumericWheelAdapter(Context context, int minValue, int maxValue, String format) {
		super(context);

		this.minValue = minValue;
		this.maxValue = maxValue;
		this.format = format;
	}
	public NumericWheelAdapter(Context context, int minValue, int maxValue, String format, ArrayList<String> list) {
		super(context);
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.format = format;
		this.list = list;
	}

	@Override
	public CharSequence getItemText(int index) {
		if (list != null) {
			if (index >= 0 && index < getItemsCount()) {
				int value = minValue + index;
				return list.get(index);
			}
		}

		if (index >= 0 && index < getItemsCount()) {
			int value = minValue + index;
			return format != null ? String.format(format, value) : Integer
					.toString(value);
		}
		return null;
	}

	public int getItemsCount() {
		return maxValue - minValue + 1;
	}

	@Override
	public String getItem(int index) {
		if (list != null) {
			return list.get(index);
		}
		if (index >= 0 && index < getItemsCount()) {
			int value = minValue + index;
			return format != null ? String.format(format, value) : Integer.toString(value);
		}
		return null;
	}

	@Override
	public int getMaximumLength() {
		int max = Math.max(Math.abs(maxValue), Math.abs(minValue));
		int maxLen = Integer.toString(max).length();
		if (minValue < 0) {
			maxLen++;
		}
		return maxLen;
	}
}