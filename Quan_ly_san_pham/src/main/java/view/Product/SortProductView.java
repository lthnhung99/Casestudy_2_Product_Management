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

        System.out.println("1. Sắp xếp theo tên sản phẩm");
        System.out.println("2. Sắp xếp theo giá sản phẩm");
        System.out.println("3. Sắp xếp theo số lượng sản phẩm");
        System.out.println("0. Quay lại");
        System.out.println("Chọn chức năng sắp xếp: ");
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

    public static void showSortByQuantity() {
        boolean flag = true;
        int choice;
        do {

            System.out.println("SẮP XẾP THEO SỐ LƯỢNG SẢN PHẨM");
            System.out.println("1. Theo thứ tự từ từ tăng dần");
            System.out.println("2. Theo thứ tự từ từ giảm dần");
            System.out.println("0. Quay lại");
            System.out.println("Chọn chức năng: ");
            try {

                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        List<Product> productList = productService.findAll();
                        System.out.println("Sắp xếp tăng dần");
                        SortByQuantityAsc sortByQuantityAsc = new SortByQuantityAsc();
                        productList.sort(sortByQuantityAsc);
                        productView.show(productList);
                        choice();
                        break;
                    case 2:
                        List<Product> productsList = productService.findAll();
                        System.out.println("Sắp xếp theo số lượng giảm dần");
                        SortByQuantityDesc sortByQuantityDesc = new SortByQuantityDesc();
                        productsList.sort(sortByQuantityDesc);
                        productView.show(productsList);
                        choice();
                        break;
                    case 0:
                        MenuProductView.runProduct();
                        break;
                    default:
                        System.out.println("Chọn lại !");
                        flag = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (!flag);
    }

    public static void showSortByPrice() {
        boolean flag = true;
        int choice;
        do {

            System.out.println("SẮP XẾP THEO GIÁ SẢN PHẨM");

            System.out.println("1. Theo thứ tự từ tăng dần");
            System.out.println("2. Theo thứ tự từ giảm dần");
            System.out.println("0. Quay lại");

            System.out.println("Chọn chức năng: ");
            System.out.print("➽ ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        List<Product> productList = productService.findAll();
                        System.out.println("Sắp xếp tăng dần");
                        SortByPriceAsc sortByPriceAsc = new SortByPriceAsc();
                        productList.sort(sortByPriceAsc);
                        productView.show(productList);
                        choice();
                        break;
                    case 2:
                        List<Product> productsList = productService.findAll();
                        System.out.println("Sắp xếp giảm dần");
                        SortByPriceDesc sortByPriceDesc = new SortByPriceDesc();
                        productsList.sort(sortByPriceDesc);
                        productView.show(productsList);
                        choice();
                        break;
                    case 0:
                        MenuProductView.runProduct();
                        break;
                    default:
                        System.out.println("Chọn lại!");
                        flag = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (!flag);
    }


    public static void showSortByName() {
        boolean flag = true;
        int choice;
        do {

            System.out.println("SẮP XẾP THEO TÊN SẢN PHẨM");

            System.out.println("1. Theo thứ tự từ (A-Z)");
            System.out.println("2. Theo thứ tự từ (Z-A)");
            System.out.println("0. Quay lại");

            System.out.println("Chọn chức năng: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        List<Product> productList = productService.findAll();
                        System.out.println("Sắp xếp tăng dần ");
                        SortByNameAsc sortByNameAsc = new SortByNameAsc();
                        productList.sort(sortByNameAsc);
                        productView.show(productList);
                        choice();
                        break;
                    case 2:
                        List<Product> productList1 = productService.findAll();
                        System.out.println("Sắp xếp giảm dần ");
                        SortByNameDesc sortByNameDesc = new SortByNameDesc();
                        productList1.sort(sortByNameDesc);
                        productView.show(productList1);
                        choice();
                        break;
                    case 0:
                        MenuProductView.runProduct();
                        break;
                    default:
                        System.out.println("Nhập sai ! Vui lòng chọn lại !!!");
                        flag = false;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }while (!flag);
    }
}
