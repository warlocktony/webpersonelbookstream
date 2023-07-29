package com.warlocktony.webstream.service;

import com.warlocktony.webstream.datatransferobject.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee findMaxSalaryOfStaff(int department);
    Employee findMinSalaryOfStaff(int department);
    Collection<Employee> findAllStaffInDepartment(int department);
    Map<Integer, List<Employee>> findAllStaffInAllDepartments();
}
