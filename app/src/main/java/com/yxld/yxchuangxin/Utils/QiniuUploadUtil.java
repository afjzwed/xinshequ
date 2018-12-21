package com.yxld.yxchuangxin.Utils;


import com.orhanobut.logger.Logger;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.utils.UrlSafeBase64;
import com.qiniu.util.Auth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import static com.qiniu.android.common.Zone.zone2;

/**
 * @author xlei
 * @Date 2018/7/3.
 */

public class QiniuUploadUtil {
    private static int curUploadImgIndex = 0;

    public static UploadManager initQiniu() {
        Configuration config = new Configuration.Builder().chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
                .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
                .connectTimeout(10)           // 链接超时。默认10秒
                //.useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                //.recorder(recorder)           // recorder分片上传时，已上传片记录器。默认null
                //.recorder(recorder, keyGen)   // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
                .zone(zone2)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();
// 重用uploadManager一般地，只需要创建一个uploadManager对象
        return new UploadManager(config);
    }

    public static void uploadPic(final String path, final UploadCallback callBack) {

        UploadManager uploadManager = initQiniu();
        final String curUrl = "android_zjw/" + System.currentTimeMillis();
        //put第二个参数设置文件名
        Logger.i("开始上传单张图--------" + curUrl);
        uploadManager.put(path, curUrl, getQiniuToken(), new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                if (info.isOK()) {
                    //// TODO: 2018/7/5 y以后优化
//                    File file = new File(path);
//                    if (file.exists()){
//                        file.delete();
//                        Logger.i("文件删除成功");
//                    }
                    Logger.i("单图上传成功");
                    callBack.sucess(curUrl);
                } else {
                    Logger.e("单图上传失败" + info.toString());
                    callBack.fail(key, info);
                }
            }
        }, null);

    }

    public static void uploadPics(final List<String> list,  final UploadCallback callBack) {
        UploadManager uploadManager = initQiniu();
        curUploadImgIndex = 0;
        final List<String> urlList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            final String curUrl = "android_ywh/" + System.currentTimeMillis();
            //put第二个参数设置文件名
            urlList.add(curUrl);
            Logger.i("开始传第" + i + "张图--------" + curUrl);
            final int finalI = i;
            uploadManager.put(list.get(i), curUrl, getQiniuToken(), new UpCompletionHandler() {
                @Override
                public void complete(String key, ResponseInfo info, JSONObject response) {
                    curUploadImgIndex++;
                    if (info.isOK()) {
                        //// TODO: 2018/7/5 y以后优化
//                        File file = new File(list.get(finalI));
//                        if(file.exists()){
//                            file.delete();
//                            Logger.i("文件删除成功");
//                        }
                        if ((curUploadImgIndex) == list.size()) {
                            Logger.i("多图上传成功");
                            callBack.sucess(urlList);
                        }
                    } else {
                        Logger.e("多图上传失败" + info.toString());
                        callBack.fail(key, info);
                    }
                }
            }, null);

        }
    }
    public static void uploadPics1(final List<byte[]> list,  final UploadCallback callBack) {
        UploadManager uploadManager = initQiniu();
        curUploadImgIndex = 0;
        final List<String> urlList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            final String curUrl = "android_ywh/" + System.currentTimeMillis();
            //put第二个参数设置文件名
            urlList.add(curUrl);
            Logger.i("开始传第" + i + "张图--------" + curUrl);
            final int finalI = i;
            uploadManager.put(list.get(i), curUrl, getQiniuToken(), new UpCompletionHandler() {
                @Override
                public void complete(String key, ResponseInfo info, JSONObject response) {
                    curUploadImgIndex++;
                    if (info.isOK()) {
                        //// TODO: 2018/7/5 y以后优化
//                        File file = new File(list.get(finalI));
//                        if(file.exists()){
//                            file.delete();
//                            Logger.i("文件删除成功");
//                        }
                        if ((curUploadImgIndex) == list.size()) {
                            Logger.i("多图上传成功");
                            callBack.sucess(urlList);
                        }
                    } else {
                        Logger.e("多图上传失败" + info.toString());
                        callBack.fail(key, info);
                    }
                }
            }, null);

        }
    }
    public static String getQiniuToken() {
        String qiniuak = "4mJyuj6g6jjfYr2w1LBlrARdzscugcgVKAntzpfD";
        String qiniusk = "5qvaXWuaT3vvMMC2i7LXlTaJ6IzeVY7fIcGvR7C_";
        String qiniubcname = "monitorstorage";
        Auth auth = Auth.create(qiniuak, qiniusk);
        String s = auth.uploadToken(qiniubcname);
        return s;
    }


    public interface UploadCallback {
        /**
         * 单图上传返回上传照片路劲
         *
         * @param url
         */
        void sucess(String url);

        /**
         * 单图上传返回上传照片路劲
         *
         * @param url
         */
        void sucess(List<String> url);

        void fail(String key, ResponseInfo info);
    }

}
