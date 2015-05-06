package edu.finki.np.av7;



import java.util.*;

public class Factories13 {
   public static void main(String[] a){
      // create singleton map
      Map map = Collections.singletonMap("kluc","vrednost");
      Map<Integer, String> m2 = Collections.singletonMap(5, "Hello");
      Map<Integer, Integer> em  = Collections.emptyMap();
      em.put(4, 6);
      System.out.println("Singleton map is: " + map);
      System.out.println(m2);
      m2.put(4, "FINKI");
      //map.put("aaa", "vvvv");
   }
}
