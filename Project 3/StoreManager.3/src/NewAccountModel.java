public class NewAccountModel {

    //public int userID;
    public String userName;
    public String password;
    public String displayName;

    @Override //this seems like a major security issue (at least for the pwrd to be there. Consider changing).
    public String toString(){
        return "Username: " + userName + ", Password: " + password + ", Display Name: " + displayName;
    }

}
