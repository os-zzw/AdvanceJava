package string;

import java.util.StringTokenizer;

/**
 * Created by john(Zhewei) on 2016/12/15.
 * 三种对字符串的分割的比较
 */
public class Main {
    public static void main(String[] args) {
        //        ThreeSplit();
        TwoStartsWithAndEndsWith();
    }

    /**
     * 三种对字符串的分割的比较
     */
    private static void ThreeSplit() {
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
        //第三种,自己实现(性能最好)
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

    /**
     * charAt() 来判断 某个字符串是否以"java"开头 或 结尾
     */
    private static void TwoStartsWithAndEndsWith() {
        String string = "123adsdasa333333";
        String string1 = "java123adsdasa333333java";
        //判断这个字符串是否以ja开头
        boolean isStart = string1.charAt(0) == 'j' && string1.charAt(1) == 'a';
        boolean ja = string1.startsWith("ja");
        System.out.println(isStart);
        System.out.println(ja);
        //判断这个字符串是否以ja开头
        boolean b = string.charAt(string.length() - 1) == 'j' && string.charAt(string.length() - 2) == 'a';
        boolean ja1 = string.endsWith("ja");
    }
}
