package com.chadi.k8s;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import com.chadi.k8s.model.Rate;
import com.chadi.k8s.service.FoodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodServiceIntegrationTest {

    @Autowired
    private FoodService foodService;
    @Test
    public void testGetMangasByTitle() {
        List<Rate> rates = foodService.getRates(1);
        assertThat(rates).isNotNull().isNotEmpty();
        assertThat(rates.size()).isEqualTo(167);
        assertThat(rates.get(0).getName()).isEqualTo("Bitcoin");
    }


}
