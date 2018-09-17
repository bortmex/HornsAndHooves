package ru.javaproject.hornsandhooves.web.order;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.javaproject.hornsandhooves.model.Order;
import ru.javaproject.hornsandhooves.model.Status;
import ru.javaproject.hornsandhooves.repository.ManagerSystemOrderRepositoryImpl;
import ru.javaproject.hornsandhooves.service.OrderService;

import java.time.LocalDateTime;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaproject.hornsandhooves.OrderTestData.*;
import static ru.javaproject.hornsandhooves.TypeOfFurnitureTestData.TYPEOFFURNITURE1_ID;
import static ru.javaproject.hornsandhooves.TypeOfFurnitureTestData.TYPEOFFURNITURE2;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    private static final String REST_URL = OrderController.REST_URL + '/';

    @Autowired
    private OrderService orderService;

    @Autowired
    private ManagerSystemOrderRepositoryImpl managerSystemOrder;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + ORDER1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ORDER1));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @Sql(
            scripts = "classpath:/db/populateDB.sql",
            executionPhase = AFTER_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED)
    )
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + ORDER1_ID))
                .andDo(print())
                .andExpect(status().isOk());
        assertMatch(orderService.getAll(), ORDER2WITHDATA, ORDER3WITHDATA, ORDER4WITHDATA);
    }

    @Test
    @Sql(
            scripts = "classpath:/db/populateDB.sql",
            executionPhase = AFTER_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED)
    )
    public void testCreate() throws Exception {
        Order order = new Order(null, "ордер№100018", TYPEOFFURNITURE2, LocalDateTime.of(2018, 9, 16, 15, 4), Status.STATUS_PENDING, LocalDateTime.of(2018, 9, 15, 15, 4),  345599);;

        ResultActions action = mockMvc.perform(post(REST_URL + TYPEOFFURNITURE1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", order.getName())
                .param("typeId", Integer.toString(order.getTypeOfFurniture().getId()))
                .param("status", order.getStatus().toString()))
                .andExpect(status().isOk());

        Assert.assertEquals(orderService.getAll().size(), 5);
    }

    @Test
    @Sql(
            scripts = "classpath:/db/populateDB.sql",
            executionPhase = AFTER_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED)
    )
    public void testUpdate() throws Exception {
        Order order = ORDER1WITHDATA;
        order.setName("Измененное имя ордера");
        ResultActions action = mockMvc.perform(post(REST_URL + ORDER1WITHDATA.getTypeOfFurniture().getId())
                .param("id", Integer.toString(order.getId()))
                .param("name", order.getName())
                .param("depId", Integer.toString(order.getTypeOfFurniture().getId()))
                .param("status", order.getStatus().toString()))
                .andExpect(status().isOk());

        assertMatch(orderService.get(ORDER1_ID), order);
    }

    @Test
    public void getAllOrders() throws Exception {
        this.mockMvc.perform(get(REST_URL)).andDo(print()).andExpect(status().isOk()).andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ORDER1, ORDER2, ORDER3, ORDER4));
        /*if you run the test not together with all then insert this data into the output
                .andExpect(contentJson(ORDER1WITHDATA, ORDER2WITHDATA, ORDER3WITHDATA, ORDER4WITHDATA));*/
    }

    @Test
    @Sql(
            scripts = "classpath:/db/populateDB.sql",
            executionPhase = AFTER_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED)
    )
    public void getAllPendingOrders() throws Exception {
        this.mockMvc.perform(get(REST_URL + "/pending")).andDo(print()).andExpect(status().isOk()).andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ORDER1WITHDATA, ORDER2WITHDATA, ORDER3WITHDATA, ORDER4WITHDATA));
    }

    @Test
    @Sql(
            scripts = "classpath:/db/populateDB.sql",
            executionPhase = AFTER_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED)
    )
    public void getAllPendingInTheWorkPerson() throws Exception {
        this.mockMvc.perform(get(REST_URL + "/pendingintheworkperson")).andDo(print()).andExpect(status().isOk()).andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ORDER3WITHDATA, ORDER4WITHDATA));
    }

    @Test
    @Sql(
            scripts = "classpath:/db/populateDB.sql",
            executionPhase = AFTER_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED)
    )
    public void getAllPendingInTheWorkDepartment() throws Exception {
        this.mockMvc.perform(get(REST_URL + "/pendingintheworkdepartment")).andDo(print()).andExpect(status().isOk()).andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ORDER1WITHDATA, ORDER2WITHDATA));
    }

}