package com.yxld.yxchuangxin.Utils;

/**
 * 作者：Android on 2017/9/7
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class FangquUtil {
    /**
     * 防区类型0-7
     * 			0:普通防区
     *			1:紧急防区
     *			2:留守防区
     *			3:智能防区
     *			4:关闭防区
     *			5:门铃防区
     *			6:迎宾防区
     *			7:求助防区

     * @param leixing
     * @return
     */
    public static String parseFangqu(String leixing) {
        switch (leixing) {
            case "0":
                return "普通防区";
            case "1":
                return "紧急防区";
            case "2":
                return "留守防区";
            case "3":
                return "智能防区";
            case "4":
                return "关闭防区";
            case "5":
                return "门铃防区";
            case "6":
                return "迎宾防区";
            case "7":
                return "求助防区";
        }
        return "";
    }
}
