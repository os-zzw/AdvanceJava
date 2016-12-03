package mutithread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by john(Zhewei) on 2016/12/2.
 * 设计一个缓存系统(面试题)  读写锁(实现互斥)
 */
public class CacheDemo {

    private Map<String, Object> cache = new HashMap<>();

    //声明读写锁
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public Object getData(String key) {
        //首先加上读锁,多个线程可以同时读
        rwl.readLock().lock();
        Object value = null;
        try {
            //从缓存中拿到数据
            value = cache.get(key);
            //当缓存中没有数据时,从数据库拿数据
            if (value == null) {
                rwl.readLock().unlock();
                //添加写锁
                rwl.writeLock().lock();
                try {
                    if (value == null) {
                        value = "bbbb";//模拟从数据库中拿到数据
                    }
                } finally {
                    rwl.writeLock().lock();
                }
                rwl.readLock().lock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();//注意一定要在finally块中解锁,防止一个线程出现问题导致无法解锁
        }
        return value;
    }

}
