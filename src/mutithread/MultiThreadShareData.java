package mutithread;

import static mutithread.TraditionalTimer.i;

/**
 * Created by john(Zhewei) on 2016/12/1.
 * 多个线程 同时访问同一个 数据
 */
public class MultiThreadShareData {

    private static Ticket ticket = new Ticket();

    public static void main(String[] args) {
        for (int j = 0; j < 5; j++) {
            new Thread(ticket).start();
        }
    }

    private static class Ticket implements Runnable {
        private int count = 1;

        @Override
        public void run() {
            try {
                if (count >= 1) {
                    delete();
                } else {
                    System.out.println(i + "没票了");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void saleTicket() {
            try {
                for (int i = 0; i < 3; i++) {
                    if (count >= 1) {
                        delete();
                    } else {
                        System.out.println(i + "没票了.");
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public void add() {
            count++;
        }

        public void delete() throws InterruptedException {
            count--;
            System.out.println("卖票了");
        }
    }
}
