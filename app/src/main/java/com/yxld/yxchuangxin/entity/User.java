package com.yxld.yxchuangxin.entity;

/**
 * Created by hu on 2017/5/16.
 */

public class User extends BaseBack{

    /**
     * return_code : 0
     * return_msg :
     * data : {"loginTime":"2016-10-31 15:14:43","driverImg":"http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM161031151357652659/carInfo/driverImg.jpg","profession":"IT,0,1","personalitySign":"罗里吧嗦娃娃","registerTime":"2016-10-31 15:14:43","userType":"","city":"","time":"","mobileId":"","age":27,"horoscope":"摩羯座","horoscopeId":"","displayPic":"http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM161031151357652659/userInfo/img1.jpg","imgScore":"","checkState":1,"img2":"http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM161031151357652659/userInfo/img2.jpg","img1":"http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM161031151357652659/userInfo/img1.jpg","userCode":"XM161031151357652659","updateTime":"2017-05-02 12:21:37","nickname":"小龙","reportScore":"","isDing":"","img9":"","img7":"","img8":"","img5":"","carColor":"黑色","img6":"http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM161031151357652659/userInfo/img6.jpg","img3":"http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM161031151357652659/userInfo/img3.jpg","img4":"","mobile":"18670819116","renegeScore":"","credibility":3,"position":"","birthday":"1990-01-04","sex":"男","dataComplete":"","carOwner":"","registerDate":"2016-11-03 11:18:52","applyTime":"","lng":"","impressionLabel":"长腿欧巴,闷骚大叔,长坏了","id":"","distance":"","carImg":"","name":"","evaluateScore":"","lat":"","idCard":"","professionId":"","carBrand":"宝马","drivingBookImg":"http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM161031151357652659/carInfo/drivingBookImg.jpg","carLogoImg":"http://xm-chuxing-common.oss-cn-hangzhou.aliyuncs.com/carLogo/baoma.png","carNo":"湘ADHSJS","carModel":"宝马X5","preference":"唱歌","sumScore":"","broadcastList":"","realname":"胡智鹏"}
     */

    private String return_code;
    private String return_msg;
    private DataBean data;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * loginTime : 2016-10-31 15:14:43
         * driverImg : http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM161031151357652659/carInfo/driverImg.jpg
         * profession : IT,0,1
         * personalitySign : 罗里吧嗦娃娃
         * registerTime : 2016-10-31 15:14:43
         * userType :
         * city :
         * time :
         * mobileId :
         * age : 27
         * horoscope : 摩羯座
         * horoscopeId :
         * displayPic : http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM161031151357652659/userInfo/img1.jpg
         * imgScore :
         * checkState : 1
         * img2 : http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM161031151357652659/userInfo/img2.jpg
         * img1 : http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM161031151357652659/userInfo/img1.jpg
         * userCode : XM161031151357652659
         * updateTime : 2017-05-02 12:21:37
         * nickname : 小龙
         * reportScore :
         * isDing :
         * img9 :
         * img7 :
         * img8 :
         * img5 :
         * carColor : 黑色
         * img6 : http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM161031151357652659/userInfo/img6.jpg
         * img3 : http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM161031151357652659/userInfo/img3.jpg
         * img4 :
         * mobile : 18670819116
         * renegeScore :
         * credibility : 3
         * position :
         * birthday : 1990-01-04
         * sex : 男
         * dataComplete :
         * carOwner :
         * registerDate : 2016-11-03 11:18:52
         * applyTime :
         * lng :
         * impressionLabel : 长腿欧巴,闷骚大叔,长坏了
         * id :
         * distance :
         * carImg :
         * name :
         * evaluateScore :
         * lat :
         * idCard :
         * professionId :
         * carBrand : 宝马
         * drivingBookImg : http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM161031151357652659/carInfo/drivingBookImg.jpg
         * carLogoImg : http://xm-chuxing-common.oss-cn-hangzhou.aliyuncs.com/carLogo/baoma.png
         * carNo : 湘ADHSJS
         * carModel : 宝马X5
         * preference : 唱歌
         * sumScore :
         * broadcastList :
         * realname : 胡智鹏
         */

        private String loginTime;
        private String driverImg;
        private String profession;
        private String personalitySign;
        private String registerTime;
        private String userType;
        private String city;
        private String time;
        private String mobileId;
        private int age;
        private String horoscope;
        private String horoscopeId;
        private String displayPic;
        private String imgScore;
        private int checkState;
        private String img2;
        private String img1;
        private String userCode;
        private String updateTime;
        private String nickname;
        private String reportScore;
        private String isDing;
        private String img9;
        private String img7;
        private String img8;
        private String img5;
        private String carColor;
        private String img6;
        private String img3;
        private String img4;
        private String mobile;
        private String renegeScore;
        private int credibility;
        private String position;
        private String birthday;
        private String sex;
        private String dataComplete;
        private String carOwner;
        private String registerDate;
        private String applyTime;
        private String lng;
        private String impressionLabel;
        private String id;
        private String distance;
        private String carImg;
        private String name;
        private String evaluateScore;
        private String lat;
        private String idCard;
        private String professionId;
        private String carBrand;
        private String drivingBookImg;
        private String carLogoImg;
        private String carNo;
        private String carModel;
        private String preference;
        private String sumScore;
        private String broadcastList;
        private String realname;

        public String getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(String loginTime) {
            this.loginTime = loginTime;
        }

        public String getDriverImg() {
            return driverImg;
        }

        public void setDriverImg(String driverImg) {
            this.driverImg = driverImg;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getPersonalitySign() {
            return personalitySign;
        }

        public void setPersonalitySign(String personalitySign) {
            this.personalitySign = personalitySign;
        }

        public String getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(String registerTime) {
            this.registerTime = registerTime;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getMobileId() {
            return mobileId;
        }

        public void setMobileId(String mobileId) {
            this.mobileId = mobileId;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getHoroscope() {
            return horoscope;
        }

        public void setHoroscope(String horoscope) {
            this.horoscope = horoscope;
        }

        public String getHoroscopeId() {
            return horoscopeId;
        }

        public void setHoroscopeId(String horoscopeId) {
            this.horoscopeId = horoscopeId;
        }

        public String getDisplayPic() {
            return displayPic;
        }

        public void setDisplayPic(String displayPic) {
            this.displayPic = displayPic;
        }

        public String getImgScore() {
            return imgScore;
        }

        public void setImgScore(String imgScore) {
            this.imgScore = imgScore;
        }

        public int getCheckState() {
            return checkState;
        }

        public void setCheckState(int checkState) {
            this.checkState = checkState;
        }

        public String getImg2() {
            return img2;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getReportScore() {
            return reportScore;
        }

        public void setReportScore(String reportScore) {
            this.reportScore = reportScore;
        }

        public String getIsDing() {
            return isDing;
        }

        public void setIsDing(String isDing) {
            this.isDing = isDing;
        }

        public String getImg9() {
            return img9;
        }

        public void setImg9(String img9) {
            this.img9 = img9;
        }

        public String getImg7() {
            return img7;
        }

        public void setImg7(String img7) {
            this.img7 = img7;
        }

        public String getImg8() {
            return img8;
        }

        public void setImg8(String img8) {
            this.img8 = img8;
        }

        public String getImg5() {
            return img5;
        }

        public void setImg5(String img5) {
            this.img5 = img5;
        }

        public String getCarColor() {
            return carColor;
        }

        public void setCarColor(String carColor) {
            this.carColor = carColor;
        }

        public String getImg6() {
            return img6;
        }

        public void setImg6(String img6) {
            this.img6 = img6;
        }

        public String getImg3() {
            return img3;
        }

        public void setImg3(String img3) {
            this.img3 = img3;
        }

        public String getImg4() {
            return img4;
        }

        public void setImg4(String img4) {
            this.img4 = img4;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRenegeScore() {
            return renegeScore;
        }

        public void setRenegeScore(String renegeScore) {
            this.renegeScore = renegeScore;
        }

        public int getCredibility() {
            return credibility;
        }

        public void setCredibility(int credibility) {
            this.credibility = credibility;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getDataComplete() {
            return dataComplete;
        }

        public void setDataComplete(String dataComplete) {
            this.dataComplete = dataComplete;
        }

        public String getCarOwner() {
            return carOwner;
        }

        public void setCarOwner(String carOwner) {
            this.carOwner = carOwner;
        }

        public String getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
        }

        public String getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(String applyTime) {
            this.applyTime = applyTime;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getImpressionLabel() {
            return impressionLabel;
        }

        public void setImpressionLabel(String impressionLabel) {
            this.impressionLabel = impressionLabel;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getCarImg() {
            return carImg;
        }

        public void setCarImg(String carImg) {
            this.carImg = carImg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEvaluateScore() {
            return evaluateScore;
        }

        public void setEvaluateScore(String evaluateScore) {
            this.evaluateScore = evaluateScore;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getProfessionId() {
            return professionId;
        }

        public void setProfessionId(String professionId) {
            this.professionId = professionId;
        }

        public String getCarBrand() {
            return carBrand;
        }

        public void setCarBrand(String carBrand) {
            this.carBrand = carBrand;
        }

        public String getDrivingBookImg() {
            return drivingBookImg;
        }

        public void setDrivingBookImg(String drivingBookImg) {
            this.drivingBookImg = drivingBookImg;
        }

        public String getCarLogoImg() {
            return carLogoImg;
        }

        public void setCarLogoImg(String carLogoImg) {
            this.carLogoImg = carLogoImg;
        }

        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
        }

        public String getCarModel() {
            return carModel;
        }

        public void setCarModel(String carModel) {
            this.carModel = carModel;
        }

        public String getPreference() {
            return preference;
        }

        public void setPreference(String preference) {
            this.preference = preference;
        }

        public String getSumScore() {
            return sumScore;
        }

        public void setSumScore(String sumScore) {
            this.sumScore = sumScore;
        }

        public String getBroadcastList() {
            return broadcastList;
        }

        public void setBroadcastList(String broadcastList) {
            this.broadcastList = broadcastList;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "\nloginTime='" + loginTime + '\'' +
                    ", \ndriverImg='" + driverImg + '\'' +
                    ", \nprofession='" + profession + '\'' +
                    ", \npersonalitySign='" + personalitySign + '\'' +
                    ", \nregisterTime='" + registerTime + '\'' +
                    ", \nuserType='" + userType + '\'' +
                    ", \ncity='" + city + '\'' +
                    ", \ntime='" + time + '\'' +
                    ", \nmobileId='" + mobileId + '\'' +
                    ", \nage=" + age +
                    ", \nhoroscope='" + horoscope + '\'' +
                    ", \nhoroscopeId='" + horoscopeId + '\'' +
                    ", \ndisplayPic='" + displayPic + '\'' +
                    ", \nimgScore='" + imgScore + '\'' +
                    ", \ncheckState=" + checkState +
                    ", \nimg2='" + img2 + '\'' +
                    ", \nimg1='" + img1 + '\'' +
                    ", \nuserCode='" + userCode + '\'' +
                    ", \nupdateTime='" + updateTime + '\'' +
                    ", \nnickname='" + nickname + '\'' +
                    ", \nreportScore='" + reportScore + '\'' +
                    ", \nisDing='" + isDing + '\'' +
                    ", \nimg9='" + img9 + '\'' +
                    ", \nimg7='" + img7 + '\'' +
                    ", \nimg8='" + img8 + '\'' +
                    ", \nimg5='" + img5 + '\'' +
                    ", \ncarColor='" + carColor + '\'' +
                    ", \nimg6='" + img6 + '\'' +
                    ", \nimg3='" + img3 + '\'' +
                    ", \nimg4='" + img4 + '\'' +
                    ", \nmobile='" + mobile + '\'' +
                    ", \nrenegeScore='" + renegeScore + '\'' +
                    ", \ncredibility=" + credibility +
                    ", \nposition='" + position + '\'' +
                    ", \nbirthday='" + birthday + '\'' +
                    ", \nsex='" + sex + '\'' +
                    ", \ndataComplete='" + dataComplete + '\'' +
                    ", \ncarOwner='" + carOwner + '\'' +
                    ", \nregisterDate='" + registerDate + '\'' +
                    ", \napplyTime='" + applyTime + '\'' +
                    ", \nlng='" + lng + '\'' +
                    ", \nimpressionLabel='" + impressionLabel + '\'' +
                    ", \nid='" + id + '\'' +
                    ", \ndistance='" + distance + '\'' +
                    ", \ncarImg='" + carImg + '\'' +
                    ", \nname='" + name + '\'' +
                    ", \nevaluateScore='" + evaluateScore + '\'' +
                    ", \nlat='" + lat + '\'' +
                    ", \nidCard='" + idCard + '\'' +
                    ", \nprofessionId='" + professionId + '\'' +
                    ", \ncarBrand='" + carBrand + '\'' +
                    ", \ndrivingBookImg='" + drivingBookImg + '\'' +
                    ", \ncarLogoImg='" + carLogoImg + '\'' +
                    ", \ncarNo='" + carNo + '\'' +
                    ", \ncarModel='" + carModel + '\'' +
                    ", \npreference='" + preference + '\'' +
                    ", \nsumScore='" + sumScore + '\'' +
                    ", \nbroadcastList='" + broadcastList + '\'' +
                    ", \nrealname='" + realname + '\'' +
                    '}';
        }
    }
}
