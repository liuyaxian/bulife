package com.ruiya.DesignPattern;

public class DependenceInversion {
}

// 方式一： 通过接口传递实现依赖
//interface  IopenAndClose{
//    public void open(Itv tv);
//}
//interface  Itv{
//    public  void play();
//}
//class penAndClose implements IopenAndClose{
//
//    @Override
//    public void open(Itv tv) {
//        tv.play();
//    }
//}

//
//// 方式二： 通过构造方法依赖传递
//interface  IopenAndClose{
//    public void open();
//}
//interface  Itv{
//    public  void play();
//}
//class OpenAndClose implements IopenAndClose{
//     // 成员
//    public Itv tv;
//    // 构造器
//    public void OpenAndClose(Itv tv) {
//       this.tv = tv;
//    }
//
//    @Override
//    public void open() {
//        this.tv.play();
//    }
//}


// 方式3： 通过setter 方法传递
interface  IopenAndClose{
    public void open();
    public void setTv(Itv tv);
}
interface  Itv{
    public  void play();
}
class OpenAndClose implements IopenAndClose{
     // 成员
    private Itv tv;
    // 构造器
    @Override
    public void setTv(Itv tv) {
       this.tv = tv;
    }

    @Override
    public void open() {
        this.tv.play();
    }
}




