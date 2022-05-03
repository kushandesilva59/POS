package dao;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {
    private static PreparedStatement getPreparedStatement(String sql,Object...values) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        for (int i = 0; i < values.length; i++) {
            pstm.setObject((i+1),values[i]);
        }
        return pstm;
    }

    public static boolean executeUpdate(String sql,Object...values) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql,values).executeUpdate()>0;
    }

    public static ResultSet executeQuery(String sql,Object...values) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql,values).executeQuery();
    }
}
