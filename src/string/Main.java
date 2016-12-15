package string;

import java.util.StringTokenizer;

/**
 * Created by john(Zhewei) on 2016/12/15.
 * 三种对字符串的分割的比较
 */
public class Main {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10000; i++) {
            stringBuffer.append(i);
            stringBuffer.append(";");
        }

        String string = stringBuffer.toString();
        //分割字符串 第一种使用split();性能最差
        String[] split = string.split(";");
        //第二种
        StringTokenizer stringTokenizer = new StringTokenizer(string, ";");
        while (stringTokenizer.hasMoreElements()) {
            String s = stringTokenizer.nextToken();
            //            System.out.println(s);
        }
        //第三种,自己实现
        String tmp = string;
        for (int i = 0; i < 10000; i++) {
            while (true) {
                String spliteString = null;
                int j = tmp.indexOf(";");
                if (j < 0)
                    break;
                spliteString = tmp.substring(0, j);
                System.out.println(spliteString);
                tmp = tmp.substring(j + 1);
            }
        }
    }
}
