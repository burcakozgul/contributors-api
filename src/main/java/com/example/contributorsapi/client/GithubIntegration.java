package com.example.contributorsapi.client;

import com.example.contributorsapi.model.RepoContributorView;
import com.example.contributorsapi.model.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubIntegration {

    @Value("${list.repository.contributors.url}")
    private String listContributorsUrl;

    @Value("${get.user.url}")
    private String getUserUrl;

    @Autowired
    RestTemplate restTemplate;

    public RepoContributorView[] getContributors(String repo) throws HttpClientErrorException {
        String generatedUrl = listContributorsUrl + repo + "/contributors";
        return restTemplate.getForObject(generatedUrl, RepoContributorView[].class);
    }

    public UserView getUser(String username) throws HttpClientErrorException {
        String generatedUrl = getUserUrl + username;
        return restTemplate.getForObject(generatedUrl, UserView.class);
    }

}
