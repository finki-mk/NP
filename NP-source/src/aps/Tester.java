package aps;

/**
 * Created by tdelev on 30.5.16.
 */
public class Tester {
  public static void main(String[] args) {
    DLL<Integer> list = new DLL<>();
    list.insertLast(3);
    //list.insertLast(4);
    /*list.insertLast(7);
    list.insertLast(6);
    list.insertLast(2);
*/
    System.out.println(list);
    list.swapFirstAndLast();
    System.out.println(list);
  }


}
