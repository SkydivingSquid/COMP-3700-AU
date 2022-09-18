import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class OrdersNew_C {
    private JPanel mainPanel;
    private JTextField productIDTF;
    private JTextField quantityTF;
    private JTextField keywordTF;
    private JButton searchProductsButton;
    private JButton placeOrderButton;
    private JTextArea resultsTA;
    private Client client;

    private int userID;
    private int orderID;

    public OrdersNew_C(Client client) {

        this.client = client;
        productIDTF.setText("");
        quantityTF.setText("");
        keywordTF.setText("");

        searchProductsButton. addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String keyword = keywordTF.getText();
                Gson gson = new Gson();

                String searchOrderString = gson.toJson(keyword);
                Message message = new Message(Message.SEARCH_PRODUCT, searchOrderString);
                client.sendMessage(message);

            }
        });



        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //create a new order & new orderline immediately
                Gson gson = new Gson();
                Order order = new Order();
                OrderLine orderline = new OrderLine();
                //No need to set orderID because it's auto incremented

                Random rand = new Random();

               //Obtain a number between [0 - 9,999].
               orderID = rand.nextInt(10000);


                //Save UserID into Order
                System.out.println(client.getUser());
                userID = client.getUser().getUserID();
                order.setUserID(userID);
                order.setOrderID(orderID);

                System.out.println("" + userID);

                String orderString = gson.toJson(order);
                Message message1 = new Message(Message.SAVE_ORDER,orderString);
                client.sendMessage(message1);

                //Get orderId from Order to save into OrderLine
                orderline.setOrderID(orderID);


                //Grabbing from actual fields on screen.
                orderline.setProductID(Integer.parseInt(productIDTF.getText()));
                orderline.setQuantity(Double.parseDouble(quantityTF.getText()));

                //Order
                //Figure out how to set cost by pulling item, cost then * by quantity

                //Orderline
                //Add all orderline item costs together...
                //this means orderline needs to accept multiple items... wtf


                String orderlineString = gson.toJson(orderline);

                Message message2 = new Message(Message.SAVE_ORDERLINE, orderlineString);
                client.sendMessage(message2);
            }
        });



    }
    public JPanel getMainPanel() {return mainPanel;}

    public void updateProductInfo(ProductListModel products) {resultsTA.setText(products.toString());
    }

} //final brace
