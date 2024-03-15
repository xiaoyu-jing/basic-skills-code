package com.tech.solution.common.designpattern.javabean;

/**
 * @author jing1560
 * @data 2024/3/15
 * 工厂方法模式-卡车工厂
 */
public class TruckFactory implements TransportFactory{
    @Override
    public Transport createTransport() {
        return new Truck();
    }
}
