public class CustomerModel {

    public int customerID;
    public String firstName;
    public String lastName;
    public String address;

    @Override
    public String toString(){
        return "Customer ID: " + customerID + "First Name: " + firstName + ", Last Name: " + lastName + ", Address: " + address;
    }

}



