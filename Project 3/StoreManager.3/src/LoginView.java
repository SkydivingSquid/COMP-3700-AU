import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame{

    public JTextField userName_TF = new JTextField(30);
    public JPasswordField userPwrd_PF = new JPasswordField(30);
    public JButton loginButton = new JButton("Login");
    public JButton createButton = new JButton("Create New Account");

    public LoginView() {

        this.setTitle("Login Menu");
        this.setSize(new Dimension(500,150));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout

        JPanel line1 = new JPanel();
        line1.add(new JLabel("Username:"));
        line1.add(userName_TF);
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        line2.add(new JLabel("Password:"));
        line2.add(userPwrd_PF);
        this.getContentPane().add(line2);

        JPanel line3 = new JPanel();
        line3.add(loginButton);
        line3.add(createButton);
        this.getContentPane().add(line3);

    }

}
