package com.yxld.yxchuangxin.view;


import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;


public class YwhTjDialog extends BaseDialog {


	private Button bt_dialog_cancel;
	private Button bt_dialog_confirm;
	private OnConfirmListener onConfirmListener;
	private EditText editText;



	public YwhTjDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

//	public static void showDialog(Context context, String title, String message, OnConfirmListener confirmListener){
//		YwhTjDialog dialog = new YwhTjDialog(context);
//		dialog.setCanceledOnTouchOutside(false);
//		dialog.setTitle(title);
//		dialog.setMessage(message);
//		dialog.setConfirmListener(confirmListener);
//		dialog.show();
//	}
//
	@Override
	public void initView() {
		setContentView(R.layout.dialog_tuijian_ly);
		bt_dialog_cancel = (Button) findViewById(R.id.bt_dialog_cancel);
		bt_dialog_confirm = (Button) findViewById(R.id.bt_dialog_confirm);
		editText = (EditText) findViewById(R.id.et_content);
	}

	public EditText getEditText() {
		return editText;
	}

	public void setEditText(EditText editText) {
		this.editText = editText;
	}

	@Override
	public void initListener() {
		bt_dialog_cancel.setOnClickListener(this);
		bt_dialog_confirm.setOnClickListener(this);

	}

	@Override
	public void initData() {


	}

	@Override
	public void processClick(View v) {
		switch (v.getId()) {
		//如果取消按钮按下时，侦听存在，那么调用侦听的onCancel
		case R.id.bt_dialog_cancel:
			if(onConfirmListener != null){
				onConfirmListener.onCancel();
			}
			break;
		case R.id.bt_dialog_confirm:
			if(onConfirmListener != null){
				onConfirmListener.onConfirm();
			}
			break;
			
		}
		//对话框消失
		dismiss();
	}
	
	public void setConfirmListener(OnConfirmListener confirmListener) {
		this.onConfirmListener = confirmListener;
	}



	public interface OnConfirmListener{
		void onCancel();
		void onConfirm();
	}
}
