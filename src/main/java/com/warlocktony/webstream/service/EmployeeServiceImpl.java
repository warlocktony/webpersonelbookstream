package com.warlocktony.webstream.service;

import com.warlocktony.webstream.datatransferobject.Employee;
import com.warlocktony.webstream.exception.EmployeeAlreadyAddedException;
import com.warlocktony.webstream.exception.EmployeeNotFoundException;
import com.warlocktony.webstream.exception.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee>employeeMap;
    private static final int EMPLOYEE_MAX_SIZE = 2;

    public EmployeeServiceImpl(){
        this.employeeMap=new HashMap<>();

    }
    @Override
    public Employee addEmployee(String firstName, String lastName,double salary,int department){

        if(employeeMap.size()==EMPLOYEE_MAX_SIZE){
            throw new EmployeeStorageIsFullException("staff limit is over");
        }
        Employee employee = new Employee(
                StringUtils.capitalize(firstName),
                StringUtils.capitalize(lastName),
                salary,
                department );
        String key = (firstName + lastName);
        if(employeeMap.containsKey(key)){
            throw new EmployeeAlreadyAddedException("this staff already added");
        }
        employeeMap.put(key, employee);
        return employee;
    }
    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = employeeMap.remove(firstName + lastName);
        if(employee == null){
            throw new EmployeeNotFoundException("staff not found");
        }
        return employee;

    }
    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = employeeMap.get(firstName + lastName);
        if(employee == null){
            throw new EmployeeNotFoundException("staff not found");
        }
        return employee;
    }
    @Override
    public Collection<Employee> findAll(){
        return employeeMap.values();
    }



}
