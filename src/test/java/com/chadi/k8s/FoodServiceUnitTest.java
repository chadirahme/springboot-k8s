package com.chadi.k8s;


import com.chadi.k8s.model.Rate;
import com.chadi.k8s.service.FoodService;
import com.chadi.k8s.service.impl.FoodServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
public class FoodServiceUnitTest {

    @InjectMocks
    private FoodService foodService=new FoodServiceImpl();

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testGetMangasByTitle() throws IOException {
        // Parsing mock file
       // MangaResult mRs = JsonUtils.jsonFile2Object("ken.json", MangaResult.class);

        List<Rate> lst=new ArrayList<>();
        Rate rate=new Rate();
        rate.setName("one");
        lst.add(rate);

        // Mocking remote service
       // when(template.getForEntity(any(String.class), any(Class.class))).thenReturn(new ResponseEntity(lst, HttpStatus.OK));
        //when(restTemplate.exchange(any(String.class), HttpMethod.GET).thenReturn(new ResponseEntity(lst, HttpStatus.OK));

       when(restTemplate.exchange(
               ArgumentMatchers.anyString(),
               ArgumentMatchers.any(HttpMethod.class),
               ArgumentMatchers.any(),
               ArgumentMatchers.<Class<String>>any())
             //  ArgumentMatchers.<Class<String>>anyList()
        ).thenReturn(new ResponseEntity(lst, HttpStatus.OK));



        // I search for goku but system will use mocked response containing only ken, so I can check that mock is used.
        List<Rate> mangasByTitle = foodService.getRates(10);
        assertThat(mangasByTitle).isNotNull()
                .isNotEmpty()
                .allMatch(p -> p.getName()
                        .toLowerCase()
                        .contains("ken"));
    }

    @Test
    public void test() {
        Rate rate=new Rate();
        rate.setName("one");
        when(restTemplate.getForEntity(
                ArgumentMatchers.anyString(),  ArgumentMatchers.<Class<String>>any()))
          .thenReturn(new ResponseEntity(rate, HttpStatus.OK));

        Rate employee = foodService.getRate("1");
        Assert.assertEquals(rate, employee);
    }

}
