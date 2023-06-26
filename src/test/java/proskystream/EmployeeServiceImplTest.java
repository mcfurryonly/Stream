package proskystream;

import Stream.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {



    EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

    Collection<Employee> DATASET = List.of(
            new Employee("Иван", "Петров", 0, 0));

    @BeforeEach
    void setUp() {
        employeeServiceImpl.add("Иван","Петров");

    }



    @Test
    void add() {


        var actual = employeeServiceImpl.add("Петр", "Петров");
        var expected = new Employee("Петр", "Петров", 0, 0);

        assertEquals(actual, expected);


    }


    @Test
    void remove() {
        var actual = employeeServiceImpl.remove("Иван", "Петров");
        var expected = new Employee("Иван", "Петров", 0, 0);

        assertEquals(actual, expected);
    }

    @Test
    void find() {
        var actual = employeeServiceImpl.find("Иван", "Петров");
        var expected = new Employee("Иван", "Петров", 0, 0);

        assertEquals(actual, expected);
    }

    @Test
    void getAll() {

        var actual = employeeServiceImpl.getAll();
        var expected = DATASET ;
        assertIterableEquals(actual,expected);
    }
}