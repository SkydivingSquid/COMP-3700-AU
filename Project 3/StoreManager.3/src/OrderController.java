import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderController implements ActionListener {

    OrderView myView;
    DataAccess myDAO;

    public OrderController(OrderView view, DataAccess dao) {
        myView = view;
        myDAO = dao;
        myView.btnLoad.addActionListener(this);
        myView.btnSave.addActionListener(this);
        myView.btnDelete.addActionListener(this);
    }

    //Define what to do when buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myView.btnLoad) {      // button Load is clicked
            loadOrderAndDisplay();
        }

        if (e.getSource() == myView.btnSave) {      // button Save is clicked
            saveOrder();
        }

        if (e.getSource() == myView.btnDelete) {      // button Delete is clicked
            deleteOrder();
        }

    }

    //Define how save button works
    private void saveOrder() {
        OrderModel orderModel = new OrderModel();

        try {
            int orderID = Integer.parseInt(myView.txtOrderID.getText());
            orderModel.orderID = orderID;
            orderModel.orderDate = myView.txtOrderDate.getText();
            orderModel.customer = myView.txtOrderCustomer.getText();
            orderModel.totalCost = Double.parseDouble(myView.txtOrderTotalCost.getText());
            orderModel.totalTax = Double.parseDouble(myView.txtOrderTotalTax.getText());

            myDAO.saveOrder(orderModel);
            JOptionPane.showMessageDialog(null, "Order saved successfully!");


        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for OrderID");
            ex.printStackTrace();
        }
    }

    //Define how load button works
    private void loadOrderAndDisplay() {
        try {
            int orderID = Integer.parseInt(myView.txtOrderID.getText());
            OrderModel orderModel = myDAO.loadOrder(orderID);
            myView.txtOrderDate.setText(orderModel.orderDate);
            myView.txtOrderCustomer.setText(orderModel.customer);
            myView.txtOrderTotalCost.setText(String.valueOf(orderModel.totalCost));
            myView.txtOrderTotalTax.setText(String.valueOf(orderModel.totalTax));

        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for OrderID");
            ex.printStackTrace();
        }
    }

    //Define how delete button works
    private void deleteOrder() {
        OrderModel orderModel = new OrderModel();

        try {
            int orderID = Integer.parseInt(myView.txtOrderID.getText());
            orderModel.orderID = orderID;
            orderModel.orderDate = myView.txtOrderDate.getText();
            orderModel.customer = myView.txtOrderCustomer.getText();
            orderModel.totalCost = Double.parseDouble(myView.txtOrderTotalCost.getText());
            orderModel.totalTax = Double.parseDouble(myView.txtOrderTotalTax.getText());

            myDAO.deleteOrder(orderModel);
            JOptionPane.showMessageDialog(null, "Order deleted successfully!");

        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for OrderID");
            ex.printStackTrace();
        }
    }


}
