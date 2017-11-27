import java.lang.management.GarbageCollectorMXBean;

import javax.crypto.spec.GCMParameterSpec;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private Percolation percolation;
    private int trials;
    private double mean;
    private double stddev;
    private int size;
    private double confidenceLo;
    private double confidenceHi;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trials = trials;
        this.size = n*n;
        int[] results = new int[trials]; 
        for(int i = 0 ; i < trials ; i++){
            Stopwatch stopwatch = new Stopwatch();
            System.out.println("Opening random sites "+ i);
            Percolation p = new Percolation(n);
            for(int j = 0 ; j < size ; j++){
                int x = StdRandom.uniform(1,n+1);
                int y = StdRandom.uniform(1,n+1);
                p.open(x, y);
                if(p.percolates()){
                    System.out.println("Elapsed time until percolation (s): " +stopwatch.elapsedTime());
                    break;
                }
            }
            results[i] = p.numberOfOpenSites();
            p = null;
            System.gc();
        }
        
        this.mean = StdStats.mean(results);
        this.stddev = StdStats.stddev(results);
        this.confidenceLo = this.mean - (((1.96)*stddev)/Math.sqrt(trials));
        this.confidenceHi = this.mean + (((1.96)*stddev)/Math.sqrt(trials));
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean/size;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev/size;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.confidenceLo/size;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.confidenceHi/size;
    }

    // test client (described below)
    public static void main(String[] args) {
        
        Stopwatch stopwatch = new Stopwatch();
        
        PercolationStats stats = new PercolationStats(10000, 10);
        System.out.println("Mean: " + stats.mean());
        System.out.println("Std Dev: " + stats.stddev());
        System.out.println("Lo/Hi: [" + stats.confidenceLo() +";"+stats.confidenceHi()+"]");

        System.out.println("Elapsed time (s): " +stopwatch.elapsedTime());
        
    }

}
