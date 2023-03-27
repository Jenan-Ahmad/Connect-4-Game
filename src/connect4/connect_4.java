/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author Rawan_Zanabeet
 */
public class connect_4 {

    private board b;
    private String color1;
    private String color2;

    private boolean try_play;

    connect_4(String color1, String color2) {
        this.b = new board();
        this.color1 = color1;
        this.color2 = color2;
        try_play = true;

    }

         public void clean() {
    b.clean(b.get_board());
    }
    
    
    public boolean isIs1playing() {
        return try_play;
    }

 public  String color;
     public int round(int col) {
     //   System.out.print("im reach here");
         
          color = try_play ? color1 : color2;

        int row = b.addp(col, color);

        if(row != -1) try_play = !try_play;

        return row;
    }
        
    public boolean check_if_it_connect_4_piece(int column) {
 
        String color;
        if (!try_play) {
            color = color1;
        } else {
            color = color2;
        }

      return  b.check_if_it_connect_4_piece(column, color);

      
    }

    public void start_game() {
        int res = 0;
        boolean flag = true;
        while (flag) {
            b.print();
            String color = null;
            if (try_play) {
                color = this.color1;
                System.out.println("player color  "+color1+ "  trun :");
            } else {
                color = this.color2;
                System.out.println("player color  "+color2+ "  trun :");
            }

            System.out.println("select between 1 and" + b.get_column());

            Scanner input = new Scanner(System.in);
            res = input.nextInt() - 1;

            int bool = b.addp(res, color);

            if (bool!=-1) {

                if (check_if_it_connect_4_piece(res)) {
                    b.print();flag = false;
                    if (try_play) {
                           //  b.print();
                        System.out.println("player color  "+color1+ "  won ._.");
                    } else {
                           //  b.print();
                        System.out.println("player color  "+color2+"   won ._.");
                    }
                 
                }
                try_play = !try_play;
            }

        }
    }
}
