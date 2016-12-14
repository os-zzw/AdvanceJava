package mutithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by john(Zhewei) on 2016/12/1.
 * 线程池中固定线程 以及 线程池中的定时器
 */
public class ThreadPool {

    public static void main(String[] args) {
        List<String> numList= new ArrayList<>();

        for(int i=1;i<=10000;i++){
            numList.add(String.valueOf(i));
        }

        ExecutorService pools= Executors.newFixedThreadPool(10);
        for(int i =1;i<11;i++){
            int finalI = i;
            pools.execute(new Runnable(){
                @Override
                public void run(){
                    for(int j = (finalI -1)*1000; j< finalI *1000; j++){
                        if(Integer.parseInt(numList.get(j))%2==0){
                            System.out.println(numList.get(j));
                        }
                    }
                }
            });
        }
    }
//    public static void main(String[] args) {
//        //固定几条线程
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
//
//        //固定单线程-->好处是,当线程死掉的时候,还可以再生成一条线程继续执行
//        //可以用来实现线程死掉后重新启动
//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//
//
//        for (int i = 0; i < 10; i++) {
//            int finalI = i;
//            fixedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println(Thread.currentThread().getName() + "is looping" + finalI);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//        System.out.println("shut down");
//        fixedThreadPool.shutdown();
//
//        //线程池中的定时器
//        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
//        scheduledThreadPool.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hhhh");
//            }
//        }, 2, TimeUnit.SECONDS);//10秒之后执行该代码
//
//        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("111");
//            }
//        }, 2, 2, TimeUnit.SECONDS);
//        //
//    }
}
