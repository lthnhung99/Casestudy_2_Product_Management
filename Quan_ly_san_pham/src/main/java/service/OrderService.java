package service;

import model.Order;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {
    private static final String PATH = "D:\\Casestudy_2_Product_Management\\Quan_ly_san_pham\\src\\main\\java\\data\\order.csv";
    private static final String pathPrintedOrder = "D:\\Casestudy_2_Product_Management\\Quan_ly_san_pham\\src\\main\\java\\data\\printOrder.csv";

    public static OrderService orderService;

    public OrderService() {

    }

    public static OrderService orderService() {
        if (orderService == null)
            orderService = new OrderService();
        return orderService;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        List<String> records = FileUtils.readFile(PATH);
        for (String record : records) {
            orders.add(Order.parseOrder(record));
        }
        return orders;
    }

    public List<Order> findAllPrintedOrder() {
        List<Order> orders = new ArrayList<>();
        List<String> records = FileUtils.readFile(pathPrintedOrder);
        for (String record : records) {
            orders.add(Order.parseOrder(record));
        }
        return orders;
    }

    @Override
    public void add(Order newInstant) {
        List<Order> orders = findAll();
        orders.add(newInstant);
        FileUtils.writeFile(PATH, orders);
    }

    @Override
    public void removeById(long id) {
        List<Order> orderList = findAll();
        List<Order> orderRemovedList = findAllPrintedOrder();
        for (Order order : orderList) {
            if (order.getId() == id) {
                orderRemovedList.add(order);
                orderList.remove(order);
                break;
            }
        }
        FileUtils.writeFile(PATH, orderList);
        FileUtils.writeFile(pathPrintedOrder, orderRemovedList);
    }

    @Override
    public void update(Order newInstant) {
        List<Order> orders = findAll();
        for (Order order : orders) {
            if (order.getId() == newInstant.getId()) {
                order.setName(newInstant.getName());
                order.setPhone(newInstant.getPhone());
                order.setAddress(newInstant.getAddress());
                order.setGrandTotal(newInstant.getGrandTotal());
                order.setIdUser(newInstant.getIdUser());
                FileUtils.writeFile(PATH, orders);
                break;
            }
        }
    }

    @Override
    public boolean existsById(long id) {
        List<Order> orders = findAll();
        for (Order order : orders) {
            if (id == order.getId())
                return true;
        }
        return false;
    }

    @Override
    public Order findById(long id) {
        List<Order> orders = findAll();
        for (Order order : orders) {
            if (id == order.getId())
                return order;
        }
        return null;
    }

    @Override
    public List<Order> findUserById(long idUser) {
        List<Order> orders = findAllPrintedOrder();
        List<Order> findId = new ArrayList<>();
        for (Order order : orders) {
            if (order.getIdUser() == idUser)
                findId.add(order);
        }
        return findId;
    }

    public List<Order> findIdUserByOrder(long idUser) {
        List<Order> orders = findAll();
        List<Order> findId = new ArrayList<>();
        for (Order order : orders) {
            if (order.getIdUser() == idUser)
                findId.add(order);
        }
        return findId;
    }
    public List<Order> findByFullName(String value) {
        List<Order> orders = findAllPrintedOrder();
        List<Order> find = new ArrayList<>();
        for (Order item : orders) {
            if ((item.getName().toUpperCase()).contains(value.toUpperCase())) {
                find.add(item);
            }
        }
        if (find.isEmpty()) {
            return null;
        }
        return find;
    }


    public List<Order> findByPhone(String value, long userId) {
        List<Order> orders = findUserById(userId);
        List<Order> find = new ArrayList<>();
        for (Order item : orders) {
            if ((item.getPhone().toUpperCase()).contains(value.toUpperCase())) {
                find.add(item);
            }
        }
        if (find.isEmpty()) {
            return null;
        }
        return find;
    }

    public List<Order> findByAddress(String value, long userId) {
        List<Order> orders = findAllPrintedOrder();
        List<Order> find = new ArrayList<>();
        for (Order item : orders) {
            if ((item.getAddress().toUpperCase()).contains(value.toUpperCase())) {
                find.add(item);
            }
        }
        if (find.isEmpty()) {
            return null;
        }
        return find;
    }

    public Order findByIdOrder(long orderId) {
        List<Order> orders = findAllPrintedOrder();
        for (Order order : orders) {
            if ( order.getId()==orderId)
                return order;
        }
        return null;
    }
}
