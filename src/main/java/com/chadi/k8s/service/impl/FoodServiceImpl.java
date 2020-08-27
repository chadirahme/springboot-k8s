package com.chadi.k8s.service.impl;

import com.chadi.k8s.model.Food;
import com.chadi.k8s.model.FoodResult;
import com.chadi.k8s.model.Rate;
import com.chadi.k8s.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    Logger logger = LoggerFactory.getLogger(FoodServiceImpl.class);
    //private static final String MANGA_SEARCH_URL="https://api.fda.gov/food/enforcement.json?limit=10";
    private static final String MANGA_SEARCH_URL="https://bitpay.com/api/rates";

    @Autowired
    RestTemplate restTemplate;


    @Override
    public List<Food> getFoods(int limit) {
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(MANGA_SEARCH_URL, Object[].class);
        return restTemplate.getForEntity(MANGA_SEARCH_URL, FoodResult.class).getBody().getResult();
    }


    @Override
    public List<Rate> getRates(int limit) {

       // ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("https://bitpay.com/api/rates", Object[].class);
       // return restTemplate.getForEntity(MANGA_SEARCH_URL, FoodResult.class).getBody().getRates();



        ResponseEntity<List<Rate>> rateResponse =
                restTemplate.exchange("https://bitpay.com/api/rates",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Rate>>() {
                        });
        List<Rate> rates = rateResponse.getBody();
        return rates;

        // ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(MANGA_SEARCH_URL, Object[].class);
        //return restTemplate.getForEntity(MANGA_SEARCH_URL, FoodResult.class).getBody().getResult();
    }

    public Rate getRate(String id) {
        ResponseEntity<Rate> resp =
                restTemplate.getForEntity("https://bitpay.com/api/rates", Rate.class);

        return resp.getStatusCode() == HttpStatus.OK ? resp.getBody() : null;
    }


}
