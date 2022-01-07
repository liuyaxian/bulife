package com.ruiya.DesignPattern.ocp;

/**
 * @desc:
 * @author: admin
 * @since: 2022/1/7 15:43
 * @history:
 */
public class Ocp {

    public static void main(String[] args) {
//        Rectangle rectangle = new Rectangle();
//        rectangle.m_type =1 ;
//        rectangle.draw();


        GrahicEditor grahicEditor = new GrahicEditor();

        grahicEditor.drawShape(new Rectangle());

        grahicEditor.drawShape(new Reiar());

    }
}

class GrahicEditor{
    public void drawShape(Shape shape){
        shape.draw();
    }
}


abstract class Shape{
    int m_type;
    public abstract void draw();
}

class Rectangle extends Shape{

    Rectangle(){
        super.m_type =1;
    }

    @Override
    public void draw(){
        System.out.println(" 绘制矩形 ！");
    }
}


class Reiar extends Shape{

    Reiar(){
        super.m_type =2 ;
    }

    @Override
    public void draw(){
        System.out.println(" 绘制原型！");
    }
}