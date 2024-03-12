package com.tech.solution.common.designpattern;

/**
 * @author jing1560
 * @data 2024/3/12
 * @discription 正确使用 里式替换原则
 */
public class SafetyPrinciple {

    //四边形接口
    public interface QuadRangle {
        long getWidth();
        long getHeight();
    }

    //长方形
    public static class Rectangle implements QuadRangle{
        private long height;
        private long width;

        public void setHeight(long height) {
            this.height = height;
        }

        public void setWidth(long width) {
            this.width = width;
        }

        @Override
        public long getWidth() {
            return width;
        }

        @Override
        public long getHeight() {
            return height;
        }
    }

    //正方形
    public static class Square implements QuadRangle{
        private long length;

        public long getLength() {
            return length;
        }

        public void setLength(long length) {
            this.length = length;
        }

        @Override
        public long getWidth() {
            return length;
        }
        @Override
        public long getHeight() {
            return length;
        }
    }

    // 这里的 Rectangle参数就不能被改变了，如果传入QuadRangle则会报错，避免了继承泛滥问题
    public static void resize(Rectangle rectangle){
        while (rectangle.getWidth() >= rectangle.getHeight()) {
            rectangle.setHeight(rectangle.getHeight() + 1);
            System.out.println("width:" + rectangle.getWidth() + ", height:" + rectangle.getHeight());
        }
    }

    public static void main(String[] args){
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(10);
        rectangle.setHeight(5);
        //正常输出
        resize(rectangle);

        Square square = new Square();
        square.setLength(10);
        //此处由于不是继承关系，所以这个方法也就不能正常调用了，这就保证了 里式替换原则
        //resize(square);
    }

}
