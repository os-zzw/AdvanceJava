package mutithread;

/**
 * Created by ZheWei on 2016/11/29.
 * 同步
 */
public class TraditionalThreadSynchronized {
    public static void main(String[] args) {
        TraditionalThreadSynchronized traditionalThreadSynchronized = new TraditionalThreadSynchronized();
        traditionalThreadSynchronized.init();
    }


    private void init() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            OutPuter outPuter = new OutPuter();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    outPuter.output("zhangzhewei");
                    //                    new OutPuter().output("zhangzhewei");
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    outPuter.output("laogelaoge");
                    //                    new OutPuter().output("laogelaoge");//这种也会出问题
                }
            }).start();

        }
    }

    class OutPuter {
        void output(String name) {
            synchronized (this) {
                int length = name.length();
                for (int i = 0; i < length; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
        }



        synchronized void output2(String name) {
                int length = name.length();
                for (int i = 0; i < length; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
        }

        //当方法成为静态方法的时候
        //synchronized 关键字 使用的是类的字节码作为同步锁
    }


}
