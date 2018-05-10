package com.yxld.yxchuangxin.ui.adapter.wuye;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.Question;

import java.util.List;

/**
 * 作者：hu on 2017/6/20
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class SatisficingAdapter extends PagerAdapter {
    private Activity mActivity;
    private List<Question.DataBean> urlList;
    private GetCheckItemAnser getCheckItemAnser;

    private int checkedItem = -1;

    public SatisficingAdapter(Activity mActivity, List<Question.DataBean> list) {
        this.mActivity = mActivity;
        urlList = list;
    }

    @Override
    public int getCount() {
        return urlList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_satisfing, container, false);
        TextView tv_question = (TextView) view.findViewById(R.id.tv_question);
        CheckBox cb1 = (CheckBox) view.findViewById(R.id.cb1);
        CheckBox cb2 = (CheckBox) view.findViewById(R.id.cb2);
        CheckBox cb3 = (CheckBox) view.findViewById(R.id.cb3);
        CheckBox cb4 = (CheckBox) view.findViewById(R.id.cb4);
        cb1.setChecked(false);
        cb2.setChecked(false);
        cb3.setChecked(false);
        cb4.setChecked(false);
        tv_question.setText(urlList.get(position).getQuestion());
        cb1.setText(urlList.get(position).getAnswer().get(0).getName());
        cb2.setText(urlList.get(position).getAnswer().get(1).getName());
        cb3.setText(urlList.get(position).getAnswer().get(2).getName());
        cb4.setText(urlList.get(position).getAnswer().get(3).getName());
        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedItem = 0;
                cb1.setChecked(true);
                cb2.setChecked(false);
                cb3.setChecked(false);
                cb4.setChecked(false);
            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedItem = 1;
                cb1.setChecked(false);
                cb2.setChecked(true);
                cb3.setChecked(false);
                cb4.setChecked(false);
            }
        });
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedItem = 2;
                cb1.setChecked(false);
                cb2.setChecked(false);
                cb3.setChecked(true);
                cb4.setChecked(false);
            }
        });
        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedItem = 3;
                cb1.setChecked(false);
                cb2.setChecked(false);
                cb3.setChecked(false);
                cb4.setChecked(true);
            }
        });

        getCheckItemAnser = new GetCheckItemAnser() {
            @Override
            public int getCheckdItem() {
                KLog.i(checkedItem);
                if (checkedItem == -1) {
                    return -1;
                } else {
                    int temp = checkedItem;
                    checkedItem = -1;
                    return temp;
                }
            }

            @Override
            public Question.DataBean getCurrentItem() {
                return urlList.get(position);
            }
        };
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    interface GetCheckItemAnser {
        int getCheckdItem();

        Question.DataBean getCurrentItem();
    }

    public int getCheckdItem() {
        return getCheckItemAnser.getCheckdItem();
    }

    public Question.DataBean getCurrentItem() {
        return getCheckItemAnser.getCurrentItem();
    }
}
