package com.example.contributorsapi.service;

import com.example.contributorsapi.client.GithubIntegration;
import com.example.contributorsapi.model.ContributorsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContributorsService {

    @Autowired
    GithubIntegration integration;

    public void getContributors(String repo) {
        integration.getContributors(repo);
        //repodaki en çok commit atan 10 kişi response'daki ilk 10 kişiyi tut.
        //dosyaya yazdırma işlemi
    }

    //

}
