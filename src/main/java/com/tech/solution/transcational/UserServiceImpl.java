package com.tech.solution.transcational;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/3/14
 */
public class UserServiceImpl {

    public UserServiceImpl(){}

    //--------------  场景1：事务超时，报错  ------------------------

    /**
     * 运行的时候，由于事务超时，就会报错 TransactionTimedOutException
     */
    @Transactional(propagation = Propagation.REQUIRED, timeout = 2)  //启用事务管理
    public void updateUserInfo(){
        System.out.println("----开始执行事务方法----");
        this.addUser();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.updateUser();
        System.out.println("----事务执行完毕----");
    }

    public void addUser(){
        System.out.println("添加用户");
    }

    /**
     * 如果 addUser() 和 updateUser() 为两个真实调用数据库的方法 时，上面的 updateUserInfo() 方法
     * 运行的时候，由于事务超时，就会报错 TransactionTimedOutException
     */
    public void updateUser(){
        System.out.println("更新用户");
    }


    //--------------  场景2：同一个类中方法调用，导致@Transactional失效  ------------------------

    /**
     * 开发中避免不了会对同一个类里面的方法调用，比如有一个类Test，它的一个方法A，A再调用本类的方法B（不论方法B是用public还是private修饰），
     * 但方法A没有声明注解事务，而B方法有。则外部调用方法A之后，方法B的事务是不会起作用的。这也是经常犯错误的一个地方。
     *
     * 那为啥会出现这种情况？其实这还是由于使用Spring AOP代理造成的，因为只有当事务方法被当前类以外的代码调用时，才会由Spring生成的代理对象来管理。
     */
    public void commitUserData(){
        System.out.println("开始提交用户数据");
        // insert 某个数据进入 DB

        // 调用 同类中的事务方法，保存用户配置信息
        // 此处的事务失效
        this.updateUserProperties();
    }

    @Transactional()
    public void updateUserProperties(){
        List<String> properties = Arrays.asList("aaa","bbb","ccc","ddd");
        // 此处将 properties insert 到 DB 中
        System.out.println("UserProperties:" + properties + ", 已成功添加");
    }
}
