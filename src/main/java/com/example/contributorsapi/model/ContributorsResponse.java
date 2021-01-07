package com.example.contributorsapi.model;

public class ContributorsResponse {
    private String login;
    private Long id;
    private String repos_url;
    private Long contributions;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public Long getContributions() {
        return contributions;
    }

    public void setContributions(Long contributions) {
        this.contributions = contributions;
    }
}
