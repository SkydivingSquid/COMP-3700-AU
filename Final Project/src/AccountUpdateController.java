import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountUpdateController {

    private JPanel mainPanel;
    private JButton saveButton;
    private JTextField usernameTF;
    private JTextField nameTF;
    private JTextField addressTF;
    private JTextField paymentTF;
    private JPasswordField userPasswrdTF;
    private Client client;

    public AccountUpdateController(Client client) {
        this.client = client;
        //Auto-load username, and other values here.
        usernameTF.setText(this.client.getUser().getUserName());
        usernameTF.setEditable(false);
        nameTF.setText(this.client.getUser().getFullName());
        addressTF.setText(this.client.getUser().getAddress());
        paymentTF.setText(this.client.getUser().getPayment());
        userPasswrdTF.setText(this.client.getUser().getPassword());

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users user = new Users();

                user.setUserName(usernameTF.getText());
                user.setFullName(nameTF.getText());
                user.setAddress(addressTF.getText());
                user.setPayment(paymentTF.getText());
                user.setPassword(new String(userPasswrdTF.getPassword())); //I think?

                Gson gson = new Gson();

                String userString = gson.toJson(user);

                Message message = new Message(Message.UPDATE_ACCOUNT, userString);
                client.sendMessage(message);
            }
        });
    }

    public JPanel getMainPanel() {return mainPanel;}


}
