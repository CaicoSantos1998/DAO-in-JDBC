package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;
    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department depart) {
        try (PreparedStatement pst = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, depart.getName());
            int rowsAffected = pst.executeUpdate();
            if(rowsAffected>0) {
                ResultSet rs = pst.getGeneratedKeys();
                if(rs.next()) {
                    int id = rs.getInt(1);
                    depart.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Department depart) {
        try (PreparedStatement pst = conn.prepareStatement("UPDATE department SET Name = ? WHERE id = ?")){
            pst.setString(1, depart.getName());
            pst.setInt(2, depart.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (PreparedStatement pst = conn.prepareStatement("DELETE FROM department WHERE Id = ?")) {
            pst.setInt(1, id);
            int rows = pst.executeUpdate();
            if(rows==0) {
                throw new DbException("Id not exist!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Department findById(Integer id) {
        try (PreparedStatement pst = conn.prepareStatement("SELECT * FROM department WHERE id = ?");
        ResultSet rs = pst.executeQuery()) {
            pst.setInt(1, id);
            if(rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));
                return dep;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Department> findAll() {
        try (PreparedStatement pst = conn.prepareStatement("SELECT * FROM department ORDER BY Name");
        ResultSet rs = pst.executeQuery()) {
            List<Department> list = new ArrayList<>();
            while (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));
                list.add(dep);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}