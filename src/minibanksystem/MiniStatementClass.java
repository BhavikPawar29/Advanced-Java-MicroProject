package minibanksystem;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MiniStatementClass extends JFrame {

    JLabel lbCardNo;
    JTable statementTable;
    DefaultTableModel tableModel;

    String strPinNumber;
    MiniStatementClass(String pinNumber) {
        this.setTitle("Mini-Statement");
        this.setSize(600, 400);
        this.setLocation(20, 20);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        this.strPinNumber = pinNumber;
        
        lbCardNo = new JLabel();
        this.add(lbCardNo, BorderLayout.NORTH);

        // Create a table model
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Date");
        tableModel.addColumn("Transaction Type");
        tableModel.addColumn("Amount");

        // Create the JTable with the table model
        statementTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(statementTable);
        this.add(scrollPane, BorderLayout.CENTER);

        fetchMiniStatement();
        getContentPane().setBackground(Color.WHITE);
    }

    public void fetchMiniStatement() {
        DataBaseConnection connect = new DataBaseConnection();
        int intBalance = 0;

        try {
            ResultSet result = connect.stateMen.executeQuery("SELECT * FROM tbllogin WHERE pinNo = '" + strPinNumber + "'");

            if (result.next()) {
                String cardNo = result.getString("cardno");
                lbCardNo.setText("Card Number: " + cardNo.substring(0, 4) + " - XXXXXXXX - " + cardNo.substring(12));
            }

            result = connect.stateMen.executeQuery("SELECT * FROM tblbank WHERE pin = '" + strPinNumber + "'");

            while (result.next()) {
                String date = result.getString("date");
                String type = result.getString("type");
                String amount = result.getString("amount");

                tableModel.addRow(new String[] { date, type, amount });

                if (type.equals("Deposit")) {
                    intBalance += Integer.parseInt(amount);
                } else {
                    intBalance -= Integer.parseInt(amount);
                }
            }

            // Add the balance row to the table
            tableModel.addRow(new String[] { "", "Your current account balance", "Rs " + intBalance });
            
            // Close the PreparedStatement and database connection
            connect.stateMen.close();
            connect.conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new MiniStatementClass("");
    }
}
