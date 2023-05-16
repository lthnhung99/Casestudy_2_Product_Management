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
    public static Product parseProduct(String raw){
        Product product = new Product();
        //long idProduct, String nameProduct, double price, int quantity,Instant createAt , Instant updateAt
        String[] fields = raw.split(",");
        product.idProduct = Long.parseLong(fields[0]);
        product.nameProduct = fields[1];
        product.price = Double.parseDouble(fields[2]);
        product.quantity = Integer.parseInt(fields[3]);
        product.createAt = Instant.parse(fields[4]);
        String temp = fields[5];
        if(temp != null && !temp.equals("null"))
            product.updateAt = Instant.parse(temp);
        return product;
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
}
