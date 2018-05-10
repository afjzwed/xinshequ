package com.yxld.yxchuangxin.entity.adapter;

import java.util.List;

/**
 * 作者：hu on 2017/6/5
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class Wuye {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"icon":"R.mipmap.menjin","name":"门禁管理"},{"icon":"R.mipmap.cheliang","name":"车辆管理"},{"icon":"R.mipmap.anfangzaijia","name":"居家安防"},{"icon":"R.mipmap.ic_common","name":"公共区域"},{"icon":"R.mipmap.zufang","name":"房屋出租"}]
         * name : 我的物业
         */

        private String name;
        private List<ListBean> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * icon : R.mipmap.menjin
             * name : 门禁管理
             */

            private String icon;
            private String name;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
