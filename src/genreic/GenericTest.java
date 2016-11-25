package genreic;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Vector;

/**
 * Created by john on 2016/11/25.
 */
public class GenericTest {

    public static void main(String[] args) throws NoSuchMethodException {
        Method applyVector = GenericTest.class.getMethod("applyVector", Vector.class);
        Type[] genericParameterTypes = applyVector.getGenericParameterTypes();
        ParameterizedType parameterType = (ParameterizedType) genericParameterTypes[0];
        //得到方法的参数的泛型
        Type[] types = parameterType.getActualTypeArguments();
        System.out.println(types[0]);

    }

    public static void applyVector(Vector<Date> vector) {

    }
}
