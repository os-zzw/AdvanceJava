package proxy;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import proxy.aopframework.BeanFactory;
import proxy.aopframework.TargetInter;

/**
 * Created by john on 2016/11/27.
 * 动态代理,用来实现aop编程  面向切面编程,实现事务性
 */
public class ProxyTest {
    public static void main(String[] args) throws Exception {
//        firstTest();

//      InputStream inputStream =  proxy.ProxyTest.class.getClassLoader().getResourceAsStream("config.properties");
        InputStream inputStream = ProxyTest.class.getClassLoader().getResourceAsStream("config.properties");

        BeanFactory beanFactory = new BeanFactory(inputStream);
        //在这里只能转换成接口,不能直接转化成为类
        TargetInter list = (TargetInter) beanFactory.getBean("factory");
        list.Print();
    }

    private static void firstTest() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        //三种方式创建动态类
        Class<?> clazzProxy1 = Proxy.getProxyClass(Collection.class.getClassLoader(),
                Collection.class);
        String clazzProxy1Name = clazzProxy1.getName();
        System.out.println(clazzProxy1);
        Constructor<?>[] constructors = clazzProxy1.getConstructors();
//        for (Constructor<?> constructor : constructors) {
//            System.out.println(constructor);
//            String name = constructor.getName();
//            StringBuilder stringBuilder = new StringBuilder(name);
//            stringBuilder.append("(");
//            Class<?>[] parameterTypes = constructor.getParameterTypes();
//            for (Class<?> parameterType : parameterTypes) {
//                String name1 = parameterType.getName();
//                stringBuilder.append(name1);
//            }
//            stringBuilder.append(")");
//            System.out.println(stringBuilder);
//        }
        Constructor<?> constructor = clazzProxy1.getConstructor(InvocationHandler.class);
        class InvocationTest implements InvocationHandler {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        }
        //1
        Collection instance = (Collection) constructor.newInstance(new InvocationTest());

        //2
        Collection instance1 = (Collection) constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });

        //3
        ArrayList list = new ArrayList();
        List instance2 = (List) getProxy(list);

        instance2.add("asd");

        System.out.println(instance2);
    }

    private static Object getProxy(final Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object invoke = method.invoke(target, args);
                        return invoke;
                    }
                });
    }
}
