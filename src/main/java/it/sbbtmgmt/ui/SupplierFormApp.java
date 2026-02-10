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
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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

        JLabel endpointFoToBoLabel = new JLabel("Endpoint FO to BO:");
        JTextField endpointFoToBoField = createPlaceholderField(24, "www.suap.it/fo-to-bo");

        JLabel endpointBoToFoLabel = new JLabel("Endpoint BO to FO:");
        JTextField endpointBoToFoField = createPlaceholderField(24, "www.suap.it/bo-to-fo");

        JLabel endpointFoToCuLabel = new JLabel("Endpoint FO to CU:");
        JTextField endpointFoToCuField = createPlaceholderField(24, "www.suap.it/fo-to-cu");

        JLabel endpointBoToRiLabel = new JLabel("Endpoint BO to RI:");
        JTextField endpointBoToRiField = createPlaceholderField(24, "www.suap.it/bo-to-ri");

        JLabel endpointBoToEtLabel = new JLabel("Endpoint BO to ET:");
        JTextField endpointBoToEtField = createPlaceholderField(24, "www.suap.it/bo-to-et");

        JLabel endpointEtToBoLabel = new JLabel("Endpoint ET to BO:");
        JTextField endpointEtToBoField = createPlaceholderField(24, "www.suap.it/et-to-bo");

        JButton saveButton = new JButton("Conferma");
        saveButton.addActionListener(event -> {
            String componentName = componentNameField.getText().trim();
            String componentDescription = componentDescriptionField.getText().trim();
            String supplierName = supplierField.getText().trim();
            String vatNumber = vatField.getText().trim();
            String endpointFoToBo = getFieldValue(endpointFoToBoField, "www.suap.it/fo-to-bo");
            String endpointBoToFo = getFieldValue(endpointBoToFoField, "www.suap.it/bo-to-fo");
            String endpointFoToCu = getFieldValue(endpointFoToCuField, "www.suap.it/fo-to-cu");
            String endpointBoToRi = getFieldValue(endpointBoToRiField, "www.suap.it/bo-to-ri");
            String endpointBoToEt = getFieldValue(endpointBoToEtField, "www.suap.it/bo-to-et");
            String endpointEtToBo = getFieldValue(endpointEtToBoField, "www.suap.it/et-to-bo");

            String message = "Nome: " + componentName
                    + "\nDescrizione: " + componentDescription
                    + "\nNome fornitore: " + supplierName
                    + "\nPartita IVA: " + vatNumber
                    + "\nEndpoint FO to BO: " + endpointFoToBo
                    + "\nEndpoint BO to FO: " + endpointBoToFo
                    + "\nEndpoint FO to CU: " + endpointFoToCu
                    + "\nEndpoint BO to RI: " + endpointBoToRi
                    + "\nEndpoint BO to ET: " + endpointBoToEt
                    + "\nEndpoint ET to BO: " + endpointEtToBo;
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

        JPanel endpointPanel = new JPanel(new GridBagLayout());
        endpointPanel.setBorder(new TitledBorder("Endpoint"));

        GridBagConstraints endpointGbc = new GridBagConstraints();
        endpointGbc.insets = new Insets(6, 6, 6, 6);
        endpointGbc.anchor = GridBagConstraints.WEST;

        endpointGbc.gridx = 0;
        endpointGbc.gridy = 0;
        endpointPanel.add(endpointFoToBoLabel, endpointGbc);

        endpointGbc.gridx = 1;
        endpointGbc.fill = GridBagConstraints.HORIZONTAL;
        endpointPanel.add(endpointFoToBoField, endpointGbc);

        endpointGbc.gridx = 0;
        endpointGbc.gridy = 1;
        endpointGbc.fill = GridBagConstraints.NONE;
        endpointPanel.add(endpointBoToFoLabel, endpointGbc);

        endpointGbc.gridx = 1;
        endpointGbc.fill = GridBagConstraints.HORIZONTAL;
        endpointPanel.add(endpointBoToFoField, endpointGbc);

        endpointGbc.gridx = 0;
        endpointGbc.gridy = 2;
        endpointGbc.fill = GridBagConstraints.NONE;
        endpointPanel.add(endpointFoToCuLabel, endpointGbc);

        endpointGbc.gridx = 1;
        endpointGbc.fill = GridBagConstraints.HORIZONTAL;
        endpointPanel.add(endpointFoToCuField, endpointGbc);

        endpointGbc.gridx = 0;
        endpointGbc.gridy = 3;
        endpointGbc.fill = GridBagConstraints.NONE;
        endpointPanel.add(endpointBoToRiLabel, endpointGbc);

        endpointGbc.gridx = 1;
        endpointGbc.fill = GridBagConstraints.HORIZONTAL;
        endpointPanel.add(endpointBoToRiField, endpointGbc);

        endpointGbc.gridx = 0;
        endpointGbc.gridy = 4;
        endpointGbc.fill = GridBagConstraints.NONE;
        endpointPanel.add(endpointBoToEtLabel, endpointGbc);

        endpointGbc.gridx = 1;
        endpointGbc.fill = GridBagConstraints.HORIZONTAL;
        endpointPanel.add(endpointBoToEtField, endpointGbc);

        endpointGbc.gridx = 0;
        endpointGbc.gridy = 5;
        endpointGbc.fill = GridBagConstraints.NONE;
        endpointPanel.add(endpointEtToBoLabel, endpointGbc);

        endpointGbc.gridx = 1;
        endpointGbc.fill = GridBagConstraints.HORIZONTAL;
        endpointPanel.add(endpointEtToBoField, endpointGbc);

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
        panel.add(endpointPanel, gbc);

        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panel.add(saveButton, gbc);

        return panel;
    }

    private static JTextField createPlaceholderField(int columns, String placeholder) {
        JTextField textField = new JTextField(columns);
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent event) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (textField.getText().trim().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
        return textField;
    }

    private static String getFieldValue(JTextField field, String placeholder) {
        String value = field.getText().trim();
        if (value.equals(placeholder)) {
            return "";
        }
        return value;
    }
}
