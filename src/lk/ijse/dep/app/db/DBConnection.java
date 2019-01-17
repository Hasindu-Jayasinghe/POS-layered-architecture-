package lk.ijse.dep.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null){
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC", "root", "mysql");
        }
        return connection;
    }

}
