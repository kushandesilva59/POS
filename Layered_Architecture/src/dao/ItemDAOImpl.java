package dao;

import db.DBConnection;
import model.ItemDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements CrudDAO<ItemDTO,String>{
    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {


        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item");

        ArrayList <ItemDTO> items = new ArrayList<>();
        while(rst.next()){
            items.add(new ItemDTO(rst.getString(1),rst.getString(2), BigDecimal.valueOf(Double.valueOf(rst.getString(3))),Integer.valueOf(rst.getString(4))));
        }
        return items;
    }

    @Override
    public boolean save(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO item VALUES(?,?,?,?)",dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {


       return SQLUtil.executeUpdate("DELETE FROM Item WHERE code=?",code);
    }
    @Override
    public boolean update(ItemDTO dto) throws SQLException, ClassNotFoundException {
       return SQLUtil.executeUpdate("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getCode());
    }
    @Override
    public boolean ifExist(String code) throws SQLException, ClassNotFoundException {

       return SQLUtil.executeQuery("SELECT code FROM Item WHERE code=?",code).next();
    }
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public ItemDTO search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM item WHERE id = ?", code);
        if(rst.next()){
            return new ItemDTO(rst.getString(1),rst.getString(2),BigDecimal.valueOf(Double.valueOf(rst.getString(3))),Integer.valueOf(rst.getString(4)));
        }
        return null;
    }




    /*@Override
    public boolean save(String code,String description,BigDecimal unitPrice,int qtyOnHand) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeUpdate("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",code,description,unitPrice,qtyOnHand);
    }*/
}
