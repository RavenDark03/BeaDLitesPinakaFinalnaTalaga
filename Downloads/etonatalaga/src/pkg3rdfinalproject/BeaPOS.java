/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pkg3rdfinalproject;


import java.awt.Color;
import java.sql.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;

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
    
//private void saveOrderToDatabase() {
//    double orderTotal = parseTotalAmountLabel();
//    StringBuilder productNames = new StringBuilder();
//    StringBuilder productQuantities = new StringBuilder();
//    StringBuilder productToppings = new StringBuilder();
//
//    for (int i = 0; i < billListModel.size(); i++) {
//        String entry = billListModel.get(i);
//        String[] parts = entry.split("\\|");
//
//        // Product Name
//        String productName = parts[0].trim();
//
//        // Quantity
//        String quantity = (parts.length > 1) ? parts[1].replace("Qty:", "").trim() : "1";
//
//        // Toppings: search for "Toppings:" in whole entry
//        String toppings = "None";
//        int toppingsIndex = entry.indexOf("Toppings:");
//        if (toppingsIndex != -1) {
//            toppings = entry.substring(toppingsIndex + "Toppings:".length()).trim();
//            // If there are further fields after toppings, only take up to next " |" or end
//            int nextField = toppings.indexOf("|");
//            if (nextField != -1) {
//                toppings = toppings.substring(0, nextField).trim();
//            }
//            if (toppings.isEmpty()) toppings = "None";
//        }
//
//        // Only append commas if not the first product
//        if (productNames.length() > 0) {
//            productNames.append(",");
//            productQuantities.append(",");
//            productToppings.append(",");
//        }
//        productNames.append(productName);
//        productQuantities.append(quantity);
//        productToppings.append(toppings);
//    }
//
//    String productNamesString = productNames.toString();
//    String productQuantitiesString = productQuantities.toString();
//    String productToppingsString = productToppings.toString();
//
//    try (Connection conn = getConnection()) {
//        String orderSql = "INSERT INTO orders (customer_name, customer_email, customer_contact, order_total, order_date, payment_method, product_names, product_quantities, toppings) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        PreparedStatement orderStmt = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
//        orderStmt.setString(1, savedCustomerName);
//        orderStmt.setString(2, savedCustomerEmail);
//        orderStmt.setString(3, savedCustomerContact);
//        orderStmt.setDouble(4, orderTotal);
//        orderStmt.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
//        orderStmt.setString(6, lastPaymentMethod);
//        orderStmt.setString(7, productNamesString);
//        orderStmt.setString(8, productQuantitiesString);
//        orderStmt.setString(9, productToppingsString);
//        orderStmt.executeUpdate();
//        // rest of your code
//    } catch (SQLException ex) {
//        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
//    }
//}
   
   private void resetCart() {
        billListModel.clear();
        
        savedCustomerName = null;
        savedCustomerEmail = null;
        savedCustomerContact = null;
        lastPaymentMethod = null;
    }
   private static final Map<String, Integer> PRODUCT_PRICE_MAP = new HashMap<>();
    static {
        // Mango Bravo
        PRODUCT_PRICE_MAP.put("Mango Bravo|6x2", 699);
        PRODUCT_PRICE_MAP.put("Mango Bravo|5x2", 399);
        PRODUCT_PRICE_MAP.put("Mango Bravo|4x2", 299);

        // Red Velvet
        PRODUCT_PRICE_MAP.put("Red Velvet|6x2", 799);
        PRODUCT_PRICE_MAP.put("Red Velvet|5x2", 499);

        // Strawberry Shortcake
        PRODUCT_PRICE_MAP.put("Strawberry Shortcake|4x3", 299);
        PRODUCT_PRICE_MAP.put("Strawberry Shortcake|5x3", 499);
        PRODUCT_PRICE_MAP.put("Strawberry Shortcake|6x3", 699);
        PRODUCT_PRICE_MAP.put("Strawberry Shortcake|8x3", 999);

        // Bento Cake
        PRODUCT_PRICE_MAP.put("Bento Cake|4x2 + 2pcs Cupcake", 340);

        // Cheesecake
        PRODUCT_PRICE_MAP.put("Cheesecake|8", 1400);
        PRODUCT_PRICE_MAP.put("Slice Cheesecake|1 Tub", 170);

        // Caramel de Leche Flan
        PRODUCT_PRICE_MAP.put("Caramel de Leche Flan|6x2", 699);
        PRODUCT_PRICE_MAP.put("Caramel de Leche Flan|5x2", 399);
        PRODUCT_PRICE_MAP.put("Caramel de Leche Flan|4x2", 299);

        // Mango Bravo Tub
        PRODUCT_PRICE_MAP.put("Mango Bravo Tub|1 Tub", 170);

        // Mini Cupcakes
        PRODUCT_PRICE_MAP.put("Mini Cupcakes|6pcs", 98);
        PRODUCT_PRICE_MAP.put("Mini Cupcakes|12pcs", 169);

        // Pisces Cupcakes
        PRODUCT_PRICE_MAP.put("Pisces Cupcakes|1pc", 55);
        PRODUCT_PRICE_MAP.put("Pisces Cupcakes|3pcs", 99);
        PRODUCT_PRICE_MAP.put("Pisces Cupcakes|4pcs", 149);
        PRODUCT_PRICE_MAP.put("Pisces Cupcakes|6pcs", 300);
        PRODUCT_PRICE_MAP.put("Pisces Cupcakes|12pcs", 600);

        // Yema Cake
        PRODUCT_PRICE_MAP.put("Yema Cake|6x2", 699);
        PRODUCT_PRICE_MAP.put("Yema Cake|5x2", 399);
        PRODUCT_PRICE_MAP.put("Yema Cake|4x2", 299);

        // Ube Pandesal
        PRODUCT_PRICE_MAP.put("Ube Pandesal|Ube Cheesedesal", 170);
        PRODUCT_PRICE_MAP.put("Ube Pandesal|Ube Halaya Cheesedesal", 185);

        // Banana Loaf
        PRODUCT_PRICE_MAP.put("Banana Loaf|Moist Banana Choco Chip Loaf", 160);
        PRODUCT_PRICE_MAP.put("Banana Loaf|Moist Banana Choco Chip Almond Loaf", 190);

        // Brownies
        PRODUCT_PRICE_MAP.put("Brownies|6pcs", 150);
        PRODUCT_PRICE_MAP.put("Brownies|12pcs", 280);
        PRODUCT_PRICE_MAP.put("Brownies|30pcs", 750);

        // Classic Cinnamon
        PRODUCT_PRICE_MAP.put("Classic Cinnamon|Box of 4", 185);
        PRODUCT_PRICE_MAP.put("Classic Cinnamon|Box of 6", 280);

        // Milky Cheese Donut
        PRODUCT_PRICE_MAP.put("Milky Cheese Donut|5pcs", 125);
        PRODUCT_PRICE_MAP.put("Milky Cheese Donut|10pcs", 240);

        // Banana Muffin
        PRODUCT_PRICE_MAP.put("Banana Muffin|6pcs", 109);
    }

    // Lookup method
    public static int getProductPrice(String productName, String sizeOrVariant) {
        Integer price = PRODUCT_PRICE_MAP.get(productName + "|" + sizeOrVariant);
        return price != null ? price : 0;
    }
    
    private static final Map<String, String[]> PRODUCT_SIZES = new HashMap<>();
    private static final Map<String, String[]> PRODUCT_TOPPINGS = new HashMap<>();
    static {
        PRODUCT_SIZES.put("Mango Bravo", new String[]{"6x2", "5x2", "4x2"});
    // ...more products
        PRODUCT_TOPPINGS.put("Mango Bravo", new String[]{"Mango Toppings"});
    // ...more products
}
   
    
    
    
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
    
//    saveOrderToDatabase();
    
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
   
//    saveOrderToDatabase();
    
    savedCustomerName = null;
    savedCustomerEmail = null;
    savedCustomerContact = null;
    lastPaymentMethod = null;
    billListModel.clear();
    resetCart();
    setTotalAmount();
});

    }  


    
    
    
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
        iconPanel = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        AddMangoBravoToPanel = new javax.swing.JButton();
        MangoBravolabel = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        AddRedVelvetCakeToPanel = new javax.swing.JButton();
        RedVelvetCakelabel = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        AddStrawberryShortCakeToPanel = new javax.swing.JButton();
        StrawberryShortCakelabel = new javax.swing.JLabel();
        jPanel51 = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        AddBentoCakeToPanel = new javax.swing.JButton();
        BentoCakelabel = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        AddCaramelFlanDeLecheCakeToPanel = new javax.swing.JButton();
        Caramelflandelechecakelabel = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        AddCheeseCakeToPanel = new javax.swing.JButton();
        CheeseCakelabel = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        AddSliceCheeseCakeToPanel = new javax.swing.JButton();
        SliceCheeseCakelabel = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        AddMangoBravoTubToPanel = new javax.swing.JButton();
        MangoBravoTublabel = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        AddMiniCupcakeToPanel = new javax.swing.JButton();
        MiniCupcakelabel = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        AddPiscesCupcakeToPanel = new javax.swing.JButton();
        PiscesCupcakelabel = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        AddIcingCustomCakeToPanel = new javax.swing.JButton();
        IcingCustomCakelabel = new javax.swing.JLabel();
        jPanel84 = new javax.swing.JPanel();
        jPanel85 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        AddMinimalistCustomCakeToPanel = new javax.swing.JButton();
        MinimalistCustomCakelabel = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel88 = new javax.swing.JPanel();
        jPanel89 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        AddFondantCustomCakeToPanel = new javax.swing.JButton();
        FondantCustomCakelabel = new javax.swing.JLabel();
        jPanel92 = new javax.swing.JPanel();
        jPanel93 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        AddYemaCakeToPanel = new javax.swing.JButton();
        YemaCakelabel = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        breadsandsweetsPanel = new javax.swing.JPanel();
        jPanel59 = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        AddUbeCheesedesalToPanel = new javax.swing.JButton();
        UbeCheesedesallabel = new javax.swing.JLabel();
        jPanel63 = new javax.swing.JPanel();
        jPanel64 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        AddBananaLoafToPanel = new javax.swing.JButton();
        BananaLoaflabel = new javax.swing.JLabel();
        jPanel67 = new javax.swing.JPanel();
        jPanel68 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        AddBrowniesToPanel = new javax.swing.JButton();
        Brownieslabel = new javax.swing.JLabel();
        jPanel72 = new javax.swing.JPanel();
        jPanel73 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        AddClassicCinnamonRollToPanel = new javax.swing.JButton();
        ClassicCinnamonlabel = new javax.swing.JLabel();
        jPanel76 = new javax.swing.JPanel();
        jPanel77 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        AddMilkyCheeseDonutToPanel = new javax.swing.JButton();
        MilkyCheeseDonutlabel = new javax.swing.JLabel();
        Donut = new javax.swing.JLabel();
        jPanel80 = new javax.swing.JPanel();
        jPanel81 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        AddBananaMuffinToPanel = new javax.swing.JButton();
        BananaMuffinlabel = new javax.swing.JLabel();

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

        iconPanel.setBackground(new java.awt.Color(255, 255, 153));

        jPanel18.setBackground(new java.awt.Color(255, 255, 153));

        jLabel46.setBackground(new java.awt.Color(225, 135, 44));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Mango bravo.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout iconPanelLayout = new javax.swing.GroupLayout(iconPanel);
        iconPanel.setLayout(iconPanelLayout);
        iconPanelLayout.setHorizontalGroup(
            iconPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iconPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        iconPanelLayout.setVerticalGroup(
            iconPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iconPanelLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
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

        MangoBravolabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        MangoBravolabel.setForeground(new java.awt.Color(225, 135, 44));
        MangoBravolabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MangoBravolabel.setText("Mango Bravo");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MangoBravolabel, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(AddMangoBravoToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(MangoBravolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddMangoBravoToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(iconPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        AddRedVelvetCakeToPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRedVelvetCakeToPanelActionPerformed(evt);
            }
        });

        RedVelvetCakelabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        RedVelvetCakelabel.setForeground(new java.awt.Color(225, 135, 44));
        RedVelvetCakelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RedVelvetCakelabel.setText("RedVelvet Cake");

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
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(RedVelvetCakelabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(RedVelvetCakelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddRedVelvetCakeToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        AddStrawberryShortCakeToPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddStrawberryShortCakeToPanelActionPerformed(evt);
            }
        });

        StrawberryShortCakelabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        StrawberryShortCakelabel.setForeground(new java.awt.Color(225, 135, 44));
        StrawberryShortCakelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StrawberryShortCakelabel.setText("<html>Strawberry<br>ShortCake</html>");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StrawberryShortCakelabel)
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
                        .addComponent(StrawberryShortCakelabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        AddBentoCakeToPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBentoCakeToPanelActionPerformed(evt);
            }
        });

        BentoCakelabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BentoCakelabel.setForeground(new java.awt.Color(225, 135, 44));
        BentoCakelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BentoCakelabel.setText("Bento Cake");

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addComponent(BentoCakelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addComponent(AddBentoCakeToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addComponent(BentoCakelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        Caramelflandelechecakelabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Caramelflandelechecakelabel.setForeground(new java.awt.Color(225, 135, 44));
        Caramelflandelechecakelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Caramelflandelechecakelabel.setText("<html>Caramel flan de<br>leche cake</html> ");

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
                    .addComponent(Caramelflandelechecakelabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(Caramelflandelechecakelabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        CheeseCakelabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CheeseCakelabel.setForeground(new java.awt.Color(225, 135, 44));
        CheeseCakelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CheeseCakelabel.setText("CheeseCake");

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
                    .addComponent(CheeseCakelabel, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(CheeseCakelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        SliceCheeseCakelabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SliceCheeseCakelabel.setForeground(new java.awt.Color(225, 135, 44));
        SliceCheeseCakelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SliceCheeseCakelabel.setText("Slice CheeseCake");

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
                    .addComponent(SliceCheeseCakelabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(SliceCheeseCakelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        MangoBravoTublabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        MangoBravoTublabel.setForeground(new java.awt.Color(225, 135, 44));
        MangoBravoTublabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MangoBravoTublabel.setText("Mango Bravo Tub");

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
                    .addComponent(MangoBravoTublabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(0, 5, Short.MAX_VALUE)
                        .addComponent(MangoBravoTublabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        MiniCupcakelabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        MiniCupcakelabel.setForeground(new java.awt.Color(225, 135, 44));
        MiniCupcakelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MiniCupcakelabel.setText("Mini Cupcake");

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
                    .addComponent(MiniCupcakelabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(MiniCupcakelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        PiscesCupcakelabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PiscesCupcakelabel.setForeground(new java.awt.Color(225, 135, 44));
        PiscesCupcakelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PiscesCupcakelabel.setText("Pisces Cupcake");

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
                    .addComponent(PiscesCupcakelabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(PiscesCupcakelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        IcingCustomCakelabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        IcingCustomCakelabel.setForeground(new java.awt.Color(225, 135, 44));
        IcingCustomCakelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        IcingCustomCakelabel.setText("Icing Custom Cake");

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
                    .addComponent(IcingCustomCakelabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel55Layout.createSequentialGroup()
                        .addComponent(IcingCustomCakelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        MinimalistCustomCakelabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        MinimalistCustomCakelabel.setForeground(new java.awt.Color(225, 135, 44));
        MinimalistCustomCakelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MinimalistCustomCakelabel.setText("Minimalist Custom");

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
                    .addComponent(MinimalistCustomCakelabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel84Layout.setVerticalGroup(
            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel84Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel84Layout.createSequentialGroup()
                        .addComponent(MinimalistCustomCakelabel)
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

        FondantCustomCakelabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        FondantCustomCakelabel.setForeground(new java.awt.Color(225, 135, 44));
        FondantCustomCakelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FondantCustomCakelabel.setText("<html>Fondant<br>Custom Cake</html>");

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
                    .addComponent(FondantCustomCakelabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel88Layout.setVerticalGroup(
            jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel88Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel88Layout.createSequentialGroup()
                        .addComponent(FondantCustomCakelabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        YemaCakelabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        YemaCakelabel.setForeground(new java.awt.Color(225, 135, 44));
        YemaCakelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        YemaCakelabel.setText("Yema Cake");

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
                    .addComponent(YemaCakelabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel92Layout.setVerticalGroup(
            jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel92Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel92Layout.createSequentialGroup()
                        .addComponent(YemaCakelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addContainerGap(14, Short.MAX_VALUE))))
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
                .addContainerGap(74, Short.MAX_VALUE))
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
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
                .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        AddUbeCheesedesalToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddUbeCheesedesalToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddUbeCheesedesalToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddUbeCheesedesalToPanel.setText("ADD");
        AddUbeCheesedesalToPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddUbeCheesedesalToPanelActionPerformed(evt);
            }
        });

        UbeCheesedesallabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        UbeCheesedesallabel.setForeground(new java.awt.Color(225, 135, 44));
        UbeCheesedesallabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        UbeCheesedesallabel.setText("Ube Cheesedesal");

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UbeCheesedesallabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddUbeCheesedesalToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel59Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel59Layout.createSequentialGroup()
                        .addComponent(UbeCheesedesallabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AddUbeCheesedesalToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
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
                .addGap(23, 23, 23)
                .addComponent(jLabel62)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AddBananaLoafToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddBananaLoafToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddBananaLoafToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddBananaLoafToPanel.setText("ADD");
        AddBananaLoafToPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBananaLoafToPanelActionPerformed(evt);
            }
        });

        BananaLoaflabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BananaLoaflabel.setForeground(new java.awt.Color(225, 135, 44));
        BananaLoaflabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BananaLoaflabel.setText("Banana Loaf");

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BananaLoaflabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddBananaLoafToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel63Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel63Layout.createSequentialGroup()
                        .addComponent(BananaLoaflabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(AddBananaLoafToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
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
                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AddBrowniesToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddBrowniesToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddBrowniesToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddBrowniesToPanel.setText("ADD");
        AddBrowniesToPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBrowniesToPanelActionPerformed(evt);
            }
        });

        Brownieslabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Brownieslabel.setForeground(new java.awt.Color(225, 135, 44));
        Brownieslabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Brownieslabel.setText("Brownies");

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Brownieslabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel67Layout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addComponent(AddBrowniesToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel67Layout.createSequentialGroup()
                        .addComponent(Brownieslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddBrowniesToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel73Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                .addContainerGap())
        );

        AddClassicCinnamonRollToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddClassicCinnamonRollToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddClassicCinnamonRollToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddClassicCinnamonRollToPanel.setText("ADD");
        AddClassicCinnamonRollToPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddClassicCinnamonRollToPanelActionPerformed(evt);
            }
        });

        ClassicCinnamonlabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ClassicCinnamonlabel.setForeground(new java.awt.Color(225, 135, 44));
        ClassicCinnamonlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ClassicCinnamonlabel.setText("Classic Cinnamon Roll");

        javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
        jPanel72.setLayout(jPanel72Layout);
        jPanel72Layout.setHorizontalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel72Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddClassicCinnamonRollToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ClassicCinnamonlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel72Layout.setVerticalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel72Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel72Layout.createSequentialGroup()
                        .addComponent(ClassicCinnamonlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AddClassicCinnamonRollToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
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
                .addComponent(jLabel92)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AddMilkyCheeseDonutToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddMilkyCheeseDonutToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddMilkyCheeseDonutToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddMilkyCheeseDonutToPanel.setText("ADD");
        AddMilkyCheeseDonutToPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMilkyCheeseDonutToPanelActionPerformed(evt);
            }
        });

        MilkyCheeseDonutlabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        MilkyCheeseDonutlabel.setForeground(new java.awt.Color(225, 135, 44));
        MilkyCheeseDonutlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MilkyCheeseDonutlabel.setText("Milky Cheese Donut");

        Donut.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Donut.setForeground(new java.awt.Color(225, 135, 44));
        Donut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel76Layout = new javax.swing.GroupLayout(jPanel76);
        jPanel76.setLayout(jPanel76Layout);
        jPanel76Layout.setHorizontalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel76Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MilkyCheeseDonutlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddMilkyCheeseDonutToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Donut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel76Layout.setVerticalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel76Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addComponent(MilkyCheeseDonutlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Donut)
                        .addGap(12, 12, 12)
                        .addComponent(AddMilkyCheeseDonutToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );

        AddBananaMuffinToPanel.setBackground(new java.awt.Color(255, 255, 153));
        AddBananaMuffinToPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddBananaMuffinToPanel.setForeground(new java.awt.Color(225, 135, 44));
        AddBananaMuffinToPanel.setText("ADD");
        AddBananaMuffinToPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBananaMuffinToPanelActionPerformed(evt);
            }
        });

        BananaMuffinlabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BananaMuffinlabel.setForeground(new java.awt.Color(225, 135, 44));
        BananaMuffinlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BananaMuffinlabel.setText("Banana Muffin");

        javax.swing.GroupLayout jPanel80Layout = new javax.swing.GroupLayout(jPanel80);
        jPanel80.setLayout(jPanel80Layout);
        jPanel80Layout.setHorizontalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel80Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BananaMuffinlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddBananaMuffinToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel80Layout.setVerticalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel80Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel80Layout.createSequentialGroup()
                        .addComponent(BananaMuffinlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(AddBananaMuffinToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout breadsandsweetsPanelLayout = new javax.swing.GroupLayout(breadsandsweetsPanel);
        breadsandsweetsPanel.setLayout(breadsandsweetsPanelLayout);
        breadsandsweetsPanelLayout.setHorizontalGroup(
            breadsandsweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, breadsandsweetsPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(breadsandsweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(breadsandsweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(breadsandsweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(251, 251, 251))
        );
        breadsandsweetsPanelLayout.setVerticalGroup(
            breadsandsweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(breadsandsweetsPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(breadsandsweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(breadsandsweetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel76, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel80, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(384, Short.MAX_VALUE))
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
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
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
    
    
    public void addOrUpdateBillItem(String productName, int quantity, int unitPrice, String size, String toppings) {
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

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
 
       setVisible(false);
       Dashboard main = new Dashboard();
       main.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void payByCashBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payByCashBtnActionPerformed
        
    }//GEN-LAST:event_payByCashBtnActionPerformed

    private void AddClassicCinnamonRollToPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddClassicCinnamonRollToPanelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddClassicCinnamonRollToPanelActionPerformed

    private void AddMilkyCheeseDonutToPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMilkyCheeseDonutToPanelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddMilkyCheeseDonutToPanelActionPerformed

    private void AddBananaMuffinToPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBananaMuffinToPanelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddBananaMuffinToPanelActionPerformed

    private void AddBananaLoafToPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBananaLoafToPanelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddBananaLoafToPanelActionPerformed

    private void AddUbeCheesedesalToPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddUbeCheesedesalToPanelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddUbeCheesedesalToPanelActionPerformed

    private void AddBrowniesToPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBrowniesToPanelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddBrowniesToPanelActionPerformed

    private void AddRedVelvetCakeToPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddRedVelvetCakeToPanelActionPerformed
    String productName = "Red Velvet";
    String[] sizes = PRODUCT_SIZES.getOrDefault(productName, new String[]{"Default Size"});
    String[] toppings = PRODUCT_TOPPINGS.getOrDefault(productName, new String[]{"None"});

    ProductPanel productPanel = new ProductPanel(this, productName, sizes, toppings);
    productPanel.setLocationRelativeTo(this);
    productPanel.setVisible(true);
    productPanel.setResizable(false);
    }//GEN-LAST:event_AddRedVelvetCakeToPanelActionPerformed

    private void AddStrawberryShortCakeToPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddStrawberryShortCakeToPanelActionPerformed
    String productName = "Strawberry Shortcake";
    String[] sizes = PRODUCT_SIZES.getOrDefault(productName, new String[]{"Default Size"});
    String[] toppings = PRODUCT_TOPPINGS.getOrDefault(productName, new String[]{"None"});

    ProductPanel productPanel = new ProductPanel(this, productName, sizes, toppings);
    productPanel.setLocationRelativeTo(this);
    productPanel.setVisible(true);
    productPanel.setResizable(false);
    }//GEN-LAST:event_AddStrawberryShortCakeToPanelActionPerformed

    private void AddMangoBravoToPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMangoBravoToPanelActionPerformed
        String productName = "Mango Bravo";
        String[] sizes = PRODUCT_SIZES.getOrDefault(productName, new String[]{"Default Size"});
        String[] toppings = PRODUCT_TOPPINGS.getOrDefault(productName, new String[]{"None"});

        ProductPanel productPanel = new ProductPanel(this, productName, sizes, toppings);
        productPanel.setLocationRelativeTo(this);
        productPanel.setVisible(true);
        productPanel.setResizable(false);
    }//GEN-LAST:event_AddMangoBravoToPanelActionPerformed

    private void AddBentoCakeToPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBentoCakeToPanelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddBentoCakeToPanelActionPerformed
    




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
    private javax.swing.JButton AddBananaLoafToPanel;
    private javax.swing.JButton AddBananaMuffinToPanel;
    private javax.swing.JButton AddBentoCakeToPanel;
    private javax.swing.JButton AddBrowniesToPanel;
    private javax.swing.JButton AddCaramelFlanDeLecheCakeToPanel;
    private javax.swing.JButton AddCheeseCakeToPanel;
    private javax.swing.JButton AddClassicCinnamonRollToPanel;
    private javax.swing.JButton AddFondantCustomCakeToPanel;
    private javax.swing.JButton AddIcingCustomCakeToPanel;
    private javax.swing.JButton AddMangoBravoToPanel;
    private javax.swing.JButton AddMangoBravoTubToPanel;
    private javax.swing.JButton AddMilkyCheeseDonutToPanel;
    private javax.swing.JButton AddMiniCupcakeToPanel;
    private javax.swing.JButton AddMinimalistCustomCakeToPanel;
    private javax.swing.JButton AddPiscesCupcakeToPanel;
    private javax.swing.JButton AddRedVelvetCakeToPanel;
    private javax.swing.JButton AddSliceCheeseCakeToPanel;
    private javax.swing.JButton AddStrawberryShortCakeToPanel;
    private javax.swing.JButton AddUbeCheesedesalToPanel;
    private javax.swing.JButton AddYemaCakeToPanel;
    private javax.swing.JLabel BananaLoaflabel;
    private javax.swing.JLabel BananaMuffinlabel;
    private javax.swing.JLabel BentoCakelabel;
    private javax.swing.JPanel BillPanel;
    private javax.swing.JLabel Brownieslabel;
    private javax.swing.JLabel Caramelflandelechecakelabel;
    private javax.swing.JLabel CheeseCakelabel;
    private javax.swing.JLabel ClassicCinnamonlabel;
    private javax.swing.JLabel Donut;
    private javax.swing.JLabel FondantCustomCakelabel;
    private javax.swing.JLabel IcingCustomCakelabel;
    private javax.swing.JLabel MangoBravoTublabel;
    private javax.swing.JLabel MangoBravolabel;
    private javax.swing.JLabel MilkyCheeseDonutlabel;
    private javax.swing.JLabel MiniCupcakelabel;
    private javax.swing.JLabel MinimalistCustomCakelabel;
    private javax.swing.JLabel PiscesCupcakelabel;
    private javax.swing.JLabel RedVelvetCakelabel;
    private javax.swing.JLabel SliceCheeseCakelabel;
    private javax.swing.JLabel StrawberryShortCakelabel;
    private javax.swing.JLabel UbeCheesedesallabel;
    private javax.swing.JLabel YemaCakelabel;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel billLabel;
    private javax.swing.JList<String> billList;
    private javax.swing.JPanel breadsandsweetsPanel;
    private javax.swing.JButton btnBreadSweets1;
    private javax.swing.JButton btnCake;
    private javax.swing.JPanel cakesPanel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JPanel iconPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
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
    private javax.swing.JPanel jPanel18;
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
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JPanel jPanel88;
    private javax.swing.JPanel jPanel89;
    private javax.swing.JPanel jPanel92;
    private javax.swing.JPanel jPanel93;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton payByCashBtn;
    private javax.swing.JToggleButton payByGCASHBtn;
    private javax.swing.JPanel productsPanel;
    private javax.swing.JLabel totalAmountLabel;
    private javax.swing.JLabel totalstaticlabel;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
