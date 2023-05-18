package view;

import view.Order.OrderViewLauncher;
import view.Product.MenuProductView;
import view.User.MenuUserView;
import view.User.UserViewLauncher;

import java.util.Scanner;

public class MainLauncher {
    static Scanner scanner = new Scanner(System.in);

    public MainLauncher() {
        launch();
    }
    public static void launch() {
        MenuUserView.login();
    }


    public static void menuOption() {
        do {
            mainMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        UserViewLauncher.launch();
                        break;
                    case 2:
                        MenuProductView.runProduct();
                        break;
                    case 3:
                        OrderViewLauncher.runOrder();
                        break;
                    case 4:
                        MenuUserView.login();
                        break;
                    default:
                        System.err.println("Nhập sai! Vui lòng nhập lại! ");
                        break;
                }
            }catch (Exception e) {
                System.err.println("Error!");
            }
        }while (true);
    }

    public static void mainMenu() {
        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println("║                     MENU QUẢN LÝ                    ║");
        System.out.println("╠═════════════════════════════════════════════════════╣");
        System.out.println("║ Options:                                            ║");
        System.out.println("║ ▶ 1.Quản lý người dùng                              ║");
        System.out.println("║ ▶ 2.Quản lý sản phẩm                                ║");
        System.out.println("║ ▶ 3.Quản lý đơn đặt hàng                            ║");
        System.out.println("║ ▶ 4.Đăng xuất                                       ║");
        System.out.println("║ ▶ Chon chức năng                                    ║");
        System.out.println("╚═════════════════════════════════════════════════════╝");


    }
}


