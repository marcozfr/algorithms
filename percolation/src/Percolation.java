import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private static int VTTOP = -1;
    private static int VTBOTTOM = -1;
    private int[] array;
    private int size;
    private int width;
    private int opencount;
    private WeightedQuickUnionUF wquf;
    
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        int npow2 = n * n;
        wquf = new WeightedQuickUnionUF(npow2+2);
        VTTOP = npow2;
        VTBOTTOM = npow2 + 1;
        array = new int[npow2];
        size = array.length;
        width = n;
        opencount = 0;
        for(int i = 0 ; i < array.length ; i++){
            array[i] = 0;
            if(i < n){
                wquf.union(VTTOP,i);
            }else if (i >= (npow2-n)){
                wquf.union(VTBOTTOM,i);
            }
        }
    }
    
    public int getRightNeig(int i){
        if(i == (size-1)){
            return i;
        }
        return i + 1;
    }
    
    public int getLeftNeig(int i){
        if(i == 0){
            return i;
        }
        return i - 1;
    }
    
    public int getTopNeig(int i){
        int res = i+(width);
        if(res >= size){
            return i;
        }
        return res; 
    }
    
    public int getBottomNeig(int i){
        int res = i-(width);
        if(res < 0){
            return i;
        }
        return res; 
    }
    
    public int getLinearPos(int row, int col){
        return ( (row - 1) * width ) + (col) -1;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        //1 2 3
        //4 5 6
        //7 8 9
        if(row == 0 || col == 0){
           throw new IllegalArgumentException();
        }
        int pos = getLinearPos(row, col);
        if(array[pos] == 1){
            return;
        }
        array[pos] = 1;
        opencount++;
        int bottom = getBottomNeig(pos);
        int top = getTopNeig(pos);
        int right = getRightNeig(pos);
        int left = getLeftNeig(pos);
        if(array[bottom] == 1){
            wquf.union(pos, bottom);
        }
        if(array[top] == 1){
            wquf.union(pos, top);
        }
        if(array[right] == 1){
            wquf.union(pos, right);
        }
        if(array[left] == 1){
            wquf.union(pos, left);
        }
        
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if(row == 0 || col == 0){
            throw new IllegalArgumentException();
         }
        int pos = getLinearPos(row, col);
        if(array[pos] == 1){
            return true;
        }
        return false;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        int pos = getLinearPos(row, col);
        boolean isTopConnected = wquf.connected(VTTOP, pos);
        boolean isBottomConnected = wquf.connected(VTBOTTOM, pos);
        return (isTopConnected && isBottomConnected);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return opencount;
    }

    // does the system percolate?
    public boolean percolates() {
        return wquf.connected(VTTOP, VTBOTTOM);
    }

    // test client (optional)
    public static void main(String[] args) {
        
    }

}
