package edu.finki.np.av1;

/**
 * Created by tdelev on 8.1.16.
 */
public class Test {
  public static void main(String[] args) throws InterruptedException {
    System.out.println("Hello world");
    Thread t = new Thread(new Runnable() {
      @Override
      public void run() {
        //while(true) {
          System.out.println("This is a thread");
        //}
      }
    });
    t.start();
    Thread.sleep(5000);
    System.out.println("End");
  }
}
