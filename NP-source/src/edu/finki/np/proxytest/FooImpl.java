package edu.finki.np.proxytest;

public class FooImpl implements Foo {
    public Object bar(Object obj)  {
      System.out.println("Bar method");
      return null;
    }

  @Override
  public void x() {
    System.out.println("method x");
  }
}