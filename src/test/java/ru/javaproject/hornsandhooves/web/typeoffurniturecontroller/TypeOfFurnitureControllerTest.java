package ru.javaproject.hornsandhooves.web.typeoffurniturecontroller;

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
import ru.javaproject.hornsandhooves.model.TypeOfFurniture;
import ru.javaproject.hornsandhooves.service.TypeOfFurnitureService;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaproject.hornsandhooves.DepartmentTestData.DEPARTMENT1_ID;
import static ru.javaproject.hornsandhooves.TypeOfFurnitureTestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TypeOfFurnitureControllerTest {
    private static final String REST_URL = TypeOfFurnitureController.REST_URL + '/';

    @Autowired
    private TypeOfFurnitureService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + TYPEOFFURNITURE1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(TYPEOFFURNITURE1));
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
        mockMvc.perform(delete(REST_URL + TYPEOFFURNITURE1_ID))
                .andDo(print())
                .andExpect(status().isOk());
        assertMatch(service.getAll(), TYPEOFFURNITURE2, TYPEOFFURNITURE3, TYPEOFFURNITURE4,
                                      TYPEOFFURNITURE5, TYPEOFFURNITURE6, TYPEOFFURNITURE7,
                                      TYPEOFFURNITURE8, TYPEOFFURNITURE9);
    }


    @Test
    @Sql(
            scripts = "classpath:/db/populateDB.sql",
            executionPhase = AFTER_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED)
    )
    public void testCreate() throws Exception {
        TypeOfFurniture typeOfFurniture = new TypeOfFurniture(null, "Мебель", 99999);

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON).param("name", typeOfFurniture.getName())
                .param("department", Integer.toString(DEPARTMENT1_ID))
                .param("numberofsecondsperformedbyoneperson", Integer.toString(typeOfFurniture.getNumberofsecondsperformedbyoneperson())))
                .andExpect(status().isOk());

        Assert.assertEquals(service.getAll().size(), 10);
    }


    @Test
    @Sql(
            scripts = "classpath:/db/populateDB.sql",
            executionPhase = AFTER_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED)
    )
    public void testUpdate() throws Exception {
        TypeOfFurniture typeOfFurniture = TYPEOFFURNITURE1;
        typeOfFurniture.setName("Измененное название мебели");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON).param("id", Integer.toString(typeOfFurniture.getId()))
                .param("name", typeOfFurniture.getName())
                .param("numberofsecondsperformedbyoneperson", Integer.toString(typeOfFurniture.getNumberofsecondsperformedbyoneperson()))
        )
                .andExpect(status().isOk());

        assertMatch(service.get(TYPEOFFURNITURE1_ID), typeOfFurniture);
    }


    @Test
    public void getAllOrders() throws Exception {
        this.mockMvc.perform(get(REST_URL)).andDo(print()).andExpect(status().isOk()).andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(TYPEOFFURNITURE1, TYPEOFFURNITURE2, TYPEOFFURNITURE3, TYPEOFFURNITURE4,
                        TYPEOFFURNITURE5, TYPEOFFURNITURE6, TYPEOFFURNITURE7,
                        TYPEOFFURNITURE8, TYPEOFFURNITURE9));
    }

}