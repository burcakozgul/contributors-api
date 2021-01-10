package com.example.contributorsapi.model;

import lombok.Data;

@Data
public class Contributor {
    private String repo;
    private String username;
    private String location;
    private String company;
    private Long contributions;

}
