import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    LoginView thisLoginView;

    public LoginController(LoginView LGV){
        thisLoginView = LGV;
        thisLoginView.loginButton.addActionListener(this);
        thisLoginView.createButton.addActionListener(this);
    }

    @Override //This will just bring user to mainmenu no matter what. Have to change to verify login creds.
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == thisLoginView.loginButton){

        //THIS IS WHERE IS WILL MAYBE CHECK FOR USERNAME + PWRD

            //IF VALID DISPLAY A MENU WITH DIFFERENT OPTIONS DEPENDING ON ROLE
            //Will need to modify this.
            StoreManager.getInstance().getMainMenuView().setVisible(true);

            // get the username and password
            String username = thisLoginView.userName_TF.getText();
            String password = new String(thisLoginView.userPwrd_PF.getPassword());
            // send that information to server
            // wait for the server to send back the account information
            // decide what to do next



        }

        if(event.getSource() == thisLoginView.createButton){
            StoreManager.getInstance().getNewAccountView().setVisible(true);
        }

    }




}
