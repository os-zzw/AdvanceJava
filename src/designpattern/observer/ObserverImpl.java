package designpattern.observer;


import java.util.Observable;
import java.util.Observer;

/**
 * Created by john(Zhewei) on 2016/12/10.
 * 这是观察者
 */
public class ObserverImpl implements Observer {

    private String name;

    public ObserverImpl(String name) {
        this.name = name;
    }

    /**
     * 当观察的对象发生改变时,收到通知,并作出对应的操作
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("收到了"+o.getClass().getName()+"内容是:" + arg.toString());
    }

}
