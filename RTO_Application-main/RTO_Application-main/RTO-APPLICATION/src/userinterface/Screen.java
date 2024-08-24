package userinterface;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface Screen {
    public void show(Screen currentScreen) throws NoSuchAlgorithmException, SQLException;
}
