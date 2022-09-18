import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerViewController {
    private JPanel mainPanel;
    private JButton productsButton;
    private JButton logoutButton;

    private Client client;

    public ManagerViewController(Client client) {
        this.client = client;

        //Opens the CreateNewAccount window when the button is pressed from the main screen.
        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Manage Products");
                // Create the view that you want to display
                frame.setContentPane(client.getProductViewController().getMainPanel());
                frame.setMinimumSize(new Dimension(800, 400));
                frame.pack();
                frame.setVisible(true);
            }
        });

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


    }



    public JPanel getMainPanel() {
        return mainPanel;
    }
}
