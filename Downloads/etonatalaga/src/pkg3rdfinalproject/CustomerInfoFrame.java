package pkg3rdfinalproject;

import javax.swing.*;
import java.awt.*;

public class CustomerInfoFrame extends JDialog {
    public String customerName = "";
    public String customerEmail = "";
    public String customerContact = "";
    private boolean submitted = false;

    public CustomerInfoFrame(Frame parent) {
        super(parent, "Customer Information", true); // true = modal
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(350, 220);
        setLocationRelativeTo(parent);

        JLabel nameLabel = new JLabel("Customer Name:");
        JTextField nameField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);

        JLabel contactLabel = new JLabel("Contact Number:");
        JTextField contactField = new JTextField(20);

        JButton submitBtn = new JButton("OK");
        submitBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name is required!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            customerName = name;
            customerEmail = emailField.getText().trim();
            customerContact = contactField.getText().trim();
            submitted = true;
            dispose();
        });

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(nameLabel);    panel.add(nameField);
        panel.add(emailLabel);   panel.add(emailField);
        panel.add(contactLabel); panel.add(contactField);
        panel.add(new JLabel()); panel.add(submitBtn);

        add(panel);
        pack();
    }

    public boolean isSubmitted() {
        return submitted;
    }
}