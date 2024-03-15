package com.tech.solution.common.designpattern;

import com.tech.solution.common.designpattern.javabean.ShipFactory;
import com.tech.solution.common.designpattern.javabean.Transport;
import com.tech.solution.common.designpattern.javabean.TransportFactory;
import com.tech.solution.common.designpattern.javabean.TruckFactory;

/**
 * @author jing1560
 * @data 2024/3/15
 * 工厂模式：1、简单工厂模式
 *         2、工厂方法模式 （本示例）
 */
public class FactoryMethodPatternDemo {

    public static void main(String[] args){
        //创建卡车工厂
        TransportFactory truckFactory = new TruckFactory();
        //创建卡车对象
        Transport truck = truckFactory.createTransport();
        //调用卡车方法
        truck.deliver();

        TransportFactory shipFactory = new ShipFactory();
        Transport ship = shipFactory.createTransport();
        ship.deliver();
    }
}
