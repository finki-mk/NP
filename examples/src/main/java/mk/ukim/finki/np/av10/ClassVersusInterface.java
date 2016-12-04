package mk.ukim.finki.np.av10;

import java.util.ArrayList;
import java.util.List;

interface AlarmListener {
	public void alarm();
}

class SensorSystem {
	private List<AlarmListener> listeners = new ArrayList<AlarmListener>();

	public void register(AlarmListener al) {
		listeners.add(al);
	}

	public void soundTheAlarm() {
		for (AlarmListener listener : listeners) {
			listener.alarm();
		}
	}
}

class Lighting implements AlarmListener {
	public void alarm() {
		System.out.println("lights up");
	}
}

class Gates implements AlarmListener {
	public void alarm() {
		System.out.println("gates close");
	}
}

class CheckList {
	public void byTheNumbers() { // Template Method design pattern
		localize();
		isolate();
		identify();
	}

	protected void localize() {
		System.out.println("   establish a perimeter");
	}

	protected void isolate() {
		System.out.println("   isolate the grid");
	}

	protected void identify() {
		System.out.println("   identify the source");
	}
}

class Surveillance extends CheckList implements AlarmListener {
	public void alarm() {
		System.out.println("Surveillance - by the numbers:");
		byTheNumbers();
	}

	protected void isolate() {
		System.out.println("   train the cameras");
	}
}

public class ClassVersusInterface {
	public static void main(String[] args) {
		SensorSystem ss = new SensorSystem();
		ss.register(new Gates());
		ss.register(new Lighting());
		ss.register(new Surveillance());
		ss.soundTheAlarm();
	}
}