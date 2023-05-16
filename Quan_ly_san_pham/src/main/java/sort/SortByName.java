package sort;

import model.Product;

import java.util.Comparator;

public class SortByName implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getNameProduct().toLowerCase().compareTo(o2.getNameProduct().toLowerCase());
    }
}
