package mutithread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by john on 2016/11/29.
 */
public class TraditionalTimer {
    private static int count;
    static int i=0;
    public static void main(String[] args) throws Exception {

        //first
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("boom");
//            }
//        },1000,2000);
        //

        class Mytask extends TimerTask{
            @Override
            public void run() {
                count=(count+1)%2;
                System.out.println("boom"+count);
                new Timer().schedule(new Mytask(),2000*count+2000);
            }
        }
        Mytask mytask = new Mytask();
        new Timer().schedule(mytask,2000);



        circlePrint();
    }

    private static void circlePrint() throws InterruptedException {
        while (true){
            Thread.sleep(1000);
            System.out.println(i++);
        }
    }


}
