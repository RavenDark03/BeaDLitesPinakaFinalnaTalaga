/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg3rdfinalproject;
import pkg3rdfinalproject.JavaMailSender;
import javax.mail.MessagingException;

/**
 *
 * @author Lynch
 */
public class ReceiptGenerator {
    public static void main(String[] args) {
        // Test email details
        String recipientEmail = "matthewmarcsantua@gmail.com"; // Replace with actual email
        String emailSubject = "Receipt";
        String receipt = """
                         <html lang="en">
                         <head>
                           <meta charset="UTF-8">
                           <meta name="viewport" content="width=device-width, initial-scale=1.0">
                           <title>Receipt</title>
                           <style>
                             .receipt-box {
                               font-family: Arial, Helvetica, sans-serif;
                               border: 2px dashed #d9a066;
                               background: #fffaf0;
                               width: 400px;
                               margin: 20px auto;
                               padding: 20px;
                             }
                             h2, h4 {
                               text-align: center;
                               margin: 0;
                             }
                             .receipt-header {
                               border-bottom: 1px solid #ccc;
                               margin-bottom: 10px;
                               padding-bottom: 10px;
                             }
                             .receipt-details, .items, .totals {
                               width: 100%;
                               margin-bottom: 15px;
                             }
                             .items th, .items td {
                               text-align: left;
                               padding: 5px 0;
                             }
                             .totals td {
                               font-weight: bold;
                             }
                             .footer {
                               text-align: center;
                               font-size: 0.9em;
                               color: #e20f8a;
                               margin-top: 10px;
                             }
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
                                 Receipt #: <strong>000001</strong><br>
                                 Date: <strong>May 29, 2025</strong>
                               </p>
                             </div>
                         
                             <div class="receipt-details">
                               <p><strong>Customer:</strong> Bea Quindoza </p>
                             </div>
                         
                             <table class="items">
                               <thead>
                                 <tr>
                                   <th>Item</th>
                                   <th>Qty</th>
                                   <th style="text-align:right;">Price</th>
                                 </tr>
                               </thead>
                               <tbody>
                                 <tr>
                                   <td>Red Velvet Cupcake</td>
                                   <td>2</td>
                                   <td style="text-align:right;">₱140</td>
                                 </tr>
                                 <tr>
                                   <td>Chocolate Cake Slice</td>
                                   <td>1</td>
                                   <td style="text-align:right;">₱95</td>
                                 </tr>
                                 <tr>
                                   <td>Banana Muffins (Box of 6)</td>
                                   <td>1</td>
                                   <td style="text-align:right;">₱109</td>
                                 </tr>
                               </tbody>
                             </table>
                         
                             <table class="totals">
                               <tr>
                                 <td>Total</td>
                                 <td style="text-align:right;">₱415</td>
                               </tr>
                               <tr>
                                 <td>Cash</td>
                                 <td style="text-align:right;">₱500</td>
                               </tr>
                               <tr>
                                 <td>Change</td>
                                 <td style="text-align:right;">₱85</td>
                               </tr>
                             </table>
                         
                             <div class="footer">
                               <p>Thank you for your purchase!</p>
                               <p>Follow us on Instagram @beadlites</p>
                             </div>
                           </div>
                         </body>
                         </html>
                         """;
        
        try {
            // Send the test email
            JavaMailSender.sendHtmlEmail(recipientEmail, emailSubject, receipt);
            System.out.println("Test email sent successfully to: " + recipientEmail);
        } catch (MessagingException e) {
            System.err.println("Failed to send test email: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Method to generate and send a receipt email
     * @param customerEmail The customer's email address
     * @param receiptDetails The details of the receipt
     * @throws MessagingException if email sending fails
     */
    public static void sendReceiptEmail(String customerEmail, String receiptDetails) throws MessagingException {
        String subject = "Your Purchase Receipt";
        String message = "Thank you for your purchase!\n\n"
                + "Here is your receipt details:\n"
                + receiptDetails
                + "\n\nIf you have any questions, please contact support.";
        
        JavaMailSender.sendHtmlEmail(customerEmail, subject, message);
    }
   
}
