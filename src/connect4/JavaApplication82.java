package connect4;

import connect4.connect_4;

/**
 *
 * @author Rawan_Zanabeet
 */
public class JavaApplication82 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        connect_4 a = new connect_4("R", "Y");
//a.start_game();
       design_gui ourGUI = new design_gui(a.isIs1playing(),a,6,7);
  
    }

}
