package view.Order;

import model.*;
import service.*;
import utils.AppUtils;
import utils.InstantUtils;
import utils.ValidateUtils;
import view.AdminView;
import view.Product.ProductView;
import view.SelectFunction;
import view.User.MenuUserView;
import view.User.UserView;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    public static Scanner scanner = new Scanner(System.in);
    public final IProductService productService;
    public final IOrderService orderService;
    public final OrderItemService orderItemService ;

    public final IUserService userService;

    public final User  user = new User();
    public final OrderItemView orderItemView = new OrderItemView();

    public OrderView(){
        productService = ProductService.getProductService();
        orderService = OrderService.orderService();
        orderItemService = OrderItemService.orderItemService();
        userService = UserService.getUserService();
    }

    public void addOrder(long idUser){
        try {
            idUser = AdminView.idOnlineUser;
            Order order = new Order();
            long orderId = System.currentTimeMillis() %100000;
            Instant creatAt = Instant.now();
            ProductView productView = new ProductView();
            productView.showProduct(SelectFunction.ADD);
            order.setCreatAt(creatAt);
            order.setIdUser(idUser);
            order.setId(orderId);
            orderItemView.addOrderItem(orderId);
            showOrderItemsByOrder(order);
            String name = inputName(SelectFunction.ADD);
            String phone = inputPhone(SelectFunction.ADD);
            String address = inputAddress(SelectFunction.ADD);
            order.setName(name);
            order.setPhone(phone);
            order.setAddress(address);
            order.setGrandTotal(orderItemService.getGrandTotal1(orderId));
            orderService.add(order);
            System.out.println("Tạo đơn thành công!");
        }catch (Exception e){
            System.out.println("Nhập sai!!! Vui lòng nhập lại!");
            e.printStackTrace();
        }
    }
    public void updateOrder(long userId) {
        User user = userService.findById(userId);
        int option;
        boolean isTrue = true;
        do {
            try {
                if (user.getRole() == Role.ADMIN)
                    showOrder1(orderService.findAll(), SelectFunction.UPDATE);
                if (user.getRole() == Role.USER)
                    showOrder1(orderService.findIdUserByOrder(userId), SelectFunction.UPDATE);
                long id = inputId(SelectFunction.UPDATE);
                Order order = orderService.findById(id);
                MenuOrder.menuUpdateOrder();
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        updateFullName(order);
                        break;
                    case 2:
                        updatePhone(order);
                        break;
                    case 3:
                        updateAddress(order);
                        break;
                    case 4:
                        updateOrderItem(order);
                        orderItemView.setGrandTotal(order.getId());
                        break;
                    case 5 :
                        isTrue = false;
                        break;
                    case 0:
                        System.out.println("Thoát khỏi chương trình");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                        break;
                }
                // isTrue = case4:
                isTrue = option != 6 && AppUtils.isRetry(SelectFunction.UPDATE);

            } catch (Exception e) {
                System.out.println("Sai cú pháp. Vui lòng nhập lại!");
            }
        } while (isTrue);

    }
    public void updateOrderItem(Order order) {
        boolean isTrue = true;
        String choose;
        do {
            try {
                if (orderItemService.findByOrderId(order.getId()) != null) {
                    orderItemView.showOrderItem1(orderItemService.findByOrderId(order.getId()), SelectFunction.UPDATE);
                    System.out.println(" Chọn 't' để thêm sản phẩm \t|\t  'y' để sửa sản phẩm \t|\t 'x' để xóa sản phẩm trong giỏ hàng ");
                    System.out.print("=>");
                    choose = scanner.nextLine();
                    switch (choose) {
                        case "t" :
                            ProductView productView = new ProductView();
                            productView.showProduct(SelectFunction.SHOW);
                            orderItemView.addOrderItem(order.getId());
                            showOrderItemsByOrder(order);
                            isTrue = false;
                            break;
                        case "y" :
                            orderItemView.updateOrderItem();
                            isTrue = false;
                            break;
                        case "x" :
                            orderItemView.removeOrderItem();
                            isTrue = false;
                            break;
                        default :
                            System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                    }
                } else {
                    System.out.println("Giỏ hàng rỗng!");
                    System.out.println("Chọn 'y' để thêm sản phẩm \t|\t 'q' để quay lại");
                    choose = scanner.nextLine();
                    switch (choose) {
                        case "y" :
                            ProductView productView = new ProductView();
                            productView.showProduct(SelectFunction.SHOW);
                            orderItemView.addOrderItem(order.getId());
                            break;
                        case "q" :
                            isTrue = false;
                        default :
                            System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                            isTrue = true;
                    }
                    isTrue = false;
                }
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Vui lòng nhập lại!");
            }
        } while (isTrue);
    }

    private void updateAddress(Order order) {
        String address = inputAddress(SelectFunction.UPDATE);
        order.setAddress(address);
        orderService.update(order);
        System.out.println("Cập nhật địa chỉ thành công!");
    }

    private void updatePhone(Order order) {
        String phone = inputPhone(SelectFunction.UPDATE);
        order.setPhone(phone);
        orderService.update(order);
        System.out.println("Cập nhật số điện thoại thành công!");
    }

    private void updateFullName(Order order) {
        String fullName = inputName(SelectFunction.UPDATE);
        order.setName(fullName);
        orderService.update(order);
        System.out.println("Cập nhật tên khách hàng thành công!");
    }

    private long inputId(SelectFunction choose) {
        long id;
        switch (choose) {
            case SHOW -> System.out.println("Nhập ID đơn hàng: ");

            case UPDATE -> System.out.println("Nhập ID đơn hàng bạn muốn chỉnh sửa: ");

            case REMOVE -> System.out.println("Nhập ID đơn hàng bạn muốn xóa: ");
        }
        boolean isTrue = true;
        do {
            id = AppUtils.retryParseLong();
            boolean FindId = orderService.existsById(id);
            if (FindId) {
                isTrue = false;
            } else {
                System.out.println("Không tìm thấy, vui lòng nhập lại!");
            }
        } while (isTrue);
        return id;
    }


    public void showOrderItemsByOrder(Order order) {
        System.out.println("Danh sách các món bạn đang chọn");
        orderItemView.showOrderItem(order);
    }
    private String inputAddress(SelectFunction choose) {
        String address;
        do {
            System.out.println("Nhập địa chỉ (Huế )");
            System.out.print("➤ ");
            address = scanner.nextLine();
            if (address.trim().isEmpty()) {
                System.out.println("Địa chỉ không được để trống!!");
                System.out.println("Nhập lại địa chỉ:(vd:Huế )");
            }
        } while (address.trim().isEmpty());
        return address;

    }

    private String inputPhone(SelectFunction choose) {
        System.out.println("Nhập số điện thoại(Gồm 10 số, bắt đầu là số 0)");
        System.out.print("➤ ");
        String phone = scanner.nextLine();
        while (!ValidateUtils.isPhoneValid(phone)) {
            System.out.println("Số điện thoại(Gồm 10 số, bắt đầu là số 0)");
            phone = scanner.nextLine();
        }
        return phone;
    }

    private String inputName(SelectFunction choose) {
        System.out.println("Nhập chức năng:");
        System.out.println("Nhập họ và tên(Tên phải viết hoa chữ cái đầu)");

        String name = scanner.nextLine();
        while (!ValidateUtils.isFullNameValid(name)) {
            System.out.println("Tên " + name + " không hợp lệ!" + "Vui lòng nhập lại" + "Tên phải viết hoa chữ cái đầu, có dấu");
            System.out.println("Nhập tên(Tên phải viết hoa chữ cái đầu");

            name = scanner.nextLine();
        }
        return name;
    }

    public void showOrder(SelectFunction choose){
        List<Order> orders = orderService.findAll();
//        id + "," + userId+ "," + phone + "," + address+ "," + grandTotal+ "," + name + "," + creatAt+ ","+ updateAt;
        System.out.println(" DANH SÁCH ĐƠN HÀNG ");
        System.out.printf("| %-5s%-9s | %-8s%-18s | %-6s%-10s | %-5s%-24s  | %-7s%-15s | %-11s%-24s | %-2s%-20s |\n",
                "", "ID",
                "", "KHÁCH HÀNG ",
                "", "SĐT",
                "", "ĐỊA CHỈ",
                "", " TỔNG TIỀN",
                "", "NHÂN VIÊN (ID)",
                "", "THỜI GIAN TẠO"
        );
        for (Order order : orders) {
            System.out.printf("| %-2s%-12s | %-3s%-23s | %-3s%-13s | %-5s%-24s  | %-4s%-18s | %-2s%-33s | %-2s%-20s |\n",
                    "", order.getId(),
                    "",order.getName() ,
                    "", order.getPhone(),
                    "", order.getAddress(),
                    "", order.getGrandTotal(),
                    "", userService.findNameById(order.getIdUser()) + " (" + order.getIdUser()+ ")" ,
                    "", InstantUtils.instantToString(order.getCreatAt())
            );
        }

        if (choose == SelectFunction.SHOW || choose == SelectFunction.PRINT) {
            orderItemView.showAllItemOfOrder(choose);
        }
    }

    public void showOrdersOfEmployee(long userId , SelectFunction choose){
        List<Order> orders = orderService.findIdUserByOrder(userId);
        if (orders.size() != 0) {
//        id + "," + userId+ "," + phone + "," + address+ "," + grandTotal+ "," + name + "," + creatAt+ ","+ updateAt;
            System.out.println("DANH SÁCH ĐƠN HÀNG ");

            System.out.printf("| %-5s%-9s | %-8s%-18s | %-6s%-10s | %-5s%-24s  | %-7s%-15s | %-11s%-24s | %-2s%-20s |\n",
                    "", "ID",
                    "", "KHÁCH HÀNG ",
                    "", "SĐT",
                    "", "ĐỊA CHỈ",
                    "", " TỔNG TIỀN",
                    "", "NHÂN VIÊN (ID)",
                    "", "THỜI GIAN TẠO"
            );
            for (Order order : orders) {
                System.out.printf("| %-2s%-12s | %-3s%-23s | %-3s%-13s | %-5s%-24s  | %-4s%-18s | %-2s%-33s | %-2s%-20s |\n",
                        "", order.getId(),
                        "", order.getName(),
                        "", order.getPhone(),
                        "", order.getAddress(),
                        "", order.getGrandTotal(),
                        "", userService.findNameById(order.getIdUser()) + " (" + order.getIdUser() + ")",
                        "", InstantUtils.instantToString(order.getCreatAt())
                );
            }

            orderItemView.showAllItemOfOrder(choose);
        }else {
            System.out.println("Danh sách trống . Hãy thêm đơn hàng.");
            addToEmptyList(userId);
        }
    }
    public void addToEmptyList(long orderId ){
        boolean flag = true;
        do {
            System.out.println(" Chọn 't' để thêm sản phẩm \t|\t 'q' để quay lại.");
            String option = scanner.nextLine();
            switch (option) {
                case "t":
                    addOrder(orderId);
                case "q":
                    flag = false;
                    break;
                default:
                    System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                    flag = true;
            }
        } while (flag);
    }

    public void showOrder1(List<Order> orders, SelectFunction choose){
//        id + "," + userId+ "," + phone + "," + address+ "," + grandTotal+ "," + name + "," + creatAt+ ","+ updateAt;
        System.out.println("DANH SÁCH ĐƠN HÀNG");
        System.out.printf("| %-5s%-9s | %-8s%-18s | %-6s%-10s | %-5s%-24s  | %-7s%-15s | %-11s%-24s | %-2s%-20s |\n",
                "", "ID",
                "", " KHÁCH HÀNG",
                "", "SĐT",
                "", "ĐỊA CHỈ",
                "", "TỔNG TIỀN",
                "", " NHÂN VIÊN (ID)",
                "", "THỜI GIAN TẠO"
        );

        for (Order order : orders) {
            System.out.printf("| %-2s%-12s | %-3s%-23s | %-3s%-13s | %-5s%-24s  | %-4s%-18s | %-2s%-33s | %-2s%-20s |\n",
                    "", order.getId(),
                    "", order.getName(),
                    "", order.getPhone(),
                    "", order.getAddress(),
                    "", order.getGrandTotal(),
                    "", userService.findNameById(order.getIdUser()) + " (" + order.getIdUser()+ ")" ,
                    "", InstantUtils.instantToString(order.getCreatAt())
            );
        }

        if (choose == SelectFunction.SHOW ) {
            orderItemView.showAllItemOfOrder(choose);
        }
    }
}
