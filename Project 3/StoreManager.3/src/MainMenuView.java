import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame{

    public JButton productButton = new JButton("Products");
    public JButton customerButton = new JButton("Customers");
    public JButton orderButton = new JButton("Orders");
    public JButton infoButton = new JButton("Change Information");
    public JButton securityButton = new JButton("Change Password");

    public MainMenuView() {

        this.setTitle("Main Menu");
        this.setSize(new Dimension(600,150));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout

        //Can we modify this according to role? Like if manager then this, is employee then this?
        //Or do we need to make 3 separate menus?


        //Can I do something like:
        //  If <this user . user role == manager, else if == employee , else?

        //** This question would solve a lot of issues right now, such as different displays
        // In product, etc. as well as probably solve the login issue.

        //Manager View
        JPanel line1 = new JPanel();
        line1.add(productButton);
        line1.add(customerButton);
        line1.add(orderButton);
        this.getContentPane().add(line1);

        //Employee View
//        JPanel line1 = new JPanel();
//        line1.add(customerButton);
//        line1.add(orderButton);
//        this.getContentPane().add(line1);
//
//        //Customer View:
//        JPanel line1 = new JPanel();
//        line1.add(productButton);
//        line1.add(orderButton);
//        this.getContentPane().add(line1);
//
//        JPanel line2 = new JPanel();
//        line1.add(infoButton);
//        line1.add(securityButton);
//        this.getContentPane().add(line1);


    }
}