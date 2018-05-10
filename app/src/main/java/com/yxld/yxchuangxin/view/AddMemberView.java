package com.yxld.yxchuangxin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.StringUitl;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 作者：hu on 2017/6/7
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class AddMemberView extends AutoRelativeLayout {

    private ImageView iv_head;
    private TextView tv_head;
    private EditText tv_content;
    private ImageView iv_navigation;
    private View line;

    private static Drawable HEADIMAGERES;
    private static Drawable NAVIGATEIMARES;
    private static String HEADTEXT = "";
    private static String CONTENTHINT = "";
    private static String rightText = "";
    private static boolean CLEAREDITFOCUS = false;
    private static boolean SHOWLINE = false;
    private static boolean SHOWNAVIGATE = true;
    private static final boolean DEFAULT_CLEAREDITFOCUS = false;           //默认不要edittext的焦点
    private static final boolean DEFAULT_SHOWLINE = false;                  //默认不要清楚下划线
    private static final boolean DEFAULT_SHOWNAVIGATE = true;              //默认要导航图标
    private TextView tv_right;
    private TextView tv_left;


    private RightImgOnClickListener rightImgOnClickListener;


    public AddMemberView(Context context) {
        super(context);
    }

    public AddMemberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AddMemberView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.add_member_layout, this, true);
        iv_head = (ImageView) view.findViewById(R.id.iv_head);
        tv_head = (TextView) view.findViewById(R.id.tv_head);
        tv_content = (EditText) view.findViewById(R.id.tv_content);
        iv_navigation = (ImageView) view.findViewById(R.id.iv_navigation);
        line = view.findViewById(R.id.line);
        tv_right = (TextView) view.findViewById(R.id.tv_right);
        tv_left = (TextView) view.findViewById(R.id.tv_left);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AddMemberView);
        CONTENTHINT = typedArray.getString(R.styleable.AddMemberView_contentHint);
        HEADTEXT = typedArray.getString(R.styleable.AddMemberView_headText);
        HEADIMAGERES = typedArray.getDrawable(R.styleable.AddMemberView_headImageRes);
        NAVIGATEIMARES = typedArray.getDrawable(R.styleable.AddMemberView_navigateImgeRes);
        CLEAREDITFOCUS = typedArray.getBoolean(R.styleable.AddMemberView_clearEditFocus, DEFAULT_CLEAREDITFOCUS);
        SHOWLINE = typedArray.getBoolean(R.styleable.AddMemberView_showLine, DEFAULT_SHOWLINE);
        SHOWNAVIGATE = typedArray.getBoolean(R.styleable.AddMemberView_showNavigate, DEFAULT_SHOWNAVIGATE);
        rightText = typedArray.getString(R.styleable.AddMemberView_rightText);
        boolean canEdit = typedArray.getBoolean(R.styleable.AddMemberView_canEdit, true);
        addStyle();
        typedArray.recycle();

        if (!canEdit) {
            tv_content.setFocusable(false);
            tv_content.setFocusableInTouchMode(false);
        }
    }

    private void addStyle() {
        tv_content.setHint(CONTENTHINT);
        tv_head.setText(HEADTEXT);
        iv_head.setImageDrawable(HEADIMAGERES);
        tv_content.setFocusableInTouchMode(!CLEAREDITFOCUS);
        tv_content.setFocusable(!CLEAREDITFOCUS);
        tv_content.setClickable(!CLEAREDITFOCUS);
        if (SHOWNAVIGATE) {
            iv_navigation.setImageDrawable(NAVIGATEIMARES);
            iv_navigation.setVisibility(VISIBLE);
        } else {
            iv_navigation.setVisibility(INVISIBLE);
        }
        if (SHOWLINE) {
            line.setVisibility(VISIBLE);
        } else {
            line.setVisibility(INVISIBLE);
        }
        tv_right.setText(rightText);

        iv_navigation.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rightImgOnClickListener != null) {
                    rightImgOnClickListener.OnRightClick();
                }
            }
        });
        StringUitl.setEditTextInhibitInputSpeOnlyChnese50(tv_content);
    }

    public void setEdittext(String edittext) {
        tv_content.setText(edittext);
    }

    public EditText getEditTextView() {
        return tv_content;
    }

    public void setRightText(String rightText) {
        tv_right.setText(rightText);
    }

    public String getEdittext() {
        return tv_content.getText().toString();
    }

    public String getRightText() {
        return tv_right.getText().toString();
    }

    public interface RightImgOnClickListener {
        void OnRightClick();
    }

    public void setRightImgOnClickListener(RightImgOnClickListener rightImgOnClickListener) {
        this.rightImgOnClickListener = rightImgOnClickListener;
    }

    public void setLeftText(String leftText) {
        tv_left.setText(leftText);
    }
}
