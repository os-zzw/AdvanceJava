package proxy.aopFramework;

/**
 * Created by john on 2016/11/28.
 */
public class MyAdvice implements Advice {
    @Override
    public void beforeMethod() {
        System.out.println("before");
    }

    @Override
    public void afterMethod() {
        System.out.println("after");
    }
}
