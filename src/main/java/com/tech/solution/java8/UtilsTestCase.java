package com.tech.solution.java8;

import java.time.Clock;
import java.time.LocalDate;

/**
 * @author jing1560
 * @data 2024/3/5
 * @discription Java8中的工具类
 */
public class UtilsTestCase {

    public static void main(String[] args){
        testDate();
    }

    public static void testDate(){
        //获取东八区 零时区 的时间
        final Clock clock = Clock.systemUTC();
        System.out.println("clock1:" + clock.instant()); //clock1:2024-03-05T02:55:34.978Z
        System.out.println("clock2:" + clock.millis());//clock2:1709607335048

        LocalDate date = LocalDate.now();
        System.out.println("date:" + date); //date:2024-03-05

        LocalDate date1 = LocalDate.now(clock);
        System.out.println("date1:" + date1);//date1:2024-03-05
    }
}
