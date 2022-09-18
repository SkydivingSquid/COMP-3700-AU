public class RequestModel {

    static final public int EXIT_REQUEST = 0;

    static final public int LOAD_NOTE_REQUEST = 10;
    static final public int SAVE_NOTE_REQUEST = 11;
    static final public int DELETE_NOTE_REQUEST = 12;
    static final public int SEARCH_NOTE_REQUEST = 13;

    static final public int LOAD_PRODUCT_REQUEST = 20;
    static final public int SAVE_PRODUCT_REQUEST = 21;
    static final public int DELETE_PRODUCT_REQUEST = 22;

    static final public int LOAD_CUSTOMER_REQUEST = 30;
    static final public int SAVE_CUSTOMER_REQUEST = 31;
    static final public int DELETE_CUSTOMER_REQUEST = 32;

    static final public int LOAD_ORDER_REQUEST = 40;
    static final public int SAVE_ORDER_REQUEST = 41;
    static final public int DELETE_ORDER_REQUEST = 42;


    public int code;
    public String body;
}