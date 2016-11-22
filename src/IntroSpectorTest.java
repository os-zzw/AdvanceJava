import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Created by john on 2016/11/22.
 */
public class IntroSpectorTest {
    public static void main(String[] args) throws Exception {
        ReflectTest.ReflectPoint reflectPoint = new ReflectTest.ReflectPoint(1, 4);

        PropertyDescriptor descriptor = new PropertyDescriptor("x", reflectPoint.getClass());
        Method readMethod = descriptor.getReadMethod();
        Object invoke = readMethod.invoke(reflectPoint, null);
        System.out.println(invoke);
        Method writeMethod = descriptor.getWriteMethod();
        System.out.println(reflectPoint);
        writeMethod.invoke(reflectPoint, 10);
        System.out.println(reflectPoint);
    }
}
