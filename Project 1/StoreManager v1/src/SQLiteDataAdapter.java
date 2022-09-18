import java.sql.*;

public class SQLiteDataAdapter implements DataAccess {
    Connection conn = null;

    @Override
    //Establish connect with SQL
    public void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:store.db";
            // create a connection to the database
            Class.forName("org.sqlite.JDBC");

            conn = DriverManager.getConnection(url);

            if (conn == null)
                System.out.println("Cannot make the connection!!!");
            else
                System.out.println("The connection object is " + conn);

            System.out.println("Connection to SQLite has been established.");

            /* Test data!!!
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product");

            while (rs.next())
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            */

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    //Save info from ProductView into SQL as new or updated.
    @Override
    public void saveProduct(ProductModel product) {
        try {
            Statement stmt = conn.createStatement();

            if (loadProduct(product.productID) == null) {           // this is a new product!
                stmt.execute("INSERT INTO Product(productID, name, price, quantity) VALUES ("
                        + product.productID + ","
                        + '\'' + product.name + '\'' + ","
                        + product.price + ","
                        + product.quantity + ")"
                );
            }
            else {
                stmt.executeUpdate("UPDATE Product SET "
                        + "productID = " + product.productID + ","
                        + "name = " + '\'' + product.name + '\'' + ","
                        + "price = " + product.price + ","
                        + "quantity = " + product.quantity +
                        " WHERE productID = " + product.productID
                );

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    //Save info from OrderView into SQL as new or updated.
    @Override
    public void saveOrder(OrderModel order) {
        try {
            Statement stmt = conn.createStatement();

            if (loadOrder(order.orderID) == null) {           // this is a new order!
                stmt.execute("INSERT INTO Orders(orderID, orderDate, customer, totalCost, totalTax ) VALUES ("
                        + order.orderID + ","
                        + '\'' + order.orderDate + '\'' + ","
                        + '\'' + order.customer + '\'' + ","
                        + order.totalCost + ","
                        + order.totalTax + ")"
                );
            }
            else {
                stmt.executeUpdate("UPDATE Orders SET "
                        + "orderID = " + order.orderID + ","
                        + "orderDate =" + '\'' + order.orderDate + '\'' + ","
                        + "customer = " + '\'' + order.customer + '\'' + ","
                        + "totalCost = " + order.totalTax + ","
                        + "totalTax = " + order.totalCost +
                        " WHERE orderID = " + order.orderID
                );

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    //Save info from CustomerView into SQL as new or updated.
    @Override
    public void saveCustomer(CustomerModel customer) {
        try {
            Statement stmt = conn.createStatement();

            if (loadCustomer(customer.customerID) == null) {           // this is a new customer!
                stmt.execute("INSERT INTO Customers(CustomerID, FirstName, LastName, Address ) VALUES ("
                        + customer.customerID + ","
                        + '\'' + customer.firstName + '\'' + ","
                        + '\'' + customer.lastName + '\'' + ","
                        + '\'' + customer.address + '\'' + ")"
                );
            }
            else {
                stmt.executeUpdate("UPDATE Customers SET "
                        + "CustomerID = " + customer.customerID + ","
                        + "FirstName =" + '\'' + customer.firstName + '\'' + ","
                        + "LastName = " + '\'' + customer.lastName + '\'' + ","
                        + "Address = " + '\'' + customer.address + '\'' +
                        " WHERE CustomerID = " + customer.customerID
                );

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    //Load information from SQL into ProductView
    @Override
    public ProductModel loadProduct(int productID) {
        ProductModel product = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product WHERE ProductID = " + productID);
            if (rs.next()) {
                product = new ProductModel();
                product.productID = rs.getInt(1);
                product.name = rs.getString(2);
                product.price = rs.getDouble(3);
                product.quantity = rs.getDouble(4);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return product;
    }


    //Load information from SQL into OrderView
    @Override
    public OrderModel loadOrder(int orderID) {
        OrderModel order = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Orders WHERE OrderID = " + orderID);
            if (rs.next()) {
                order = new OrderModel();
                order.orderID = rs.getInt(1);
                //order.orderDate = rs.getDate(2);
                order.orderDate = rs.getString(2);
                order.customer = rs.getString(3);
                order.totalCost = rs.getDouble(4);
                order.totalTax = rs.getDouble(5);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return order;
    }


    //Load information from SQL into ClientView
    @Override
    public CustomerModel loadCustomer(int customerID) {
        CustomerModel customer = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customers WHERE customerID = " + customerID);
            if (rs.next()) {
                customer = new CustomerModel();
                customer.customerID = rs.getInt(1);
                customer.firstName = rs.getString(2);
                customer.lastName = rs.getString(3);
                customer.address = rs.getString(4);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customer;
    }

    //Extra Credit: Delete information from SQL from ProductView
    @Override
    public void deleteProduct(ProductModel product) {
        try {
            Statement stmt = conn.createStatement();

            if (loadProduct(product.productID) != null) {           // this is an existing product!
                stmt.executeUpdate("DELETE FROM Product WHERE ProductID = " + product.productID);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    //Extra Credit: Delete information from SQL from OrderView
    @Override
    public void deleteOrder(OrderModel order) {
        try {
            Statement stmt = conn.createStatement();

            if (loadOrder(order.orderID) != null) {           // this is an existing order!
                stmt.executeUpdate("DELETE FROM Orders WHERE OrderID = " + order.orderID);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    //Extra Credit: Delete information from SQL from CustomerView
    @Override
    public void deleteCustomer(CustomerModel customer) {
        try {
            Statement stmt = conn.createStatement();

            if (loadCustomer(customer.customerID) != null) {           // this is an existing customer!
                stmt.executeUpdate("DELETE FROM Customers WHERE customerId = " + customer.customerID);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



} //final brace
