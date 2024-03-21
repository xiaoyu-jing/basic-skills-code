package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/3/21
 * 对象引用测试-修改引用对象，原对象中的值也发生改变
 */
public class ObjectReferenceTest {

    static class ObjectReference {
        String name;

        int age;

        public ObjectReference(String name, int age){
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static void main(String[] args){
        updateObjectReference();
    }

    /**
     * 打印结果为：
     *
     * tempObject: zhangsan:8
     * updateObject: lisi:8
     * tempObject: lisi:8
     */
    public static void updateObjectReference(){
        ObjectReference tempObject = new ObjectReference("zhangsan",8);
        System.out.println("tempObject: " + tempObject.getName() + ":" + tempObject.getAge());

        //此处 updateObject 只是一个对象的引用，指向的还是 原tempObject对象的地址
        ObjectReference updateObject = tempObject;
        updateObject.setName("lisi");

        System.out.println("updateObject: " + updateObject.getName() + ":" + updateObject.getAge());
        System.out.println("tempObject: " + tempObject.getName() + ":" + tempObject.getAge());
    }

}
