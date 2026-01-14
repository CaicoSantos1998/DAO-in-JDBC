package model.service;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.util.List;

public class SellerService {

    private Connection conn = DB.getConnection();
    private SellerDao dao = DaoFactory.createSellerDao(conn);

    public void insert(Seller seller) {
        dao.insert(seller);
    }

    public void update(Seller seller) {
        dao.update(seller);
    }

    public void deleteById(Integer id) {
        dao.deleteById(id);
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
