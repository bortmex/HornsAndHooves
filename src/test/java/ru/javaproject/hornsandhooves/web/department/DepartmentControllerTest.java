package ru.javaproject.hornsandhooves.web.department;

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
import ru.javaproject.hornsandhooves.model.Department;
import ru.javaproject.hornsandhooves.service.DepartmentService;
import ru.javaproject.hornsandhooves.web.json.JacksonObjectMapper;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaproject.hornsandhooves.DepartmentTestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentControllerTest {
    private static final String REST_URL = DepartmentController.REST_URL + '/';

    @Autowired
    private DepartmentService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + DEPARTMENT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DEPARTMENT1));
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
        mockMvc.perform(delete(REST_URL + DEPARTMENT1_ID))
                .andDo(print())
                .andExpect(status().isOk());
        assertMatch(service.getAll(), DEPARTMENT2, DEPARTMENT3);
    }

    @Test
    @Sql(
            scripts = "classpath:/db/populateDB.sql",
            executionPhase = AFTER_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED)
    )
    public void testCreate() throws Exception {
        Department department = new Department(null, "Новый отдел");

        String req = JacksonObjectMapper.getMapper().writeValueAsString(department);

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON).param("name", department.getName()))
                .andExpect(status().isOk());

        /*Department returned = TestUtil.readFromJson(action, Department.class);
        department.setId(returned.getId());*/

        Assert.assertEquals(service.getAll().size(), 4);
    }

    @Test
    @Sql(
            scripts = "classpath:/db/populateDB.sql",
            executionPhase = AFTER_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED)
    )
    public void testUpdate() throws Exception {
        Department department = DEPARTMENT3;
        department.setName("Измененный отдел");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON).param("id", Integer.toString(department.getId())).param("name", department.getName()))
                .andExpect(status().isOk());

        assertMatch(service.get(DEPARTMENT3_ID), department);
    }

    @Test
    public void getAllOrders() throws Exception {
        this.mockMvc.perform(get(REST_URL)).andDo(print()).andExpect(status().isOk()).andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DEPARTMENT1, DEPARTMENT2, DEPARTMENT3));
    }
}