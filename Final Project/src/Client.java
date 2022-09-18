import com.google.gson.Gson;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Base64;

public class Client {

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private Gson gson;

    private Worker worker;

    private SecretKey secretKey;

    private byte[] initializationVector;

    //Association to controllers
    private LoginViewController loginViewController;
    private CreateNewAccountViewController createNewAccountViewController;
    private ProductViewController productViewController;
    private ManagerViewController managerViewController;
    private CustomerViewController customerViewController;
    private AccountUpdateController accountUpdateController;
    private OrdersViewController_C ordersViewController_c;
    private OrdersNew_C ordersNew_c;
    private OrdersExisting_C ordersExisting_c;
    private Users user;
    private JFrame mainFrame;

    public Client() {
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 12002);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            // send the secret key
            secretKey = KeyService.createAESKey();

            String keyString = KeyService.convertSecretKeyToString(secretKey);

            dataOutputStream.writeUTF(keyString);

            // send the initialization vector

            initializationVector = KeyService.createInitializationVector();

            String vectorString = Base64.getEncoder().encodeToString(initializationVector);

            dataOutputStream.writeUTF(vectorString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        gson = new Gson();

        //Worker used for multithreading.
        worker = new Worker();
        Thread workerThread = new Thread(worker);
        workerThread.start();

        //Initialization
        this.loginViewController = new LoginViewController(this);
        this.createNewAccountViewController = new CreateNewAccountViewController(this);
        this.productViewController = new ProductViewController(this);
        this.managerViewController = new ManagerViewController(this);
        this.customerViewController = new CustomerViewController(this);
        this.ordersViewController_c = new OrdersViewController_C(this);
        this.ordersNew_c = new OrdersNew_C(this);
        this.ordersExisting_c = new OrdersExisting_C(this);

    }

    public void sendMessage(Message message) {
        String str = gson.toJson(message);
        try {

            byte[] cipherText
                    = KeyService.do_AESEncryption(
                    str,
                    secretKey,
                    initializationVector);

            String cipherTextString = Base64.getEncoder().encodeToString(cipherText);

            dataOutputStream.writeUTF(cipherTextString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LoginViewController getLoginViewController() {
        return loginViewController;
    }
    public CreateNewAccountViewController getCreateNewAccountViewController() {return createNewAccountViewController;}
    public ProductViewController getProductViewController() {return productViewController;}
    public AccountUpdateController getAccountUpdateController() {return accountUpdateController;}
    public OrdersViewController_C getOrdersViewController_c() {return ordersViewController_c;}
    public OrdersExisting_C getOrdersExisting_c() {return ordersExisting_c;}
    public OrdersNew_C getOrdersNew_c() {return ordersNew_c;}

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setAccountUpdateController(AccountUpdateController accountUpdateController) {
        this.accountUpdateController = accountUpdateController;
    }


    private class Worker implements Runnable {

        @Override
        public void run() {
            while (true) {
                String replyString = null;
                try {
                    replyString = dataInputStream.readUTF();

                    byte[] decode = Base64.getDecoder().decode(replyString);

                    replyString
                            = KeyService.do_AESDecryption(
                            decode,
                            secretKey,
                            initializationVector);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Message message = gson.fromJson(replyString, Message.class);

                processMessage(message);

            }
        }
    }

    private void processMessage(Message message) {
        System.out.println(gson.toJson(message));
        switch (message.getId()) {

            case Message.LOAD_USERS_REPLY: {
                Users users = gson.fromJson(message.getContent(), Users.class);
                createNewAccountViewController.updateUserInfo(users);
                break;
            }

            case Message.LOAD_PRODUCT_REPLY: {
                Product product = gson.fromJson(message.getContent(), Product.class);
                productViewController.updateProductInfo(product);
                break;
            }

            case Message.LOGIN_FAIL: {
                System.out.println(message.getContent());
                JOptionPane.showMessageDialog(null, "Invalid Login Credentials.\n" +
                        "Please Try Again.");
                break;
            }

            case Message.LOGIN_SUCCESS: {
                Users users = gson.fromJson(message.getContent(), Users.class);

                System.out.println(users);

                this.user = users;

                if (users.getUserRole().equals("Manager")) {
                    System.out.println("A Manager has logged in.");

                    mainFrame.setContentPane(managerViewController.getMainPanel());
                    mainFrame.setTitle("Manager View");
                    managerViewController.getMainPanel().updateUI();
                } else {
                    System.out.println("A Customer has logged in.");
                    mainFrame.setContentPane(customerViewController.getMainPanel());
                    mainFrame.setTitle("Customer View");
                    customerViewController.getMainPanel().updateUI();
                }
                break;
            }

            case Message.LOGOUT: {
                System.out.println("The user has logged out.");
                JOptionPane.showMessageDialog(null, "You have been successfully logged out");
                mainFrame.setContentPane(loginViewController.getMainPanel());
                managerViewController.getMainPanel().updateUI();
                break;
            }

            case Message.CREATION_SUCCESS: {
                System.out.print("A new account has been created.");
                JOptionPane.showMessageDialog(null, "Account Created!");
                break;
            }

            case Message.CREATION_FAIL: {
                System.out.print("User tried to create an account with an existing username.");
                JOptionPane.showMessageDialog(null, "This username already exists, please try again.");
                break;
            }

            case Message.DELETED_PRODUCT_SUCCESS: {
                JOptionPane.showMessageDialog(null, "Product Deleted.");
                break;
            }

            case Message.DELETED_PRODUCT_FAIL: {
                JOptionPane.showMessageDialog(null, "Unable to delete Product.");
                break;
            }

            case Message.PRODUCT_SAVED: {
                JOptionPane.showMessageDialog(null, "Product Saved!");
                break;
            }

            case Message.PRODUCT_FAILED: {
                JOptionPane.showMessageDialog(null, "Unable to save Product.");
                break;
            }


            case Message.UPDATE_ACCOUNT_SUCCESS: {
                JOptionPane.showMessageDialog(null, "Account Updated!");
                break;
            }

            case Message.DELETED_ORDER_SUCCESS: {
                JOptionPane.showMessageDialog(null, "Order Deleted.");
                break;
            }

            case Message.DELETED_ORDER_FAIL: {
                JOptionPane.showMessageDialog(null, "Order Deleted.");
                break;
            }

            case Message.SAVED_ORDER_SUCCESS: {
                JOptionPane.showMessageDialog(null, "Order Saved.");
                break;
            }

            case Message.SAVED_ORDER_FAIL: {
                JOptionPane.showMessageDialog(null, "Order failed to save.");
                break;
            }

            case Message.UPDATE_ORDER_SUCCESS: {
                JOptionPane.showMessageDialog(null, "Order Saved.");
                break;
            }

            case Message.UPDATE_ORDER_FAIL: {
                JOptionPane.showMessageDialog(null, "Order failed to update.");
                break;
            }

            case Message.SEARCH_PRODUCT_REPLY: {
                ProductListModel list = gson.fromJson(message.getContent(), ProductListModel.class);
                ordersNew_c.updateProductInfo(list);
                break;
            }

            default:
                return;
        }

    }

    public static void main(String[] args) {
        Client client = new Client();
        JFrame frame = new JFrame("Client: Login Screen");
        client.setMainFrame(frame);
        frame.setContentPane(client.getLoginViewController().getMainPanel());
        frame.setMinimumSize(new Dimension(800, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
