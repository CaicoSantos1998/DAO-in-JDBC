package model.service;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SellerService {

    private Connection conn = DB.getConnection();
    private SellerDao dao = DaoFactory.createSellerDao(conn);

    public void insert(Seller seller) {
        try {
            DB.beginTransaction(conn);
            dao.insert(seller);
            DB.commit(conn);
        } catch (DbException e) {
            DB.rollback(conn);
            System.out.println("Error to save seller!: " + e.getMessage());
        }
    }

    public void update(Seller seller) {
        try {
            DB.beginTransaction(conn);
            dao.update(seller);
            DB.commit(conn);
        } catch (DbException e) {
            DB.rollback(conn);
            System.out.println("Error to update seller!: " + e.getMessage());
        }
    }

    public void deleteById(Integer id) {
        try {
            DB.beginTransaction(conn);
            dao.deleteById(id);
            DB.commit(conn);
            System.out.println("Done!");
        } catch (DbException e) {
            DB.rollback(conn);
            System.out.println("Error to delete seller!: " + e.getMessage());
        }
    }

    public Seller findById(Integer id) {
        return dao.findById(id);
    }

    public List<Seller> findAll() {
        return dao.findAll();
    }

    public List<Seller> findByDepartment(Department department) {
        return dao.findByDepartment(department);
    }
}
