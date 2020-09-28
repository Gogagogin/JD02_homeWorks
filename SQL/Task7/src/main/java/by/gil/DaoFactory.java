package by.gil;

import by.gil.mysql.DaoImpl;

import java.security.InvalidParameterException;
import java.sql.SQLException;

public class DaoFactory {

    private static DaoImpl daoImpl;

    public static Dao getDao(String database) throws SQLException {
        if ("hometaskdatabase".equals(database)) {
            if (daoImpl == null) {
                daoImpl = new DaoImpl();
            }
            return daoImpl;
        } else if ("hometaskdatabase_test".equals(database)) {
            if (daoImpl == null) {
                daoImpl = new DaoImpl(true);
            }
            return daoImpl;
        }
throw new InvalidParameterException("NO such database " + database);
    }
}