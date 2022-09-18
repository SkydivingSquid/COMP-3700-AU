import javax.swing.*;
import java.awt.*;

public class OrderView extends JFrame {

    public JTextField txtOrderID = new JTextField(30);
    public JTextField txtOrderDate = new JTextField(30);
    public JTextField txtOrderCustomer = new JTextField(30);
    public JTextField txtOrderTotalCost = new JTextField(30);
    public JTextField txtOrderTotalTax = new JTextField(30);

    //Instantiate buttons
    public JButton btnLoad = new JButton("Load");
    public JButton btnSave = new JButton("Save");
    public JButton btnDelete = new JButton("Delete");

    public OrderView() {

        this.setTitle("Order View");
        this.setSize(new Dimension(600, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout

        JPanel line1 = new JPanel();
        line1.add(new JLabel("Order ID"));
        line1.add(txtOrderID);
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        line2.add(new JLabel("Order Date"));
        line2.add(txtOrderDate);
        this.getContentPane().add(line2);

        JPanel line3 = new JPanel();
        line3.add(new JLabel("Customer"));
        line3.add(txtOrderCustomer);
        this.getContentPane().add(line3);

        JPanel line4 = new JPanel();
        line4.add(new JLabel("Total Cost"));
        line4.add(txtOrderTotalCost);
        this.getContentPane().add(line4);

        JPanel line5 = new JPanel();
        line5.add(new JLabel("Total Tax"));
        line5.add(txtOrderTotalTax);
        this.getContentPane().add(line5);

        //Make buttons visible in Viewer
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnLoad);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnDelete);

        this.getContentPane().add(buttonPanel);

    }

}
