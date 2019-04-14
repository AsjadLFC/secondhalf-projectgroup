package com.sampletask.task.controllers;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.Model;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
public class Construct {

    private StringBuffer stringBuffer = new StringBuffer();

    @PostMapping(value = "/query", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String query(bodyModel requestBody){
        String tempQuery = requestBody.getQuery();
        String tempService = requestBody.getService();
        Query query = QueryFactory.create(tempQuery);
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(tempService, query);
        Model results = queryExecution.execConstruct();
        store(results);
        return "done";
    }

    public void store(Model model){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        model.write(byteArrayOutputStream, "NTRIPLES");
        stringBuffer.append(byteArrayOutputStream.toString());
    }

    @GetMapping("/fetch-all")
    public String fetchData(){
        String temp = stringBuffer.toString();
        return temp;
    }
}