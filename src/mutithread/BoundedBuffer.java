package mutithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by john(Zhewei) on 2016/12/3.
 * 实现多线程循环队列
 */
public class BoundedBuffer {
    private Lock mLock = new ReentrantLock();
    //定义两个条件
    private Condition full = mLock.newCondition();
    private Condition empty = mLock.newCondition();
    //缓冲区大小
    private Object[] items = new Object[100];

    //定义循环队列的两个指针以及当前缓冲区大小
    private int putptr, takeptr, count;

    //向缓冲区放数据
    public void put(Object obj) throws InterruptedException {
        mLock.lock();
        try {
            //当缓冲区满的时候,等待
            while (count == items.length)
                full.await();
            //放入数据
            items[putptr++] = obj;
            //循环起来
            if (putptr == items.length)
                putptr = 0;
            //当前缓冲区大小增加
            count++;
            //唤醒空条件
            empty.signal();
        } finally {
            mLock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        mLock.lock();
        Object value = null;
        try {
            //当缓冲区空的时候,等待
            while (count == 0)
                empty.await();
            //拿走数据
            value = items[takeptr++];
            //循环利用数组
            if (takeptr == items.length)
                takeptr = 0;
            //当前缓冲区大小减少
            count--;
            //唤醒满条件
            full.signal();
        } finally {
            mLock.unlock();
        }
        return value;
    }
}
