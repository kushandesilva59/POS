package dao;

import db.DBConnection;
import model.ItemDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDAOImpl {
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");

        ArrayList <ItemDTO> items = new ArrayList<>();
        while(rst.next()){
            items.add(new ItemDTO(rst.getString(1),rst.getString(2), BigDecimal.valueOf(Double.valueOf(rst.getString(3))),Integer.valueOf(rst.getString(4))));
        }
        return items;
    }
}
