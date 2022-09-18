import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginViewController {
    private JPanel mainPanel;
    private JTextField usernameTF;
    private JPasswordField passTF;
    private JButton loginButton;
    private JButton createNewAccountButton;

    private Client client;

    public LoginViewController(Client client) {
        this.client = client;

        //Opens the CreateNewAccount window when the button is pressed from the main screen.
        createNewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Account Registration");
                // Create the view that you want to display
                frame.setContentPane(client.getCreateNewAccountViewController().getMainPanel());
                frame.setMinimumSize(new Dimension(800, 400));
                frame.pack();
                frame.setVisible(true);
                usernameTF.setText("");
                passTF.setText("");
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LoginInfo loginInfo = new LoginInfo(usernameTF.getText(), new String(passTF.getPassword()));
                Gson gson = new Gson();
                Message message = new Message(Message.LOGIN_REQUEST, gson.toJson(loginInfo));
                client.sendMessage(message);
                usernameTF.setText("");
                passTF.setText("");

            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
