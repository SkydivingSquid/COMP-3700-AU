import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame{

    public JButton UpLoadDownloadButton = new JButton("Upload/Download Notes");
    public JButton searchButton = new JButton("Search Notes");

    public MainMenuView() {

        this.setTitle("Main Menu");
        this.setSize(new Dimension(420,150));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout

        JPanel line1 = new JPanel();
        line1.add(UpLoadDownloadButton);
        line1.add(searchButton);
        this.getContentPane().add(line1);


    }
}