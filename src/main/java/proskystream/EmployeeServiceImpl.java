package proskystream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final int MAX_SIZE = 100;
    private final Map<String, Employee> employees = new HashMap<>(MAX_SIZE);
    private int salary;
    private int department;



    @Override
    public Employee add(String firstName, String lastName) {
       checkArg(firstName, lastName);

            if (employees.size() > MAX_SIZE) {
                throw new EmployeeStoragelsFullException();
            }
        var key = (firstName + "_" + lastName).toLowerCase();
        if (employees.containsKey(key)) {

            throw new EmployeeAlreadyAddedException();
        }

        var employee = create(firstName, lastName);
        employees.put(key, employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        checkArg(firstName, lastName);
        var key = (firstName + "_" + lastName).toLowerCase();
        var removed = employees.remove(key);
        if (removed == null) {
            throw new EmployeeNotFoundException();
        }

        return removed;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        checkArg(firstName, lastName);
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

    private void checkArg(String... args) {
        for (String arg : args) {
            if (!StringUtils.isAlpha(arg)) {
                throw new NotValidCaracterException();
            }
        }
    }

    private Employee create(String firstName, String lastName) {
        return new Employee(
                StringUtils.capitalize(firstName),
                StringUtils.capitalize(lastName),
                1, 555444);
    }


}
