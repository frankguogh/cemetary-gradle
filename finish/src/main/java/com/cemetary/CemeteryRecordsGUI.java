package com.cemetary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CemeteryRecordsGUI extends JFrame {
    private JTextField searchField;
    private JTextArea resultArea;

    public CemeteryRecordsGUI() {
        initUI();
    }

    private void initUI() {
        setTitle("Cemetery Records");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        searchField = new JTextField(20);
        topPanel.add(searchField);

        JButton searchByNameButton = new JButton("Search by Name");
        JButton searchByStreetButton = new JButton("Search by Street");
        JButton sortByNameButton = new JButton("Sort by Name");
        JButton sortByDateButton = new JButton("Sort by Date");
        JButton sortByAgeButton = new JButton("Sort by Age");
        JButton showAllButton = new JButton("Show All");

        topPanel.add(searchByNameButton);
        topPanel.add(searchByStreetButton);
        topPanel.add(sortByNameButton);
        topPanel.add(sortByDateButton);
        topPanel.add(sortByAgeButton);
        topPanel.add(showAllButton);

        add(topPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);

        // Placeholder event listeners
        searchByNameButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Search by Name clicked"));
        searchByStreetButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Search by Street clicked"));
        sortByNameButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Sort by Name clicked"));
        sortByDateButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Sort by Date clicked"));
        sortByAgeButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Sort by Age clicked"));
        showAllButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Show All clicked"));
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CemeteryRecordsGUI().setVisible(true));
    }
}
