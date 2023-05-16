package view.Order;

import model.Order;
import model.Role;
import model.User;
import service.OrderService;
import service.UserService;
import view.MainLauncher;
import view.SelectFunction;
import view.User.MenuUserView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchOrderView {
    public static Scanner scanner = new Scanner(System.in);
    public UserService userService;
    public OrderService orderService;
    OrderView orderView = new OrderView();

    public SearchOrderView(){
        orderService = OrderService.orderService();
        userService = UserService.getUserService();
    }

    public void search(long idUser){
        boolean isTrue = true;
        do {
            menuFindOrder();
            try {
                System.out.println("Chọn chức năng: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        findByOrderId(idUser);
                        break;
                    case 2:
                        findByFullName(idUser);
                        break;
                    case 3:
                        findByPhone(idUser);
                        break;
                    case 4:
                        findByAddress(idUser);
                        break;
                    case 5:
                        findByUserId();
                        break;
                    case 6 :
                        OrderViewLauncher.runOrder();
                        break;
                    case 7:
                        isTrue = false;
                        break;
                    default:
                        System.out.println("Nhập sai! Vui lòng nhập lại! ");
                        break;
                }
            }catch (Exception e) {
                System.out.println("Error!");
            }
        }while (isTrue);
    }

    private void findByUserId() {
        orderView.showOrder1(orderService.findAllPrintedOrder(), SelectFunction.SEARCH);
        System.out.println("TÌM KIẾM THEO NHÂN VIÊN");
        System.out.print("Nhập id nhân viên: ");
        long value = Long.parseLong(scanner.nextLine());
        List<Order> ordersFind = orderService.findUserById(value);
        if (ordersFind != null) {
            orderView.showOrder1(ordersFind, SelectFunction.SEARCH);
        } else {
            System.out.println("Không tìm thấy!");
        }

    }
    public void findByOrderId(long userId) {
        User user = userService.findById(userId);
        if (user.getRole() == Role.ADMIN) {
            orderView.showOrder1(orderService.findAllPrintedOrder(), SelectFunction.SEARCH);
        }
        if (user.getRole() == Role.USER) {
            orderView.showOrder1(orderService.findUserById(userId), SelectFunction.SEARCH);
        }
        System.out.println(" TÌM KIẾM THEO ID");
        System.out.print("Nhập id hóa đơn cần tìm: ");
        long orderId = Long.parseLong(scanner.nextLine());
        Order order = orderService.findByIdOrder(orderId);
        if (order != null) {
            List<Order> ordersFind = new ArrayList<>();
            ordersFind.add(order);
            orderView.showOrder1(ordersFind, SelectFunction.SEARCH);
        } else {
            System.out.println("Không tìm thấy!");
        }
    }

    private void findByFullName(long userId) {
        User user = userService.findById(userId);
        if (user.getRole() == Role.ADMIN) {
            orderView.showOrder1(orderService.findAllPrintedOrder(), SelectFunction.SEARCH);
        }
        if (user.getRole() == Role.USER) {
            orderView.showOrder1(orderService.findUserById(userId), SelectFunction.SEARCH);
        }
        System.out.println("TÌM KIẾM THEO KHÁCH HÀNG");
        System.out.print("Nhập tên khách hàng: ");
        String name = scanner.nextLine();
        List<Order> ordersFind = orderService.findByFullName(name);
        if (ordersFind != null) {
            orderView.showOrder1(ordersFind, SelectFunction.SEARCH);
        } else {
            System.out.println("Không tìm thấy!");
        }
    }

    private void findByPhone(long userId) {
        User user = userService.findById(userId);
        if (user.getRole() == Role.ADMIN) {
            orderView.showOrder1(orderService.findAllPrintedOrder(), SelectFunction.SEARCH);
        }
        if (user.getRole() == Role.USER) {
            orderView.showOrder1(orderService.findUserById(userId), SelectFunction.SEARCH);
        }
        System.out.println("TÌM KIẾM THEO SỐ ĐIỆN THOẠI");
        System.out.print("Nhập số điện thoại cần tìm: ");
        String value = scanner.nextLine();
        List<Order> ordersFind = orderService.findByPhone(value, userId);
        if (ordersFind != null) {
            orderView.showOrder1(ordersFind, SelectFunction.SEARCH);
        } else {
            System.out.println("Không tìm thấy");
        }
    }
    private void findByAddress(long userId) {
        User user = userService.findById(userId);
        if (user.getRole() == Role.ADMIN) {
            orderView.showOrder1(orderService.findAllPrintedOrder(), SelectFunction.SEARCH);
        }
        if (user.getRole() == Role.USER) {
            orderView.showOrder1(orderService.findUserById(userId), SelectFunction.SEARCH);
        }
        System.out.println("TÌM KIẾM THEO ĐỊA CHỈ");
        System.out.print("Nhập địa chỉ cần tìm: ");
        String value = scanner.nextLine();
        List<Order> ordersFind = orderService.findByAddress(value, userId);
        if (ordersFind != null) {
            orderView.showOrder1(ordersFind, SelectFunction.SEARCH);
        } else {
            System.out.println("Không tìm thấy!");
        }
    }
    private static void menuFindOrder() {


        System.out.println("TÌM KIẾM");

        System.out.println("1. Tìm kiếm theo ID");
        System.out.println("2. Tìm kiếm theo khách hàng");
        System.out.println("3. Tìm kiếm theo số điện thoại");
        System.out.println("4. Tìm kiếm theo địa chỉ");
        System.out.println("5. Tìm kiếm theo nhân viên");
        System.out.println("6. Quay lại");
        System.out.println("0. Thoát");


    }
}
