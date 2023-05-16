package sort;



import model.Product;

import java.util.Comparator;

public class SortByNameDesc implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getNameProduct().toLowerCase().compareTo(o1.getNameProduct().toLowerCase());
    }
}
