public class Users {

    //Not sure if we need this method or if it was for testing.
    @Override
    public String toString() {
        return "Users{" +
                "UserID=" + UserID +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", FullName='" + FullName + '\'' +
                ", Address='" + Address + '\'' +
                ", Payment='" + Payment + '\'' +
                ", UserRole='" + UserRole + '\'' +
                '}';
    }

    private int UserID; // this is set by default auto-increment
    private String UserName;
    private String Password;
    private String FullName;
    private String Address;

    private String Payment;
    private String UserRole;

    //Getters and Setters

    public int getUserID() {return UserID;}
    //This is automatically get by the DB.
    public void setUserID(int userID) {this.UserID = userID;}

    public String getUserName() { return UserName;}
    public void setUserName(String userName) {this.UserName = userName;}

    public String getPassword() {return Password;}
    public void setPassword(String password) {this.Password = password;}

    public String getFullName() {return FullName;}
    public void setFullName(String fullName) {this.FullName = fullName;}

    public String getAddress() {return Address;}
    public void setAddress(String address) {this.Address = address;}

    public String getPayment() {return Payment;}
    public void setPayment(String payment) {this.Payment = payment;}

    public String getUserRole() {return UserRole;}
    public void setUserRole(String userRole) {this.UserRole = userRole;}

}

