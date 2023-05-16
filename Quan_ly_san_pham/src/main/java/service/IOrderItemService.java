package service;

import model.OrderItem;

import java.util.List;

public interface IOrderItemService extends  InterfaceService<OrderItem>{
    List<OrderItem> findByOrderId(long orderId);

    double getGrandTotal1(long idOrder);
}
