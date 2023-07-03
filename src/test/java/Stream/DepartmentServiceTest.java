package Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import proskystream.Employee;
import proskystream.EmployeeServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DepartmentServiceTest {
    @Mock
    EmployeeServiceImpl employeeServiceimpl;

    DepartmentService departmentService;

    List<Employee> DATASET = List.of(
            new Employee("fee", "rar", 1, 1000),
            new Employee("fee1", "rar1", 3, 1000),
            new Employee("fee2", "rar2", 4, 1000),
            new Employee("fee3", "rar3", 1, 1001));

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departmentService = new DepartmentService(employeeServiceimpl);
        when(employeeServiceimpl.getAll()).thenReturn(DATASET);
    }

    @Test
    void max() {


        var actual = departmentService.max(1);
        var expected = new Employee("fee3", "rar3", 1, 1001);
        assertEquals(expected, actual);
    }

    @Test
    void min() {
        var e1 = new Employee("fee", "rar", 1, 1000);
        var e2 = new Employee("fee1", "rar1", 3, 1000);
        var e3 = new Employee("fee2", "rar2", 4, 1000);
        var e4 = new Employee("fee3", "rar3", 1, 1001);

        when(employeeServiceimpl.getAll()).thenReturn(List.of(e1, e2, e3, e4));


        var actualMin = departmentService.min(1);
        assertEquals(e1, actualMin);
    }

    @Test
    void allByDept() {


        var firstDeptAll = departmentService.allByDept(1);
        assertEquals(firstDeptAll.size(), 2);

        assertIterableEquals(List.of(DATASET.get(0), DATASET.get(3)), firstDeptAll);


    }

    @Test
    void all() {



        var actual = employeeServiceimpl.getAll()
                .stream()
                .collect(groupingBy(Employee::getDepartment));


        var DATA = new HashMap<Integer, List<Employee>>();
        DATA.put(1, List.of(
                new Employee("fee", "rar", 1, 1000),
                new Employee("fee3", "rar3", 1, 1001)));
        DATA.put(3, List.of(
                new Employee("fee1", "rar1", 3, 1000)
        ));
        DATA.put(4, List.of(
                new Employee("fee2", "rar2", 4, 1000)
        ));
        var expected = DATA;
        assertEquals(expected, actual);

    }

}