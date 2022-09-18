import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame{

    public JButton productButton = new JButton("Products");
    public JButton customerButton = new JButton("Customers");
    public JButton orderButton = new JButton("Orders");

    public MainMenuView() {

        this.setTitle("Main Menu");
        this.setSize(new Dimension(600,150));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout

        JPanel line1 = new JPanel();
        line1.add(productButton);
        line1.add(customerButton);
        line1.add(orderButton);
        this.getContentPane().add(line1);


    }
}