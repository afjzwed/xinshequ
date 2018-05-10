package com.yxld.yxchuangxin.ui.adapter.camera;
////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//         佛祖保佑       永无BUG     永不修改                  //
////////////////////////////////////////////////////////////////////

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.entity.RecordFile;

import java.util.List;
import java.util.Locale;

/**
 * Created by yishangfei on 2017/3/27 0027.
 * 个人主页：http://yishangfei.me
 * Github:https://github.com/yishangfei
 */
public class RecordFileAdapter extends BaseQuickAdapter<RecordFile,BaseViewHolder> {
    public RecordFileAdapter(List<RecordFile> data) {
        super(R.layout.activity_recordfiles_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecordFile item) {
        String text = String.format(Locale.getDefault(), "%d、%s", helper.getAdapterPosition(), item.getName());
        helper.setText(R.id.recordfiles_text,text)
        .addOnClickListener(R.id.recordfiles_text);
    }
}
