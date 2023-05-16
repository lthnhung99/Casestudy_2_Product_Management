package view.User;

import view.AdminView;
import view.MainLauncher;
import view.SelectFunction;
import view.User.MenuUserView;
import view.User.UserView;

import java.util.Scanner;

import static view.User.MenuUserView.login;

public class UserViewLauncher {
    static Scanner scanner = new Scanner(System.in);
    static UserView userView = new UserView();
    static AdminView adminView = new AdminView();

    public static void launch() {
        int choice = -1;
        do {
            MenuUserView.menuUser();
            try {
                do {
                    System.out.println("Chọn chức năng");
                    System.out.print("➠ ");
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice > 6 || choice < 1)
                        System.out.println("Chọn chức năng không đúng !!!");
                } while (choice > 6 || choice < 1);
                switch (choice) {
                    case 1:
                        userView.addUser();
                        break;
                    case 2:
                        userView.updateUser();
                        break;
                    case 3:
                        userView.removeUser();
                        break;
                    case 4:
                        userView.showUsers(SelectFunction.SHOW);
                        break;
                    case 5:
                        MainLauncher.menuOption();
                        break;
                    case 6:
                        login();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng !!!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập sai !!!");
            }
        } while (choice != 6);
    }

}
