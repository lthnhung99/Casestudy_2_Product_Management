package view.User;

import model.ERole;
import model.User;
import service.UserService;
import utils.AppUtils;
import utils.InstantUtils;
import utils.ValidateUtils;
import view.MainLauncher;
import view.EFunction;

import java.util.List;
import java.util.Scanner;

public class UserView {
    public static Scanner sc = new Scanner(System.in);

    private UserService userService;

    public UserView(){
        userService= UserService.getUserService();
    }

    public void addUser(){
        do {
            try {
                long idUser = System.currentTimeMillis()%100000;
                String username = inputUserName();
                String password = inputPassword();
                String fullName = inputFullName(EFunction.ADD);
                String phone = inputPhone(EFunction.ADD);
                String address = inputAddress(EFunction.ADD);
                String email = inputEmail(EFunction.ADD);
                User user = new User(idUser, username, password, fullName, phone, email, address, ERole.USER);
                user.setRole(ERole.USER);
                userService.add(user);
                System.out.println("Đã thêm người dùng thành công!");
                showUsers(EFunction.SHOW);

            }catch (Exception e){
                System.err.println("Nhập sai. Vui lòng nhập lại!!!");
            }
        }while (AppUtils.isRetry(EFunction.ADD));
    }

    public void updateUser() {
        boolean isRetry = false;
        do {
            try {
                showUsers(EFunction.UPDATE);
                int id = inputId(EFunction.UPDATE);
                System.out.println("                                            ╔═════════════════════════════════════════════════════╗");
                System.out.println("                                            ║                SỬA THÔNG TIN NGƯỜI DÙNG             ║");
                System.out.println("                                            ╠═════════════════════════════════════════════════════╣");
                System.out.println("                                            ║ Options:                                            ║");
                System.out.println("                                            ║ ▶ 1.Sửa tên                                         ║");
                System.out.println("                                            ║ ▶ 2.Sửa số điện thoại                               ║");
                System.out.println("                                            ║ ▶ 3.Sửa địa chỉ                                     ║");
                System.out.println("                                            ║ ▶ 4.Sửa email                                       ║");
                System.out.println("                                            ║ ▶ 5.Quay lại                                        ║");
                System.out.println("                                            ╚═════════════════════════════════════════════════════╝");

                int option = AppUtils.retryChoose(1, 5);
                User newUser = new User();
                newUser.setIdUser(id);
                switch (option) {
                    case 1:
                        String name = inputFullName(EFunction.UPDATE);
                        newUser.setFullName(name);
                        userService.update(newUser);
                        System.out.println("Đã đổi tên thành công!");
                        break;
                    case 2:
                        String phone = inputPhone(EFunction.UPDATE);
                        newUser.setPhone(phone);
                        userService.update(newUser);
                        System.out.println("Đổi số điện thoại thành công!");
                        break;
                    case 3:
                        String address = inputAddress(EFunction.UPDATE);
                        newUser.setAddress(address);
                        userService.update(newUser);
                        System.out.println("Đổi địa chỉ thành công!");
                        break;
                    case 4:
                        String email = inputEmail(EFunction.UPDATE);
                        newUser.setEmail(email);
                        userService.update(newUser);
                        System.out.println("Đổi email thành công!");
                        break;
                }
                isRetry = option != 5 && AppUtils.isRetry(EFunction.UPDATE);
            } catch (Exception e) {
                System.err.println("Nhập sai !!! ");
            }
        } while (isRetry);
    }

    public void removeUser() {
        showUsers(EFunction.REMOVE);
        int id;
        while (!userService.existsById(id = inputId(EFunction.REMOVE))) {
            System.out.println("Không tồn tại người dùng !");
            System.out.println("Nhấn 'y' để tìm lại người dùng || Nhấn 'b' để quay lại || Nhấn 't' để thoát chương trình ");
            String option = sc.nextLine().toLowerCase();
            switch (option) {
                case "y":
                    removeUser();
                    break;
                case "b":
                    MenuUserView.menuUser();
                    return;
                case "t":
                    AppUtils.exit();
                    break;
                default:
                    System.out.println("Nhập chức năng không đúng! Vui lòng nhập lại!");
                    break;
            }
        }
        System.out.println("                                            ╔═════════════════════════════════════════════════════╗");
        System.out.println("                                            ║                  XÁC NHẬN ĐỂ XÓA                    ║");
        System.out.println("                                            ╠═════════════════════════════════════════════════════╣");
        System.out.println("                                            ║ Options:                                            ║");
        System.out.println("                                            ║ ▶ 1.Xóa                                             ║");
        System.out.println("                                            ║ ▶ 2.Quay lại                                        ║");
        System.out.println("                                            ╚═════════════════════════════════════════════════════╝");

        int option = AppUtils.retryChoose(1, 2);
        if (option == 1) {
            userService.removeById(id);
            System.out.println("Đã xóa user thành công!");
            showUsers(EFunction.REMOVE);
            AppUtils.isRetry(EFunction.REMOVE);
        } else if (option == 2) {
            MainLauncher.launch();
        }
    }
    private int inputId(EFunction choose) {
        int id;
        switch (choose) {
            case ADD:
                System.out.println("Nhập ID: ");
                break;
            case UPDATE:
                System.out.println("Nhập ID bạn muốn sửa: ");
                break;
            case REMOVE:
                System.out.println("Nhập ID muốn xóa: ");
                break;
        }
        boolean isRetry = false;
        do {
            id = AppUtils.retryParseInt();
            boolean exist = userService.existsById(id);
            switch (choose) {
                case ADD:
                    if (exist) {
                        System.out.println("ID đã tồn tại. Vui lòng nhập lại!");
                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist) {
                        System.out.println("Không tìm thấy ID. Vui lòng nhập lại!");
                    }
                    isRetry = !exist;
            }
        } while (isRetry);
        return id;
    }
    public String inputEmail(EFunction choose) {
        switch (choose) {
            case ADD:
                System.out.println("Nhập email");
                break;
            case UPDATE:
                System.out.println("Nhập email mà bạn muốn đổi: ");
                break;
        }
        String email;
        do {
            if (!ValidateUtils.isEmailValid(email = sc.nextLine())) {
                System.out.println("Email " + email + " không đúng định dạng Vui lòng nhập lại !" + "");
                System.out.println("Nhập email");
                continue;
            }
            if (userService.existByEmail(email)) {
                System.out.println("Email " + email + " của bạn đẫ tồn tại! Vui lòng nhập lại!");
                System.out.println("Nhập email");
                continue;
            }
            break;
        } while (true);
        return email;
    }

    public String inputAddress(EFunction choose) {
        switch (choose) {
            case ADD:
                String address;
                System.out.println("Nhập địa chỉ");
                do {
                    address = sc.nextLine();
                    if (address.trim().isEmpty()) {
                        System.out.println("Địa chỉ không được để trống!");
                        System.out.println("Nhập lại địa chỉ");
                        address = sc.nextLine();
                    }
                } while (address.trim().isEmpty());
                return address;
            case UPDATE:
                System.out.println("Nhập địa chỉ muốn đổi");
                do {
                    address = sc.nextLine();
                    if (address.trim().isEmpty()) {
                        System.out.println("Địa chỉ không được để trống!");
                        System.out.println("Nhập lại địa chỉ");
                        address = sc.nextLine();
                    }
                } while (address.trim().isEmpty());
                return address;
        }
        return null;
    }


    public String inputPhone(EFunction choose) {
        switch (choose) {
            case ADD:
                System.out.println("Nhập số điện thoại (10 số, bắt đầu số 0)");
                break;
            case UPDATE:
                System.out.println("Nhập số điện thoại mà bạn muốn đổi lại: ");
                break;
        }
        String phone;
        do {
            phone = sc.nextLine();
            if (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Số " + phone + " không đúng định dạng! Nhập lại!" + "Gồm 10 số và bắt đầu bằng số 0");
                System.out.println("Nhập số điện thoại");
                continue;
            }
            if (userService.existByPhone(phone)) {
                System.out.println("Số này đã tồn tại. Mời bạn nhập lại!");
                continue;
            }
            break;
        } while (true);
        return phone;
    }

    public String inputFullName(EFunction choose) {
        switch (choose) {
            case ADD:
                System.out.println("Nhập họ và tên");
                break;
            case UPDATE:
                System.out.println("Nhập tên mà bạn muốn sửa: ");
                break;
        }

        String fullName;
        while (!ValidateUtils.isFullNameValid(fullName = sc.nextLine())) {
            System.out.println("Tên " + fullName + " không đúng định dạng." + " Viết hoa chữ cái đầu, hơn 10 ký tự");
            System.out.println("Nhập tên");
        }
        return fullName;
    }

    public String inputPassword() {
        System.out.println("Nhập mật khẩu (Nhập số)");
        String password;
        while (!ValidateUtils.isPasswordValid(password = sc.nextLine())) {
            System.out.println("Mật khẩu không đúng định dạng. Nhập lại !!!");
        }
        return password;
    }

    public String inputUserName() {
        System.out.println("Nhập Username (không bao gồm dấu cách, kí tự đặc biệt)");
        String username;
        do {
            if (!ValidateUtils.isUserNameValid(username = AppUtils.retryString("Username"))) {
                System.out.println("Không đúng định dạng. Vui lòng nhập lại!");
                continue;
            }
            if (userService.existByUserName(username)) {
                System.out.println("Username này đã tồn tại. Vui lòng nhập lại!");
                continue;
            }
            break;
        } while (true);
        return username;
    }


    public void showUsers(EFunction choose) {
        System.out.println("DANH SÁCH NGƯỜI DÙNG");
        System.out.printf("%-15s %-22s %-15s %-22s %-18s %-15s %-20s %-20s\n", "Id", "Tên", "Số điện thoại", "Email", "Địa chỉ", "Người dùng", "Ngày tạo", "Ngày cập nhật");
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.printf("%-15d %-22s %-15s %-22s %-18s %-15s %-20s %-20s\n",
                    user.getIdUser(),
                    user.getFullName(),
                    user.getPhone(),
                    user.getEmail(),
                    user.getAddress(),
                    user.getRole(),
                    InstantUtils.instantToString(user.getCreateAt()),
                    user.getUpdateAt() == null ? "" : InstantUtils.instantToString(user.getUpdateAt())
            );
        }
        if (choose == EFunction.SHOW)
            AppUtils.isRetry(EFunction.SHOW);
    }


}
