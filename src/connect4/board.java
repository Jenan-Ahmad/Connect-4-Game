package connect4;

import java.util.ArrayList;
import java.util.List;

public class board {

    private int row = 6;
    private int column = 7;

    piece[][] board = new piece[row][column];
    private List<Integer> available_node;

    public int get_column() {
        return column;
    }

     public void clean(   piece[][] v  ) {
       for (int i = 0; i < row; i++) 
       {
           for(int j=0;j<column;j++)
           {
               v[i][j]=null;
               }}
    }
      public     piece[][] get_board() {
        return board;
    }
    
    public int get_row() {
        return row;
    }

    public void set_column(int a) {
        this.row = a;
    }

    public void set_row(int b) {
        this.column = b;
    }
 public void evaluation_f() {
available_node = new ArrayList<Integer>(column);
  for (int i = 0; i < column; i++) {
      for(int j=0;j<row;j++){
            if (board[j][i] != null) {
                available_node.add(j-1);
                break;
            }
        
                if (board[j][i] == null && j==row-1) {
                available_node.add(row-1);
                break;
            }
        
          
           
      }
   
  
  }

 }
 public int count=0;
 public int calh(board b,int col,String color_w){
 for (int i = 0; i < row; i++) {
               if (board[i][col] != null) {
                    
               //down case
                    for (int j = i + 1; j < row; j++) {
                    if (board[j][col].getColor().equals(color_w)) {
                        count++;
                    }}
               
               
                   //horizantal case
                for (int j = col - 3; j <=col+3; j++) {

                    if (j < 0) continue;
                  
                    if (j >= column) 
                        break;
                if (board[i][j] != null) {
                        if (board[i][j].getColor().equals(color_w)){
               count++;
                        }}}
                
                
                
                         //diagonalleft case
                    int r,v;
            for(  r=i-3,v=col-3;v<=col+3 && r<=i+3;r++,v++){
                    if (v < 0 ||  r<0)continue;
                  
                    if (r >= row || v>=column)break;
                 
                if (board[r][v] != null) {
                        if (board[r][v].getColor().equals(color_w)){
                       count++;}}}
            
                
                
                         //diagonal right case 
       
            for( r=i-3, v=col+3;v>=col-3 && r<=i+3;r++,v--){
                    if (v >= column ||  r<0)continue;
                  
                    if (r >= row || v<0)break;
                 
                if (board[r][v] != null) {
                        if (board[r][v].getColor().equals(color_w)){
                    count++;}}}
               
               
               
               }}
                    
                    
     return count;
 }
 
                    
 
 
 public int v;
 
 public int alpha_beta_algo(board b,int depth,int alpha,int beta,int maxminplayer,int col,String color_w){
     if(depth==0)
     return calh(b,col,color_w);
     if(maxminplayer==1){
      for (int i = 0; i < available_node.size(); i++){
         v=-1000000;
         b.addp(available_node.get(i),"R");
         v=max(v,alpha_beta_algo(b,depth-1,alpha,beta,maxminplayer,available_node.get(i),"R"),col,b);
         alpha=max(v,alpha,col,b);
         if(beta<=alpha)
             break;
     }}
     else{
         for (int i = 0; i < available_node.size(); i++){
         v=1000000;
         b.addp(available_node.get(i),"Y");
         v=min(v,alpha_beta_algo(b,depth-1,alpha,beta,maxminplayer,available_node.get(i),"y"),col,b);
         alpha=min(v,alpha,col,b);
         if(beta<=alpha)
             break;  
     }}
     
     
     return v;
     
 }
 public int max(int a,int b,int col,board aa){
    for (int i = 0; i < row; i++) {
               if (board[i][col] != null) {
                   aa.addp(col, "R");
               }}
    aa.print();
     if(a>=b)
         return a;
     else return b;
 }
   public int min(int a,int b,int col,board aa){
    
           for (int i = 0; i < row; i++) {
               if (board[i][col] != null) {
                   aa.addp(col, "y");
               }}   aa.print();
       if(a<=b)
         return a;
     else return b;
 }
 
  public void score(board temp,int R, String color) {
    int count=0;
     temp.addp(R, color);

        
 }
 
    public boolean check_if_it_connect_4_piece(int c, String color_win) {
//evaluation_f();
  //   System.out.print("leleleeeesh");
      for (int  i = 5; i >= 0; i--) {
          for(int y=0;y<column;y++){
              if(board[i][y]!=null)   
              System.out.println(board[i][y].getColor());
          }
     }
     
     
     System.out.println(color_win+"color_win");
     
     
 // for (int i = 0; i < available_node.size(); i++) 
 // System.out.println(available_node.get(i));
        boolean f = false;
        int i=0,num;
        for ( i = 0; i < row; i++) {
            if (board[i][c] != null) {
                 num = 3;

                //down_case
                for (int j = i + 1; j < row; j++) {
                    if (board[j][c].getColor().equals(color_win)) {
                        num--;

                         if (num == 0) {
                            f = true;
                            System.out.println("done");
                
                        }
                    }
                        else num=4;
                }
                }
                   num = 4;
               
                //horizantal case
                int b = c + 3;
                for (int j = c - 3; j <=b; j++) {

                    if (j < 0) continue;
                  
                    if (j >= column) 
                        break;
                if (board[i][j] != null) {
                        if (board[i][j].getColor().equals(color_win)){
                           num--;

                        if (num == 0) {
                            f = true;
                            break;
                        }
                    }

                }
                }
                   num = 4;
                
                
                
                
               //diagonal left case 
            int r,v;
            for(  r=i-3,v=c-3;v<=c+3 && r<=i+3;r++,v++){
                    if (v < 0 ||  r<0)continue;
                  
                    if (r >= row || v>=column)break;
                 
                if (board[r][v] != null) {
                        if (board[r][v].getColor().equals(color_win)){
                       num--;

                        if (num == 0) {
                            f = true;
                            break;
                        }
                    }

                }
                }
                   num = 4;
                
               
               
                   
                   
                       //diagonal right case 
       
            for( r=i-3, v=c+3;v>=c-3 && r<=i+3;r++,v--){
                    if (v >= column ||  r<0)continue;
                  
                    if (r >= row || v<0)break;
                 
                if (board[r][v] != null) {
                        if (board[r][v].getColor().equals(color_win)){
                    num--;

                        if (num == 0) {
                            f = true;
                
                        }
                    }
                        else num=4;
                }
                }
                   num = 4;
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
                
                
                
                
                
            
        }

        return f;

    }
    
    
    
     public int addp(int c, String color) {
         
             //  System.out.println("lololeesh");
         
         
        // within normal range
        if(c >= 0 && c < column) {
            // we can add
            if(board[0][c] == null) {
        
                int addedRow = -1;
                for(int r = row - 1; r >= 0; r--)
                    if(board[r][c] == null) {
                        board[r][c] = new piece();
                        board[r][c].setcolor(color);
                
                        addedRow = r;
                        break;
                    }
                return addedRow;
            } else {
                // that row is full
                System.err.println("This column is full, please choose another.");
                return -1;
            }
        } else {
            // outside normal range
            System.err.println("You are trying to add somewhere that is not supported.");
            return -1;
        }
        

            
    }

 

    public void print() {
        
        
        
 
        
        
        for (int i = 0; i < row; i++) {
            System.out.print("|");

            for (int j = 0; j < column; j++) {
                if (board[i][j] == null) {
                    System.out.print("-");
                } else {
                    System.out.print(board[i][j].getColor());

                }
                System.out.print("|");

            }

            System.out.println();
        }
    }

    public board() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                board[i][j] = null;
            }
        }

    }

}
