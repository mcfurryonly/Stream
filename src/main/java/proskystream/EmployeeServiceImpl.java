package proskystream;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public abstract class EmployeeServiceImpl implements EmployeeService {
    private static final int MAX_SIZE = 100;
    private final Map<String, Employee> employees = new HashMap<>(MAX_SIZE);
    private int salary;
    private int department;


    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() > MAX_SIZE) {
            throw new EmployeeStoragelsFullException();
        }
        var key = (firstName + "_" + lastName).toLowerCase();
        if (employees.containsKey(key)) {

            throw new EmployeeAlreadyAddedException();
        }

        var employee = new Employee(firstName, lastName, department, salary);
        employees.put(key, employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        var key = (firstName + "_" + lastName).toLowerCase();
        var removed = employees.remove(key);
        if (removed == null) {
            throw new EmployeeNotFoundException();
        }

        return removed;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        var key = (firstName + "_" + lastName).toLowerCase();
        var employee = employees.get(key);
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }


    @Override
    public Collection<Employee> getAll() {
        return employees.values();
    }


}
