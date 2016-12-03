package mutithread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by john(Zhewei) on 2016/12/2.
 * Lock 来替代 synchronized
 */
public class LockTest {
    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();
        lockTest.init();
    }

    private void init() throws InterruptedException {
        OutPuter outPuter = new OutPuter();
        //        while (true) {
        //
        //            Thread.sleep(1000);
        //
        //            new Thread(new Runnable() {
        //                @Override
        //                public void run() {
        //                    outPuter.print("zhangzhewei");
        //                }
        //            }).start();
        //
        //            new Thread(new Runnable() {
        //                @Override
        //                public void run() {
        //                    outPuter.print("wangdachui");
        //                }
        //            }).start();
        //
        //        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                outPuter.read();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                outPuter.read();
            }
        }).start();


        int i = 0;
        while (true) {
            System.out.println(i++);
            Thread.sleep(1000);
        }


    }
}


class OutPuter {

    //这是不管读操作还是写操作都会进行加锁
    Lock mLock = new ReentrantLock();

    void print(String ss) {
        mLock.lock();
        try {
            for (int i = 0; i < ss.length(); i++) {
                System.out.print(ss.charAt(i));
            }
            System.out.println();
        } finally {
            mLock.unlock();
        }
    }

    private Object data;

    ReentrantReadWriteLock mReadWriteLock = new ReentrantReadWriteLock();


    /**
     * //在这里加上读操作的锁,多个读操作可以同时执行,当写操作的锁生效时,不可以在进行读操作
     * //换句话说就是当有读锁的时候,还可以进行读,当有写锁时要等待写锁完成
     */
    void read() {
        mReadWriteLock.readLock().lock();//加上读操作的锁
        try {
            Thread.sleep(3000);
            System.out.println("进行读操作了");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mReadWriteLock.readLock().unlock();
        }
    }

    /**
     * //在这里加上写操作的锁,这和普通的锁一样,只要有锁就必须等待
     * //换句话说,就是无论是有读锁还是写锁都要等
     */
    void write() {
        mReadWriteLock.writeLock().lock();
        try {
            Thread.sleep(3000);
            data = new Object();
            System.out.println("进行写操作了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mReadWriteLock.writeLock().unlock();
        }
    }

}