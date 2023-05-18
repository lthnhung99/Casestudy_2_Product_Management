package view.Product;

import model.Product;
import service.ProductService;
import utils.AppUtils;
import utils.InstantUtils;
import view.ESelect;

import java.text.DecimalFormat;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class ProductView {

    private static Scanner sc = new Scanner(System.in);
    private final DecimalFormat decimalFormat = new DecimalFormat("###,###,###" + " đ");
    private ProductService productService;

    public ProductView(){
        productService = ProductService.getProductService();
    }

    public void addProduct(){
        do {
            long idProduct =System.currentTimeMillis()%100000;
            String name = inputNameProduct(ESelect.ADD);
            double price = inputPrice(ESelect.ADD);
            int quantity = inputQuantity(ESelect.ADD);
            Instant createAt = Instant.now();
            Instant updateAt = Instant.now();
            Product product = new Product(idProduct ,name ,price ,quantity ,createAt , updateAt);
            productService.add(product);
            System.out.println("Đã thêm sản phẩm thành công!");
            showProduct(ESelect.ADD);
        }while (AppUtils.isRetry(ESelect.ADD));
    }

    public void removeProduct(){
        showProduct(ESelect.SHOW);
        System.out.println("Nhập ID muốn xóa:");
        long id = Long.parseLong(sc.nextLine());
        productService.removeById(id);
        System.out.println("Đã xóa sp.");
        showProduct(ESelect.REMOVE);
    }

    public void updateProduct(){
        show(productService.findAll());
        System.out.println("Nhập ID cần sửa :");
        try {
            int id = Integer.parseInt(sc.nextLine());
            if (productService.existsById(id)) {
                MenuProductView.inputUpdate();
                boolean isFlag = true;
                do {
                    try {
                        int choice = Integer.parseInt(sc.nextLine());
                        switch (choice) {
                            case 1:
                                inputName(id);
                                break;
                            case 2:
                                inputPrice(id);
                                break;
                            case 3:
                                inputQuantity(id);
                                break;
                            case 5:
                                MenuProductView.menuProduct();
                                MenuProductView.runProduct();
                                break;
                            default:
                                System.out.println("Chưa hợp lệ! Vui lòng nhập lại !");
                                break;
                        }
                    } catch (Exception e) {
                        updateProduct();
                    }
                } while (!isFlag);
                boolean flag = true;
                do {
                    System.out.print("Nhấn 'c' để tiếp tục cập nhật || Nhấn 'b' để quay lại || Nhấn 'e' để thoát \n");
                    System.out.print("➠");
                    String choice = sc.nextLine();
                    switch (choice) {
                        case "c":
                            updateProduct();
                            break;
                        case "b":
                            MenuProductView.menuProduct();
                            MenuProductView.runProduct();
                            break;
                        case "e":
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Nhập sai! Vui lòng nhập lại !");
                            break;
                    }
                } while (!flag);
            } else {
                System.out.println("Mời nhập lại!");
                updateProduct();
            }
        } catch (Exception e) {
            System.out.println("Nhập không chính xác");
            updateProduct();
        }
    }


    private int inputQuantity(ESelect choose) {
        switch (choose){
            case ADD :
                System.out.println("Nhập số lượng sản phẩm :");
                break;
            case UPDATE:
                System.out.println("Nhập số lượng bạn muốn sửa:");
                break;
        }
        int quantity;
        do {
            quantity = AppUtils.retryParseInt();
            if(quantity <= 0){
                System.out.println("Nhập số lượng lớn hơn 0.");
            }
        }while (quantity < 0);
        return quantity;
    }
    private void inputPrice (int id){
        Product product = productService.findById(id);
        System.out.println("Nhập giá:");
        double price = Double.parseDouble(sc.nextLine());
        product.setPrice(price);
        productService.update(product);
        showProduct(ESelect.UPDATE);
        System.out.println("Giá đã được thay đổi");
    }
    private void inputQuantity (int id){
        Product product = productService.findById(id);
        System.out.println("Nhập số lượng:");
        int quantity = Integer.parseInt(sc.nextLine());
        product.setQuantity(quantity);
        productService.update(product);
        showProduct(ESelect.UPDATE);
        System.out.println("Số lượng đã được thay đổi");
    }
    private void inputName (int id){
        Product product = productService.findById(id);
        System.out.println("Nhập tên:");
        String nameProduct = sc.nextLine();
        product.setNameProduct(nameProduct);
        productService.update(product);
        showProduct(ESelect.UPDATE);
        System.out.println("Tên sản phẩm đã được thay đổi");
    }


    private double inputPrice(ESelect choose) {
        switch (choose){
            case ADD:
                System.out.println("Nhập giá sản phẩm : ");
                break;
            case UPDATE:
                System.out.println("Nhập giá bạn muốn sửa: ");
                break;
        }
        double price;
        do {
            price = AppUtils.retryParseDouble();
            if (price <=0){
                System.out.println("Nhập giá lớn hơn 0.");
            }
        }while (price < 0);
        return price;
    }

    private String inputNameProduct(ESelect choose) {
        String nameProduct = "";
        switch (choose) {
            case ADD:
                System.out.println("Nhập tên sản phẩm:");
                break;
            case UPDATE:
                System.out.println("Nhập tên sản phẩm bạn muốn sửa:");
                break;
        }
        do {
            nameProduct = AppUtils.retryString("Tên sản phẩm");
        }while (nameProduct.isEmpty());
        return nameProduct;
    }

    public void showProduct(ESelect choose) {
        List<Product> productList = productService.findAll();
        System.out.println("DANH SÁCH SẢN PHẨM");
        System.out.printf("%-25s %-25s %-15s %-20s\n", "ID", "Tên ", "Giá", "Số lượng");
        for (Product product : productList) {
            System.out.printf("%-25s %-25s %-15s %-20s\n",
                    product.getIdProduct(),
                    product.getNameProduct(),
                    decimalFormat.format(product.getPrice()),
                    product.getQuantity());
        }
    }

    public void showProduct1(List<Product>products, ESelect choose) {
//        List<Product> productList = productService.findAll();
        System.out.println("DANH SÁCH SẢN PHẨM");
        System.out.printf("%-25s %-25s %-15s %-20s %-20s %-20s\n", "ID", "Tên ", "Giá", "Số lượng","Thời gian " ,"Thời gian cập nhật");
        for (Product product : products) {
            System.out.printf("%-25s %-25s %-15s %-20s %-20s %-20s\n",
                    product.getIdProduct(),
                    product.getNameProduct(),
                    decimalFormat.format(product.getPrice()),
                    product.getQuantity(),
                    InstantUtils.instantToString(product.getCreateAt()),
                    product.getUpdateAt() == null ? "" : InstantUtils.instantToString(product.getUpdateAt())
            );
        }
        if (choose != ESelect.UPDATE && choose != ESelect.REMOVE && choose != ESelect.SEARCH) {
        }
    }

    public void show(List<Product> products) {
        System.out.println("Danh sách sản phẩm");
        System.out.printf("%-25s %-25s %-15s %-20s %-20s %-20s\n", "ID", "Tên ", "Giá", "Số lượng" ,"Thời gian " ,"Thời gian cập nhật");
        for (Product product : products) {
            System.out.printf("%-25s %-25s %-15s %-20s %-20s %-20s\n",
                    product.getIdProduct(),
                    product.getNameProduct(),
                    decimalFormat.format(product.getPrice()),
                    product.getQuantity(),
                    InstantUtils.instantToString(product.getCreateAt()),
                    product.getUpdateAt() == null ? "" : InstantUtils.instantToString(product.getUpdateAt())
            );
        }
    }
}
