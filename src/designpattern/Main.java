package designpattern;

import designpattern.observer.ObserverImpl;
import designpattern.observer.SubjectImpl;

/**
 * Created by john(Zhewei) on 2016/12/10.
 */
public class Main {
    public static void main(String[] args) {
        //观察者
        ObserverImpl observer = new ObserverImpl("观察者");
        //被观察者
        SubjectImpl subject = new SubjectImpl();

        //观察者订阅被观察者
        subject.addObserver(observer);

        subject.postNewPublication("你的钱没了啊,大哥!");


    }
}
