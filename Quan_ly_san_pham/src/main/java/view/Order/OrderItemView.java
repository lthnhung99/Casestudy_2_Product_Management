package view.Order;

import model.Order;
import model.OrderItem;
import model.Product;
import service.*;
import utils.AppUtils;
import utils.InstantUtils;
import view.Product.ProductView;
import view.SelectFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderItemView {
    private static Scanner scanner = new Scanner(System.in);
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    private final ProductView productView = new ProductView();

    public OrderItemView(){
        productService = ProductService.getProductService();
        orderService = OrderService.orderService();
        orderItemService = OrderItemService.orderItemService();
    }

    public void addOrderItem(long orderId){
        boolean checkOrderItem = false;
        do {
            System.out.println("Nhập ID sản phẩm muốn mua");
            int idPro = Integer.parseInt(scanner.nextLine());
            while (!productService.existsById(idPro)) {
                System.out.println("ID không tồn tại!");
                System.out.println("Nhập ID sản phẩm");
                idPro = scanner.nextInt();
            }
            Product product = productService.findById(idPro);
            int oldQuantity = product.getQuantity();
            System.out.println("Nhập số lượng muốn mua");
            int quantity;
            do {
                quantity = AppUtils.retryParseInt();
                if (quantity < 0)
                    System.out.println("(Số lượng phải lớn hơn 0)");
            } while (quantity < 0);
            while (!checkQuantity(product, quantity)) {
                System.out.println("Nhập sai số lượng!!! Vui lòng nhập lại!");
                System.out.println("Nhập số lượng");
                quantity = scanner.nextInt();
            }
            int currentQuantity = oldQuantity - quantity;
            product.setQuantity(currentQuantity);
            productService.updateQuantity(idPro,quantity);
            if (orderItemService.ExistsInOrder(orderId, idPro)) {
                OrderItem orderItems = orderItemService.findOrderItem(orderId, idPro);
                orderItems.setQuantity(orderItems.getQuantity() + quantity);
                double total =  product.getPrice()* orderItems.getQuantity();
                orderItems.setTotal(total);
                setGrandTotal(orderId);
                orderItemService.update(orderItems);
            }else {
                OrderItem orderItem = new OrderItem(System.currentTimeMillis() % 1000, idPro, product.getNameProduct(), product.getPrice(), quantity, quantity * product.getPrice(), orderId);
                ////(long orderId, String name, String phone, String address, Instant creatAt)
                orderItemService.add(orderItem);
            }
            System.out.println("Đã thêm sản phẩm vào đơn hàng.");
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            boolean checkContinue = false;
            do {
                System.out.println("Bạn có muốn đặt thêm sản phẩm?");
                System.out.println("----------------------------------------------------------------------------------------------------------------");
                System.out.println("Chọn 'Y' để thêm sản phẩm || 'N' để ngừng thêm sản phẩm");
                System.out.print("Nhập chức năng: ");
                String choices = scanner.nextLine();
                switch (choices){
                    case "y":
                        checkOrderItem = true;
                        productView.showProduct(SelectFunction.SHOW);
                        break;
                    case "n":
                        checkOrderItem = false;
                        break;
                    default:
                        System.out.println("Chọn sai chức năng. Kiểm tra lại!");
                        checkContinue = true;
                        break;
                }
            }while (checkContinue); // true
        }while (checkOrderItem);
    }
    public boolean checkQuantity(Product product, int quantity) {
        if (quantity <= product.getQuantity())
            return true;
        else
            return false;
    }
    public void removeOrderItem() {

        long id = inputIdOrderItem(SelectFunction.REMOVE);
        int option;
        boolean isTrue = true;
        do {
            try {
                AppUtils.menuDelete();
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1 :
                        OrderItem orderItem = orderItemService.findById(id);
                        setProductQuantity(orderItem.getProductId(), orderItem.getQuantity());
                        orderItemService.deleteById(id);
                        System.out.println("Xóa thành công!");
                        setGrandTotal(orderItem.getOrderId());
                        isTrue =false;
                    case 2 :
                        isTrue = false;
                        break;
                    default :
                        System.out.println("Lựa chọn sai, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Lỗi cú pháp. Vui lòng nhập lại!");
            }
        } while (isTrue);
    }

    private void setProductQuantity(long productId, int quantity) {
        Product product = productService.findProductById(productId);
        product.setQuantity(product.getQuantity() - quantity);
        productService.update(product);
    }

    public void updateOrderItem() {
        int option;
        boolean isTrue = true;
        do {
            try {
                long id = inputIdOrderItem(SelectFunction.UPDATE);
                OrderItem orderItem = orderItemService.findById(id);
                MenuOrderItem.menuUpdateOrderItem();
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1 :
                        updateProductId(orderItem);
                        isTrue = false;
                        break;
                    case 2 :
                        updateQuantity(orderItem);
                        isTrue = false;
                        break;
                    case 3 :
                        isTrue = false;
                        break;
                    case 0 :
                        System.out.println("Exit the program...");
                        System.exit(0);
                    default :
                        System.out.println("Lựa chọn sai, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Lỗi cú pháp. Vui lòng nhập lại!");
            }
        } while (isTrue);
    }
    private void updateProductId(OrderItem orderItem) {
        boolean flag = true;
        do {
            try {
                productView.showProduct1(productService.findAll(), SelectFunction.SHOW);
                long productId = inputProductId(SelectFunction.UPDATE);
                orderItem.setProductId(productId);
                Product product = productService.findProductById(productId);
                orderItem.setPrice(product.getPrice());
                updateQuantity(orderItem);
                System.out.println("Chỉnh sửa sản phẩm thành công!");
                setGrandTotal(orderItem.getOrderId());
                List<OrderItem> orderItems = new ArrayList<>();
                orderItems.add(orderItem);
                orderItemService.update(orderItem);
//                showOrderItem2(orderItems);
                flag = false;
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Vui lòng nhập lại!");
            }
        } while (flag);
    }

    private void updateQuantity(OrderItem orderItem) {
        boolean flag = true;
        do {
            try {
                int oldQuantity = orderItem.getQuantity();
                int newQuantity = inputQuantity(SelectFunction.UPDATE, orderItem.getProductId());
                setProductQuantity(orderItem.getProductId(), newQuantity - oldQuantity);
                orderItem.setQuantity(newQuantity);
                orderItem.setTotal(orderItem.getPrice()*newQuantity);
                orderItemService.update(orderItem);
                System.out.printf("Chỉnh sửa số lượng sản phầm từ %s thành %s.\n", oldQuantity, newQuantity);
                List<OrderItem> orderItems = new ArrayList<>();
                orderItems.add(orderItem);
                showOrderItem2(orderItems);
                flag = false;
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Vui lòng nhập lại!");
            }
        } while (flag);
    }


    public void setGrandTotal(long orderId) {
        Order order = orderService.findById(orderId);
        List<OrderItem> orderItems = orderItemService.findByOrderId(orderId);
        if (orderItems == null) {
            order.setGrandTotal(0);
        } else {
            double grandTotal = 0;
            for (OrderItem orderItem : orderItems) {
                grandTotal = grandTotal + orderItem.getTotal();
            }
            order.setGrandTotal(grandTotal);
        }
        orderService.update(order);
    }

    private long inputProductId(SelectFunction choose) {
        switch (choose) {
            case ADD -> System.out.println("Nhập ID sản phẩm: ");

            case UPDATE -> System.out.println("Nhập ID sản phẩm muốn chỉnh sửa: ");

            case REMOVE -> System.out.println("Nhập ID sản phẩm muốn xóa: ");
        }
        long id;
        boolean flag = true;
        do {
            id = AppUtils.retryParseLong();
            boolean FindId = productService.existsById(id);
            if (FindId) {
                Product product = productService.findById(id);
                if (product.getQuantity() == 0)
                    System.out.println("Số lượng sản phẩm đã hết, vui lòng chọn sản phẩm khác!");
                else flag = false;
            } else {
                System.out.println("Không tìm thấy! Vui lòng nhập lại");
            }
        } while (flag);
        return id;
    }
    private int inputQuantity(SelectFunction choose, long productId) {
        Product product = productService.findProductById(productId);
        switch (choose) {
            case ADD -> System.out.println("Nhập số lượng sản phẩm: ");

            case UPDATE -> System.out.println("Nhập số lượng sản phẩm mới: ");
        }
        int quantity;
        do {
            quantity = AppUtils.retryParseInt();
            if (quantity < 0) {
                System.out.println("Số lượng sản phẩm không thể âm, vui lòng nhập lại!");
            }
            if (quantity > product.getQuantity()) {
                System.out.printf("Số lượng '%s' vượt quá '%s' sản phẩm hiện có! Vui lòng nhập lại!\n", product.getNameProduct(), product.getQuantity());
            }

        } while (quantity < 0 || quantity > product.getQuantity());
        return quantity;
    }
    public void showOrderItem(Order order) {
        System.out.println(" GIỎ HÀNG ");

        System.out.printf("| %-5s%-9s | %-11s%-19s | %-7s%-11s | %-2s%-10s | %-5s%-17s |\n",
                "", "ID",
                "", "SẢN PHẨM",
                "", "GIÁ",
                "", "SỐ LƯỢNG",
                "", "THÀNH TIỀN"
        );

        for (OrderItem orderItem : orderItemService.findAll()) {
            if (orderItem.getOrderId() == order.getId()){
                System.out.printf("| %-2s%-12s | %-2s%-28s | %-2s%-16s | %-4s%-8s | %-2s%-20s |\n",
                        "", orderItem.getId(),
                        "", productService.findProductById(orderItem.getProductId()).getNameProduct(),
                        "", AppUtils.doubleToVND(orderItem.getPrice()),
                        "", orderItem.getQuantity(),
                        "", AppUtils.doubleToVND(orderItem.getTotal())
                );
            }
        }

    }
    public void showOrderItem2(List<OrderItem> orderItems) {
        System.out.println("GIỎ HÀNG");
        System.out.printf("| %-5s%-9s | %-11s%-19s | %-7s%-11s | %-2s%-10s | %-5s%-17s |\n",
                "", "ID",
                "", "SẢN PHẨM",
                "", "GIÁ",
                "", "SỐ LƯỢNG",
                "", "THÀNH TIỀN"
        );
        for (OrderItem orderItem : orderItems) {
            System.out.printf("| %-2s%-12s | %-2s%-28s | %-2s%-16s | %-4s%-8s | %-2s%-20s |\n",
                    "", orderItem.getId(),
                    "", productService.findProductById(orderItem.getProductId()).getNameProduct(),
                    "", AppUtils.doubleToVND(orderItem.getPrice()),
                    "", orderItem.getQuantity(),
                    "", AppUtils.doubleToVND(orderItem.getTotal())
            );
        }

    }

    public void showOrderItem1(List<OrderItem> orderItems, SelectFunction choose) {
        System.out.println("GIỎ HÀNG");
        System.out.printf("| %-5s%-9s | %-11s%-19s | %-7s%-11s | %-2s%-10s | %-5s%-17s |\n",
                "", "ID",
                "", "SẢN PHẨM",
                "", "GIÁ",
                "", "SỐ LƯỢNG",
                "", "THÀNH TIỀN"
        );
        for (OrderItem orderItem : orderItems) {
            System.out.printf("| %-2s%-12s | %-2s%-28s | %-2s%-16s | %-4s%-8s | %-2s%-20s |\n",
                    "", orderItem.getId(),
                    "", productService.findProductById(orderItem.getProductId()).getNameProduct(),
                    "", AppUtils.doubleToVND(orderItem.getPrice()),
                    "", orderItem.getQuantity(),
                    "", AppUtils.doubleToVND(orderItem.getTotal())
            );
        }
        if (choose != SelectFunction.UPDATE && choose != SelectFunction.REMOVE && choose != SelectFunction.SEARCH);
    }


    public void showAllItemOfOrder(SelectFunction  selectFunction) {
        boolean flag = true;
        long orderId;
        do {
            if (selectFunction == SelectFunction.SHOW){
                System.out.println("Chọn 'y' để xem chi tiết đơn hàng \t|\t 'q' để trở lại.");
            }
            System.out.print(" => ");
            String choose = scanner.nextLine();
            switch (choose) {
                case "y":
                    System.out.println("XEM CHI TIẾT ĐƠN HÀNG");
                    orderId = inputIdOrder(SelectFunction.SHOW);
                    System.out.println("ID Order:" + orderId);
                    showOrderItem1(orderItemService.findByOrderId(orderId), SelectFunction.PRINT);
                    isRetryAddOrderItem(orderId);
                case "q":
                    flag = false;
                    break;
                default:
                    System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                    System.out.print(" => ");
            }
        } while (flag);
    }
    public boolean isRetryAddOrderItem(long orderId) {
        do {
            System.out.println(" Chọn 't' tiếp tục thêm sản phẩm \t|\t 'i' để in hóa đơn \t|\t 'e' để thoát.");

            String option = scanner.nextLine();
            switch (option) {
                case "t":
                    productView.showProduct1(productService.findAll(), SelectFunction.SHOW);
                    addOrderItem(orderId);
                    setGrandTotal(orderId);
                    checkContinueOrderItem(orderId);
                    return false;
                case "i":
                    setGrandTotal(orderId);
                    printProductInvoice(orderId);
                    return false;
                case "e":
                    List<OrderItem> orderItems = orderItemService.findByOrderId(orderId);
                    for (OrderItem orderItem : orderItems) {
                        setProductQuantity(orderItem.getProductId(), orderItem.getQuantity());
                    }
                    System.out.println("Exit the program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                    break;
            }
        } while (true);
    }
    public void checkContinueOrderItem(long orderId){
        boolean checkContinue1 = false;
        do {
            System.out.println("Bạn có muốn in đơn hàng?");
            System.out.println("Chọn 'i' để in \t || chọn 'q' để quay lại");
            System.out.print(">Nhập chức năng: ");
            String choices = scanner.nextLine();
            switch (choices){
                case "i":
                    printProductInvoice(orderId);
                    break;
                case "q":
                    checkContinue1 = false;
                    break;
                default:
                    System.out.println("Chọn sai chức năng. Kiểm tra lại!");
                    checkContinue1 = true;
                    break;
            }
        }while (checkContinue1);
    }
    private void removePrintedOrders(long orderId) {
        setGrandTotal(orderId);
        orderService.removeById(orderId);
        orderItemService.removeById(orderId);
    }

    private long inputIdOrder(SelectFunction choose) {
        long id;
        switch (choose) {
            case SHOW -> System.out.println("Nhập ID : ");

            case UPDATE -> System.out.println("Nhập ID muốn chỉnh sửa: ");

            case REMOVE -> System.out.println("Nhập ID muốn xóa: ");

            case PRINT -> System.out.println("Nhập ID muốn in: ");
        }
        boolean flag = false;
        do {
            id = AppUtils.retryParseLong();
            boolean FindId = orderItemService.existsByIdOrder(id);
            if (FindId) {
                flag = true;
            } else {
                System.out.println("Không tìm thấy! Vui lòng nhập lại");
            }
        } while (!flag);
        return id;
    }
    private long inputIdOrderItem(SelectFunction choose) {
        long id;
        switch (choose) {
            case SHOW -> System.out.println("Nhập ID : ");

            case UPDATE -> System.out.println("Nhập ID muốn chỉnh sửa: ");

            case REMOVE -> System.out.println("Nhập ID muốn xóa: ");

            case PRINT -> System.out.println("Nhập ID muốn in: ");
        }
        boolean flag = false;
        do {
            id = AppUtils.retryParseLong();
            boolean FindId = orderItemService.existsById(id);
            if (FindId) {
                flag = true;
            } else {
                System.out.println("Không tìm thấy! Vui lòng nhập lại");
            }
        } while (!flag);
        return id;
    }

    public void printProductInvoice(long orderId) {
        List<OrderItem> orderItems = orderItemService.findByOrderId(orderId);
        Order order = orderService.findById(orderId);

        System.out.println("HÓA ĐƠN THANH TOÁN");
        System.out.println("│                                                                                                 │");
        System.out.printf("│   Người mua: %-25s  ĐƠN THANH TOÁN           Thời gian: %16s    │\n", order.getName(), InstantUtils.instantToString(order.getCreatAt()));
        System.out.printf("│   Số điện thoại: %-30s                         (MISS. MIÊU)            │\n", order.getPhone());
        System.out.printf("│   Địa chỉ: %-30s                             Liên Hệ: 0938479238       │\n", order.getAddress());

        System.out.printf("│ %-2s%-5s | %-11s%-19s | %-7s%-11s | %-1s%-9s | %-2s%-16s │\n",
                "", "STT",
                "", "SẢN PHẨM",
                "", "GIÁ",
                "", "SỐ LƯỢNG",
                "", "THÀNH TIỀN"
        );

        for (int i = 0; i < orderItems.size(); i++) {
            OrderItem orderItem = orderItems.get(i);
            System.out.printf("│ %-3s%-4s | %-2s%-28s | %-2s%-16s | %-4s%-6s | %-2s%-16s │\n",
                    "", i + 1,
                    "", productService.findProductById(orderItem.getProductId()).getNameProduct(),
                    "", AppUtils.doubleToVND(orderItem.getPrice()),
                    "", orderItem.getQuantity(),
                    "", AppUtils.doubleToVND(orderItem.getPrice() * orderItem.getQuantity())
            );
        }


        System.out.printf("│            Cám Ơn Quý Khách!                                   Tổng tiền: %-20s  │\n", AppUtils.doubleToVND(orderItemService.getGrandTotal1(orderId)));


        removePrintedOrders(orderId);
        AppUtils.pressToContinue();
    }



}
