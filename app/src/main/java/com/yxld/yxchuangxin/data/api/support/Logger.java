package com.yxld.yxchuangxin.data.api.support;


import com.socks.library.KLog;

/**
 * @author yuyh.
 * @date 2016/12/13.
 */
public class Logger implements LoggingInterceptor.Logger {

    @Override
    public void log(String message) {
        KLog.i("http : " + message);
    }
}
