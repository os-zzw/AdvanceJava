package designpattern.observer;

import java.util.Observable;

/**
 * Created by john(Zhewei) on 2016/12/14.
 * 被观察者,发生某种情况的时候,设置自身改变了,并通知观察者
 */
public class Subject extends Observable {
    //这是被观察者,继承Observable类
    public void postNews(String content) {
        //设置改变了
        setChanged();
        //通知所有观察者
        notifyObservers(content);
    }
}
