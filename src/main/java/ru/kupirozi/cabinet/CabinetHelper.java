package ru.kupirozi.cabinet;

import ru.kupirozi.db.AccountHandler;
import ru.kupirozi.db.StorageManager;
import ru.kupirozi.utils.CryptUtils;
import ru.kupirozi.utils.TextUtils;

import javax.xml.soap.Text;

/**
 * Created by fedorov on 28.02.2018.
 */
public class CabinetHelper {

    private StorageManager storage;

    public CabinetHelper(final StorageManager storage) {
        this.storage = storage;
    }

    public Profile authorize(final String login, final String password) {
        Profile profile = null;
        try {
            if (TextUtils.isBlank(login) || TextUtils.isBlank(password)) {
                // validation
            } else {
                profile = (Profile) storage.execute(new AccountHandler(login));
                if (profile != null && profile.isPasswordValid(password.getBytes("UTF-8"))) {
                    return profile;
                } else {
                    return null;
                }
            }
        } catch (Exception e) {

        }
        return  profile;
    }

    public boolean isAuthorized(final String token) {
        if (TextUtils.isBlank(token)) {
            return false;
        }
        try {
            CryptUtils cryptUtils = new CryptUtils();
            String authToken = cryptUtils.decrypt(token);
            String[] params = authToken.split("O_o");
            if (params.length == 3) {
                String login = params[0];
                String password = params[1];
                Integer timestamp = Integer.getInteger(params[2]);
                Profile profile = (Profile)storage.execute(new AccountHandler(login));
                return profile != null && profile.isPasswordValid(password.getBytes("UTF-8"));
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
