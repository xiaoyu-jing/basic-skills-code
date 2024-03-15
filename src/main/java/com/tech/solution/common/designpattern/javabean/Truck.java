package com.tech.solution.common.designpattern.javabean;

/**
 * @author jing1560
 * @data 2024/3/15
 * 交通方式-卡车
 */
public class Truck implements Transport{

    @Override
    public void deliver() {
        System.out.println("在陆地上运输");
    }
}
