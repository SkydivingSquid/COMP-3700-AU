import com.google.gson.Gson;

import javax.annotation.processing.Processor;
import javax.xml.crypto.Data;
import java.sql.*;

public class DatabaseManager {

    private static DatabaseManager databaseManager;
    public static DatabaseManager getInstance() {
        if (databaseManager == null) databaseManager = new DatabaseManager();
        return databaseManager;
    }

    private Connection connection;

    private DatabaseManager() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:data/store.db");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    ////////////////////
    ////SERVER COMMS////
    ////////////////////

    public Message process(String requestString) {

        Gson gson = new Gson();
        Message message = gson.fromJson(requestString, Message.class);

        //////////////
        ///PRODUCTS///
        //////////////

        switch (message.getId()) {
            case Message.LOAD_PRODUCT: {
                Integer productID=null;
                try {
                    productID = Integer.parseInt(message.getContent());
                } catch (NumberFormatException ex) {
                    Message replyMessage = new Message(Message.PRODUCT_FAILED, "The product ID is invalid");
                    return replyMessage;
                }
                Product product = loadProduct(productID);
                Message replyMessage = new Message(Message.LOAD_PRODUCT_REPLY, gson.toJson(product));
                return replyMessage;
            }

            case Message.SAVE_PRODUCT: {
                Product product = gson.fromJson(message.getContent(), Product.class);
                boolean result = saveProduct(product);
                if (result) return new Message(Message.PRODUCT_SAVED, "Product saved");
                else return new Message(Message.PRODUCT_FAILED, "Cannot save the product");
            }

            case Message.DELETE_PRODUCT: {
                Product product = gson.fromJson(message.getContent(), Product.class);
                boolean result = deleteProduct(product);
                if (result) return new Message(Message.DELETED_PRODUCT_SUCCESS, "Product deleted.");
                else return new Message(Message.DELETED_PRODUCT_FAIL, "Unable to delete product..uh oh.");
            }

            //////////////
            ///PRODUCTS///
            //////////////

                //update to have
            case Message.DELETE_ORDER: {
                Order order = gson.fromJson(message.getContent(), Order.class);
                boolean result = deleteOrder(order);
                if (result) return new Message(Message.DELETED_ORDER_SUCCESS, "Order deleted.");
                else return new Message(Message.DELETED_ORDER_FAIL, "Unable to delete order.");
            }

            ///////////
            ///USERS///
            ///////////

            case Message.LOAD_USERS: {
                Users users = loadUsers(message.getContent());
                Message replyMessage = new Message(Message.LOAD_USERS_REPLY, gson.toJson(users));
                return replyMessage;
            }

            case Message.SAVE_USERS: {
                Users users = gson.fromJson(message.getContent(), Users.class);
                boolean result = saveUsers(users);
                if (result) return new Message(Message.CREATION_SUCCESS, "Account Created!");
                else return new Message(Message.CREATION_FAIL, "Username is not available.");
            }

            case Message.LOGIN_REQUEST: {
                LoginInfo loginInfo = gson.fromJson(message.getContent(), LoginInfo.class);

                Users user = loadUsersFromLoginInfo(loginInfo);

                //For testing users that exist.
                System.out.println(user);
                //System.out.println(loginInfo);

                if (user==null) {
                    return new Message(Message.LOGIN_FAIL, "The entered account does not exist.");
                }
                else if (!user.getPassword().equals(loginInfo.getPassword())){
                    return new Message(Message.LOGIN_FAIL,  "The entered password is incorrect.");
                }
                else {
                    return new Message(Message.LOGIN_SUCCESS, gson.toJson(user));
                }
            }

            case Message.UPDATE_ACCOUNT: {
                Users user = gson.fromJson(message.getContent(), Users.class);
                boolean result = updateAccount(user);
                if (result) return new Message(Message.UPDATE_ACCOUNT_SUCCESS, "Account saved");
                else return new Message(Message.FAIL, "Cannot save the Account");
            }

            ////////////
            ///ORDERS///
            ////////////

            case Message.SAVE_ORDER: {
                Order orders = gson.fromJson(message.getContent(), Order.class);
                boolean result = saveOrder(orders);
                if (result) {return new Message(Message.SUCCESS, "Order saved");}
                else return new Message(Message.FAIL, "Cannot save the order");
            }

            case Message.SAVE_ORDERLINE: {
                OrderLine orderline = gson.fromJson(message.getContent(), OrderLine.class);
                boolean result = saveOrderLine(orderline);
                if (result) {return new Message(Message.UPDATE_ORDER_SUCCESS, "Orderline saved");}
                else return new Message(Message.UPDATE_ORDER_FAIL, "Cannot save the orderline");
            }

            ////////////
            ///SEARCH///
            ////////////

            case Message.SEARCH_PRODUCT: {
                ProductListModel product = searchProduct(message.getContent());
                Message replyMessage = new Message(Message.SEARCH_PRODUCT_REPLY, gson.toJson(product));
                System.out.println(replyMessage);
                return replyMessage;
            }

            default:
                return new Message(Message.FAIL, "Cannot process the message");
        }
    }

    /////////////////////
    ////LOGIN METHODS////
    /////////////////////

    private Users loadUsersFromLoginInfo(LoginInfo loginInfo) {
        return loadUsers(loginInfo.getUsername());
    }

    //////////////////////////////
    ////CREATE ACCOUNT METHODS////
    //////////////////////////////

    //Not sure about this one...
    public Users loadUsers(String name) {
        try {
            String query = "SELECT * FROM Users WHERE UserName = '" + name +"'";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                Users users = new Users();
                //UserID should be autoset.
                users.setUserID(resultSet.getInt(1));
                users.setUserName(resultSet.getString(2));
                users.setPassword(resultSet.getString(3));
                users.setFullName(resultSet.getString(4));
                users.setAddress(resultSet.getString(5));
                users.setPayment(resultSet.getString(6));
                users.setUserRole(resultSet.getString(7));
                resultSet.close();
                statement.close();

                return users;
            }

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveUsers(Users users) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE UserName = ?");
            statement.setString(1, users.getUserName());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) { // this username exists, update fields
                System.out.println("Error: User Attempted To Create An Account With A Username That Already Exists.");
                return false;
            }
            else { // this user does not exist, use update fields
                statement = connection.prepareStatement("INSERT INTO Users (UserName, Password, FullName) VALUES (?,?,?)");
                statement.setString(1, users.getUserName());
                statement.setString(2, users.getPassword());
                statement.setString(3, users.getFullName());
            }
            statement.execute();
            resultSet.close();
            statement.close();
            return true;        // save successfully

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false; // cannot save!
        }
    }


    //////////////////////////////
    ////UPDATE ACCOUNT METHODS////
    //////////////////////////////

    public boolean updateAccount(Users users) {

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE UserName = ?");
            statement.setString(1, users.getUserName());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                statement = connection.prepareStatement("UPDATE Users SET FullName = ?, Address = ?, " +
                        "Payment = ?, Password = ? WHERE UserName = ?");

                statement.setString(1, users.getFullName());
                statement.setString(2, users.getAddress());
                statement.setString(3, users.getPayment());
                statement.setString(4, users.getPassword());
                statement.setString(5, users.getUserName());
            }

                statement.execute();
                resultSet.close();
                statement.close();
                return true;        // save successfully

    } catch (SQLException e) {
        System.out.println("Database access error!");
        e.printStackTrace();
        return false; // cannot save!
    }
}

    ///////////////////////
    ////PRODUCT METHODS////
    ///////////////////////

    public Product loadProduct(int id) {
        try {
            String query = "SELECT * FROM Products WHERE ProductID = ' " + id + "'"; //added some marks

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setQuantity(resultSet.getDouble(4));
                resultSet.close();
                statement.close();

                return product;
            }

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveProduct(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Products WHERE ProductID = ?");
            statement.setInt(1, product.getProductID());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) { // this product exists, update its fields
                statement = connection.prepareStatement("UPDATE Products SET Name = ?, Price = ?, Quantity = ? WHERE ProductID = ?");
                statement.setString(1, product.getName());
                statement.setDouble(2, product.getPrice());
                statement.setDouble(3, product.getQuantity());
                statement.setInt(4, product.getProductID());
            }
            else { // this product does not exist, use insert into
                statement = connection.prepareStatement("INSERT INTO Products VALUES (?, ?, ?, ?)");
                statement.setString(2, product.getName());
                statement.setDouble(3, product.getPrice());
                statement.setDouble(4, product.getQuantity());
                statement.setInt(1, product.getProductID());
            }
            statement.execute();
            resultSet.close();
            statement.close();
            return true;        // save successfully

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false; // cannot save!
        }
    }

    public boolean deleteProduct(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Products WHERE ProductID = ?");
            statement.setInt(1, product.getProductID());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) { // this product exists, update its fields
                statement = connection.prepareStatement("DELETE FROM Products WHERE ProductID = ?");
                statement.setInt(1, product.getProductID());
            }

            statement.execute();
            resultSet.close();
            statement.close();
            return true;        // save successfully

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false; // cannot save!
        }
    }

    ////////////////////////////////
    ////ORDER/ORDERLINE METHODS////
    ///////////////////////////////

    public boolean saveOrderLine(OrderLine orderline) {

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM OrderLine WHERE OrderID = ?");
            statement.setInt(1, orderline.getOrderID());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) { // this orderline exists, update its fields
                statement = connection.prepareStatement("UPDATE OrderLine SET ProductID = ?, Quantity = ? WHERE OrderID = ?");
                statement.setInt(1, orderline.getProductID());
                statement.setDouble(2, orderline.getQuantity());
                statement.setInt(3, orderline.getOrderID());

            }
            else { // this orderline does not exist, use insert into
                statement = connection.prepareStatement("INSERT INTO OrderLine VALUES (?, ?, ?)");
                statement.setInt(2, orderline.getProductID());
                statement.setDouble(3, orderline.getQuantity());
                statement.setInt(1, orderline.getOrderID());

            }
            statement.execute();
            resultSet.close();
            statement.close();
            return true;        // save successfully

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false; // cannot save!
        }
    }


    public boolean saveOrder(Order order) {

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Orders(OrderID, UserID) VALUES (?,?)");
                statement.setInt(1, order.getOrderID());
                statement.setInt(2, order.getUserID());
            statement.execute();
            statement.close();

            return true; // save successfully
        }

        catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false; // cannot save!
        }

    }

    public boolean deleteOrder(Order order) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Orders WHERE OrderID = ?");
            statement.setInt(1, order.getOrderID());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) { // this product exists, update its fields
                statement = connection.prepareStatement("DELETE FROM Orders WHERE OrderID = ?");
                statement.setInt(1, order.getOrderID());
            }

            statement.execute();
            resultSet.close();
            statement.close();

            try {
                PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM OrderLine WHERE OrderID = ?");
                statement2.setInt(1, order.getOrderID());

                ResultSet resultSet2 = statement2.executeQuery();

                if (resultSet2.next()) { // this product exists, update its fields
                    statement2 = connection.prepareStatement("DELETE FROM OrderLine WHERE OrderID = ?");
                    statement2.setInt(1, order.getOrderID());
                }

                statement2.execute();
                resultSet2.close();
                statement2.close();
                return true;        // save successfully

            }

            catch (SQLException e) {
                System.out.println("Database access error!");
                e.printStackTrace();
                return false; // cannot save!
            }

        }
            catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false; // cannot save!
        }
    }


    //////////////////////
    ////SEARCH METHODS////
    //////////////////////

    public ProductListModel searchProduct(String keyword) {
        Product product = null;
        ProductListModel listModel = new ProductListModel();
        try {
            keyword = keyword.substring(1, keyword.length()-1);
            String query = "SELECT * FROM Products WHERE Name LIKE " + "\"%" +  keyword + "%\"";
            System.out.println(query);
            System.out.println(keyword);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                product = new Product();
                product.setProductID(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setQuantity(resultSet.getDouble(4));
                //resultSet.close();
                //statement.close();

                listModel.list.add(product);
            }

        }
        catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
        }
        return listModel;
    }

} // Final Brace
