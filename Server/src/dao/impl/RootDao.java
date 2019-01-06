package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class RootDao {

    public PreparedStatement ppst;
    public ResultSet rs;

    public Connection getJDBCConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/gamecocaro", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
        }
        return null;

    }

}
