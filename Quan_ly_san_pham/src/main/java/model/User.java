package model;

import java.time.Instant;

public class User {
    private long idUser;
    private String userName;
    private String passWord;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private ERole role;
    private Instant createAt;
    private Instant updateAt;

    public User() {

    }

    public User(long idUser, String userName, String passWord, String fullName, String phone, String email, String address, ERole role, Instant createAt, Instant updateAt) {
        this.idUser = idUser;
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public User(long idUser, String username, String password, String fullName, String phone, String email, String address, ERole role) {
        this.idUser = idUser;
        this.userName = username;
        this.passWord = password;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
    }


    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s,", idUser, userName, passWord, fullName, phone, email, address, role, createAt, updateAt);
    }

    public static User parseUser(String line) {
        User user = new User();
        String[] item = line.split(",");
        user.idUser = Long.parseLong(item[0]);
        user.userName = item[1];
        user.passWord = item[2];
        user.fullName = item[3];
        user.phone = item[4];
        user.email = item[5];
        user.address = item[6];
        user.role = ERole.parseRole(item[7]);
        user.createAt = Instant.parse(item[8]);
        String temp = item[9];
        if (temp != null && !temp.equals("null"))
            user.updateAt = Instant.parse(temp);
        return user;
    }
}
