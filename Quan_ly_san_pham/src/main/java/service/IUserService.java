package service;

import model.User;


public interface IUserService extends InterfaceService<User> {
    User adminLogin(String userName, String passWord);

    public String findNameById(long id);

    public boolean existByEmail(String email);

    public boolean existByPhone(String phone);

    public boolean existByUserName(String username);

}
