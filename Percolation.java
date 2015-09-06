import java.lang.IndexOutOfBoundsException ;
import java.lang.IllegalArgumentException ;
import edu.princeton.cs.algs4.StdIn ;
import edu.princeton.cs.algs4.WeightedQuickUnionUF ;

public class Percolation {
        private static boolean[][]sites;
        private WeightedQuickUnionUF wquf;
        private int gridsize =0 ;
        private int virtualTopNode;
        private int virtualBottomNode;
        
    public Percolation(int N)  {
        if (N <= 0) throw new IllegalArgumentException ("N can't be 0");
        sites = new boolean [N][N];
        gridsize= N;
        virtualTopNode=0;
        virtualBottomNode= N*N+1;
        wquf = new WeightedQuickUnionUF(N*N+2);

    }   
   public void open(int i, int j)   {
       if (i <= 0 || j <=0) throw new IndexOutOfBoundsException ("Out of bounds problem");
       if (i > gridsize || j >gridsize)   throw new IndexOutOfBoundsException ("Out of bounds problem");
       int a = i-1;
       int b = j-1;
       if (sites [a][b] == false){
         sites [a][b] = true;
          int arrayindex = xyto1D(a,b);
         if (a == 0){
             wquf.union(virtualTopNode, arrayindex);}
         if (a == gridsize-1){
             wquf.union(arrayindex, virtualBottomNode);}
         if ((a-1 & b) >= 0 && (a-1 & b) <= gridsize-1 && isOpen(a,b+1)){
           wquf.union(arrayindex,xyto1D (a-1,b));  
         }
            if ((a & b-1) >= 0 && (a & b-1) <= gridsize-1 && isOpen(a+1,b)){
           wquf.union(arrayindex,xyto1D (a,b-1));  
         }
               if ((a+1 & b) >= 0 && (a+1 & b) <= gridsize-1 && isOpen(a+2,b+1)){
           wquf.union(arrayindex,xyto1D (a+1,b));  
         }
                  if ((a & b+1) >= 0 && (a & b+1) <= gridsize-1 && isOpen(a+1,b+2)){
           wquf.union(arrayindex,xyto1D (a,b+1));  
         }
          }  
   }    
   public boolean isOpen(int i, int j)  {
       int a = i-1;
       int b = j-1;
       if (a <0 || a > gridsize-1) {return false;}
       if (b <0 || b > gridsize-1) {return false;}
       return sites[a][b] == true;}

   public boolean isFull(int i, int j)  {
       if (i <= 0  || j <=0) throw new IndexOutOfBoundsException ("Out of bounds problem");
       if (i > gridsize || j >gridsize)   throw new IndexOutOfBoundsException ("Out of bounds problem");
         int a = i -1;
         int b = j-1;
       return wquf.connected( xyto1D(a,b),virtualTopNode);}  

   public boolean percolates()    {
  return wquf.connected(virtualTopNode,virtualBottomNode);
   }         
   private int xyto1D (int x, int y) {
       int arrayindex = y + 1 + gridsize * x;
       return arrayindex;
   } 
   public static void main(String[] args)   {
    System.out.println("Percolation");
    int size =0 ;
    size= StdIn.readInt();
    System.out.println(size);
    Percolation grid = new Percolation(size);
    System.out.println(grid.isOpen(3,4));
   }  
}

