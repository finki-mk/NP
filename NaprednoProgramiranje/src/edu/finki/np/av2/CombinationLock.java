package edu.finki.np.av2;
public class CombinationLock {
	private int first;
	private int second;
	private int third;
	private boolean isOpen;

	public CombinationLock(int first, int second, int third) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.isOpen = false;
	}

	public boolean open(int first, int second, int third) {
		this.isOpen = (this.first == first && this.second == second && this.third == third);
		return this.isOpen;
	}

	public boolean changeCombo(int first, int second, int third, int newFirst,
			int newSecond, int newThird) {
		boolean isCorrect = (this.first == first && this.second == second && this.third == third);
		if (isCorrect) {
			this.first = newFirst;
			this.second = newSecond;
			this.third = newThird;
		}
		return isCorrect;
	}

}
