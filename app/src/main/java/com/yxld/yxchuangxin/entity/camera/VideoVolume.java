package com.yxld.yxchuangxin.entity.camera;

/**
 * 作者：Android on 2017/11/1
 * 邮箱：365941593@qq.com
 * 描述：对讲音量调节的eventbus消息类
 */

public class VideoVolume {
    int size;

    public VideoVolume(int size) {
        this.size = size;
    }

    public int getSize() {

        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
