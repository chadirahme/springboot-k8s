package com.chadi.k8s.service;


import com.chadi.k8s.model.Food;
import com.chadi.k8s.model.Rate;

import java.util.List;

public interface FoodService {
     List<Food> getFoods(int limit) ;
     List<Rate> getRates(int limit) ;

     Rate getRate(String id);
}
