package com.yxld.yxchuangxin.Utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.SmsShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SmsHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.WeiXinShareContent;
import com.yxld.yxchuangxin.contain.PayContain;
import com.yxld.yxchuangxin.entity.ShareInfo;

/**
 * 友盟分享公用方法
 * @author WWx
 */
public class YouMengShareUtil {

	/** Activity*/
	private Activity mActivity;

	// 整个平台的Controller,负责管理整个SDK的配置、操作等处理
	private UMSocialService mController = UMServiceFactory
			.getUMSocialService("com.umeng.share");

	public YouMengShareUtil(Activity activity) {
		this.mActivity =activity;
	}

	/**
	 * 添加所有的平台</br>
	 */
	public void addCustomPlatforms(ShareInfo shareInfo) {
		Log.d("geek","addCustomPlatforms（）shareInfo="+shareInfo.toString());
		if (shareInfo == null) {
			Toast.makeText(mActivity, "分享内容未获取", Toast.LENGTH_SHORT).show();
			return;
		}
		// 配置需要分享的相关平台
		configPlatforms();
		// 设置分享的内容
		setShareContent(shareInfo.Title, shareInfo.ShareCon, shareInfo.ImgUrl,shareInfo.bitmap
		,shareInfo.getQQImgUrl());

		mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.SMS);
		mController.openShare(mActivity, false);
	}

	/**
	 * 配置分享平台参数
	 */
	public void configPlatforms() {
		// 添加QQ、QZone平台
		addQQQZonePlatform();

		// 添加微信、微信朋友圈平台
		addWXPlatform();

		// 添加短信
		SmsHandler smsHandler = new SmsHandler();
		smsHandler.addToSocialSDK();
	}

	/**
	 * 根据不同的平台设置不同的分享内容</br>
	 */
	public void setShareContent(String title, String content, String url, Bitmap bitmap,String qqurl) {

		UMImage urlBitMap = new UMImage(mActivity,bitmap);

		//微信分享内容
		WeiXinShareContent weixinContent = new WeiXinShareContent();
		weixinContent.setShareContent(content);
		weixinContent.setTitle(title);
		weixinContent.setTargetUrl(url);
		weixinContent.setShareImage(urlBitMap);
		mController.setShareMedia(weixinContent);

		//QQ分享内容
		QQShareContent qqShareContent = new QQShareContent();
		qqShareContent.setShareContent(content);
		qqShareContent.setTitle(title);
		qqShareContent.setShareImage(urlBitMap);
		qqShareContent.setTargetUrl(qqurl);
		mController.setShareMedia(qqShareContent);

		//短信分享内容
		SmsShareContent smsShareContent = new SmsShareContent();
		smsShareContent.setShareContent("分享地址为"+url);

         // 设置分享内容
		mController.setShareContent("分享地址为"+url);
	}

	/**
	 * @功能描述 : 添加微信平台分享
	 * @return
	 */
	public void addWXPlatform() {
		// 注意：在微信授权的时候，必须传递appSecret
		// wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
		String appId = PayContain.WX_APP_ID;
		String appSecret = PayContain.WX_APP_SECRET;
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(mActivity, appId, appSecret);
		wxHandler.addToSocialSDK();

		// 支持微信朋友圈
		UMWXHandler wxCircleHandler = new UMWXHandler(mActivity, appId, appSecret);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
	}

	/**
	 * @功能描述 : 添加QQ平台支持 QQ分享的内容， 包含四种类型， 即单纯的文字、图片、音乐、视频. 参数说明 : title, summary,
	 *       image url中必须至少设置一个, targetUrl必须设置,网页地址必须以"http://"开头 . title :
	 *       要分享标题 summary : 要分享的文字概述 image url : 图片地址 [以上三个参数至少填写一个] targetUrl
	 *       : 用户点击该分享时跳转到的目标地址 [必填] ( 若不填写则默认设置为友盟主页 )
	 * @return
	 */
	public void addQQQZonePlatform() {
		String appId = "1105723141";
		String appKey = "6CI0p9aZ7uCzWrzJ";
		// 添加QQ支持, 并且设置QQ分享内容的target url
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(mActivity, appId, appKey);
		qqSsoHandler.addToSocialSDK();

		// 添加QZone平台
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(mActivity, appId,
				appKey);
		qZoneSsoHandler.addToSocialSDK();
	}

}