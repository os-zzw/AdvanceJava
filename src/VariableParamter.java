/**
 * Created by john on 2016/11/19.
 * 可变参数
 */
public class VariableParamter {
    public static void main(String[] args) {
        int add = add(1, 2, 3);
        System.out.println(add);
    }

    private static int add(int... args) {
        int sum = 0;
        for (int arg : args) {
            sum += arg;
        }
        return sum;
    }
}
