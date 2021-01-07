package com.example.contributorsapi.client;

import com.example.contributorsapi.model.ContributorList;
import com.example.contributorsapi.model.ContributorsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class GithubIntegration {

    @Value("${list.repository.contributors.url}")
    private String listContributors;

    RestTemplate restTemplate = new RestTemplate();

    //resttemplate kullanılacak
    public ResponseEntity<ContributorList> getContributors(String repo) {
        String generatedUrl= listContributors+repo+"/contributors";
        HttpEntity entity = new HttpEntity(new HttpHeaders());
        ResponseEntity<List<ContributorsResponse>> response = restTemplate
                .exchange(generatedUrl, HttpMethod.GET,entity,ContributorList.class);
        return response;
    }
}
