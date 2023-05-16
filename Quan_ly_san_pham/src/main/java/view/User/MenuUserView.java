package view.User;

import view.AdminView;
import view.Order.OrderView;
import view.Order.SearchOrderView;
import view.Product.ProductView;
import view.SelectFunction;

import java.util.Scanner;

import static view.User.UserViewLauncher.adminView;

public class MenuUserView {

    private static Scanner sc = new Scanner(System.in);
    static ProductView productView = new ProductView();

    public static void menuOderUser() {

        System.out.println("MAIN MENU");
        System.out.println("1. Tạo đơn hàng");
        System.out.println("2. Chỉnh sửa đơn hàng");
        System.out.println("3. Xem đơn hàng");
        System.out.println(" 4. Tìm kiếm đơn hàng");
        System.out.println("0. Đăng xuất");
        System.out.println("Chọn chức năng:");
    }
    public static void menuUser() {
        System.out.println("QUẢN LÝ NHÂN VIÊN");
        System.out.println("1. Thêm nhân viên");
        System.out.println("2. Sửa thông tin nhân viên");
        System.out.println("3. Xóa nhân viên");
        System.out.println("4. Hiện thông tin nhân viên");
        System.out.println("5. Quay lại MAIN MENU");
        System.out.println("6. Thoát");

    }
    public static void login() {
        System.out.println("1.Đăng nhập");
        System.out.println("0.Thoát");
        System.out.println("Chọn chức năng (chọn số) :");
        try {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    adminView.adminLogin();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nhập sai !!! Vui lòng nhập lại !!!");
            }
        }catch (Exception e){
            System.out.println("Nhập sai cú pháp . Vui lòng đăng nhập lại (chỉ chọn các số có ở menu)");
            login();
        }
    }
    public static void runOderUser() {
        do {
            menuOderUser();
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    OrderView orderView = new OrderView();
                    orderView.addOrder(AdminView.idOnlineUser);
                    break;
                case 2:
                    OrderView orderView1 = new OrderView();
                    orderView1.updateOrder(AdminView.idOnlineUser);
                    break;
                case 3:
                    OrderView orderView2 = new OrderView();
                    orderView2.showOrdersOfEmployee(AdminView.idOnlineUser , SelectFunction.SHOW);
                    break;
                case 4 :
                    SearchOrderView searchOrderView = new SearchOrderView();
                    searchOrderView.search(AdminView.idOnlineUser);
                case 0:
                    login();
                    break;
                default:
                    System.out.println("Nhập chức năng sai! Vui lòng nhập lại !!!");
            }
        }while (true);
    }
}
