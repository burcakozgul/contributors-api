package com.example.contributorsapi.model;

public class RepoContributorView {
    private String login;
    private Long contributions;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getContributions() {
        return contributions;
    }

    public void setContributions(Long contributions) {
        this.contributions = contributions;
    }
}
