package Stream;


import proskystream.Employee;
import proskystream.EmployeeServiceImpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class DepartmentService {
    private final EmployeeServiceImpl employeeService;

    public DepartmentService(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    public Employee max(int dept) {
       return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartment() == dept)
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }
    public Employee min(int dept) {
        return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartment() == dept)
                .min(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }
    public Collection<Employee> allByDept(int dept) {
        return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartment() == dept)
                .collect(Collectors.toList());
    }
    public Map<Integer, List <Employee>> all() {
        return employeeService.getAll()
                .stream()
                .collect(groupingBy(Employee::getDepartment));

    }
}
