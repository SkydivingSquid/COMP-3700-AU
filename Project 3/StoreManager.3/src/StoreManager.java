public class StoreManager {

    private static StoreManager instance = null;

    private RemoteDataAdapter dao;

    private ProductView productView = null;
    private CustomerView customerView = null;
    private OrderView orderView = null;
    private MainMenuView mainMenuView = null;
    private LoginView loginView = null;
    private NewAccountView newAccountView = null;

    public ProductView getProductView() {return productView;}
    public CustomerView getCustomerView() {return customerView;}
    public OrderView getOrderView() {return orderView;}
    public MainMenuView getMainMenuView() {return mainMenuView;}
    public LoginView getLoginView() {return loginView;}
    public NewAccountView getNewAccountView() {return newAccountView;}

    private ProductController productController = null;
    private CustomerController customerController = null;
    private OrderController orderController = null;
    private MainMenuController mainMenuController = null;
    private LoginController loginController = null;
    private NewAccountController newAccountController = null;

    public static StoreManager getInstance() {
        if (instance == null)
            instance = new StoreManager("SQLite");
        return instance;
    }

    public RemoteDataAdapter getDataAccess() {
        return dao;
    }

    private StoreManager(String db) {
        // do some initialization here!!!
        dao = new RemoteDataAdapter();
        dao.connect();

        loginView = new LoginView();
        loginController = new LoginController(loginView);

        mainMenuView = new MainMenuView();
        mainMenuController = new MainMenuController(mainMenuView);

        productView = new ProductView();
        productController = new ProductController(productView, dao);

        customerView = new CustomerView();
        customerController = new CustomerController(customerView, dao);

        orderView = new OrderView();
        orderController = new OrderController(orderView, dao);

        newAccountView = new NewAccountView();
        newAccountController = new NewAccountController(newAccountView, dao);

    }
}