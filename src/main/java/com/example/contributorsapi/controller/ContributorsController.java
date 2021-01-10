package com.example.contributorsapi.controller;

import com.example.contributorsapi.service.ContributorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ContributorsController {

    @Autowired
    private ContributorsService service;

    @GetMapping()
    public ResponseEntity<String> getContributors() {
        service.getRepoContributors();
        return ResponseEntity.ok("Write to text successful");
    }
}
