package com.example.contributorsapi.model;

import java.util.List;

public class ContributorList {
    private List<ContributorsResponse> list;

    public List<ContributorsResponse> getList() {
        return list;
    }

    public void setList(List<ContributorsResponse> list) {
        this.list = list;
    }
}
