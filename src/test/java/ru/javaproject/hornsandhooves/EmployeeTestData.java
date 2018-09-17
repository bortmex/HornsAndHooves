package ru.javaproject.hornsandhooves;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.javaproject.hornsandhooves.model.Employee;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.javaproject.hornsandhooves.DepartmentTestData.*;
import static ru.javaproject.hornsandhooves.model.BaseEntity.START_SEQ;
import static ru.javaproject.hornsandhooves.web.json.JsonUtil.writeIgnoreProps;

public class EmployeeTestData {
    public static final int EMPLOYEE1_ID = START_SEQ + 12;

    public static final Employee EMPLOYEE1 = new Employee(EMPLOYEE1_ID, "Набойщиков Сергей Прокофиевич", DEPARTMENT1, true);

    public static void assertMatch(Iterable<Employee> actual, Employee... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static ResultMatcher contentJson(Employee... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected)));
    }

    public static ResultMatcher contentJson(Employee expected) {
        return content().json(writeIgnoreProps(expected));
    }

    public static void assertMatch(Employee actual, Employee expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<Employee> actual, Iterable<Employee> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("statusWork").isEqualTo(expected);
    }
}