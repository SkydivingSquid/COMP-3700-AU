import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Order {
    private int orderID;
    private int userID;
    private Date date;

    private List<OrderLine> lines;

    public Order() {
        lines = new ArrayList<>();
    }

    //These are not needed because its automatically set.
    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}

    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {this.orderID = orderID;}

    public int getUserID() {return userID;}
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void addLine(OrderLine line) {
        lines.add(line);
    }
    public void removeLine(OrderLine line) {
        lines.remove(line);
    }

    public List<OrderLine> getLines() {
        return lines;
    }
}
