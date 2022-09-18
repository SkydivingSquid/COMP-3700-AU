import javax.swing.*;
import java.awt.*;

public class NewAccountView extends JFrame {

    //public JTextField txUserID = new JTextField(30);
    public JTextField txtUserName = new JTextField(30);
    public JPasswordField txtPassword = new JPasswordField(30);
    public JTextField txtDisplayName = new JTextField(30);


    //Instantiate buttons
    public JButton btnSave = new JButton("Create New Account");

    public NewAccountView() {

        this.setTitle("New User");
        this.setSize(new Dimension(500, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout

        JPanel line1 = new JPanel();
        line1.add(new JLabel("Username"));
        line1.add(txtUserName);
        this.getContentPane().add(line1);

        //Consider doing a "verify password" later.
        JPanel line2 = new JPanel();
        line2.add(new JLabel("Password"));
        line2.add(txtPassword);
        this.getContentPane().add(line2);

        JPanel line3 = new JPanel();
        line3.add(new JLabel("Real Name"));
        line3.add(txtDisplayName);
        this.getContentPane().add(line3);

        //Make buttons visible in Viewer.
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnSave);

        this.getContentPane().add(buttonPanel);

    }
}
