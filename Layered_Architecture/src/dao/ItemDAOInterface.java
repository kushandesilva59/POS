package dao;

import model.ItemDTO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAOInterface {
    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
    boolean ifExistItem(String code) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    boolean saveItem(String code, String description, BigDecimal unitPrice, int qtyOnHand) throws SQLException, ClassNotFoundException;
}
