package com.chadi.k8s.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Rate {
    private String name;
    private String code;
    private Double rate;
    // add getters and setters
}