import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewAccountController implements ActionListener {

    NewAccountView myView;
    DataAccess myDAO;

    public NewAccountController(NewAccountView view, DataAccess dao) {
        myView = view;
        myDAO = dao;
        myView.btnSave.addActionListener(this);
    }

    @Override
    //Define what to do when buttons are clicked
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == myView.btnSave) {      // button Save is clicked
            saveNewAccount();
        }

    }

    //this is never called???
    private void loadNewAccountAndDisplay() {
        try {
            String userName = myView.txtUserName.getText();
            NewAccountModel newAccountModel = myDAO.loadNewAccount(userName);
            myView.txtUserName.setText(newAccountModel.userName);
            myView.txtDisplayName.setText(newAccountModel.displayName);

        }
        //This should never catch.
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for NewAccount");
            ex.printStackTrace();
        }
    }



    //Define how save button Works
    //How do we get this to prompt if the username already exists?
    private void saveNewAccount() {
        NewAccountModel newAccountModel = new NewAccountModel();

            newAccountModel.userName = myView.txtUserName.getText();
            newAccountModel.password = myView.txtPassword.getText();
            newAccountModel.displayName = myView.txtDisplayName.getText();

            myDAO.saveNewAccount(newAccountModel);
            JOptionPane.showMessageDialog(null, "Account created successfully!");

    }


} //final brace
