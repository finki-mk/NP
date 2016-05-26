package edu.finki.np.av8;

import java.util.Scanner;

interface Observer {
	//protected Subject subj;

	public abstract void update(Subject subj);
}

class HexObserver implements Observer {
	public HexObserver() {
	}

	public void update(Subject subj) {
		System.out.print(" " + Integer.toHexString(subj.getState()));
	}
} // Observers "pull" information

class OctObserver implements Observer {
	public OctObserver() {
	}

	public void update(Subject subj) {
		System.out.print(" " + Integer.toOctalString(subj.getState()));
	}
} // Observers "pull" information

class BinObserver implements Observer {
	public BinObserver() {
		
	} // Observers register themselves

	public void update(Subject subj) {
		System.out.print(" " + Integer.toBinaryString(subj.getState()));
	}
}

class Subject {
	private Observer[] observers = new Observer[9];
	private int totalObs = 0;
	private int state;

	public void attach(Observer o) {
		observers[totalObs++] = o;
	}

	public int getState() {
		return state;
	}

	public void setState(int in) {
		state = in;
		notifySubscribers();
	}

	private void notifySubscribers() {
		for (int i = 0; i < totalObs; i++) {
			observers[i].update(this);
		}
	}
}

public class ObserverDemo {
	public static void main(String[] args) {
		Subject sub = new Subject();
		// Client configures the number and type of Observers
		sub.attach(new HexObserver());
		sub.attach(new OctObserver());
		sub.attach(new BinObserver());
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.print("\nEnter a number: ");
			sub.setState(scan.nextInt());
		}
	}
}
