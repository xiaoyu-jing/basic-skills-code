package com.tech.solution.transcational;

/**
 * @author jing1560
 * @data 2024/3/14
 */
public class TransactionalTestCase {

    public static void main(String[] args){
        transactionalTimeoutTest();

        sameClassTransactionalCall();
    }

    /**
     * 事务超时测试
     */
    public static void transactionalTimeoutTest(){
        UserServiceImpl userService = new UserServiceImpl();
        userService.updateUserInfo();
    }

    /**
     * 同一个类中方法调用，导致@Transactional失效
     */
    public static void sameClassTransactionalCall(){
        UserServiceImpl userService = new UserServiceImpl();
        userService.commitUserData();
    }
}
