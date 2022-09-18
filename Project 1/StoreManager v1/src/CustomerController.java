import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerController implements ActionListener {

    CustomerView myView;
    DataAccess myDAO;

    public CustomerController(CustomerView view, DataAccess dao) {
        myView = view;
        myDAO = dao;
        myView.btnLoad.addActionListener(this);
        myView.btnSave.addActionListener(this);
        myView.btnDelete.addActionListener(this);
    }

    @Override
    //Define what to do when buttons are clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myView.btnLoad) {      // button Load is clicked
            loadCustomerAndDisplay();
        }

        if (e.getSource() == myView.btnSave) {      // button Save is clicked
            saveCustomer();
        }

        if (e.getSource() == myView.btnDelete) {      // button Delete is clicked
            deleteCustomer();
        }

    }

    //Define how save button Works
    private void saveCustomer() {
        CustomerModel customerModel = new CustomerModel();

        try {
            int customerID = Integer.parseInt(myView.txtCustomerID.getText());
            customerModel.customerID = customerID;
            customerModel.firstName = myView.txtFirstName.getText();
            customerModel.lastName = myView.txtLastName.getText();
            customerModel.address = myView.txtAddress.getText();

            myDAO.saveCustomer(customerModel);
            JOptionPane.showMessageDialog(null, "Customer saved successfully!");


        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for CustomerID");
            ex.printStackTrace();
        }    }

    //Define how Load button Works
    private void loadCustomerAndDisplay() {
        try {
            int customerID = Integer.parseInt(myView.txtCustomerID.getText());
            CustomerModel customerModel = myDAO.loadCustomer(customerID);
            myView.txtFirstName.setText(customerModel.firstName);
            myView.txtLastName.setText(customerModel.lastName);
            myView.txtAddress.setText(customerModel.address);

        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for CustomerID");
            ex.printStackTrace();
        }
    }


    //Define how delete button Works
    private void deleteCustomer() {
        CustomerModel customerModel = new CustomerModel();

        try {
            int customerID = Integer.parseInt(myView.txtCustomerID.getText());
            customerModel.customerID = customerID;
            customerModel.firstName = myView.txtFirstName.getText();
            customerModel.lastName = myView.txtLastName.getText();
            customerModel.address = myView.txtAddress.getText();

            myDAO.deleteCustomer(customerModel);
            JOptionPane.showMessageDialog(null, "Customer deleted successfully!");


        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for CustomerID");
            ex.printStackTrace();
        }
    }



} //final brace
