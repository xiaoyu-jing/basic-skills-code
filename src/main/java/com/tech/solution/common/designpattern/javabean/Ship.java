package com.tech.solution.common.designpattern.javabean;

/**
 * @author jing1560
 * @data 2024/3/15
 * 交通方式-轮船
 */
public class Ship implements Transport{
    @Override
    public void deliver() {
        System.out.println("在海上运输");
    }
}
