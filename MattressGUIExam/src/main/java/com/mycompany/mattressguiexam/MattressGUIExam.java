/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mattressguiexam;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class MattressGUIExam extends JFrame{
    
    private final String[] productName = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phukrt"};
    
    private final JComboBox<String> productNameBox = new JComboBox<>(productName);
    private final JTextField customerNameField = new JTextField();
    
    private final JTextField priceField = new JTextField(); 
    
    public MattressGUIExam(){
        setTitle("Product App");//NAME THAT APPEARS AT THE TOP
        setSize(600, 600);//THE SIZE OF THE PAGE
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));
        
        
        form.add(new JLabel("Customer Name:"));    
        form.add(customerNameField);   
        form.add(new JLabel("Select product: ")); 
        form.add(productNameBox); 
        form.add(new JLabel("Enter price: ")); 
        form.add(priceField); 
        
        JButton processButton = new JButton("PROCESS");
        
        form.add(new JLabel(""));                 
        form.add(processButton);
        
        add(form, BorderLayout.CENTER);            // KEEPS BUTTON LINES UP WITH THE DROP DOWN THINGS
        setJMenuBar(createMenuBar());
        
        add(form, BorderLayout.CENTER);            // KEEPS BUTTON LINES UP WITH THE DROP DOWN THINGS
        setJMenuBar(createMenuBar()); 
        
        processButton.addActionListener(event -> processButton());
        form.add(new JLabel(""));  
        
        pack();
        
        setLocationRelativeTo(null);  
        
        

        setVisible(true);
        
        
        
    };
    
    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar(); 
         
        JMenu fileMenu = new JMenu("File");         // Creates the File menu.
        JMenu toolsMenu = new JMenu("Edit");       // Creates the Tools menu.

        JMenuItem exitItem = new JMenuItem("Exit"); // Creates the Exit menu choice.
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");
        
        exitItem.addActionListener(event -> dispose());//LINKS THE BUTTON TO THE CORRECT METHOD
        processItem.addActionListener(event -> processButton());
        clearItem.addActionListener(event -> clearButton());
        

        fileMenu.add(exitItem);//SHOWS WHICH "BUTTONS" GO UNDER WITH SECTION OF THE MENU
        toolsMenu.add(processItem);                 
        toolsMenu.add(clearItem);                  

        menuBar.add(fileMenu);//PLACES BOTH MENUE OPTIONS ON THE MAIN PAGE 
        menuBar.add(toolsMenu);                    

        return menuBar;      
    }
    
    
    private void processButton(){
        String customerName = customerNameField.getText();
        String priceText = priceField.getText();
        String selectedProduct = (String) productNameBox.getSelectedItem();
      
        if(customerName.isEmpty() || priceText.isEmpty()){//MAKES SURE THE FIELDS ARENT EMPTY OR ELSE IT WILL DISPLAT A ERROR MESSAGE OR IT WILL SHOW ALL THE THINGS YOU ENTERED + THE CALCULATIONS RELATED
            JOptionPane.showMessageDialog(this, "Error,please enter both a customer name and a price.","Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            double basePrice = Double.parseDouble(priceText); // CONVERTS PRICE TO DOUBLE
            
            double vat = basePrice * 0.15;//CALCULATIONS
            double commission = basePrice * 0.05;
            double totalSales = basePrice + vat + commission;

            
            String message = String.format(
                    "Customer Name: %s\n" +
                    "Product: %s\n" +
                    "VAT (15%%): R %.2f\n" +
                    "Commission (5%%): R %.2f\n" +
                    "Total Sales: R %.2f",
                    customerName, selectedProduct, vat, commission, totalSales
            );

           
            JOptionPane.showMessageDialog(this, message, "Order Summary",
                    JOptionPane.INFORMATION_MESSAGE);
                    
        } catch (NumberFormatException e) {
            
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the price (e.g., 1500.50).", "Invalid Price", JOptionPane.ERROR_MESSAGE);
        

        
        }
    }
    
    
    
    private void clearButton(){
        customerNameField.setText("");
        priceField.setText("");
        productNameBox.setSelectedIndex(0);
    }
    
    

    public static void main(String[] args) {
      new MattressGUIExam();
    }
}
