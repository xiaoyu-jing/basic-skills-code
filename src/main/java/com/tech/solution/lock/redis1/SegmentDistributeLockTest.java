package com.tech.solution.lock.redis1;

import java.io.IOException;

/**
 * @author jing1560
 * @data 2024/4/27
 *
 * Redis 分布式分段锁单线程测试
 */
class SegmentDistributeLockTest{

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 模拟单线程扣减
        SegmentDistributeLock segmentDistributeLock = new SegmentDistributeLock();
        if(segmentDistributeLock.handlerStock_02(new StockRequest("memberId_001",54))){
            System.out.println("扣减成功");
        }else{
            System.out.println("扣减失败");
        }
        segmentDistributeLock.showStocks();
        /**
         * 成功; 结果为:
         * RedisStock{stockName='pId_stock_00', num=0}  扣减了20个
         * RedisStock{stockName='pId_stock_01', num=10} 扣减了10个
         * RedisStock{stockName='pId_stock_02', num=20}
         * RedisStock{stockName='pId_stock_03', num=20}
         * RedisStock{stockName='pId_stock_04', num=20}
         */
    }

}
