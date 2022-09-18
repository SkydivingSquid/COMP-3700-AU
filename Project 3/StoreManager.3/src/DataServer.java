// Java implementation of Server side
// It contains two classes : Server and ClientHandler
// Save file as Server.java

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

import com.google.gson.Gson;


// Server class
public class DataServer
{
    public static void main(String[] args) throws IOException
    {
        // server is listening on port 5056
        ServerSocket ss = new ServerSocket(5056);

        // running infinite loop for getting
        // client request

        System.out.println("Starting server program!!!");

        int nClients = 0;

        while (true)
        {
            Socket s = null;

            try
            {
                // socket object to receive incoming client requests
                s = ss.accept();

                nClients++;
                System.out.println("A new client is connected : " + s + " client number: " + nClients);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread t = new ClientHandler(s, dis, dos);

                // Invoking the start() method
                t.start();

            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
}


// ClientHandler class
class ClientHandler extends Thread
{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    Gson gson = new Gson();
    DataAccess dao = new SQLiteDataAdapter();

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        dao.connect();
    }

    @Override
    public void run()
    {
        String received;
        boolean exit = false;
        while (!exit) {
            try {
                // receive the answer from client
                received = dis.readUTF();

                System.out.println("Message from client " + received);

                RequestModel req = gson.fromJson(received, RequestModel.class);
                ResponseModel res = new ResponseModel();

                switch(req.code){

                    case RequestModel.EXIT_REQUEST:
                        System.out.println("Client " + this.s + " sends exit...");
                        System.out.println("Closing this connection.");
                        this.s.close();
                        System.out.println("Connection closed");
                        exit = true;
                        break;

                        ////////////////////////
                        /////NOTE SECTION///////
                        ////////////////////////

   /*                 case RequestModel.LOAD_NOTE_REQUEST:
                        int id = Integer.parseInt(req.body);
                        System.out.println("The Client asks for a note with ID = " + id);
                        NoteModel model = dao.loadNote(id);
                        if (model != null) {
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(model);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;

                    case RequestModel.SAVE_NOTE_REQUEST:
                        NoteModel note = gson.fromJson(req.body, NoteModel.class);
                        System.out.println("The Client asks to store the Note: \n" + note.toString());
                        dao.saveNote(note);
                        NoteModel confirm = dao.loadNote(note.noteID);
                        if(confirm != null){
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(note);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;


                    case RequestModel.DELETE_NOTE_REQUEST:
                        NoteModel dnote = gson.fromJson(req.body, NoteModel.class);
                        System.out.println("The Client asks to delete the Note: \n" + dnote.toString());
                        dao.deleteNote(dnote);
                        NoteModel dconfirm = dao.loadNote(dnote.noteID);
                        if(dconfirm == null){
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(dnote);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;


                    case RequestModel.SEARCH_NOTE_REQUEST:
                        String keyword = req.body;
                        SearchModel found = dao.searchNotes(keyword);
                        if(found != null){
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(found);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;*/

                    ////////////////////////
                    /////PRODUCT SECTION////
                    ////////////////////////

                    case RequestModel.LOAD_PRODUCT_REQUEST:
                        int p_id = Integer.parseInt(req.body);
                        System.out.println("The Client asks for a product with ID = " + p_id);
                        ProductModel pmodel = dao.loadProduct(p_id);
                        if (pmodel != null) {
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(pmodel);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;

                    case RequestModel.SAVE_PRODUCT_REQUEST:
                        ProductModel product = gson.fromJson(req.body, ProductModel.class);
                        System.out.println("The Client asks to store the Product: \n" + product.toString());
                        dao.saveProduct(product);
                        ProductModel p_confirm = dao.loadProduct(product.productID);
                        if(p_confirm != null){
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(product);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;


                    case RequestModel.DELETE_PRODUCT_REQUEST:
                        ProductModel dproduct = gson.fromJson(req.body, ProductModel.class);
                        System.out.println("The Client asks to delete the Product: \n" + dproduct.toString());
                        dao.deleteProduct(dproduct);
                        ProductModel dp_confirm = dao.loadProduct(dproduct.productID);
                        if(dp_confirm == null){
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(dproduct);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;


                    ////////////////////////
                    ////CUSTOMER SECTION////
                    ////////////////////////

                    case RequestModel.LOAD_CUSTOMER_REQUEST:
                        int c_id = Integer.parseInt(req.body);
                        System.out.println("The Client asks for a customer with ID = " + c_id);
                        CustomerModel cmodel = dao.loadCustomer(c_id);
                        if (cmodel != null) {
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(cmodel);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;

                    case RequestModel.SAVE_CUSTOMER_REQUEST:
                        CustomerModel customer = gson.fromJson(req.body, CustomerModel.class);
                        System.out.println("The Client asks to store the Customer: \n" + customer.toString());
                        dao.saveCustomer(customer);
                        CustomerModel c_confirm = dao.loadCustomer(customer.customerID);
                        if(c_confirm != null){
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(customer);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;


                    case RequestModel.DELETE_CUSTOMER_REQUEST:
                        CustomerModel dcustomer = gson.fromJson(req.body, CustomerModel.class);
                        System.out.println("The Client asks to delete the Customer: \n" + dcustomer.toString());
                        dao.deleteCustomer(dcustomer);
                        CustomerModel dc_confirm = dao.loadCustomer(dcustomer.customerID);
                        if(dc_confirm == null){
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(dcustomer);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;


                    ////////////////////////
                    /////ORDER SECTION//////
                    ////////////////////////

                    case RequestModel.LOAD_ORDER_REQUEST:
                        int o_id = Integer.parseInt(req.body);
                        System.out.println("The Client asks for an order with ID = " + o_id);
                        OrderModel omodel = dao.loadOrder(o_id);
                        if (omodel != null) {
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(omodel);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;

                    case RequestModel.SAVE_ORDER_REQUEST:
                        OrderModel order = gson.fromJson(req.body, OrderModel.class);
                        System.out.println("The Client asks to store the Order: \n" + order.toString());
                        dao.saveOrder(order);
                        OrderModel o_confirm = dao.loadOrder(order.orderID);
                        if(o_confirm != null){
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(order);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;


                    case RequestModel.DELETE_ORDER_REQUEST:
                        OrderModel dorder = gson.fromJson(req.body, OrderModel.class);
                        System.out.println("The Client asks to delete the Order: \n" + dorder.toString());
                        dao.deleteOrder(dorder);
                        OrderModel do_confirm = dao.loadOrder(dorder.orderID);
                        if(do_confirm == null){
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(dorder);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;


                    ///////////////////////////
                    ////NEW ACCOUNT SECTION////
                    ///////////////////////////

                    case RequestModel.LOAD_ACCOUNT_REQUEST:
                        String a_name = req.body; // not sure if this will work
                        System.out.println("The Client asks for an account with name = " + a_name);
                        NewAccountModel amodel = dao.loadNewAccount(a_name);
                        if (amodel != null) {
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(amodel);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;


                    case RequestModel.SAVE_ACCOUNT_REQUEST:
                        NewAccountModel account = gson.fromJson(req.body, NewAccountModel.class);
                        System.out.println("The Client asks to store the NewAccount: \n" + account.toString());
                        dao.saveNewAccount(account);
                        NewAccountModel a_confirm = dao.loadNewAccount(account.userName);
                        if(a_confirm != null){
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(account);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;









                    ////////////////////////
                    /////DEFAULT SECTION////
                    ////////////////////////


                    default:
                        res.code = ResponseModel.UNKNOWN_REQUEST;
                        res.body = "";
                }

                String json = gson.toJson(res);
                System.out.println("JSON object of ResponseModel: " + json);

                dos.writeUTF(json);
                dos.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}