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
public class StoreResourceTest extends InvilliaApplicationTest {
    
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
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("MOCK_STORE_NAME"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("MOCK_STORE_ADDRESS_STREET"));
    }

    @Test
    public void Test2GetStore() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/stores/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("MOCK_STORE_NAME"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("MOCK_STORE_ADDRESS_STREET"));
    }

    @Test
    public void Test3UpdateStore() throws Exception {
        final String json = FixtureHelper.readFixture("store/storeUpdate.json");

        this.mockMvc.perform(MockMvcRequestBuilders.put("/v1/stores/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("MOCK_STORE_NAME_2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("MOCK_STORE_ADDRESS_STREET_2"));
    }

    @Test
    public void Test4GetStoreUpdated() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/stores/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("MOCK_STORE_NAME_2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("MOCK_STORE_ADDRESS_STREET_2"));
    }

    @Test
    public void Test5GetStoreNotFound() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/stores/99")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void Test6UpdateStoreNotFound() throws Exception {
        final String json = FixtureHelper.readFixture("store/storeUpdate.json");

        this.mockMvc.perform(MockMvcRequestBuilders.put("/v1/stores/87")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }



}
