import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class RemoteDataAdapter implements DataAccess {
    Gson gson = new Gson();
    Socket s = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;

    @Override
    public void connect() {
        try {
            s = new Socket("localhost", 5056);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //////////////////////
    /////NOTE SECTION/////
    //////////////////////

    /*@Override
    public NoteModel loadNote(int noteID) {
        RequestModel req = new RequestModel();
        req.code = RequestModel.LOAD_NOTE_REQUEST;
        req.body = Integer.toString(noteID);

        String json = gson.toJson(req);
        try {

            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a note with that ID!");
                    return null;
                } else {
                    NoteModel model = gson.fromJson(res.body, NoteModel.class);
                    System.out.println("Receiving a NoteModel object");
                    System.out.println("NoteID = " + model.noteID);
                    System.out.println("Note title = " + model.title);
                    System.out.println("Note body = " + model.body);
                    return model; // found this note and return!!!
                }
            }        // this is a JSON string for a product information
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveNote(NoteModel note) {
        RequestModel req = new RequestModel();
        req.code = RequestModel.SAVE_NOTE_REQUEST;
        req.body = gson.toJson(note);

        String json = gson.toJson(req);
        try {

            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not save the Note!");
                } else {
                    NoteModel model = gson.fromJson(res.body, NoteModel.class);
                    System.out.println("Saved the following Note!");
                    System.out.println("NoteID = " + model.noteID);
                    System.out.println("Note title = " + model.title);
                    System.out.println("Note body = " + model.body);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void deleteNote(NoteModel note) {
        RequestModel req = new RequestModel();
        req.code = RequestModel.DELETE_NOTE_REQUEST;
        req.body = gson.toJson(note);

        String json = gson.toJson(req);
        try {

            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not save the Note!");
                } else {
                    NoteModel model = gson.fromJson(res.body, NoteModel.class);
                    System.out.println("Deleted the Note!");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        @Override
        public SearchModel searchNotes(String keyword) {
            RequestModel req = new RequestModel();
            req.code = RequestModel.SEARCH_NOTE_REQUEST;
            req.body = keyword;

            String json = gson.toJson(req);
            try {
                dos.writeUTF(json);

                String received = dis.readUTF();

                System.out.println("Server response:" + received);

                ResponseModel res = gson.fromJson(received, ResponseModel.class);

                if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                    System.out.println("The request is not recognized by the Server");
                    return null;
                } else {
                    if (res.code == ResponseModel.DATA_NOT_FOUND) {
                        System.out.println("The Server could not find a note that matches your keyword!");
                        return null;
                    } else {
                        SearchModel model = gson.fromJson(res.body, SearchModel.class);
                        System.out.println("Receiving a SearchModel object");
                        System.out.println(model.toString());
                        return model; // found this product and return!!!
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

    }*/


    //////////////////////
    ////PRODUCT SECTION///
    //////////////////////

    @Override
    public ProductModel loadProduct(int productID) {
        RequestModel req = new RequestModel();
        req.code = RequestModel.LOAD_PRODUCT_REQUEST;
        req.body = Integer.toString(productID);

        String json = gson.toJson(req);
        try {

            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a product with that ID!");
                    return null;
                } else {
                    ProductModel model = gson.fromJson(res.body, ProductModel.class);
                    System.out.println("Receiving a ProductModel object");
                    System.out.println("ProductID = " + model.productID);
                    System.out.println("Name = " + model.name);
                    System.out.println("Price = " + model.price);
                    System.out.println("Quantity = " + model.quantity);
                    return model; // found this product and return!!!
                }
            }        // this is a JSON string for a product information
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveProduct(ProductModel product) {
        RequestModel req = new RequestModel();
        req.code = RequestModel.SAVE_PRODUCT_REQUEST;
        req.body = gson.toJson(product);

        String json = gson.toJson(req);
        try {

            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not save the Product!");
                } else {
                    ProductModel model = gson.fromJson(res.body, ProductModel.class);
                    System.out.println("Saved the following Product!");
                    System.out.println("ProductID = " + model.productID);
                    System.out.println("Name = " + model.name);
                    System.out.println("Price = " + model.price);
                    System.out.println("Quantity = " + model.quantity);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void deleteProduct(ProductModel product) {
        RequestModel req = new RequestModel();
        req.code = RequestModel.DELETE_PRODUCT_REQUEST;
        req.body = gson.toJson(product);

        String json = gson.toJson(req);
        try {

            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not save the Product!");
                } else {
                    ProductModel model = gson.fromJson(res.body, ProductModel.class);
                    System.out.println("Deleted the Product!");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //////////////////////
    ///CUSTOMER SECTION///
    //////////////////////

    @Override
    public CustomerModel loadCustomer(int customerID) {
        RequestModel req = new RequestModel();
        req.code = RequestModel.LOAD_CUSTOMER_REQUEST;
        req.body = Integer.toString(customerID);

        String json = gson.toJson(req);
        try {

            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a cstomeru with that ID!");
                    return null;
                } else {
                    CustomerModel model = gson.fromJson(res.body, CustomerModel.class);
                    System.out.println("Receiving a CustomerModel object");
                    System.out.println("CustomerID = " + model.customerID);
                    System.out.println("First Name = " + model.firstName);
                    System.out.println("Last Name = " + model.lastName);
                    System.out.println("Address = " + model.address);
                    return model; // found this customer and return!!!
                }
            }        // this is a JSON string for a product information
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveCustomer(CustomerModel customer) {
        RequestModel req = new RequestModel();
        req.code = RequestModel.SAVE_CUSTOMER_REQUEST;
        req.body = gson.toJson(customer);

        String json = gson.toJson(req);
        try {

            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not save the Customer!");
                } else {
                    CustomerModel model = gson.fromJson(res.body, CustomerModel.class);
                    System.out.println("Saved the following Customer!");
                    System.out.println("CustomerID = " + model.customerID);
                    System.out.println("First Name = " + model.firstName);
                    System.out.println("Last Name = " + model.lastName);
                    System.out.println("Address = " + model.address);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void deleteCustomer(CustomerModel customer) {
        RequestModel req = new RequestModel();
        req.code = RequestModel.DELETE_CUSTOMER_REQUEST;
        req.body = gson.toJson(customer);

        String json = gson.toJson(req);
        try {

            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not save the Customer!");
                } else {
                    CustomerModel model = gson.fromJson(res.body, CustomerModel.class);
                    System.out.println("Deleted the Customer!");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    //////////////////////
    ////ORDERS SECTION////
    //////////////////////

            @Override
            public OrderModel loadOrder(int orderID) {
                RequestModel req = new RequestModel();
                req.code = RequestModel.LOAD_ORDER_REQUEST;
                req.body = Integer.toString(orderID);

                String json = gson.toJson(req);
                try {

                    dos.writeUTF(json);
                    String received = dis.readUTF();
                    System.out.println("Server response:" + received);
                    ResponseModel res = gson.fromJson(received, ResponseModel.class);

                    if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                        System.out.println("The request is not recognized by the Server");
                        return null;
                    } else {
                        if (res.code == ResponseModel.DATA_NOT_FOUND) {
                            System.out.println("The Server could not find an order with that ID!");
                            return null;
                        } else {
                            OrderModel model = gson.fromJson(res.body, OrderModel.class);
                            System.out.println("Receiving an OrderModel object");
                            System.out.println("OrderID = " + model.orderID);
                            System.out.println("Order date = " + model.orderDate);
                            System.out.println("Customer = " + model.customer);
                            System.out.println("Total Cost = " + model.totalCost);
                            System.out.println("Total Tax = " + model.totalTax);
                            return model; // found this order and return!!!
                        }
                    }        // this is a JSON string for a product information
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            public void saveOrder(OrderModel order) {
                RequestModel req = new RequestModel();
                req.code = RequestModel.SAVE_ORDER_REQUEST;
                req.body = gson.toJson(order);

                String json = gson.toJson(req);
                try {

                    dos.writeUTF(json);
                    String received = dis.readUTF();
                    System.out.println("Server response:" + received);
                    ResponseModel res = gson.fromJson(received, ResponseModel.class);

                    if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                        System.out.println("The request is not recognized by the Server");
                    } else {
                        if (res.code == ResponseModel.DATA_NOT_FOUND) {
                            System.out.println("The Server could not save the Order!");
                        } else {
                            OrderModel model = gson.fromJson(res.body, OrderModel.class);
                            System.out.println("Receiving a OrderModel object");
                            System.out.println("OrderID = " + model.orderID);
                            System.out.println("Order date = " + model.orderDate);
                            System.out.println("Customer = " + model.customer);
                            System.out.println("Total Cost = " + model.totalCost);
                            System.out.println("Total Tax = " + model.totalTax);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


            @Override
            public void deleteOrder(OrderModel order) {
                RequestModel req = new RequestModel();
                req.code = RequestModel.DELETE_ORDER_REQUEST;
                req.body = gson.toJson(order);

                String json = gson.toJson(req);
                try {

                    dos.writeUTF(json);
                    String received = dis.readUTF();
                    System.out.println("Server response:" + received);
                    ResponseModel res = gson.fromJson(received, ResponseModel.class);

                    if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                        System.out.println("The request is not recognized by the Server");
                    } else {
                        if (res.code == ResponseModel.DATA_NOT_FOUND) {
                            System.out.println("The Server could not save the Order!");
                        } else {
                            OrderModel model = gson.fromJson(res.body, OrderModel.class);
                            System.out.println("Deleted the Order!");
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }



} //FINAL BRACE THIS CLASS