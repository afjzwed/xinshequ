package com.yxld.yxchuangxin.application;

import android.app.Application;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yxld.yxchuangxin.BuildConfig;
import com.yxld.yxchuangxin.Utils.SpUtil;
import com.yxld.yxchuangxin.contain.ContainValue;
import com.yxld.yxchuangxin.data.api.API;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.data.api.HttpApi;
import com.yxld.yxchuangxin.data.api.RequestBodyInterceptor;
import com.yxld.yxchuangxin.data.api.support.ErrorHandlerInterceptor;
import com.yxld.yxchuangxin.data.qualifier.Remote;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * @author hu
 * @desc 功能描述
 * @date 2017/5/31 10:04
 */
@Module
public final class APIModule {

    private final Application application;

    public APIModule(Application application) {
        this.application = application;
    }

    @Provides
    public OkHttpClient provideOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
            builder.addInterceptor(new ErrorHandlerInterceptor());
        }
        builder.connectTimeout(API.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(API.IO_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new RequestBodyInterceptor());
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        if (SpUtil.getBoolean(AppConfig.getInstance(), ContainValue.ENVIRONMENT, true)) {
            //线上环境
            builder.client(okHttpClient)
                    .baseUrl(API.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());
        } else {
            //开发环境
            if (SpUtil.getBoolean(AppConfig.getInstance(), ContainValue.ENTERURL, false)) {
                //选择手动输入url
                builder.client(okHttpClient)
                        .baseUrl(SpUtil.getString(AppConfig.getInstance(), ContainValue.URL, "").equals("") ? API.BASE_URL_DEVOLOP : SpUtil.getString(AppConfig.getInstance(), ContainValue.URL, ""))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create());
            } else {
                //默认选择默认的开发环境地质
                builder.client(okHttpClient)
                        .baseUrl(API.BASE_URL_DEVOLOP)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create());
            }
        }
        return builder.build();
    }

    @Provides
    @Singleton
    public HttpApi provideHttpAPI(Retrofit restAdapter) {
        return restAdapter.create(HttpApi.class);
    }

    //这里是对外输出部分
    @Provides
    @Singleton
    @Remote
    public HttpAPIWrapper provideHttpAPIWrapper(HttpApi httpAPI){
        return new HttpAPIWrapper(httpAPI);
    }
}
