package com.yxld.yxchuangxin.entity;

import java.util.List;

/**
 * 作者：hu on 2017/6/27
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class Question {

    /**
     * data : [{"id":1,"question":"您对物业服务人员统一着装、佩戴工牌是否满意？","img":"app/public/images/banner.png","answer":[{"name":"非常满意","value":"0"},{"name":"满意","value":"1"},{"name":"一般","value":"2"},{"name":"不满意","value":"3"}]},{"id":2,"question":"您对物业服务人员行为规范、热情服务是否满意？","img":"app/public/images/banner.png","answer":[{"name":"非常满意","value":"0"},{"name":"满意","value":"1"},{"name":"一般","value":"2"},{"name":"不满意","value":"3"}]},{"id":3,"question":"您对物业服务热线、及时与业主沟通互动是否满意？","img":"app/public/images/banner.png","answer":[{"name":"非常满意","value":"0"},{"name":"满意","value":"1"},{"name":"一般","value":"2"},{"name":"不满意","value":"3"}]},{"id":4,"question":"您对小区楼宇室内卫生是否满意？","img":"app/public/images/banner.png","answer":[{"name":"非常满意","value":"0"},{"name":"满意","value":"1"},{"name":"一般","value":"2"},{"name":"不满意","value":"3"}]},{"id":5,"question":"您对室外道路、公共场所等卫生是否满意？","img":"app/public/images/banner.png","answer":[{"name":"非常满意","value":"0"},{"name":"满意","value":"1"},{"name":"一般","value":"2"},{"name":"不满意","value":"3"}]},{"id":6,"question":"您对绿化绿植维护养护工作是否满意？","img":"app/public/images/banner.png","answer":[{"name":"非常满意","value":"0"},{"name":"满意","value":"1"},{"name":"一般","value":"2"},{"name":"不满意","value":"3"}]},{"id":7,"question":"您对公共秩序维护工作是否满意？","img":"app/public/images/banner.png","answer":[{"name":"非常满意","value":"0"},{"name":"满意","value":"1"},{"name":"一般","value":"2"},{"name":"不满意","value":"3"}]},{"id":8,"question":"您对小区车辆管理及外来人员控制是否满意？","img":"app/public/images/banner.png","answer":[{"name":"非常满意","value":"0"},{"name":"满意","value":"1"},{"name":"一般","value":"2"},{"name":"不满意","value":"3"}]},{"id":9,"question":"您对公共区域消防控制管理是否满意？","img":"app/public/images/banner.png","answer":[{"name":"非常满意","value":"0"},{"name":"满意","value":"1"},{"name":"一般","value":"2"},{"name":"不满意","value":"3"}]},{"id":10,"question":"您对公共设施设备的配置是否满意？","img":"app/public/images/banner.png","answer":[{"name":"非常满意","value":"0"},{"name":"满意","value":"1"},{"name":"一般","value":"2"},{"name":"不满意","value":"3"}]},{"id":11,"question":"您对公共设施设备的正常运行是否满意？","img":"app/public/images/banner.png","answer":[{"name":"非常满意","value":"0"},{"name":"满意","value":"1"},{"name":"一般","value":"2"},{"name":"不满意","value":"3"}]},{"id":12,"question":"您对小区工程维修的及时、质量、回访是否满意？","img":"app/public/images/banner.png","answer":[{"name":"非常满意","value":"0"},{"name":"满意","value":"1"},{"name":"一般","value":"2"},{"name":"不满意","value":"3"}]},{"id":13,"question":"您对小区工程维修人员的服务热情、态度是否满意？","img":"app/public/images/banner.png","answer":[{"name":"非常满意","value":"0"},{"name":"满意","value":"1"},{"name":"一般","value":"2"},{"name":"不满意","value":"3"}]},{"id":14,"question":"与本市其它小区相比，您对创欣物业服务的总体评价为？","img":"app/public/images/banner.png","answer":[{"name":"高档","value":"0"},{"name":"中高档","value":"1"},{"name":"一般","value":"2"},{"name":"低档","value":"3"}]}]
     * success : true
     * msg :
     */

    private boolean success;
    private String msg;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * question : 您对物业服务人员统一着装、佩戴工牌是否满意？
         * img : app/public/images/banner.png
         * answer : [{"name":"非常满意","value":"0"},{"name":"满意","value":"1"},{"name":"一般","value":"2"},{"name":"不满意","value":"3"}]
         */

        private int id;
        private String question;
        private String img;
        private List<AnswerBean> answer;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public List<AnswerBean> getAnswer() {
            return answer;
        }

        public void setAnswer(List<AnswerBean> answer) {
            this.answer = answer;
        }

        public static class AnswerBean {
            /**
             * name : 非常满意
             * value : 0
             */

            private String name;
            private String value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
