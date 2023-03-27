/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.awt.Color;
import static java.lang.Float.NEGATIVE_INFINITY;
import static java.lang.Float.POSITIVE_INFINITY;
import java.util.ArrayList;

/**
 *
 * @author Jenan
 */
public class game extends javax.swing.JFrame {

    int[][] gboard = {{0, 0, 0, 0, 0, 0, 0}, 
                      {0, 0, 0, 0, 0, 0, 0}, 
                      {0, 0, 0, 0, 0, 0, 0}, 
                      {0, 0, 0, 0, 0, 0, 0},
                      {0, 0, 0, 0, 0, 0, 0},
                      {0, 0, 0, 0, 0, 0, 0}};
    public static int mdepth = 5;
    public static int mode = 1;//1vspc, 2 twoplayers
    public game() {
//        this.gboard = new int[6][7];
        
        initComponents();
        jPanel5.setVisible(false);
        
//        System.out.println(nextmove());
    }

    /**
     *
     * @param board
     * @param depth
     * @param alpha
     * @param beta
     * @param turn: turn = 1->pc, turn = 2->player
     * @return
     */
    public int nextmove(){
        
        int nmove = 0;
        float bestu = NEGATIVE_INFINITY;
        float alpha = NEGATIVE_INFINITY;
        float beta = POSITIVE_INFINITY;
        for(int i=0; i<7; i++){
            int[][] child;
            
            for(int j=0; j<6; j++){
                if(gboard[j][i] == 0){
                    child = makechild(gboard, i, 1);
                    int uchild = alphabeta(child, mdepth-1, alpha, beta, 2);
//                    for(int ye = 0; ye<6; ye++)
//                        System.out.println(child[ye][0]+" "+child[ye][1]+" "+child[ye][2]+" "+child[ye][3]+" "+child[ye][4]+" "+child[ye][5]+" "+child[ye][6]+" ");
//            
//                    System.out.println(uchild);
//                    System.out.println("childnm"+i);
                    if(uchild > bestu){
                        bestu = uchild;
                        nmove = i;
                    }
                    if(uchild > alpha){
                        alpha = uchild;
                    }
                    break;
                }
            }
            
        }
//        System.out.println(alpha +" "+bestu + " "+nmove);
        return nmove;
    }
    public int alphabeta(int[][] board, int depth, float alpha, float beta, int turn){
        if((depth == 0) || (getanyavaialblemoves(board) || (gameover(board)))){
//            for(int ye = 0; ye<6; ye++)
//                System.out.println(board[ye][0]+" "+board[ye][1]+" "+board[ye][2]+" "+board[ye][3]+" "+board[ye][4]+" "+board[ye][5]+" "+board[ye][6]+" ");
//            
//            System.out.println(utility(board, turn));
//            System.out.println("alpha beta finish");
            return utility(board, turn);
        }
        if(turn == 1){//maximize
            int v = (int) NEGATIVE_INFINITY;
            ArrayList<int[][]> children = new ArrayList<int[][]>();
            for(int i = 0; i<7; i++){
                for(int j = 0; j<6; j++){
                    if(board[j][i] == 0){
                        children.add(makechild(board, i, 1));
                        break;
                    }
                }
            }
            for(int i=0; i<children.size(); i++){
                int tempv = alphabeta(children.get(i), depth-1, alpha, beta, 2);
                if(v < tempv){
                    v = tempv; 
                }
                if(v > alpha){
                    alpha = v;
                }
                if(alpha >= beta){
                    break;
                }
            }
            return v;
        }
        if(turn == 2){//minimize
            int v = (int) POSITIVE_INFINITY;
            ArrayList<int[][]> children = new ArrayList<int[][]>();
            for(int i = 0; i<7; i++){
                for(int j = 0; j<6; j++){
                    if(board[j][i] == 0){
                        children.add(makechild(board, i, 2));
                        break;
                    }
                }
            }
            for(int i=0; i<children.size(); i++){
                int tempv = alphabeta(children.get(i), depth-1, alpha, beta, 1);
                if(v > tempv){
                    v = tempv; 
                }
                if(v < beta){
                    beta = v;
                }
                if(alpha >= beta){
                    break;
                }
            }
            return v;
        }
        return 0;
    
    
    }
    public int[][] makechild(int[][] board, int col, int turn){
        
        int [][]copyb = new int[6][7];
        for(int i=0; i<6; i++)
            for(int j=0; j<7; j++)
                copyb[i][j] = board[i][j];
        for(int i=0; i<6; i++){
            if(copyb[i][col] == 0){
                copyb[i][col] = turn;      
                return copyb;
            }
        }
        return null;
    }
    public boolean gameover(int[][] board) {
        int w, x, y, z,a , b, c, d;
        w = cvertical(board, 1);
        x = cvertical(board, 2);
        y = chorizontal(board, 1);
        z = chorizontal(board, 2);
        a = cdiagonal(board, 1);
        b = cdiagonal(board, 2);
        c = cantidiagonal(board, 1);
        d = cantidiagonal(board, 2);
        
        System.out.println("e"+x);
        if(w>=100000 || x>=100000 || y>=100000 || z>=100000 || a>=100000 || b>=100000 || c>=100000 || d>=100000){
//            System.out.println("ew9ifdb3w8f7");
            return true;
        }
        return false;
    }
    public int utility(int[][] board, int turn){
        int w, x, y, z, a, b, c, d;
        w = cvertical(board, 1);
        x = cvertical(board, 2);
        y = chorizontal(board, 1);
        z = chorizontal(board, 2);
        a = cdiagonal(board, 1);
        b = cdiagonal(board, 2);
        c = cantidiagonal(board, 1);
        d = cantidiagonal(board, 2);
        return w+y+a+c-b-d-x-z;
    
    
    }
    public int cvertical(int[][]board, int turn){
        int vttl = 0;
        
        for(int i=0; i<7; i++){
            int cntclm = 0;
            int empty = 0;
            if(board[5][i] != 0)
                continue;
            for(int j=0; j<6; j++){
                if(board[j][i] == turn){
                    cntclm++;
                }
                else if(board[j][i] == 3-turn){
                    cntclm = 0;
                } 
                else
                    empty++;
            }
            if(cntclm + empty <4)
                continue;
            switch (cntclm) {
                case 4 -> vttl += 100000;
                case 3 -> vttl += 1000;
                case 2 -> vttl += 100;
                default -> {
                }
            }
        
        }
        return vttl;
    }
    public int cdiagonal(int[][]board, int turn){
        int dttl = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<4; j++){
                if(board[i][j] != turn){
                    continue;
                }
                if(board[i+1][j+1] != turn){
                    continue;
                }
                if(board[i+2][j+2] != turn){
                    dttl += 100;
                    continue;
                }
                if(board[i+3][j+3] != turn){
                    dttl += 1000;
                }
                else{
                    dttl += 100000;
                }
                
            }
            
        }
        
        return dttl;
    }
    public int cantidiagonal(int[][]board, int turn){
        int dttl = 0;
        for(int i=5; i>2; i--){
            for(int j=0; j<4; j++){
                if(board[i][j] != turn){
                    continue;
                }
                if(board[i-1][j+1] != turn){
                    continue;
                }
                if(board[i-2][j+2] != turn){
                    dttl += 100;
                    continue;
                }
                if(board[i-3][j+3] != turn){
                    dttl += 1000;
                }
                else{
                    dttl += 100000;
                }
                
            }
            
        }
        
        return dttl;
    }
    public int chorizontal(int[][]board, int turn){
        int httl = 0;
        
        for(int i=0; i<6; i++){
            int empty = 0;
            int temp = 0;
            int cons4 = 0;
            for(int j=0; j<7; j++){
                if(board[i][j] == turn){
                    
                    temp++;
                    cons4++;
                    
                }
                if(board[i][j] == 0){
                    empty++;
                    if(cons4<4)
                        cons4=0;
                        
                }
                if(board[i][j] == 3-turn){
                    if(cons4<4)
                        cons4 = 0;
                    if(empty + temp < 4 || temp<2){
                        empty = 0;
                        temp = 0;
                    }
                    else if((empty +temp >= 4) && temp>2){
                        switch (temp) {
                            //case 4 -> httl += 100000;
                            case 3 -> httl += 1000;
                            case 2 -> httl += 100;
                            default -> {
                            }
                        
                        }
                        empty=0;
                        temp=0;
                    }
                }
            }
                if(cons4==4)
                    httl += 100000;
                switch (temp) {
                //case 4 -> httl += 100000;
                case 3 -> httl += 1000;
                case 2 -> httl += 100;
                default -> {
                }
            
            }
        }
//        System.out.println(turn+" "+httl);
        return httl;
    }
    public boolean getanyavaialblemoves(int[][]board) {
        for(int i = 0; i<7; i++){
            if(board[5][i] != 0){
            return true;}
        }
        return false;

    }
/**
     * Creates new form game
     */
        /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bexit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jl16 = new javax.swing.JLabel();
        jl26 = new javax.swing.JLabel();
        jl36 = new javax.swing.JLabel();
        jl46 = new javax.swing.JLabel();
        jl56 = new javax.swing.JLabel();
        jl66 = new javax.swing.JLabel();
        jl76 = new javax.swing.JLabel();
        jl15 = new javax.swing.JLabel();
        jl25 = new javax.swing.JLabel();
        jl35 = new javax.swing.JLabel();
        jl45 = new javax.swing.JLabel();
        jl55 = new javax.swing.JLabel();
        jl65 = new javax.swing.JLabel();
        jl75 = new javax.swing.JLabel();
        jl14 = new javax.swing.JLabel();
        jl24 = new javax.swing.JLabel();
        jl34 = new javax.swing.JLabel();
        jl44 = new javax.swing.JLabel();
        jl54 = new javax.swing.JLabel();
        jl64 = new javax.swing.JLabel();
        jl74 = new javax.swing.JLabel();
        jl13 = new javax.swing.JLabel();
        jl23 = new javax.swing.JLabel();
        jl33 = new javax.swing.JLabel();
        jl43 = new javax.swing.JLabel();
        jl53 = new javax.swing.JLabel();
        jl63 = new javax.swing.JLabel();
        jl73 = new javax.swing.JLabel();
        jl12 = new javax.swing.JLabel();
        jl22 = new javax.swing.JLabel();
        jl32 = new javax.swing.JLabel();
        jl42 = new javax.swing.JLabel();
        jl52 = new javax.swing.JLabel();
        jl62 = new javax.swing.JLabel();
        jl72 = new javax.swing.JLabel();
        jl11 = new javax.swing.JLabel();
        jl21 = new javax.swing.JLabel();
        jl31 = new javax.swing.JLabel();
        jl41 = new javax.swing.JLabel();
        jl51 = new javax.swing.JLabel();
        jl61 = new javax.swing.JLabel();
        jl71 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(57, 64, 73));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel1none(evt);
            }
        });

        bexit.setBackground(new java.awt.Color(57, 64, 73));
        bexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit.PNG"))); // NOI18N
        bexit.setBorder(null);
        bexit.setMaximumSize(new java.awt.Dimension(20, 20));
        bexit.setMinimumSize(new java.awt.Dimension(20, 20));
        bexit.setPreferredSize(new java.awt.Dimension(20, 20));
        bexit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bexitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bexitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bexitMousePressed(evt);
            }
        });
        bexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bexitActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Connect 4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                .addComponent(bexit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bexit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(null);

        jPanel5.setBackground(new java.awt.Color(133, 158, 139));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/loop.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/settings.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(193, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(177, 177, 177)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(40, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(32, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(22, 22, 22)))
        );

        jPanel3.add(jPanel5);
        jPanel5.setBounds(310, 300, 280, 130);

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 218, 71));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(jLabel3);
        jLabel3.setBounds(60, 60, 790, 260);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        jPanel4.setForeground(new java.awt.Color(60, 63, 65));
        jPanel4.setOpaque(false);
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });
        jPanel4.setLayout(new java.awt.GridLayout(6, 7));

        jl16.setFont(new java.awt.Font("Lucida Grande", 1, 0));
        jl16.setForeground(new java.awt.Color(0, 0, 0));
        jl16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl16.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(153, 153, 255)));
        jl16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl16MouseClicked(evt);
            }
        });
        jPanel4.add(jl16);

        jl26.setForeground(new java.awt.Color(153, 0, 255));
        jl26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl26.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl26MouseClicked(evt);
            }
        });
        jPanel4.add(jl26);

        jl36.setFont(new java.awt.Font("Lucida Grande", 1, 0)

        );
        jl36.setForeground(new java.awt.Color(0, 0, 0));
        jl36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl36.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl36MouseClicked(evt);
            }
        });
        jPanel4.add(jl36);

        jl46.setFont(new java.awt.Font("Lucida Grande", 1, 0));
        jl46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl46.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl46MouseClicked(evt);
            }
        });
        jPanel4.add(jl46);

        jl56.setFont(new java.awt.Font("Lucida Grande", 1, 0));
        jl56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl56.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl56MouseClicked(evt);
            }
        });
        jPanel4.add(jl56);

        jl66.setFont(new java.awt.Font("Lucida Grande", 1, 0));
        jl66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl66.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl66MouseClicked(evt);
            }
        });
        jPanel4.add(jl66);

        jl76.setFont(new java.awt.Font("Lucida Grande", 1, 0));
        jl76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl76.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(153, 153, 255)));
        jl76.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl76MouseClicked(evt);
            }
        });
        jPanel4.add(jl76);

        jl15.setFont(new java.awt.Font("Lucida Grande", 1, 0));
        jl15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl15.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(153, 153, 255)));
        jl15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl15MouseClicked(evt);
            }
        });
        jPanel4.add(jl15);

        jl25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl25.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl25MouseClicked(evt);
            }
        });
        jPanel4.add(jl25);

        jl35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl35.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl35MouseClicked(evt);
            }
        });
        jPanel4.add(jl35);

        jl45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl45.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl45MouseClicked(evt);
            }
        });
        jPanel4.add(jl45);

        jl55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl55.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl55MouseClicked(evt);
            }
        });
        jPanel4.add(jl55);

        jl65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl65.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl65.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl65MouseClicked(evt);
            }
        });
        jPanel4.add(jl65);

        jl75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl75.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(153, 153, 255)));
        jl75.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl75MouseClicked(evt);
            }
        });
        jPanel4.add(jl75);

        jl14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl14.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(153, 153, 255)));
        jl14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl14MouseClicked(evt);
            }
        });
        jPanel4.add(jl14);

        jl24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl24.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl24MouseClicked(evt);
            }
        });
        jPanel4.add(jl24);

        jl34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl34.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl34MouseClicked(evt);
            }
        });
        jPanel4.add(jl34);

        jl44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl44.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl44MouseClicked(evt);
            }
        });
        jPanel4.add(jl44);

        jl54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl54.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl54MouseClicked(evt);
            }
        });
        jPanel4.add(jl54);

        jl64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl64.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl64MouseClicked(evt);
            }
        });
        jPanel4.add(jl64);

        jl74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl74.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(153, 153, 255)));
        jl74.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl74MouseClicked(evt);
            }
        });
        jPanel4.add(jl74);

        jl13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(153, 153, 255)));
        jl13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl13MouseClicked(evt);
            }
        });
        jPanel4.add(jl13);

        jl23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl23.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl23MouseClicked(evt);
            }
        });
        jPanel4.add(jl23);

        jl33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl33.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl33MouseClicked(evt);
            }
        });
        jPanel4.add(jl33);

        jl43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl43.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl43MouseClicked(evt);
            }
        });
        jPanel4.add(jl43);

        jl53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl53.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl53MouseClicked(evt);
            }
        });
        jPanel4.add(jl53);

        jl63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl63.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl63MouseClicked(evt);
            }
        });
        jPanel4.add(jl63);

        jl73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl73.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(153, 153, 255)));
        jl73.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl73MouseClicked(evt);
            }
        });
        jPanel4.add(jl73);

        jl12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(153, 153, 255)));
        jl12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl12MouseClicked(evt);
            }
        });
        jPanel4.add(jl12);

        jl22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl22.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl22MouseClicked(evt);
            }
        });
        jPanel4.add(jl22);

        jl32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl32.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl32MouseClicked(evt);
            }
        });
        jPanel4.add(jl32);

        jl42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl42.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl42MouseClicked(evt);
            }
        });
        jPanel4.add(jl42);

        jl52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl52.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl52MouseClicked(evt);
            }
        });
        jPanel4.add(jl52);

        jl62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl62.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl62MouseClicked(evt);
            }
        });
        jPanel4.add(jl62);

        jl72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl72.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(153, 153, 255)));
        jl72.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl72MouseClicked(evt);
            }
        });
        jPanel4.add(jl72);

        jl11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(153, 153, 255)));
        jl11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl11MouseClicked(evt);
            }
        });
        jPanel4.add(jl11);

        jl21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl21.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl21MouseClicked(evt);
            }
        });
        jPanel4.add(jl21);

        jl31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl31.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl31MouseClicked(evt);
            }
        });
        jPanel4.add(jl31);

        jl41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl41.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl41MouseClicked(evt);
            }
        });
        jPanel4.add(jl41);

        jl51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl51.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl51MouseClicked(evt);
            }
        });
        jPanel4.add(jl51);

        jl61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl61.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 2, new java.awt.Color(153, 153, 255)));
        jl61.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl61MouseClicked(evt);
            }
        });
        jPanel4.add(jl61);

        jl71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl71.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(153, 153, 255)));
        jl71.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl71MouseClicked(evt);
            }
        });
        jPanel4.add(jl71);

        jPanel3.add(jPanel4);
        jPanel4.setBounds(310, 20, 600, 550);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reload.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel4);
        jLabel4.setBounds(230, 510, 70, 60);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/settings.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel8);
        jLabel8.setBounds(4, 4, 80, 80);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/brdbg.PNG"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(0, 0, 938, 580);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bexitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bexitMouseEntered
        // TODO add your handling code here:
        bexit.setBackground(Color.gray);
    }//GEN-LAST:event_bexitMouseEntered

    private void bexitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bexitMouseExited
        // TODO add your handling code here:
        bexit.setBackground(new Color(57,64,73));
    }//GEN-LAST:event_bexitMouseExited

    private void bexitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bexitMousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_bexitMousePressed

    private void bexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bexitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bexitActionPerformed

    private void jPanel1none(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1none
        // TODO add your handling code here:
        bexit.setBackground(new Color(57,64,73));
    }//GEN-LAST:event_jPanel1none

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jPanel4MouseClicked

    public void clm1(int turn){
        System.out.println("in");
        String imgp = "";
        if(turn == 2)
            imgp = "/Images/astic.PNG";
        else
            imgp = "/Images/alienic.PNG";
        if(gboard[5][0] == 0){
            for(int i=0; i<6; i++){
                if(gboard[i][0] == 0){
                    gboard[i][0] = turn;
                    switch (i) {
                        case 0 -> jl11.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 1 -> jl12.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 2 -> jl13.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 3 -> jl14.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 4 -> jl15.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 5 -> jl16.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        
                        default -> {
                        }
                    }
//                    if(turn == 1)
//                        return;
                    if(gameover(gboard)){
                        jLabel3.setText("GAME OVER\n YOU WON!");
                        jPanel5.setVisible(true);
                        return;
                    }
                    if(turn == 1)
                        return;
                    else{
                        jLabel3.setText("");
                        int nm = nextmove();
                        switch (nm) {
                        case 0 -> clm1(1);
                        case 1 -> clm2(1);
                        case 2 -> clm3(1);
                        case 3 -> clm4(1);
                        case 4 -> clm5(1);
                        case 5 -> clm6(1);
                        case 6 -> clm7(1);
                        
                        default -> {
                        }
                    }
                      if(gameover(gboard)){
                        jLabel3.setText("GAME OVER\n PC WON!");
                        jPanel5.setVisible(true);
                        return;
                    }  
                    }
                    return;
                }
                    
            }
        }
        else
            jLabel3.setText("CHOOSE ANOTHER COLUMN!");
        
    }
    public void clm2(int turn){
        System.out.println("in");
        String imgp = "";
        if(turn == 2)
            imgp = "/Images/astic.PNG";
        else
            imgp = "/Images/alienic.PNG";
        if(gboard[5][1] == 0){
            for(int i=0; i<6; i++){
                if(gboard[i][1] == 0){
                    gboard[i][1] = turn;
                    switch (i) {
                        case 0 -> jl21.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 1 -> jl22.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 2 -> jl23.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 3 -> jl24.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 4 -> jl25.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 5 -> jl26.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        
                        default -> {
                        }
                    }
                    if(turn == 1)
                        return;
                    if(gameover(gboard)){
                        jLabel3.setText("GAME OVER\n YOU WON");
                        jPanel5.setVisible(true);
                        return;
                    }
                    else{
                        jLabel3.setText("");
                        int nm = nextmove();
                        switch (nm) {
                        case 0 -> clm1(1);
                        case 1 -> clm2(1);
                        case 2 -> clm3(1);
                        case 3 -> clm4(1);
                        case 4 -> clm5(1);
                        case 5 -> clm6(1);
                        case 6 -> clm7(1);
                        
                        default -> {
                        }
                    }
                      if(gameover(gboard)){
                        jLabel3.setText("GAME OVER\n PC WON");
                        jPanel5.setVisible(true);
                        return;
                    }  
                    }
                    return;
                }
                    
            }
        }
        else
            jLabel3.setText("CHOOSE ANOTHER COLUMN!");
        
    }
    public void clm3(int turn){
        System.out.println("in");
        String imgp = "";
        if(turn == 2)
            imgp = "/Images/astic.PNG";
        else
            imgp = "/Images/alienic.PNG";
        if(gboard[5][2] == 0){
            for(int i=0; i<6; i++){
                if(gboard[i][2] == 0){
                    gboard[i][2] = turn;
                    switch (i) {
                        case 0 -> jl31.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 1 -> jl32.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 2 -> jl33.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 3 -> jl34.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 4 -> jl35.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 5 -> jl36.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        
                        default -> {
                        }
                    }
                    if(turn == 1)
                        return;
                    if(gameover(gboard)){
                        jLabel3.setText("GAME OVER\n YOU WON");
                        jPanel5.setVisible(true);
                        return;
                    }
                    else{
                        jLabel3.setText("");
                        int nm = nextmove();
                        switch (nm) {
                        case 0 -> clm1(1);
                        case 1 -> clm2(1);
                        case 2 -> clm3(1);
                        case 3 -> clm4(1);
                        case 4 -> clm5(1);
                        case 5 -> clm6(1);
                        case 6 -> clm7(1);
                        
                        default -> {
                        }
                    }
                      if(gameover(gboard)){
                        jLabel3.setText("GAME OVER\n PC WON");
                        jPanel5.setVisible(true);
                        return;
                    }  
                    }
                    return;
                }
                    
            }
        }
        else
            jLabel3.setText("CHOOSE ANOTHER COLUMN!");
        
    }
    public void clm4(int turn){
        System.out.println("in");
        String imgp = "";
        if(turn == 2)
            imgp = "/Images/astic.PNG";
        else
            imgp = "/Images/alienic.PNG";
        if(gboard[5][3] == 0){
            for(int i=0; i<6; i++){
                if(gboard[i][3] == 0){
                    gboard[i][3] = turn;
                    switch (i) {
                        case 0 -> jl41.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 1 -> jl42.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 2 -> jl43.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 3 -> jl44.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 4 -> jl45.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 5 -> jl46.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        
                        default -> {
                        }
                    }
                    if(turn == 1)
                        return;
                    if(gameover(gboard)){
                        jLabel3.setText("GAME OVER\n YOU WON");
                        jPanel5.setVisible(true);
                        return;
                    }
                    else{
                        jLabel3.setText("");
                        int nm = nextmove();
                        switch (nm) {
                        case 0 -> clm1(1);
                        case 1 -> clm2(1);
                        case 2 -> clm3(1);
                        case 3 -> clm4(1);
                        case 4 -> clm5(1);
                        case 5 -> clm6(1);
                        case 6 -> clm7(1);
                        
                        default -> {
                        }
                    }
                      if(gameover(gboard)){
                        jLabel3.setText("GAME OVER\n PC WON");
                        jPanel5.setVisible(true);
                        return;
                    }  
                    }
                    return;
                }
                    
            }
        }
        else
            jLabel3.setText("CHOOSE ANOTHER COLUMN!");
        
    }
    public void clm5(int turn){
        System.out.println("in");
        String imgp = "";
        if(turn == 2)
            imgp = "/Images/astic.PNG";
        else
            imgp = "/Images/alienic.PNG";
        if(gboard[5][4] == 0){
            for(int i=0; i<6; i++){
                if(gboard[i][4] == 0){
                    gboard[i][4] = turn;
                    switch (i) {
                        case 0 -> jl51.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 1 -> jl52.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 2 -> jl53.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 3 -> jl54.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 4 -> jl55.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 5 -> jl56.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        
                        default -> {
                        }
                    }
                    if(turn == 1)
                        return;
                    if(gameover(gboard)){
                        jLabel3.setText("GAME OVER\n YOU WON");
                        jPanel5.setVisible(true);
                        return;
                    }
                    else{
                        jLabel3.setText("");
                        int nm = nextmove();
                        switch (nm) {
                        case 0 -> clm1(1);
                        case 1 -> clm2(1);
                        case 2 -> clm3(1);
                        case 3 -> clm4(1);
                        case 4 -> clm5(1);
                        case 5 -> clm6(1);
                        case 6 -> clm7(1);
                        
                        default -> {
                        }
                    }
                      if(gameover(gboard)){
                        jLabel3.setText("GAME OVER\n PC WON");
                        jPanel5.setVisible(true);
                        return;
                    }  
                    }
                    return;
                }
                    
            }
        }
        else
            jLabel3.setText("CHOOSE ANOTHER COLUMN!");
        
    }
    public void clm6(int turn){
        System.out.println("in");
        String imgp = "";
        if(turn == 2)
            imgp = "/Images/astic.PNG";
        else
            imgp = "/Images/alienic.PNG";
        if(gboard[5][5] == 0){
            for(int i=0; i<6; i++){
                if(gboard[i][5] == 0){
                    gboard[i][5] = turn;
                    switch (i) {
                        case 0 -> jl61.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 1 -> jl62.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 2 -> jl63.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 3 -> jl64.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 4 -> jl65.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 5 -> jl66.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        
                        default -> {
                        }
                    }
                    if(turn == 1)
                        return;
                    if(gameover(gboard)){
                        jLabel3.setText("GAME OVER\n YOU WON");
                        jPanel5.setVisible(true);
                        return;
                    }
                    else{
                        jLabel3.setText("");
                        int nm = nextmove();
                        switch (nm) {
                        case 0 -> clm1(1);
                        case 1 -> clm2(1);
                        case 2 -> clm3(1);
                        case 3 -> clm4(1);
                        case 4 -> clm5(1);
                        case 5 -> clm6(1);
                        case 6 -> clm7(1);
                        
                        default -> {
                        }
                    }
                      if(gameover(gboard)){
                        jLabel3.setText("GAME OVER\n PC WON");
                        jPanel5.setVisible(true);
                        return;
                    }  
                    }
                    return;
                }
                    
            }
        }
        else
            jLabel3.setText("CHOOSE ANOTHER COLUMN!");
        
    }
    public void clm7(int turn){
        System.out.println("in");
        String imgp = "";
        if(turn == 2)
            imgp = "/Images/astic.PNG";
        else
            imgp = "/Images/alienic.PNG";
        if(gboard[5][6] == 0){
            for(int i=0; i<6; i++){
                if(gboard[i][6] == 0){
                    gboard[i][6] = turn;
                    switch (i) {
                        case 0 -> jl71.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 1 -> jl72.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 2 -> jl73.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 3 -> jl74.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 4 -> jl75.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        case 5 -> jl76.setIcon(new javax.swing.ImageIcon(getClass().getResource(imgp)));
                        
                        default -> {
                        }
                    }
                    if(turn == 1)
                        return;
                    if(gameover(gboard)){
                        jLabel3.setText("GAME OVER\n YOU WON");
                        jPanel5.setVisible(true);
                        return;
                    }
                    else{
                        jLabel3.setText("");
                        int nm = nextmove();
                        switch (nm) {
                        case 0 -> clm1(1);
                        case 1 -> clm2(1);
                        case 2 -> clm3(1);
                        case 3 -> clm4(1);
                        case 4 -> clm5(1);
                        case 5 -> clm6(1);
                        case 6 -> clm7(1);
                        
                        default -> {
                        }
                    }
                      if(gameover(gboard)){
                        jLabel3.setText("GAME OVER\n PC WON");
                        jPanel5.setVisible(true);
                        return;
                    }  
                    }
                    return;
                }
                    
            }
        }
        else
            jLabel3.setText("CHOOSE ANOTHER COLUMN!");
        
    }
    private void jl16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl16MouseClicked
        // TODO add your handling code here:
        clm1(2);
    }//GEN-LAST:event_jl16MouseClicked

    private void jl15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl15MouseClicked
        // TODO add your handling code here:
        clm1(2);
    }//GEN-LAST:event_jl15MouseClicked

    private void jl14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl14MouseClicked
        // TODO add your handling code here:
        clm1(2);
    }//GEN-LAST:event_jl14MouseClicked

    private void jl13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl13MouseClicked
        // TODO add your handling code here:
        clm1(2);
    }//GEN-LAST:event_jl13MouseClicked

    private void jl12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl12MouseClicked
        // TODO add your handling code here:
        clm1(2);
    }//GEN-LAST:event_jl12MouseClicked

    private void jl11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl11MouseClicked
        // TODO add your handling code here:
        clm1(2);
    }//GEN-LAST:event_jl11MouseClicked

    private void jl26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl26MouseClicked
        // TODO add your handling code here:
        clm2(2);
    }//GEN-LAST:event_jl26MouseClicked

    private void jl25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl25MouseClicked
        // TODO add your handling code here:
        clm2(2);
    }//GEN-LAST:event_jl25MouseClicked

    private void jl24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl24MouseClicked
        // TODO add your handling code here:
        clm2(2);
    }//GEN-LAST:event_jl24MouseClicked

    private void jl23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl23MouseClicked
        // TODO add your handling code here:
        clm2(2);
    }//GEN-LAST:event_jl23MouseClicked

    private void jl22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl22MouseClicked
        // TODO add your handling code here:
        clm2(2);
    }//GEN-LAST:event_jl22MouseClicked

    private void jl21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl21MouseClicked
        // TODO add your handling code here:
        clm2(2);
    }//GEN-LAST:event_jl21MouseClicked

    private void jl36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl36MouseClicked
        // TODO add your handling code here:
        clm3(2);
    }//GEN-LAST:event_jl36MouseClicked

    private void jl35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl35MouseClicked
        // TODO add your handling code here:
        clm3(2);
    }//GEN-LAST:event_jl35MouseClicked

    private void jl34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl34MouseClicked
        // TODO add your handling code here:
        clm3(2);
    }//GEN-LAST:event_jl34MouseClicked

    private void jl33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl33MouseClicked
        // TODO add your handling code here:
        clm3(2);
    }//GEN-LAST:event_jl33MouseClicked

    private void jl32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl32MouseClicked
        // TODO add your handling code here:
        clm3(2);
    }//GEN-LAST:event_jl32MouseClicked

    private void jl31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl31MouseClicked
        // TODO add your handling code here:
        clm3(2);
    }//GEN-LAST:event_jl31MouseClicked

    private void jl46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl46MouseClicked
        // TODO add your handling code here:
        clm4(2);
    }//GEN-LAST:event_jl46MouseClicked

    private void jl45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl45MouseClicked
        // TODO add your handling code here:
        clm4(2);
    }//GEN-LAST:event_jl45MouseClicked

    private void jl44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl44MouseClicked
        // TODO add your handling code here:
        clm4(2);
    }//GEN-LAST:event_jl44MouseClicked

    private void jl43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl43MouseClicked
        // TODO add your handling code here:
        clm4(2);
    }//GEN-LAST:event_jl43MouseClicked

    private void jl42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl42MouseClicked
        // TODO add your handling code here:
        clm4(2);
    }//GEN-LAST:event_jl42MouseClicked

    private void jl41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl41MouseClicked
        // TODO add your handling code here:
        clm4(2);
    }//GEN-LAST:event_jl41MouseClicked

    private void jl56MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl56MouseClicked
        // TODO add your handling code here:
        clm5(2);
    }//GEN-LAST:event_jl56MouseClicked

    private void jl55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl55MouseClicked
        // TODO add your handling code here:
        clm5(2);
    }//GEN-LAST:event_jl55MouseClicked

    private void jl54MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl54MouseClicked
        // TODO add your handling code here:
        clm5(2);
    }//GEN-LAST:event_jl54MouseClicked

    private void jl53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl53MouseClicked
        // TODO add your handling code here:
        clm5(2);
    }//GEN-LAST:event_jl53MouseClicked

    private void jl52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl52MouseClicked
        // TODO add your handling code here:
        clm5(2);
    }//GEN-LAST:event_jl52MouseClicked

    private void jl51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl51MouseClicked
        // TODO add your handling code here:
        clm5(2);
    }//GEN-LAST:event_jl51MouseClicked

    private void jl66MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl66MouseClicked
        // TODO add your handling code here:
        clm6(2);
    }//GEN-LAST:event_jl66MouseClicked

    private void jl65MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl65MouseClicked
        // TODO add your handling code here:
        clm6(2);
    }//GEN-LAST:event_jl65MouseClicked

    private void jl64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl64MouseClicked
        // TODO add your handling code here:
        clm6(2);
    }//GEN-LAST:event_jl64MouseClicked

    private void jl63MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl63MouseClicked
        // TODO add your handling code here:
        clm6(2);
    }//GEN-LAST:event_jl63MouseClicked

    private void jl62MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl62MouseClicked
        // TODO add your handling code here:
        clm6(2);
    }//GEN-LAST:event_jl62MouseClicked

    private void jl61MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl61MouseClicked
        // TODO add your handling code here:
        clm6(2);
    }//GEN-LAST:event_jl61MouseClicked

    private void jl76MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl76MouseClicked
        // TODO add your handling code here:
        clm7(2);
    }//GEN-LAST:event_jl76MouseClicked

    private void jl75MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl75MouseClicked
        // TODO add your handling code here:
        clm7(2);
    }//GEN-LAST:event_jl75MouseClicked

    private void jl74MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl74MouseClicked
        // TODO add your handling code here:
        clm7(2);
    }//GEN-LAST:event_jl74MouseClicked

    private void jl73MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl73MouseClicked
        // TODO add your handling code here:
        clm7(2);
    }//GEN-LAST:event_jl73MouseClicked

    private void jl72MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl72MouseClicked
        // TODO add your handling code here:
        clm7(2);
    }//GEN-LAST:event_jl72MouseClicked

    private void jl71MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl71MouseClicked
        // TODO add your handling code here:
        clm7(2);
    }//GEN-LAST:event_jl71MouseClicked

    public void resetall(){
    jl11.setIcon(new javax.swing.ImageIcon());
        jl12.setIcon(new javax.swing.ImageIcon());
        jl13.setIcon(new javax.swing.ImageIcon());
        jl14.setIcon(new javax.swing.ImageIcon());
        jl15.setIcon(new javax.swing.ImageIcon());
        jl16.setIcon(new javax.swing.ImageIcon());
        
        jl21.setIcon(new javax.swing.ImageIcon());
        jl22.setIcon(new javax.swing.ImageIcon());
        jl23.setIcon(new javax.swing.ImageIcon());
        jl24.setIcon(new javax.swing.ImageIcon());
        jl25.setIcon(new javax.swing.ImageIcon());
        jl26.setIcon(new javax.swing.ImageIcon());
        
        jl31.setIcon(new javax.swing.ImageIcon());
        jl32.setIcon(new javax.swing.ImageIcon());
        jl33.setIcon(new javax.swing.ImageIcon());
        jl34.setIcon(new javax.swing.ImageIcon());
        jl35.setIcon(new javax.swing.ImageIcon());
        jl36.setIcon(new javax.swing.ImageIcon());
       
        jl41.setIcon(new javax.swing.ImageIcon());
        jl42.setIcon(new javax.swing.ImageIcon());
        jl43.setIcon(new javax.swing.ImageIcon());
        jl44.setIcon(new javax.swing.ImageIcon());
        jl45.setIcon(new javax.swing.ImageIcon());
        jl46.setIcon(new javax.swing.ImageIcon());
        
        jl51.setIcon(new javax.swing.ImageIcon());
        jl52.setIcon(new javax.swing.ImageIcon());
        jl53.setIcon(new javax.swing.ImageIcon());
        jl54.setIcon(new javax.swing.ImageIcon());
        jl55.setIcon(new javax.swing.ImageIcon());
        jl56.setIcon(new javax.swing.ImageIcon());
        
        jl61.setIcon(new javax.swing.ImageIcon());
        jl62.setIcon(new javax.swing.ImageIcon());
        jl63.setIcon(new javax.swing.ImageIcon());
        jl64.setIcon(new javax.swing.ImageIcon());
        jl65.setIcon(new javax.swing.ImageIcon());
        jl66.setIcon(new javax.swing.ImageIcon());
        
        jl71.setIcon(new javax.swing.ImageIcon());
        jl72.setIcon(new javax.swing.ImageIcon());
        jl73.setIcon(new javax.swing.ImageIcon());
        jl74.setIcon(new javax.swing.ImageIcon());
        jl75.setIcon(new javax.swing.ImageIcon());
        jl76.setIcon(new javax.swing.ImageIcon());
        
        for(int i=0; i<6; i++)
            for(int j=0; j<7; j++)
                gboard[i][j] = 0;
        jLabel3.setText("");
        jPanel5.setVisible(false);
    
    }
    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        
       resetall();
        
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        gamesettings gs = new gamesettings();
        gs.setVisible(true);
        gs.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        resetall();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        gamesettings gs = new gamesettings();
        gs.setVisible(true);
        gs.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new game().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bexit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel jl11;
    private javax.swing.JLabel jl12;
    private javax.swing.JLabel jl13;
    private javax.swing.JLabel jl14;
    private javax.swing.JLabel jl15;
    private javax.swing.JLabel jl16;
    private javax.swing.JLabel jl21;
    private javax.swing.JLabel jl22;
    private javax.swing.JLabel jl23;
    private javax.swing.JLabel jl24;
    private javax.swing.JLabel jl25;
    private javax.swing.JLabel jl26;
    private javax.swing.JLabel jl31;
    private javax.swing.JLabel jl32;
    private javax.swing.JLabel jl33;
    private javax.swing.JLabel jl34;
    private javax.swing.JLabel jl35;
    private javax.swing.JLabel jl36;
    private javax.swing.JLabel jl41;
    private javax.swing.JLabel jl42;
    private javax.swing.JLabel jl43;
    private javax.swing.JLabel jl44;
    private javax.swing.JLabel jl45;
    private javax.swing.JLabel jl46;
    private javax.swing.JLabel jl51;
    private javax.swing.JLabel jl52;
    private javax.swing.JLabel jl53;
    private javax.swing.JLabel jl54;
    private javax.swing.JLabel jl55;
    private javax.swing.JLabel jl56;
    private javax.swing.JLabel jl61;
    private javax.swing.JLabel jl62;
    private javax.swing.JLabel jl63;
    private javax.swing.JLabel jl64;
    private javax.swing.JLabel jl65;
    private javax.swing.JLabel jl66;
    private javax.swing.JLabel jl71;
    private javax.swing.JLabel jl72;
    private javax.swing.JLabel jl73;
    private javax.swing.JLabel jl74;
    private javax.swing.JLabel jl75;
    private javax.swing.JLabel jl76;
    // End of variables declaration//GEN-END:variables

    

    
}
