import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.*;


public class MyPizzaOrder extends JFrame implements ActionListener {
    JButton btnAdd;
    JLabel lbGreatings, lbOrderNo, lbRate, lbCustomerName, lbAmount, lbPhoneNo, lbPayment, lbItems, lbAddress, imgLabel;
    JTextField tfOrderNo, tfRate, tfCustomerName, tfAmount, tfPhoneNo;
    JComboBox<String> cbPayment, cbToppings;
    JTextArea taAddress;
    
    Connection conn;
    Statement stmt;

    public MyPizzaOrder() {
        this.setTitle("PIZZA PICASSO SHOP");
        this.setSize(800, 790);
        this.setLayout(null);

        String[] modes = {"", "Cash on Delivery", "Debit Card", "Net Banking"};
        String[] items = {"Pepperoni", "Margherita", "Vegetarian", "Hawaiian", "Veggie Delight", "Meat Lovers", "BBQ Chicken", "Extra Cheese", "Spicy Sausage", "Supreme"};
      
        lbGreatings = new JLabel("Welcome To Pizza Picasso");
        lbGreatings.setBounds(200, 40, 450, 40);
        lbGreatings.setFont(new Font("Serif", Font.PLAIN, 35));
        lbGreatings.setForeground(Color.BLACK);
        this.add(this.lbGreatings);

        lbOrderNo = new JLabel("Order NO:");
        lbOrderNo.setBounds(110, 170, 150, 30);
        lbOrderNo.setFont(new Font("Serif", Font.PLAIN, 25));
        lbOrderNo.setForeground(Color.BLACK);
        this.add(this.lbOrderNo);

        tfOrderNo = new JTextField();
        tfOrderNo.setBounds(300, 170, 230, 30);
        tfOrderNo.setFont(new Font("Ariel", Font.BOLD, 20));
        this.add(this.tfOrderNo);

        lbRate = new JLabel("Amount");
        lbRate.setBounds(110, 230, 200, 30);
        lbRate.setFont(new Font("Serif", Font.PLAIN, 25));
        lbRate.setForeground(Color.BLACK);
        this.add(this.lbRate);

        tfRate = new JTextField();
        tfRate.setBounds(300, 230, 230, 30);
        tfRate.setFont(new Font("Ariel", Font.BOLD, 25));
        this.add(this.tfRate);

        lbCustomerName = new JLabel("Name:");
        lbCustomerName.setBounds(110, 280, 150, 30);
        lbCustomerName.setFont(new Font("Serif", Font.PLAIN, 25));
        lbCustomerName.setForeground(Color.BLACK);
        this.add(this.lbCustomerName);

        tfCustomerName = new JTextField();
        tfCustomerName.setBounds(300, 280, 230, 30);
        tfCustomerName.setFont(new Font("Ariel", Font.BOLD, 20));
        this.add(this.tfCustomerName);

        lbPhoneNo = new JLabel("Phone No");
        lbPhoneNo.setBounds(110, 335, 150, 30);
        lbPhoneNo.setFont(new Font("Serif", Font.PLAIN, 25));
        lbPhoneNo.setForeground(Color.BLACK);
        this.add(this.lbPhoneNo);

        tfPhoneNo = new JTextField();
        tfPhoneNo.setBounds(300, 335, 230, 30);
        tfPhoneNo.setFont(new Font("Ariel", Font.BOLD, 20));
        this.add(this.tfPhoneNo);

        lbPayment = new JLabel("Payment Mode");
        lbPayment.setBounds(110, 400, 190, 30);
        lbPayment.setFont(new Font("Serif", Font.PLAIN, 25));
        lbPayment.setForeground(Color.BLACK);
        this.add(this.lbPayment);

        cbPayment = new JComboBox<>(modes);
        cbPayment.setFont(new Font("Railway", Font.BOLD, 14));
        cbPayment.setBounds(300, 400, 400, 40);
        cbPayment.setBackground(Color.WHITE);
        this.add(cbPayment);

        lbAddress = new JLabel("Address:");
        lbAddress.setFont(new Font("Serif", Font.PLAIN, 25));
        lbAddress.setBounds(110, 470, 190, 30);
        this.add(this.lbAddress);

        taAddress = new JTextArea();
        taAddress.setFont(new Font("Railway", Font.BOLD, 14));
        taAddress.setBounds(300, 470, 400, 100);
        taAddress.setLineWrap(true);
        taAddress.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(this.taAddress);

        lbItems = new JLabel("Toppings: ");
        lbItems.setBounds(110, 590, 150, 30);
        lbItems.setFont(new Font("Serif", Font.PLAIN, 25));
        lbItems.setForeground(Color.BLACK);
        this.add(this.lbItems);

        cbToppings = new JComboBox<>(items);
        cbToppings.setFont(new Font("Railway", Font.BOLD, 14));
        cbToppings.setBounds(300, 590, 400, 40);
        cbToppings.setBackground(Color.WHITE);
        this.add(cbToppings);

        btnAdd = new JButton("Add");
        btnAdd.setBackground(Color.LIGHT_GRAY);
        btnAdd.setForeground(Color.DARK_GRAY);
        btnAdd.setFont(new Font("Ariel", Font.BOLD, 15));
        btnAdd.setBounds(550, 650, 180, 40);
        btnAdd.addActionListener(this);
        this.add(this.btnAdd);

        connectToDatabase();
        getContentPane().setBackground(new Color(220, 220, 220));
        this.setLocation(350, 10);
        this.setVisible(true);
    }
     @Override
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == btnAdd) {
            String orderNo = tfOrderNo.getText();
            String amount = tfRate.getText();
            String customerName = tfCustomerName.getText();
            String phoneNo = tfPhoneNo.getText();
            String paymentMode = (String) cbPayment.getSelectedItem();
            String address = taAddress.getText();
            String selectedTopping = (String) cbToppings.getSelectedItem();
            
                 try {
                   Statement stmt = conn.createStatement();
                   String sql = "INSERT INTO tblOrders (OrderNo, Amount, CustomerName, PhoneNo, PaymentMode, Address, SelectedTopping) " +
                    "VALUES ('" + orderNo + "', '" + amount + "', '" + customerName + "', '" +
                    phoneNo + "', '" + paymentMode + "', '" + address + "', '" + selectedTopping + "')";
                  
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(this, "Order information added to database.");
                    
                    stmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        
    }
    
}
    private void connectToDatabase() {
        
        String url = "jdbc:mysql:///pizzaordersystem"; 
        String user = "root"; 
        String password = "root"; 

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MyPizzaOrder();
    }
}
