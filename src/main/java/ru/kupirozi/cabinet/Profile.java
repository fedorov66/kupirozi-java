package ru.kupirozi.cabinet;

import java.util.Arrays;

/**
 * Created by fedorov on 28.02.2018.
 */
public class Profile {

    private int id;
    private String login;
    private String password;
    private int createDate;
    private int updateDate;
    private final Account account = new Account();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCreateDate() {
        return createDate;
    }

    public void setCreateDate(int createDate) {
        this.createDate = createDate;
    }

    public int getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(int updateDate) {
        this.updateDate = updateDate;
    }

    public Account getAccount() {
        return account;
    }

    public boolean isPasswordValid(final char[] checkPassword) throws Exception {
        return isPasswordValid(checkPassword);
    }

    public boolean isPasswordValid(final byte[] checkPassword) throws Exception {
        return Arrays.equals(password.getBytes("UTF-8"), checkPassword);
    }

}
