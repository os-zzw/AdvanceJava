package mutithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by john(Zhewei) on 2016/11/29.
 * 线程之间的通信
 */
public class ConditionCommunication {

    public static void main(String[] args) {
        ConditionCommunication communication = new ConditionCommunication();
        communication.init();
    }

    private void init() {
        ThreadRun threadRun = new ThreadRun();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    threadRun.sub();
                }
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            threadRun.main();
        }

    }

    /**
     * 在这里要记住,同步互斥通信代码要放到 资源中  不要写在线程中
     */
    private class ThreadRun {


        private Lock mLock = new ReentrantLock();//锁
        private Condition mCondition1=mLock.newCondition();//实现线程间的通信
        private Condition mCondition2=mLock.newCondition();//实现线程间的通信
        private Condition mCondition3=mLock.newCondition();//实现线程间的通信
        private int numRun=1;
        private void thread1() throws InterruptedException {
            mLock.lock();//加锁
            try {
                //等待
                while (numRun!=1) mCondition1.await();
                //执行循环
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread1 sequence of  " + i);
                }
                //执行完成之后让线程2执行
                numRun=2;
                mCondition2.signal();
            } finally {
                mLock.unlock();
            }
        }
        private void thread2() throws InterruptedException {
            mLock.lock();//加锁
            try {
                //等待
                while (numRun!=2) mCondition1.await();
                //执行循环
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread2 sequence of  " + i);
                }
                //执行完成之后让线程2执行
                numRun=3;
                mCondition3.signal();
            } finally {
                mLock.unlock();
            }
        }

        private void thread3() throws InterruptedException {
            mLock.lock();//加锁
            try {
                //等待
                while (numRun!=3) mCondition1.await();
                //执行循环
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread3 sequence of  " + i);
                }
                //执行完成之后让线程2执行
                numRun=1;
                mCondition1.signal();
            } finally {
                mLock.unlock();
            }
        }

        private boolean isSub = true;
        private Condition mCondition=mLock.newCondition();//实现线程间的通信
        private  void sub() {
            mLock.lock();
            //等待
            try {
                while (!isSub) {
                    try {
                        mCondition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //执行循环
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("sub thread sequence of  " + i);
                }
                //唤醒
                mCondition.signal();
                isSub = !isSub;
            } finally {
                mLock.unlock();
            }
        }

        private  void main() {
            mLock.lock();
            try {
                //等待
                while (isSub) {//这里必须要使用while,可防止伪唤醒
                    try {
                        mCondition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //执行循环
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("main thread sequence of  " + i);
                }
                //唤醒
                mCondition.signal();
                isSub = !isSub;
            } finally {
                mLock.unlock();
            }
        }
    }
}
