package service;

import model.Product;

public interface IProductService extends InterfaceService {
    void add(Product newProduct);

    void update(Product newProduct);

    Product findProductById(long productId);

    public void updateQuantity(long id, int quantity);

}
