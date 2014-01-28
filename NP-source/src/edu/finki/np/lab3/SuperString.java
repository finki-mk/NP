package edu.finki.np.lab3;

import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

public class SuperString {
	private LinkedList<String> elements;
	private LinkedList<Integer> positions;

	public SuperString() {
		elements = new LinkedList<String>();
		positions = new LinkedList<Integer>();
	}

	public void append(String s) {
		elements.add(s);
		positions.add(1);
	}

	public void insert(String s) {
		elements.addFirst(s);
		positions.addFirst(-1);
	}

	public boolean contains(String s) {
		StringBuilder full = new StringBuilder();
		for (String element : elements) {
			full.append(element);
		}
		return full.indexOf(s) != -1;
	}

	public void reverse() {
		LinkedList<String> reversed = new LinkedList<String>();
		for (String element : elements) {
			reversed.add(reverseString(element));
		}
		Collections.reverse(reversed);
		ListIterator<Integer> i = positions.listIterator();
		while (i.hasNext()) {
			i.set(i.next() * -1);
		}
		elements = reversed;
	}

	static String reverseString(String s) {
		StringBuilder result = new StringBuilder();
		char[] ss = s.toCharArray();
		for (int i = ss.length - 1; i >= 0; --i) {
			result.append(ss[i]);
		}
		return result.toString();
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (String element : elements) {
			result.append(element);
		}
		return result.toString();
	}

	public void removeLast(int k) {
		while (k > 0) {
			int p = positions.removeFirst();
			if (p == -1) {
				elements.removeFirst();
			} else {
				elements.removeLast();
			}
			--k;
		}
	}

	public static void main(String[] args) {
		SuperString ss = new SuperString();
		ss.append("abc");
		ss.insert("xyz");
		ss.insert("mmm");
		ss.append("bbb");
		ss.reverse();
		ss.removeLast(1);
		System.out.println(ss);
	}
}
