package com.example.contributorsapi.service;

import com.example.contributorsapi.client.GithubIntegration;
import com.example.contributorsapi.exception.GitIntegrationException;
import com.example.contributorsapi.model.Contributor;
import com.example.contributorsapi.model.RepoContributorView;
import com.example.contributorsapi.model.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContributorsService {

    @Autowired
    GithubIntegration integration;

    @Value("${list.repository}")
    private String[] listRepos;

    List<Contributor> contributorList = new ArrayList<>();

    public void getRepoContributors() {
        try {
            for (String repo : listRepos) {
                RepoContributorView[] response = integration.getContributors(repo);
                contributorList = getContributorList(response, repo);
            }
            writeToFile(contributorList);
        } catch (RuntimeException e) {
            throw new GitIntegrationException(e.getMessage());
        }
    }

    private List<Contributor> getContributorList(RepoContributorView[] response, String repoName) {
        try {
            for (int i = 0; i < 10; i++) {
                Contributor contributor = new Contributor();
                contributor.setRepo(repoName);
                contributor.setUsername(response[i].getLogin());
                contributor.setContributions(response[i].getContributions());
                UserView userResponse = integration.getUser(contributor.getUsername());
                contributor.setCompany(userResponse.getCompany());
                contributor.setLocation(userResponse.getLocation());
                contributorList.add(contributor);
            }
        } catch (RuntimeException e) {
            throw new GitIntegrationException(e.getMessage());
        }
        return contributorList;
    }

    private void writeToFile(List<Contributor> contributorList) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("file.txt"));
            for (Contributor contributor : contributorList) {
                pw.print("repo: " + contributor.getRepo());
                pw.print(", username: " + contributor.getUsername());
                pw.print(", location: " + contributor.getLocation());
                pw.print(", company: " + contributor.getCompany());
                pw.print(", contributor: " + contributor.getContributions());
                pw.println();
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


}