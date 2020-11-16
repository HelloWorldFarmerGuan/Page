package com.gzc.page.utils;


import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jul668 on 16/2/26.
 */
public class TimeTools {
    /**
     * 一天的时间 （毫秒）
     */
    public static final long DAY_TIME = 24 * 60 * 60 * 1000;
    /**
     * 一小时时间（毫秒）
     */
    public static final long HOUR_TIME = 60 * 60 * 1000;
    /*
     * 一分钟的时间（毫秒）
     */
    public static final long MINUTE_TIME = 60 * 1000;
    private Date date;

    public static String getTimestamp() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String s = calendar.get(Calendar.YEAR)
                + num2String(calendar.get(Calendar.MONTH) + 1)
                + num2String(calendar.get(Calendar.DAY_OF_MONTH))
                + num2String(calendar.get(Calendar.HOUR_OF_DAY))
                + num2String(calendar.get(Calendar.MINUTE))
                + num2String(calendar.get(Calendar.SECOND));
        return s;
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    public static String getYMD(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String formatData(long time,SimpleDateFormat format) {
        Date date = new Date(time);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static long getNowTime() {
        Date date = new Date();
        return date.getTime();
    }

    public static boolean isExpired(long time) {
        Date date = new Date();
        return date.getTime() > time;
    }

    public static long getDayStart(long time) {
//        Date date = new Date(time);
        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(date);
        calendar.setTimeInMillis(time);
        Calendar calendar1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        Log.d("timetools", calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + " / " + calendar.get(Calendar.DAY_OF_MONTH));
        return calendar1.getTimeInMillis();
    }

    public static long getDayEnd(long time) {
//        Date date = new Date(time);
        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(date);
        calendar.setTimeInMillis(time);
        Calendar calendar1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) + 1);
        return calendar1.getTimeInMillis();
    }

    public static String formatTime(long time) {
        Date date = new Date(time);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String s = calendar.get(Calendar.YEAR) + "-"
                + num2String(calendar.get(Calendar.MONTH) + 1) + "-"
                + num2String(calendar.get(Calendar.DAY_OF_MONTH)) + " "
                + num2String(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
                + num2String(calendar.get(Calendar.MINUTE)) + ":"
                + num2String(calendar.get(Calendar.SECOND));
        return s;
    }

    public static String formatTimeYMDHM(long time) {
        Date date = new Date(time);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String s = calendar.get(Calendar.YEAR) + "-"
                + num2String(calendar.get(Calendar.MONTH) + 1) + "-"
                + num2String(calendar.get(Calendar.DAY_OF_MONTH)) + " "
                + num2String(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
                + num2String(calendar.get(Calendar.MINUTE));
        return s;
    }

    public static String formatTimeYMDHMS(long time) {
        Date date = new Date(time);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String s = calendar.get(Calendar.YEAR) + "-"
                + num2String(calendar.get(Calendar.MONTH) + 1) + "-"
                + num2String(calendar.get(Calendar.DAY_OF_MONTH)) + " "
                + num2String(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
                + num2String(calendar.get(Calendar.MINUTE)) + ":"
                + num2String(calendar.get(Calendar.SECOND));
        return s;
    }

    public static String formatTimeYMD(long time) {
        Date date = new Date(time);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String s = calendar.get(Calendar.YEAR) + "-"
                + num2String(calendar.get(Calendar.MONTH) + 1) + "-"
                + num2String(calendar.get(Calendar.DAY_OF_MONTH));
        return s;
    }

    public static String formatTimeHMS(long time) {
        Date date = new Date(time);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String s =
                num2String(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
                        + num2String(calendar.get(Calendar.MINUTE)) + ":"
                        + num2String(calendar.get(Calendar.SECOND));
        return s;
    }

    public static String formatTimeHM(long time) {
        Date date = new Date(time);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String s =
                num2String(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
                        + num2String(calendar.get(Calendar.MINUTE));

        return s;
    }

    public static String num2String(Integer n) {
        String s = null;
        if (n < 10)
            s = "0" + n;
        else
            s = "" + n;
        return s;
    }


    /**
     * @param value 秒
     * @return
     */
    public static String timeFormatByHMS(long value) {
        long h = (value / 3600);
        long m = ((value % 3600) / 60);
        long s = (value % 60);
        return "" + zeroFill(h) + ":" + zeroFill(m) + ":" + zeroFill(s);
    }

    public static String zeroFill(long d) {
        int len = 2;
        String s = Long.toString(d);
        while (s.length() < len) {
            s = "0" + s;
        }
        return s;
    }

    public static boolean isSameDay(long t1, long t2) {
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTimeInMillis(t1);

        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTimeInMillis(t2);

        if (calendar1.get(Calendar.YEAR) != calendar2.get(Calendar.YEAR))
            return false;
        if (calendar1.get(Calendar.MONTH) != calendar2.get(Calendar.MONTH))
            return false;
        if (calendar1.get(Calendar.DAY_OF_MONTH) != calendar2.get(Calendar.DAY_OF_MONTH))
            return false;
        return true;

    }

    public static boolean isToday(long t1) {
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTimeInMillis(t1);

        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTimeInMillis(getNowTime());

        if (calendar1.get(Calendar.YEAR) != calendar2.get(Calendar.YEAR))
            return false;
        if (calendar1.get(Calendar.MONTH) != calendar2.get(Calendar.MONTH))
            return false;
        if (calendar1.get(Calendar.DAY_OF_MONTH) != calendar2.get(Calendar.DAY_OF_MONTH))
            return false;
        return true;
    }

    public static int getFullYear(long time) {
        Date date = new Date(time);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(long time) {
        Date date = new Date(time);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDate(long time) {
        Date date = new Date(time);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    public static Date getDateByFormat(String s) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = df.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static String getPublishFormat(long time) {
        if (time <= 0)
            return "刚刚";
        long interval = getNowTime() - time;
        if (interval < 5 * MINUTE_TIME) {
            return "刚刚";
        }
        if (interval < 1 * HOUR_TIME) {
            return (interval / MINUTE_TIME) + "分钟前";
        }
        if (interval < 24 * HOUR_TIME) {
            return interval / HOUR_TIME + "小时前";
        }

        int year = getFullYear(time);
        int nowYear = getFullYear(getNowTime());
        String strYear = (year == nowYear ? "" : ("" + year + "年"));
        return strYear + getMonth(time) + "月" + getDate(time) + "日";
    }

    public static String getChatFormat(long time) {
        if (time == 0)
            return "";
        Date date = new Date(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR);
            if (diffDay == 0) {
                return new SimpleDateFormat("HH:mm").format(date);
            }
        } else {
            return getYMD(date);
        }

        return getMonth(time) + "-" + getDate(time);
    }

    public static String formatTimeZc(long time) {
        Date date = new Date(time);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String s = "";
        if (time > DAY_TIME) {
            s = (calendar.get(Calendar.DAY_OF_MONTH)) + "天 "
                    + (calendar.get(Calendar.HOUR_OF_DAY)) + "小时";
        } else if (time > HOUR_TIME) {
            s = (calendar.get(Calendar.HOUR_OF_DAY)) + "小时"
                    + num2String(calendar.get(Calendar.MINUTE)) + "分钟";
        } else {
            s = num2String(calendar.get(Calendar.MINUTE)) + "分钟"
                    + num2String(calendar.get(Calendar.SECOND)) + "秒";
        }

        return s;
    }

    public static String formatTimeZc2(long time) {
        long days = time / (1000 * 60 * 60 * 24);
        long hours = (time % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (time % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (time % (1000 * 60)) / 1000;
        if (time > TimeTools.DAY_TIME) {
            return days + "天" + hours + "小时";
        } else if (time > TimeTools.HOUR_TIME) {
            return hours + "小时" + TimeTools.num2String(Integer.parseInt(minutes + "")) + "分钟";
        } else {
            return TimeTools.num2String(Integer.parseInt(minutes + "")) + "分钟"
                    + TimeTools.num2String(Integer.parseInt(seconds + "")) + "秒";
        }
    }
}
