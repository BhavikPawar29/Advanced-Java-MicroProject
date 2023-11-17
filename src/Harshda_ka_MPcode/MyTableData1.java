import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyTableData1 extends JFrame implements ActionListener {
    JTable table;
    JScrollPane scrollPane;
    JButton btnAdd;
    JLabel lbOrderNo, lbRate, lbCustomerName, lbAmount, lbPhoneNo, lbPayment, lbItems, lbAddress;
    JTextField tfOrderNo, tfRate, tfCustomerName, tfAmount, tfPhoneNo;
    JComboBox<String> cbPayment;
    JList<String> lstItems;
    JTextArea taAddress;
    JScrollPane paneForItems;
   
    public MyTableData1() {
        setTitle("Pizza Ordering and Billing System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        table = new JTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        scrollPane.setVisible(false);
        addComponent(scrollPane, gbc, 0, 0, 2, 1);

        lbOrderNo = new JLabel("Order No: ");
        tfOrderNo = new JTextField(15);
        tfOrderNo.setPreferredSize(new Dimension(150, 25));
        addComponent(lbOrderNo, gbc, 0, 1, 1, 1);
        addComponent(tfOrderNo, gbc, 1, 1, 1, 1);

        lbRate = new JLabel("Rate: ");
        tfRate = new JTextField(15);
        tfRate.setPreferredSize(new Dimension(150, 25));
        addComponent(lbRate, gbc, 0, 2, 1, 1);
        addComponent(tfRate, gbc, 1, 2, 1, 1);

        lbCustomerName = new JLabel("Customer Name:");
        tfCustomerName = new JTextField(15);
        tfCustomerName.setPreferredSize(new Dimension(150, 25));
        addComponent(lbCustomerName, gbc, 0, 3, 1, 1);
        addComponent(tfCustomerName, gbc, 1, 3, 1, 1);

        lbAmount = new JLabel("Amount:");
        tfAmount = new JTextField(15);
        tfAmount.setPreferredSize(new Dimension(150, 25));
        addComponent(lbAmount, gbc, 0, 4, 1, 1);
        addComponent(tfAmount, gbc, 1, 4, 1, 1);

        lbPhoneNo = new JLabel("Phone No:");
        tfPhoneNo = new JTextField(15);
        tfPhoneNo.setPreferredSize(new Dimension(150, 25));
        addComponent(lbPhoneNo, gbc, 0, 5, 1, 1);
        addComponent(tfPhoneNo, gbc, 1, 5, 1, 1);

        lbPayment = new JLabel("Payment Method:");
        addComponent(lbPayment, gbc, 0, 6, 1, 1);
        cbPayment = new JComboBox<>();
        cbPayment.addItem("Cash on Delivery");
        cbPayment.addItem("Debit Card");
        cbPayment.addItem("Net Banking");
        addComponent(cbPayment, gbc, 1, 6, 1, 1);

        lbAddress = new JLabel("Address: ");
        addComponent(lbAddress, gbc, 0, 7, 1, 1);
        taAddress = new JTextArea(3, 15);
        addComponent(taAddress, gbc, 1, 7, 1, 1);

        lbItems = new JLabel("Pizza Toppings: ");
        addComponent(lbItems, gbc, 0, 8, 1, 1);
        String[] items = {"Pepperoni", "Margherita", "Vegetarian", "Hawaiian", "Veggie Delight", "Meat Lovers", "BBQ Chicken", "Extra Cheese", "Spicy Sausage", "Supreme"};
        lstItems = new JList<>(items);
        paneForItems = new JScrollPane(lstItems);
        addComponent(paneForItems, gbc, 1, 8, 1, 1);
        
        btnAdd = new JButton("Generate Bill");
        btnAdd.addActionListener(this);
        addComponent(btnAdd, gbc, 0, 9, 2, 1);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addComponent(Component comp, GridBagConstraints gbc, int x, int y, int width, int height) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(comp, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Idhar tumhara button pai kuch kam kroo..... :)
    }

    public static void main(String[] args) {
        new MyTableData1();
    }
}
