package com.tech.solution.work;

import java.text.DecimalFormat;

import static java.lang.Math.abs;

/**
 * @author jing1560
 * @data 2023/10/10
 * @discription 呼分的投标计算
 */
public class BiddingCompute {

    public static String formatDouble(double s) {
        DecimalFormat fmt = new DecimalFormat("##0.00");
        return fmt.format(s);
    }

    public static void main(String[] args){
        // A~E 为所有投标人的有效投标报价
        double A = 12;
        double B = 13;
        double C = 18;
        double D = 14;
        double E = 11;
        //评标基准价 = 所有投标人的有效投标报价的算术平均值
        double standardValue = (A + B + C + D + E) / 5;

        System.out.println("中国移动报价为：" + A);
        if(A > standardValue){
            double a = (A - standardValue)/standardValue/0.01 * 0.6;
            if(5-a <= 5){
                System.out.println("扣5分");
            }else {
                System.out.println(5-a);
            }
        } else if (A < standardValue) {
            double b = (standardValue - A)/standardValue/0.01 * 0.5;
            if(5-b <= 5){
                System.out.println("扣5分");
            }else {
                System.out.println(5-b);
            }
        }

        System.out.println("投标报价的偏差率：" + formatDouble(abs((A - standardValue) / standardValue * 100 )) + "%");

    }
}
