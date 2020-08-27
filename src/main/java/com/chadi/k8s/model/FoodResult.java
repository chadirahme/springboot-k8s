package com.chadi.k8s.model;

import lombok.Data;

import java.util.List;

@Data
public class FoodResult {

    private List<Food> result;
    private List<Rate> rates;
}
