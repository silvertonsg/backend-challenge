package com.invillia.acme.resource;

import com.invillia.acme.InvilliaApplicationTest;
import com.invillia.acme.helper.FixtureHelper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaymentResourceTest extends InvilliaApplicationTest {

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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }


    @Test
    public void Test2CreateOrder() throws Exception {
        final String json = FixtureHelper.readFixture("order/orderCreate.json");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/orders")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));

    }

    @Test
    public void Test3CreatePayment() throws Exception {
        final String json = FixtureHelper.readFixture("payment/paymentCreate.json");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/payments")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.creditCardNumber").value("MOCK_CREDIT_CARD_NUMBER"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("PAGO"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(1));

    }
}
