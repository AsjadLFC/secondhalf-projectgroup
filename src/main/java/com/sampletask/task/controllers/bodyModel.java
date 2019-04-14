package com.sampletask.task.controllers;

public class bodyModel {
    private String service;
    private String query;

    public bodyModel(String service, String query){
        this.service = service;
        this.query = query;
    }

    public String getService(){
        return service;
    }
    public String getQuery(){
        return query;
    }
}
