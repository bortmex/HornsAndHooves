package ru.javaproject.hornsandhooves;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.javaproject.hornsandhooves.model.TypeOfFurniture;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.javaproject.hornsandhooves.model.BaseEntity.START_SEQ;
import static ru.javaproject.hornsandhooves.web.json.JsonUtil.writeIgnoreProps;

public class TypeOfFurnitureTestData {

    public static final int TYPEOFFURNITURE1_ID = START_SEQ + 3;
    public static final int TYPEOFFURNITURE2_ID = START_SEQ + 4;
    public static final int TYPEOFFURNITURE3_ID = START_SEQ + 5;
    public static final int TYPEOFFURNITURE4_ID = START_SEQ + 6;
    public static final int TYPEOFFURNITURE5_ID = START_SEQ + 7;
    public static final int TYPEOFFURNITURE6_ID = START_SEQ + 8;
    public static final int TYPEOFFURNITURE7_ID = START_SEQ + 9;
    public static final int TYPEOFFURNITURE8_ID = START_SEQ + 10;
    public static final int TYPEOFFURNITURE9_ID = START_SEQ + 11;

    public static final TypeOfFurniture TYPEOFFURNITURE1 = new TypeOfFurniture(TYPEOFFURNITURE1_ID, "кровать", 864000);
    public static final TypeOfFurniture TYPEOFFURNITURE2 = new TypeOfFurniture(TYPEOFFURNITURE2_ID, "диван", 1036800);
    public static final TypeOfFurniture TYPEOFFURNITURE3 = new TypeOfFurniture(TYPEOFFURNITURE3_ID, "кресло", 1728000);
    public static final TypeOfFurniture TYPEOFFURNITURE4 = new TypeOfFurniture(TYPEOFFURNITURE4_ID, "шкаф", 864000);
    public static final TypeOfFurniture TYPEOFFURNITURE5 = new TypeOfFurniture(TYPEOFFURNITURE5_ID, "тумба", 1468800);
    public static final TypeOfFurniture TYPEOFFURNITURE6 = new TypeOfFurniture(TYPEOFFURNITURE6_ID, "полка", 864000);
    public static final TypeOfFurniture TYPEOFFURNITURE7 = new TypeOfFurniture(TYPEOFFURNITURE7_ID, "стол", 1123200);
    public static final TypeOfFurniture TYPEOFFURNITURE8 = new TypeOfFurniture(TYPEOFFURNITURE8_ID, "стул", 1036800);
    public static final TypeOfFurniture TYPEOFFURNITURE9 = new TypeOfFurniture(TYPEOFFURNITURE9_ID, "кресло-качалка", 1296000);

    public static void assertMatch(TypeOfFurniture actual, TypeOfFurniture expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "typeOfFurniture", "orders");
    }

    public static void assertMatch(Iterable<TypeOfFurniture> actual, TypeOfFurniture... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<TypeOfFurniture> actual, Iterable<TypeOfFurniture> expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static ResultMatcher contentJson(TypeOfFurniture... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected)));
    }

    public static ResultMatcher contentJson(TypeOfFurniture expected) {
        return content().json(writeIgnoreProps(expected, "registered", "password"));
    }

}
