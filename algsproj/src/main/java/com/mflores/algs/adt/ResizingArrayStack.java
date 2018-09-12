package com.mflores.algs.adt;

import java.util.Iterator;

public class ResizingArrayStack<T> {
	
	private T[] array;
	private int n = 0;
	
	@SuppressWarnings("unchecked")
	public ResizingArrayStack(int initialCapacity) {
		array = (T[]) new Object[initialCapacity];
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public int size() {
		return n;
	}
	
	public boolean isFull() { // should never return true
		return n == array.length;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int max) {
		T[] temp = (T[]) new Object[max];
		for (int i = 0; i < n; i++) {
			temp[i] = array[i];
		}
		array = temp;
	}
	
	public T push(T object) {
		if(n == array.length) { // never overflows. Duplicate size on full stack.
			resize(2*n);
		}
		array[n++] = object;
		logContents();
		return object;
	}

	public T pop() {
		T object = array[--n];
		array[n] = null;
		if (n > 0 && n == array.length/4) { // never becomes less than 1/4 full
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
		private int i = n;

		public boolean hasNext() {
			return i > 0;
		}

		public T next() {
			return array[--i];
		}

		public void remove() {
		}
	}
	
	public static void main(String[] args) {
		ResizingArrayStack<String> stack = new ResizingArrayStack<String>(3);
		stack.push("Go");
		stack.push("To");
		stack.push("The");
		stack.push("Market");
		
		stack.pop();
		stack.pop();
		
		stack.push("Now");
		
		stack.pop();
		stack.pop();
		
		stack.push(".");
		stack.push("But");
		stack.push("You ");
		stack.push("can ");
		stack.push("wait");
		
		System.out.println("Iteration over stack:");
		Iterator it = stack.iterator();
		while(it.hasNext()) {
			System.out.print("[" + it.next() + "]");
		}
	}

}
