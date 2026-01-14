package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
import model.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        DepartmentService departmentService = new DepartmentService();

        System.out.println("=== TEST 1: Department findByID ===");
        Department dep = departmentService.findById(12);
        System.out.println(dep);
        System.out.println();

        /*System.out.println("=== TEST 2: Department findAll ===");
        List<Department> list = departmentService.findAll();
        list.forEach(System.out::println);
        System.out.println();*/

        System.out.println("=== TEST 3: Department insert ===");
        dep = new Department(null, "Test");
        departmentService.insert(dep);
        System.out.println("Inserted! New Id: " + dep.getId());
        System.out.println();

        /*System.out.println("=== TEST 4: Department update ===");
        dep = departmentService.findById(6);
        dep.setName("Network management");
        departmentService.update(dep);
        System.out.println("Update completed!");
        System.out.println();

        System.out.println("=== TEST 5: Department deleteById ===");
        System.out.println("Enter if for delete test: ");
        int id = kb.nextInt();
        departmentService.deleteById(id);
        System.out.println("Delete completed!");
        System.out.println();*/


        DB.closeConnection();
        kb.close();
    }
}
