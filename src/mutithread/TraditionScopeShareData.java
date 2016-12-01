package mutithread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by john(Zhewei) on 2016/12/1.
 * 线程范围的共享变量
 */
public class TraditionScopeShareData {

    private static Map<Thread, Integer> threadData = new HashMap<Thread, Integer>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int data = new Random().nextInt();
                        threadData.put(Thread.currentThread(), data);
                        System.out.println(Thread.currentThread().getName() + "put data" + data);
                        Thread.sleep(100);
                        new A().get();
                        Thread.sleep(100);
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
            int i = threadData.get(Thread.currentThread());
            System.out.println(" A from " + Thread.currentThread().getName() + "get data" + i);
        }
    }

    static class B {
        public void get() {
            int i = threadData.get(Thread.currentThread());
            System.out.println(" B from " + Thread.currentThread().getName() + "get data" + i);
        }
    }

}
