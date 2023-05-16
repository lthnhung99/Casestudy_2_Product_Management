package service;

import model.Product;
import utils.FileUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService{

    private static final String PATH = "D:\\Casestudy_2_Product_Management\\Quan_ly_san_pham\\src\\main\\java\\data\\product.csv";

    public static ProductService productService;

    public ProductService(){

    }

    public static ProductService getProductService(){
        if (productService== null){
            productService = new ProductService();
        }return productService;
    }

    public double getPrice(long id) {
        return findById(id).getPrice();
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        List<String> records = FileUtils.readFile(PATH);
        for (String record : records ){
            products.add(Product.parseProduct(record));
        }
        return products;
    }

    @Override
    public void add(Object newInstant) {

    }

    @Override
    public void add(Product newProduct) {
        List<Product> products = findAll();
        products.add(newProduct);
        FileUtils.writeFile(PATH,products);
    }

    @Override
    public void removeById(long id) {
        List<Product> products = findAll();
        products.removeIf(product -> product.getIdProduct()==id);
        FileUtils.writeFile(PATH,products);
    }

    @Override
    public void update(Object newInstant) {

    }

    @Override
    public void update(Product newProduct) {
        List<Product> products = findAll();
        for (Product product : products) {
            if(product.getIdProduct()== newProduct.getIdProduct()){
                product.setNameProduct(newProduct.getNameProduct());
                product.setPrice(newProduct.getPrice());
                product.setQuantity(newProduct.getQuantity());
               // product.setTrademark(newProduct.getTrademark());
                product.setUpdateAt(Instant.now());
            }
        }
        FileUtils.writeFile(PATH,products);

    }

    @Override
    public boolean existsById(long id) {
        return findById(id) != null;
    }

    @Override
    public Product findById(long id) {
        List<Product> products = findAll();
        for (Product product:products) {
            if (id == product.getIdProduct()) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product findProductById(long id) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getIdProduct() == id) {
                return product;
            }
        }
        return null;
    }

    public void updateQuantity(long id, int quantity) {
        List<Product> products = findAll();
        for (Product product: products) {
            if (product.getIdProduct() == id) {
                if (product.getQuantity() >= quantity) {
                    product.setQuantity(product.getQuantity() - quantity);
                    FileUtils.writeFile(PATH, products);
                    break;
                }
            }
        }
    }
}
