package com.mflores.algs.adt;

import java.util.Iterator;

public class ResizingArrayQueue<T> {
	
	private T[] array;
	private int tail; // tail is 1-based
	private int head; // head is 0-based
	
	@SuppressWarnings("unchecked")
	public ResizingArrayQueue(int initialCapacity) {
		array = (T[]) new Object[initialCapacity];
	}
	
	public boolean isEmpty() {
		return tail == head;
	}
	
	public int size() {
		return tail;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int max) {
		T[] temp = (T[]) new Object[max];
		int j=0;
		for (int i = head; i < tail; i++, j++) {
			temp[j] = array[i];
		}
		tail = tail - head;
		head = 0;
		array = temp;
	}
	
	public T queue(T object) {
		if((tail-head) == array.length) { 
			resize(2*tail);
		}
		array[tail++] = object;
		logContents();
		return object;
	}

	public T dequeue() {
		T object = array[head++];
		array[head-1] = null;
		if ((tail-head) <= array.length/4) {
			resize(array.length/2);
		}
		logContents();
		return object;
	}
	
	public void logContents() {
		for (int i = 0; i < array.length; i++) {
			System.out.print("[" + (array[i] != null ? array[i] : " ") + "]");
		}
		System.out.println();
	}
	
	public Iterator<T> iterator() {
		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<T> { // Support LIFO iteration.
		private int i = head;

		public boolean hasNext() {
			return i < tail;
		}

		public T next() {
			return array[i++];
		}

		public void remove() {
		}
	}
	
	public static void main(String[] args) {
		ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>(4);
		queue.queue("Hi");
		queue.queue("Thiss");
		queue.queue("Goes");
		queue.queue("Well");
		queue.queue("heehe");
		
		queue.dequeue();
		queue.dequeue();
		
		queue.queue("Stahp");
		
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		
		
		queue.queue("Oh");
		queue.queue("Noes");
		
		System.out.println("Iterating over queue contents. No empty spaces shown:");
		Iterator it = queue.iterator();
		while(it.hasNext()) {
			System.out.print("[" + it.next() + "]");
		}
		
	}

}
