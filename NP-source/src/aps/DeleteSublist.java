package aps;

import java.util.Scanner;

/**
 * Created by tdelev on 30.5.16.
 */
public class DeleteSublist {
  public static void main(String[] args) {
    DLL<Integer> first = new DLL<>();
    DLL<Integer> second = new DLL();
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    for (int i = 0; i < n; ++i) {
      first.insertLast(scanner.nextInt());
    }
    n = scanner.nextInt();
    for (int i = 0; i < n; ++i) {
      second.insertLast(scanner.nextInt());
    }
    //System.out.println(first);
    //System.out.println(second);
    deleteSecond(first, second);
    DLLNode<Integer> node = first.getFirst();
    if(node == null) {
      System.out.println("Prazna lista");
    }
    while (node != null) {
      System.out.print(node.element);
      if (node.succ != null) {
        System.out.print(" ");
      }
      node = node.succ;
    }
  }

  static void deleteSecond(DLL<Integer> first, DLL<Integer> second) {
    DLLNode<Integer> f = first.getFirst();
    while (f != null) {
      DLLNode<Integer> s = second.getFirst();
      DLLNode<Integer> ff = f;
      int count = 0;
      while (s != null && ff != null) {
        if (ff.element.equals(s.element)) {
          ++count;
        }
        s = s.succ;
        ff = ff.succ;
      }
      if (count == second.length()) {
        int cc = count;
        DLLNode<Integer> del = f;
        while (cc > 0) {
          f = f.succ;
          //System.out.println("F: " + f);
          --cc;
        }
        //System.out.println("HERE DELETE");
        while (count > 0) {
          DLLNode<Integer> x = del.succ;
          //System.out.println("X: " + x);
          first.delete(del);
          del = x;
          --count;
        }
        //return;
      } else {
        f = f.succ;
      }
    }
  }
}
