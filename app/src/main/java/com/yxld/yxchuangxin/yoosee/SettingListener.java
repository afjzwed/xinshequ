package com.yxld.yxchuangxin.yoosee;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.p2p.core.P2PInterface.ISetting;
import com.socks.library.KLog;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.entity.DefenceStates;
import com.yxld.yxchuangxin.entity.camera.VideoVolume;
import com.yxld.yxchuangxin.ui.activity.camera.RecordFilesActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;

//import com.yxld.yxchuangxin.activity.camera.RecordFilesActivity;
//import com.yxld.yxchuangxin.base.AppConfig;

/**
 * Created by dansesshou on 16/11/30.
 */

public class SettingListener implements ISetting {
    @Override
    public void ACK_vRetSetDeviceTime(int msgId, int result) {

    }

    @Override
    public void ACK_vRetGetDeviceTime(int msgId, int result) {

    }

    @Override
    public void ACK_vRetGetNpcSettings(String contactId, int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetRemoteDefence(String contactId, int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetRemoteRecord(int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetNpcSettingsVideoFormat(int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetNpcSettingsVideoVolume(int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetNpcSettingsBuzzer(int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetNpcSettingsMotion(int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetNpcSettingsRecordType(int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetNpcSettingsRecordTime(int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetNpcSettingsRecordPlanTime(int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetNpcSettingsNetType(int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetAlarmEmail(int msgId, int result) {

    }

    @Override
    public void ACK_vRetGetAlarmEmail(int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetAlarmBindId(int srcID, int result) {

    }

    @Override
    public void ACK_vRetGetAlarmBindId(int srcID, int result) {

    }

    @Override
    public void ACK_vRetSetInitPassword(int msgId, int result) {
        KLog.i("msgId:" + msgId + "  result:" + result);
        EventBus.getDefault().post(new InitPasswordEvent(result));
    }

    @Override
    public void ACK_vRetSetDevicePassword(int msgId, int result) {
        if (result!=9997){
            EventBus.getDefault().post("SetDevicePassword" + 1);
        }
        KLog.i("msgId:" + msgId + "  result:" + result);
    }

    @Override
    public void ACK_vRetCheckDevicePassword(int msgId, int result, String deviceId) {
        KLog.i("msgId:" + msgId + "  result:" + result);
        EventBus.getDefault().post(new CheckEvent(result));
    }

    @Override
    public void ACK_vRetSetWifi(int msgId, int result) {

    }

    @Override
    public void ACK_vRetGetWifiList(int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetDefenceArea(int msgId, int result) {

    }

    @Override
    public void ACK_vRetGetDefenceArea(int msgId, int result) {
        Log.d("...", "ACK_vRetGetDefenceArea: "+msgId+"result:"+result);
    }

    @Override
    public void ACK_vRetGetRecordFileList(int msgId, int result) {

    }

    @Override
    public void ACK_vRetMessage(int msgId, int result) {

    }

    @Override
    public void ACK_vRetCustomCmd(int msgId, int result) {
        Log.e("dxsTest", "ACK_vRetCustomCmd:" + msgId + "result:" + result);
    }

    @Override
    public void ACK_vRetGetDeviceVersion(int msgId, int result) {

    }

    @Override
    public void ACK_vRetCheckDeviceUpdate(int msgId, int result) {

    }

    @Override
    public void ACK_vRetDoDeviceUpdate(int msgId, int result) {

    }

    @Override
    public void ACK_vRetCancelDeviceUpdate(int msgId, int result) {

    }

    @Override
    public void ACK_vRetClearDefenceAreaState(int msgId, int result) {

    }

    @Override
    public void ACK_vRetGetDefenceStates(String contactId, int msgId, int result) {
        KLog.i("获取布防状态的返回");
        KLog.i(result);
    }

    @Override
    public void ACK_vRetSetImageReverse(int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetInfraredSwitch(int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetWiredAlarmInput(int msgId, int state) {

    }

    @Override
    public void ACK_vRetSetWiredAlarmOut(int msgId, int state) {

    }

    @Override
    public void ACK_vRetSetAutomaticUpgrade(int msgId, int state) {

    }

    @Override
    public void ACK_VRetSetVisitorDevicePassword(int msgId, int state) {

    }

    @Override
    public void ACK_vRetSetTimeZone(int msgId, int state) {

    }

    @Override
    public void ACK_vRetGetSDCard(int msgId, int state) {

    }

    @Override
    public void ACK_vRetSdFormat(int msgId, int state) {

    }

    @Override
    public void ACK_vRetSetGPIO(int msgId, int state) {

    }

    @Override
    public void ACK_vRetSetGPIO1_0(int msgId, int state) {

    }

    @Override
    public void ACK_vRetSetPreRecord(int msgId, int state) {

    }

    @Override
    public void ACK_vRetGetSensorSwitchs(int msgId, int state) {

    }

    @Override
    public void ACK_vRetSetSensorSwitchs(int msgId, int state) {

    }

    @Override
    public void ACK_vRetGetAlarmCenter(int msgId, int state) {

    }

    @Override
    public void ACK_vRetSetAlarmCenter(int msgId, int state) {

    }

    @Override
    public void ACK_VRetGetNvrIpcList(int msgId, int state) {

    }

    @Override
    public void ACK_VRetGetNvrInfo(String deviceId, int msgId, int state) {

    }

    @Override
    public void ACK_OpenDoor(int msgId, int state) {

    }

    @Override
    public void ACK_vRetGetFTPInfo(int msgId, int state) {

    }

    @Override
    public void ACK_vRetGetPIRLight(int msgId, int state) {

    }

    @Override
    public void ACK_vRetSetPIRLight(int msgId, int state) {

    }

    @Override
    public void ACK_vRetGetDefenceWorkGroup(int msgId, int state) {

    }

    @Override
    public void ACK_VRetGetPresetPos(int msgId, int state) {

    }

    @Override
    public void ACK_VRetSetKeepClient(String contactId, int msgId, int state) {

    }

    @Override
    public void ACK_VRetGetLed(String contactId, int msgId, int state) {

    }

    @Override
    public void ACK_VRetSetLed(String contactId, int msgId, int state) {

    }

    @Override
    public void ACK_vRetSetNpcSettingsMotionSens(int msgId, int result) {

    }

    @Override
    public void ACK_vRetGetVideoQuality(int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetVideoQuality(int msgId, int result) {

    }

    @Override
    public void ACK_vRetGetApIsWifiSetting(String contactId, int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetApStaWifiInfo(String contactId, int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetLockInfo(String contactId, int msgId, int result) {

    }

    @Override
    public void ACK_vRetGetLockInfo(String contactId, int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetLEDStatus(String contactId, int msgId, int result) {

    }

    @Override
    public void ACK_vRetSetApStart(String contactId, int msgId, int result) {

    }


    @Override
    public void vRetGetRemoteDefenceResult(String contactId, int state) {
        KLog.i("获取布防状态" + state);
        EventBus.getDefault().post("Defence" + state);
        DefenceStates defenceStates = new DefenceStates(state, contactId);
        EventBus.getDefault().post(defenceStates);
    }

    @Override
    public void vRetGetRemoteRecordResult(int state) {
        Log.d("...", "vRetGetRemoteRecordResult: "+state);
        EventBus.getDefault().post(state);
    }

    @Override
    public void vRetGetBuzzerResult(int state) {
        Log.d("...", "vRetGetBuzzerResult: "+state);
        EventBus.getDefault().post("Buzzer"+state);
    }

    @Override
    public void vRetGetMotionResult(int state) {
        Log.d("...", "vRetGetMotionResult: "+state);
        EventBus.getDefault().post("Motion"+state);
    }

    @Override
    public void vRetGetVideoFormatResult(int type) {

    }

    @Override
    public void vRetGetRecordTypeResult(int type) {
        KLog.i("recordType"+type);
        EventBus.getDefault().post("recordType"+type);
    }

    @Override
    public void vRetGetRecordTimeResult(int time) {

    }

    @Override
    public void vRetGetNetTypeResult(int type) {

    }

    @Override
    public void vRetGetVideoVolumeResult(int value) {
        KLog.i("收到了录音声音大小：" + value);
        EventBus.getDefault().post(new VideoVolume(value));
    }

    @Override
    public void vRetGetRecordPlanTimeResult(String time) {

    }

    @Override
    public void vRetGetImageReverseResult(int type) {
        EventBus.getDefault().post("ImageReverse"+type);
    }

    @Override
    public void vRetGetInfraredSwitch(int state) {

    }

    @Override
    public void vRetGetWiredAlarmInput(int state) {

    }

    @Override
    public void vRetGetWiredAlarmOut(int state) {

    }

    @Override
    public void vRetGetAutomaticUpgrade(int state) {

    }

    @Override
    public void vRetGetTimeZone(int state) {

    }

    @Override
    public void vRetGetAudioDeviceType(int type) {

    }

    @Override
    public void vRetGetPreRecord(int state) {

    }

    @Override
    public void vRetGetSensorSwitchs(int result, ArrayList<int[]> data) {

    }

    @Override
    public void vRetSetRemoteDefenceResult(String contactId, int result) {

    }

    @Override
    public void vRetSetRemoteRecordResult(int result) {

    }

    @Override
    public void vRetSetBuzzerResult(int result) {
        Log.d("...", "vRetSetBuzzerResult: "+result);
        EventBus.getDefault().post("SetBuzzer"+result);
    }

    @Override
    public void vRetSetMotionResult(int result) {
        Log.d("...", "vRetSetMotionResult: "+result);
        EventBus.getDefault().post("SetMotion"+result);
    }

    @Override
    public void vRetSetVideoFormatResult(int result) {

    }

    @Override
    public void vRetSetRecordTypeResult(int result) {
    }

    @Override
    public void vRetSetRecordTimeResult(int result) {

    }

    @Override
    public void vRetSetNetTypeResult(int result) {

    }

    @Override
    public void vRetSetVolumeResult(int result) {

    }

    @Override
    public void vRetSetRecordPlanTimeResult(int result) {

    }

    @Override
    public void vRetSetDeviceTimeResult(int result) {

    }

    @Override
    public void vRetGetDeviceTimeResult(String time) {

    }

    @Override
    public void vRetAlarmEmailResult(int result, String email) {

    }

    @Override
    public void vRetAlarmEmailResultWithSMTP(int result, String email, int smtpport, byte Entry, String[] SmptMessage, byte reserve) {

    }

    @Override
    public void vRetWifiResult(int result, int currentId, int count, int[] types, int[] strengths, String[] names) {

    }

    @Override
    public void vRetDefenceAreaResult(int result, ArrayList<int[]> data, int group, int item) {
        Log.d("...", "防区信息返回 " + result);
        EventBus.getDefault().post(new LearnEvent(data,result));

    }

    @Override
    public void vRetBindAlarmIdResult(int srcID, int result, int maxCount, String[] data) {

    }

    @Override
    public void vRetSetInitPasswordResult(int result) {

    }

    @Override
    public void vRetSetDevicePasswordResult(int result) {
        EventBus.getDefault().post("SetDevicePassword" + result);

    }

    @Override
    public void vRetGetFriendStatus(int count, String[] contactIds, int[] status, int[] types, boolean isFinish) {

    }

    @Override
    public void vRetGetRecordFiles(String[] names, byte option0, byte option1) {
        Intent i = new Intent();
        i.setAction(RecordFilesActivity.RECORDFILES);
        i.putExtra("recordList", names);
        i.putExtra("option0", option0);
        i.putExtra("option1", option1);
        AppConfig.app.sendBroadcast(i);

    }

    @Override
    public void vRetMessage(String contactId, String msg) {

    }

    @Override
    public void vRetSysMessage(String msg) {

    }

    @Override
    public void vRetCustomCmd(int contactId, int len, byte[] cmd) {
        Log.e("dxsTest", "ACK_vRetCustomCmd:" + contactId + "cmd:" + Arrays.toString(cmd));
        Toast.makeText(AppConfig.app, "" + contactId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void vRetGetDeviceVersion(int result, String cur_version, int iUbootVersion, int iKernelVersion, int iRootfsVersion) {

    }

    @Override
    public void vRetCheckDeviceUpdate(String contactId, int result, String cur_version, String upg_version) {
        Log.d("...", "vRetCheckDeviceUpdate: "+contactId);
        Log.d("...", "vRetCheckDeviceUpdate: "+result);
        Log.d("...", "vRetCheckDeviceUpdate: "+cur_version);
        Log.d("...", "vRetCheckDeviceUpdate: "+upg_version);
        EventBus.getDefault().post(new CheckUpdate(contactId,result,cur_version,upg_version));
    }

    @Override
    public void vRetDoDeviceUpdate(String contactId, int result, int value) {
        Log.d("...", "vRetDoDeviceUpdate: contactId"+contactId);
        Log.d("...", "vRetDoDeviceUpdate: result"+result);
        Log.d("...", "vRetDoDeviceUpdate: value"+value);
        EventBus.getDefault().post(new DeviceUpdate(contactId,result,value));
    }

    @Override
    public void vRetCancelDeviceUpdate(int result) {

    }

    @Override
    public void vRetDeviceNotSupport() {

    }

    @Override
    public void vRetClearDefenceAreaState(int result) {

    }

    @Override
    public void vRetSetImageReverse(int result) {
        EventBus.getDefault().post("SetImageReverse"+result);
    }

    @Override
    public void vRetSetInfraredSwitch(int result) {

    }

    @Override
    public void vRetSetWiredAlarmInput(int state) {

    }

    @Override
    public void vRetSetWiredAlarmOut(int state) {

    }

    @Override
    public void vRetSetAutomaticUpgrade(int state) {

    }

    @Override
    public void vRetSetVisitorDevicePassword(int result) {

    }

    @Override
    public void vRetSetTimeZone(int result) {

    }

    @Override
    public void vRetGetSdCard(int result1, int result2, int SDcardID, int state) {

    }

    @Override
    public void VRetGetUsb(int result1, int result2, int SDcardID, int state) {

    }

    @Override
    public void vRetSdFormat(int result) {

    }

    @Override
    public void vRetSetGPIO(int result) {

    }

    @Override
    public void vRetSetPreRecord(int result) {

    }

    @Override
    public void vRetSetSensorSwitchs(int result) {

    }

    @Override
    public void vRecvSetLAMPStatus(String deviceId, int result) {

    }

    @Override
    public void vACK_RecvSetLAMPStatus(int result, int value) {

    }

    @Override
    public void vRecvGetLAMPStatus(String deviceId, int result) {

    }

    @Override
    public void vRetPresetMotorPos(byte[] result) {

    }

    @Override
    public void vRetDefenceSwitchStatus(int result) {

    }

    @Override
    public void vRetDefenceSwitchStatusResult(byte[] result) {

    }

    @Override
    public void vRetAlarmPresetMotorPos(byte[] result) {

    }

    @Override
    public void vRetIpConfig(byte[] result) {

    }

    @Override
    public void vRetGetAlarmCenter(int result, int state, String ipdress, int port, String userId) {

    }

    @Override
    public void vRetSetAlarmCenter(int result) {

    }

    @Override
    public void vRetDeviceNotSupportAlarmCenter() {

    }

    @Override
    public void vRetNPCVistorPwd(int pwd) {

    }

    @Override
    public void vRetDeleteDeviceAlarmID(String deviceId, int result, int result1) {

    }

    @Override
    public void vRetDeviceLanguege(int result, int languegecount, int curlanguege, int[] langueges) {

    }

    @Override
    public void vRetFocusZoom(String deviceId, int result) {

    }

    @Override
    public void vRetGetAllarmImage(int id, String filename, int errorCode) {

    }

    @Override
    public void vRetFishEyeData(int iSrcID, byte[] data, int datasize) {

    }

    @Override
    public void vRetGetNvrIpcList(String contactId, String[] date, int number) {

    }

    @Override
    public void vRetSetWifiMode(String id, int result) {

    }

    @Override
    public void vRetAPModeSurpport(String id, int result) {

    }

    @Override
    public void vRetDeviceType(String id, int mainType, int subType) {

    }

    @Override
    public void vRetNVRInfo(int iSrcID, byte[] data, int datasize) {

    }

    @Override
    public void vRetGetFocusZoom(String deviceId, int result, int value) {

    }

    @Override
    public void vRetSetFocusZoom(String deviceId, int result, int value) {

    }

    @Override
    public void vRetSetGPIO(String contactid, int result) {

    }

    @Override
    public void vRetGetGPIO(String contactid, int result, int bValueNs) {

    }

    @Override
    public void vRetGetDefenceWorkGroup(String contactid, byte[] data) {

    }

    @Override
    public void vRetSetDefenceWorkGroup(String contactid, byte[] data) {

    }

    @Override
    public void vRetFTPConfigInfo(String contactid, byte[] data) {

    }

    @Override
    public void vRetGPIOStatus(String contactid, byte[] Level) {

    }

    @Override
    public void vRecvSetGPIOStatus(String contactid, byte[] data) {

    }

    @Override
    public void vRetGetDefenceSwitch(int value) {

    }

    @Override
    public void vRetSetDefenceSwitch(int result) {

    }

    @Override
    public void vRetDefenceAreaName(String contactid, byte[] data) {

    }

    @Override
    public void vRetGetPIRLightControl(int value) {

    }

    @Override
    public void vRetFishInfo(String contactid, byte[] data) {

    }

    @Override
    public void vRetGetAutoSnapshotSwitch(int value) {

    }

    @Override
    public void vRetSetAutoSnapshotSwitch(int result) {

    }

    @Override
    public void vRecvGetPrepointSurpporte(String deviceId, int result) {

    }

    @Override
    public void vRetGetMotionSensResult(int value) {

    }

    @Override
    public void vRetSetMotionSensResult(int iResult) {

    }

    @Override
    public void vRetVideoQuality(String contactId, byte[] data) {

    }

    @Override
    public void vRetGetIndexFriendStatus(int count, String[] contactIds, int[] IdProtery, int[] status, int[] DevTypes, int[] SubType, int[] DefenceState, byte bRequestResult) {
        KLog.i("...", "vRetGetIndexFriendStatus: count"+count);
        for (int i = 0; i <contactIds.length ; i++) {
            KLog.i("...", "vRetGetIndexFriendStatus: contactIds"+contactIds[i]);
        }
        for (int i = 0; i <IdProtery.length ; i++) {
            KLog.i("...", "vRetGetIndexFriendStatus: IdProtery"+IdProtery[i]);
        }
        for (int i = 0; i <status.length ; i++) {
            KLog.i("...", "vRetGetIndexFriendStatus: status"+status[i]);
        }
        for (int i = 0; i <DevTypes.length ; i++) {
            KLog.i("...", "vRetGetIndexFriendStatus: DevTypes"+DevTypes[i]);
        }
        for (int i = 0; i <SubType.length ; i++) {
            KLog.i("...", "vRetGetIndexFriendStatus: SubType"+SubType[i]);
        }
        for (int i = 0; i <DefenceState.length ; i++) {
            KLog.i("...", "vRetGetIndexFriendStatus: DefenceState"+DefenceState[i]);
        }
        KLog.i("...", "vRetGetIndexFriendStatus: bRequestResult"+bRequestResult);
        EventBus.getDefault().post(new FriendStatus(status,bRequestResult, DefenceState));
    }

    @Override
    public void vRetGetIRLEDResult(int value) {

    }

    @Override
    public void vRetSetIRLEDResult(String contactId, int iResult) {

    }

    @Override
    public void vRetGetApIsWifiSetting(String contactId, byte[] data) {

    }

    @Override
    public void vRetSetApStaWifiInfo(String contactId, byte[] data) {

    }

    @Override
    public void vRetGetLEDResult(String contactId, int iResult) {

    }

    @Override
    public void vRetSetLEDResult(String contactId, int iResult) {

    }

    @Override
    public void vRetSetApStart(String contactId, int iResult) {

    }

    @Override
    public void vRetLockInfo(String contactId, byte[] data) {

    }

    @Override
    public void vRetGroupMessage(String groupName, int srcId, int ReciveTime, byte[] msg, int msgSize, int MesgType) {

    }

    @Override
    public void vRetGroupMessageAck(String groupName, int srcId, int Error) {

    }

    @Override
    public void vRetOfflineGroupMessage(String groupName, int srcId, int ReciveTime, byte[] msg, int msgSize, int MesgType) {


    }

    @Override
    public void vRetGroupMessageOver() {

    }
}
