package com.yxld.yxchuangxin.Utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yxld.yxchuangxin.R;

/**
 * @author: Yuan.Y.Q
 * @date: 2017/6/26
 * @descprition:
 */

public class GlideUtil {
    public static void showImgDefault(Context context, ImageView iv,Uri uri){
        Glide.with(context)
                .load(uri)
                .into(iv);
    }
}
