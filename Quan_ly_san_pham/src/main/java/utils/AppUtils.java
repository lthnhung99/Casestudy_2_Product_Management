package utils;

import view.Select;

import java.text.DecimalFormat;
import java.util.Scanner;


public class AppUtils {
    public static Scanner sc = new Scanner(System.in);

    public static int retryParseInt() {
        int result;
        do {
            try {
                result = Integer.parseInt(sc.nextLine());
                return result;
            } catch (Exception ex) {
                System.out.println("Nhập sai! Vui lòng nhập lại!!!");
            }
        } while (true);
    }

    public static int retryChoose(int min, int max) {
        int option;
        do {
            try {
                option = Integer.parseInt(sc.nextLine());
                if (option > max || option < min) {
                    System.out.println("Chọn chức năng không đúng! Vui lòng nhập lại.!");
                    continue;
                }
                break;
            } catch (Exception ex) {
                System.out.println("Nhập sai! Vui lòng nhập lại!!! ");
            }
        } while (true);
        return option;
    }

    public static double retryParseDouble() {
        double result;
        do {
            try {
                result = Double.parseDouble(sc.nextLine());
                return result;
            } catch (Exception ex) {
                System.out.println("Nhập sai! Vui lòng nhập lại!!!");
            }
        } while (true);
    }

    public static String retryString(String fieldName) {
        String result;
        while ((result = sc.nextLine()).isEmpty()) {
            System.out.printf("%s không được để trống\n", fieldName);
        }
        return result;
    }

    public static boolean isRetry(Select choose) {
        do {
            switch (choose) {
                case ADD:
                    System.out.println("Nhấn 'y' để thêm tiếp || Nhấn 'q' để quay lại || Nhấn 't' để thoát chương trình");
                    break;
                case UPDATE:
                    System.out.println("Nhấn 'y' để sửa tiếp || Nhấn 'q' để quay lại || Nhấn 't' để thoát chương trình");
                    break;
                case REMOVE:
                    System.out.println("Nhấn 'q' để quay lại || Nhấn 't' để thoát chương trình");
                    break;
                case SHOW:
                    System.out.println("Nhấn 'q' để quay lại || Nhấn 't' để thoát chương trình!");
                    break;
                case PRINT:
                    System.out.println("Nhấn 'i' để in hoá đơn || Nhấn 'q' để quay lại || Nhấn 't' để thoát chương trình! ");
                default:
                    throw new IllegalStateException("Unexpected value " + choose);
            }
            String option = sc.nextLine().toLowerCase();
            switch (option) {
                case "y":
                    return true;
                case "q":
                    return false;
                case "t":
                    exit();
                    break;
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng nhập lại.");
            }
        } while (true);
    }

    public static void exit() {
        System.out.println("\t Cảm ơn quý khách. Hẹn gặp lại !");
        System.exit(0);
    }

    public static long retryParseLong() {
        long result;
        do {
            try {
                result = Long.parseLong(sc.nextLine());
                return result;
            } catch (Exception ex) {
                System.out.println("Nhập sai! vui lòng nhập lại");
            }
        } while (true);
    }


    public static String doubleToVND(double value) {
        String patternVND = ",### VNĐ";
        DecimalFormat decimalFormat = new DecimalFormat(patternVND);
        return decimalFormat.format(value);
    }

    public static void pressToContinue() {
        System.out.println("Ấn nút bất kỳ để tiếp tục. ");
        sc.nextLine();
    }

    public static void menuDelete() {
        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println("║               BẠN CÓ MUỐN XÓA KHÔNG                 ║");
        System.out.println("╠═════════════════════════════════════════════════════╣");
        System.out.println("║ Options:                                            ║");
        System.out.println("║ ▶ 1.Có                                              ║");
        System.out.println("║ ▶ 2.Không                                           ║");
        System.out.println("║ ▶ Nhập lựa chọn                                     ║");
        System.out.println("╚═════════════════════════════════════════════════════╝");

    }
}
