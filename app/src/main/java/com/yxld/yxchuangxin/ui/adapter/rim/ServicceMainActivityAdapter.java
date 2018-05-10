package com.yxld.yxchuangxin.ui.adapter.rim;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.AndroidWuYeEntity;

import java.util.List;

/**
 * @ClassName: ServicceMainActivityAdapter
 * @Description: 社区助手主界面适配器
 * @author wwx
 * @date 2016年4月5日 下午2:40:00 
 *
 */
@SuppressLint("InflateParams")
public class ServicceMainActivityAdapter extends BaseAdapter {
	private List<AndroidWuYeEntity> mList;
	Context mContext;
	private int tags;

	public ServicceMainActivityAdapter(List<AndroidWuYeEntity> list, Context context, int tag) {
		this.mList = list;
		this.mContext = context;
		this.tags = tag;
	}

	// 是获取显示数据的数量
	@Override
	public int getCount() {
		return mList.size();
	}

	// 获得当前位置的元素
	@Override
	public Object getItem(int arg0) {
		return mList.get(arg0);
	}

	// 获取当前控件的id号
	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	// 创建list里面子元素
	@SuppressWarnings("deprecation")
	@Override
	public View getView(final int position, // 当前要构造的位置
						View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			// 视图构造器
			LayoutInflater lif = LayoutInflater.from(mContext);
			// 构造视图
			view = lif.inflate(R.layout.activity_main_item, null);
			holder.menuName = (TextView) view.findViewById(R.id.main_name);
			holder.menuSrc = (ImageView) view.findViewById(R.id.main_icon);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// 填充数据
		final AndroidWuYeEntity sb = mList.get(position);
		// 填充控件
		holder.menuName.setText(sb.getMenuName());
		holder.menuSrc.setImageResource(sb.getMenuSrc());
		return view;
	}

	class ViewHolder {
	    TextView menuName;
		ImageView menuSrc;
	}

	public List<AndroidWuYeEntity> getmList() {
		return mList;
	}

	public void setmList(List<AndroidWuYeEntity> mList) {
		this.mList = mList;
		notifyDataSetChanged();
	}

	/**
	 * @param tag 二级菜单
	 * @param position
     */
	private void jumpActivity(int tag ,int position){
		Toast.makeText(mContext,"敬请期待",Toast.LENGTH_SHORT).show();
	}

	/**
	 * 启动Activity
	 *
	 * @param <T>
	 * @param clazz
	 */
	protected <T> void startActivity(Class<T> clazz) {
		Intent intent = new Intent(mContext, clazz);
		try {
			mContext.startActivity(intent);
		} catch (Exception e) {
			Toast.makeText(mContext, "敬请期待！"+ clazz.getSimpleName()
					+ "未注册！",Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 启动Activity
	 *
	 * @param clazz
	 */
	protected <T> void startActivity(Class<T> clazz, Bundle bundle) {
		Intent intent = new Intent(mContext, clazz);
		intent.putExtras(bundle);
		try {
			mContext.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(mContext, "敬请期待！"+ clazz.getSimpleName()
					+ "未注册！",Toast.LENGTH_SHORT).show();
		}
	}
}