package reflect;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * Created by john on 2016/11/22.
 * 反射  用来实现Ioc  控制反转和依赖注入
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {
//        basic();
//        constructorC();
//        Field();
//        changeFieldTest();
//        invokeMethod();

//        reflect.TestArgument.main(new String[]{"123", "222"});

//        String startingClassName = args[0];
//        sendParamter();
        arrayReflect();

    }

    private static void arrayReflect() {
        int[] a1 = new int[]{1, 2, 3};
        int[] a2 = new int[4];
        int[][] a3 = new int[3][4];
        String[] s2 = new String[]{"asd", "asdasd"};
        System.out.println(a1.getClass() == a2.getClass());
        System.out.println(a1.getClass().getName());
        System.out.println(a1.getClass().getSuperclass());
        System.out.println(Arrays.asList(a1));
        System.out.println(Arrays.asList(s2));
        //基本类型的数组只能当做Object不能被解析为Object[],但是非基本类型的可以

        printObject(a1);
        printObject(s2);
    }

    private static void printObject(Object obj) {
        Class<?> aClass = obj.getClass();
        if (aClass.isArray()) {
            for (int i = 0; i < Array.getLength(obj); i++) {
                System.out.println(Array.get(obj, i));
            }
        } else {
            System.out.println(obj);
        }
    }

    private static void sendParater() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class.forName("reflect.TestArgument");


        String startingClassName = "reflect.TestArgument";
        Method main = Class.forName(startingClassName).getMethod("main", String[].class);
        main.invoke(null, (Object) new String[]{"123", "444"});//我给你的是一个对象,不是数组别拆包
    }

    private static void invokeMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //实现str1.charAt(1);
        String str1 = "abcde";
        Method charAt = String.class.getMethod("charAt", int.class);
        //方法的调用
        Object invoke = charAt.invoke(str1, new Object[]{2});
        //静态方法调用的时候第一个参数传null
        System.out.println(invoke);
    }


    private static void changeFieldTest() throws IllegalAccessException {
        ReflectPoint reflectPoint = new ReflectPoint(1, 2);
        System.out.println(reflectPoint);
        changeField(reflectPoint);
        System.out.println(reflectPoint);
    }

    private static void changeField(ReflectPoint reflectPoint) throws IllegalAccessException {
        Field[] fields = ReflectPoint.class.getFields();
        for (Field field : fields) {
            if (field.getType() == String.class) {
                String o = (String) field.get(reflectPoint);
                String replace = o.replace("b", "a");
                field.set(reflectPoint, replace);
            }
        }
    }

    private static void Field() throws NoSuchFieldException, IllegalAccessException {
        Field y = ReflectPoint.class.getField("y");
        Field x = ReflectPoint.class.getDeclaredField("x");
        ReflectPoint reflectPoint = new ReflectPoint(1, 5);
        Object o = y.get(reflectPoint);
        x.setAccessible(true);//暴力反射
        Object o1 = x.get(reflectPoint);
        System.out.println(o);
        System.out.println(o1);
    }


    public static class ReflectPoint {
        private int x;
        public int y;

        public String str1 = "ball";
        public String str2 = "basketball";
        public String str3 = "zzw";

        public ReflectPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "ReflectPoint{" +
                    "x=" + x +
                    ", y=" + y +
                    ", str1='" + str1 + '\'' +
                    ", str2='" + str2 + '\'' +
                    ", str3='" + str3 + '\'' +
                    '}';
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }


    private static void constructorC() throws NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        //使用反射实现   new String(new Stringbuffer("adb"))
        Constructor<String> stringConstructor = String.class.getConstructor(StringBuffer.class);
        String adc = stringConstructor.newInstance(new StringBuffer("adc"));
        System.out.println(adc);
    }


    private static void basic() throws ClassNotFoundException {
        String str1 = "abc";
        Class cla1 = str1.getClass();
        Class cla2 = String.class;
        Class cla3 = Class.forName("java.lang.String");
        System.out.println(cla1 == cla2);
        System.out.println(cla3 == cla2);
        //打印是否是原始类型
        System.out.println(cla1.isPrimitive());
        //只有9个原始类型,8个基本类型的加上void.class
        Class<Integer> aClass = int.class;
        System.out.println(aClass.isPrimitive());
        //不同
        System.out.println(int.class == Integer.class);
        //Integter.type包装的是基本类型的字节码
        System.out.println(int.class == Integer.TYPE);
        //数组也是一种类型,并且不是原始类型
        System.out.println(int[].class.isPrimitive());
        //可判断是否是数组
        System.out.println(int[].class.isArray());
    }
}

class TestArgument {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}