package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== TEST 1: Department findByID ===");
        Department dep = departmentDao.findById(2);
        System.out.println(dep);
        System.out.println();

        System.out.println("=== TEST 2: Department findAll ===");
        List<Department> list = departmentDao.findAll();
        list.forEach(System.out::println);
        System.out.println();

        System.out.println("=== TEST 3: Department insert ===");
        dep = new Department(null, "TI");
        departmentDao.insert(dep);
        System.out.println("Inserted! New Id: " + dep.getId());
        System.out.println();

        System.out.println("=== TEST 4: Department update ===");
        dep = departmentDao.findById(6);
        dep.setName("Network management");
        departmentDao.update(dep);
        System.out.println("Update completed!");
        System.out.println();

        kb.close();
    }
}
