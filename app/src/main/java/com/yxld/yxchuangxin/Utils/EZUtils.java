package com.yxld.yxchuangxin.Utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.text.TextUtils;

import com.videogo.exception.InnerException;
import com.videogo.openapi.bean.EZCameraInfo;
import com.videogo.openapi.bean.EZDeviceInfo;
import com.videogo.util.LogUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class EZUtils {


    public static void saveCapturePictrue(String filePath, Bitmap bitmap) throws InnerException {
        if (TextUtils.isEmpty(filePath)){
            LogUtil.d("EZUtils","saveCapturePictrue file is null");
            return;
        }
        File filepath = new File(filePath);
        File parent = filepath.getParentFile();
        if (parent == null || !parent.exists() || parent.isFile()) {
            parent.mkdirs();
        }
        FileOutputStream out = null;
        try {
            // 保存原图

            if (!TextUtils.isEmpty(filePath)) {
                out = new FileOutputStream(filepath);
                bitmap.compress(CompressFormat.JPEG, 100, out);
                //out.write(tempBuf, 0, size);
                out.flush();
                out.close();
                out = null;
            }


        } catch (FileNotFoundException e) {
//            throw new InnerException(e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IOException e) {
//            throw new InnerException(e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }


    public static Object getPrivateMethodInvoke(Object instance, /*Class destClass,*/ String methodName,
                                                Class<?>[] parameterClass, Object... args) throws Exception {
        Class<?>[] parameterTypes = null;
        if(args != null) {
            parameterTypes = parameterClass;
        }
        Method method = instance.getClass().getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(instance, args);
    }

    /**
     * 通过ezdevice 得到其中通道信息
     * @param deviceInfo
     * @return
     */
    public static EZCameraInfo getCameraInfoFromDevice(EZDeviceInfo deviceInfo, int camera_index) {
        if (deviceInfo == null) {
            return null;
        }
        if (deviceInfo.getCameraNum() > 0 && deviceInfo.getCameraInfoList() != null && deviceInfo.getCameraInfoList().size() > camera_index) {
            return deviceInfo.getCameraInfoList().get(camera_index);
        }
        return null;
    }

    /**
     * EZDeviceInfo 得到取流通道和探测器意外的信息对象
     * @param deviceInfo
     * @return
     */
    public static EZDeviceInfo CopyEzDeviceInfoNoCameraAndDetector(EZDeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return null;
        }
        EZDeviceInfo ezDeviceInfo = new EZDeviceInfo();
        ezDeviceInfo.setDeviceName(deviceInfo.getDeviceName());
        ezDeviceInfo.setIsEncrypt(deviceInfo.getIsEncrypt());
        ezDeviceInfo.setCameraNum(deviceInfo.getCameraNum());
        ezDeviceInfo.setDefence(deviceInfo.getDefence());
        return deviceInfo;
    }

    /**
     * 此判断方式只能判断获取报警信息列表获取到的报警图片url
     * @param url
     * @return  图片url解析得到图片是否加密
     */
    private static boolean isEncrypt(String url){
        int ret = 0;
        try {
            Uri uri = Uri.parse(url);
            ret = Integer.parseInt(uri.getQueryParameter("isEncrypted"));
        }catch (Exception e){
          e.printStackTrace();
        }
        return ret == 1;
    }
}
