import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener{

    MainMenuView thisMainMenuView;


    public MainMenuController(MainMenuView MMV){
        thisMainMenuView = MMV;
        thisMainMenuView.UpLoadDownloadButton.addActionListener(this);
        thisMainMenuView.searchButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == thisMainMenuView.UpLoadDownloadButton){
            StoreManager.getInstance().getNoteView().setVisible(true);
        }
        if(event.getSource() == thisMainMenuView.searchButton){
            StoreManager.getInstance().getSearchView().setVisible(true);
        }
    }



}