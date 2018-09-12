package com.mflores.algs.tests;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

public class Parenthesis {
	
	private Stack<String> stack = new Stack<>();
	
	public boolean validateStringSequence(String [] sequence){
		
		for (String item : sequence) {
			if(item.equals("[") || item.equals("(") || item.equals("{")) {
				stack.push(item);
			}else {
				if(stack.size() == 0) {
					return false;
				}
				String popped = stack.pop();
				if(item.equals("]") && !popped.equals("[")) {
					return false;
				} else if(item.equals(")") && !popped.equals("(")) {
					return false;
				} else if(item.equals("}") && !popped.equals("{")) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		String [] sequence=  "[{[]{]]".split("(?!^)");
		Parenthesis p = new Parenthesis();
		boolean val = p.validateStringSequence(sequence);
		System.out.println("Valition is: "+val);
	}

}
