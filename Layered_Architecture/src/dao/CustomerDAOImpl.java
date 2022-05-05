package dao;

import model.CustomerDTO;
import model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CrudDAO <CustomerDTO,String>{
    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Customer");

        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        while (rst.next()){
            allCustomers.add(new CustomerDTO(rst.getString(1),rst.getString(2),rst.getString(3)));
        }
        return allCustomers;
    }
    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {

       return SQLUtil.executeUpdate("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",dto.getId(),dto.getName(),dto.getAddress());

    }
    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {


        return SQLUtil.executeUpdate("UPDATE Customer SET name=?, address=? WHERE id=?",dto.getName(),dto.getAddress(),dto.getId());

    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {


      return SQLUtil.executeUpdate("DELETE FROM Customer WHERE id=?",id);
    }
    @Override
    public boolean ifExist(String id) throws SQLException, ClassNotFoundException {


        ResultSet resultSet = SQLUtil.executeQuery("SELECT id FROM Customer WHERE id=?", id);
        return resultSet.next();
    }
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM customer WHERE id = ?", id);
        if (rst.next()){
            return new CustomerDTO(rst.getString(1),rst.getString(2),rst.getString(3));
        }
        return null;
    }


}
