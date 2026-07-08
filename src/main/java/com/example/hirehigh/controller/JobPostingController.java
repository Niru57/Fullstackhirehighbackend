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

import com.example.hirehigh.entity.JobPosting;
import com.example.hirehigh.service.JobPostingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hirehigh")
public class JobPostingController {

    @Autowired
    private JobPostingService service;

    // Create Job
    @PostMapping("/jobs")
    public ResponseEntity<JobPosting> saveJob(@Valid @RequestBody JobPosting job) {

        JobPosting savedJob = service.saveJob(job);

        return new ResponseEntity<>(savedJob, HttpStatus.CREATED);
    }

    
    @GetMapping("/jobs")
    public ResponseEntity<List<JobPosting>> getAllJobs() {

        List<JobPosting> jobs = service.getAllJobs();

        return ResponseEntity.ok(jobs);
    }


    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobPosting> getJobById(@PathVariable Long id) {

        JobPosting job = service.getJobById(id);

        return ResponseEntity.ok(job);
    }

    
    @PutMapping("/jobs/{id}")
    public ResponseEntity<JobPosting> updateJob(
            @PathVariable Long id,
            @Valid @RequestBody JobPosting job) {

        JobPosting updatedJob = service.updateJob(id, job);

        return ResponseEntity.ok(updatedJob);
    }

    
    @PatchMapping("/jobs/{id}")
    public ResponseEntity<JobPosting> patchJob(
            @PathVariable Long id,
            @RequestBody JobPosting job) {

        JobPosting patchedJob = service.patchJob(id, job);

        return ResponseEntity.ok(patchedJob);
    }


    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {

        service.deleteJob(id);

        return ResponseEntity.ok("Job deleted successfully. ID = " + id);
    }
}