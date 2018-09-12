package com.mflores.algs.func;

public class TowersOfHanoi {
	
	private int count = 0;
	
	public void doMoves(int disks, char from, char to, char middle) {
		if(disks == 1) {
			System.out.println("Moving disk "+ disks + " from " +from + " to "+ to);
			count++;
		}else {
			doMoves(disks-1, from, middle, to);
			
			System.out.println("Moving disk "+ disks + " from " +from + " to "+ to);
			count++;
			
			doMoves(disks-1, middle, to, from);
		}
	}
	
	public int getCount() {
		return count;
	}
	
	public static void main(String[] args) {
		TowersOfHanoi towers = new TowersOfHanoi();
		towers.doMoves(4, 'A', 'C', 'B');
		System.out.println("Number of moves: " + towers.getCount());
	}

}
