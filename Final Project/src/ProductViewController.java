import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductViewController {
    private JTextField productIDTF;
    private JTextField productNameTF;
    private JTextField productPriceTF;
    private JTextField productQuantityTF;
    private JButton loadProductButton;
    private JButton saveProductButton;
    private JPanel mainPanel;
    private JButton deleteProductButton;

    private Client client;

    public ProductViewController(Client client) {
        this.client = client;

        // Sends server the load request when load button is pressed
        loadProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productID = productIDTF.getText();
                try {
                    Integer.parseInt(productID);
                } catch (NumberFormatException ex) {
                    //send popup.
                    return;
                }
                Message message = new Message(Message.LOAD_PRODUCT, productID);
                client.sendMessage(message);
            }
        });

        //Sends server the save request when the save button is pressed
        saveProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = new Product();

                product.setProductID(Integer.parseInt(productIDTF.getText()));
                product.setName(productNameTF.getText());
                product.setPrice(Double.parseDouble(productPriceTF.getText()));
                product.setQuantity(Double.parseDouble(productQuantityTF.getText()));

                Gson gson = new Gson();

                String productString = gson.toJson(product);

                Message message = new Message(Message.SAVE_PRODUCT, productString);
                client.sendMessage(message);
            }
        });

        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = new Product();

                product.setProductID(Integer.parseInt(productIDTF.getText()));
                product.setName(productNameTF.getText());
                product.setPrice(Double.parseDouble(productPriceTF.getText()));
                product.setQuantity(Double.parseDouble(productQuantityTF.getText()));

                Gson gson = new Gson();

                String productString = gson.toJson(product);

                Message message = new Message(Message.DELETE_PRODUCT, productString);
                client.sendMessage(message);
            }
        });
    }

    public JPanel getMainPanel() {return mainPanel;}

    //Not sure if this is still necessary, but I assume this updates the info in the panel?
    public void updateProductInfo(Product product) {
        productIDTF.setText(String.valueOf(product.getProductID()));
        productNameTF.setText(product.getName());
        productPriceTF.setText(String.valueOf(product.getPrice()));
        productQuantityTF.setText(String.valueOf(product.getQuantity()));
    }
}
