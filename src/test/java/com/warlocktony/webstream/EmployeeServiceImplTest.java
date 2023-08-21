package com.warlocktony.webstream;

import com.warlocktony.webstream.datatransferobject.Employee;
import com.warlocktony.webstream.exception.EmployeeAlreadyAddedException;
import com.warlocktony.webstream.exception.EmployeeNotFoundException;
import com.warlocktony.webstream.exception.EmployeeStorageIsFullException;
import com.warlocktony.webstream.service.EmployeeService;
import com.warlocktony.webstream.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class EmployeeServiceImplTest {

     EmployeeService underTest = new EmployeeServiceImpl();
     Employee employee1 = new Employee
             ("ANTON","ANTONOV",100_000,1);
     Employee employee2 = new Employee
             ("IVAN","IVANOV",90_000,1);
     Employee employee3= new Employee
             ("IRA","IRINOVNA",80_000,1);
     Collection<Employee> employees = List.of(employee1,employee2);
     Collection<Employee> employeesEmpty = List.of();

     @Test

     void addEmployee_mapIsFull_throwEmployeeStorageIsFullException(){
         underTest.addEmployee(employee1.getFirstName(),employee1.getLastName()
                 ,employee1.getSalary(),employee1.getDepartment());
         underTest.addEmployee(employee2.getFirstName(),employee2.getLastName()
                 ,employee2.getSalary(),employee2.getDepartment());
         EmployeeStorageIsFullException ex =
                 assertThrows(EmployeeStorageIsFullException.class,
                         () -> underTest.addEmployee(employee3.getFirstName(),employee3.getLastName()
                                 ,employee3.getSalary(),employee3.getDepartment())
                         );
         assertEquals("staff limit is over",ex.getMessage());


     }
     @Test
     void addEmployee_employeeAlreadyInMap_throwEmployeeAlreadyAddedException(){
         underTest.addEmployee(employee1.getFirstName(),employee1.getLastName()
                 ,employee1.getSalary(),employee1.getDepartment());
         EmployeeAlreadyAddedException ex =
                 assertThrows(EmployeeAlreadyAddedException.class,
                         () -> underTest.addEmployee(employee1.getFirstName(),employee1.getLastName()
                                 ,employee1.getSalary(),employee1.getDepartment())
                 );
         assertEquals("this staff already added",ex.getMessage());

     }
     @Test
     void addEmployee_employeeNotInMap_checkResult(){
         underTest.addEmployee(employee1.getFirstName(),employee1.getLastName()
                 ,employee1.getSalary(),employee1.getDepartment());
         Employee result = underTest.findEmployee(
                 employee1.getFirstName(),employee1.getLastName());
         assertEquals(employee1,result);
         assertTrue(underTest.findAll().contains(employee1));
     }


    @Test
    void removeEmployee_employeeIsNotInMap_thrownException(){
        EmployeeNotFoundException ex =
        assertThrows(EmployeeNotFoundException.class,
                () -> underTest.removeEmployee("Anton","Antonov"));
        assertEquals("staff not found",ex.getMessage());

    }
    @Test
     void removeEmployee_employeeIsInMap_employeeRemovedAndReturned(){
        underTest.addEmployee(employee1.getFirstName(),employee1.getLastName()
                ,employee1.getSalary(),employee1.getDepartment());
        Employee result = underTest.removeEmployee
                (employee1.getFirstName(),employee1.getLastName());
        assertEquals(employee1,result);
        assertFalse(underTest.findAll().contains(employee1));
    }
     @Test
     void findEmployee_employeeInMap_returnEmployee(){
         underTest.addEmployee(employee1.getFirstName(),employee1.getLastName()
                 ,employee1.getSalary(),employee1.getDepartment());
         Employee result = underTest.findEmployee(
                 employee1.getFirstName(),employee1.getLastName());
         assertEquals(employee1,result);
         assertTrue(underTest.findAll().contains(employee1));
     }
     @Test
     void findEmployee_employeeNotInMap_throwException(){
         EmployeeNotFoundException ex =
                 assertThrows(EmployeeNotFoundException.class,
                         () -> underTest.findEmployee("Django","Ohlajdenovich"));
         assertEquals("staff not found",ex.getMessage());
     }
     @Test
     void findAll_employeesInMap_returnListOfEmployees() {
         underTest.addEmployee(employee1.getFirstName(), employee1.getLastName()
                 , employee1.getSalary(), employee1.getDepartment());
         underTest.addEmployee(employee2.getFirstName(), employee2.getLastName()
                 , employee2.getSalary(), employee2.getDepartment());

         Collection<Employee> result = underTest.findAll();
         assertFalse(result.contains(employees));


     }
     @Test
     void findAll_collectionIsEmpty_returnEmptyCollection(){

         Collection<Employee> result = underTest.findAll();
         assertFalse(result.contains(employeesEmpty));

     }

}
