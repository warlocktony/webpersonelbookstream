package com.warlocktony.webstream;

import com.warlocktony.webstream.datatransferobject.Employee;
import com.warlocktony.webstream.exception.EmployeeNotFoundException;
import com.warlocktony.webstream.service.DepartmentServiceImpl;
import com.warlocktony.webstream.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import java.util.*;

@ExtendWith(MockitoExtension.class)
 class DepartmentServiceImplTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentServiceImpl underTest;

    Employee employee1 = new Employee("ANTON", "ANTONOV",
            100_000, 1);
    Employee employee2 = new Employee("IVAN", "IVANOV",
            90_000, 1);
    Employee employee3 = new Employee("IRA", "IRINOVNA",
            80_000, 2);
    Collection<Employee> employees;


    @BeforeEach
    void beforeEach() {
        employees = List.of(employee1, employee2, employee3);
    }

    @Test
    void findMaxSalaryOfStaff_employeeFind_returnEmployeeVsMaxSalary() {
        when(employeeService.findAll()).thenReturn(employees);

        Employee result = underTest.findMaxSalaryOfStaff(1);

        assertEquals(employee1, result);

    }

    @Test
    void findMaxSalaryOfStaff_employeeNotFind_throwEmployeeNotFoundException() {
        int department = 1;
        when(employeeService.findAll()).thenReturn(Collections.emptyList());
        EmployeeNotFoundException ex = assertThrows(EmployeeNotFoundException.class,
                () -> underTest.findMaxSalaryOfStaff(department));
        assertEquals("Staff not found !" + department, ex.getMessage());
    }

    @Test
    void findMinSalaryOfStaff_employeeFind_returnEmployeeVsMinSalary() {
        when(employeeService.findAll()).thenReturn(employees);

        Employee result = underTest.findMinSalaryOfStaff(1);

        assertEquals(employee2, result);


    }
    @Test
    void findMinSalaryOfStaff_employeeNotFind_throwEmployeeNotFoundException(){
        int department = 1;
        when(employeeService.findAll()).thenReturn(Collections.emptyList());
        EmployeeNotFoundException ex = assertThrows(EmployeeNotFoundException.class,
                ()->underTest.findMinSalaryOfStaff(department));
        assertEquals("Staff not found !" + department, ex.getMessage());
    }
    @Test
    void findAllStaffInDepartment_addEmployees_resultFindAllStaffInDepartments(){
        Collection<Employee> employeesDepartmentOne = List.of(
                employee1, employee2
        );
        when(employeeService.findAll()).thenReturn(employeesDepartmentOne);
        Collection<Employee> result = underTest.findAllStaffInDepartment(1);

        assertEquals(employeesDepartmentOne, result);


    }
    @Test
    void findAllStaffInDepartment_noStaffInDepartment_resultFindAllStaffInDepartments() {
        Collection<Employee> employeesDepartmentOne = List.of();

        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        Collection<Employee> result = underTest.findAllStaffInDepartment(1);

        assertEquals(employeesDepartmentOne, result);

    }
    @Test
    void findAllStaffInAllDepartment_employeesAreInCollection_resultFindAllStaffInAllDepartments() {
        Map<Integer, List<Employee>> employeeMap = new HashMap<>(Map.of(1,employee1,2,employee3));

        Map<Integer, List<Employee>> result = underTest.findAllStaffInAllDepartments();

        assertEquals(employeeMap, result);
    }
    @Test
    void findAllStaffInAllDepartment_employeesAreNotInCollection_resultFindAllStaffInAllDepartments() {
        Map<Integer, List<Employee>> employeeMap = new HashMap<>();

        Map<Integer, List<Employee>> result = underTest.findAllStaffInAllDepartments();

        assertEquals(employeeMap, result);
    }


}





