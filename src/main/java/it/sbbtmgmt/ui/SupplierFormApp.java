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
import javax.swing.border.TitledBorder;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class SupplierFormApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SupplierFormApp::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("Nuova componente");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(createFormPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel componentNameLabel = new JLabel("Nome:");
        JTextField componentNameField = new JTextField(24);

        JLabel componentDescriptionLabel = new JLabel("Descrizione:");
        JTextField componentDescriptionField = new JTextField(24);

        JLabel supplierLabel = new JLabel("Nome fornitore:");
        JTextField supplierField = new JTextField(24);

        JLabel vatLabel = new JLabel("Partita IVA:");
        JTextField vatField = new JTextField(24);

        JButton saveButton = new JButton("Conferma");
        saveButton.addActionListener(event -> {
            String componentName = componentNameField.getText().trim();
            String componentDescription = componentDescriptionField.getText().trim();
            String supplierName = supplierField.getText().trim();
            String vatNumber = vatField.getText().trim();

            String message = "Nome: " + componentName
                    + "\nDescrizione: " + componentDescription
                    + "\nNome fornitore: " + supplierName
                    + "\nPartita IVA: " + vatNumber;
            JOptionPane.showMessageDialog(panel, message, "Dati inseriti", JOptionPane.INFORMATION_MESSAGE);
        });

        JPanel componentPanel = new JPanel(new GridBagLayout());
        componentPanel.setBorder(new TitledBorder("Componente"));

        GridBagConstraints componentGbc = new GridBagConstraints();
        componentGbc.insets = new Insets(6, 6, 6, 6);
        componentGbc.anchor = GridBagConstraints.WEST;

        componentGbc.gridx = 0;
        componentGbc.gridy = 0;
        componentPanel.add(componentNameLabel, componentGbc);

        componentGbc.gridx = 1;
        componentGbc.fill = GridBagConstraints.HORIZONTAL;
        componentPanel.add(componentNameField, componentGbc);

        componentGbc.gridx = 0;
        componentGbc.gridy = 1;
        componentGbc.fill = GridBagConstraints.NONE;
        componentPanel.add(componentDescriptionLabel, componentGbc);

        componentGbc.gridx = 1;
        componentGbc.fill = GridBagConstraints.HORIZONTAL;
        componentPanel.add(componentDescriptionField, componentGbc);

        JPanel supplierPanel = new JPanel(new GridBagLayout());
        supplierPanel.setBorder(new TitledBorder("Fornitore"));

        GridBagConstraints supplierGbc = new GridBagConstraints();
        supplierGbc.insets = new Insets(6, 6, 6, 6);
        supplierGbc.anchor = GridBagConstraints.WEST;

        supplierGbc.gridx = 0;
        supplierGbc.gridy = 0;
        supplierPanel.add(supplierLabel, supplierGbc);

        supplierGbc.gridx = 1;
        supplierGbc.fill = GridBagConstraints.HORIZONTAL;
        supplierPanel.add(supplierField, supplierGbc);

        supplierGbc.gridx = 0;
        supplierGbc.gridy = 1;
        supplierGbc.fill = GridBagConstraints.NONE;
        supplierPanel.add(vatLabel, supplierGbc);

        supplierGbc.gridx = 1;
        supplierGbc.fill = GridBagConstraints.HORIZONTAL;
        supplierPanel.add(vatField, supplierGbc);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(componentPanel, gbc);

        gbc.gridy = 1;
        panel.add(supplierPanel, gbc);

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panel.add(saveButton, gbc);

        return panel;
    }
}
