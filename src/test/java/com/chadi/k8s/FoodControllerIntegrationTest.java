package com.chadi.k8s;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.chadi.k8s.controller.FoodController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class FoodControllerIntegrationTest {

     @Autowired
    MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;
    @Autowired
    FoodController foodController;
    @Before
    public void setup() throws Exception {
        //use this of we dont add @AutoConfigureMockMvc and   @Autowired to MockMvc
      //  this.mockMvc = standaloneSetup(this.foodController).build();// Standalone context


        // mockMvc = MockMvcBuilders.webAppContextSetup(wac)
        // .build();
    }
    @Test
    public void testSearchSync() throws Exception {
        mockMvc.perform(get("/food/sync/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*.name", hasItem(is("Bitcoin"))));
    }

}
