package com.yxld.yxchuangxin.yoosee;
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

/**
 * Created by 89876 on 2017/5/23 0023.
 * 个人主页：http://yishangfei.me
 * Github:https://github.com/yishangfei
 * <p>
 */
public class CheckUpdate {
    public String contactId;
    public int result;
    public String cur_version;
    public String upg_version;

    public CheckUpdate(String contactId, int result, String cur_version, String upg_version) {
        this.contactId = contactId;
        this.result = result;
        this.cur_version = cur_version;
        this.upg_version = upg_version;
    }
}