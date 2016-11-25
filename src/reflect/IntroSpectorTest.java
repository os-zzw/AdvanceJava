package reflect;

import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by john on 2016/11/22.
 */
public class IntroSpectorTest {
    public static void main(String[] args) throws Exception {
        ReflectTest.ReflectPoint reflectPoint = new ReflectTest.ReflectPoint(1, 4);
        String propertyName = "x";
//        Object retVal = getProperty(reflectPoint, propertyName);
        String retVal = BeanUtils.getSimpleProperty(reflectPoint, propertyName);
        System.out.println(retVal);

        System.out.println(reflectPoint);
//        setProperty(reflectPoint, propertyName, 10);
        BeanUtils.setProperty(reflectPoint, propertyName, 10);
        System.out.println(reflectPoint);
    }


    //设置属性
    private static void setProperty(Object reflectPoint, String propertyName, Object... value) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        /**
         * 内省的简单操作
         */
//        PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, reflectPoint.getClass());
//        Method writeMethod = descriptor.getWriteMethod();
//        writeMethod.invoke(reflectPoint, value);
        /**
         * 内省的复杂操作
         */
        BeanInfo beanInfo = Introspector.getBeanInfo(reflectPoint.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String name = propertyDescriptor.getName();
            if (name.equals(propertyName)) {
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(reflectPoint, value);
            }
        }
    }

    //得到属性
    private static Object getProperty(Object reflectPoint, String propertyName) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        /**
         * 内省的简单操作
         */
//        PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, reflectPoint.getClass());
//        Method readMethod = descriptor.getReadMethod();
//        return readMethod.invoke(reflectPoint, null);
        /**
         * 内省的复杂操作
         */
        BeanInfo beanInfo = Introspector.getBeanInfo(reflectPoint.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String name = propertyDescriptor.getName();
            if (name.equals(propertyName)) {
                Method readMethod = propertyDescriptor.getReadMethod();
                Object invoke = readMethod.invoke(reflectPoint, null);
                return invoke;
            }
        }
        return null;
    }
}
