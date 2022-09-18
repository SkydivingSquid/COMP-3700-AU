import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerViewController {
    private JButton logoutButton;
    private JButton productsAndOrdersButton;
    private JButton updateAccountInformationButton;
    private JPanel mainPanel;

    private Client client;

    public CustomerViewController(Client client) {
        this.client = client;

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //clear user
                //hide the current view
                //display the login screen
                client.setUser(null);
                client.getMainFrame().setContentPane(client.getLoginViewController().getMainPanel());
                client.getMainFrame().setVisible(true);
            }
        });

        productsAndOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Orders and Products");
                // Create the view that you want to display
                frame.setContentPane(client.getOrdersViewController_c().getMainPanel());
                frame.setMinimumSize(new Dimension(800, 400));
                frame.pack();
                frame.setVisible(true);
            }
        });

        updateAccountInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.setAccountUpdateController(new AccountUpdateController(client));
                JFrame frame = new JFrame("Account Management");
                // Create the view that you want to display
                frame.setContentPane(client.getAccountUpdateController().getMainPanel());
                frame.setMinimumSize(new Dimension(800, 400));
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public JPanel getMainPanel() {return mainPanel;}

    } //final brace
