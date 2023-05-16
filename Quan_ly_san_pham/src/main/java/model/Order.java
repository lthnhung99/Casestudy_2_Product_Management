package model;


import java.time.Instant;
import java.util.List;

public class Order {
    private long id;
    private String name;
    private String phone;
    private String address;
    private double grandTotal;
    private List<OrderItem> orderItems;
    private long idUser;
    private Instant creatAt;


    public Order() {
    }

    public Order(long id, long idUser, String name, String phone, String address, double grandTotal, Instant creatAt) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.grandTotal = grandTotal;
        this.creatAt = creatAt;
    }

    public Order(long orderId, String name, String phone, String address, Instant creatAt) {
        this.id = orderId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.creatAt = creatAt;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Instant getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Instant creatAt) {
        this.creatAt = creatAt;
    }


    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }


    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    @Override
    public String toString() {
        return id + "," +
                idUser +
                "," +
                phone +
                "," +
                address +
                "," +
                grandTotal +
                "," +
                name +
                "," +
                creatAt;
    }
    public static Order parseOrder(String raw) {
        Order order = new Order();
        String[] item = raw.split(",");
        order.id = Long.parseLong(item[0]);
        order.idUser = Long.parseLong(item[1]);
        order.phone = item[2];
        order.address = item[3];
        order.grandTotal = Double.parseDouble(item[4]);
        order.name = item[5];
        order.creatAt = Instant.parse(item[6]);
        return order;
    }


}
