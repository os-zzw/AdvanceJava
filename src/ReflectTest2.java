import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;

/**
 * Created by john on 2016/11/22.
 */
public class ReflectTest2 {
    public static void main(String[] args) throws Exception {
//        CollectonsHasCode();

//        FileInputStream inputStream = new FileInputStream("config.properties");
//        InputStream inputStream = ReflectTest2.class.getClassLoader().getResourceAsStream("config.properties");

        InputStream inputStream = ReflectTest2.class.getResourceAsStream("config.properties");

        Properties properties = new Properties();
        properties.load(inputStream);
        inputStream.close();

        String className = properties.getProperty("className");
        Collection collection = (Collection) Class.forName(className).newInstance();

        ReflectTest.ReflectPoint r1 = new ReflectTest.ReflectPoint(1, 1);
        ReflectTest.ReflectPoint r2 = new ReflectTest.ReflectPoint(2, 2);
        ReflectTest.ReflectPoint r3 = new ReflectTest.ReflectPoint(1, 1);
        collection.add(r1);
        collection.add(r2);
        collection.add(r3);
        collection.add(r3);

        System.out.println(collection.size());

    }

    private static void CollectonsHasCode() {
        //        Collection collection = new ArrayList();
        Collection<ReflectTest.ReflectPoint> collection = new HashSet<ReflectTest.ReflectPoint>();
        ReflectTest.ReflectPoint r1 = new ReflectTest.ReflectPoint(1, 1);
        ReflectTest.ReflectPoint r2 = new ReflectTest.ReflectPoint(2, 2);
        ReflectTest.ReflectPoint r3 = new ReflectTest.ReflectPoint(1, 1);
        collection.add(r1);
        collection.add(r2);
        collection.add(r3);
        collection.add(r3);

        System.out.println(collection.size());
    }
}
