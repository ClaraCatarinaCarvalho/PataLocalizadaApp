package com.clara.patalocalizada.models.ApiInfoResponse;

public class ApiInfoResponse {
    /*

            {
                "success": true,
                "status": "success",
                "message": "API is working",
                "data": {
                    "name": "My mydev.loginsystem.com",
                    "version": "1.0.0",
                    "description": "this is a simple API app example"
                }
            }
         */
    private boolean success;
    private String status;
    private String message;
    private ApiData data;

    public ApiData getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}