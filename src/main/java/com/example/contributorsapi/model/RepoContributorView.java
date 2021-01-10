package com.example.contributorsapi.model;

import lombok.Data;

@Data
public class RepoContributorView {
    private String login;
    private Long contributions;

}
