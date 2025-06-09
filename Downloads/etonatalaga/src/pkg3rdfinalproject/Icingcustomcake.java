/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pkg3rdfinalproject;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;


/**
 *
 * @author A315
 */
public class Icingcustomcake extends javax.swing.JFrame {

    private BeaPOS beaPOSParent;
    private String selectedSize = null;
    private int selectedPrice = 0;
    private int selectedQuantity = 0;
    
    
    //custom icing cake
    
     
     int icingCakeQty = 0;
     int icingCakeBasePrice = 0;
    
    

   
    
    
    public Icingcustomcake(BeaPOS beaPOSParent) {
        initComponents();
        this.beaPOSParent = beaPOSParent;
        setLocationRelativeTo(null);
        setResizable(false);

        
        
        ActionListener sizeBtnListener = e -> {
            JToggleButton src = (JToggleButton) e.getSource();
            // Deselect all
            btn5x3IcingCake.setSelected(false);
            btn6x2IcingCake.setSelected(false);
            btn6x3IcingCake.setSelected(false);
            btn7x3IcingCake.setSelected(false);
            btn8x3IcingCake.setSelected(false);
            btn8x4IcingCake.setSelected(false);
            btn9x4IcingCake.setSelected(false);
            // Select only this
            src.setSelected(true);
            // Set size and price
            if (src == btn5x3IcingCake) {
                selectedSize = "5 x 3\"";
                selectedPrice = 650;
            } else if (src == btn6x2IcingCake) {
                selectedSize = "6 x 2\"";
                selectedPrice = 750;
            } else if (src == btn6x3IcingCake) {
                selectedSize = "6 x 3\"";
                selectedPrice = 850;
            } else if (src == btn7x3IcingCake) {
                selectedSize = "7 x 3\"";
                selectedPrice = 1075;
            } else if (src == btn8x3IcingCake) {
                selectedSize = "8 x 3\"";
                selectedPrice = 1300;
            } else if (src == btn8x4IcingCake) {
                selectedSize = "8 x 4\"";
                selectedPrice = 1400;
            } else if (src == btn9x4IcingCake) {
                selectedSize = "9 x 4\"";
                selectedPrice = 1800;
            }
            updatePriceDisplay();
        };
        
        
        //custom icing cake 
//        btn5x3IcingCake.addActionListener(e->{
//           icingCakeBasePrice = 650;
//           updateCustomIcingCakePrice();
//        });
//        btn6x2IcingCake.addActionListener(e->{
//            icingCakeBasePrice = 750;
//             updateCustomIcingCakePrice();
//        });
//        btn6x3IcingCake.addActionListener(e->{
//            icingCakeBasePrice = 850;
//             updateCustomIcingCakePrice();
//        });
//        btn7x3IcingCake.addActionListener(e->{
//            icingCakeBasePrice = 1075;
//             updateCustomIcingCakePrice();
//        });
//        btn8x3IcingCake.addActionListener(e->{
//            icingCakeBasePrice = 1300;
//           updateCustomIcingCakePrice(); 
//        });
//        btn8x4IcingCake.addActionListener(e->{
//            icingCakeBasePrice = 1400;
//             updateCustomIcingCakePrice();
//        });
//        btn9x4IcingCake.addActionListener(e->{
//            icingCakeBasePrice = 1800;
//             updateCustomIcingCakePrice();
//        });

        btn5x3IcingCake.addActionListener(sizeBtnListener);
        btn6x2IcingCake.addActionListener(sizeBtnListener);
        btn6x3IcingCake.addActionListener(sizeBtnListener);
        btn7x3IcingCake.addActionListener(sizeBtnListener);
        btn8x3IcingCake.addActionListener(sizeBtnListener);
        btn8x4IcingCake.addActionListener(sizeBtnListener);
        btn9x4IcingCake.addActionListener(sizeBtnListener);
         
        
       btnPlusIcingCake.addActionListener(e -> {
            selectedQuantity++;
            lbzeroQty.setText(String.valueOf(selectedQuantity));
            updatePriceDisplay();
        });

        btnMinusIcingCake.addActionListener(e -> {
            if (selectedQuantity > 0) {
                selectedQuantity--;
                lbzeroQty.setText(String.valueOf(selectedQuantity));
                updatePriceDisplay();
            }
        }); 
        
        
        
        confirmButton.addActionListener(e -> {
            if (selectedSize == null || selectedQuantity <= 0) {
                JOptionPane.showMessageDialog(this, "Please select a size and quantity.");
                return;
            }
            if (beaPOSParent != null) {
                beaPOSParent.addCustomCakeToBill(selectedSize, selectedQuantity, selectedPrice);
            }
            this.dispose();
        });

        // Initialize display
        lbzeroQty.setText("0");
        updatePriceDisplay();
    
        
    }
    private void updatePriceDisplay() {
        customCakePrice.setText("Php: " + (selectedPrice * selectedQuantity) + ".00");
    }
    
    
    
    private void updateCustomIcingCakePrice(){
        int total = (icingCakeBasePrice) * icingCakeQty;
        customCakePrice.setText("Php: " + total + ".00");
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        btn5x3IcingCake = new javax.swing.JToggleButton();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        btn6x2IcingCake = new javax.swing.JToggleButton();
        btn6x3IcingCake = new javax.swing.JToggleButton();
        btn7x3IcingCake = new javax.swing.JToggleButton();
        btn8x3IcingCake = new javax.swing.JToggleButton();
        btn8x4IcingCake = new javax.swing.JToggleButton();
        btn9x4IcingCake = new javax.swing.JToggleButton();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        lb650IcingCake = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        lb750IcingCake = new javax.swing.JLabel();
        lb850IcingCake = new javax.swing.JLabel();
        lb1075IcingCake = new javax.swing.JLabel();
        lb1300IcingCake = new javax.swing.JLabel();
        lb1400IcingCake = new javax.swing.JLabel();
        lb1800IcingCake = new javax.swing.JLabel();
        btnMinusIcingCake = new javax.swing.JButton();
        btnPlusIcingCake = new javax.swing.JButton();
        lbzeroQty = new javax.swing.JLabel();
        jPanel58 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        customCakePrice = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel13.setBackground(new java.awt.Color(44, 204, 98));

        jPanel12.setBackground(new java.awt.Color(225, 135, 44));

        jPanel11.setBackground(new java.awt.Color(255, 255, 153));
        jPanel11.setForeground(new java.awt.Color(225, 135, 44));

        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BeA D' Lites.1png.png"))); // NOI18N

        jLabel83.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(225, 135, 44));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("Custom Icing Cake");

        btn5x3IcingCake.setBackground(new java.awt.Color(255, 255, 153));
        btn5x3IcingCake.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn5x3IcingCake.setForeground(new java.awt.Color(225, 135, 44));
        btn5x3IcingCake.setText("5 x 3\"");

        jLabel84.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(225, 135, 44));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("Size");

        jLabel85.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(225, 135, 44));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("Price");

        jLabel86.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(225, 135, 44));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setText("&");

        btn6x2IcingCake.setBackground(new java.awt.Color(255, 255, 153));
        btn6x2IcingCake.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn6x2IcingCake.setForeground(new java.awt.Color(225, 135, 44));
        btn6x2IcingCake.setText("6 x 2\"");

        btn6x3IcingCake.setBackground(new java.awt.Color(255, 255, 153));
        btn6x3IcingCake.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn6x3IcingCake.setForeground(new java.awt.Color(225, 135, 44));
        btn6x3IcingCake.setText("6 x 3\"");

        btn7x3IcingCake.setBackground(new java.awt.Color(255, 255, 153));
        btn7x3IcingCake.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn7x3IcingCake.setForeground(new java.awt.Color(225, 135, 44));
        btn7x3IcingCake.setText("7 x 3\"");

        btn8x3IcingCake.setBackground(new java.awt.Color(255, 255, 153));
        btn8x3IcingCake.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn8x3IcingCake.setForeground(new java.awt.Color(225, 135, 44));
        btn8x3IcingCake.setText("8 x 3\"");

        btn8x4IcingCake.setBackground(new java.awt.Color(255, 255, 153));
        btn8x4IcingCake.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn8x4IcingCake.setForeground(new java.awt.Color(225, 135, 44));
        btn8x4IcingCake.setText("8 x 4\"");

        btn9x4IcingCake.setBackground(new java.awt.Color(255, 255, 153));
        btn9x4IcingCake.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn9x4IcingCake.setForeground(new java.awt.Color(225, 135, 44));
        btn9x4IcingCake.setText("9 x  4\"");

        jLabel87.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(225, 135, 44));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("-");

        jLabel88.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(225, 135, 44));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setText("-");

        jLabel89.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(225, 135, 44));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel89.setText("-");

        jLabel90.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(225, 135, 44));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("-");

        jLabel91.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(225, 135, 44));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel91.setText("-");

        jLabel92.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(225, 135, 44));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel92.setText("-");

        jLabel93.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(225, 135, 44));
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel93.setText("-");

        jLabel94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/philippine-peso.png"))); // NOI18N

        lb650IcingCake.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb650IcingCake.setForeground(new java.awt.Color(225, 135, 44));
        lb650IcingCake.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb650IcingCake.setText("650");

        jLabel96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/philippine-peso.png"))); // NOI18N

        jLabel97.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/philippine-peso.png"))); // NOI18N

        jLabel98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/philippine-peso.png"))); // NOI18N

        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/philippine-peso.png"))); // NOI18N

        jLabel100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/philippine-peso.png"))); // NOI18N

        jLabel101.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/philippine-peso.png"))); // NOI18N

        lb750IcingCake.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb750IcingCake.setForeground(new java.awt.Color(225, 135, 44));
        lb750IcingCake.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb750IcingCake.setText("750");

        lb850IcingCake.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb850IcingCake.setForeground(new java.awt.Color(225, 135, 44));
        lb850IcingCake.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb850IcingCake.setText("850");

        lb1075IcingCake.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb1075IcingCake.setForeground(new java.awt.Color(225, 135, 44));
        lb1075IcingCake.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb1075IcingCake.setText("1,075");

        lb1300IcingCake.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb1300IcingCake.setForeground(new java.awt.Color(225, 135, 44));
        lb1300IcingCake.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb1300IcingCake.setText("1,300");

        lb1400IcingCake.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb1400IcingCake.setForeground(new java.awt.Color(225, 135, 44));
        lb1400IcingCake.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb1400IcingCake.setText("1,400");

        lb1800IcingCake.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb1800IcingCake.setForeground(new java.awt.Color(225, 135, 44));
        lb1800IcingCake.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb1800IcingCake.setText("1,800");

        btnMinusIcingCake.setBackground(new java.awt.Color(255, 204, 102));
        btnMinusIcingCake.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMinusIcingCake.setForeground(new java.awt.Color(225, 135, 44));
        btnMinusIcingCake.setText("-");
        btnMinusIcingCake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusIcingCakeActionPerformed(evt);
            }
        });

        btnPlusIcingCake.setBackground(new java.awt.Color(255, 204, 102));
        btnPlusIcingCake.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPlusIcingCake.setForeground(new java.awt.Color(225, 135, 44));
        btnPlusIcingCake.setText("+");
        btnPlusIcingCake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusIcingCakeActionPerformed(evt);
            }
        });

        lbzeroQty.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbzeroQty.setForeground(new java.awt.Color(225, 135, 44));
        lbzeroQty.setText("0");

        jPanel58.setBackground(new java.awt.Color(255, 255, 153));

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(225, 135, 44));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        customCakePrice.setBackground(new java.awt.Color(255, 204, 102));
        customCakePrice.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        customCakePrice.setForeground(new java.awt.Color(255, 255, 153));
        customCakePrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        customCakePrice.setText("Php: 0.00");
        jPanel2.add(customCakePrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 40));

        confirmButton.setBackground(new java.awt.Color(255, 204, 102));
        confirmButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        confirmButton.setForeground(new java.awt.Color(225, 135, 44));
        confirmButton.setText("Confirm");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn9x4IcingCake, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn8x4IcingCake, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn8x3IcingCake, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn7x3IcingCake, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn6x3IcingCake, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn6x2IcingCake, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn5x3IcingCake, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel92, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel93, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel87, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel89, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel90, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel94)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lb650IcingCake, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel100)
                                        .addComponent(jLabel97)
                                        .addComponent(jLabel98)
                                        .addComponent(jLabel96))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lb1400IcingCake, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lb1075IcingCake, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lb850IcingCake, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lb750IcingCake, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel101)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lb1800IcingCake, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel99)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lb1300IcingCake, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(btnMinusIcingCake, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(lbzeroQty)
                                .addGap(18, 18, 18)
                                .addComponent(btnPlusIcingCake, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(confirmButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(21, 21, 21)))))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btn5x3IcingCake)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn6x2IcingCake)
                                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn6x3IcingCake)
                                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn7x3IcingCake)
                                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn8x3IcingCake)
                                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn8x4IcingCake)
                                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn9x4IcingCake)
                                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(lb650IcingCake, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel96, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb750IcingCake, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel97, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb850IcingCake, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb1075IcingCake, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb1300IcingCake, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel99, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb1400IcingCake, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb1800IcingCake, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMinusIcingCake)
                    .addComponent(btnPlusIcingCake)
                    .addComponent(lbzeroQty))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinusIcingCakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusIcingCakeActionPerformed
//        if(icingCakeQty>0){
//        icingCakeQty--;
//        lbzeroQty.setText(String.valueOf(icingCakeQty));
//        updateCustomIcingCakePrice();
//            
//        }
    }//GEN-LAST:event_btnMinusIcingCakeActionPerformed

    private void btnPlusIcingCakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusIcingCakeActionPerformed
//        icingCakeQty++;
//        lbzeroQty.setText(String.valueOf(icingCakeQty));
//        updateCustomIcingCakePrice();
    }//GEN-LAST:event_btnPlusIcingCakeActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
//        setVisible(false);
    }//GEN-LAST:event_confirmButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Icingcustomcake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Icingcustomcake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Icingcustomcake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Icingcustomcake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BeaPOS BeaPOS = null;
                new Icingcustomcake(BeaPOS).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JToggleButton btn5x3IcingCake;
    public javax.swing.JToggleButton btn6x2IcingCake;
    public javax.swing.JToggleButton btn6x3IcingCake;
    public javax.swing.JToggleButton btn7x3IcingCake;
    public javax.swing.JToggleButton btn8x3IcingCake;
    public javax.swing.JToggleButton btn8x4IcingCake;
    public javax.swing.JToggleButton btn9x4IcingCake;
    private javax.swing.JButton btnMinusIcingCake;
    private javax.swing.JButton btnPlusIcingCake;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel customCakePrice;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JLabel lb1075IcingCake;
    private javax.swing.JLabel lb1300IcingCake;
    private javax.swing.JLabel lb1400IcingCake;
    private javax.swing.JLabel lb1800IcingCake;
    private javax.swing.JLabel lb650IcingCake;
    private javax.swing.JLabel lb750IcingCake;
    private javax.swing.JLabel lb850IcingCake;
    private javax.swing.JLabel lbzeroQty;
    // End of variables declaration//GEN-END:variables
}
