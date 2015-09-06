import java.lang.IndexOutOfBoundsException ;
import java.util.*;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

 public class PercolationStats {
     private double[] meancalc = new double[0] ;
     private double count ;
     private double size ;
     private double iteration ;
   public PercolationStats(int N, int T) {
      if (N<=0) throw new IllegalArgumentException ("N can't be 0");
      if (T<=0) throw new IllegalArgumentException ("T can't be 0");
      iteration = T ;
      Percolation grid ;
       size = 1.0 * N *N ;
      meancalc = new double[T] ;
      while (T>0){
          grid = new Percolation(N);
          count =0;
          while (!grid.percolates()){
          int i=StdRandom.uniform(N) + 1;
          int j=StdRandom.uniform(N) + 1;
          if (!grid.isOpen(i,j)){
              grid.open(i,j);
              count ++;
          }
      } 
          meancalc[T-1]=(count / (size));
          T--;
    }
   }
   public double mean()    {
       return StdStats.mean(meancalc);  
   }
   public double stddev() {
       return StdStats.stddev(meancalc);
   }
   public double confidenceLo() {
       return (mean()- 1.96* stddev() / Math.sqrt(iteration));
   }
   public double confidenceHi() {
       return (mean()+ 1.96* stddev() / Math.sqrt(iteration));
   }
   public static void main(String[] args)
   {
     int arg0=Integer.parseInt(args[0]);
     int arg1=Integer.parseInt (args[1]);
     PercolationStats gridstats=new PercolationStats (arg0, arg1);
     System.out.println("Mean :  " + gridstats.mean());
     System.out.println("Standard Deviation :  " + gridstats.stddev());
     System.out.println("95% confidence interval :  " + gridstats.confidenceLo() + "," +  gridstats.confidenceHi() );
   }

}

