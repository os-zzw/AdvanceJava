package proxy;

import java.lang.reflect.*;
import java.util.Collection;

/**
 * Created by john on 2016/11/27.
 */
public class ProxyTest {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

        Class<?> clazzProxy1 = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);

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
        Collection instance = (Collection) constructor.newInstance(new InvocationTest());
        System.out.println(instance);
        instance.clear();

    }
}
