package designpattern.observer;

/**
 * Created by john(Zhewei) on 2016/12/14.
 * 观察者模式
 * <p>
 * 定义: 当一个对象的行为依赖于另一个对象的行为时
 * 若不用该模式,只能在另一个线程中不断监听对象所依赖的状态,对系统开销很大
 * 在单一线程中,使得某一对象及时得知自身所依赖的状态的变化
 * <p>
 * 作用:1.将观察者和被观察者解耦
 * 2.
 */
public class Main {
    public static void main(String[] args) {
        //被观察者
        Subject subject = new Subject();
        //观察者
        Observer observer = new Observer();
        //让被观察者的观察队列中增加一个
        subject.addObserver(observer);

        //调用被观察者的方法,让其状态改变
        subject.postNews("哈哈哈");
    }
}
