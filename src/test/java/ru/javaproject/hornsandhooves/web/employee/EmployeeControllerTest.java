package ru.javaproject.hornsandhooves.web.employee;

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
import ru.javaproject.hornsandhooves.model.Employee;
import ru.javaproject.hornsandhooves.service.EmployeesService;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaproject.hornsandhooves.DepartmentTestData.DEPARTMENT1;
import static ru.javaproject.hornsandhooves.DepartmentTestData.DEPARTMENT1_ID;
import static ru.javaproject.hornsandhooves.EmployeeTestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    private static final String REST_URL = EmployeesController.REST_URL + '/';

    @Autowired
    private EmployeesService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + EMPLOYEE1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(EMPLOYEE1));
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
        mockMvc.perform(delete(REST_URL + EMPLOYEE1_ID))
                .andDo(print())
                .andExpect(status().isOk());
        Assert.assertEquals(service.getAll().size(), 16);
    }


    @Test
    @Sql(
            scripts = "classpath:/db/populateDB.sql",
            executionPhase = AFTER_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED)
    )
    public void testCreate() throws Exception {
        Employee employee = new Employee(null, "ФИО нового сотрудника", DEPARTMENT1, false);

        ResultActions action = mockMvc.perform(post(REST_URL + DEPARTMENT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", employee.getName())
                .param("depId", Integer.toString(employee.getDepartment().getId()))
                .param("statusWork", Boolean.toString(employee.getStatusWork())))
                .andExpect(status().isOk());

       /* Employee returned = TestUtil.readFromJson(action, Employee.class);
        returned.setId(returned.getId());*/

        Assert.assertEquals(service.getAll().size(), 18);
    }


    @Test
    @Sql(
            scripts = "classpath:/db/populateDB.sql",
            executionPhase = AFTER_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED)
    )
    public void testUpdate() throws Exception {
        Employee employee = EMPLOYEE1;
        employee.setName("Измененное ФИО сотрудника");
        ResultActions action = mockMvc.perform(post(REST_URL + EMPLOYEE1.getDepartment().getId())
                .param("id", Integer.toString(employee.getId()))
                .param("name", employee.getName())
                .param("depId", Integer.toString(employee.getDepartment().getId()))
                .param("statusWork", Boolean.toString(employee.getStatusWork())))
                .andExpect(status().isOk());

        assertMatch(service.get(EMPLOYEE1_ID), employee);
    }


    @Test
    public void getAllOrders() throws Exception {
        this.mockMvc.perform(get(REST_URL)).andDo(print()).andExpect(status().isOk()).andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Assert.assertEquals(service.getAll().size(), 17);
    }

}