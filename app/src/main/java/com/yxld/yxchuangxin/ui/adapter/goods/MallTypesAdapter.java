package com.yxld.yxchuangxin.ui.adapter.goods;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.adapter.Wuye;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/15
 * @descprition:
 */

public class MallTypesAdapter extends RecyclerView.Adapter<MallTypesAdapter.BaseTypeViewHolder> {
    public static final int TYPE_NORAML = 0;//普通
    public static final int TYPE_PICKED = 1;//精选
    private int mType;
    private LayoutInflater mInflater;
    private List<Wuye.DataBean.ListBean> mListBeans;
    private Context mContext;
    private List<MallPickTypeBean> mPickTypeBeans;
    public MallTypesAdapter(Context context,int type,List<Wuye.DataBean.ListBean> beans) {
        this.mType = type;
        this.mInflater= LayoutInflater.from(context);
        this.mContext = context;
        this.mListBeans = beans;
    }

    public MallTypesAdapter(Context context,List<MallPickTypeBean> mallPickTypeBeans){
        this.mContext = context;
        this.mInflater= LayoutInflater.from(context);
        this.mPickTypeBeans = mallPickTypeBeans;
        this.mType = TYPE_PICKED;

    }

    @Override
    public BaseTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        BaseTypeViewHolder holder = null;
        if(mType == TYPE_NORAML){
            holder = new MyNormalTypeViewHolder(mInflater.inflate(R.layout.item_mall_normal_types,parent,false));
        }else {
            holder = new MyPickedTypeViewHolder(mInflater.inflate(R.layout.item_mall_picked,parent,false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseTypeViewHolder holder, int position) {
        if(holder instanceof MyNormalTypeViewHolder){
            MyNormalTypeViewHolder normalHolder = (MyNormalTypeViewHolder) holder;
            handlerNormalType(normalHolder,position);
        }else if(holder instanceof MyPickedTypeViewHolder){
            MyPickedTypeViewHolder pickedHolder = (MyPickedTypeViewHolder) holder;
            handlerPickedType(pickedHolder,position);
        }
    }


    private void handlerPickedType(MyPickedTypeViewHolder holder,int position){
        MallPickTypeBean bean = mPickTypeBeans.get(position);
        Log.e("Adapter",bean.toString());
        holder.llRoot.setBackgroundColor(bean.bgColor);

        holder.tvBigTitle.setText(bean.bigTitle);
        holder.tvBigTitle.setTextColor(bean.bigTitleColor);

        holder.tvSmallTitle.setText(bean.smallTitle);
        holder.tvSmallTitle.setTextColor(bean.smallTitleColor);

        Glide.with(mContext)
                .load(bean.imgResId)
                .error(R.mipmap.scsy_hd01)
                .into(holder.ivPicked);
    }


    private void  handlerNormalType(MyNormalTypeViewHolder normalHolder,int position){
        Wuye.DataBean.ListBean bean = mListBeans.get(position);
        Glide.with(mContext)
                .load(mContext.getResources().getIdentifier(bean.getIcon(),"mipmap",mContext.getPackageName()))
                .into(normalHolder.iv);
        normalHolder.tv.setText(bean.getName());

        normalHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.onClick(normalHolder.itemView,normalHolder.getLayoutPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mType == TYPE_NORAML)
            return mListBeans.size();
        else
            return mPickTypeBeans.size();
    }


    public void release(){
        this.mContext = null;
        this.mListener = null;
        this.mListBeans.clear();
        this.mListBeans = null;
        this.mInflater = null;
    }

    private OnRecycleItemClickListener mListener;
    public void setOnItemClickListener(OnRecycleItemClickListener listener){
        this.mListener = listener;
    }


    public static class BaseTypeViewHolder extends RecyclerView.ViewHolder{

        public BaseTypeViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class MyNormalTypeViewHolder extends BaseTypeViewHolder{
        ImageView iv;
        TextView tv;
        public MyNormalTypeViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_mall_normal_type);
            tv = (TextView) itemView.findViewById(R.id.tv_mall_normal_type);
        }
    }

    public static class MyPickedTypeViewHolder extends BaseTypeViewHolder{
        TextView tvBigTitle;
        TextView tvSmallTitle;
        AutoRelativeLayout llRoot;
        ImageView ivPicked;
        public MyPickedTypeViewHolder(View itemView) {
            super(itemView);
            tvBigTitle = (TextView) itemView.findViewById(R.id.tv_mall_pick_big_title);
            tvSmallTitle = (TextView) itemView.findViewById(R.id.tv_mall_pick_small_title);
            llRoot = (AutoRelativeLayout) itemView.findViewById(R.id.ll_mall_pick_root);
            ivPicked = (ImageView) itemView.findViewById(R.id.iv_mall_pick);
        }
    }




    public interface OnRecycleItemClickListener{
        void onClick(View view, int position);
    }

    public static class MallPickTypeBean{
        public String bigTitle;
        public String smallTitle;
        public int bgColor;
        public int bigTitleColor;
        public int smallTitleColor;
        public String imgUrl;
        public int imgResId;

        @Override
        public String toString() {
            return "MallPickTypeBean{" +
                    "bigTitle='" + bigTitle + '\'' +
                    ", smallTitle='" + smallTitle + '\'' +
                    ", bgColor=" + bgColor +
                    ", bigTitleColor=" + bigTitleColor +
                    ", smallTitleColor=" + smallTitleColor +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", imgResId=" + imgResId +
                    '}';
        }
    }
}
