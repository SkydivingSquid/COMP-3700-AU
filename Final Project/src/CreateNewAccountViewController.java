import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNewAccountViewController {
    private JPanel mainPanel;
    private JPasswordField passwordTF;
    private JTextField usernameTF;
    private JTextField FullNameTF;
    private JButton createNewAccountButton;

    private Client client;

    public CreateNewAccountViewController(Client client) {
        this.client = client;

        createNewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users users = new Users();

                users.setUserName(usernameTF.getText());
                users.setPassword(new String(passwordTF.getPassword()));
                users.setFullName(FullNameTF.getText());

                Gson gson = new Gson();

                String usersString = gson.toJson(users);

                Message message = new Message(Message.SAVE_USERS, usersString);
                client.sendMessage(message);
            }
        });
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }


    public void updateUserInfo(Users users) {
        usernameTF.setText(users.getUserName());
        passwordTF.setText(users.getPassword());
        FullNameTF.setText(users.getFullName());
    }

}
