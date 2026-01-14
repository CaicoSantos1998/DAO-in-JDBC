package model.services;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.util.List;

public class DepartmentService {

    private DepartmentDao dao;
    private Connection conn;

    public DepartmentService() {
        this.conn = DB.getConnection();
        this.dao = DaoFactory.createDepartmentDao(conn);
    }

    public void insert(Department department) {
        try {
            DB.beginTransaction(conn);
            dao.insert(department);
            DB.commit(conn);
            System.out.println("Done!");
        } catch (DbException e) {
            DB.rollback(conn);
            System.out.println("Error to insert department: " + e.getMessage());
        }
    }

    public void update(Department department) {
        try {
            DB.beginTransaction(conn);
            dao.update(department);
            DB.commit(conn);
            System.out.println("Done!");
        } catch (DbException e) {
            DB.rollback(conn);
            System.out.println("Error to update department: " + e.getMessage());
        }
    }

    public void deleteById(Integer id) {
        if(dao.findById(id)==null) {
            throw new DbException("Id: " + id + " not found!");
        }
        try {
            DB.beginTransaction(conn);
            dao.deleteById(id);
            DB.commit(conn);
            System.out.println("Done!");
        } catch (DbIntegrityException e) {
            DB.rollback(conn);
            throw e;
        }
    }

    public Department findById(Integer id) {
        Department dep = dao.findById(id);
        if(dep==null) {
            throw new DbIntegrityException("Id: " + id + " not found!");
        }
        return dep;
    }

    public List<Department> findAll() {
        return dao.findAll();
    }
}
