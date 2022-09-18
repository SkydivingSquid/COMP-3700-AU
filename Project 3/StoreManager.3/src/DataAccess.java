public interface DataAccess {
    void connect();
/*
    void saveNote(NoteModel note);
    void deleteNote(NoteModel note);
    NoteModel loadNote(int noteID);
    SearchModel searchNotes(String keyword);*/

    void saveProduct(ProductModel product);
    void deleteProduct(ProductModel product);
    ProductModel loadProduct(int productID);

    void saveCustomer(CustomerModel customer);
    void deleteCustomer(CustomerModel customer);
    CustomerModel loadCustomer(int customerID);

    void saveOrder(OrderModel order);
    void deleteOrder(OrderModel order);
    OrderModel loadOrder(int orderID);

    void saveNewAccount(NewAccountModel account);
    NewAccountModel loadNewAccount(String userName);

}