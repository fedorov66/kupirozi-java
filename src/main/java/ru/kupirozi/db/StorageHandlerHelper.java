package ru.kupirozi.db;

import org.eclipse.jetty.websocket.common.util.TextUtil;
import ru.kupirozi.catalogue.*;
import ru.kupirozi.utils.TextUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by fedorov on 01.02.2018.
 */
public class StorageHandlerHelper {

    public static Category populateCategory(final Category category, final ResultSet rs, final String paramPrefix) throws SQLException {
        String prefix = paramPrefix == null ? "" : paramPrefix;
        category.setId(rs.getInt(prefix + "id"));
        category.setName(rs.getString(prefix + "name"));
        category.setDescription(rs.getString(prefix + "description"));
        category.setActive(rs.getBoolean(prefix + "active"));
        category.setWeight(rs.getInt(prefix + "weight"));
        if (rs.getString(prefix + "picture") != null) {
            String[] images = rs.getString(prefix + "picture").split(";");
            for (String img : images) {
                if (!"".equals(img)) {
                    Image image = new Image();
                    image.setName(img);
                    category.getImages().add(image);
                }
            }
        }
        return category;
    }

    public static Item populateItem(final Item item, final ResultSet rs, final String paramPrefix) throws SQLException {
        String prefix = paramPrefix == null ? "" : paramPrefix;
        item.setId(rs.getInt(prefix + "id"));
        item.setCategoryId(rs.getInt("category_id"));
        item.setNameRu(rs.getString(prefix + "name_ru"));
        item.setNameEn(rs.getString(prefix + "name_en"));
        if (rs.getString(prefix + "images") != null) {
            String[] images = rs.getString(prefix + "images").split(";");
            for (String img : images) {
                if (!"".equals(img)) {
                    Image image = new Image();
                    image.setName(img);
                    item.getImages().add(image);
                }
            }
        }
        item.setNew(rs.getInt("is_new"));
        item.setIsNewText(rs.getString("is_new_text"));
        item.setPrice(rs.getBigDecimal("price"));
        item.setQuantity(rs.getInt("quantity"));
        item.setQuantityAvailable(rs.getInt("quantityAvailable"));
        return item;
    }


    public static ItemFull populateItemFull(final ItemFull item, final ResultSet rs, final String paramPrefix) throws SQLException {
        String prefix = paramPrefix == null ? "" : paramPrefix;
        populateItem(item, rs, paramPrefix);
        item.setDescription(!TextUtils.isBlank(rs.getString("description")) ? TextUtils.html2text(rs.getString("description")) : "");
        item.setAuthor(rs.getString("author"));
        item.setColor(rs.getString("color"));
        item.setHeight(rs.getString("height"));
        item.setWidth(rs.getString("width"));
        item.setSize(rs.getString("size"));
        item.setPetalsNum(rs.getString("petalsNum"));
        item.setStabMuchRosa(rs.getString("stabMuchRosa"));
        item.setStabBlankPyat(rs.getString("stabBlankPyat"));
        item.setBloom(rs.getString("bloom"));
        item.setAroma(rs.getString("aroma"));
        return item;
    }

    public static ru.kupirozi.catalogue.ShoppingCart populateShoppingCart(final ResultSet rs) throws SQLException {
        ru.kupirozi.catalogue.ShoppingCart result = new ru.kupirozi.catalogue.ShoppingCart();

        while(rs.next()) {
            ReservedItem item = new ReservedItem();
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name_ru"));
            item.setQuantity(rs.getInt("quantity"));
            item.setPrice(rs.getBigDecimal("price"));
            result.getItems().add(item);
        }

        return result;
    }

}