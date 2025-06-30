package com.clara.patalocalizada.models.ApiInfoResponse;

public class ApiData {
    /*
        "data": {
                 "name": "My mydev.loginsystem.com",
                 "version": "1.0.0",
                 "description": "this is a simple API app example"
                }
         */
    private String name;
    private String version;
    private String description;

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getdescription() {
        return description;
    }

}