package edu.finki.np.lab3;

import java.util.*;

public class PaintTest {

	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		System.out.println("Ekranot e 30x30 pikseli");
		Screen s = new Screen(30, 30);
		System.out.println("Poceten ekran, prazen");
		s.display();
		Paint p = new Paint();
		jin.next();
		p.addShape(new Rectangle(new Pixel(new Point(jin.nextInt(), jin
				.nextInt()), jin.next().charAt(0)), jin.nextInt(), jin
				.nextInt()));
		System.out.println("Eden pravogolnik");
		p.draw(s);
		s.display();
		jin.next();
		p.addShape(new Circle(new Pixel(
				new Point(jin.nextInt(), jin.nextInt()), jin.next().charAt(0)),
				jin.nextInt()));
		System.out.println("pravogolnik i krug");
		p.draw(s);
		s.display();

		jin.next();
		Shape ss = p.select(new Point(jin.nextInt(), jin.nextInt()));
		if (ss != null)
			ss.setColor(jin.next().charAt(0));
		System.out.println("promena na boja na krug");
		p.draw(s);
		s.display();

		jin.next();
		p.addShape(new Circle(new Pixel(
				new Point(jin.nextInt(), jin.nextInt()), jin.next().charAt(0)),
				jin.nextInt()));
		System.out.println("nov krug");
		p.draw(s);
		s.display();

		jin.next();
		p.addShape(new Rectangle(new Pixel(new Point(jin.nextInt(), jin
				.nextInt()), jin.next().charAt(0)), jin.nextInt(), jin
				.nextInt()));
		System.out.println("nov pravoagolnik");
		p.draw(s);
		s.display();

		jin.next();
		ss = p.select(new Point(jin.nextInt(), jin.nextInt()));
		if (ss != null)
			ss.move(new Point(jin.nextInt(), jin.nextInt()));
		System.out.println("pomestena figura");
		p.draw(s);
		s.display();

		jin.next();
		jin.next();
		jin.next();
		Shape sss = p.select(new Point(jin.nextInt(), jin.nextInt()));
		p.group(new Shape[] { ss, sss });
		jin.next();
		Shape g = p.select(new Point(jin.nextInt(), jin.nextInt()));
		g.setColor(jin.next().charAt(0));
		System.out.println("oboena grupa");
		p.draw(s);
		s.display();

		jin.next();
		jin.next();
		jin.next();
		g.move(new Point(jin.nextInt(), jin.nextInt()));
		System.out.println("pomestena grupa");
		p.draw(s);
		s.display();
	}

	public static final class Point {
		private final int x, y;
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public Point(int xx, int yy) {
			x = xx;
			y = yy;
		}
		public double distance(Point p) {
			return Math.sqrt((Math.pow(x - p.getX(), 2) + Math.pow(
					y - p.getY(), 2)));
		}
	}

	public static final class Pixel {
		private final Point p;
		private final char c;

		public Point getPoint() {
			return p;
		}

		public char getColor() {
			return c;
		}

		public Pixel(Point point, char color) {
			p = point;
			c = color;
		}
	}

	public static class Screen {

		private int w, h;
		private Pixel[][] pixels;

		public Screen(int width, int height) {
			w = width;
			h = height;
			pixels = new Pixel[h][w];
			clear();
		}
		
		public char getColor(Point p) {
			return pixels[p.getX()][p.getY()].getColor();
		}

		public int getWidth() { // vraka visina na ekran
			return w;
		}

		public int getHeight() { // vraka sirina na ekran
			return h;
		}

		public void display() {
			System.out.println();
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++)
					System.out.printf("%c ", pixels[i][j].getColor());
				System.out.println();
			}
			System.out.println();
		}

		public Pixel[] getAllPixels() {
			Pixel[] p = new Pixel[w * h];
			for (int i = 0; i < h; i++)
				for (int j = 0; j < w; j++)
					p[i * w + j] = pixels[i][j];
			return p;
		}

		public void setPixel(Pixel pixel) {
			pixels[pixel.getPoint().getX()][pixel.getPoint().getY()] = pixel;
		}

		public void clear() {
			for (int i = 0; i < h; i++)
				for (int j = 0; j < w; j++)
					pixels[i][j] = new Pixel(new Point(i, j), ' ');
		}

	}

	public static abstract class Shape {
		protected Pixel p;
		public int group; // grupata vo koja pripaga Shape objektot(0=ne e vo
							// grupa)
		public static Shape[][] groups = null; // niza od site grupi vo
												// programata(zaednicko za site
												// Shape objekti)
		public static int gr_n = 0; // vkupen broj na grupi

		public Shape(Pixel topleft) {
			p = topleft;
			group = 0;
		}

		public char getColor() {

			return p.getColor();
		}

		public Pixel getTopLeft() {

			return p;
		}

		public void setTopLeft(Pixel pixel) { // go menuva pixelot sto ja cuva
												// polozbata i bojata na
												// objektot

			p = pixel;
		}

		public void move(Point point) {
			if (group == 0)
				p = new Pixel(new Point(p.getPoint().getX() + point.getX(), p
						.getPoint().getY() + point.getY()), p.getColor());
			else {
				for (int i = 0; i < groups[group - 1].length; i++)
					groups[group - 1][i].setTopLeft(new Pixel(new Point(
							groups[group - 1][i].getTopLeft().getPoint().getX()
									+ point.getX(), groups[group - 1][i]
									.getTopLeft().getPoint().getY()
									+ point.getY()), groups[group - 1][i]
							.getTopLeft().getColor()));

			}
		}

		public void draw(Screen s) {
			Pixel[] pixels = s.getAllPixels();
			int w = s.getWidth();
			int h = s.getHeight();
			for (int i = 0; i < h; i++)
				for (int j = 0; j < w; j++) {
					if (contains(pixels[i * w + j].getPoint()))
						s.setPixel(new Pixel(pixels[i * w + j].getPoint(), p
								.getColor()));
				}
		}

		public void setColor(char c) {
			if (group == 0)
				p = new Pixel(p.getPoint(), c);
			else {
				for (int i = 0; i < groups[group - 1].length; i++)
					groups[group - 1][i].setTopLeft(new Pixel(
							groups[group - 1][i].getTopLeft().getPoint(), c));
			}
		}

		public abstract boolean contains(Point point);

		public void ungroup() {

			if (group != 0) {
				int t = group;
				for (int i = 0; i < groups[t - 1].length; i++)
					groups[t - 1][i].group = 0;
				groups[t - 1] = null;
			}
		}

	}

	public static class Circle extends Shape {

		private int r;

		public Circle(Pixel topleft, int radius) {

			super(new Pixel(new Point(topleft.getPoint().getX() + radius,
					topleft.getPoint().getY() + radius), topleft.getColor()));
			r = radius;
		}

		@Override
		public boolean contains(Point point) {
			if (p.getPoint().distance(point) <= r)
				return true;
			else
				return false;
		}

	}

	public static class Rectangle extends Shape {

		private int w;
		private int h;

		public Rectangle(Pixel topleft, int width, int height) {
			super(topleft);
			w = width;
			h = height;
		}

		@Override
		public boolean contains(Point point) {

			if ((p.getPoint().getX() <= point.getX())
					&& (p.getPoint().getX() + h >= point.getX())
					&& (p.getPoint().getY() <= point.getY())
					&& (p.getPoint().getY() + w >= point.getY()))
				return true;
			else
				return false;
		}

	}

	public static class Paint {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		public Paint() {

		}

		public void addShape(Shape s) {
			shapes.add(s);
		}

		public Shape select(Point p) {
			for (int i = shapes.size() - 1; i >= 0; i--)
				if (shapes.get(i).contains(p))
					return shapes.get(i);

			return null;
		}

		public void draw(Screen screen) {
			screen.clear();
			for (int i = 0; i < shapes.size(); i++)
				shapes.get(i).draw(screen);
		}

		public void group(Shape... shapes) {
			Shape.gr_n++;
			for (int i = 0; i < shapes.length; i++)
				shapes[i].group = Shape.gr_n;
			Shape[][] s = new Shape[Shape.gr_n][];
			for (int i = 0; i < s.length - 1; i++)
				s[i] = Shape.groups[i];
			s[s.length - 1] = shapes;
			Shape.groups = s;
		}

	}

}
