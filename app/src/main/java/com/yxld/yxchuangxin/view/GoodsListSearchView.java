package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/24
 * @descprition: 与商品列表与商品搜索的头部
 */

public class GoodsListSearchView extends AutoRelativeLayout {
    private static final String INSTANCE_BUNDLE = "instance_bundle";
    private static final String INSTANCE_EDIT_TEXT = "instance_edit_text";
    private static final String INSTANCE_TEXT = "instance_text";


    private TextView tvSearchContent;
    private EditText editSearchContent;
    private AutoRelativeLayout searchRec;
    public GoodsListSearchView(Context context) {
        this(context,null);
    }

    public GoodsListSearchView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GoodsListSearchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.GoodsListSearchView);
        boolean editShow = ta.getBoolean(R.styleable.GoodsListSearchView_edit_show,false);
        ta.recycle();
        editSearchContent.setVisibility(editShow?VISIBLE:GONE);
        tvSearchContent.setVisibility(editShow?GONE:VISIBLE);

        if(editShow){
            searchRec.setClickable(false);
        }
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.header_mall_goods_list_search,this);
        searchRec = (AutoRelativeLayout) view.findViewById(R.id.mall_goods_list_search);
        AutoRelativeLayout ivBack = (AutoRelativeLayout) view.findViewById(R.id.iv_mall_goods_list_back);
        TextView tvSearch = (TextView) view.findViewById(R.id.tv_mall_goods_list_search);

        tvSearchContent = (TextView) view.findViewById(R.id.tv_mall_goods_list_search_content);
        editSearchContent = (EditText) view.findViewById(R.id.edit_goods_list_search);
        StringUitl.setEditTextInhibitInputSpeOnlyChnese50(editSearchContent);

        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.onBackClick();
                }
            }
        });

        searchRec.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.onSearchRecClick();
                }
            }
        });

        tvSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.onSearchTextClick();
                }
            }
        });
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        if(tvSearchContent == null || editSearchContent == null){
            return super.onSaveInstanceState();
        }

        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_BUNDLE,super.onSaveInstanceState());
        bundle.putString(INSTANCE_TEXT,tvSearchContent.getText().toString());
        bundle.putString(INSTANCE_EDIT_TEXT,editSearchContent.getText().toString());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if(state!=null && state instanceof Bundle){
            Bundle bundle = (Bundle) state;
            tvSearchContent.setText(bundle.getString(INSTANCE_TEXT));
            editSearchContent.setText(bundle.getString(INSTANCE_EDIT_TEXT));
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_BUNDLE));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    /**
     * 获取EditText的内容
     * @return
     */
    public String getSearchContent(){
        return editSearchContent.getText().toString();
    }

    /**
     * 获取商品列表的显示内容
     * @return
     */
    public String getContent(){
        return tvSearchContent.getText().toString();
    }


    public void setContent(String type){
        tvSearchContent.setText(type);
    }

    public void setSearchContent(CharSequence text) {
        editSearchContent.setText(text);
        editSearchContent.setSelection(editSearchContent.getText().length());
    }

    private OnViewClickListener mListener;
    public void setOnViewClickListener(OnViewClickListener listener){
        mListener = listener;
    }

    public interface OnViewClickListener{
        void onBackClick();
        void onSearchTextClick();
        void onSearchRecClick();
    }
}
