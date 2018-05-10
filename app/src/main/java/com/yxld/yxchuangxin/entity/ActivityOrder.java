package com.yxld.yxchuangxin.entity;

import java.util.List;

/**
 * 出行fragment的模型
 * Created by Anroid on 2017/3/21.
 */

public class ActivityOrder extends BaseBack {

    /**
     * data : {"endRow":5,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"acceptTime":"","actualPrice":"","actualTravelTime":"","age":"","applyTime":"","baseAddress":"中南大学","baseAddressLabel":"","baseAddressLat":"28.1703380","baseAddressLng":"112.9387890","bespeakTime":"2017-03-24 19:15:00","bespeakTimeDesc":"","birthday":"","carBrand":"","carColor":"","carImg":"","carLogoImg":"","carModel":"","carNo":"","carOwner":"","checkState":"","credibility":"","destination":"烈士公园","destinationLat":"28.2145810","destinationLng":"112.9997640","discount":"","discountBgUrl":"","displayPic":"http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM199009162212464/userInfo/img1.jpg","distance":"","driverAge":"","driverBirthday":"","driverCode":"","driverCredibility":"","driverDisplayPic":"","driverEvaluate":0,"driverHoroscope":"","driverHoroscopeId":"","driverIdCard":"","driverImg":"","driverImg1":"","driverImg2":"","driverImg3":"","driverImg4":"","driverImg5":"","driverImg6":"","driverImg7":"","driverImg8":"","driverImg9":"","driverImpressionLabel":"","driverLoginTime":"","driverMobile":"","driverMobileId":"","driverName":"","driverNickname":"","driverPersonalitySign":"","driverPosition":"","driverPreference":"","driverProfession":"","driverProfessionId":"","driverRegisterTime":"","driverSex":"","driverUpdateTime":"","driverUserCode":"","driverUserType":"","drivingBookImg":"","horoscope":"","horoscopeId":"","iPickup":0,"id":"","idCard":"","img1":"","img2":"","img3":"","img4":"","img5":"","img6":"","img7":"","img8":"","img9":"","impressionLabel":"","isCollection":"1","isNopay":"0","isPay":"0","isPayOrder":"0","kilometreNo":15.2,"loginTime":"","mobile":"","mobileId":"","name":"","needPickup":0,"needSits":1,"nickname":"蓝小鱼","offerSits":4,"orderCode":"NO170323210000251148","orderType":"","overTime":"","passengerCode":"XM199009162212464","passengerEvaluate":0,"payStyle":"AA","personalitySign":"","position":"","preference":"","price":15.2,"profession":"","professionId":"","realname":"","registerDate":"","registerTime":"","releaseTime":"2017-03-23 21:00:00","sex":"","state":1,"timePlan":"","updateTime":"","userCode":"XM199009162212464","userType":"","version":0,"videoShotUrl":"http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM199009162212464/userInfo/img2.jpg","videoUrl":""}],"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":20,"pages":1,"prePage":0,"size":5,"startRow":1,"total":5}
     * return_code : 0
     * return_msg :
     */

    private DataBean data;
    private String return_code;
    private String return_msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * endRow : 5
         * firstPage : 1
         * hasNextPage : false
         * hasPreviousPage : false
         * isFirstPage : true
         * isLastPage : true
         * lastPage : 1
         * list : [{"acceptTime":"","actualPrice":"","actualTravelTime":"","age":"","applyTime":"","baseAddress":"中南大学","baseAddressLabel":"","baseAddressLat":"28.1703380","baseAddressLng":"112.9387890","bespeakTime":"2017-03-24 19:15:00","bespeakTimeDesc":"","birthday":"","carBrand":"","carColor":"","carImg":"","carLogoImg":"","carModel":"","carNo":"","carOwner":"","checkState":"","credibility":"","destination":"烈士公园","destinationLat":"28.2145810","destinationLng":"112.9997640","discount":"","discountBgUrl":"","displayPic":"http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM199009162212464/userInfo/img1.jpg","distance":"","driverAge":"","driverBirthday":"","driverCode":"","driverCredibility":"","driverDisplayPic":"","driverEvaluate":0,"driverHoroscope":"","driverHoroscopeId":"","driverIdCard":"","driverImg":"","driverImg1":"","driverImg2":"","driverImg3":"","driverImg4":"","driverImg5":"","driverImg6":"","driverImg7":"","driverImg8":"","driverImg9":"","driverImpressionLabel":"","driverLoginTime":"","driverMobile":"","driverMobileId":"","driverName":"","driverNickname":"","driverPersonalitySign":"","driverPosition":"","driverPreference":"","driverProfession":"","driverProfessionId":"","driverRegisterTime":"","driverSex":"","driverUpdateTime":"","driverUserCode":"","driverUserType":"","drivingBookImg":"","horoscope":"","horoscopeId":"","iPickup":0,"id":"","idCard":"","img1":"","img2":"","img3":"","img4":"","img5":"","img6":"","img7":"","img8":"","img9":"","impressionLabel":"","isCollection":"1","isNopay":"0","isPay":"0","isPayOrder":"0","kilometreNo":15.2,"loginTime":"","mobile":"","mobileId":"","name":"","needPickup":0,"needSits":1,"nickname":"蓝小鱼","offerSits":4,"orderCode":"NO170323210000251148","orderType":"","overTime":"","passengerCode":"XM199009162212464","passengerEvaluate":0,"payStyle":"AA","personalitySign":"","position":"","preference":"","price":15.2,"profession":"","professionId":"","realname":"","registerDate":"","registerTime":"","releaseTime":"2017-03-23 21:00:00","sex":"","state":1,"timePlan":"","updateTime":"","userCode":"XM199009162212464","userType":"","version":0,"videoShotUrl":"http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM199009162212464/userInfo/img2.jpg","videoUrl":""}]
         * navigatePages : 8
         * navigatepageNums : [1]
         * nextPage : 0
         * pageNum : 1
         * pageSize : 20
         * pages : 1
         * prePage : 0
         * size : 5
         * startRow : 1
         * total : 5
         */

        private String endRow;
        private String firstPage;
        private boolean hasNextPage;
        private boolean hasPreviousPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private String lastPage;
        private String navigatePages;
        private String nextPage;
        private int pageNum;
        private String pageSize;
        private String pages;
        private String prePage;
        private String size;
        private String startRow;
        private String total;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public String getEndRow() {
            return endRow;
        }

        public void setEndRow(String endRow) {
            this.endRow = endRow;
        }

        public String getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(String firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public String getLastPage() {
            return lastPage;
        }

        public void setLastPage(String lastPage) {
            this.lastPage = lastPage;
        }

        public String getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(String navigatePages) {
            this.navigatePages = navigatePages;
        }

        public String getNextPage() {
            return nextPage;
        }

        public void setNextPage(String nextPage) {
            this.nextPage = nextPage;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getPages() {
            return pages;
        }

        public void setPages(String pages) {
            this.pages = pages;
        }

        public String getPrePage() {
            return prePage;
        }

        public void setPrePage(String prePage) {
            this.prePage = prePage;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getStartRow() {
            return startRow;
        }

        public void setStartRow(String startRow) {
            this.startRow = startRow;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * acceptTime :
             * actualPrice :
             * actualTravelTime :
             * age :
             * applyTime :
             * baseAddress : 中南大学
             * baseAddressLabel :
             * baseAddressLat : 28.1703380
             * baseAddressLng : 112.9387890
             * bespeakTime : 2017-03-24 19:15:00
             * bespeakTimeDesc :
             * birthday :
             * carBrand :
             * carColor :
             * carImg :
             * carLogoImg :
             * carModel :
             * carNo :
             * carOwner :
             * checkState :
             * credibility :
             * destination : 烈士公园
             * destinationLat : 28.2145810
             * destinationLng : 112.9997640
             * discount :
             * discountBgUrl :
             * displayPic : http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM199009162212464/userInfo/img1.jpg
             * distance :
             * driverAge :
             * driverBirthday :
             * driverCode :
             * driverCredibility :
             * driverDisplayPic :
             * driverEvaluate : 0
             * driverHoroscope :
             * driverHoroscopeId :
             * driverIdCard :
             * driverImg :
             * driverImg1 :
             * driverImg2 :
             * driverImg3 :
             * driverImg4 :
             * driverImg5 :
             * driverImg6 :
             * driverImg7 :
             * driverImg8 :
             * driverImg9 :
             * driverImpressionLabel :
             * driverLoginTime :
             * driverMobile :
             * driverMobileId :
             * driverName :
             * driverNickname :
             * driverPersonalitySign :
             * driverPosition :
             * driverPreference :
             * driverProfession :
             * driverProfessionId :
             * driverRegisterTime :
             * driverSex :
             * driverUpdateTime :
             * driverUserCode :
             * driverUserType :
             * drivingBookImg :
             * horoscope :
             * horoscopeId :
             * iPickup : 0
             * id :
             * idCard :
             * img1 :
             * img2 :
             * img3 :
             * img4 :
             * img5 :
             * img6 :
             * img7 :
             * img8 :
             * img9 :
             * impressionLabel :
             * isCollection : 1
             * isNopay : 0
             * isPay : 0
             * isPayOrder : 0
             * kilometreNo : 15.2
             * loginTime :
             * mobile :
             * mobileId :
             * name :
             * needPickup : 0
             * needSits : 1
             * nickname : 蓝小鱼
             * offerSits : 4
             * orderCode : NO170323210000251148
             * orderType :
             * overTime :
             * passengerCode : XM199009162212464
             * passengerEvaluate : 0
             * payStyle : AA
             * personalitySign :
             * position :
             * preference :
             * price : 15.2
             * profession :
             * professionId :
             * realname :
             * registerDate :
             * registerTime :
             * releaseTime : 2017-03-23 21:00:00
             * sex :
             * state : 1
             * timePlan :
             * updateTime :
             * userCode : XM199009162212464
             * userType :
             * version : 0
             * videoShotUrl : http://xm-chuxing-app.oss-cn-hangzhou.aliyuncs.com/XM199009162212464/userInfo/img2.jpg
             * videoUrl :
             */

            private String acceptTime;
            private String actualPrice;
            private String actualTravelTime;
            private String age;
            private String applyTime;
            private String baseAddress;
            private String baseAddressLabel;
            private String baseAddressLat;
            private String baseAddressLng;
            private String bespeakTime;
            private String bespeakTimeDesc;
            private String birthday;
            private String carBrand;
            private String carColor;
            private String carImg;
            private String carLogoImg;
            private String carModel;
            private String carNo;
            private String carOwner;
            private String checkState;
            private String credibility;
            private String destination;
            private String destinationLat;
            private String destinationLng;
            private String discount;
            private String discountBgUrl;
            private String displayPic;
            private String distance;
            private String driverAge;
            private String driverBirthday;
            private String driverCode;
            private String driverCredibility;
            private String driverDisplayPic;
            private String driverEvaluate;
            private String driverHoroscope;
            private String driverHoroscopeId;
            private String driverIdCard;
            private String driverImg;
            private String driverImg1;
            private String driverImg2;
            private String driverImg3;
            private String driverImg4;
            private String driverImg5;
            private String driverImg6;
            private String driverImg7;
            private String driverImg8;
            private String driverImg9;
            private String driverImpressionLabel;
            private String driverLoginTime;
            private String driverMobile;
            private String driverMobileId;
            private String driverName;
            private String driverNickname;
            private String driverPersonalitySign;
            private String driverPosition;
            private String driverPreference;
            private String driverProfession;
            private String driverProfessionId;
            private String driverRegisterTime;
            private String driverSex;
            private String driverUpdateTime;
            private String driverUserCode;
            private String driverUserType;
            private String drivingBookImg;
            private String horoscope;
            private String horoscopeId;
            private String iPickup;
            private String id;
            private String idCard;
            private String img1;
            private String img2;
            private String img3;
            private String img4;
            private String img5;
            private String img6;
            private String img7;
            private String img8;
            private String img9;
            private String impressionLabel;
            private String isCollection;
            private String isNopay;
            private String isPay;
            private String isPayOrder;
            private String kilometreNo;
            private String loginTime;
            private String mobile;
            private String mobileId;
            private String name;
            private String needPickup;
            private String needSits;
            private String nickname;
            private String offerSits;
            private String orderCode;
            private String orderType;
            private String overTime;
            private String passengerCode;
            private String passengerEvaluate;
            private String payStyle;
            private String personalitySign;
            private String position;
            private String preference;
            private String price;
            private String profession;
            private String professionId;
            private String realname;
            private String registerDate;
            private String registerTime;
            private String releaseTime;
            private String sex;
            private String state;
            private String timePlan;
            private String updateTime;
            private String userCode;
            private String userType;
            private String version;
            private String videoShotUrl;
            private String videoUrl;

            public String getAcceptTime() {
                return acceptTime;
            }

            public void setAcceptTime(String acceptTime) {
                this.acceptTime = acceptTime;
            }

            public String getActualPrice() {
                return actualPrice;
            }

            public void setActualPrice(String actualPrice) {
                this.actualPrice = actualPrice;
            }

            public String getActualTravelTime() {
                return actualTravelTime;
            }

            public void setActualTravelTime(String actualTravelTime) {
                this.actualTravelTime = actualTravelTime;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public String getApplyTime() {
                return applyTime;
            }

            public void setApplyTime(String applyTime) {
                this.applyTime = applyTime;
            }

            public String getBaseAddress() {
                return baseAddress;
            }

            public void setBaseAddress(String baseAddress) {
                this.baseAddress = baseAddress;
            }

            public String getBaseAddressLabel() {
                return baseAddressLabel;
            }

            public void setBaseAddressLabel(String baseAddressLabel) {
                this.baseAddressLabel = baseAddressLabel;
            }

            public String getBaseAddressLat() {
                return baseAddressLat;
            }

            public void setBaseAddressLat(String baseAddressLat) {
                this.baseAddressLat = baseAddressLat;
            }

            public String getBaseAddressLng() {
                return baseAddressLng;
            }

            public void setBaseAddressLng(String baseAddressLng) {
                this.baseAddressLng = baseAddressLng;
            }

            public String getBespeakTime() {
                return bespeakTime;
            }

            public void setBespeakTime(String bespeakTime) {
                this.bespeakTime = bespeakTime;
            }

            public String getBespeakTimeDesc() {
                return bespeakTimeDesc;
            }

            public void setBespeakTimeDesc(String bespeakTimeDesc) {
                this.bespeakTimeDesc = bespeakTimeDesc;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getCarBrand() {
                return carBrand;
            }

            public void setCarBrand(String carBrand) {
                this.carBrand = carBrand;
            }

            public String getCarColor() {
                return carColor;
            }

            public void setCarColor(String carColor) {
                this.carColor = carColor;
            }

            public String getCarImg() {
                return carImg;
            }

            public void setCarImg(String carImg) {
                this.carImg = carImg;
            }

            public String getCarLogoImg() {
                return carLogoImg;
            }

            public void setCarLogoImg(String carLogoImg) {
                this.carLogoImg = carLogoImg;
            }

            public String getCarModel() {
                return carModel;
            }

            public void setCarModel(String carModel) {
                this.carModel = carModel;
            }

            public String getCarNo() {
                return carNo;
            }

            public void setCarNo(String carNo) {
                this.carNo = carNo;
            }

            public String getCarOwner() {
                return carOwner;
            }

            public void setCarOwner(String carOwner) {
                this.carOwner = carOwner;
            }

            public String getCheckState() {
                return checkState;
            }

            public void setCheckState(String checkState) {
                this.checkState = checkState;
            }

            public String getCredibility() {
                return credibility;
            }

            public void setCredibility(String credibility) {
                this.credibility = credibility;
            }

            public String getDestination() {
                return destination;
            }

            public void setDestination(String destination) {
                this.destination = destination;
            }

            public String getDestinationLat() {
                return destinationLat;
            }

            public void setDestinationLat(String destinationLat) {
                this.destinationLat = destinationLat;
            }

            public String getDestinationLng() {
                return destinationLng;
            }

            public void setDestinationLng(String destinationLng) {
                this.destinationLng = destinationLng;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getDiscountBgUrl() {
                return discountBgUrl;
            }

            public void setDiscountBgUrl(String discountBgUrl) {
                this.discountBgUrl = discountBgUrl;
            }

            public String getDisplayPic() {
                return displayPic;
            }

            public void setDisplayPic(String displayPic) {
                this.displayPic = displayPic;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getDriverAge() {
                return driverAge;
            }

            public void setDriverAge(String driverAge) {
                this.driverAge = driverAge;
            }

            public String getDriverBirthday() {
                return driverBirthday;
            }

            public void setDriverBirthday(String driverBirthday) {
                this.driverBirthday = driverBirthday;
            }

            public String getDriverCode() {
                return driverCode;
            }

            public void setDriverCode(String driverCode) {
                this.driverCode = driverCode;
            }

            public String getDriverCredibility() {
                return driverCredibility;
            }

            public void setDriverCredibility(String driverCredibility) {
                this.driverCredibility = driverCredibility;
            }

            public String getDriverDisplayPic() {
                return driverDisplayPic;
            }

            public void setDriverDisplayPic(String driverDisplayPic) {
                this.driverDisplayPic = driverDisplayPic;
            }

            public String getDriverEvaluate() {
                return driverEvaluate;
            }

            public void setDriverEvaluate(String driverEvaluate) {
                this.driverEvaluate = driverEvaluate;
            }

            public String getDriverHoroscope() {
                return driverHoroscope;
            }

            public void setDriverHoroscope(String driverHoroscope) {
                this.driverHoroscope = driverHoroscope;
            }

            public String getDriverHoroscopeId() {
                return driverHoroscopeId;
            }

            public void setDriverHoroscopeId(String driverHoroscopeId) {
                this.driverHoroscopeId = driverHoroscopeId;
            }

            public String getDriverIdCard() {
                return driverIdCard;
            }

            public void setDriverIdCard(String driverIdCard) {
                this.driverIdCard = driverIdCard;
            }

            public String getDriverImg() {
                return driverImg;
            }

            public void setDriverImg(String driverImg) {
                this.driverImg = driverImg;
            }

            public String getDriverImg1() {
                return driverImg1;
            }

            public void setDriverImg1(String driverImg1) {
                this.driverImg1 = driverImg1;
            }

            public String getDriverImg2() {
                return driverImg2;
            }

            public void setDriverImg2(String driverImg2) {
                this.driverImg2 = driverImg2;
            }

            public String getDriverImg3() {
                return driverImg3;
            }

            public void setDriverImg3(String driverImg3) {
                this.driverImg3 = driverImg3;
            }

            public String getDriverImg4() {
                return driverImg4;
            }

            public void setDriverImg4(String driverImg4) {
                this.driverImg4 = driverImg4;
            }

            public String getDriverImg5() {
                return driverImg5;
            }

            public void setDriverImg5(String driverImg5) {
                this.driverImg5 = driverImg5;
            }

            public String getDriverImg6() {
                return driverImg6;
            }

            public void setDriverImg6(String driverImg6) {
                this.driverImg6 = driverImg6;
            }

            public String getDriverImg7() {
                return driverImg7;
            }

            public void setDriverImg7(String driverImg7) {
                this.driverImg7 = driverImg7;
            }

            public String getDriverImg8() {
                return driverImg8;
            }

            public void setDriverImg8(String driverImg8) {
                this.driverImg8 = driverImg8;
            }

            public String getDriverImg9() {
                return driverImg9;
            }

            public void setDriverImg9(String driverImg9) {
                this.driverImg9 = driverImg9;
            }

            public String getDriverImpressionLabel() {
                return driverImpressionLabel;
            }

            public void setDriverImpressionLabel(String driverImpressionLabel) {
                this.driverImpressionLabel = driverImpressionLabel;
            }

            public String getDriverLoginTime() {
                return driverLoginTime;
            }

            public void setDriverLoginTime(String driverLoginTime) {
                this.driverLoginTime = driverLoginTime;
            }

            public String getDriverMobile() {
                return driverMobile;
            }

            public void setDriverMobile(String driverMobile) {
                this.driverMobile = driverMobile;
            }

            public String getDriverMobileId() {
                return driverMobileId;
            }

            public void setDriverMobileId(String driverMobileId) {
                this.driverMobileId = driverMobileId;
            }

            public String getDriverName() {
                return driverName;
            }

            public void setDriverName(String driverName) {
                this.driverName = driverName;
            }

            public String getDriverNickname() {
                return driverNickname;
            }

            public void setDriverNickname(String driverNickname) {
                this.driverNickname = driverNickname;
            }

            public String getDriverPersonalitySign() {
                return driverPersonalitySign;
            }

            public void setDriverPersonalitySign(String driverPersonalitySign) {
                this.driverPersonalitySign = driverPersonalitySign;
            }

            public String getDriverPosition() {
                return driverPosition;
            }

            public void setDriverPosition(String driverPosition) {
                this.driverPosition = driverPosition;
            }

            public String getDriverPreference() {
                return driverPreference;
            }

            public void setDriverPreference(String driverPreference) {
                this.driverPreference = driverPreference;
            }

            public String getDriverProfession() {
                return driverProfession;
            }

            public void setDriverProfession(String driverProfession) {
                this.driverProfession = driverProfession;
            }

            public String getDriverProfessionId() {
                return driverProfessionId;
            }

            public void setDriverProfessionId(String driverProfessionId) {
                this.driverProfessionId = driverProfessionId;
            }

            public String getDriverRegisterTime() {
                return driverRegisterTime;
            }

            public void setDriverRegisterTime(String driverRegisterTime) {
                this.driverRegisterTime = driverRegisterTime;
            }

            public String getDriverSex() {
                return driverSex;
            }

            public void setDriverSex(String driverSex) {
                this.driverSex = driverSex;
            }

            public String getDriverUpdateTime() {
                return driverUpdateTime;
            }

            public void setDriverUpdateTime(String driverUpdateTime) {
                this.driverUpdateTime = driverUpdateTime;
            }

            public String getDriverUserCode() {
                return driverUserCode;
            }

            public void setDriverUserCode(String driverUserCode) {
                this.driverUserCode = driverUserCode;
            }

            public String getDriverUserType() {
                return driverUserType;
            }

            public void setDriverUserType(String driverUserType) {
                this.driverUserType = driverUserType;
            }

            public String getDrivingBookImg() {
                return drivingBookImg;
            }

            public void setDrivingBookImg(String drivingBookImg) {
                this.drivingBookImg = drivingBookImg;
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

            public String getIPickup() {
                return iPickup;
            }

            public void setIPickup(String iPickup) {
                this.iPickup = iPickup;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
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

            public String getImg5() {
                return img5;
            }

            public void setImg5(String img5) {
                this.img5 = img5;
            }

            public String getImg6() {
                return img6;
            }

            public void setImg6(String img6) {
                this.img6 = img6;
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

            public String getImg9() {
                return img9;
            }

            public void setImg9(String img9) {
                this.img9 = img9;
            }

            public String getImpressionLabel() {
                return impressionLabel;
            }

            public void setImpressionLabel(String impressionLabel) {
                this.impressionLabel = impressionLabel;
            }

            public String getIsCollection() {
                return isCollection;
            }

            public void setIsCollection(String isCollection) {
                this.isCollection = isCollection;
            }

            public String getIsNopay() {
                return isNopay;
            }

            public void setIsNopay(String isNopay) {
                this.isNopay = isNopay;
            }

            public String getIsPay() {
                return isPay;
            }

            public void setIsPay(String isPay) {
                this.isPay = isPay;
            }

            public String getIsPayOrder() {
                return isPayOrder;
            }

            public void setIsPayOrder(String isPayOrder) {
                this.isPayOrder = isPayOrder;
            }

            public String getKilometreNo() {
                return kilometreNo;
            }

            public void setKilometreNo(String kilometreNo) {
                this.kilometreNo = kilometreNo;
            }

            public String getLoginTime() {
                return loginTime;
            }

            public void setLoginTime(String loginTime) {
                this.loginTime = loginTime;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getMobileId() {
                return mobileId;
            }

            public void setMobileId(String mobileId) {
                this.mobileId = mobileId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNeedPickup() {
                return needPickup;
            }

            public void setNeedPickup(String needPickup) {
                this.needPickup = needPickup;
            }

            public String getNeedSits() {
                return needSits;
            }

            public void setNeedSits(String needSits) {
                this.needSits = needSits;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getOfferSits() {
                return offerSits;
            }

            public void setOfferSits(String offerSits) {
                this.offerSits = offerSits;
            }

            public String getOrderCode() {
                return orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }

            public String getOrderType() {
                return orderType;
            }

            public void setOrderType(String orderType) {
                this.orderType = orderType;
            }

            public String getOverTime() {
                return overTime;
            }

            public void setOverTime(String overTime) {
                this.overTime = overTime;
            }

            public String getPassengerCode() {
                return passengerCode;
            }

            public void setPassengerCode(String passengerCode) {
                this.passengerCode = passengerCode;
            }

            public String getPassengerEvaluate() {
                return passengerEvaluate;
            }

            public void setPassengerEvaluate(String passengerEvaluate) {
                this.passengerEvaluate = passengerEvaluate;
            }

            public String getPayStyle() {
                return payStyle;
            }

            public void setPayStyle(String payStyle) {
                this.payStyle = payStyle;
            }

            public String getPersonalitySign() {
                return personalitySign;
            }

            public void setPersonalitySign(String personalitySign) {
                this.personalitySign = personalitySign;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getPreference() {
                return preference;
            }

            public void setPreference(String preference) {
                this.preference = preference;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getProfession() {
                return profession;
            }

            public void setProfession(String profession) {
                this.profession = profession;
            }

            public String getProfessionId() {
                return professionId;
            }

            public void setProfessionId(String professionId) {
                this.professionId = professionId;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getRegisterDate() {
                return registerDate;
            }

            public void setRegisterDate(String registerDate) {
                this.registerDate = registerDate;
            }

            public String getRegisterTime() {
                return registerTime;
            }

            public void setRegisterTime(String registerTime) {
                this.registerTime = registerTime;
            }

            public String getReleaseTime() {
                return releaseTime;
            }

            public void setReleaseTime(String releaseTime) {
                this.releaseTime = releaseTime;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getTimePlan() {
                return timePlan;
            }

            public void setTimePlan(String timePlan) {
                this.timePlan = timePlan;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public String getUserType() {
                return userType;
            }

            public void setUserType(String userType) {
                this.userType = userType;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getVideoShotUrl() {
                return videoShotUrl;
            }

            public void setVideoShotUrl(String videoShotUrl) {
                this.videoShotUrl = videoShotUrl;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }
        }
    }
}
