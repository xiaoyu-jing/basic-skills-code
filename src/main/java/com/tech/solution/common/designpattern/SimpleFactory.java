package com.tech.solution.common.designpattern;

import com.tech.solution.common.designpattern.javabean.Ship;
import com.tech.solution.common.designpattern.javabean.Transport;
import com.tech.solution.common.designpattern.javabean.Truck;

/**
 * @author jing1560
 * @data 2024/3/15
 * 工厂模式：1、简单工厂模式 （本示例）
 *         2、工厂方法模式
 */
public class SimpleFactory {

    public static Transport createTransport(String type){
        if("truck".equalsIgnoreCase(type)){
            return new Truck();
        } else if ("ship".equalsIgnoreCase(type)){
            return new Ship();
        }
        return null;
    }

    public static void main(String[] args){
        Transport truck = SimpleFactory.createTransport("truck");
        truck.deliver();

        Transport ship = SimpleFactory.createTransport("ship");
        ship.deliver();
    }
}
