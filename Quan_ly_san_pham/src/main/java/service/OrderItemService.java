package service;

import model.OrderItem;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderItemService implements IOrderItemService{
    private static final String PATH = "D:\\case2\\src\\main\\java\\data\\orderItem.csv";
    private static final String pathPrintedOrderItem = "D:\\Casestudy_2_Product_Management\\Quan_ly_san_pham\\src\\main\\java\\data\\printOrder.csv";


    public static OrderItemService orderItemService;

    public OrderItemService(){

    }

    public static OrderItemService orderItemService(){
        if (orderItemService == null)
            orderItemService = new OrderItemService();
        return orderItemService;
    }
    @Override
    public List<OrderItem> findAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        List<String> records = FileUtils.readFile(PATH);
        for (String record : records) {
            orderItems.add(OrderItem.parseOrderItem(record));
        }
        return orderItems;
    }

    public static List<OrderItem> findAllPrintedOrderItem() {
        List<String> stringOrderItems = FileUtils.readFile(pathPrintedOrderItem);
        List<OrderItem> orderItemsList = new ArrayList<>();
        for (String strOrderItem : stringOrderItems){
            orderItemsList.add(OrderItem.parseOrderItem(strOrderItem));
        }
        return orderItemsList;
    }

    @Override
    public void add(OrderItem newInstant) {
        List<OrderItem> orderItems = findAll();
        orderItems.add(newInstant);
        FileUtils.writeFile(PATH,orderItems);
    }


    @Override
    public void removeById(long idOrder) {
        List<OrderItem> orderItemList = findAll();
        List<OrderItem> orderItemListPrinted = findAllPrintedOrderItem();
        for (int i=0;i<orderItemList.size();i++){
            if (orderItemList.get(i).getOrderId() == idOrder){
                orderItemListPrinted.add(orderItemList.get(i));
                orderItemList.remove(orderItemList.get(i));
                i--;
            }
        }
        FileUtils.writeFile(PATH , orderItemList);
        FileUtils.writeFile(pathPrintedOrderItem , orderItemListPrinted);
    }

    @Override
    public void update(OrderItem newInstant) {
        List<OrderItem> orderItems = findAll();
        for (OrderItem orderItem: orderItems) {
            if (orderItem.getId() == newInstant.getId()){
                orderItem.setProductId(newInstant.getProductId());
                orderItem.setProductName(newInstant.getProductName());
                orderItem.setPrice(newInstant.getPrice());
                orderItem.setQuantity(newInstant.getQuantity());
                orderItem.setOrderId(newInstant.getOrderId());
                orderItem.setTotal(newInstant.getTotal());
                break;
            }
        }
        FileUtils.writeFile(PATH,orderItems);
    }
    @Override
    public boolean existsById(long id) {
        return findById(id) != null;
    }

    public boolean existsByIdOrder(long id) {
        return findByOrderId(id) != null;
    }
    @Override
    public OrderItem findById(long id) {
        List<OrderItem> orderItems = findAll();
        for (OrderItem orderItem : orderItems){
            if ( id == orderItem.getId())
                return orderItem;
        }
        return null;
    }

    @Override
    public List<OrderItem> findByOrderId(long orderId) {
        List<OrderItem> orderItems = findAll();
        List<OrderItem> orderItemsFind = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getOrderId() == orderId) {
                orderItemsFind.add(orderItem);
            }
        }
        if (orderItemsFind.isEmpty()) {
            return null;
        }
        return orderItemsFind;
    }

    public double getGrandTotal1(long idOrder) {
        List<OrderItem> orderItemList = findAll();
        double sum = 0;
        for ( OrderItem item : orderItemList) {
            if (item.getOrderId() == idOrder) {
                sum += item.getTotal();
            }
        }
        return sum;

    }

    public void deleteById(long id) {
        List<OrderItem> orderItems = findAll();
        for (int i = 0; i < orderItems.size(); i++) {
            if ((orderItems.get(i)).getId() == id) {
                orderItems.remove(orderItems.get(i));
            }
        }
        FileUtils.writeFile(PATH, orderItems);
    }

    public OrderItem findOrderItem(long orderID, long productID){
        for (OrderItem orderItem : findAll()){
            if (orderItem.getOrderId() == orderID && orderItem.getProductId() == productID)
                return orderItem;
        }
        return null;
    }

    public boolean ExistsInOrder(long orderID, long productID){
        for (OrderItem orderItems : findAll()){
            if (orderItems.getOrderId() == orderID && orderItems.getProductId() == productID)
                return true;
        }
        return false;
    }



}
