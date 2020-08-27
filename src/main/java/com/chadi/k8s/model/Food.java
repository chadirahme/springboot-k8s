package com.chadi.k8s.model;

import lombok.Data;

@Data
public class Food {

    private String country;
    private String city;
    private String reason_for_recall;
}
