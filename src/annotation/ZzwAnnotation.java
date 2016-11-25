package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by john on 2016/11/22.
 */
//这是元注解
//还有元数据 元信息  数据的数据  信息的信息
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface ZzwAnnotation {
    String color() default "red";

}
