/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pkg3rdfinalproject;


import java.awt.Color;
import java.awt.Dialog;
import java.sql.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;

//import pkg3rdfinalproject.Product;




/**
 *
 * @author A315
 */
public class BeaPOS extends javax.swing.JFrame {
    
    
private String savedCustomerName = null;
private String savedCustomerEmail = null;
private String savedCustomerContact = null;
private String lastPaymentMethod = null;
    
    private Connection getConnection() throws SQLException {
    String url = "jdbc:mysql://localhost:3306/bea_d_lites";
    String user = "root";
    String password = "root";
    return DriverManager.getConnection(url, user, password);
}
    private double parseTotalAmountLabel() {
    try {
        String text = totalAmountLabel.getText().replace("₱", "").replace(",", "").trim();
        return Double.parseDouble(text);
    } catch (NumberFormatException e) {
        return 0.0;
    }
}
    
private void saveOrderToDatabase() {
    double orderTotal = parseTotalAmountLabel();
    StringBuilder productNames = new StringBuilder();
    StringBuilder productQuantities = new StringBuilder();
    StringBuilder productToppings = new StringBuilder();

    for (int i = 0; i < billListModel.size(); i++) {
        String entry = billListModel.get(i);
        String[] parts = entry.split("\\|");

        // Product Name
        String productName = parts[0].trim();

        // Quantity
        String quantity = (parts.length > 1) ? parts[1].replace("Qty:", "").trim() : "1";

        // Toppings: search for "Toppings:" in whole entry
        String toppings = "None";
        int toppingsIndex = entry.indexOf("Toppings:");
        if (toppingsIndex != -1) {
            toppings = entry.substring(toppingsIndex + "Toppings:".length()).trim();
            // If there are further fields after toppings, only take up to next " |" or end
            int nextField = toppings.indexOf("|");
            if (nextField != -1) {
                toppings = toppings.substring(0, nextField).trim();
            }
            if (toppings.isEmpty()) toppings = "None";
        }

        // Only append commas if not the first product
        if (productNames.length() > 0) {
            productNames.append(",");
            productQuantities.append(",");
            productToppings.append(",");
        }
        productNames.append(productName);
        productQuantities.append(quantity);
        productToppings.append(toppings);
    }

    String productNamesString = productNames.toString();
    String productQuantitiesString = productQuantities.toString();
    String productToppingsString = productToppings.toString();

    try (Connection conn = getConnection()) {
        String orderSql = "INSERT INTO orders (customer_name, customer_email, customer_contact, order_total, order_date, payment_method, product_names, product_quantities, toppings) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement orderStmt = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
        orderStmt.setString(1, savedCustomerName);
        orderStmt.setString(2, savedCustomerEmail);
        orderStmt.setString(3, savedCustomerContact);
        orderStmt.setDouble(4, orderTotal);
        orderStmt.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
        orderStmt.setString(6, lastPaymentMethod);
        orderStmt.setString(7, productNamesString);
        orderStmt.setString(8, productQuantitiesString);
        orderStmt.setString(9, productToppingsString);
        orderStmt.executeUpdate();
        // rest of your code
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
    }
}
   
   private void resetCart() {
        billListModel.clear();
        
        savedCustomerName = null;
        savedCustomerEmail = null;
        savedCustomerContact = null;
        lastPaymentMethod = null;
    }
    
    
    
    
    
    
    
    //mango bravo
    int mangoQty = 0;
    int mangoBasePrice = 0;
    
    // RedVelvet
    int redVelvetQty = 0;
    int redVelvetBasePrice = 0;

    // StrawberryShortcake
    int strawberryShortcakeQty = 0;
    int strawberryShortcakeBasePrice = 0;

    //bento cake
    int bentoCakeQty = 0;
    int bentoCakeBasePrice = 0;
    
    //chesecake
    int cheesecakeQuantity = 0;
    int cheesecakeBasePrice = 0;
    
    //caramel flan de leche
    int caramelFlanQty = 0;
    int caramelFlanBasePrice = 0;
    
    //mango bravo tub
    int mangoBravoTubQty = 0;
    int mangoBravoTubBasePrice = 0;
    
    //slice cheese cake
    int slicecheesecakeQty = 0;
    int slicecheesecakeBasePrice = 0;
    
    //mini cupcakes
    int miniCupcakesQty = 0;
    int miniCupcakesBasePrice = 0;
    
    //pisces cupcakes
    int piscesCupcakesQty = 0;
    int piscesCupcakesBasePrice = 0;
    
    //yema cake
    int yemaCakeQty = 0;
    int yemaCakeBasePrice = 0;
    
    //ube pandesal
    int ubePandesalQty = 0;
    int ubePandesalBasePrice = 0;
    
    //banana loaf
    int bananaLoafQty = 0;
    int bananaLoafBasePrice = 0;
    
    //brownies
    int browniesQty = 0;
    int browniesBasePrice = 0;
    
    //classic cinnamon
    int classicCinnamonQty = 0;
    int classicCinnamonBasePrice = 0;
    
    //milky cheese donut
    int milkyCheeseDonutQty = 0;
    int milkyCheeseDonutBasePrice = 0;
    
    //banana muffin
    int bananaMuffinQty = 0;
    int bananaMuffinBasePrice = 0;
    
   
    
   
    //variables for sizes of products
    private String selectedMangoBravoSize = "";
    private String selectedRedVelvetSize = "";
    private String selectedStrawberryShortcakeSize = "";
    private String selectedBentoCakeSize = "";
    private String selectedCheesecakeSize = "";
    private String selectedCaramelFlanSize = "";
    private String selectedSliceCheesecakeSize = "";
    private String selectedMangoBravoTubPieces = "";
    private String selectedMiniCupcakesPieces = "";
    private String selectedPiscesCupcakePieces = "";
    private String selectedYemaCakeSize = "";
    private String selectedUbePandesalVariety = "";
    private String selectedBananaLoafVariety = "";
    private String selectedBrowniesPieces= "";
    private String selectedClassicCinnamonPieces = "";
    private String selectedMilkyCheeseDonutPieces = "";
    private String selectedBananaMuffinPieces = "";
    
    
   


    
    /**
     * Creates new form BeaPOS
     */
   DefaultListModel<String> billListModel = new DefaultListModel<>();    
    // At class level
    

    
    public BeaPOS(){
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                confirmExit();
            }
        });
    billList.setModel(billListModel);
    
        
     
        
        
        
        
        
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    String formattedDate = today.format(formatter);
    dateLabel.setText(formattedDate);
        
//    //mango bravo action listeners
//    mangoBravo6x2button.addActionListener(e -> { // 6x2
//    mangoBasePrice = 699;
//    selectedMangoBravoSize = "6x2";
//    
//    updateMangoPrice();
//    
//    });
//    
//    
//    
//    mangoBravo5x2button.addActionListener(e -> { // 5x2
//    mangoBasePrice = 399;
//    selectedMangoBravoSize = "5x2";
//    
//    updateMangoPrice();
//    
//    });
//    
//    mangoBravo4x2button.addActionListener(e -> { // 4x2
//    mangoBasePrice = 299;
//    selectedMangoBravoSize = "4x2";
//    
//    updateMangoPrice();
//    
//    });
//
//    
//    //red velvet action listeners
//    redVelvet6x2sizebutton.addActionListener(e->{
//    redVelvetBasePrice = 799;
//    selectedRedVelvetSize = "6x2";
//    
//    updateRedVelvetPrice();
//    });
//    
//    redVelvet5x2sizeButton.addActionListener(e->{
//    redVelvetBasePrice = 499;
//    selectedRedVelvetSize = "5x2";
//    updateRedVelvetPrice();
//    });
//    
//    
//    //strawberry shortcake listeners
//    strawberryshortcake4x3sizebtn.addActionListener(e->{
//    strawberryShortcakeBasePrice = 299;
//    selectedStrawberryShortcakeSize = "4x3";
//    updateStrawberryShortPrice();    
//    });
//    
//    strawberryshortcake5x3sizebtn.addActionListener(e->{
//    strawberryShortcakeBasePrice = 499;
//    selectedStrawberryShortcakeSize = "5x3";
//    updateStrawberryShortPrice();  
//    });
//    strawberryshortcake6x3sizebtn.addActionListener(e->{
//    strawberryShortcakeBasePrice = 699;
//    selectedStrawberryShortcakeSize = "6x3";    
//    });
//    strawberryshortcake8x3sizebtn.addActionListener(e->{
//    strawberryShortcakeBasePrice = 999;
//    selectedStrawberryShortcakeSize = "8x3";
//    updateStrawberryShortPrice();     
//    });
//    
//    
//    //bento cake listener
//    bentoCakeSizebutton.addActionListener(e->{
//    bentoCakeBasePrice = 340;
//    selectedBentoCakeSize = "4x2 + 2pcs Cupcake";
//    updateBentoCakePrice();     
//    });
//    
//    //chesecake 
//    btn8Cheesecake.addActionListener(e->{
//    cheesecakeBasePrice = 1400;
//    selectedCheesecakeSize = "8";
//    updateCheeseCakePrice();     
//    });
//    
//    //caramel de leche flan 
//    btn6x2CaramelFlan.addActionListener(e->{
//        caramelFlanBasePrice = 699;
//        selectedCaramelFlanSize  = "6x2";
//        updateCaramelLechePrice();
//        
//        
//    });
//    
//    
//    btn5x2CaramelFlan.addActionListener(e->{
//        caramelFlanBasePrice = 399;
//        selectedCaramelFlanSize  = "5x2";
//        updateCaramelLechePrice();
//    });
//    btn4x2CaramelFlan.addActionListener(e->{
//        caramelFlanBasePrice = 299;
//        selectedCaramelFlanSize  = "4x2";
//        updateCaramelLechePrice();
//    });
//    
//    //mangoBravotub
//    mangoBravoTub.addActionListener(e->{
//        mangoBravoTubBasePrice = 170;
//        selectedMangoBravoTubPieces  = "1 Tub";
//        updateMangoBravoTubPrice();
//    });
//    
//    //slice Cheesecake
//    btnSliceCheesecakePiece.addActionListener(e->{
//        slicecheesecakeBasePrice = 170;
//        selectedSliceCheesecakeSize  = "1 Tub";
//        updateSliceCheesecakePrice();
//    });
//    
//    //mini cupcakes
//    btn6PcsMiniCupcake.addActionListener(e->{
//        miniCupcakesBasePrice = 98;
//        selectedMiniCupcakesPieces = "6pcs";
//        updateMiniCupcakesPrice();
//        
//    });
//    btn12PcsMiniCupcake.addActionListener(e->{
//        miniCupcakesBasePrice = 169;
//        selectedMiniCupcakesPieces = "12pcs";
//        updateMiniCupcakesPrice();
//    });
//    
//    //pisces cupcakes
//    btn1pcPisces.addActionListener(e->{ 
//        piscesCupcakesBasePrice = 55;
//        selectedPiscesCupcakePieces = "1pc";
//        updatePiscesCupcakesPrice();        
//    });
//    btn3PcsPisces.addActionListener(e->{ 
//        piscesCupcakesBasePrice = 99;
//        selectedPiscesCupcakePieces = "3pcs";
//        updatePiscesCupcakesPrice();        
//    });
//    btn4PcsPisces.addActionListener(e->{ 
//        piscesCupcakesBasePrice = 149;
//        selectedPiscesCupcakePieces = "4pcs";
//        updatePiscesCupcakesPrice();        
//    });
//    btn6PcsPisces.addActionListener(e->{ 
//        piscesCupcakesBasePrice = 300;
//        selectedPiscesCupcakePieces = "6pcs";
//        updatePiscesCupcakesPrice();        
//    });
//    btn12PcsPisces.addActionListener(e->{ 
//        piscesCupcakesBasePrice = 600;
//        selectedPiscesCupcakePieces = "12pcs";
//        updatePiscesCupcakesPrice();        
//    });
//    
//    //yema cake
//    btn6x2Yemacake.addActionListener(e->{
//        yemaCakeBasePrice = 699;
//        selectedYemaCakeSize = "6x2";
//        updateYemaCakePrice();
//    });
//    btn5x2Yemacake.addActionListener(e->{
//        yemaCakeBasePrice = 399;
//        selectedYemaCakeSize = "5x2";
//        updateYemaCakePrice();
//    });
//    btn4x2Yemacake.addActionListener(e->{
//        yemaCakeBasePrice = 299;
//        selectedYemaCakeSize = "4x2";
//        updateYemaCakePrice();
//    });
    
    
    //ube pandesal 
//    btnUbeCheesedesal.addActionListener(e->{
//        ubePandesalBasePrice = 170;
//        selectedUbePandesalVariety = "Ube Cheesedesal";
//        updateCheesePandesalPrice();
//    });
//    btnUbeHalayaCheesedesal.addActionListener(e->{
//        ubePandesalBasePrice = 185;
//        selectedUbePandesalVariety = "Ube Halaya Cheesedesal";
//        updateCheesePandesalPrice();
//    });
//    
//    //bananaloaf 
//    btnMoistBananaChocoChipLoaf.addActionListener(e->{
//        bananaLoafBasePrice =  160;
//        selectedBananaLoafVariety = "Moist Banana Choco Chip Loaf";
//        updateBananaLoafPrice();
//    });
//    btnMoistBananaChocoChipAlmondLoaf.addActionListener(e->{
//        bananaLoafBasePrice =  190;
//        selectedBananaLoafVariety = "Moist Banana Choco Chip Almond Loaf";
//        updateBananaLoafPrice();
//    });
//    
//    //brownies
//    btn6PcsBrownies.addActionListener(e->{
//        browniesBasePrice = 150;
//        selectedBrowniesPieces = "6pcs";
//        updateBrowniesPrice();
//    });
//    btn12PcsBrownies.addActionListener(e->{
//        browniesBasePrice = 280;
//        selectedBrowniesPieces = "12pcs";
//        updateBrowniesPrice();
//    });
//    btn30PcsBrownies.addActionListener(e->{
//        browniesBasePrice = 750;
//        selectedBrowniesPieces = "30pcs";
//        updateBrowniesPrice();
//    });
//    
//    //classicCinnamon
//    btnBoxof4ClassicCinnamon.addActionListener(e->{
//        classicCinnamonBasePrice = 185;
//        selectedClassicCinnamonPieces = "Box of 4";
//        updateCinnamonPrice();
//    });
//    btnBoxof6ClassicCinnamon.addActionListener(e->{
//        classicCinnamonBasePrice = 280;
//         selectedClassicCinnamonPieces = "Box of 6";
//        updateCinnamonPrice();
//    });
//    
//    //milky cheese Donut
//    btn5PcsMilkyDonut.addActionListener(e->{
//        milkyCheeseDonutBasePrice = 125;
//        selectedMilkyCheeseDonutPieces = "5pcs";
//        updateMilkyCheeseDonutPrice();
//    });
//    btn10PcsMilkyDonut.addActionListener(e->{
//        milkyCheeseDonutBasePrice = 240;
//        selectedMilkyCheeseDonutPieces = "10pcs";
//        updateMilkyCheeseDonutPrice();
//    });
//    
//    //banana muffim
//    btn6PcsBananaMuffin.addActionListener(e->{
//        bananaMuffinBasePrice = 109;
//        selectedBananaMuffinPieces = "6pcs";
//        updateBananaMuffinPrice();
//    });
    
   

    
    //actionlisteners for radiobuttons
   // Mango Bravo
//    mangoRadioButton.addItemListener(e -> {
//    // Assume selectedMangoBravoSize is a String and mangoQty is an int
//    String size = selectedMangoBravoSize;
//    int qty = mangoQty; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Mango Bravo.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        mangoRadioButton.setSelected(false);
//        return;
//        
//    }
//
//    // If all okay, continue as usual
//    String toppings = getSelectedToppingsByProduct("Mango Bravo");
//    addOrUpdateBillItem("Mango Bravo", qty, mangoBasePrice, size, toppings);
//    
//});
//
//// Red Velvet Cake  
//    redVelvetRadioButton.addItemListener(e -> {
//    String size = selectedRedVelvetSize;
//    int qty = redVelvetQty; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Red Velvet Cake.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        redVelvetRadioButton.setSelected(false);
//        return;
//    }
//
//    // If all okay, continue as usual
//    String toppings = getSelectedToppingsByProduct("Red Velvet");
//    addOrUpdateBillItem("Red Velvet", qty, redVelvetBasePrice, size, toppings);
//});
//
//// Strawberry Shortcake
//    strawberryshortcakeradioButton.addItemListener(e -> {
//    String size = selectedStrawberryShortcakeSize;
//    int qty = strawberryShortcakeQty; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Strawberry Shortcake.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        strawberryshortcakeradioButton.setSelected(false);
//        return;
//    }
//    String toppings = getSelectedToppingsByProduct("Stawberry Shortcake");
//    addOrUpdateBillItem("Strawberry Shortcake", qty, strawberryShortcakeBasePrice, size, toppings);
//});
//
//// Bento Cake
//    bentoCakeRadioButton.addItemListener(e -> {
//    String size = selectedBentoCakeSize;
//    int qty = bentoCakeQty; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Bento Cake.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        bentoCakeRadioButton.setSelected(false);
//        return;
//    }
//    String toppings = getSelectedToppingsByProduct("Bento Cake");
//    addOrUpdateBillItem("Bento Cake", qty, bentoCakeBasePrice, size, toppings);
//});
//
//// Caramel Flan de Leche
//    caramelFlanRadio.addItemListener(e -> {
//    String size = selectedCaramelFlanSize;
//    int qty = caramelFlanQty; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Caramel Flan de Leche.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        caramelFlanRadio.setSelected(false);
//        return;
//    }
//    String toppings = getSelectedToppingsByProduct("Caramel Flan");
//    addOrUpdateBillItem("Caramel Flan de Leche", qty, caramelFlanBasePrice, size, toppings);
//});
//
//// Cheesecake
//    cheeseCakeRadio.addItemListener(e -> {
//    String size = selectedCheesecakeSize;
//    int qty = cheesecakeQuantity; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Cheesecake.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        cheeseCakeRadio.setSelected(false);
//        return;
//    }
//    String toppings = getSelectedToppingsByProduct("Cheesecake");
//    addOrUpdateBillItem("Cheesecake", qty, cheesecakeBasePrice, size, toppings);
//});
//
//// Slice Cheesecake
//    sliceCheesecakeRadio.addItemListener(e -> {
//    String size = selectedSliceCheesecakeSize;
//    int qty = cheesecakeQuantity; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Slice Cheesecake.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        sliceCheesecakeRadio.setSelected(false);
//        return;
//    }
//    String toppings = getSelectedToppingsByProduct("Cheesecake");
//    addOrUpdateBillItem("Cheesecake", qty, cheesecakeBasePrice, size, toppings);
//});
//
//// Mango Bravo Tub
//    mangoBravoTubRadio.addItemListener(e -> {
//    String size = selectedMangoBravoTubPieces;
//    int qty = mangoBravoTubQty; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Mango Bravo Tub.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        mangoBravoTubRadio.setSelected(false);
//        return;
//    }
//    String toppings = getSelectedToppingsByProduct("Mango Bravo Tub");
//    addOrUpdateBillItem("Mango Bravo Tub", qty, mangoBravoTubBasePrice, size, toppings);
//    
//});
//
//// Mini Cupcakes
//    miniCupcakesRadio.addItemListener(e -> {
//    String size = selectedMiniCupcakesPieces;
//    int qty = miniCupcakesQty; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Mini Cupcakes.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        miniCupcakesRadio.setSelected(false);
//        return;
//    }
//    String toppings = getSelectedToppingsByProduct("Mini Cupcakes");
//    addOrUpdateBillItem("Mini Cupcakes", qty, miniCupcakesBasePrice, size, toppings);
//});
//
//// Pisces Cupcake
//    piscesRadio.addItemListener(e -> {
//    
//    String size = selectedPiscesCupcakePieces;
//    int qty = piscesCupcakesQty; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Pisces Cupcakes.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        piscesRadio.setSelected(false);
//        return;
//    }
//    String toppings = getSelectedToppingsByProduct("Pisces Cupcakes");
//    addOrUpdateBillItem("Pisces Cupcakes", qty, piscesCupcakesBasePrice, size, toppings);
//});
//
//// Yema Cake
//    yemaCakeRadio.addItemListener(e -> {
//    String size = selectedYemaCakeSize;
//    int qty = yemaCakeQty; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Yema Cake.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        yemaCakeRadio.setSelected(false);
//        return;
//    }
//    String toppings = getSelectedToppingsByProduct("Yema Cake");
//    addOrUpdateBillItem("Yema Cake", qty, yemaCakeBasePrice, size, toppings);
//});
   

//// Ube Pandesal
//    ubePandesalRadio.addItemListener(e -> {
//    String size = selectedUbePandesalVariety;
//    int qty = ubePandesalQty; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Ube Pandesal.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        ubePandesalRadio.setSelected(false);
//        return;
//    }
//    String toppings = getSelectedToppingsByProduct("Ube Pandesal");
//    addOrUpdateBillItem("Ube Pandesal", qty, yemaCakeBasePrice, size, toppings);
//});
//
//// Banana Loaf
//    BananaLoafRadio.addItemListener(e -> {
//    String size = selectedBananaLoafVariety;
//    int qty = bananaLoafQty; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Banana Loaf.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        BananaLoafRadio.setSelected(false);
//        return;
//    }
//    String toppings = getSelectedToppingsByProduct("Banana Loaf");
//    addOrUpdateBillItem("Banana Loaf", qty, bananaLoafBasePrice, size, toppings);
//});
//
//// Brownies
//    browniesRadio.addItemListener(e -> {
//    String size = selectedBrowniesPieces;
//    int qty = browniesQty; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Brownies.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        browniesRadio.setSelected(false);
//        return;
//    }
//    String toppings = getSelectedToppingsByProduct("Brownies");
//    addOrUpdateBillItem("Brownies", qty, browniesBasePrice, size, toppings);
//    
//});
//
//// Classic
//    classicCinnamonRadio.addItemListener(e -> {
//    String size = selectedClassicCinnamonPieces;
//    int qty = classicCinnamonQty; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Classic Cinnamon.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        classicCinnamonRadio.setSelected(false);
//        return;
//    }
//    String toppings = getSelectedToppingsByProduct("Classic Cinnamon");
//    addOrUpdateBillItem("Classic Cinnamon", qty, classicCinnamonBasePrice, size, toppings);
//});
//
//// Milky Cheese Donut
//    milkyCheeseRadio.addItemListener(e -> {
//    String size = selectedMilkyCheeseDonutPieces;
//    int qty = milkyCheeseDonutQty; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Milky Cheese Donut.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        milkyCheeseRadio.setSelected(false);
//        return;
//    }
//    String toppings = getSelectedToppingsByProduct("Milky Cheese");
//    addOrUpdateBillItem("Milky Cheese Donut", qty, milkyCheeseDonutBasePrice, size, toppings);
//});
//
//// Banana Muffin
//    bananaMuffinRadio.addItemListener(e -> {
//    String size = selectedBananaMuffinPieces;
//    int qty = bananaMuffinQty; // Or get the value from your quantity control
//
//    // Check if size or quantity is not set
//    if (size == null || size.isEmpty() || qty <= 0) {
//        // Show error message and unselect the radio button
//        JOptionPane.showMessageDialog(null,
//            "Please select a size and enter a quantity before adding Banana Muffin.",
//            "Selection Error",
//            JOptionPane.ERROR_MESSAGE);
//        // Uncheck the radio button programmatically
//        bananaMuffinRadio.setSelected(false);
//        return;
//    }
//    String toppings = getSelectedToppingsByProduct("Banana Muffin");
//    addOrUpdateBillItem("Banana Muffin", qty, bananaMuffinBasePrice, size, toppings);
//});
    
//    btnCustomeCakeIcingCake.addActionListener(e -> {
//    Icingcustomcake icingFrame = new Icingcustomcake(this);
//    icingFrame.setVisible(true);
//});
//    
//    
//    btnMinimalistCakeCustomCake.addActionListener(e -> {
//    new MinimalistCustomCake(this).setVisible(true);
//});
//    
//    
//    
//    customCakeButton.addActionListener(e -> {
//    
//    String sizePattern = "^\\d+x\\d+[a-zA-Z]*$";
//    String size = null;
//    while (true) {
//        size = JOptionPane.showInputDialog(this, "Enter fondant cake size (e.g. 8x2, 10x4in):", "Fondant Cake Size", JOptionPane.QUESTION_MESSAGE);
//        if (size == null) return; // User cancelled
//        size = size.trim();
//        if (size.matches(sizePattern)) {
//            break; // Valid
//        } else {
//            JOptionPane.showMessageDialog(this, "Invalid size format. Please use e.g. 8x2 or 10x4in.", "Input Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    // Prompt for declared value/price
//    String valueStr = null;
//    int price = 0;
//    while (true) {
//        valueStr = JOptionPane.showInputDialog(this, "Enter declared value/price for this fondant cake:", "Fondant Cake Price", JOptionPane.QUESTION_MESSAGE);
//        if (valueStr == null) return; // User cancelled
//        valueStr = valueStr.trim();
//        try {
//            price = Integer.parseInt(valueStr);
//            if (price > 0) break;
//            else throw new NumberFormatException();
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(this, "Please enter a valid positive integer for the price.", "Input Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    // Add to bill
//    String productName = "Fondant Custom Cake (" + size + ")";
//    String entry = String.format("%s | Qty: %d | ₱%d", productName, 1, price);
//    billListModel.addElement(entry);
//    updateTotalAmountLabel();
//});
//    
    
    //action listener for receipt 
    payByCashBtn.addActionListener(e -> {
    // 0. Prevent payment if bill is empty
    if (billListModel.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Cart is empty. Please add items before proceeding to payment.", "Empty Cart", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 1. Collect customer info (loop until valid contact number or cancel)
    while (savedCustomerName == null) {
        CustomerInfoFrame infoFrame = new CustomerInfoFrame(this);
        infoFrame.setVisible(true);
        if (!infoFrame.isSubmitted()) {
            // User cancelled
            return;
        }
        String contact = infoFrame.customerContact;
        if (!contact.matches("^09\\d{9}$")) {
            JOptionPane.showMessageDialog(this,
                "Invalid contact number. Please enter a number starting with 09 and 11 digits long.",
                "Invalid Contact Number",
                JOptionPane.ERROR_MESSAGE);
            // Loop again
            continue;
        }
        savedCustomerName = infoFrame.customerName;
        savedCustomerEmail = infoFrame.customerEmail;
        savedCustomerContact = contact;
    }
    lastPaymentMethod = "Cash";

    // 2. Gather bill items (assuming billListModel holds your items in the format: "Product | Qty: n | ₱amount")
    StringBuilder itemsHtml = new StringBuilder();
    int total = 0;
    for (int i = 0; i < billListModel.size(); i++) {
        String entry = billListModel.get(i);
        String[] parts = entry.split("\\|");
        if (parts.length == 3) {
            String itemName = parts[0].trim();
            String qty = parts[1].replace("Qty:", "").trim();
            String priceStr = parts[2].replace("₱", "").trim();
            int price = Integer.parseInt(priceStr);
            total += price;
            itemsHtml.append("<tr>")
                .append("<td>").append(itemName).append("</td>")
                .append("<td>").append(qty).append("</td>")
                .append("<td style=\"text-align:right;\">₱").append(price).append("</td>")
                .append("</tr>");
        }
    }

    // 3. Ask for paid amount (cash)
    int paid = 0;
    while (true) {
        String paidStr = JOptionPane.showInputDialog(this, "Enter cash amount paid:", total);
        if (paidStr == null) return; // Cancelled
        paidStr = paidStr.trim();
        try {
            paid = Integer.parseInt(paidStr);
            if (paid < total) {
                JOptionPane.showMessageDialog(this, "Amount paid is less than the total. Please enter a valid amount.", "Insufficient Payment", JOptionPane.WARNING_MESSAGE);
                continue;
            }
            break; // Valid input
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }
    int change = paid - total;


    // 4. Prepare HTML receipt using String.format for cleaner code
    String htmlReceipt = String.format("""
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Receipt</title>
  <style>
    .receipt-box { font-family: Arial, Helvetica, sans-serif; border: 2px dashed #d9a066; background: #fffaf0; width: 400px; margin: 20px auto; padding: 20px; }
    h2, h4 { text-align: center; margin: 0; }
    .receipt-header { border-bottom: 1px solid #ccc; margin-bottom: 10px; padding-bottom: 10px; }
    .items th, .items td { text-align: left; padding: 5px 0; }
    .totals td { font-weight: bold; }
    .footer { text-align: center; font-size: 0.9em; color: #e20f8a; margin-top: 10px; }
  </style>
</head>
<body>
  <div class="receipt-box">
    <div class="receipt-header">
      <h2>Bea D Lites</h2>
      <h4>Pastries & Cakes</h4>
      <p style="text-align:center;">
        411 Nebraska St., Villa Priscilla, Cutcot, Pulilan, Bulacan<br>
        Contact: 0949 470 1077<br>
        Receipt #: <strong>00000X</strong><br>
        Date: <strong>%s</strong>
      </p>
    </div>
    <div class="receipt-details">
      <p><strong>Customer:</strong> %s<br>
      <strong>Email:</strong> %s<br>
      <strong>Contact:</strong> %s</p>
    </div>
    <table class="items" width="100%%">
      <thead>
        <tr>
          <th>Item</th>
          <th>Qty</th>
          <th style="text-align:right;">Price</th>
        </tr>
      </thead>
      <tbody>
        %s
      </tbody>
    </table>
    <table class="totals" width="100%%">
      <tr>
        <td>Total</td>
        <td style="text-align:right;">₱%d</td>
      </tr>
      <tr>
        <td>Cash</td>
        <td style="text-align:right;">₱%d</td>
      </tr>
      <tr>
        <td>Change</td>
        <td style="text-align:right;">₱%d</td>
      </tr>
    </table>
    <div class="footer">
      <p>Thank you for your purchase!</p>
      <p>Follow us on Instagram @beadlites</p>
    </div>
  </div>
</body>
</html>
""",
        java.time.LocalDate.now(),
        savedCustomerName,
        savedCustomerEmail,
        savedCustomerContact,
        itemsHtml.toString(),
        total,
        paid,
        change
            
            
    );
    // 5. Send the email using JavaMailSender
    try {
        JavaMailSender.sendHtmlEmail(savedCustomerEmail, "Your Purchase Receipt", htmlReceipt);
        JOptionPane.showMessageDialog(this, "Receipt emailed to " + savedCustomerEmail);
        
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Failed to send email: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    saveOrderToDatabase();
    
    savedCustomerName = null;
    savedCustomerEmail = null;
    savedCustomerContact = null;
    lastPaymentMethod = null;
    billListModel.clear();
    resetCart();
    setTotalAmount();
    });
    
    
    payByGCASHBtn.addActionListener(e -> {
    // 0. Prevent payment if bill is empty
    if (billListModel.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Cart is empty. Please add items before proceeding to payment.", "Empty Cart", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 1. Collect customer info (loop until valid contact number or cancel)
    while (savedCustomerName == null) {
        CustomerInfoFrame infoFrame = new CustomerInfoFrame(this);
        infoFrame.setVisible(true);
        if (!infoFrame.isSubmitted()) {
            // User cancelled
            return;
        }
        String contact = infoFrame.customerContact;
        if (!contact.matches("^09\\d{9}$")) {
            JOptionPane.showMessageDialog(this,
                "Invalid contact number. Please enter a number starting with 09 and 11 digits long.",
                "Invalid Contact Number",
                JOptionPane.ERROR_MESSAGE);
            // Loop again
            continue;
        }
        savedCustomerName = infoFrame.customerName;
        savedCustomerEmail = infoFrame.customerEmail;
        savedCustomerContact = contact;
    }
    lastPaymentMethod = "GCash";

    // 2. Calculate total from your billListModel
    StringBuilder itemsHtml = new StringBuilder();
    int total = 0;
    for (int i = 0; i < billListModel.size(); i++) {
        String entry = billListModel.get(i);
        String[] parts = entry.split("\\|");
        if (parts.length == 3) {
            String itemName = parts[0].trim();
            String qty = parts[1].replace("Qty:", "").trim();
            String priceStr = parts[2].replace("₱", "").trim();
            int price = Integer.parseInt(priceStr);
            total += price;
            itemsHtml.append("<tr>")
                .append("<td>").append(itemName).append("</td>")
                .append("<td>").append(qty).append("</td>")
                .append("<td style=\"text-align:right;\">₱").append(price).append("</td>")
                .append("</tr>");
        }
    }

    // 3. Generate the GCash QR string 
    String gcashQRString = "00020101021127830012com.p2pqrpay0111GXCHPHM2XXX02089996440303152170200000006560417DWQM4TK3JDNYRIX0S5204601653036085802PH5915MA****W MA*C S.6005Sibul6104123463043B33"; // Replace with your merchant QR

    // 4. Show the QR code dialog
    GCashQRDialog.showGCashQR(gcashQRString);

    // 5. After payment, let the cashier confirm, then send the receipt
    int confirm = JOptionPane.showConfirmDialog(this, "Has the payment been completed via GCash?", "Confirm Payment", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) return;

    // 6. Prepare HTML receipt
    String htmlReceipt = String.format("""
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Receipt</title>
  <style>
    .receipt-box { font-family: Arial, Helvetica, sans-serif; border: 2px dashed #d9a066; background: #fffaf0; width: 400px; margin: 20px auto; padding: 20px; }
    h2, h4 { text-align: center; margin: 0; }
    .receipt-header { border-bottom: 1px solid #ccc; margin-bottom: 10px; padding-bottom: 10px; }
    .items th, .items td { text-align: left; padding: 5px 0; }
    .totals td { font-weight: bold; }
    .footer { text-align: center; font-size: 0.9em; color: #e20f8a; margin-top: 10px; }
  </style>
</head>
<body>
  <div class="receipt-box">
    <div class="receipt-header">
      <h2>Bea D Lites</h2>
      <h4>Pastries & Cakes</h4>
      <p style="text-align:center;">
        411 Nebraska St., Villa Priscilla, Cutcot, Pulilan, Bulacan<br>
        Contact: 0949 470 1077<br>
        Receipt #: <strong>00000X</strong><br>
        Date: <strong>%s</strong>
      </p>
    </div>
    <div class="receipt-details">
      <p><strong>Customer:</strong> %s<br>
      <strong>Email:</strong> %s<br>
      <strong>Contact:</strong> %s</p>
    </div>
    <table class="items" width="100%%">
      <thead>
        <tr>
          <th>Item</th>
          <th>Qty</th>
          <th style="text-align:right;">Price</th>
        </tr>
      </thead>
      <tbody>
        %s
      </tbody>
    </table>
    <table class="totals" width="100%%">
      <tr>
        <td>Total</td>
        <td style="text-align:right;">₱%d</td>
      </tr>
      <tr>
        <td>Paid via</td>
        <td style="text-align:right;">GCash</td>
      </tr>
    </table>
    <div class="footer">
      <p>Thank you for your purchase!</p>
      <p>Follow us on Instagram @beadlites</p>
    </div>
  </div>
</body>
</html>
""",
        java.time.LocalDate.now(),
        savedCustomerName,
        savedCustomerEmail,
        savedCustomerContact,
        itemsHtml.toString(),
        total
    );

    // 7. Send the email
    try {
        JavaMailSender.sendHtmlEmail(savedCustomerEmail, "Your Purchase Receipt", htmlReceipt);
        JOptionPane.showMessageDialog(this, "Receipt emailed to " + savedCustomerEmail);
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Failed to send email: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
   
    saveOrderToDatabase();
    
    savedCustomerName = null;
    savedCustomerEmail = null;
    savedCustomerContact = null;
    lastPaymentMethod = null;
    billListModel.clear();
    resetCart();
    setTotalAmount();
});

    

    
      }
    
    
    //methods for getting the toppings
//    private String getSelectedToppingsByProduct(String productName) {
//    StringBuilder toppings = new StringBuilder();
//    switch (productName) {
//        case "Mango Bravo":
//            if (mangoToppings.isSelected()) toppings.append("Mango Toppings");
//            break;
//        case "Strawberry Shortcake": // fixed typo
//            if (strawberryToppingsShortcake.isSelected()) 
//                toppings.append("Strawberry Toppings, ");
//            break;
//        case "Cheesecake":
//            if (blueberryToppingsCheesecake.isSelected()) toppings.append("Blueberry Toppings, ");
//            if (mangoToppingsCheesecake.isSelected()) toppings.append("Mango Toppings, ");
//            if (biscoffToppingsCheesecake.isSelected()) toppings.append("Biscoff Toppings, ");
//            if (strawberryToppingsCheesecake.isSelected()) toppings.append("Strawberry Toppings, ");
//            break;
//        case "Caramel Flan":
//            if (caramelToppingsLecheFlan.isSelected()) toppings.append("Caramel Toppings, ");
//            if (lecheFlanToppingsCake.isSelected()) toppings.append("Leche Flan Toppings, ");
//            break;
//        case "Slice Cheesecake":
//            if (blueberryToppingsSliceCheesecake.isSelected()) toppings.append("Blueberry Toppings, ");
//            if (mangoToppingsSliceCheesecake.isSelected()) toppings.append("Mango Toppings, ");
//            if (biscoffToppingsSliceCheesecake.isSelected()) toppings.append("Biscoff Toppings, ");
//            if (strawberryToppingsSliceCheesecake.isSelected()) toppings.append("Strawberry Toppings, ");
//            break;
//        // No toppings for these products
//        case "Red Velvet":
//        case "Bento Cake":
//        case "Mango Bravo Tub":
//        case "Mini Cupcakes":
//        case "Pisces Cupcakes":
//        case "Ube Pandesal":
//        case "Banana Loaf":
//        case "Brownies":
//        case "Classic Cinnamon":
//        case "Milky Cheese Donut":
//        case "Banana Muffin":
//            return "None";
//        default:
//            return "None";
//    }
//    // Remove trailing comma and space, if any
//    if (toppings.length() > 0) {
//        if (toppings.charAt(toppings.length() - 2) == ',' && toppings.charAt(toppings.length() - 1) == ' ') {
//            toppings.setLength(toppings.length() - 2);
//        }
//        return toppings.toString();
//    } else {
//        return "None";
//    }
//}
    
    
    
    //methods for actionListeners
    public void setTotalAmount(){
        totalAmountLabel.setText("₱"+ 0.00);
    }
 
    
    /**
     *
     * @param cakeSize
     * @param quantity
     * @param price
     */
public void addCustomCakeToBill(String cakeSize, int quantity, int price) {
    if (quantity <= 0) return;
    String productName = "Custom Icing Cake (" + cakeSize + ")";
    // Remove existing entry for this size if it exists
    for (int i = 0; i < billListModel.size(); i++) {
        if (billListModel.get(i).startsWith(productName + " |")) {
            billListModel.remove(i);
            break;
        }
    }
    String entry = String.format("%s | Qty: %d | ₱%d", productName, quantity, quantity * price);
    billListModel.addElement(entry);
  
    updateTotalAmountLabel();
}

    /**
     *
     * @param cakeSize
     * @param quantity
     * @param price
     */

    public void addMinimalistCustomCakeToBill(String cakeSize, int quantity, int price) {
    if (quantity <= 0) return;
    String productName = "Custom Minimalist Cake (" + cakeSize + ")";
    // Remove existing entry for this size if it exists
    for (int i = 0; i < billListModel.size(); i++) {
        if (billListModel.get(i).startsWith(productName + " |")) {
            billListModel.remove(i);
            break;
        }
    }
    String entry = String.format("%s | Qty: %d | ₱%d", productName, quantity, quantity * price);
    billListModel.addElement(entry);
    
    updateTotalAmountLabel();
}

 
    
    private void confirmExit(){
        int choice = JOptionPane.showConfirmDialog(this,"Are you sure you want to Exit?", "Exit",JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
   
    
    public void addFondantCakeToBill(String size, int price) {
        String productName = "Fondant Custom Cake (" + size + ")";
        // Remove existing
        for (int i = 0; i < billListModel.size(); i++) {
            if (billListModel.get(i).startsWith(productName + " |")) {
                billListModel.remove(i);
                
                break;
            }
        }
        String entry = String.format("%s | Qty: %d | ₱%d", productName, 1, price);
        billListModel.addElement(entry);
        
        updateTotalAmountLabel();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        productsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCake = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnBreadSweets1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        BillPanel = new javax.swing.JPanel();
        dateLabel = new javax.swing.JLabel();
        totalstaticlabel = new javax.swing.JLabel();
        totalAmountLabel = new javax.swing.JLabel();
        payByCashBtn = new javax.swing.JToggleButton();
        payByGCASHBtn = new javax.swing.JToggleButton();
        billLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        billList = new javax.swing.JList<>();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel71 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cakesPanel = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        AddMangoBravoToPanel = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        AddRedVelvetCakeToPanel = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        AddStrawberryShortCakeToPanel = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel51 = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        AddBentoCakeToPanel = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        AddCaramelFlanDeLecheCakeToPanel = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        AddCheeseCakeToPanel = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        AddSliceCheeseCakeToPanel = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        AddMangoBravoTubToPanel = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        AddMiniCupcakeToPanel = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        AddPiscesCupcakeToPanel = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        AddIcingCustomCakeToPanel = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jPanel84 = new javax.swing.JPanel();
        jPanel85 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        AddMinimalistCustomCakeToPanel = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel88 = new javax.swing.JPanel();
        jPanel89 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        AddFondantCustomCakeToPanel = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel92 = new javax.swing.JPanel();
        jPanel93 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        AddYemaCakeToPanel = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        breadsandsweetsPanel = new javax.swing.JPanel();
        jPanel59 = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jPanel61 = new javax.swing.JPanel();
        btnMinusUbePandesal = new javax.swing.JButton();
        lbzeroUbePandesal = new javax.swing.JLabel();
        btnPlusUbePandesal = new javax.swing.JButton();
        jPanel62 = new javax.swing.JPanel();
        lbPhpUbePandesal = new javax.swing.JLabel();
        btnUbeCheesedesal = new javax.swing.JToggleButton();
        btnUbeHalayaCheesedesal = new javax.swing.JToggleButton();
        ubePandesalRadio = new javax.swing.JRadioButton();
        jPanel63 = new javax.swing.JPanel();
        jPanel64 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jPanel65 = new javax.swing.JPanel();
        btnMinusBananaLoaf = new javax.swing.JButton();
        lbzeroBananaLoaf = new javax.swing.JLabel();
        btnPlusBananaLoaf = new javax.swing.JButton();
        jPanel66 = new javax.swing.JPanel();
        lbPhpBananaLoaf = new javax.swing.JLabel();
        btnMoistBananaChocoChipLoaf = new javax.swing.JToggleButton();
        btnMoistBananaChocoChipAlmondLoaf = new javax.swing.JToggleButton();
        BananaLoafRadio = new javax.swing.JRadioButton();
        jPanel67 = new javax.swing.JPanel();
        jPanel68 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jPanel69 = new javax.swing.JPanel();
        btnMinusBrownies = new javax.swing.JButton();
        lbzeroBrownies = new javax.swing.JLabel();
        btnPlusBrownies = new javax.swing.JButton();
        jPanel70 = new javax.swing.JPanel();
        lbPhpBrownies = new javax.swing.JLabel();
        btn6PcsBrownies = new javax.swing.JToggleButton();
        btn12PcsBrownies = new javax.swing.JToggleButton();
        browniesRadio = new javax.swing.JRadioButton();
        btn30PcsBrownies = new javax.swing.JToggleButton();
        jPanel72 = new javax.swing.JPanel();
        jPanel73 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        jPanel74 = new javax.swing.JPanel();
        btnMinusClassicCinnamon = new javax.swing.JButton();
        lbzeroClassicCinnamon = new javax.swing.JLabel();
        btnPlusClassicCinnamon = new javax.swing.JButton();
        jPanel75 = new javax.swing.JPanel();
        lbPhpClassicCinnamon = new javax.swing.JLabel();
        btnBoxof4ClassicCinnamon = new javax.swing.JToggleButton();
        btnBoxof6ClassicCinnamon = new javax.swing.JToggleButton();
        classicCinnamonRadio = new javax.swing.JRadioButton();
        jPanel76 = new javax.swing.JPanel();
        jPanel77 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jPanel78 = new javax.swing.JPanel();
        btnMinusMilkyDonut = new javax.swing.JButton();
        lbzeroMilkyDonut = new javax.swing.JLabel();
        btnPlusMilkyDonut = new javax.swing.JButton();
        jPanel79 = new javax.swing.JPanel();
        lbPhpMilkyDonut = new javax.swing.JLabel();
        btn5PcsMilkyDonut = new javax.swing.JToggleButton();
        btn10PcsMilkyDonut = new javax.swing.JToggleButton();
        milkyCheeseRadio = new javax.swing.JRadioButton();
        jPanel80 = new javax.swing.JPanel();
        jPanel81 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jPanel82 = new javax.swing.JPanel();
        btnMinuMuffin = new javax.swing.JButton();
        lbzeroMuffin = new javax.swing.JLabel();
        btnPlusMuffin = new javax.swing.JButton();
        jPanel83 = new javax.swing.JPanel();
        lbPhpMuffin = new javax.swing.JLabel();
        bananaMuffinRadio = new javax.swing.JRadioButton();
        btn6PcsBananaMuffin = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(44, 204, 98));

        jPanel2.setBackground(new java.awt.Color(225, 135, 44));

        jPanel3.setBackground(new java.awt.Color(255, 204, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 204, 102));

        jPanel8.setBackground(new java.awt.Color(255, 255, 153));
        jPanel8.setForeground(new java.awt.Color(225, 135, 44));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loupe.png"))); // NOI18N

        txtSearch.setBackground(new java.awt.Color(255, 255, 153));
        txtSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(225, 135, 44));
        txtSearch.setText("Search");
        txtSearch.setBorder(null);
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(225, 135, 44));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BeA D' Lites.1png_1.png"))); // NOI18N
        jLabel6.setText("Bea D Lites Point & Stock");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(633, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 6, 1300, 50));

        productsPanel.setBackground(new java.awt.Color(255, 255, 153));
        productsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cake (1).png"))); // NOI18N
        jLabel1.setText(" Cakes");
        productsPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 90, -1));

        btnCake.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCake.setForeground(new java.awt.Color(0, 0, 0));
        btnCake.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Top view of cake with cinnamon sticks _ Premium Photo.png"))); // NOI18N
        btnCake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCakeActionPerformed(evt);
            }
        });
        productsPanel.add(btnCake, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 170, 70));

        jLabel2.setBackground(new java.awt.Color(255, 204, 102));
        jLabel2.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 102));
        jLabel2.setText("& Sweets");
        productsPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 70, -1));

        jLabel3.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 204, 102));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bakery.png"))); // NOI18N
        jLabel3.setText("Breads ");
        productsPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 100, -1));

        btnBreadSweets1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBreadSweets1.setForeground(new java.awt.Color(0, 0, 0));
        btnBreadSweets1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Premium Photo _ Pastries.jpg"))); // NOI18N
        btnBreadSweets1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        btnBreadSweets1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBreadSweets1ActionPerformed(evt);
            }
        });
        productsPanel.add(btnBreadSweets1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 170, 70));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(225, 135, 44));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Pastry");
        productsPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 70, -1));

        backButton.setBackground(new java.awt.Color(255, 255, 153));
        backButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        backButton.setForeground(new java.awt.Color(225, 135, 44));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        productsPanel.add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 490, 160, 40));

        jPanel3.add(productsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 58, 190, 690));

        BillPanel.setBackground(new java.awt.Color(255, 255, 153));

        dateLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        dateLabel.setForeground(new java.awt.Color(225, 135, 44));
        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateLabel.setText("day");

        totalstaticlabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        totalstaticlabel.setForeground(new java.awt.Color(0, 0, 0));
        totalstaticlabel.setText("Total");

        totalAmountLabel.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        totalAmountLabel.setForeground(new java.awt.Color(102, 102, 102));
        totalAmountLabel.setText("Amount");

        payByCashBtn.setBackground(new java.awt.Color(255, 204, 102));
        payByCashBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        payByCashBtn.setForeground(new java.awt.Color(225, 135, 44));
        payByCashBtn.setText("Pay By Cash");
        payByCashBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payByCashBtnActionPerformed(evt);
            }
        });

        payByGCASHBtn.setBackground(new java.awt.Color(255, 204, 102));
        payByGCASHBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        payByGCASHBtn.setForeground(new java.awt.Color(225, 135, 44));
        payByGCASHBtn.setText("Pay By Gcash");

        billLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        billLabel.setForeground(new java.awt.Color(225, 135, 44));
        billLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        billLabel.setText("Bill");

        billList.setBackground(new java.awt.Color(255, 255, 153));
        billList.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        billList.setForeground(new java.awt.Color(0, 0, 0));
        billList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(billList);

        javax.swing.GroupLayout BillPanelLayout = new javax.swing.GroupLayout(BillPanel);
        BillPanel.setLayout(BillPanelLayout);
        BillPanelLayout.setHorizontalGroup(
            BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BillPanelLayout.createSequentialGroup()
                .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BillPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(payByGCASHBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(payByCashBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BillPanelLayout.createSequentialGroup()
                                .addComponent(totalstaticlabel)
                                .addGap(115, 115, 115)
                                .addComponent(totalAmountLabel)))
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addGroup(BillPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(billLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(BillPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );
        BillPanelLayout.setVerticalGroup(
            BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BillPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(billLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalstaticlabel)
                    .addGroup(BillPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(totalAmountLabel)))
                .addGap(32, 32, 32)
                .addComponent(payByCashBtn)
                .addGap(18, 18, 18)
                .addComponent(payByGCASHBtn)
                .addContainerGap())
        );

        jPanel3.add(BillPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 60, 240, 690));

        jPanel71.setBackground(new java.awt.Color(225, 135, 44));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        cakesPanel.setBackground(new java.awt.Color(255, 255, 153));

        jPanel15.setBackground(new java.awt.Color(255, 204, 102));

        jPanel9.setBackground(new java.awt.Color(255, 255, 153));

        jLabel43.setBackground(new java.awt.Color(225, 135, 44));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Mango bravo.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AddMangoBravoToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddMangoBravoToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddMangoBravoToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddMangoBravoToPanel.setText("ADD");
        AddMangoBravoToPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMangoBravoToPanelActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(225, 135, 44));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Mango Bravo");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddMangoBravoToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddMangoBravoToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        jPanel16.setBackground(new java.awt.Color(255, 204, 102));

        jPanel17.setBackground(new java.awt.Color(255, 255, 153));

        jLabel45.setBackground(new java.awt.Color(225, 135, 44));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b81690e5-b1dd-4a5f-8dd0-4d09b24d2ecf.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        AddRedVelvetCakeToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddRedVelvetCakeToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddRedVelvetCakeToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddRedVelvetCakeToPanel.setText("ADD");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(225, 135, 44));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("RedVelvet Cake");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(AddRedVelvetCakeToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddRedVelvetCakeToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel23.setBackground(new java.awt.Color(255, 204, 102));

        jPanel24.setBackground(new java.awt.Color(255, 255, 153));

        jLabel49.setBackground(new java.awt.Color(225, 135, 44));
        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/strawberry cake.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        AddStrawberryShortCakeToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddStrawberryShortCakeToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddStrawberryShortCakeToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddStrawberryShortCakeToPanel.setText("ADD");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(225, 135, 44));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Strawberry");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(225, 135, 44));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("ShortCake");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(AddStrawberryShortCakeToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddStrawberryShortCakeToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        jPanel51.setBackground(new java.awt.Color(255, 204, 102));

        jPanel52.setBackground(new java.awt.Color(255, 255, 153));

        jLabel82.setBackground(new java.awt.Color(225, 135, 44));
        jLabel82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bento _1.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel82)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createSequentialGroup()
                .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        AddBentoCakeToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddBentoCakeToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddBentoCakeToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddBentoCakeToPanel.setText("ADD");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(225, 135, 44));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Bento Cake");

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AddBentoCakeToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(AddBentoCakeToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel27.setBackground(new java.awt.Color(255, 204, 102));

        jPanel28.setBackground(new java.awt.Color(255, 255, 153));

        jLabel53.setBackground(new java.awt.Color(225, 135, 44));
        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/87960fcc-fa36-41bd-92dd-bfa842c9fe36.png"))); // NOI18N

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        AddCaramelFlanDeLecheCakeToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddCaramelFlanDeLecheCakeToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddCaramelFlanDeLecheCakeToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddCaramelFlanDeLecheCakeToPanel.setText("ADD");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(225, 135, 44));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Caramel flan de");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(225, 135, 44));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("leche cake");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(AddCaramelFlanDeLecheCakeToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddCaramelFlanDeLecheCakeToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel31.setBackground(new java.awt.Color(255, 204, 102));

        jPanel32.setBackground(new java.awt.Color(255, 255, 153));

        jLabel58.setBackground(new java.awt.Color(225, 135, 44));
        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cheesecake(1).jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel58)
                .addGap(17, 17, 17))
        );

        AddCheeseCakeToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddCheeseCakeToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddCheeseCakeToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddCheeseCakeToPanel.setText("ADD");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(225, 135, 44));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("CheeseCake");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AddCheeseCakeToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddCheeseCakeToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel35.setBackground(new java.awt.Color(255, 204, 102));

        jPanel36.setBackground(new java.awt.Color(255, 255, 153));

        jLabel63.setBackground(new java.awt.Color(225, 135, 44));
        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/per slice cheesee cake.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel63)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        AddSliceCheeseCakeToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddSliceCheeseCakeToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddSliceCheeseCakeToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddSliceCheeseCakeToPanel.setText("ADD");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(225, 135, 44));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Slice CheeseCake");

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddSliceCheeseCakeToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddSliceCheeseCakeToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );

        jPanel39.setBackground(new java.awt.Color(255, 204, 102));

        jPanel40.setBackground(new java.awt.Color(255, 255, 153));

        jLabel68.setBackground(new java.awt.Color(225, 135, 44));
        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b183dd51-ca7d-43cf-a2e3-da67038c0f2a (1).jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel68)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel68)
                .addGap(29, 29, 29))
        );

        AddMangoBravoTubToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddMangoBravoTubToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddMangoBravoTubToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddMangoBravoTubToPanel.setText("ADD");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(225, 135, 44));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Mango Bravo Tub");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddMangoBravoTubToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(0, 5, Short.MAX_VALUE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AddMangoBravoTubToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel43.setBackground(new java.awt.Color(255, 204, 102));

        jPanel44.setBackground(new java.awt.Color(255, 255, 153));

        jLabel74.setBackground(new java.awt.Color(225, 135, 44));
        jLabel74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minicupcake.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel74)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        AddMiniCupcakeToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddMiniCupcakeToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddMiniCupcakeToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddMiniCupcakeToPanel.setText("ADD");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(225, 135, 44));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Mini Cupcake");

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddMiniCupcakeToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddMiniCupcakeToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel47.setBackground(new java.awt.Color(255, 204, 102));

        jPanel48.setBackground(new java.awt.Color(255, 255, 153));

        jLabel78.setBackground(new java.awt.Color(225, 135, 44));
        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cupcake.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel78)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                .addContainerGap())
        );

        AddPiscesCupcakeToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddPiscesCupcakeToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddPiscesCupcakeToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddPiscesCupcakeToPanel.setText("ADD");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(225, 135, 44));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Pisces Cupcake");

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddPiscesCupcakeToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddPiscesCupcakeToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74))
        );

        jPanel55.setBackground(new java.awt.Color(255, 204, 102));

        jPanel56.setBackground(new java.awt.Color(255, 255, 153));

        jLabel67.setBackground(new java.awt.Color(225, 135, 44));
        jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Icing cake.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel67)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AddIcingCustomCakeToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddIcingCustomCakeToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddIcingCustomCakeToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddIcingCustomCakeToPanel.setText("ADD");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(225, 135, 44));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Icing Custom Cake");

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddIcingCustomCakeToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel55Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddIcingCustomCakeToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel84.setBackground(new java.awt.Color(255, 204, 102));

        jPanel85.setBackground(new java.awt.Color(255, 255, 153));

        jLabel70.setBackground(new java.awt.Color(225, 135, 44));
        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minimalist.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel85Layout = new javax.swing.GroupLayout(jPanel85);
        jPanel85.setLayout(jPanel85Layout);
        jPanel85Layout.setHorizontalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel85Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel70)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel85Layout.setVerticalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel85Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        AddMinimalistCustomCakeToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddMinimalistCustomCakeToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddMinimalistCustomCakeToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddMinimalistCustomCakeToPanel.setText("ADD");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(225, 135, 44));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Minimalist Custom");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(225, 135, 44));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Cake");

        javax.swing.GroupLayout jPanel84Layout = new javax.swing.GroupLayout(jPanel84);
        jPanel84.setLayout(jPanel84Layout);
        jPanel84Layout.setHorizontalGroup(
            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel84Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddMinimalistCustomCakeToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel84Layout.setVerticalGroup(
            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel84Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel84Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddMinimalistCustomCakeToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel88.setBackground(new java.awt.Color(255, 204, 102));

        jPanel89.setBackground(new java.awt.Color(255, 255, 153));

        jLabel77.setBackground(new java.awt.Color(225, 135, 44));
        jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Fondant.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel89Layout = new javax.swing.GroupLayout(jPanel89);
        jPanel89.setLayout(jPanel89Layout);
        jPanel89Layout.setHorizontalGroup(
            jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel89Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel77)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel89Layout.setVerticalGroup(
            jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel89Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        AddFondantCustomCakeToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddFondantCustomCakeToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddFondantCustomCakeToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddFondantCustomCakeToPanel.setText("ADD");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(225, 135, 44));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Fondant");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(225, 135, 44));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Custom Cake");

        javax.swing.GroupLayout jPanel88Layout = new javax.swing.GroupLayout(jPanel88);
        jPanel88.setLayout(jPanel88Layout);
        jPanel88Layout.setHorizontalGroup(
            jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel88Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel89, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddFondantCustomCakeToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel88Layout.setVerticalGroup(
            jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel88Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel88Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddFondantCustomCakeToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel89, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98))
        );

        jPanel92.setBackground(new java.awt.Color(255, 204, 102));

        jPanel93.setBackground(new java.awt.Color(255, 255, 153));

        jLabel73.setBackground(new java.awt.Color(225, 135, 44));
        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/YemaCake.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel93Layout = new javax.swing.GroupLayout(jPanel93);
        jPanel93.setLayout(jPanel93Layout);
        jPanel93Layout.setHorizontalGroup(
            jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel93Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel73)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel93Layout.setVerticalGroup(
            jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel93Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        AddYemaCakeToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddYemaCakeToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddYemaCakeToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddYemaCakeToPanel.setText("ADD");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(225, 135, 44));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Yema Cake");

        javax.swing.GroupLayout jPanel92Layout = new javax.swing.GroupLayout(jPanel92);
        jPanel92.setLayout(jPanel92Layout);
        jPanel92Layout.setHorizontalGroup(
            jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel92Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddYemaCakeToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel92Layout.setVerticalGroup(
            jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel92Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel92Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddYemaCakeToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel93, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout cakesPanelLayout = new javax.swing.GroupLayout(cakesPanel);
        cakesPanel.setLayout(cakesPanelLayout);
        cakesPanelLayout.setHorizontalGroup(
            cakesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cakesPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(cakesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(cakesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(cakesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cakesPanelLayout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(cakesPanelLayout.createSequentialGroup()
                        .addGroup(cakesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(24, Short.MAX_VALUE))))
        );
        cakesPanelLayout.setVerticalGroup(
            cakesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cakesPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(cakesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(cakesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel51, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(cakesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(cakesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cakesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel88, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(cakesPanel);

        javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
        jPanel71.setLayout(jPanel71Layout);
        jPanel71Layout.setHorizontalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
        );
        jPanel71Layout.setVerticalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1178, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("cakes", jPanel71);

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        breadsandsweetsPanel.setBackground(new java.awt.Color(255, 255, 153));

        jPanel59.setBackground(new java.awt.Color(255, 204, 102));

        jPanel60.setBackground(new java.awt.Color(255, 255, 153));

        jLabel48.setBackground(new java.awt.Color(225, 135, 44));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Ube Pandesal.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel61.setBackground(new java.awt.Color(255, 204, 102));

        btnMinusUbePandesal.setBackground(new java.awt.Color(255, 204, 102));
        btnMinusUbePandesal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMinusUbePandesal.setForeground(new java.awt.Color(225, 135, 44));
        btnMinusUbePandesal.setText("-");
        btnMinusUbePandesal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusUbePandesalActionPerformed(evt);
            }
        });

        lbzeroUbePandesal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbzeroUbePandesal.setForeground(new java.awt.Color(225, 135, 44));
        lbzeroUbePandesal.setText("0");

        btnPlusUbePandesal.setBackground(new java.awt.Color(255, 204, 102));
        btnPlusUbePandesal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPlusUbePandesal.setForeground(new java.awt.Color(225, 135, 44));
        btnPlusUbePandesal.setText("+");
        btnPlusUbePandesal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusUbePandesalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMinusUbePandesal, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(lbzeroUbePandesal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPlusUbePandesal, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMinusUbePandesal)
                    .addComponent(lbzeroUbePandesal)
                    .addComponent(btnPlusUbePandesal))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel62.setBackground(new java.awt.Color(255, 255, 153));

        lbPhpUbePandesal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbPhpUbePandesal.setForeground(new java.awt.Color(225, 135, 44));
        lbPhpUbePandesal.setText("Php: 0.00");

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel62Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbPhpUbePandesal)
                .addContainerGap())
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel62Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbPhpUbePandesal)
                .addContainerGap())
        );

        btnUbeCheesedesal.setBackground(new java.awt.Color(255, 255, 153));
        btnUbeCheesedesal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUbeCheesedesal.setForeground(new java.awt.Color(225, 135, 44));
        btnUbeCheesedesal.setText("Ube Cheesedesal");

        btnUbeHalayaCheesedesal.setBackground(new java.awt.Color(255, 255, 153));
        btnUbeHalayaCheesedesal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUbeHalayaCheesedesal.setForeground(new java.awt.Color(225, 135, 44));
        btnUbeHalayaCheesedesal.setText("Ube  Halaya Cheesedesal");

        ubePandesalRadio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ubePandesalRadio.setForeground(new java.awt.Color(225, 135, 44));
        ubePandesalRadio.setText("Ube Pandesal");

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUbeCheesedesal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel59Layout.createSequentialGroup()
                        .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ubePandesalRadio)
                            .addGroup(jPanel59Layout.createSequentialGroup()
                                .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnUbeHalayaCheesedesal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ubePandesalRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel59Layout.createSequentialGroup()
                        .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUbeCheesedesal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUbeHalayaCheesedesal)
                .addGap(10, 10, 10))
        );

        jPanel63.setBackground(new java.awt.Color(255, 204, 102));

        jPanel64.setBackground(new java.awt.Color(255, 255, 153));

        jLabel62.setBackground(new java.awt.Color(225, 135, 44));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Banana loaf.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel64Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel62)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel65.setBackground(new java.awt.Color(255, 204, 102));

        btnMinusBananaLoaf.setBackground(new java.awt.Color(255, 204, 102));
        btnMinusBananaLoaf.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMinusBananaLoaf.setForeground(new java.awt.Color(225, 135, 44));
        btnMinusBananaLoaf.setText("-");
        btnMinusBananaLoaf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusBananaLoafActionPerformed(evt);
            }
        });

        lbzeroBananaLoaf.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbzeroBananaLoaf.setForeground(new java.awt.Color(225, 135, 44));
        lbzeroBananaLoaf.setText("0");

        btnPlusBananaLoaf.setBackground(new java.awt.Color(255, 204, 102));
        btnPlusBananaLoaf.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPlusBananaLoaf.setForeground(new java.awt.Color(225, 135, 44));
        btnPlusBananaLoaf.setText("+");
        btnPlusBananaLoaf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusBananaLoafActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMinusBananaLoaf, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(lbzeroBananaLoaf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPlusBananaLoaf, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMinusBananaLoaf)
                    .addComponent(lbzeroBananaLoaf)
                    .addComponent(btnPlusBananaLoaf))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel66.setBackground(new java.awt.Color(255, 255, 153));

        lbPhpBananaLoaf.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbPhpBananaLoaf.setForeground(new java.awt.Color(225, 135, 44));
        lbPhpBananaLoaf.setText("Php: 0.00");

        javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
        jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel66Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbPhpBananaLoaf)
                .addContainerGap())
        );
        jPanel66Layout.setVerticalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel66Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbPhpBananaLoaf)
                .addContainerGap())
        );

        btnMoistBananaChocoChipLoaf.setBackground(new java.awt.Color(255, 255, 153));
        btnMoistBananaChocoChipLoaf.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMoistBananaChocoChipLoaf.setForeground(new java.awt.Color(225, 135, 44));
        btnMoistBananaChocoChipLoaf.setText("Moist Banana Choco Chip Loaf");

        btnMoistBananaChocoChipAlmondLoaf.setBackground(new java.awt.Color(255, 255, 153));
        btnMoistBananaChocoChipAlmondLoaf.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMoistBananaChocoChipAlmondLoaf.setForeground(new java.awt.Color(225, 135, 44));
        btnMoistBananaChocoChipAlmondLoaf.setText("Moist Banana Choco Chip Almond Loaf");

        BananaLoafRadio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BananaLoafRadio.setForeground(new java.awt.Color(225, 135, 44));
        BananaLoafRadio.setText("Banana Loaf");

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BananaLoafRadio)
                    .addGroup(jPanel63Layout.createSequentialGroup()
                        .addComponent(jPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnMoistBananaChocoChipAlmondLoaf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMoistBananaChocoChipLoaf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BananaLoafRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel63Layout.createSequentialGroup()
                        .addComponent(jPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMoistBananaChocoChipLoaf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMoistBananaChocoChipAlmondLoaf)
                .addGap(10, 10, 10))
        );

        jPanel67.setBackground(new java.awt.Color(255, 204, 102));

        jPanel68.setBackground(new java.awt.Color(255, 255, 153));

        jLabel86.setBackground(new java.awt.Color(225, 135, 44));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Brownies.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel69.setBackground(new java.awt.Color(255, 204, 102));

        btnMinusBrownies.setBackground(new java.awt.Color(255, 204, 102));
        btnMinusBrownies.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMinusBrownies.setForeground(new java.awt.Color(225, 135, 44));
        btnMinusBrownies.setText("-");
        btnMinusBrownies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusBrowniesActionPerformed(evt);
            }
        });

        lbzeroBrownies.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbzeroBrownies.setForeground(new java.awt.Color(225, 135, 44));
        lbzeroBrownies.setText("0");

        btnPlusBrownies.setBackground(new java.awt.Color(255, 204, 102));
        btnPlusBrownies.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPlusBrownies.setForeground(new java.awt.Color(225, 135, 44));
        btnPlusBrownies.setText("+");
        btnPlusBrownies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusBrowniesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
        jPanel69.setLayout(jPanel69Layout);
        jPanel69Layout.setHorizontalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel69Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMinusBrownies, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(lbzeroBrownies)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPlusBrownies, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel69Layout.setVerticalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel69Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMinusBrownies)
                    .addComponent(lbzeroBrownies)
                    .addComponent(btnPlusBrownies))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel70.setBackground(new java.awt.Color(255, 255, 153));

        lbPhpBrownies.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbPhpBrownies.setForeground(new java.awt.Color(225, 135, 44));
        lbPhpBrownies.setText("Php: 0.00");

        javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
        jPanel70.setLayout(jPanel70Layout);
        jPanel70Layout.setHorizontalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel70Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbPhpBrownies)
                .addContainerGap())
        );
        jPanel70Layout.setVerticalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel70Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbPhpBrownies)
                .addContainerGap())
        );

        btn6PcsBrownies.setBackground(new java.awt.Color(255, 255, 153));
        btn6PcsBrownies.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn6PcsBrownies.setForeground(new java.awt.Color(225, 135, 44));
        btn6PcsBrownies.setText("6 PCS");

        btn12PcsBrownies.setBackground(new java.awt.Color(255, 255, 153));
        btn12PcsBrownies.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn12PcsBrownies.setForeground(new java.awt.Color(225, 135, 44));
        btn12PcsBrownies.setText("12 PCS");

        browniesRadio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        browniesRadio.setForeground(new java.awt.Color(225, 135, 44));
        browniesRadio.setText("Brownies");

        btn30PcsBrownies.setBackground(new java.awt.Color(255, 255, 153));
        btn30PcsBrownies.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn30PcsBrownies.setForeground(new java.awt.Color(225, 135, 44));
        btn30PcsBrownies.setText("30 PCS");

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(browniesRadio)
                    .addGroup(jPanel67Layout.createSequentialGroup()
                        .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel67Layout.createSequentialGroup()
                        .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn12PcsBrownies, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn6PcsBrownies, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn30PcsBrownies)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel67Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(browniesRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel67Layout.createSequentialGroup()
                        .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn6PcsBrownies)
                    .addComponent(btn30PcsBrownies))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn12PcsBrownies)
                .addGap(10, 10, 10))
        );

        jPanel72.setBackground(new java.awt.Color(255, 204, 102));

        jPanel73.setBackground(new java.awt.Color(255, 255, 153));

        jLabel89.setBackground(new java.awt.Color(225, 135, 44));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Cinnamon.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
        jPanel73.setLayout(jPanel73Layout);
        jPanel73Layout.setHorizontalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel73Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel73Layout.setVerticalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel74.setBackground(new java.awt.Color(255, 204, 102));

        btnMinusClassicCinnamon.setBackground(new java.awt.Color(255, 204, 102));
        btnMinusClassicCinnamon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMinusClassicCinnamon.setForeground(new java.awt.Color(225, 135, 44));
        btnMinusClassicCinnamon.setText("-");
        btnMinusClassicCinnamon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusClassicCinnamonActionPerformed(evt);
            }
        });

        lbzeroClassicCinnamon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbzeroClassicCinnamon.setForeground(new java.awt.Color(225, 135, 44));
        lbzeroClassicCinnamon.setText("0");

        btnPlusClassicCinnamon.setBackground(new java.awt.Color(255, 204, 102));
        btnPlusClassicCinnamon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPlusClassicCinnamon.setForeground(new java.awt.Color(225, 135, 44));
        btnPlusClassicCinnamon.setText("+");
        btnPlusClassicCinnamon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusClassicCinnamonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel74Layout = new javax.swing.GroupLayout(jPanel74);
        jPanel74.setLayout(jPanel74Layout);
        jPanel74Layout.setHorizontalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel74Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMinusClassicCinnamon, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(lbzeroClassicCinnamon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPlusClassicCinnamon, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel74Layout.setVerticalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel74Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMinusClassicCinnamon)
                    .addComponent(lbzeroClassicCinnamon)
                    .addComponent(btnPlusClassicCinnamon))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel75.setBackground(new java.awt.Color(255, 255, 153));

        lbPhpClassicCinnamon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbPhpClassicCinnamon.setForeground(new java.awt.Color(225, 135, 44));
        lbPhpClassicCinnamon.setText("Php: 0.00");

        javax.swing.GroupLayout jPanel75Layout = new javax.swing.GroupLayout(jPanel75);
        jPanel75.setLayout(jPanel75Layout);
        jPanel75Layout.setHorizontalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel75Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbPhpClassicCinnamon)
                .addContainerGap())
        );
        jPanel75Layout.setVerticalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel75Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbPhpClassicCinnamon)
                .addContainerGap())
        );

        btnBoxof4ClassicCinnamon.setBackground(new java.awt.Color(255, 255, 153));
        btnBoxof4ClassicCinnamon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBoxof4ClassicCinnamon.setForeground(new java.awt.Color(225, 135, 44));
        btnBoxof4ClassicCinnamon.setText("Box of 4");

        btnBoxof6ClassicCinnamon.setBackground(new java.awt.Color(255, 255, 153));
        btnBoxof6ClassicCinnamon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBoxof6ClassicCinnamon.setForeground(new java.awt.Color(225, 135, 44));
        btnBoxof6ClassicCinnamon.setText("Box of 6");

        classicCinnamonRadio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        classicCinnamonRadio.setForeground(new java.awt.Color(225, 135, 44));
        classicCinnamonRadio.setText("Classic Cinnamon Roll");

        javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
        jPanel72.setLayout(jPanel72Layout);
        jPanel72Layout.setHorizontalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel72Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(classicCinnamonRadio)
                    .addGroup(jPanel72Layout.createSequentialGroup()
                        .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel72Layout.createSequentialGroup()
                        .addComponent(btnBoxof4ClassicCinnamon, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBoxof6ClassicCinnamon)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel72Layout.setVerticalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel72Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(classicCinnamonRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel72Layout.createSequentialGroup()
                        .addComponent(jPanel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBoxof6ClassicCinnamon)
                    .addComponent(btnBoxof4ClassicCinnamon))
                .addGap(27, 27, 27))
        );

        jPanel76.setBackground(new java.awt.Color(255, 204, 102));

        jPanel77.setBackground(new java.awt.Color(255, 255, 153));

        jLabel92.setBackground(new java.awt.Color(225, 135, 44));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Milky donut.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel77Layout = new javax.swing.GroupLayout(jPanel77);
        jPanel77.setLayout(jPanel77Layout);
        jPanel77Layout.setHorizontalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel77Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel77Layout.setVerticalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel78.setBackground(new java.awt.Color(255, 204, 102));

        btnMinusMilkyDonut.setBackground(new java.awt.Color(255, 204, 102));
        btnMinusMilkyDonut.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMinusMilkyDonut.setForeground(new java.awt.Color(225, 135, 44));
        btnMinusMilkyDonut.setText("-");
        btnMinusMilkyDonut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusMilkyDonutActionPerformed(evt);
            }
        });

        lbzeroMilkyDonut.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbzeroMilkyDonut.setForeground(new java.awt.Color(225, 135, 44));
        lbzeroMilkyDonut.setText("0");

        btnPlusMilkyDonut.setBackground(new java.awt.Color(255, 204, 102));
        btnPlusMilkyDonut.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPlusMilkyDonut.setForeground(new java.awt.Color(225, 135, 44));
        btnPlusMilkyDonut.setText("+");
        btnPlusMilkyDonut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusMilkyDonutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel78Layout = new javax.swing.GroupLayout(jPanel78);
        jPanel78.setLayout(jPanel78Layout);
        jPanel78Layout.setHorizontalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel78Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMinusMilkyDonut, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(lbzeroMilkyDonut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPlusMilkyDonut, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel78Layout.setVerticalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel78Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMinusMilkyDonut)
                    .addComponent(lbzeroMilkyDonut)
                    .addComponent(btnPlusMilkyDonut))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel79.setBackground(new java.awt.Color(255, 255, 153));

        lbPhpMilkyDonut.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbPhpMilkyDonut.setForeground(new java.awt.Color(225, 135, 44));
        lbPhpMilkyDonut.setText("Php: 0.00");

        javax.swing.GroupLayout jPanel79Layout = new javax.swing.GroupLayout(jPanel79);
        jPanel79.setLayout(jPanel79Layout);
        jPanel79Layout.setHorizontalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel79Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbPhpMilkyDonut)
                .addContainerGap())
        );
        jPanel79Layout.setVerticalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel79Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbPhpMilkyDonut)
                .addContainerGap())
        );

        btn5PcsMilkyDonut.setBackground(new java.awt.Color(255, 255, 153));
        btn5PcsMilkyDonut.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn5PcsMilkyDonut.setForeground(new java.awt.Color(225, 135, 44));
        btn5PcsMilkyDonut.setText("5 PCS");

        btn10PcsMilkyDonut.setBackground(new java.awt.Color(255, 255, 153));
        btn10PcsMilkyDonut.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn10PcsMilkyDonut.setForeground(new java.awt.Color(225, 135, 44));
        btn10PcsMilkyDonut.setText("10 PCS");

        milkyCheeseRadio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        milkyCheeseRadio.setForeground(new java.awt.Color(225, 135, 44));
        milkyCheeseRadio.setText("Milky Cheese Donut");

        javax.swing.GroupLayout jPanel76Layout = new javax.swing.GroupLayout(jPanel76);
        jPanel76.setLayout(jPanel76Layout);
        jPanel76Layout.setHorizontalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel76Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(milkyCheeseRadio)
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addComponent(jPanel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addComponent(btn5PcsMilkyDonut, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn10PcsMilkyDonut)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel76Layout.setVerticalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel76Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(milkyCheeseRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addComponent(jPanel78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn10PcsMilkyDonut)
                    .addComponent(btn5PcsMilkyDonut))
                .addGap(27, 27, 27))
        );

        jPanel80.setBackground(new java.awt.Color(255, 204, 102));

        jPanel81.setBackground(new java.awt.Color(255, 255, 153));

        jLabel95.setBackground(new java.awt.Color(225, 135, 44));
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Banana Muffin.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel81Layout = new javax.swing.GroupLayout(jPanel81);
        jPanel81.setLayout(jPanel81Layout);
        jPanel81Layout.setHorizontalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel81Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel81Layout.setVerticalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel81Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel82.setBackground(new java.awt.Color(255, 204, 102));

        btnMinuMuffin.setBackground(new java.awt.Color(255, 204, 102));
        btnMinuMuffin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMinuMuffin.setForeground(new java.awt.Color(225, 135, 44));
        btnMinuMuffin.setText("-");
        btnMinuMuffin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinuMuffinActionPerformed(evt);
            }
        });

        lbzeroMuffin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbzeroMuffin.setForeground(new java.awt.Color(225, 135, 44));
        lbzeroMuffin.setText("0");

        btnPlusMuffin.setBackground(new java.awt.Color(255, 204, 102));
        btnPlusMuffin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPlusMuffin.setForeground(new java.awt.Color(225, 135, 44));
        btnPlusMuffin.setText("+");
        btnPlusMuffin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusMuffinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel82Layout = new javax.swing.GroupLayout(jPanel82);
        jPanel82.setLayout(jPanel82Layout);
        jPanel82Layout.setHorizontalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel82Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMinuMuffin, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(lbzeroMuffin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPlusMuffin, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel82Layout.setVerticalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel82Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMinuMuffin)
                    .addComponent(lbzeroMuffin)
                    .addComponent(btnPlusMuffin))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel83.setBackground(new java.awt.Color(255, 255, 153));

        lbPhpMuffin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbPhpMuffin.setForeground(new java.awt.Color(225, 135, 44));
        lbPhpMuffin.setText("Php: 0.00");

        javax.swing.GroupLayout jPanel83Layout = new javax.swing.GroupLayout(jPanel83);
        jPanel83.setLayout(jPanel83Layout);
        jPanel83Layout.setHorizontalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel83Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbPhpMuffin)
                .addContainerGap())
        );
        jPanel83Layout.setVerticalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel83Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbPhpMuffin)
                .addContainerGap())
        );

        bananaMuffinRadio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bananaMuffinRadio.setForeground(new java.awt.Color(225, 135, 44));
        bananaMuffinRadio.setText("Banana Muffin");

        btn6PcsBananaMuffin.setBackground(new java.awt.Color(255, 255, 153));
        btn6PcsBananaMuffin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn6PcsBananaMuffin.setForeground(new java.awt.Color(225, 135, 44));
        btn6PcsBananaMuffin.setText("6 PCS");

        javax.swing.GroupLayout jPanel80Layout = new javax.swing.GroupLayout(jPanel80);
        jPanel80.setLayout(jPanel80Layout);
        jPanel80Layout.setHorizontalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel80Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bananaMuffinRadio)
                    .addGroup(jPanel80Layout.createSequentialGroup()
                        .addComponent(jPanel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn6PcsBananaMuffin, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel80Layout.setVerticalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel80Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bananaMuffinRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel80Layout.createSequentialGroup()
                        .addComponent(jPanel82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btn6PcsBananaMuffin)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout breadsandsweetsPanelLayout = new javax.swing.GroupLayout(breadsandsweetsPanel);
        breadsandsweetsPanel.setLayout(breadsandsweetsPanelLayout);
        breadsandsweetsPanelLayout.setHorizontalGroup(
            breadsandsweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, breadsandsweetsPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(breadsandsweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(breadsandsweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(breadsandsweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(215, 215, 215))
        );
        breadsandsweetsPanelLayout.setVerticalGroup(
            breadsandsweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(breadsandsweetsPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(breadsandsweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(breadsandsweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(681, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(breadsandsweetsPanel);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("breads", jPanel11);

        jPanel3.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 20, 930, 730));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 780));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void updateTotalAmountLabel() {
    int total = 0;
    for (int i = 0; i < billListModel.size(); i++) {
        String entry = billListModel.get(i);
        // Find the first occurrence of "₱" after "Qty:"
        int pesoIndex = entry.indexOf("₱");
        if (pesoIndex != -1) {
            // Find the next " |" after "₱" to get only the price
            int endIndex = entry.indexOf("|", pesoIndex);
            String priceStr;
            if (endIndex != -1) {
                priceStr = entry.substring(pesoIndex + 1, endIndex).trim();
            } else {
                priceStr = entry.substring(pesoIndex + 1).trim();
            }
            // Remove commas if price is formatted with them
            priceStr = priceStr.replace(",", "");
            try {
                int itemTotal = Integer.parseInt(priceStr);
                total += itemTotal;
            } catch (NumberFormatException e) {
                // Skip this entry if parsing fails
            }
        }
    }
    totalAmountLabel.setText(String.format("₱%,.2f", (double) total));
}
    
    
    private void addOrUpdateBillItem(String productName, int quantity, int unitPrice, String size, String toppings) {
    if (quantity <= 0) {
        // Remove the item from the billList
        for (int i = 0; i < billListModel.size(); i++) {
            if (billListModel.get(i).startsWith(productName + " |")) {
                billListModel.remove(i);
                break;
            }
        }
        updateTotalAmountLabel();
        return;
    }
    // Remove existing entry (to avoid duplicates)
    for (int i = 0; i < billListModel.size(); i++) {
        if (billListModel.get(i).startsWith(productName + " |")) {
            billListModel.remove(i);
            break;
        }
    }
    // Build the entry string with size and toppings
    String entry = String.format(
        "%s | Qty: %d | ₱%d | Size: %s | Toppings: %s",
        productName, quantity, quantity * unitPrice, size, toppings
    );
    billListModel.addElement(entry);

    updateTotalAmountLabel();
}
    
    
        
    
//    private void updateMangoPrice() {
//    int total = (mangoBasePrice) * mangoQty;
//    mangoPrice.setText("Php: " + total + ".00");
//    }
//    private void updateRedVelvetPrice(){
//       int total = (redVelvetBasePrice) * redVelvetQty;
//       redVelvetCakePrice.setText("Php: " + total + ".00"); 
//    }
//    
//    private void updateStrawberryShortPrice(){
//       int total = (strawberryShortcakeBasePrice) * strawberryShortcakeQty ;
//       strawberryShortCakePrice.setText("Php: " + total + ".00"); 
//    }
//    
//    private void updateBentoCakePrice(){
//        int total = (bentoCakeBasePrice) * bentoCakeQty;
//        bentoCakePrice.setText("Php: " + total + ".00");
//        
//    }
//    private void updateCheeseCakePrice(){
//        int total = (cheesecakeBasePrice) * cheesecakeQuantity;
//        cheeseCakePrice.setText("Php: " + total + ".00");
//    }
//    private void updateCaramelLechePrice(){
//        int total = (caramelFlanBasePrice) * caramelFlanQty;
//        caramelFlanDeLecheCakePrice.setText("Php: " + total + ".00");
//    }
//    private void updateMangoBravoTubPrice(){
//        mangoBravoTubBasePrice = 170;
//        int total = (mangoBravoTubBasePrice) * mangoBravoTubQty;
//        mangoBravoTubPrice.setText("Php: " + total + ".00");
//        
//    }
//    private void updateSliceCheesecakePrice(){
//        slicecheesecakeBasePrice = 175;
//        int total = (slicecheesecakeBasePrice) * slicecheesecakeQty;
//        sliceCheesecakePrice.setText("Php: " + total + ".00");
//        
//    }
//    private void updateMiniCupcakesPrice(){
//        int total = (miniCupcakesBasePrice) * miniCupcakesQty;
//        miniCupcakesPrice.setText("Php: " + total + ".00");
//    }
//    private void updatePiscesCupcakesPrice(){
//        int total = piscesCupcakesBasePrice * piscesCupcakesQty;
//        piscesCupcakesPrice.setText("Php: " + total + ".00");
//    }
//    private void updateYemaCakePrice(){
//        
//        int total = (yemaCakeBasePrice) * yemaCakeQty;
//        lbPhpYemaCake.setText("Php: " + total + ".00");
//        
//    }
//    private void updateCheesePandesalPrice(){
//         int total = (ubePandesalBasePrice) * ubePandesalQty;
//         lbPhpUbePandesal.setText("Php: " + total + ".00");
//    }
//    private void updateBananaLoafPrice(){
//        int total  = (bananaLoafBasePrice)* bananaLoafQty;
//        lbPhpBananaLoaf.setText("Php: " + total + ".00");
//    }
//    private void updateBrowniesPrice(){
//        int total = (browniesBasePrice) * browniesQty;
//        lbPhpBrownies.setText("Php: " + total + ".00");
//    }
//    private void updateCinnamonPrice(){
//        int total = (classicCinnamonQty) * classicCinnamonBasePrice;
//        lbPhpClassicCinnamon.setText("Php: " + total + ".00");
//        
//    }
//    private void updateMilkyCheeseDonutPrice(){
//        int total = (milkyCheeseDonutQty) * milkyCheeseDonutBasePrice;
//        lbPhpMilkyDonut.setText("Php: " + total + ".00");
//    }
//    private void updateBananaMuffinPrice(){
//        int total = (bananaMuffinQty) * bananaMuffinBasePrice;
//        lbPhpMuffin.setText("Php: " + total + ".00");
//    }
    
    
    private void btnCakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCakeActionPerformed
      jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btnCakeActionPerformed

    private void btnBreadSweets1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBreadSweets1ActionPerformed
      jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnBreadSweets1ActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained
       if(txtSearch.getText().equals("Search"))
       {
           txtSearch.setText("");
           txtSearch.setForeground(new Color(153,153,153));
       }            
    }//GEN-LAST:event_txtSearchFocusGained

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
         if(txtSearch.getText().equals(""))
       {
           txtSearch.setText("Search");
           txtSearch.setForeground(new Color(153,153,153));
       }       
    }//GEN-LAST:event_txtSearchFocusLost

    private void btnMinusUbePandesalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusUbePandesalActionPerformed
//       if(ubePandesalQty>0){
//           ubePandesalQty--;
//           lbzeroUbePandesal.setText(String.valueOf(ubePandesalQty));
//           updateCheesePandesalPrice();
//       }
    }//GEN-LAST:event_btnMinusUbePandesalActionPerformed

    private void btnMinusBananaLoafActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusBananaLoafActionPerformed
//        if(bananaLoafQty>0){
//            bananaLoafQty--;
//            lbzeroBananaLoaf.setText(String.valueOf(bananaLoafQty));
//            updateBananaLoafPrice();
//        }
    }//GEN-LAST:event_btnMinusBananaLoafActionPerformed

    private void btnMinusBrowniesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusBrowniesActionPerformed
//       if(browniesQty>0){
//           browniesQty--;
//           lbzeroBrownies.setText(String.valueOf(browniesQty));
//           updateBrowniesPrice();
//       }
    }//GEN-LAST:event_btnMinusBrowniesActionPerformed

    private void btnMinusClassicCinnamonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusClassicCinnamonActionPerformed
//        if(classicCinnamonQty>0){
//            classicCinnamonQty--;
//            lbzeroClassicCinnamon.setText(String.valueOf(classicCinnamonQty));
//            updateCinnamonPrice();
//        }
    }//GEN-LAST:event_btnMinusClassicCinnamonActionPerformed

    private void btnMinusMilkyDonutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusMilkyDonutActionPerformed
//       if(milkyCheeseDonutQty>0){
//           milkyCheeseDonutQty--;
//           lbzeroMilkyDonut.setText(String.valueOf(milkyCheeseDonutQty));
//           updateMilkyCheeseDonutPrice();
//           
//       }
    }//GEN-LAST:event_btnMinusMilkyDonutActionPerformed

    private void btnMinuMuffinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinuMuffinActionPerformed
//        if(bananaMuffinQty>0){
//            bananaMuffinQty--;
//            lbzeroMuffin.setText(String.valueOf(bananaMuffinQty));
//            updateBananaMuffinPrice();
//        }
    }//GEN-LAST:event_btnMinuMuffinActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
 
       setVisible(false);
       Dashboard main = new Dashboard();
       main.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void btnPlusClassicCinnamonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusClassicCinnamonActionPerformed
//        classicCinnamonQty++;
//        lbzeroClassicCinnamon.setText(String.valueOf(classicCinnamonQty));
//        updateCinnamonPrice();
    }//GEN-LAST:event_btnPlusClassicCinnamonActionPerformed

    private void btnPlusUbePandesalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusUbePandesalActionPerformed
//        ubePandesalQty++;
//        lbzeroUbePandesal.setText(String.valueOf(ubePandesalQty));
//        updateCheesePandesalPrice();
    }//GEN-LAST:event_btnPlusUbePandesalActionPerformed

    private void btnPlusBananaLoafActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusBananaLoafActionPerformed
//        bananaLoafQty++;
//        lbzeroBananaLoaf.setText(String.valueOf(bananaLoafQty));
//        updateBananaLoafPrice();
    }//GEN-LAST:event_btnPlusBananaLoafActionPerformed

    private void btnPlusBrowniesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusBrowniesActionPerformed
//        browniesQty++;
//        lbzeroBrownies.setText(String.valueOf(browniesQty));
//        updateBrowniesPrice();
    }//GEN-LAST:event_btnPlusBrowniesActionPerformed

    private void btnPlusMilkyDonutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusMilkyDonutActionPerformed
//        milkyCheeseDonutQty++;
//        lbzeroMilkyDonut.setText(String.valueOf(milkyCheeseDonutQty));
//        updateMilkyCheeseDonutPrice();
    }//GEN-LAST:event_btnPlusMilkyDonutActionPerformed

    private void btnPlusMuffinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusMuffinActionPerformed
//       bananaMuffinQty++;
//       lbzeroMuffin.setText(String.valueOf(bananaMuffinQty));
//       updateBananaMuffinPrice();
    }//GEN-LAST:event_btnPlusMuffinActionPerformed

    private void payByCashBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payByCashBtnActionPerformed
        
    }//GEN-LAST:event_payByCashBtnActionPerformed

    private void AddMangoBravoToPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMangoBravoToPanelActionPerformed
       
    }//GEN-LAST:event_AddMangoBravoToPanelActionPerformed
    




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
            java.util.logging.Logger.getLogger(BeaPOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BeaPOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BeaPOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BeaPOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BeaPOS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBentoCakeToPanel;
    private javax.swing.JButton AddCaramelFlanDeLecheCakeToPanel;
    private javax.swing.JButton AddCheeseCakeToPanel;
    private javax.swing.JButton AddFondantCustomCakeToPanel;
    private javax.swing.JButton AddIcingCustomCakeToPanel;
    private javax.swing.JButton AddMangoBravoToPanel;
    private javax.swing.JButton AddMangoBravoTubToPanel;
    private javax.swing.JButton AddMiniCupcakeToPanel;
    private javax.swing.JButton AddMinimalistCustomCakeToPanel;
    private javax.swing.JButton AddPiscesCupcakeToPanel;
    private javax.swing.JButton AddRedVelvetCakeToPanel;
    private javax.swing.JButton AddSliceCheeseCakeToPanel;
    private javax.swing.JButton AddStrawberryShortCakeToPanel;
    private javax.swing.JButton AddYemaCakeToPanel;
    private javax.swing.JRadioButton BananaLoafRadio;
    private javax.swing.JPanel BillPanel;
    private javax.swing.JButton backButton;
    private javax.swing.JRadioButton bananaMuffinRadio;
    private javax.swing.JLabel billLabel;
    private javax.swing.JList<String> billList;
    private javax.swing.JPanel breadsandsweetsPanel;
    private javax.swing.JRadioButton browniesRadio;
    private javax.swing.JToggleButton btn10PcsMilkyDonut;
    private javax.swing.JToggleButton btn12PcsBrownies;
    private javax.swing.JToggleButton btn30PcsBrownies;
    private javax.swing.JToggleButton btn5PcsMilkyDonut;
    private javax.swing.JToggleButton btn6PcsBananaMuffin;
    private javax.swing.JToggleButton btn6PcsBrownies;
    private javax.swing.JToggleButton btnBoxof4ClassicCinnamon;
    private javax.swing.JToggleButton btnBoxof6ClassicCinnamon;
    private javax.swing.JButton btnBreadSweets1;
    private javax.swing.JButton btnCake;
    private javax.swing.JButton btnMinuMuffin;
    private javax.swing.JButton btnMinusBananaLoaf;
    private javax.swing.JButton btnMinusBrownies;
    private javax.swing.JButton btnMinusClassicCinnamon;
    private javax.swing.JButton btnMinusMilkyDonut;
    private javax.swing.JButton btnMinusUbePandesal;
    private javax.swing.JToggleButton btnMoistBananaChocoChipAlmondLoaf;
    private javax.swing.JToggleButton btnMoistBananaChocoChipLoaf;
    private javax.swing.JButton btnPlusBananaLoaf;
    private javax.swing.JButton btnPlusBrownies;
    private javax.swing.JButton btnPlusClassicCinnamon;
    private javax.swing.JButton btnPlusMilkyDonut;
    private javax.swing.JButton btnPlusMuffin;
    private javax.swing.JButton btnPlusUbePandesal;
    private javax.swing.JToggleButton btnUbeCheesedesal;
    private javax.swing.JToggleButton btnUbeHalayaCheesedesal;
    private javax.swing.JPanel cakesPanel;
    private javax.swing.JRadioButton classicCinnamonRadio;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JPanel jPanel88;
    private javax.swing.JPanel jPanel89;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel92;
    private javax.swing.JPanel jPanel93;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbPhpBananaLoaf;
    private javax.swing.JLabel lbPhpBrownies;
    private javax.swing.JLabel lbPhpClassicCinnamon;
    private javax.swing.JLabel lbPhpMilkyDonut;
    private javax.swing.JLabel lbPhpMuffin;
    private javax.swing.JLabel lbPhpUbePandesal;
    private javax.swing.JLabel lbzeroBananaLoaf;
    private javax.swing.JLabel lbzeroBrownies;
    private javax.swing.JLabel lbzeroClassicCinnamon;
    private javax.swing.JLabel lbzeroMilkyDonut;
    private javax.swing.JLabel lbzeroMuffin;
    private javax.swing.JLabel lbzeroUbePandesal;
    private javax.swing.JRadioButton milkyCheeseRadio;
    private javax.swing.JToggleButton payByCashBtn;
    private javax.swing.JToggleButton payByGCASHBtn;
    private javax.swing.JPanel productsPanel;
    private javax.swing.JLabel totalAmountLabel;
    private javax.swing.JLabel totalstaticlabel;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JRadioButton ubePandesalRadio;
    // End of variables declaration//GEN-END:variables
}
