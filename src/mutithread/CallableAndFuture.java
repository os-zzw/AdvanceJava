package mutithread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by john(Zhewei) on 2016/12/1.
 * submit()方法  用来执行Callable 可以拿到线程的返回结果
 * 以及 拿到率先完成的线程的返回值
 */
public class CallableAndFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        Future<Object> future = singleThreadExecutor.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(2000);
                return "hello";
            }
        });

        System.out.println("等待结果");
        System.out.println(future.get());//等待完成后拿到结果
        System.out.println(future.get(1, TimeUnit.SECONDS));//该方法是必须在1秒内拿到结果,否则会抛出异常


        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(fixedThreadPool);

        for (int i = 0; i < 10; i++) {
            int temp = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int i1 = new Random().nextInt(1000);
                    Thread.sleep(i1);
                    return temp;
                }
            });
        }

        //先拿到率先执行完的线程返回的数值
        for (int i = 0; i < 10; i++) {
            System.out.println(completionService.take().get());
        }


    }

}
