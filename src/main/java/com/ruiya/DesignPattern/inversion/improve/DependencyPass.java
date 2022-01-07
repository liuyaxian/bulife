package com.ruiya.DesignPattern.inversion.improve;

/**
 * @desc:
 * @author: admin
 * @since: 2022/1/7 14:16
 * @history:
 */
public class DependencyPass {


    public static void main(String[] args) {

        OpenAndClose  openAndClose = new OpenAndClose();
        openAndClose.setTv(new ChangHong());
        openAndClose.open();

        openAndClose.setTv(new Haier());
        openAndClose.open();
    }



}


interface  ITV {
    public void play();
}

interface  IOpenAndClose{
    public void open();

    public void setTv(ITV itv);
}

class  OpenAndClose implements  IOpenAndClose{

    private ITV itv;
    @Override
    public void open() {
        this.itv.play();
    }

    @Override
    public void setTv(ITV itv) {
        this.itv = itv;
    }
}

class  ChangHong implements  ITV{

    @Override
    public void play() {
        System.out.println("打开电视");
    }
}


class Haier implements  ITV{

    @Override
    public void play() {
        System.out.println("海尔电视");

    }
}