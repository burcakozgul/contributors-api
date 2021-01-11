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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContributorsService {

    private final String REPO="repo: ";
    private final String USERNAME=", username: ";
    private final String LOCATION=", location: ";
    private final String COMPANY=", company: ";
    private final String CONTRIBUTOR=", contributor: ";

    @Autowired
    GithubIntegration integration;

    @Value("${list.repository}")
    private String[] listRepos;

    List<Contributor> contributorList = new ArrayList<>();

    public void getRepoContributors() {
        try {
            for (String repo : listRepos) {
                RepoContributorView[] response = integration.getContributors(repo);
                List<RepoContributorView> sortedResponse = sortedRepoContributor(response);
                contributorList = getContributorList(sortedResponse, repo);
            }
            writeToFile(contributorList);
        } catch (Exception e) {
            throw new GitIntegrationException(e.getMessage());
        }
    }

    private List<RepoContributorView> sortedRepoContributor(RepoContributorView[] response) {
        return Arrays.stream(response)
                .sorted((o1, o2) -> o2.getContributions().compareTo(o1.getContributions()))
                .limit(10)
                .collect(Collectors.toList());
    }

    private List<Contributor> getContributorList(List<RepoContributorView> response, String repoName) {
        try {
            for (RepoContributorView repoContributorView : response) {
                Contributor contributor = new Contributor();
                UserView userResponse = getUser(repoContributorView.getLogin());

                contributor.setRepo(repoName);
                contributor.setUsername(repoContributorView.getLogin());
                contributor.setLocation(userResponse.getLocation());
                contributor.setCompany(userResponse.getCompany());
                contributor.setContributions(repoContributorView.getContributions());
                contributorList.add(contributor);
            }
        } catch (Exception e) {
            throw new GitIntegrationException(e.getMessage());
        }
        return contributorList;
    }

    private UserView getUser(String username) {
        return integration.getUser(username);
    }

    private void writeToFile(List<Contributor> contributorList) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("file.txt"));
            for (Contributor contributor : contributorList) {
                pw.print(REPO+ contributor.getRepo());
                pw.print(USERNAME+ contributor.getUsername());
                pw.print(LOCATION+ contributor.getLocation());
                pw.print(COMPANY+ contributor.getCompany());
                pw.print(CONTRIBUTOR+ contributor.getContributions());
                pw.println();
            }
            pw.close();
        } catch (FileNotFoundException e) {
            throw new GitIntegrationException(e.getMessage());
        }
    }


}