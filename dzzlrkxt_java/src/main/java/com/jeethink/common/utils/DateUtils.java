package com.jeethink.common.utils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 时间工具类
 *
 @author  官方网址
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date)
    {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts)
    {
        try
        {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return DateUtils.parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate()
    {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    public static String getDateCn(String time,String type){
        String[] times = time.split("-");
        String result="";
        boolean flag = false;
        if("month".equals(type)){
            flag = true;
        }
        if(times.length == 3){
            switch (times[1]){
                case "01":
                    if(flag){
                        result = "一月";
                    }else{
                        result = "一季度";
                    }
                    break;
                case "02":
                    if(flag){
                        result = "二月";
                    }else{
                        result = "一季度";
                    }
                    break;
                case "03":
                    if(flag){
                        result = "三月";
                    }else{
                        result = "一季度";
                    }
                    break;
                case "04":
                    if(flag){
                        result = "四月";
                    }else{
                        result = "二季度";
                    }
                    break;
                case "05":
                    if(flag){
                        result = "五月";
                    }else{
                        result = "二季度";
                    }
                    break;
                case "06":
                    if(flag){
                        result = "六月";
                    }else{
                        result = "二季度";
                    }
                    break;
                case "07":
                    if(flag){
                        result = "七月";
                    }else{
                        result = "三季度";
                    }
                    break;
                case "08":
                    if(flag){
                        result = "八月";
                    }else{
                        result = "三季度";
                    }
                    break;
                case "09":
                    if(flag){
                        result = "九月";
                    }else{
                        result = "三季度";
                    }
                    break;
                case "10":
                    if(flag){
                        result = "十月";
                    }else{
                        result = "四季度";
                    }
                    break;
                case "11":
                    if(flag){
                        result = "十一月";
                    }else{
                        result = "四季度";
                    }
                    break;
                case "12":
                    if(flag){
                        result = "十二月";
                    }else{
                        result = "四季度";
                    }
                    break;
            }
        }
        return result;
    }
}
