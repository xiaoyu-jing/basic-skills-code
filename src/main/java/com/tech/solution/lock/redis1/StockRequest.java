package com.tech.solution.lock.redis1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author jing1560
 * @data 2024/4/27
 *
 * 库存请求入参
 */
@Getter
@Setter
@AllArgsConstructor
class StockRequest implements Serializable {
    //会员id
    String memberId;
    //购买数量
    int buyNum;
}
