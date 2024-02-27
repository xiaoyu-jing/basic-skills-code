package com.tech.solution.common.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jing1560
 * @data 2023/10/16
 */
public class TimerTransfer {

    public static void main(String[] args){
        specificTimeTransfer();
    }

    /**
     * 当前时间转换
     */
    public static void currentTimeTransfer(){
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currdate = format.format(d);
        System.out.println("现在的日期是：" + currdate);

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, 3);// num为增加的天数，可以改变的
        d = ca.getTime();
        String enddate = format.format(d);
        System.out.println("增加天数以后的日期：" + enddate);
    }

    /**
     * 指定时间转换
     */
    public static void specificTimeTransfer(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd" );
        Date selectedDate=null;
        try {
            selectedDate=dateFormat.parse("2023-9-26");// replace it with selected date
        } catch (ParseException e) {
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(selectedDate);
        System.out.println(" Selected Date: "+cal.getTime());
        cal.add( Calendar.DATE,90 );
        System.out.println("Date after 90 days: "+cal.getTime());
    }
}
