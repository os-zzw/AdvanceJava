package designpattern.proxy;

/**
 * Created by john(Zhewei) on 2016/12/14.
 * 代理主题类
 */
public class ProxySubject implements Subject {

    RealSubject mRealSubject;

    public ProxySubject(RealSubject realSubject) {
        mRealSubject = realSubject;
    }

    @Override
    public void visit() {
        //通过调用真正的主题来实现真正的任务
        mRealSubject.visit();
    }
}
