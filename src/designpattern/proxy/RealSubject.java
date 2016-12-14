package designpattern.proxy;

/**
 * Created by john(Zhewei) on 2016/12/14.
 * 真正的主题实现类
 */
public class RealSubject implements Subject {
    @Override
    public void visit() {
        System.out.println("真正实现了啊!");
    }
}
