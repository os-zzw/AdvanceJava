package genreic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 2016/11/24.
 */
public class HashMaptest {

    public static void main(String[] args) {
        Map<String, Integer> maps = new HashMap<>();

        maps.put("a", 1);
        maps.put("b", 2);
        maps.put("c", 3);

        maps.keySet().forEach(System.out::println);

    }



}
