package model;

public class OrderItem {
    private long id;
    private long productId;
    private String productName;
    private double price;
    private int quantity;

    private double total;
    private long orderId;

    public OrderItem() {
    }
    public OrderItem(long id, long productId, String productName, double price, int quantity, long orderId) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.total = price * quantity;
        this.orderId = orderId;
    }

    public OrderItem(long id, long productId, String productName, double price, int quantity, double total, long orderId) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.orderId = orderId;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return        id +
                "," + productId +
                "," + productName+
                "," + price +
                "," + quantity +
                "," + total+
                "," + orderId;
    }
    public static OrderItem parseOrderItem(String raw){
        OrderItem orderItem = new OrderItem();
        String [] item = raw.split(",");
        orderItem.id = Long.parseLong(item[0]);
        orderItem.productId = Long.parseLong(item[1]);
        orderItem.productName = item[2];
        orderItem.price = Double.parseDouble(item[3]);
        orderItem.quantity = Integer.parseInt(item[4]);
        orderItem.total = Double.parseDouble(item[5]);
        orderItem.orderId = Long.parseLong(item[6]);
        return orderItem;
    }


}
