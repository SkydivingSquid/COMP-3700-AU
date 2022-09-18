import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdersExisting_C {
    private int count = 0;
    private JPanel mainPanel;
    private JTextArea resultsTA;
    private JTextField quantityTF;
    private JTextField orderIDTF;
    private JTextField productIDTF;
    private JButton deleteOrderButton;
    private JButton updateOrderButton;
    private JButton refreshButton;
    private Client client;

    public OrdersExisting_C(Client client) {
        this.client = client;

        //clears any previous information.
        resultsTA.setText("");
        quantityTF.setText("");
        orderIDTF.setText("");
        productIDTF.setText("");

        updateOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                OrderLine orderline = new OrderLine();

                orderline.setOrderID(Integer.parseInt(orderIDTF.getText()));
                orderline.setProductID(Integer.parseInt(productIDTF.getText()));
                orderline.setQuantity(Integer.parseInt(quantityTF.getText()));

                Gson gson = new Gson();

                String productString = gson.toJson(orderline);

                Message message = new Message(Message.SAVE_ORDERLINE, productString);
                client.sendMessage(message);
            }
        });

        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = new Order();

                order.setOrderID(Integer.parseInt(orderIDTF.getText()));

                Gson gson = new Gson();

                String productString = gson.toJson(order);

                Message message = new Message(Message.DELETE_ORDER, productString);
                client.sendMessage(message);
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //At the time of submission, this method was unable to be solved.
                //Several hours were put into it, but ran out of time.
                //Did not want to bother Tam, since I found at 16 students had not
                //even started Project 3 as of April 28th and would likely swamp him.

                //METHOD FLOW
                //Do a search for orders here. Send userID.
                //This will go to Orders and grab the associated orderIDs...
                //It will then search OrderLine and return the orders with that orderID.


                //Temporary placeholder for search order method only to show intention.
               /* if(count == 0) {
                    resultsTA.setText("OrderID: 2439\t\tProductID: 2\t\tName: Banana\t\tQuantity: 3.0\n\n" +
                            "OrderID: 8265\t\tProductID: 13\t\tName: Candy\t\tQuantity: 13.0");
                    count++;
                }

                else {
                    resultsTA.setText("OrderID: 2439\t\tProductID: 2\t\tName: Banana\t\tQuantity: 3.0\n\n" +
                            "OrderID: 8265\t\tProductID: 3\t\tName: Cheese\t\tQuantity: 2.0");
                }
                */
            }
        });

    }
    public JPanel getMainPanel() {return mainPanel;}

} //final brace
