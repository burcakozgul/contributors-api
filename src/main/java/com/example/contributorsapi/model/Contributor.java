package com.example.contributorsapi.model;

public class Contributor {
    private String repo;
    private String username;
    private String location;
    private String company;
    private Long contributions;

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getContributions() {
        return contributions;
    }

    public void setContributions(Long contributions) {
        this.contributions = contributions;
    }
}
