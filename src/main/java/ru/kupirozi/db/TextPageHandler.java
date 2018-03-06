package ru.kupirozi.db;

import ru.kupirozi.site.TextPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by fedorov on 16.02.2018.
 */
public class TextPageHandler implements  StorageHandler {

    private String uri;

    public TextPageHandler(final String uri) {
        this.uri = uri;
    }

    @Override
    public Object handle(Connection connection, PreparedStatement pst, ResultSet rs) throws SQLException {

        pst = connection.prepareStatement("SELECT id,text,title,is_category FROM pages where uri=? limit 1;");
        pst.setString(1, uri);

        rs = pst.executeQuery();

        TextPage textPage = new TextPage();

        while (rs.next()) {
            textPage.setId(rs.getInt("id"));
            textPage.setText(rs.getString("text"));
            textPage.setTitle(rs.getString("title"));
            textPage.setIsCategory(rs.getInt("is_category") == 1);
        }

        return textPage;
    }
}
