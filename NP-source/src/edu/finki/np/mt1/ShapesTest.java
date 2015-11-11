package edu.finki.np.mt1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShapesTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Canvas canvas = new Canvas();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			int type = Integer.parseInt(parts[0]);
			String id = parts[1];
			if (type == 1) {
				Color color = Color.valueOf(parts[2]);
				float radius = Float.parseFloat(parts[3]);
				canvas.add(id, color, radius);
			} else if (type == 2) {
				Color color = Color.valueOf(parts[2]);
				float width = Float.parseFloat(parts[3]);
				float height = Float.parseFloat(parts[4]);
				canvas.add(id, color, width, height);
			} else if (type == 3) {
				float scaleFactor = Float.parseFloat(parts[2]);
				System.out.print(canvas);
				canvas.scale(id, scaleFactor);
				System.out.printf("SCALING: %s %.2f\n", id, scaleFactor);
				System.out.print(canvas);
			}

		}
	}
}

enum Color {
	RED, GREEN, BLUE
}

class Canvas {

	List<Shape> shapes;

	public Canvas() {
		shapes = new ArrayList<Shape>();
	}

	public void add(String id, Color color, float radius) {
		Circle c = new Circle(id, color, radius);
		int index = find(c);
		shapes.add(index, c);
	}

	int find(Stackable c) {
		if (shapes.size() == 0)
			return 0;
		for (int i = 0; i < shapes.size(); ++i) {
			Shape s = shapes.get(i);
			Stackable ss = (Stackable) s;
			if (ss.weight() < c.weight())
				return i;
		}
		return shapes.size();
	}

	public void add(String id, Color color, float width, float height) {
		Rectangle r = new Rectangle(id, color, width, height);
		int index = find(r);
		shapes.add(index, r);
	}

	public void scale(String id, float scaleFactor) {
		Scalable s = null;
		int index = 0;
		for (int i = 0; i < shapes.size(); ++i) {
			if (shapes.get(i).id.equals(id)) {
				index = i;
				s = (Scalable) shapes.get(i);
				break;
			}
		}
		s.scale(scaleFactor);
		Shape toAdd = shapes.get(index);
		shapes.remove(index);
		index = find((Stackable) toAdd);
		shapes.add(index, toAdd);
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for(Shape s : shapes) {
			res.append(s);
			res.append("\n");
		}
		return res.toString();
	}

}

abstract class Shape {
	String id;
	Color color;

	public Shape(String id, Color color) {
		this.id = id;
		this.color = color;
	}

	public String toString(float weight) {
		return String.format("%-5s%10s%10.2f", id, color, weight);
	}

}

interface Scalable {
	void scale(float scaleFactor);
}

interface Stackable {
	float weight();
}

class Rectangle extends Shape implements Scalable, Stackable {
	float width;
	float height;

	public Rectangle(String id, Color color, float width, float height) {
		super(id, color);
		this.width = width;
		this.height = height;
	}

	@Override
	public float weight() {
		return width * height;
	}

	@Override
	public void scale(float scaleFactor) {
		width = width * scaleFactor;
		height = height * scaleFactor;
	}

	@Override
	public String toString() {
		return String.format("R: %s", toString(weight()));
	}

}

class Circle extends Shape implements Stackable, Scalable {
	public Circle(String id, Color color, float radius) {
		super(id, color);
		this.radius = radius;
	}

	float radius;

	@Override
	public void scale(float scaleFactor) {
		radius = radius * scaleFactor;
	}

	@Override
	public float weight() {
		return radius * radius * (float) Math.PI;
	}

	@Override
	public String toString() {
		return String.format("C: %s", toString(weight()));
	}

}
