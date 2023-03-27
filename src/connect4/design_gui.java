
package connect4;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.PrintStream;
import java.net.URL;
import javax.swing.*;


@SuppressWarnings("serial")
public class design_gui extends JFrame {
  private javax.swing.JButton jButton1;
public String i="orange";
public String image="images/orange.png";
public String im2="images/yellow.png";

    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel2;
 Choice c1;
  Choice c2;
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
         c1= new Choice();   
        jLabel2 = new javax.swing.JLabel();
          c2= new Choice();   
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        String s1[] = { "orange", "yellow", "red", "pink", "purple" };
        String s2[] = { "yallow", "orange", "red", "green", "purple" };
        // create checkbox
     



      
        for(int i=0;i<s1.length;i++)
        {
                   c1.add(s1[i]); 
        }
        
            for(int i=0;i<s1.length;i++)
        {
                  c2.add(s2[i]); 
        }
            
         System.out.println(c1.getSelectedItem());
         
       
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

      jPanel2.setPreferredSize(new java.awt.Dimension(750, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 601, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, 621));

        jButton1.setBackground(new java.awt.Color(204, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-available-updates-48.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 80, 60));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-color-swatch-100.png"))); // NOI18N
        jLabel4.setMaximumSize(new java.awt.Dimension(958, 680));
        jLabel4.setMinimumSize(new java.awt.Dimension(958, 680));
        jLabel4.setPreferredSize(new java.awt.Dimension(958, 680));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 110, 70));
        getContentPane().add(c1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 100, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-color-swatch-100.png"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(958, 680));
        jLabel2.setMinimumSize(new java.awt.Dimension(958, 680));
        jLabel2.setPreferredSize(new java.awt.Dimension(958, 680));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 110, 70));
        getContentPane().add(c2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 100, -1));

        jButton4.setBackground(new java.awt.Color(204, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-exit-64.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 510, 80, 70));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/High_resolution_wallpaper_background_ID_77700946547.jpg"))); // NOI18N
        jLabel3.setMaximumSize(new java.awt.Dimension(958, 680));
        jLabel3.setMinimumSize(new java.awt.Dimension(958, 680));
        jLabel3.setPreferredSize(new java.awt.Dimension(958, 680));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-120, -130, 1160, 900));

       c1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choice1MouseClicked(evt);
            }
        });
       
       
         c2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choice2MouseClicked(evt);
            }
        });
        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
   
                  System.out.println("Trying to play again...");
          
//a.start_game();  
    int reply = JOptionPane.showConfirmDialog(null, " Try again?", null, JOptionPane.YES_NO_OPTION);
                if(reply==JOptionPane.YES_OPTION) {
Return_to_white_color();
a.clean();}
                else;
    // design_gui ourGUI = new design_gui(a.isIs1playing(),a,6,7);
               
                 
    }           
       private void choice2MouseClicked(java.awt.event.MouseEvent evt) {               
          Return_to_white_color();  
              a.clean();
            i=c2.getSelectedItem();
  im2="images/"+i+".png";
System.out.print(image);
       
       
       }
    
    
    private void choice1MouseClicked(java.awt.event.MouseEvent evt) {                                     
 
            Return_to_white_color();  
              a.clean();
            i=c1.getSelectedItem();
  image="images/"+i+".png";
System.out.print(image);

                    

    }  
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
 
//    System.exit(0);
        dispose();
        gamesettings gs = new gamesettings();
        gs.setVisible(true);
        gs.setLocationRelativeTo(null);
    
    
    }                             

    /**
     * @param args the command line arguments
     */

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
 
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            //    new design__Gui().setVisible(true);
            }
        });
    }
    
    private final connect_4 a;
     int R,c;


     ImageIcon white,orange,yallow;
 

    private final String title = "Connect_4_Game :) ";

    private void worldsBestUpdater(JButton button) {
        int col = Integer.parseInt(button.getName());

        //System.out.print(col);

   

        String imgRedFilename = image;
      URL  imgURL = getClass().getClassLoader().getResource(imgRedFilename);
        if (imgURL != null) orange = new ImageIcon(imgURL);
     


        String imgYellowFilename = im2;
        imgURL = getClass().getClassLoader().getResource(imgYellowFilename);
        if (imgURL != null) yallow = new ImageIcon(imgURL);
     
       
        int addedRow = a.round(col);

        if(addedRow != -1) {
            JButton buttonToUpdate = ((JButton)(jPanel2.getComponent(c * addedRow+ col)));

            if(a.isIs1playing()) buttonToUpdate.setIcon(yallow);
            else buttonToUpdate.setIcon(orange);
 
            if(a.check_if_it_connect_4_piece(col)) {
                JOptionPane.showMessageDialog(null, "You have won!");
          //    int reply = JOptionPane.showConfirmDialog(null, " Try again?", null, JOptionPane.YES_NO_OPTION);
         //     if(reply==1) {
                   // System.out.println("Trying to play again...");
                  //   game.reset(6, 7);
                    Return_to_white_color();
                    a.clean();
            //  } else {
              
             // }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a valid position.");
        }
    }

    public void Return_to_white_color() {
        for(int row = 0; row < R; row++)
            for (int col = 0; col < c; col++)
                ((JButton)(jPanel2.getComponent(c * row + col))).setIcon(white);
    }

    
    public design_gui(boolean player1turn, connect_4 a, int R, int c ) {
        this.a = a;
        this.R = R;
        this.c = c ;

        initComponents();
      //  jPanel2 = getContentPane();
         jPanel2.setLayout(new BorderLayout());
         
                    
        String imgEmptyFilename = "images/empty.png";
        URL imgURL = getClass().getClassLoader().getResource(imgEmptyFilename);
        if (imgURL != null) white = new ImageIcon(imgURL);
     

        jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
         
        for(int row = 0; row < R; row++) {
            for (int col = 0; col < c; col++) {
                JButton button = new JButton(); 
                button.setIcon(white);
                button.setPreferredSize(new Dimension(100, 100));
           
                button.setName(Integer.toString((col)));

                button.addActionListener(actionEvent -> worldsBestUpdater(((JButton) (actionEvent.getSource()))));
                jPanel2.add(button);
            }
        }     
          // JButton button = new JButton(); 
         //  button.setName("rawan");
           //          button.setIcon(yallow);
         //   button.setPreferredSize(new Dimension(50, 50));
           //         jPanel2.add(button);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1052, 810);
        setVisible(true);

          setLocationRelativeTo(null);
    }
    // Variables declaration - do not modify                     

    //private javax.swing.JButton jButton1;
   // private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
  //  private javax.swing.JPanel jPanel2;
    // End of variables declaration                   
}
