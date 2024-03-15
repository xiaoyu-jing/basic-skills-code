package com.tech.solution.common.designpattern.javabean;

/**
 * @author jing1560
 * @data 2024/3/15
 * 工厂方法模式-轮船工厂
 */
public class ShipFactory implements TransportFactory{
    @Override
    public Transport createTransport() {
        return new Ship();
    }
}
