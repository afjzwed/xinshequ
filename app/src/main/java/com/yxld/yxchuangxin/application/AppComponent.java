/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yxld.yxchuangxin.application;

import android.content.Context;

import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author yuyh.
 * @date 2016/8/3.
 */
@Singleton
@Component(modules = {AppModule.class,        //全局变量、工具类、上下文的提供Module
        APIModule.class})                     //数据提供Module
public interface AppComponent {

    Context getContext();

    HttpAPIWrapper getHttpApiWrapper();

//    DaoSession getDaoSession();

    void inject (AppConfig appConfig);

}