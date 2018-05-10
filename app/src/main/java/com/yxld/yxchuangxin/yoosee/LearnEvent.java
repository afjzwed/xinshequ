package com.yxld.yxchuangxin.yoosee;

import java.util.ArrayList;

/**
 * Created by yishangfei on 2017/2/22 0022.
 * 邮箱：yishangfei@foxmail.com
 *
 * vRetDefenceAreaResult   学习设备
 */
public class LearnEvent {
    public ArrayList<int[]> data;
    public int result;
    public LearnEvent(ArrayList<int[]> data, int result) {
        this.data = data;
        this.result = result;
    }
}
