import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        StoreManager.getInstance().getLoginView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StoreManager.getInstance().getLoginView().setVisible(true);
    }
}
