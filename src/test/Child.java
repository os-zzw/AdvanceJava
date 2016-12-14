package test;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by john on 2016/11/23.
 */
public class Child extends Father {
    public static void main(String[] args) throws Exception {
        ArrayList<String> l = new ArrayList();

        //编译器在编译完成之后,会去掉类型信息
        Method add = l.getClass().getMethod("add", String.class);
        add.invoke(l,12);
        System.out.println(l);


    }
}
