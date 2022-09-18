import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdersViewController_C {
    private JPanel mainPanel;
    private JButton newOrderButton;
    private JButton existingOrderButton;

    private Client client;

    public OrdersViewController_C(Client client) {
        this.client = client;

        newOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("New Order");
                // Create the view that you want to display
                frame.setContentPane(client.getOrdersNew_c().getMainPanel());
                frame.setMinimumSize(new Dimension(800, 400));
                frame.pack();
                frame.setVisible(true);
            }
        });

        existingOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Existing Orders");
                // Create the view that you want to display
                frame.setContentPane(client.getOrdersExisting_c().getMainPanel());
                frame.setMinimumSize(new Dimension(800, 400));
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public JPanel getMainPanel() {return mainPanel;}
}
