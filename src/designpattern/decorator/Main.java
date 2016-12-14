package designpattern.decorator;

/**
 * Created by john(Zhewei) on 2016/12/14.
 */
public class Main {
    public static void main(String[] args) {
        Decorator decorator = new Decorator(new Component());
        decorator.operator();
    }
}
