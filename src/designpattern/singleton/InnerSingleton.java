package designpattern.singleton;

/**
 * Created by john(Zhewei) on 2016/12/14.
 */
public class InnerSingleton {

    private InnerSingleton(){

    }

    private static class InstanceHolder {
        private static InnerSingleton instance = new InnerSingleton();
    }

    //饿汉的变形,让其实现了延迟加载,并能保证多线程下单例
    public static InnerSingleton getInstance() {
        return InstanceHolder.instance;
    }
}
