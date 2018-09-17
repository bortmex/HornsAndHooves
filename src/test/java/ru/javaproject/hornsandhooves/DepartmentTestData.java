package ru.javaproject.hornsandhooves;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.javaproject.hornsandhooves.model.Department;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.javaproject.hornsandhooves.model.BaseEntity.START_SEQ;
import static ru.javaproject.hornsandhooves.web.json.JsonUtil.writeIgnoreProps;

public class DepartmentTestData {
    public static final int DEPARTMENT1_ID = START_SEQ;
    public static final int DEPARTMENT2_ID = START_SEQ + 1;
    public static final int DEPARTMENT3_ID = START_SEQ + 2;

    public static final Department DEPARTMENT1 = new Department(DEPARTMENT1_ID, "мягкая мебель");
    public static final Department DEPARTMENT2 = new Department(DEPARTMENT2_ID, "системы хранения");
    public static final Department DEPARTMENT3 = new Department(DEPARTMENT3_ID, "офисная мебель");

    public static void assertMatch(Department actual, Department expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "typeOfFurniture", "employees");
    }

    public static void assertMatch(Iterable<Department> actual, Department... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Department> actual, Iterable<Department> expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Department... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected)));
    }

    public static ResultMatcher contentJson(Department expected) {
        return content().json(writeIgnoreProps(expected));
    }

}
