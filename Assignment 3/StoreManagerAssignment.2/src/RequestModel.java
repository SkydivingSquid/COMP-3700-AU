public class RequestModel {

    static final public int EXIT_REQUEST = 0;
    static final public int LOAD_NOTE_REQUEST = 1;
    static final public int SAVE_NOTE_REQUEST = 2;
    static final public int SEARCH_NOTE_REQUEST = 3;
    static final public int DELETE_NOTE_REQUEST = 4;

    public int code;
    public String body;
}