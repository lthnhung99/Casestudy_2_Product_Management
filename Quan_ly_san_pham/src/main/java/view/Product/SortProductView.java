package view.Product;

import model.Product;
import service.ProductService;
import sort.*;

import java.util.List;
import java.util.Scanner;

public class SortProductView {
    private static Scanner sc = new Scanner(System.in);

    public static ProductService productService = new ProductService();
    public static ProductView productView = new ProductView();
    static List<Product> products;

    public SortProductView(){
        products= productService.findAll();
    }

    public static void sortMenu() {
        System.out.println("                                            ╔═════════════════════════════════════════════════════╗");
        System.out.println("                                            ║ Options:                                            ║");
        System.out.println("                                            ║ ▶ 1.Sắp xếp theo tên sản phẩm                       ║");
        System.out.println("                                            ║ ▶ 2.Sắp xếp theo giá sản phẩm                       ║");
        System.out.println("                                            ║ ▶ 3.Sắp xếp theo số lượng sản phẩm                  ║");
        System.out.println("                                            ║ ▶ 0.Quay lại                                        ║");
        System.out.println("                                            ║ ▶ Chọn chức năng                                    ║");
        System.out.println("                                            ╚═════════════════════════════════════════════════════╝");
    }
    public static void choice() {
        boolean is = true;
        int option;
        do {
            sortMenu();
            option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 1:
                    showSortByName();
                    break;
                case 2:
                    showSortByPrice();
                    break;
                case 3:
                    showSortByQuantity();
                    break;
                case 0:
                    MenuProductView.runProduct();
                    break;
                default:
                    System.out.println("Nhập sai !!! Vui lòng nhập lại !!!");
            }
        }while (!is);
    }

    private static void showSortByQuantity() {
        List<Product> productList = productService.findAll();
        System.out.println("Sắp xếp tăng dần");
        SortByQuantity sortByQuantityAsc = new SortByQuantity();
        productList.sort(sortByQuantityAsc);
        productView.show(productList);
        choice();
    }

    private static void showSortByPrice() {
        List<Product> productList = productService.findAll();
        System.out.println("Sắp xếp tăng dần");
        SortByPrice sortByPriceAsc = new SortByPrice();
        productList.sort(sortByPriceAsc);
        productView.show(productList);
        choice();
    }

    private static void showSortByName() {
        List<Product> productList = productService.findAll();
        System.out.println("Sắp xếp tăng dần ");
        SortByName sortByNameAsc = new SortByName();
        productList.sort(sortByNameAsc);
        productView.show(productList);
        choice();
    }

}
