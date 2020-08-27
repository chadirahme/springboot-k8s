package com.chadi.k8s.controller;


import com.chadi.k8s.model.Food;
import com.chadi.k8s.model.Rate;
import com.chadi.k8s.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/food")
public class FoodController {

    //https://dzone.com/articles/unit-and-integration-tests-in-spring-boot-2

    @Autowired
    private FoodService foodService;

    @GetMapping("/sync/{limit}")
    public List<Rate> searchSync(@PathVariable(name = "limit") int limit) {
        return foodService.getRates(limit);
       // return foodService.getFoods(limit);
    }

    @RequestMapping(value = "/async/{limit}", method = RequestMethod.GET)
    @Async
    public CompletableFuture<List<Rate>> searchASync(@PathVariable(name = "limit") int limit) {
        return CompletableFuture.completedFuture(foodService.getRates(limit));
    }

}

