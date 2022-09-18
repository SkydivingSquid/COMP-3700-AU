import java.util.ArrayList;

public class ProductListModel {
    public ArrayList<Product> list = new ArrayList<>();

    public String toString() {
        String string = "";
        for(Product product : list) {
            string = string.format("%40s%40S%40s%40s", string + "Product ID: " + product.getProductID(),
                    " Name: " + product.getName(), " Price: " + product.getPrice(),
                    " Quantity: " + product.getQuantity() + "\n\n");
        }
        return string;
    }
    public void add(Product product) {list.add(product);}
}
