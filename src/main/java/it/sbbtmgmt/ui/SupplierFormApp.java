package it.sbbtmgmt.ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class SupplierFormApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SupplierFormApp::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("Gestione Fornitore");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(createFormPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel supplierLabel = new JLabel("Nome fornitore:");
        JTextField supplierField = new JTextField(24);

        JLabel vatLabel = new JLabel("Partita IVA:");
        JTextField vatField = new JTextField(24);

        JButton saveButton = new JButton("Conferma");
        saveButton.addActionListener(event -> {
            String supplierName = supplierField.getText().trim();
            String vatNumber = vatField.getText().trim();

            String message = "Nome fornitore: " + supplierName + "\nPartita IVA: " + vatNumber;
            JOptionPane.showMessageDialog(panel, message, "Dati inseriti", JOptionPane.INFORMATION_MESSAGE);
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(supplierLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(supplierField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(vatLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(vatField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(saveButton, gbc);

        return panel;
    }
}
