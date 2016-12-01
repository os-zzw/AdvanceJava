package mutithread;

import java.util.Random;

/**
 * Created by john(Zhewei) on 2016/12/1.
 * 实现线程内共享变量
 */
public class ThreadLocalTest {


    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int data = new Random().nextInt();
                        MyThreadScopeData instance = MyThreadScopeData.getInstance();
                        instance.setName("000");
                        instance.setAge(data);
                        System.out.println(Thread.currentThread().getName() + instance);
                        new A().get();
                        new B().get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }

    static class A {
        public void get() {
            MyThreadScopeData ins = MyThreadScopeData.getInstance();
            System.out.println(Thread.currentThread().getName() + ins);
        }
    }

    static class B {
        public void get() {
            MyThreadScopeData ins = MyThreadScopeData.getInstance();
            System.out.println(Thread.currentThread().getName() + ins);
        }
    }
}

/**
 * 线程内单实例类
 */
class MyThreadScopeData {
    private String name;
    private int age;

    //    private static MyThreadScopeData instance;

    private static ThreadLocal<MyThreadScopeData> threadLocal = new ThreadLocal<>();

    public MyThreadScopeData() {
    }

    @Override
    public String toString() {
        return "MyThreadScopeData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    /**
     * 改造成为 每个线程拥有单实例
     */
    public static MyThreadScopeData getInstance() {
        MyThreadScopeData instance = threadLocal.get();
        if (instance == null) {
            instance = new MyThreadScopeData();
            threadLocal.set(instance);
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
