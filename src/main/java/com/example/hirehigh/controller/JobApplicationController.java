package com.example.hirehigh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hirehigh.entity.JobApplication;
import com.example.hirehigh.service.JobApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hirehigh")
public class JobApplicationController {

    @Autowired
    private JobApplicationService service;


    @PostMapping("/applications")
    public ResponseEntity<JobApplication> save(@Valid @RequestBody JobApplication application) {
        JobApplication saved = service.saveApplication(application);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    
    @GetMapping("/applications")
    public List<JobApplication> getAll() {
        return service.getAllApplications();
    }


    @GetMapping("/applications/{id}")
    public JobApplication getById(@PathVariable Long id) {
        return service.getApplicationById(id);
    }


    @PutMapping("/applications/{id}")
    public JobApplication update(@PathVariable Long id,
                                 @RequestBody JobApplication application) {
        return service.updateApplication(id, application);
    }


    @PatchMapping("/applications/{id}")
    public JobApplication patch(@PathVariable Long id,
                                @RequestBody JobApplication application) {
        return service.patchApplication(id, application);
    }

    
    @DeleteMapping("/applications/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteApplication(id);
        return "Deleted successfully";
    }
}
