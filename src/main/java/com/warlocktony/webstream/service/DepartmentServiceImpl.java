package com.warlocktony.webstream.service;

import com.warlocktony.webstream.datatransferobject.Employee;
import com.warlocktony.webstream.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final EmployeeService employeeService;
    public DepartmentServiceImpl(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @Override
    public Employee findMaxSalaryOfStaff(int department){
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(employee -> employee.getSalary()))
                .orElseThrow(()-> new EmployeeNotFoundException("Staff not found !" + department));

    }

    @Override
    public Employee findMinSalaryOfStaff(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingDouble(employee ->employee.getSalary()))
                .orElseThrow(()-> new EmployeeNotFoundException("Staff not found !" + department));
    }

    @Override
    public Collection<Employee> findAllStaffInDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> findAllStaffInAllDepartments() {
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment()));
    }

}
