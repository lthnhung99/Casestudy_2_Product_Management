package view.Product;

import view.Product.ProductView;
import view.Product.SearchProductView;
import view.Product.SortProductView;
import view.SelectFunction;

import java.util.Scanner;

import static view.MainLauncher.menuOption;

public class MenuProductView {
    public static Scanner sc = new Scanner(System.in);

    public static void inputUpdate() {

        System.out.println("CẬP NHẬT");

        System.out.println("1. Cập nhật tên");
        System.out.println("2. Cập nhật giá");
        System.out.println("3. Cập nhật số lượng");
        System.out.println("0. Quay lại");

        System.out.println("Chọn chức năng");

    }
    public static void menuProduct() {

        System.out.println("[QUẢN LÝ SẢN PHẨM");

        System.out.println("1. Thêm sản phẩm");
        System.out.println("2. Sửa thông tin sản phẩm");
        System.out.println("3. Tìm sản phẩm");
        System.out.println("4. Hiển thị danh sách sản phẩm");
        System.out.println("5. Sắp xếp sản phẩm");
        System.out.println("6. Xóa sản phẩm");
        System.out.println("7. Quay lại menu");
        System.out.println("8. Thoát");
        System.out.println("Chọn chức năng");
    }

    public static void runProduct(){
        int choice;
        boolean flag = true;
        try {
            do {
                ProductView productView = new ProductView();
                menuProduct();
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        productView.addProduct();
                        runProduct();
                        break;
                    case 2:
                        productView.updateProduct();
                        runProduct();
                        break;
                    case 3:
                        SearchProductView.search();
                        break;
                    case 4:
                        productView.showProduct(SelectFunction.SHOW);
                        runProduct();
                        break;
                    case 5:
                        SortProductView.choice();
                        break;
                    case 6:
                        productView.removeProduct();
                        break;
                    case 7:
                        menuOption();
                        break;
                    case 8:
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Nhập sai! Vui lòng nhập lại!");
                }
            }while (!flag);
        }catch (Exception e) {
            System.err.println("Nhập sai! Vui lòng nhập lại");
            runProduct();
        }
    }
}

