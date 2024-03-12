package com.tech.solution.common.designpattern;

/**
 * @author jing1560
 * @data 2024/3/12
 * @discription 历史替换原则 - 经典案例：正方形不是长方形
 */
public class ExtendTestCast {

    //长方形
    public static class Rectangle {
        //宽
        private long height;
        //高
        private long width;

        public long getHeight() {
            return height;
        }

        public void setHeight(long height) {
            this.height = height;
        }

        public long getWidth() {
            return width;
        }

        public void setWidth(long width) {
            this.width = width;
        }
    }

    //正方形
    public static class Square extends Rectangle {
        private long length;

        public long getLength() {
            return length;
        }

        public void setLength(long length) {
            this.length = length;
        }

        @Override
        public long getHeight() {
            return getLength();
        }

        @Override
        public void setHeight(long height) {
            setLength(height);
        }

        @Override
        public long getWidth() {
            return getLength();
        }

        @Override
        public void setWidth(long width) {
            setLength(width);
        }
    }

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
        //死循环
        resize(square);
    }

}
