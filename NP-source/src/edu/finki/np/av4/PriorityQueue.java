package edu.finki.np.av4;

import java.util.ArrayList;

public class PriorityQueue<T> {
	private ArrayList<QueueItem<T>> queue;

	public PriorityQueue() {
		queue = new ArrayList<QueueItem<T>>();
	}

	public void add(T item, int priority) {
		int i;
		for (i = 0; i < queue.size(); i++) {
			if (queue.get(i).priority < priority) {
				break;
			}
		}
		queue.add(i, new QueueItem(item, priority));
	}

	public T remove() {
		if (queue.isEmpty()) {
			return null;
		}
		QueueItem<T> item = queue.get(0);
		queue.remove(0);
		return item.element;
	}

	public static void main(String[] args) {
		PriorityQueue<String> pq = new PriorityQueue<String>();
		pq.add("X", 0);
		pq.add("Y", 5);
		pq.add("Z", 3);
		System.out.println(pq.remove()); // Returns X
		System.out.println(pq.remove()); // Returns Z
		System.out.println(pq.remove()); // Returns Y
	}
}

class QueueItem<T> {
	T element;
	int priority;

	public QueueItem(T element, int priority) {
		this.element = element;
		this.priority = priority;
	}
}
