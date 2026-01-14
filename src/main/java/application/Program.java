package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
import model.services.DepartmentService;
import model.services.SellerService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        SellerService sellerService = new SellerService();
        /*System.out.println("=== TEST 1: seller findByID ===");
        Seller seller = sellerService.findById(15);
        System.out.println(seller);
        System.out.println();*/

        /*System.out.println("=== TEST 2: seller findByDepartment ===");
        Department department = new Department(2, null);
        List<Seller> list = sellerService.findByDepartment(department);
        list.forEach(System.out::println);
        System.out.println();*/

        /*System.out.println("=== TEST 3: seller findAll ===");
        list = sellerService.findAll();
        list.forEach(System.out::println);
        System.out.println();*/

        /*System.out.println("=== TEST 4: seller insert ===");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
        sellerService.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());
        System.out.println();*/

        /*System.out.println("=== TEST 5: seller update ===");
        seller = sellerService.findById(1);
        seller.setName("Martha Waine");
        sellerService.update(seller);
        System.out.println("Update completed!");
        System.out.println();*/

        /*System.out.println("=== TEST 6: seller delete ===");
        System.out.println("Enter if for delete test: ");
        int id = kb.nextInt();
        sellerService.deleteById(id);
        System.out.println();*/

        /****************************************TESTs-DEPARTMENT*****************************************************/
        DepartmentService departmentService = new DepartmentService();

        /*System.out.println("=== TEST 1: Department findByID ===");
        Department dep = departmentService.findById(20);
        System.out.println(dep);
        System.out.println();*/

        /*System.out.println("=== TEST 2: Department findAll ===");
        List<Department> list = departmentService.findAll();
        list.forEach(System.out::println);
        System.out.println();*/

        /*System.out.println("=== TEST 3: Department insert ===");
        dep = new Department(null, "Test");
        departmentService.insert(dep);
        System.out.println("Inserted! New Id: " + dep.getId());
        System.out.println();*/

        /*System.out.println("=== TEST 4: Department update ===");
        dep = departmentService.findById(6);
        dep.setName("Network management");
        departmentService.update(dep);
        System.out.println("Update completed!");
        System.out.println();*/

        System.out.println("=== TEST 5: Department deleteById ===");
        System.out.println("Enter if for delete test: ");
        int id = kb.nextInt();
        departmentService.deleteById(id);
        System.out.println();


        DB.closePool();
        kb.close();
    }
}
