package com.warlocktony.webstream.controller;

import com.warlocktony.webstream.datatransferobject.Employee;
import com.warlocktony.webstream.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    public final DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService){
        this.departmentService=departmentService;
    }
    @GetMapping("/max-salary")
    public Employee findMaxSalaryOfStaff(@RequestParam int department){
        return departmentService.findMaxSalaryOfStaff(department);

    }
    @GetMapping("/min-salary")
    public Employee findMinSalaryOfStaff(@RequestParam int department) {
        return departmentService.findMinSalaryOfStaff(department);
    }
    @GetMapping("/all-in-department")
    public Collection<Employee> findAllStaffInDepartment(@RequestParam int department) {
        return departmentService.findAllStaffInDepartment(department);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> findAllStaffInAllDepartment() {
        return departmentService.findAllStaffInAllDepartments();
    }

}
