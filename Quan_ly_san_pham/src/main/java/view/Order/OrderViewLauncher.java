package view.Order;

import view.AdminView;
import view.MainLauncher;
import view.Select;
import view.User.MenuUserView;

import java.util.Scanner;


public class OrderViewLauncher {
    static Scanner scanner = new Scanner(System.in);
    static OrderView orderView = new OrderView();

    public static void runOrder() {
        int choice;
        do {
            menuOrder();
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        orderView.addOrder(AdminView.idOnlineUser);
                        break;
                    case 2:
                        orderView.updateOrder(AdminView.idOnlineUser);
                        break;
                    case 3:
                        orderView.showOrder(Select.SHOW);
                        break;
                    case 4:
                        SearchOrderView searchOrderView = new SearchOrderView();
                        searchOrderView.search(AdminView.idOnlineUser);
                        break;

                    case 5:
                        MainLauncher.menuOption();
                        break;
                    case 6:
                        MenuUserView.login();
                        break;
                    default:
                        System.out.println("Nhập sai! Vui lòng nhập lại!");
                }
            } catch (Exception e) {
            }
        } while (true);
    }

    public static void menuOrder() {
        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println("║               QUẢN LÝ ĐƠN HÀNG                      ║");
        System.out.println("╠═════════════════════════════════════════════════════╣");
        System.out.println("║ Options:                                            ║");
        System.out.println("║ ▶ 1.Tạo đơn hàng                                    ║");
        System.out.println("║ ▶ 2.Sửa đơn hàng                                    ║");
        System.out.println("║ ▶ 3.Xem đơn hàng                                    ║");
        System.out.println("║ ▶ 4.Tìm kiếm đơn hàng                               ║");
        System.out.println("║ ▶ 5.Quay lại MAIN MENU                              ║");
        System.out.println("║ ▶ 6.Thoát                                           ║");
        System.out.println("║ ▶ Chọn chức năng                                    ║");
        System.out.println("╚═════════════════════════════════════════════════════╝");

    }
}

