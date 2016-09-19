package mk.ukim.finki.np.mt2;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

public class ComponentTest {

}

class Component implements Comparable<Component> {
	String color;
	int weight;
	TreeSet<Component> components;

	public Component(String color, int weight) {
		this.color = color;
		this.weight = weight;
		components = new TreeSet<Component>();
	}

	public void addComponent(Component component) {
		components.add(component);
	}

	@Override
	public int compareTo(Component o) {
		int w = Integer.compare(weight, o.weight);
		if (w == 0) {
			return color.compareTo(o.color);
		}
		return w;
	}

	@Override
	public String toString() {
		return String.format("%d:%s", weight, color);
	}

}

class Window {
	String name;
	TreeMap<Integer, Component> components;

	public Window(String name) {
		this.name = name;
		this.components = new TreeMap<Integer, Component>();
	}

	void addComponent(int position, Component component)
			throws InvalidPositionException {
		if (components.containsKey(position)) {
			throw new InvalidPositionException(position);
		}
		components.put(position, component);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(name);
		result.append("\n");
		for (Integer position : components.keySet()) {
			Component component = components.get(position);
			result.append(String.format("%d:", position));
			createString(component, result, 1);
		}
		return result.toString();
	}

	void createString(Component component, StringBuilder result, int depth) {
		result.append(String.format("%s%s\n", depth(depth),
				component.toString()));
		for (Component c : component.components) {
			createString(c, result, depth + 1);
		}
	}

	void changeColor(int weight, String color) {
		for(Entry<Integer, Component> entry : components.entrySet()) {
			changeColorComponent(weight, color, entry.getValue());
		}
	}

	void changeColorComponent(int weight, String color, Component component) {
		
		if (component.weight < weight) {
			component.color = color;
		}
		for (Component c : component.components) {
			changeColorComponent(weight, color, c);
		}
	}
	
	void swichComponents(int pos1, int pos2)  {
		Component temp = components.get(pos1);
		components.put(pos1, components.get(pos2));
		components.put(pos2, temp);
	}

	String depth(int d) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < d; ++i) {
			res.append("---");
		}
		return res.toString();
	}

}

class InvalidPositionException extends Exception {
	public InvalidPositionException(int pos) {
		super(String.format("Invalid position %d, alredy taken!", pos));
	}
}
