package com.example.contributorsapi.client;

import com.example.contributorsapi.model.ContributorsView;
import com.example.contributorsapi.model.UserView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubIntegration {

    @Value("${list.repository.contributors.url}")
    private String listContributorsUrl;

    @Value("${get.user.url}")
    private String getUserUrl;

    RestTemplate restTemplate = new RestTemplate();

    public ContributorsView[] getContributors(String repo) {
        String generatedUrl= listContributorsUrl +repo+"/contributors";
        ContributorsView[] response = restTemplate.getForObject(generatedUrl, ContributorsView[].class);
        return response;
    }

    public UserView getUser(String username) {
        String generatedUrl=getUserUrl+username;
        UserView response = restTemplate.getForObject(generatedUrl,UserView.class);
        return response;
    }
}
