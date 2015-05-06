package edu.finki.np.av7;



import java.util.*;

public class Factories14 {
   public static void main(String[] args) {
      // create a list with n copies
      Fruit f = new Fruit("apple", 10);
      List<Fruit> list;
       list = Collections.nCopies(5, f);

      System.out.println(list);

      f.setFruitName("banana");

      System.out.println(list);


//      // create an iterator
//      Iterator itr = list.iterator();
//
//      System.out.println("Values are :");
//      System.out.println(list);
//      while (itr.hasNext()){
//         System.out.println(itr.next());
//      }
   }      
}
