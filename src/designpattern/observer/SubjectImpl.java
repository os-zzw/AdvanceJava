package designpattern.observer;

import java.util.Observable;

/**
 * Created by john(Zhewei) on 2016/12/10.
 * 这是被观察者,他可以添加 观察者 来通知观察者自身发生了改变.
 */
public class SubjectImpl extends Observable {

    /**
     * 发生事件,通知观察者
     */
    public void postNewPublication(String content) {
        //标识状态或者内容发生改变了
        setChanged();
        //通知所有的观察者
        notifyObservers(content);

    }
}
