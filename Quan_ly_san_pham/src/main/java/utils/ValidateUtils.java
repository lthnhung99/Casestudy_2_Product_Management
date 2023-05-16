package utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class ValidateUtils {
    public static final String USERNAME_PATTERN = "^(?=.{5,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&–_[{}]:;',?/*~$^+=<>\\.]).{8,20}$";
    public static final String FULLNAME_PATTERN = "^([A-ZÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬĐÈẺẼÉẸÊỀỂỄẾỆÌỈĨÍỊÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢÙỦŨÚỤƯỪỬỮỨỰỲỶỸÝỴ][a-zàảãáạăằẳẵắặâầẩẫấậđèẻẽéẹêềểễếệiìỉĩíịòỏõóọôồổỗốộơờởỡớợùủũúụỤưừửữứựỳỷỹýỵ]{1,6} )*[ ]*[A-ZÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬĐÈẺẼÉẸÊỀỂỄẾỆÌỈĨÍỊÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢÙỦŨÚỤƯỪỬỮỨỰỲỶỸÝỴ][a-zàảãáạăằẳẵắặâầẩẫấậđèẻẽéẹêềểễếệiìỉĩíịòỏõóọôồổỗốộơờởỡớợùủũúụỤưừửữứựỳỷỹýỵ]{0,6}$";
    public static final String EMAIL_PATTERN = "^[A-Za-z0-9._]+@[a-z]{2,10}+\\.[a-z]{2,3}$";
    public static final String PHONE_PATTERN = "^0[1-9][0-9]{8}$";
    public static final String Pattern_pattern ="\\p{InCombiningDiacriticalMarks}+";
    private static final String DAY_PATTERN = "^(0?[1-9]|[12][0-9]|3[01])[\\-](0?[1-9]|1[012])[\\-]\\d{4}$";
    private static final String MONTH_PATTERN = "^(0?[1-9]|1[012])[\\-]\\d{4}$";
    private static final String YEAR_PATTERN = "\\d{4}$";
    public static boolean isUserNameValid(String userName) {
        return Pattern.compile(USERNAME_PATTERN).matcher(userName).matches();
    }
    public static boolean isPasswordValid(String password) {
        return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }
    public static boolean isFullNameValid(String fullName) {
        return Pattern.compile(FULLNAME_PATTERN).matcher(fullName).matches();
    }
    public static boolean isEmailValid(String email) {
        return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }
    public static boolean isPhoneValid(String phone) {
        return Pattern.compile(PHONE_PATTERN).matcher(phone).matches();
    }
    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        return Pattern.compile(Pattern_pattern).matcher(temp).replaceAll("");
    }
    public static boolean isDayValid(String day) {
        return Pattern.compile(DAY_PATTERN).matcher(day).matches();
    }
    public static boolean isMonthValid(String month) {
        return Pattern.compile(MONTH_PATTERN).matcher(month).matches();
    }

    public static boolean isYearValid(String year) {
        return Pattern.compile(YEAR_PATTERN).matcher(year).matches();
    }


}
