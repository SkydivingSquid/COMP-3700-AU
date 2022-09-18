public class StoreManager {

    private static StoreManager instance = null;

    private SQLiteDataAdapter dao;

    private ProductView productView = null;

    private OrderView orderView = null;

    private CustomerView customerView = null;

    public MainScreen getMainScreen() {
        return mainScreen;
    }

    private MainScreen mainScreen = null;

    public ProductView getProductView() {
        return productView;
    }

    public OrderView getOrderView() {return orderView;}

    public CustomerView getCustomerView() {return customerView;}

    private ProductController productController = null;

    private OrderController orderController = null;

    private CustomerController customerController = null;

    public static StoreManager getInstance() {
        if (instance == null)
            instance = new StoreManager("SQLite");
        return instance;
    }

    public SQLiteDataAdapter getDataAccess() {
        return dao;
    }

    private StoreManager(String db) {
        // do some initialization here!!!
        if (db.equals("SQLite"))
            dao = new SQLiteDataAdapter();

        //Instantiate viewers
        dao.connect();
        productView = new ProductView();
        productController = new ProductController(productView, dao);

        orderView = new OrderView();
        orderController = new OrderController(orderView, dao);

        customerView = new CustomerView();
        customerController = new CustomerController(customerView, dao);

        mainScreen = new MainScreen();
    }


}
