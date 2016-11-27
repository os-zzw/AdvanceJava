package classloader;

import java.util.Date;

/**
 * Created by john on 2016/11/26.
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        test1();
        Class<?> aClass = new MyClassLoader("class").loadClass("classloader.ClassLoaderAttachment");
        Date attachment = (Date) aClass.newInstance();
        String string1 = attachment.toString();
        System.out.println(string1);
    }

    private static void test1() {
        //这是由AppClassLoader来进行加载的
        String name = ClassLoaderTest.class.getClassLoader().getClass().getName();
        //这个会报异常,因为该类的加载器是BootStrap,他不是一个类,所以找不到
        //一般java的内部类,都是由其加载的
//        String name1 = List.class.getClassLoader().getClass().getName();
        System.out.println(name);

        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        while (classLoader != null) {
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }
    }
}
