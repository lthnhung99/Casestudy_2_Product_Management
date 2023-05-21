package view.Product;

import model.Product;
import service.ProductService;
import utils.ValidateUtils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class SearchProductView {
    public static Scanner sc = new Scanner(System.in);
    public static ProductView productView = new ProductView();
    public static ProductService productService = new ProductService();
    static DecimalFormat decimalFormat = new DecimalFormat("###,###,###" + "đ");
    public List<Product> products;

    public SearchProductView(){
        products = productService.findAll();
    }

    public static void search() {

        productView.show(productService.findAll());
        boolean flag = true;
        int choice = -1;
        do {
            System.out.println("TÌM KIẾM SẢN PHẨM ");
            System.out.println("1. Tìm kiếm theo tên sản phẩm");
            System.out.println("0. Quay lại");
            System.out.println("Chọn chức năng: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            }catch (Exception e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 1:
                    searchByName();
                    break;
                case 0:
                    MenuProductView.runProduct();
                    flag = false;
                    break;
                default:
                    System.out.println("Nhập sai !!! Vui lòng nhập lại !!!");
                    break;
            }
        }while (flag);
    }

    public static void searchByName() {
        List<Product> products = productService.findAll();
        int count = 0;
        System.out.println("Nhập tên cần tìm kiếm ");
        try {
            String name = sc.nextLine();
            System.out.printf("%-20s %-20s %-18s %-10s\n", "Id", "Tên sản phẩm", "Giá", "Số lượng");
            for (Product product:products) {
                if(name.equalsIgnoreCase(product.getNameProduct())) {
                    count++;
                    System.out.printf("%-20s %-20s %-18s %-10s\n", product.getIdProduct(), product.getNameProduct(), decimalFormat.format(product.getPrice()),
                            product.getQuantity());
                }

            }
            showReturnSearch(count);

        } catch (Exception e) {
            System.out.println();
        }
    }




    public static void showReturnSearch(int count) {
        char choice = ' ';
        boolean flag;
        System.out.println();
        do {
            System.out.println("Nhấn 'q' để quay lại.");
            try {
                choice = sc.nextLine().charAt(0);
            } catch (Exception e) {
                choice = ' ';
            }
            switch (choice) {
                case 'q':
                    SearchProductView.search();
                    flag = false;
                    break;
                default:
                    flag = true;
            }
        } while (flag);
    }
}
