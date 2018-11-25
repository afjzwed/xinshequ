package com.yxld.yxchuangxin.Utils;

import com.socks.library.KLog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hu on 2017/5/5.
 */

public class TimeUtil {
    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     *
     * @param time
     * @return
     */
    public static String timesTamp2Year(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @SuppressWarnings("unused")
        String times = sdr.format(new Date(time));
        return times;
    }

    /**
     * 到日
     * @param time
     * @return
     */
    public static String timesTamp2Year1(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressWarnings("unused")
        String times = sdr.format(new Date(time));
        return times;
    }
    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     *
     * @param time
     * @return
     */
    public static String timesTamp2YearMonthDay(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressWarnings("unused")
        String times = sdr.format(new Date(time));
        return times;
    }

    /**
     * 获取未来7天的集合（格式为：周末 5月7日）
     */
    public static ArrayList<String> getDate() {
        // + 7*24*60*60*1000
        ArrayList<String> backList = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        final long mills = c.getTimeInMillis();
        Calendar c7 = Calendar.getInstance();
        for (long i = 0; i < 7; i++) {
            ArrayList<String> dateList = new ArrayList<>();
            c7.setTimeInMillis(mills + (i * 24 * 60 * 60 * 1000));
//            KLog.i(timesTamp2Year(c7.getTimeInMillis()));
//            Logger.i(c7.getTimeInMillis() + "");
//            if (i == 1) {
//                String date = "明天" + (c7.get(Calendar.MONTH) + 1) + "月" + c7.get(Calendar.DAY_OF_MONTH) + "日";
//                backList.add(date);
//            } else if (i == 2) {
//                String date = "后天" + (c7.get(Calendar.MONTH) + 1) + "月" + c7.get(Calendar.DAY_OF_MONTH) + "日";
//                backList.add(date);
//            } else if (i == 0) {
//                String date = "今天" + (c7.get(Calendar.MONTH) + 1) + "月" + c7.get(Calendar.DAY_OF_MONTH) + "日";
//                backList.add(date);
//            } else {
            String date1=mills + (i * 24 * 60 * 60 * 1000)+"";
                String date = c7.get(Calendar.YEAR) +"-" +String.format("%02d",c7.get(Calendar.MONTH) + 1) + "-" + String.format("%02d",c7.get(Calendar.DAY_OF_MONTH)) + "";
                backList.add(date1);
//            }
//            if (i == 0) {
//                dateList.add("0");
//            } else {
//                dateList.add("1");
//            }
//            backList.add(dateList);
        }
        return backList;
//        ArrayList<String> backList = new ArrayList<>();
//        Calendar c = Calendar.getInstance();
//        final long mills = c.getTimeInMillis();
//        Calendar c7 = Calendar.getInstance();
//        for (long i = 0; i < 7; i++) {
//            ArrayList<String> dateList = new ArrayList<>();
//            c7.setTimeInMillis(mills + (i * 24 * 60 * 60 * 1000));
////            KLog.i(timesTamp2Year(c7.getTimeInMillis()));
////            Logger.i(c7.getTimeInMillis() + "");
//            if (i == 1) {
//                String date = "明天" + (c7.get(Calendar.MONTH) + 1) + "月" + c7.get(Calendar.DAY_OF_MONTH) + "日";
//                backList.add(date);
//            } else if (i == 2) {
//                String date = "后天" + (c7.get(Calendar.MONTH) + 1) + "月" + c7.get(Calendar.DAY_OF_MONTH) + "日";
//                backList.add(date);
//            } else if (i == 0) {
//                String date = "今天" + (c7.get(Calendar.MONTH) + 1) + "月" + c7.get(Calendar.DAY_OF_MONTH) + "日";
//                backList.add(date);
//            } else {
//                String date = "周" + en2Zh(c7.get(Calendar.DAY_OF_WEEK)) + (c7.get(Calendar.MONTH) + 1) + "月" + c7.get(Calendar.DAY_OF_MONTH) + "日";
//                backList.add(date);
//            }
////            if (i == 0) {
////                dateList.add("0");
////            } else {
////                dateList.add("1");
////            }
////            backList.add(dateList);
//        }
//        return backList;
    }

    public static String en2Zh(int day) {
        switch (day) {
            case 2:
                return "一";
            case 3:
                return "二";
            case 4:
                return "三";
            case 5:
                return "四";
            case 6:
                return "五";
            case 7:
                return "六";
            case 1:
                return "日";
        }
        return "";
    }

    /**
     * @param flag 0表示从0点开始， 1表示从当天的小时开始
     * @return
     */
    public static ArrayList<String> get24(int flag) {
        ArrayList<String> backList = new ArrayList<>();
        if (flag != 1) {
            for (int hour = 0; hour < 24; hour++) {
                for (int min = 0; min < 60; min++) {
                    if (min % 15 == 0) {
                        String time =  String.format("%02d", hour) + ":" + String.format("%02d", min);
                        backList.add(time);
                    }
                }
            }
        } else {
            for (int i = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1; i < 24; i++) {
                for (int min = 0; min < 60; min++) {
                    if (min % 15 == 0) {
                        String time =  String.format("%02d", i) + ":" + String.format("%02d", min);
                        backList.add(time);
                    }
                }
            }
        }
//            ArrayList<String> backList = new ArrayList<>();
//            if (flag != 1) {
//                for (int i = 0; i <= 23; i++) {
//                    String time = i + ":00~" + (i + 1) + ":00";
//                    backList.add(time);
//                }
//            } else {
//                for (int i = Calendar.getInstance().get(Calendar.HOUR_OF_DAY); i <= 23; i++) {
//                    String time = i + ":00~" + (i + 1) + ":00";
//                    backList.add(time);
//                }
//            }
        return backList;
    }

    /**
     * 调此方法输入所要转换的时间输入例如（"2014-06-14 16:09:00"）返回时间戳
     *
     * @param time
     * @return
     */

    public static long timeStamp(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date date;
        KLog.i(time);
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            KLog.i(l);
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return new Long("123");
        }
    }

    /**
     * 调用此方法，返回输入的时间与当时的时间相差的分和秒
     */

    public static String getiDefferTime(long defferTime) {
//        long tenMinute = 10 * 60 * 1000;
//        long tenMinute = SpUtil.getInt(AppConfig.getInstance(), ContainValue.INTERVALTIME, 600) * 1000;
//        long defferTime = tenMinute - (Calendar.getInstance().getTimeInMillis() - timeStamp(time));
//        KLog.i(defferTime / 1000);
        long minute = defferTime / 60;
        long second = defferTime % 60;
        return minute + "分" + second + "秒";
    }


    /**
     * @param time  2017-08-03
     * @param month 添加的月份 2
     * @return 增加后的时间  2017-10-03
     */
    public static String addMonthTime(String time, int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date now = null;
        try {
            now = sdf.parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.MONTH, month);
            //calendar.add(Calendar.DAY_OF_MONTH);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;

    }
    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 2000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    public static boolean isEarly(String string){
        boolean before = false;
        Date date=new Date();
        DateFormat df=DateFormat.getDateTimeInstance();
        try {
            before  = date.before(df.parse(string));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return before;
    }
}
