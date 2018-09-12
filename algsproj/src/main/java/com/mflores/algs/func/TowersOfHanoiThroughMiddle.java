package com.mflores.algs.func;

/**
 * @author marcof
 * Solves Hanoi Towers variation: Al disks must be transfered to the destination peg without bypassing the middle peg.
 */
public class TowersOfHanoiThroughMiddle {
	
	private int count = 0;
	
	public void doMoves(int disks, char from, char to, char middle) {
		if(disks == 1) {
			logAndCountMove(disks, from, middle);
			logAndCountMove(disks, middle, to);
		}else {
			
			doMoves(disks-1, from, to, middle);
			
			logAndCountMove(disks, from, middle);
			
			doMoves(disks-1, to, from, middle);
			
			logAndCountMove(disks, middle, to);
			
			doMoves(disks-1, from, to, middle);
			
		}
	}
	
	public void logAndCountMove(int disks, char from, char to) {
		System.out.println("Moving disk "+ disks + " from " +from + " to "+ to);
		count++;
	}
	
	public int getCount() {
		return count;
	}
	
	public static void main(String[] args) {
		TowersOfHanoiThroughMiddle towers = new TowersOfHanoiThroughMiddle();
		towers.doMoves(11, 'A', 'C', 'B');
		System.out.println("Number of moves: " + towers.getCount());
	}

}
