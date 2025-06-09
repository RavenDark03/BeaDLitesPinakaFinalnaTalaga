package pkg3rdfinalproject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lynch
 */
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GCashQRDialog {

    /**
     * Shows a dialog with a QR code for the provided content string.
     * @param qrContent The QR payload (e.g. your GCash QR string or link)
     */
    public static void showGCashQR(String qrContent) {
        int size = 300;
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, size, size);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            JLabel label = new JLabel(new ImageIcon(qrImage));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);

            JOptionPane.showMessageDialog(null, label, "Scan to Pay via GCash", JOptionPane.PLAIN_MESSAGE);

        } catch (WriterException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to generate QR: " + e.getMessage());
        }
    }
}
