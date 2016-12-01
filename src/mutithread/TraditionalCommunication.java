package mutithread;

/**
 * Created by john(Zhewei) on 2016/11/29.
 * 线程之间的通信
 */
public class TraditionalCommunication {

    public static void main(String[] args) {
        TraditionalCommunication communication = new TraditionalCommunication();
        communication.init();
    }

    private void init() {
        Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    business.sub();
                }
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            business.main();
        }
    }

    /**
     * 在这里要记住,同步互斥通信代码要放到 资源中  不要写在线程中
     */
    private class Business {

        private boolean isSub = true;

        private synchronized void sub() {
            //等待
            while (!isSub) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //执行循环
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("sub thread sequence of  " + i);
            }
            //唤醒
            isSub = !isSub;
            this.notify();
        }

        private synchronized void main() {
            //等待
            while (isSub) {//这里必须要使用while,可防止伪唤醒
                try {
                    this.wait();//wait的执行对象必须和synchronized对象一样
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //执行循环
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("main thread sequence of  " + i);
            }
            //唤醒
            isSub = !isSub;
            this.notify();
        }
    }
}
