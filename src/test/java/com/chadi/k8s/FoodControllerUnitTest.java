package com.chadi.k8s;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import java.util.ArrayList;
import java.util.List;

import com.chadi.k8s.controller.FoodController;
import com.chadi.k8s.model.Rate;
import com.chadi.k8s.service.FoodService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;



@SpringBootTest
@RunWith(SpringRunner.class)
public class FoodControllerUnitTest {

    MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;
    @Autowired
    FoodController foodController;
    @MockBean
    FoodService foodService;

    private List<Rate> rates;
    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.foodController).build();// Standalone context
        // mockMvc = MockMvcBuilders.webAppContextSetup(wac)
        // .build();

        rates = new ArrayList<>();

        Rate rate=new Rate();
        rate.setName("one");
        rates.add(rate);
        rate=new Rate();
        rate.setName("two");
        rates.add(rate);
    }

    @Test
    public void testSearchSync() throws Exception {
        // Mocking service
        when(foodService.getRates(any(Integer.class))).thenReturn(rates);

        mockMvc.perform(get("/food/sync/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("one")))
                .andExpect(jsonPath("$[1].name", is("two")));
    }


    @Test
    public void testSearchASync() throws Exception {
        // Mocking service
        when(foodService.getRates(any(Integer.class))).thenReturn(rates);
        MvcResult result = mockMvc.perform(get("/food/async/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(request().asyncStarted())
                .andDo(print())
                // .andExpect(status().is2xxSuccessful()).andReturn();
                .andReturn();
        // result.getRequest().getAsyncContext().setTimeout(10000);
        mockMvc.perform(asyncDispatch(result))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("one")));
    }

}
