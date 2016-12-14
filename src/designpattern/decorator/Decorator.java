package designpattern.decorator;

/**
 * Created by john(Zhewei) on 2016/12/14.
 * 这是装饰类,用来动态地扩展对象的功能,他也是继承关系的一种替代方式
 * 比生成子类更加灵活
 */
public class Decorator {
    private Component mComponent;

    public Decorator(Component component) {
        mComponent = component;
    }

    public void operator() {
        mComponent.operate();
        //然后再在这里动态添加一些其他的功能
        System.out.println("我在这里帮助他完成了一些其他的功能!");
    }
}
