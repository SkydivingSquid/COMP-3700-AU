import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductController implements ActionListener {

    ProductView myView;
    DataAccess myDAO;

    public ProductController(ProductView view, DataAccess dao) {
        myView = view;
        myDAO = dao;
        myView.btnLoad.addActionListener(this);
        myView.btnSave.addActionListener(this);
        myView.btnDelete.addActionListener(this);

    }

    //Define what buttons do when clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myView.btnLoad) {      // button Load is clicked
            loadProductAndDisplay();
        }

        if (e.getSource() == myView.btnSave) {      // button Save is clicked
            saveProduct();
        }

        if (e.getSource() == myView.btnDelete) {      // button Delete is clicked
            deleteProduct();
        }

    }

    //Define how save button works
    private void saveProduct() {
        ProductModel productModel = new ProductModel();

        try {
            int productID = Integer.parseInt(myView.txtProductID.getText());
            productModel.productID = productID;
            productModel.name = myView.txtProductName.getText();
            productModel.price = Double.parseDouble(myView.txtProductPrice.getText());
            productModel.quantity = Double.parseDouble(myView.txtProductQuantity.getText());

            myDAO.saveProduct(productModel);
            JOptionPane.showMessageDialog(null, "Product saved successfully!");


        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for ProductID");
            ex.printStackTrace();
        }
    }

    //Define how load button works
    private void loadProductAndDisplay() {
        try {
            int productID = Integer.parseInt(myView.txtProductID.getText());
            ProductModel productModel = myDAO.loadProduct(productID);
            myView.txtProductName.setText(productModel.name);
            myView.txtProductPrice.setText(String.valueOf(productModel.price));
            myView.txtProductQuantity.setText(String.valueOf(productModel.quantity));

        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for ProductID");
            ex.printStackTrace();
        }
    }

    //Define how delete button works
    private void deleteProduct() {
        ProductModel productModel = new ProductModel();

        try {
            int productID = Integer.parseInt(myView.txtProductID.getText());
            productModel.productID = productID;
            productModel.name = myView.txtProductName.getText();
            productModel.price = Double.parseDouble(myView.txtProductPrice.getText());
            productModel.quantity = Double.parseDouble(myView.txtProductQuantity.getText());

            myDAO.deleteProduct(productModel);
            JOptionPane.showMessageDialog(null, "Product deleted successfully!");

        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for ProductID");
            ex.printStackTrace();
        }
    }
}
