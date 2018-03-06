package ru.kupirozi.db;

import ru.kupirozi.cabinet.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by fedorov on 01.03.2018.
 */
public class AccountHandler implements StorageHandler {

    private String login;

    public AccountHandler(final String login) {
        this.login = login;
    }

    @Override
    public Object handle(Connection connection, PreparedStatement pst, ResultSet rs) throws SQLException {

        pst = connection.prepareStatement("SELECT id, password FROM accounts WHERE login=? limit 1");
        pst.setString(1, login);

        rs = pst.executeQuery();

        Profile profile = null;

        while (rs.next()) {
            profile = new Profile();
            profile.setId(rs.getInt("id"));
            profile.setLogin(login);
            profile.setPassword(rs.getString("password"));
        }

        return profile;
    }

}
