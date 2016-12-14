package designpattern.singleton;

/**
 * Created by john(Zhewei) on 2016/12/14.
 */
public class Main {
    public static void main(String[] args) {
        EnumSingleton instance = EnumSingleton.INSTANCE;
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
    }
}
