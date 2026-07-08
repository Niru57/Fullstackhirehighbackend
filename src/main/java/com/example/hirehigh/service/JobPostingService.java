package com.example.hirehigh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hirehigh.entity.JobPosting;
import com.example.hirehigh.exception.ResourceNotFoundException;
import com.example.hirehigh.repository.JobPostingRepository;

@Service
public class JobPostingService {

    @Autowired
    private JobPostingRepository repository;

    // Create Job
    public JobPosting saveJob(JobPosting job) {
        return repository.save(job);
    }

    
    public List<JobPosting> getAllJobs() {
        return repository.findAll();
    }

    
    public JobPosting getJobById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found with id: " + id));
    }

    
    public JobPosting updateJob(Long id, JobPosting job) {

        JobPosting jobData = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found with id: " + id));

        jobData.setTitle(job.getTitle());
        jobData.setDepartment(job.getDepartment());
        jobData.setDescription(job.getDescription());
        jobData.setHiringGoal(job.getHiringGoal());
        jobData.setCurrentFills(job.getCurrentFills());
        jobData.setStatus(job.getStatus());

        return repository.save(jobData);
    }

    
    public void deleteJob(Long id) {

        repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found with id: " + id));

        repository.deleteById(id);
    }

    
    public JobPosting patchJob(Long id, JobPosting job) {

        JobPosting jobData = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found with id: " + id));

        if (job.getTitle() != null) {
            jobData.setTitle(job.getTitle());
        }

        if (job.getDepartment() != null) {
            jobData.setDepartment(job.getDepartment());
        }

        if (job.getDescription() != null) {
            jobData.setDescription(job.getDescription());
        }

        if (job.getHiringGoal() != null) {
            jobData.setHiringGoal(job.getHiringGoal());
        }

        if (job.getCurrentFills() != null) {
            jobData.setCurrentFills(job.getCurrentFills());
        }

        if (job.getStatus() != null) {
            jobData.setStatus(job.getStatus());
        }

        return repository.save(jobData);
    }
}