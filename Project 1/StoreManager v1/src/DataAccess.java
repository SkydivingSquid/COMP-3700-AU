public interface DataAccess {
    void connect();

    //Instantiate Save Methods
    void saveProduct(ProductModel product);
    void saveOrder(OrderModel order);
    void saveCustomer(CustomerModel customer);

    //Instantiate Load Methods
    ProductModel loadProduct(int productID);
    OrderModel loadOrder(int orderID);
    CustomerModel loadCustomer(int customerID);

    //Instantiate Delete Methods
    void deleteProduct(ProductModel product);
    void deleteOrder(OrderModel order);
    void deleteCustomer(CustomerModel customer);

}
