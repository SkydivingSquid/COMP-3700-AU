public class Message {

    public static final int FAIL = -1;
    public static final int SUCCESS = 0;

    public static final int LOAD_PRODUCT = 10;
    public static final int LOAD_PRODUCT_REPLY = 11;
    public static final int SAVE_PRODUCT = 12;
    public static final int DELETE_PRODUCT = 13;
    public static final int DELETED_PRODUCT_SUCCESS = 14;
    public static final int DELETED_PRODUCT_FAIL = 15;
    public static final int PRODUCT_SAVED = 16;
    public static final int PRODUCT_FAILED = 17;
    public static final int SEARCH_PRODUCT = 18;
    public static final int SEARCH_PRODUCT_REPLY = 19;

    public static final int LOAD_USERS = 20;
    public static final int LOAD_USERS_REPLY = 21;
    public static final int SAVE_USERS = 22;
    public static final int CREATION_SUCCESS = 23;
    public static final int CREATION_FAIL = 24;
    public static final int UPDATE_ACCOUNT = 25;
    public static final int UPDATE_ACCOUNT_SUCCESS = 26;

    public static final int SAVE_ORDER = 30;
    public static final int SAVE_ORDERLINE = 31;
    public static final int SAVED_ORDER_SUCCESS = 32;
    public static final int SEARCH_ORDER = 33;
    public static final int SEARCH_REPLY = 34;
    public static final int SAVED_ORDER_FAIL = 35;
    public static final int UPDATE_ORDER_SUCCESS = 36;
    public static final int UPDATE_ORDER_FAIL = 37;

    public static final int LOGIN_REQUEST = 40;
    public static final int LOGIN_FAIL = 41;
    public static final int LOGIN_SUCCESS = 42;
    public static final int LOGOUT = 43;

    public static final int DELETE_ORDER = 53;
    public static final int DELETED_ORDER_SUCCESS = 54;
    public static final int DELETED_ORDER_FAIL = 55;

    private int id; // the type of the message
    private String content; // the content of the message

    public Message(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
