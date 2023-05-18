package service;

import model.ERole;
import model.User;
import utils.FileUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {

    private static final String PATH = "D:\\Casestudy_2_Product_Management\\Quan_ly_san_pham\\src\\main\\java\\data\\user.csv";
    private static UserService userService;

    public UserService() {
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        List<String> records = FileUtils.readFile(PATH);
        for (String record : records) {
            users.add(User.parseUser(record));
        }
        return users;
    }

    @Override
    public void add(User newUser) {
        List<User> users = findAll();
        users.add(newUser);
        newUser.setCreateAt(Instant.now());
        FileUtils.writeFile(PATH, users);
    }

    @Override
    public void removeById(long id) {
        List<User> users = findAll();
        users.removeIf(user -> user.getIdUser() == id);
        FileUtils.writeFile(PATH, users);
    }

    @Override
    public void update(User newUser) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getIdUser() == newUser.getIdUser()) {
                String fullName = newUser.getFullName();
                if (fullName != null && !fullName.isEmpty())
                    user.setFullName(fullName);
                String phone = newUser.getPhone();
                if (phone != null && !phone.isEmpty())
                    user.setPhone(newUser.getPhone());
                String address = newUser.getAddress();
                if (address != null && !address.isEmpty())
                    user.setAddress(newUser.getAddress());
                String email = newUser.getEmail();
                if (email!=null&&!email.isEmpty())
                    user.setEmail(newUser.getEmail());
                user.setUpdateAt(Instant.now());
                FileUtils.writeFile(PATH, users);
                break;
            }
        }
    }

    @Override
    public boolean existsById(long id) {
        return findById(id) != null;
    }

    @Override
    public User findById(long id) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getIdUser() == id)
                return user;
        }
        return null;
    }

    @Override
    public User adminLogin(String userName, String passWord) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassWord().equals(passWord)) {
                return user;
            }
            if (user.getUserName().equals(userName) && user.getPassWord().equals(passWord)
                    && user.getRole().equals(ERole.ADMIN)) {
                return user;
            }
            if (user.getUserName().equals(userName) && user.getPassWord().equals(passWord)
                    && user.getRole().equals(ERole.USER)) {
                return user;
            }
        }
        return null;
    }

    public String findNameById(long id) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getIdUser() == id) {
                return user.getFullName();
            }
        }
        return null;
    }

    public boolean existByEmail(String email) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    public boolean existByPhone(String phone) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getPhone().equals(phone))
                return true;
        }
        return false;
    }

    public boolean existByUserName(String username) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getUserName().equals(username))
                return true;
        }
        return false;
    }
}
