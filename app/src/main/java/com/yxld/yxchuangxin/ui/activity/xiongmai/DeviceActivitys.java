package com.yxld.yxchuangxin.ui.activity.xiongmai;

import android.content.Context;
import android.content.Intent;

import com.yxld.yxchuangxin.ui.activity.xiongmai.lib.funsdk.support.models.FunDevType;
import com.yxld.yxchuangxin.ui.activity.xiongmai.lib.funsdk.support.models.FunDevice;

import java.util.HashMap;
import java.util.Map;

public class DeviceActivitys {

	private static Map<FunDevType, Class<?>> sDeviceActivityMap = new HashMap<FunDevType, Class<?>>();
	
	static {
		// 监控设备
		sDeviceActivityMap.put(FunDevType.EE_DEV_NORMAL_MONITOR,
				XCameraActivity.class);
		
//		// 智能插座
//		sDeviceActivityMap.put(FunDevType.EE_DEV_INTELLIGENTSOCKET,
//				ActivityGuideDeviceSocket.class);
//
//		// 情景灯泡
//		sDeviceActivityMap.put(FunDevType.EE_DEV_SCENELAMP,
//				ActivityGuideDeviceBulb.class);
//
//		// 大眼睛行车记录仪
//		sDeviceActivityMap.put(FunDevType.EE_DEV_BIGEYE,
//				ActivityGuideDeviceCamera.class);
//
//		// 小雨点
//		sDeviceActivityMap.put(FunDevType.EE_DEV_SMALLEYE,
//				ActivityGuideDeviceCamera.class);
//
//		// 鱼眼小雨点
//		sDeviceActivityMap.put(FunDevType.EE_DEV_SMALLRAINDROPS_FISHEYE,
//				ActivityGuideDeviceCamera.class);
//
//		// 鱼眼灯泡,全景灯
//		sDeviceActivityMap.put(FunDevType.EE_DEV_LAMP_FISHEYE,
//				ActivityGuideDeviceCamera.class);
//
//		// 雄迈摇头机
//		sDeviceActivityMap.put(FunDevType.EE_DEV_BOUTIQUEROTOT,
//				ActivityGuideDeviceCamera.class);
//
//		// 运动相机
//		sDeviceActivityMap.put(FunDevType.EE_DEV_SPORTCAMERA,
//				ActivityGuideDeviceCamera.class);
	}
	
	public static void startDeviceActivity(Context context, FunDevice funDevice) {
		Class<?> a = sDeviceActivityMap.get(funDevice.devType);
		if ( null != a ) {
			Intent intent = new Intent();
			intent.setClass(context, a);
			intent.putExtra("FUN_DEVICE_ID", funDevice.getId());
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		}
	}
	public static void startDeviceActivity(Context context, FunDevice funDevice, int position) {
		Class<?> a = sDeviceActivityMap.get(funDevice.devType);
		if ( null != a ) {
			Intent intent = new Intent();
			intent.setClass(context, a);
			intent.putExtra("position", position);
			intent.putExtra("FUN_DEVICE_ID", funDevice.getId());
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		}
	}

	public static Class<?> getDeviceActivity(FunDevice funDevice) {
		if ( null == funDevice ) {
			return null;
		}
		return sDeviceActivityMap.get(funDevice.devType);
	}
}
