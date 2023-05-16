package model;

import java.time.Instant;

public class Product {
    private long idProduct ;
    private String nameProduct;
    private double price;
    private int quantity ;
    private Instant createAt;
    private Instant updateAt;

    public Product() {
    }

    public Product(long idProduct, String nameProduct, double price, int quantity,Instant createAt , Instant updateAt) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }


    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return idProduct + ","
                + nameProduct + ","
                + price + ","
                + quantity + ","
                + createAt+ ","
                + updateAt;
    }

    public static Product parseProduct(String raw){
        Product product = new Product();
        //long idProduct, String nameProduct, double price, int quantity,Instant createAt , Instant updateAt
        String[] item = raw.split(",");
        product.idProduct = Long.parseLong(item[0]);
        product.nameProduct = item[1];
        product.price = Double.parseDouble(item[2]);
        product.quantity = Integer.parseInt(item[3]);
        product.createAt = Instant.parse(item[4]);
        String temp = item[5];
        if(temp != null && !temp.equals("null"))
            product.updateAt = Instant.parse(temp);
        return product;
    }
}
