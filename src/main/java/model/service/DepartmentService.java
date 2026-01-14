package model.service;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.util.List;

public class DepartmentService {

    private Connection conn = DB.getConnection();
    private DepartmentDao dao = DaoFactory.createDepartmentDao(conn);

    public void insert(Department department) {
        dao.insert(department);
    }

    public void update(Department department) {
        dao.update(department);
    }

    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    public Department findById(Integer id) {
        return dao.findById(id);
    }

    public List<Department> findAll() {
        return dao.findAll();
    }
}
