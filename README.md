##设计一个多线程的缓存系统(读写锁)
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
            rwl.readLock().unlock();//注意一定要在finally块中解锁,防止一个线程出现问题
			//导致无法解锁
        }
        return value;
    }
##Condition实现线程间的通信
	1. 使用 private Lock mLock = new ReentrantLock();
	2.     private Condition mCondition=mLock.newCondition();
	3. 来实现线程之间的通信,替代Object.wait()和Object.notify()方法.
	4. 下面来实现首先线程1循环10次然后线程2循环10次然线程3循环10次 如此循环下去
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
##实现多线程循环队列 来模拟有界缓冲区
	/**
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
	
##Semaphore信号灯
	1. 维护当前访问资源的线程个数,并提供了同步机制
	2. 例如实现一个文件允许的并发访问数
	3. 当Semaphore设置为单个信号量的时候,可以实现互斥锁的功能,并且可以是一个线程拿到锁,由
	   另一个线程释放锁,可应用于一些死锁回复的场景.
	4. 下面是代码示例
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

##CyclicBarrier   多个线程彼此等待都准备好之后在执行任务
	1. 可以实现多个线程都达到某一个地步之后再进行执行下一步任务,多个线程在不同时间内完成一个任务后,再同时开始下一个任务.
	2. 下面是代码示例:
	
