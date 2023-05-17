package view;

import model.Role;
import model.User;
import service.IUserService;
import service.UserService;
import utils.AppUtils;
import view.User.MenuUserView;

import java.util.Scanner;

public class AdminView {
    private final IUserService userService;

    private static Scanner sc = new Scanner(System.in);
    public static long idOnlineUser;

    public AdminView() {
        userService = UserService.getUserService();
    }

    public void adminLogin() {
        boolean isRetry = false;
        System.out.println(" *************CỬA HÀNG MỸ PHẨM************* ");
        System.out.println(" ---------------Đăng nhập---------------");
        do {
            System.out.println("Tên tài khoản: ");
            String username = AppUtils.retryString("Username");
            System.out.println("Mật khẩu: ");
            String password = AppUtils.retryString("Mật khẩu");
            User user = userService.adminLogin(username, password);
            if (user == null) {
                System.out.println("Tài khoản không hợp lệ!");
                isRetry = isRetry();
            } else if (user.getRole() == Role.ADMIN) {
                System.out.println("Đăng nhập thành công");
                System.out.println("Chào mừng Admin " + user.getFullName());
                idOnlineUser = user.getIdUser();
                MainLauncher.menuOption();
            } else if (user.getRole() == Role.USER) {
                System.out.println("Đăng nhập thành công");
                System.out.println("Chào mừng" + user.getFullName());
                idOnlineUser = user.getIdUser();
                MenuUserView.runOderUser();
            }
        } while (isRetry);
    }

    public static boolean isRetry() {
        do {
            try {
                System.out.println("Nhấn 'y' để đăng nhập lại! || Nhấn 't' để thoát chương trình");
                String option = sc.nextLine().toLowerCase();
                switch (option) {
                    case "y":
                        return true;
                    case "t":
                        AppUtils.exit();
                    default:
                        System.out.println("Nhập chức năng không đúng! Vui lòng nhập lại!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập sai! Vui lòng nhập lại!");
                e.printStackTrace();
            }
        } while (true);
    }

}
