package com.yutong.axxc.parents.service.algorithm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /** 时间格式：yyyyMMddHHmmss'. */
    public static final String dateTime14Str = "yyyyMMddHHmmss";

    /** 时间格式：yyMMddHHmmss'. */
    public static final String dateTime12Str = "yyMMddHHmmss";

    /** 时间格式：yyMMddHHmmssSSS'. */
    public static final String dateTime15Str = "yyMMddHHmmssSSS";

    /** 时间格式：yyyyMMdd'. */
    public static final String dateTime8Str = "yyyyMMdd";

    /** 时间格式：yyyyMM'. */
    public static final String date6Str = "yyyyMM";
    
    /** 时间格式：yyyyMM'. */
    public static final String date7Str = "yyyy-MM";

    /** 时间格式：yyyy-MM-dd' */
    public static final String full8Str = "yyyy-MM-dd";

    /** 时间格式：yyyy-MM-dd' 'HH:mm:ss'. */
    public static final String full14Str = "yyyy-MM-dd' 'HH:mm:ss";

    public static final String full04Str = "HH:mm";

    public static final String d14Str = "yyyy-MM-dd HH:mm:ss";

    public static final String date12Str = "yyMMddHHmmss";

    /**
     * 将十四位日期字符串转换为yyyy-MM-dd HH:mm:ss形式.
     * @param string the string
     * @return the string
     * @throws ParseException
     */

    public static String changeTime14ToFormat(String string) throws ParseException {
        Date date = new SimpleDateFormat(dateTime14Str).parse(string);
        return new SimpleDateFormat(full14Str).format(date);
    }

    public static String changeTime12To14Format(String string) throws ParseException {
        Date date = new SimpleDateFormat(dateTime12Str).parse(string);
        return new SimpleDateFormat(full14Str).format(date);
    }

    public static String changeTime14To12Format(String string) throws ParseException {
        Date date = new SimpleDateFormat(d14Str).parse(string);
        return new SimpleDateFormat(date12Str).format(date);
    }

    public static String changeTime12To04Format(String string) throws ParseException {
        Date date = new SimpleDateFormat(dateTime12Str).parse(string);
        return new SimpleDateFormat(full04Str).format(date);
    }

    public static String changeTime14ToFormat2(String string) throws ParseException {
        Date date = new SimpleDateFormat(dateTime14Str).parse(string);
        return new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss").format(date);
    }

    public static String changeTime12ToFormat14(String string) throws ParseException {
        Date date = new SimpleDateFormat(date12Str).parse(string);
        return new SimpleDateFormat(dateTime14Str).format(date);
    }
    /**
     * 将十四位日期字符串转换为yyyyMMdd形式.
     * @param string the string
     * @return the string
     * @throws ParseException
     */

    public static String changeTime8ToFormat(String string) throws ParseException {
        Date date = new SimpleDateFormat(full8Str).parse(string);
        return new SimpleDateFormat(dateTime8Str).format(date);
    }

    /**
     * 将日期转换为'yyyyMMddHHmmss'格式的字符串
     * @param date 日期
     * @return 字符串
     */
    public static final String changeDateToString(Date date) {
        return new SimpleDateFormat(dateTime14Str).format(date);
    }

    public static final Date changeStringTo12Date(String date) throws ParseException {
        return new SimpleDateFormat(dateTime12Str).parse(date);
    }

    public static final String changeDateTo15String(Date date) {
        return new SimpleDateFormat(dateTime15Str).format(date);
    }

    /**
     * 将日期转换为'yyyyMMdd'格式的字符串
     * @param date 日期
     * @return 字符串
     */
    public static final String changeDateTo8String(Date date) {
        return new SimpleDateFormat(dateTime8Str).format(date);
    }

    /**
     * 将日期转换为'yyyy-MM-dd'格式的字符串
     * @param date 日期
     * @return 字符串
     */
    public static final String changeDateToFull8String(Date date) {
        return new SimpleDateFormat(full8Str).format(date);
    }

    /**
     * 将日期转换为'yyyy-MM-dd' 'HH:mm:ss'格式的字符串
     * @param date 日期
     * @return 字符串
     */
    public static final String changeDateTo14String(Date date) {
        return new SimpleDateFormat(full14Str).format(date);
    }

    /**
     * 将字符串转为日期
     * @param aMask 日期格式字串
     * @param strDate 日期字符串
     * @return 日期
     */
    public static final Date string2date(String aMask, String strDate) {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            throw new RuntimeException(pe);
        }
        return date;
    }

    /**
     * 将'yyyy-MM-dd' 'HH:mm:ss'格式的字符串转换为日期
     * @param strDate
     * @return
     */
    public static final Date full14String2date(String strDate) {
        try {
            return new SimpleDateFormat(full14Str).parse(strDate);
        } catch (ParseException pe) {
            throw new RuntimeException(pe);
        }
    }

    /**
     * 将日期转换为字符串
     * @param date 日期
     * @param mask 日期格式字串
     * @return 字符串
     */
    public static final String date2string(Date date, String mask) {
        SimpleDateFormat sdf = new SimpleDateFormat(mask);
        return sdf.format(date);
    }

    /**
     * 将日历转化为日期
     * @param calendar Calendar
     * @return Date
     */
    public static java.util.Date converToDate(java.util.Calendar calendar) {
        return Calendar.getInstance().getTime();
    }

    /**
     * 将日期转化为日历
     * @param date Date
     * @return Calendar
     */
    public static java.util.Calendar converToCalendar(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取某年的总天数
     * @param year
     * @return
     */
    public static final int getDaysOfYear(int year) {
        if (isLeapYear(year))
            return 366;
        else
            return 365;
    }

    /**
     * 判断某年是否为闰年
     * @param year
     * @return
     */
    public static final boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
            return true;
        else
            return false;
    }

    /**
     * 得到当前日期前后的日期
     * @param discreDays 相差天数
     * @return 返回日期字符串
     */
    public static final Date getDateByDiscreDaysWithNow(int discreDays) {
        Calendar day = Calendar.getInstance();
        day.add(Calendar.DATE, discreDays);
        return day.getTime();
    }

    /**
     * 计算某时间前后d毫秒的时间
     * @param string
     * @param d
     * @return
     * @throws ParseException
     */
    public static final String getDateByDiscreMilliSecondWithNow(String string, int d)
            throws ParseException {
        Date date = new SimpleDateFormat(dateTime12Str).parse(string);
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        // time.add(Calendar.SECOND, d);
        time.add(Calendar.MILLISECOND, d);
        return new SimpleDateFormat(dateTime15Str).format(time.getTime());
    }

    /**
     * 得到当前分钟前后的时间
     * @param minute
     * @return
     */
    public static final String getDateByDiscreMinutesWithNow(int minute) {
        Calendar time = Calendar.getInstance();
        time.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat(dateTime12Str).format(time.getTime());
    }

    public static final String getDateByDiscreMinutesWithNow(String date, int minute)
            throws ParseException {
        Calendar time = Calendar.getInstance();
        time.setTime(DateUtil.changeStringTo12Date(date));
        time.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat(dateTime12Str).format(time.getTime());
    }

    /**
     * 得到当前小时前后的时间
     * @param hour
     * @return
     */
    public static final String getDateByDiscreHoursWithNow(int hour) {
        Calendar time = Calendar.getInstance();
        time.add(Calendar.HOUR, hour);
        return new SimpleDateFormat(dateTime12Str).format(time.getTime());
    }

    public static final String getDateByDiscreHoursWithNow(String string, int hour)
            throws ParseException {
        Date date = new SimpleDateFormat(dateTime12Str).parse(string);
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.add(Calendar.HOUR, hour);
        return new SimpleDateFormat(dateTime12Str).format(time.getTime());
    }

    public static final String getDate15ByDiscreMinuteWithNow(String string, int minute)
            throws ParseException {
        Date date = new SimpleDateFormat(dateTime15Str).parse(string);
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat(dateTime15Str).format(time.getTime());
    }

    public static final String getDate15ByDiscreMinuteWithNow12(String string, int minute)
            throws ParseException {
        Date date = new SimpleDateFormat(dateTime15Str).parse(string);
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat(dateTime12Str).format(time.getTime());
    }

    public static final String getDateByDiscreMinuteWithNow(String string, int minute)
            throws ParseException {
        Date date = new SimpleDateFormat(dateTime12Str).parse(string);
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat(dateTime12Str).format(time.getTime());
    }

    /**
     * 将12位时间串累加1秒后的时间
     * @param string
     * @param second
     * @return
     * @throws ParseException
     */

    public static final String getDate12ByDiscreSecondWithNow(String string, int second)
            throws ParseException {
        Date date = new SimpleDateFormat(dateTime12Str).parse(string);
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.add(Calendar.SECOND, second);
        return new SimpleDateFormat(dateTime12Str).format(time.getTime());
    }

    public static final String getDateByDiscreSecondWithNow(String string, int second)
            throws ParseException {
        Date date = new SimpleDateFormat(dateTime15Str).parse(string);
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.add(Calendar.SECOND, second);
        return new SimpleDateFormat(dateTime15Str).format(time.getTime());
    }

    public static final String getDate15ByDiscreSecondWithNow12(String string, int second)
            throws ParseException {
        Date date = new SimpleDateFormat(dateTime15Str).parse(string);
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.add(Calendar.MILLISECOND, second * 1000);
        return new SimpleDateFormat(dateTime12Str).format(time.getTime());
    }

    public static final String changeTime15To12Format(String string) throws ParseException {
        Date date = new SimpleDateFormat(dateTime15Str).parse(string);
        return new SimpleDateFormat(dateTime12Str).format(date);
    }

    public static final String changeTime12ToFormat() {
        return new SimpleDateFormat(dateTime12Str).format(new Date());
    }

    public static final String changeTime15ToFormat() {
        return new SimpleDateFormat(dateTime15Str).format(new Date());
    }

    public static final String changeTime12ToFormat(Date date) {
        return new SimpleDateFormat(dateTime12Str).format(date);
    }

    /**
     * 当前日期
     * @return
     */
    public static final Date now() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 按yyyyMMdd得到当前日期.
     * @return the curr date
     */
    public static String getCurrDate() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        String currentDate = formater.format(new Date());
        return currentDate;
    }

    /**
     * 按HHmmss得到当前时间.
     * @return the curr time
     */
    public static String getCurrTime() {
        SimpleDateFormat formater = new SimpleDateFormat("HHmmss");
        String currentDate = formater.format(new Date());
        return currentDate;
    }

    /**
     * 按yyyyMMddHHmmss得到当前时间
     * @return
     */
    public static String getCurrTime14() {
        String currentDate = new SimpleDateFormat(dateTime14Str).format(new Date());
        return currentDate;
    }

    /**
     * 将当前日期转换为字符串
     * @param mask 日期格式字串
     * @return 当前日期字串
     */
    public static final String now2string(String mask) {
        return date2string(new Date(), mask);
    }

    /**
     * 将本地时间转换为UTC时间
     * @param date
     * @return
     */
    public static long transLocalTimeToUTC(Date date) {
        // 取得本地时间：
        java.util.Calendar cal = DateUtil.converToCalendar(date);
        return cal.getTimeInMillis();
    }

    /**
     * 将UTC国际标准时间的毫秒形式转换成本地的时间
     * @param utcTime
     * @return
     */
    public static Date utcToLocalDate(long utcTime) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(utcTime);
        return calendar1.getTime();
    }

    public static final String getDate15ByDiscreMuniteWithNow15(String string, int minute)
            throws ParseException {
        Date date = new SimpleDateFormat(dateTime15Str).parse(string);
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat(dateTime15Str).format(time.getTime());
    }

    public static final String getDate12ByDiscreMuniteWithNow15(String string)
            throws ParseException {
        Date date = new SimpleDateFormat(dateTime12Str).parse(string);
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        return new SimpleDateFormat(dateTime15Str).format(time.getTime());
    }

    /**
     * 获取当前月份
     * @param time
     * @return
     */
    public static int getCurrentMonth(Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static String getLocalDate(long time) {
        // Date date = new Date(ms * 1000);
        Date date = new Date((time - 3600 * 8) * 1000);
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyMMddHHmmss");
        return format.format(date);
    }

    /**
     * 取特定日期（yyyy-MM-dd格式的日期串）的后一天
     * @param str yyyy-MM-dd格式的日期串
     * @return 第二天时间
     */
    public static final Date getDateByDiscreDays(String str, int days) {
        Date date = string2date("yyyy-MM-dd", str);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 计算日期加固定小时后的时间
     * @param date 日期
     * @param hour 要增加的小时数
     * @return 更改后的日期
     */
    public static final Date getDateByDiscreHours(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);
        return cal.getTime();
    }

    /**
     * 计算日期加固定分钟后的时间
     * @param date 日期
     * @param hour 要增加的分钟数
     * @return 更改后的日期
     */
    public static final Date getDateByDiscreMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    /**
     * 日期转为'yyyyMM'格式
     * @param date
     * @return
     */
    public static final String dateToMonthStr(Date date) {
        return new SimpleDateFormat(date6Str).format(date);
    }
    
    /**
     * 日期转为'yyyy-MM'格式
     * @param date
     * @return
     */
    public static final String dateToMonth7Str(Date date) {
        return new SimpleDateFormat(date7Str).format(date);
    }

    /**
     * 获取某月份的下一月
     * @param monthStr 某月，格式'yyyyMM'
     * @return
     */
    public static final Date getNextMonth(String monthStr) {
        Date time = string2date(date6Str, monthStr);
        return addMonth(time, 1);
    }

    /**
     * 指定日期增加几个月后的日期值
     * @param time 指定日期
     * @param months 增加的月份数
     * @return 增加的月份后的日期值
     */
    public static final Date addMonth(Date time, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 获取当前日期的上一月份
     * @return
     */
    public static Date getLastMonth() {
        return string2date(date6Str, dateToMonthStr(addMonth(new Date(), -1)));
    }
    /**
     * 计算时间差(分钟)
     * @return
     */
    public static Long calDateDiffM(String start,String end){
    	DateFormat df = new SimpleDateFormat(d14Str);
		try {
			Date d1 = df.parse(end);
			Date d2 = df.parse(start);
			long diff = d1.getTime() - d2.getTime();
			long minute = diff / (1000 * 60);
			return minute;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public static void main(String[] args) throws ParseException {
        // System.out.println(utcToLocalDate(1328164517000l));
        // try {
        // System.out.println(new SimpleDateFormat(dateTime15Str).parse("110817120512450"));
        // System.out.println(getDate15ByDiscreSecondWithNow12("110817120512450", 5));
        // } catch (ParseException e) {
        // e.printStackTrace();
        // }
        // System.out.println(changeTime14To12Format("20111227155300"));

        Date date = new Date();
        System.out.println(date);
        System.out.println(dateToMonthStr(getLastMonth()));
        System.out.println(string2date(date6Str, dateToMonthStr(getLastMonth())));
        System.out.println(getLastMonth());
    }
}