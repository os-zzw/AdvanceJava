package designpattern.observer;


import java.util.Observable;

/**
 * Created by john(Zhewei) on 2016/12/14.
 * 这是观察者,当观察的对象发出通知时,做出update动作
 */
public class Observer implements java.util.Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("得到了" +o.getClass().getName()+ "发来的" + arg.toString() + "通知");
    }
}
