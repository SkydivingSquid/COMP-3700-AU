import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {

    //Create buttons on Main menu
    public JButton productBtn = new JButton("Product");
    public JButton orderBtn = new JButton("Order");
    public JButton customerBtn = new JButton("Customer");



    public MainScreen() {
        //Create Main Menu dimensions
        this.setTitle("Main Screen");
        this.setSize(new Dimension(600, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout

        //Place buttons on Main menu
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(productBtn);
        buttonPanel.add(orderBtn);
        buttonPanel.add(customerBtn);

        //Make Product Button Interactive
        productBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StoreManager.getInstance().getProductView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                StoreManager.getInstance().getProductView().setVisible(true); // Show the ProductView!
            }
        });

        //Make Order Button Interactive
        orderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StoreManager.getInstance().getOrderView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                StoreManager.getInstance().getOrderView().setVisible(true); // Show the OrderView!
            }
        });

        //Make Customer Button Interactive
        customerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StoreManager.getInstance().getCustomerView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                StoreManager.getInstance().getCustomerView().setVisible(true); // Show the CustomerView!
            }
        });


        this.getContentPane().add(buttonPanel);
    }

}
