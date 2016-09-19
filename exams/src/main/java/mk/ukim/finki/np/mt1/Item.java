package mk.ukim.finki.np.mt1;


public abstract class Item implements Comparable<Item> {
	public static final int MAX = 10;
	private int order;
	
	@Override
	public int compareTo(Item o) {
		return this.order - o.order;
	}
	
	public int getOrder() {
		return order;
	}
}
