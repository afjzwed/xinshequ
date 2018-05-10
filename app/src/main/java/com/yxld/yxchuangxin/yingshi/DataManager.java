package com.yxld.yxchuangxin.yingshi;
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

import android.content.Context;

import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 89876 on 2017/5/6 0006.
 * 个人主页：http://yishangfei.me
 * Github:https://github.com/yishangfei
 * <p>
 */
public class DataManager {
    private static DataManager mDataManager;

    private Map<String,String> mDeviceSerialVerifyCodeMap;
    private static LruBitmapPool mBitmapPool;

    private DataManager(){
        mDeviceSerialVerifyCodeMap = new HashMap<String,String>();
    }

    public static DataManager getInstance(){
        if (mDataManager == null){
            mDataManager = new DataManager();
        }
        return mDataManager;
    }

    public LruBitmapPool getBitmapPool(Context context){
        if (mBitmapPool == null){
            MemorySizeCalculator calculator = new MemorySizeCalculator(context);
            int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
            mBitmapPool = new LruBitmapPool(defaultBitmapPoolSize);
        }
        return mBitmapPool;
    }

    /**
     * 缓存设备验证码信息
     * @param deviceSerial
     * @param verifyCode
     */
    public synchronized void setDeviceSerialVerifyCode(String deviceSerial,String verifyCode){
        mDeviceSerialVerifyCodeMap.put(deviceSerial, verifyCode);
    }

    /**
     * @param deviceSerial
     * @return    获取缓存的设备验证码信息
     */
    public synchronized String getDeviceSerialVerifyCode(String deviceSerial){
        if (mDeviceSerialVerifyCodeMap.containsKey(deviceSerial)){
            return mDeviceSerialVerifyCodeMap.get(deviceSerial);
        }
        return null;
    }
}



