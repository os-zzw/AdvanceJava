package mutithread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by john(Zhewei) on 2016/12/4.
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        //创建一个线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //创建一个有3个信号量的信号灯
        Semaphore semaphore = new Semaphore(3);
        //生成10个线程
        for (int i = 0; i < 10; i++) {
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        //每次执行一个线程都先拿到1个信号量
                        semaphore.acquire();
                        System.out.println(Thread.currentThread() + "进入,当前已经有" + (3 - semaphore.availablePermits()) +
                                "条线程正在执行");
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread()+"即将离开");
                    //执行完毕之后
                    semaphore.release();

                }
            });
        }
        //执行完毕后,销毁线程池
        cachedThreadPool.shutdown();
    }
}
