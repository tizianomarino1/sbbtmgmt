package it.sbbtmgmt.ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
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
        JFrame frame = new JFrame("SBBT Management");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setJMenuBar(createMenuBar(frame));
        frame.setContentPane(createMainPanel());
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JMenuBar createMenuBar(JFrame parentFrame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu componentMenu = new JMenu("Componenti");
        JMenuItem newComponentItem = new JMenuItem("Nuova componente");
        newComponentItem.addActionListener(event -> openNewComponentDialog(parentFrame));
        componentMenu.add(newComponentItem);

        menuBar.add(componentMenu);
        return menuBar;
    }

    private static JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JTable componentsTable = new JTable(new DefaultTableModel(
                new Object[][]{
                        {"SUAP", "FO", "Componente FO"},
                        {"SUAP", "BO", "Componente BO"},
                        {"SUE", "ET", "Gateway ET"},
                        {"SUAP", "CU", "Connettore CU"},
                        {"SUE", "RI", "Adapter RI"}
                },
                new Object[]{"Contesto", "Tipologia", "Nome"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        JTable testExecutionTable = new JTable(new DefaultTableModel(
                new Object[][]{
                        {"a3f3dc7a-6ea3-4f86-bdf9-56be8fdc9152", "COMPLETATO"},
                        {"94857ed0-9ea7-4f44-b39e-6ef6e8b03db3", "IN CORSO"},
                        {"f6ac1fc1-64d0-48d8-9db8-57d9f8eb1e35", "ERRORE"},
                        {"4c72dfce-981f-44ef-ad2a-cb8f1c5ebdb4", "IN ATTESA"},
                        {"8d91d106-5ef1-4bf8-bf37-afb43a8322ef", "COMPLETATO"}
                },
                new Object[]{"Uuid", "Stato di esecuzione"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        JScrollPane componentsScrollPane = new JScrollPane(componentsTable);
        componentsScrollPane.setBorder(new TitledBorder("Componenti"));

        JScrollPane testExecutionScrollPane = new JScrollPane(testExecutionTable);
        testExecutionScrollPane.setBorder(new TitledBorder("Esecuzione test"));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, componentsScrollPane, testExecutionScrollPane);
        splitPane.setResizeWeight(0.3);
        splitPane.setDividerLocation(0.3);

        panel.add(splitPane, BorderLayout.CENTER);
        return panel;
    }

    private static void openNewComponentDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "Nuova componente", true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setContentPane(createFormPanel(dialog));
        dialog.pack();
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }

    private static JPanel createFormPanel(JDialog parentDialog) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel componentNameLabel = new JLabel("Nome:");
        JTextField componentNameField = new JTextField(24);

        JLabel componentDescriptionLabel = new JLabel("Descrizione:");
        JTextField componentDescriptionField = new JTextField(24);

        JLabel componentTypeLabel = new JLabel("Tipologia:");
        JComboBox<String> componentTypeCombo = new JComboBox<>(new String[]{
                "FO (FrontOffice)",
                "BO (BackOffice)",
                "FOBO (Architettura integrata)",
                "ET (Ente Terzo)"
        });

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

        JLabel emailTypeLabel = new JLabel("Tipo email:");
        JComboBox<String> emailTypeCombo = new JComboBox<>(new String[]{"PEC", "MAIL"});

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = createPlaceholderField(24, "example@acme.it");

        JLabel contextLabel = new JLabel("Contesto:");
        JComboBox<String> contextCombo = new JComboBox<>(new String[]{"SUAP", "SUE"});

        JLabel specificationVersionLabel = new JLabel("Versione specifiche:");
        JComboBox<String> specificationVersionCombo = new JComboBox<>(new String[]{"MAIN", "APPROVED_01"});

        JButton saveButton = new JButton("Registra");
        saveButton.addActionListener(event -> {
            String componentName = componentNameField.getText().trim();
            String componentDescription = componentDescriptionField.getText().trim();
            String componentType = (String) componentTypeCombo.getSelectedItem();
            String supplierName = supplierField.getText().trim();
            String vatNumber = vatField.getText().trim();
            String endpointFoToBo = getFieldValue(endpointFoToBoField, "www.suap.it/fo-to-bo");
            String endpointBoToFo = getFieldValue(endpointBoToFoField, "www.suap.it/bo-to-fo");
            String endpointFoToCu = getFieldValue(endpointFoToCuField, "www.suap.it/fo-to-cu");
            String endpointBoToRi = getFieldValue(endpointBoToRiField, "www.suap.it/bo-to-ri");
            String endpointBoToEt = getFieldValue(endpointBoToEtField, "www.suap.it/bo-to-et");
            String endpointEtToBo = getFieldValue(endpointEtToBoField, "www.suap.it/et-to-bo");
            String emailType = (String) emailTypeCombo.getSelectedItem();
            String email = getFieldValue(emailField, "example@acme.it");
            String context = (String) contextCombo.getSelectedItem();
            String specificationVersion = (String) specificationVersionCombo.getSelectedItem();

            String message = "Nome: " + componentName
                    + "\nDescrizione: " + componentDescription
                    + "\nTipologia: " + componentType
                    + "\nNome fornitore: " + supplierName
                    + "\nPartita IVA: " + vatNumber
                    + "\nEndpoint FO to BO: " + endpointFoToBo
                    + "\nEndpoint BO to FO: " + endpointBoToFo
                    + "\nEndpoint FO to CU: " + endpointFoToCu
                    + "\nEndpoint BO to RI: " + endpointBoToRi
                    + "\nEndpoint BO to ET: " + endpointBoToEt
                    + "\nEndpoint ET to BO: " + endpointEtToBo
                    + "\nTipo email: " + emailType
                    + "\nEmail: " + email
                    + "\nContesto: " + context
                    + "\nVersione specifiche: " + specificationVersion;
            JOptionPane.showMessageDialog(panel, message, "Dati inseriti", JOptionPane.INFORMATION_MESSAGE);
            parentDialog.dispose();
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

        componentGbc.gridx = 0;
        componentGbc.gridy = 2;
        componentGbc.fill = GridBagConstraints.NONE;
        componentPanel.add(componentTypeLabel, componentGbc);

        componentGbc.gridx = 1;
        componentGbc.fill = GridBagConstraints.HORIZONTAL;
        componentPanel.add(componentTypeCombo, componentGbc);

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

        JPanel reportPanel = new JPanel(new GridBagLayout());
        reportPanel.setBorder(new TitledBorder("Report"));

        GridBagConstraints reportGbc = new GridBagConstraints();
        reportGbc.insets = new Insets(6, 6, 6, 6);
        reportGbc.anchor = GridBagConstraints.WEST;

        reportGbc.gridx = 0;
        reportGbc.gridy = 0;
        reportPanel.add(emailTypeLabel, reportGbc);

        reportGbc.gridx = 1;
        reportGbc.fill = GridBagConstraints.HORIZONTAL;
        reportPanel.add(emailTypeCombo, reportGbc);

        reportGbc.gridx = 0;
        reportGbc.gridy = 1;
        reportGbc.fill = GridBagConstraints.NONE;
        reportPanel.add(emailLabel, reportGbc);

        reportGbc.gridx = 1;
        reportGbc.fill = GridBagConstraints.HORIZONTAL;
        reportPanel.add(emailField, reportGbc);

        JPanel sbbtSettingsPanel = new JPanel(new GridBagLayout());
        sbbtSettingsPanel.setBorder(new TitledBorder("Impostazioni SBBT"));

        GridBagConstraints sbbtSettingsGbc = new GridBagConstraints();
        sbbtSettingsGbc.insets = new Insets(6, 6, 6, 6);
        sbbtSettingsGbc.anchor = GridBagConstraints.WEST;

        sbbtSettingsGbc.gridx = 0;
        sbbtSettingsGbc.gridy = 0;
        sbbtSettingsPanel.add(contextLabel, sbbtSettingsGbc);

        sbbtSettingsGbc.gridx = 1;
        sbbtSettingsGbc.fill = GridBagConstraints.HORIZONTAL;
        sbbtSettingsPanel.add(contextCombo, sbbtSettingsGbc);

        sbbtSettingsGbc.gridx = 0;
        sbbtSettingsGbc.gridy = 1;
        sbbtSettingsGbc.fill = GridBagConstraints.NONE;
        sbbtSettingsPanel.add(specificationVersionLabel, sbbtSettingsGbc);

        sbbtSettingsGbc.gridx = 1;
        sbbtSettingsGbc.fill = GridBagConstraints.HORIZONTAL;
        sbbtSettingsPanel.add(specificationVersionCombo, sbbtSettingsGbc);

        JPanel leftColumnPanel = new JPanel(new GridBagLayout());
        GridBagConstraints leftGbc = new GridBagConstraints();
        leftGbc.insets = new Insets(6, 6, 6, 6);
        leftGbc.anchor = GridBagConstraints.NORTHWEST;
        leftGbc.fill = GridBagConstraints.HORIZONTAL;
        leftGbc.weightx = 1.0;

        leftGbc.gridx = 0;
        leftGbc.gridy = 0;
        leftColumnPanel.add(componentPanel, leftGbc);

        leftGbc.gridy = 1;
        leftColumnPanel.add(supplierPanel, leftGbc);

        leftGbc.gridy = 2;
        leftColumnPanel.add(reportPanel, leftGbc);

        leftGbc.gridy = 3;
        leftGbc.weighty = 1.0;
        leftColumnPanel.add(new JPanel(), leftGbc);

        JPanel rightColumnPanel = new JPanel(new GridBagLayout());
        GridBagConstraints rightGbc = new GridBagConstraints();
        rightGbc.insets = new Insets(6, 6, 6, 6);
        rightGbc.anchor = GridBagConstraints.NORTHWEST;
        rightGbc.fill = GridBagConstraints.HORIZONTAL;
        rightGbc.weightx = 1.0;

        rightGbc.gridx = 0;
        rightGbc.gridy = 0;
        rightColumnPanel.add(endpointPanel, rightGbc);

        rightGbc.gridy = 1;
        rightColumnPanel.add(sbbtSettingsPanel, rightGbc);

        rightGbc.gridy = 2;
        rightGbc.weighty = 1.0;
        rightColumnPanel.add(new JPanel(), rightGbc);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(leftColumnPanel, gbc);

        gbc.gridx = 1;
        panel.add(rightColumnPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
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
