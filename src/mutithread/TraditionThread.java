package mutithread;

/**
 * Created by john on 2016/11/29.
 * 1.多线程并不会提高程序的运行效率
 * 2.
 */
public class TraditionThread {
    public static void main(String[] args) {


        class MyThread extends Thread{
            @Override
            public void run() {
            sysoThread();
            }
        }
       new Thread(new Runnable() {
           @Override
           public void run() {
               sysoThread();
           }
       }).start();

      new MyThread().start();
    }

    private static void sysoThread() {
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
