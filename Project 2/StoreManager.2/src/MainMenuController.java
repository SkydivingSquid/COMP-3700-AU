import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener{

    MainMenuView thisMainMenuView;


    public MainMenuController(MainMenuView MMV){
        thisMainMenuView = MMV;
        thisMainMenuView.productButton.addActionListener(this);
        thisMainMenuView.customerButton.addActionListener(this);
        thisMainMenuView.orderButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == thisMainMenuView.productButton){
            StoreManager.getInstance().getProductView().setVisible(true);
        }
        if(event.getSource() == thisMainMenuView.customerButton){
            StoreManager.getInstance().getCustomerView().setVisible(true);
        }
        if(event.getSource() == thisMainMenuView.orderButton){
            StoreManager.getInstance().getOrderView().setVisible(true);
        }

    }



}