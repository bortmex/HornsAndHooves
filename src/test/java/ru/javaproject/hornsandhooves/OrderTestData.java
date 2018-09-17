package ru.javaproject.hornsandhooves;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.javaproject.hornsandhooves.model.Order;
import ru.javaproject.hornsandhooves.model.Status;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.javaproject.hornsandhooves.TypeOfFurnitureTestData.*;
import static ru.javaproject.hornsandhooves.model.BaseEntity.START_SEQ;
import static ru.javaproject.hornsandhooves.web.json.JsonUtil.writeIgnoreProps;

public class OrderTestData {
    public static final int ORDER1_ID = START_SEQ + 32;
    public static final int ORDER2_ID = START_SEQ + 33;
    public static final int ORDER3_ID = START_SEQ + 34;
    public static final int ORDER4_ID = START_SEQ + 35;

    public static final Order ORDER1WITHDATA = new Order(ORDER1_ID, "ордер№100010", TYPEOFFURNITURE2, LocalDateTime.parse("2018-09-16T15:04:00"), Status.STATUS_INTHEWORK_DEPARTMENT, LocalDateTime.of(2018, 9, 15, 15, 4),  345599);
    public static final Order ORDER2WITHDATA = new Order(ORDER2_ID, "ордер№100011", TYPEOFFURNITURE4, LocalDateTime.parse("2018-09-16T15:04:00"), Status.STATUS_INTHEWORK_DEPARTMENT, LocalDateTime.of(2018, 9, 14, 23, 4),  287999);
    public static final Order ORDER3WITHDATA = new Order(ORDER3_ID, "ордер№100012", TYPEOFFURNITURE1, LocalDateTime.parse("2018-09-16T15:04:00"), Status.STATUS_INTHEWORK_PERSON, LocalDateTime.of(2018, 9, 21, 15, 4),  863999);
    public static final Order ORDER4WITHDATA = new Order(ORDER4_ID, "ордер№100013", TYPEOFFURNITURE5, LocalDateTime.parse("2018-09-16T15:04:00"), Status.STATUS_INTHEWORK_PERSON, LocalDateTime.of(2018, 9, 17, 7, 4),  489599);

    public static final Order ORDER1 = new Order(ORDER1_ID, "ордер№100010", TYPEOFFURNITURE2, LocalDateTime.parse("2018-09-16T15:04:00"), Status.STATUS_PENDING, null, null);
    public static final Order ORDER2 = new Order(ORDER2_ID, "ордер№100011", TYPEOFFURNITURE4, LocalDateTime.parse("2018-09-16T15:04:00"), Status.STATUS_PENDING, null, null);
    public static final Order ORDER3 = new Order(ORDER3_ID, "ордер№100012", TYPEOFFURNITURE1, LocalDateTime.parse("2018-09-16T15:04:00"), Status.STATUS_PENDING, null, null);
    public static final Order ORDER4 = new Order(ORDER4_ID, "ордер№100013", TYPEOFFURNITURE5, LocalDateTime.parse("2018-09-16T15:04:00"), Status.STATUS_PENDING, null, null);

    public static void assertMatch(Order actual, Order expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "endOrder","createOrder", "actualDeadline","typeOfFurniture", "orders");
    }

    public static void assertMatch(Iterable<Order> actual, Order... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Order> actual, Iterable<Order> expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Order expected) {
        return content().json(writeIgnoreProps(expected, "endOrder","createOrder", "actualDeadline","typeOfFurniture", "orders"));
    }

    public static ResultMatcher contentJson(Order... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "endOrder","createOrder", "actualDeadline", "typeOfFurniture", "orders"));
    }


}
