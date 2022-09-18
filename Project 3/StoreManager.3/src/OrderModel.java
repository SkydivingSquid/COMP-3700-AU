public class OrderModel {
    public int orderID;
    public String orderDate;
    public String customer;
    public double totalCost;
    public double totalTax;

    @Override
    public String toString(){
        return "Order: " + orderID + ", Order Date: " + orderDate + ",Customer: " + customer
                + ",Total Cost: " + totalCost +", Total Tax: " + totalTax;
    }


}
