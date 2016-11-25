package annotation;

/**
 * Created by john on 2016/11/22.
 */
@ZzwAnnotation()
public class AnnotationTest {
    @SuppressWarnings("deprecation")//设置知道过时了

    public static void main(String[] args) {
        System.runFinalizersOnExit(true);
        if (AnnotationTest.class.isAnnotationPresent(ZzwAnnotation.class)) {
            ZzwAnnotation annotation = AnnotationTest.class.getAnnotation(ZzwAnnotation.class);
            System.out.println(annotation);
            System.out.println("adasd");
        }
    }
}
