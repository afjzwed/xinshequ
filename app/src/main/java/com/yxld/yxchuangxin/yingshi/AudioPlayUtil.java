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

import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.yxld.yxchuangxin.R;

import java.util.HashMap;

/**
 * Created by 89876 on 2017/5/6 0006.
 * 个人主页：http://yishangfei.me
 * Github:https://github.com/yishangfei
 * <p>
 */
public class AudioPlayUtil {

    private SoundPool mSoundPool = null;

    public static int CAPTURE_SOUND = 1;

    public static int RECORD_SOUND = 2;

    private boolean mRingerMode = true;

    private int mStreamID = 0;

    private Context mContext = null;

    private HashMap<Integer, Integer> mSoundMap = null;

    private static AudioPlayUtil mAudioPlayUtil = null;

    private AudioPlayUtil(Application application) {
        mContext = application.getApplicationContext();
        mSoundMap = new HashMap<Integer, Integer>();

        mSoundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 100);
        mSoundMap.put(CAPTURE_SOUND, mSoundPool.load(mContext, R.raw.paizhao, 0));
        mSoundMap.put(RECORD_SOUND, mSoundPool.load(mContext, R.raw.record, 0));
    };

    public static AudioPlayUtil getInstance(Application application) {
        if (mAudioPlayUtil == null) {
            mAudioPlayUtil = new AudioPlayUtil(application);
        }

        return mAudioPlayUtil;
    }

    /**
     * 这里对方法做描述
     *
     * @param soundId
     * @since V1.0
     */
    public void playAudioFile(int soundId) {
        stopAudioPlay();
        getAlarmParams();
        if (mRingerMode) {
            mStreamID = mSoundPool.play(mSoundMap.get(soundId), 1, 1, 0, 0, 1);
            if (mStreamID == 0 && (soundId == 3 || soundId == 4)) {
                mStreamID = mSoundPool.play(mSoundMap.get(soundId + 2), 1, 1, 0, 0, 1);
            }
        }
    }

    public void stopAudioPlay() {
        if (mStreamID != 0) {
            mSoundPool.stop(mStreamID);
        }
    }

    /**
     * 设置震动和声音
     *
     * @see
     * @since V2.0
     */
    private void getAlarmParams() {
        // AudioManager provides access to volume and ringer mode control.
        AudioManager volMgr = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        switch (volMgr.getRingerMode()) { // 获取系统设置的铃声模式
            case AudioManager.RINGER_MODE_SILENT:// 静音模式，值为0，这时候不震动，不响铃
            case AudioManager.RINGER_MODE_VIBRATE:// 震动模式，值为1，这时候震动，不响铃
                mRingerMode = false;
                break;
            case AudioManager.RINGER_MODE_NORMAL:// 常规模式，值为2，分两种情况：1_响铃但不震动，2_响铃+震动
                mRingerMode = true;
                break;
            default:
                break;
        }
    }
}

