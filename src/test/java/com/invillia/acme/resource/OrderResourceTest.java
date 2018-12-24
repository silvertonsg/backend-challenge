package com.invillia.acme.resource;

import com.invillia.acme.InvilliaApplicationTest;
import com.invillia.acme.helper.FixtureHelper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WithMockUser(username = "acmeService", password = "123456")
public class OrderResourceTest extends InvilliaApplicationTest {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void Test1CreateStore() throws Exception {
        final String json = FixtureHelper.readFixture("store/storeCreate.json");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/stores")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void Test2CreateOrder() throws Exception {
        final String json = FixtureHelper.readFixture("order/orderCreate.json");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/orders")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("PENDING"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("MOCK_ORDER_ADDRESS_STREET"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].description").value("MOCK_ORDER_ITEM_1_DESC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].unitPrice").value(2000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].status").value("PENDING"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].quantity").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[1].description").value("MOCK_ORDER_ITEM_2_DESC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[1].unitPrice").value(5000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[1].status").value("PENDING"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[1].quantity").value(1));
    }
}
