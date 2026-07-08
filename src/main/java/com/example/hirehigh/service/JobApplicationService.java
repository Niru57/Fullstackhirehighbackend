package com.example.hirehigh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hirehigh.entity.JobApplication;
import com.example.hirehigh.exception.ResourceNotFoundException;
import com.example.hirehigh.repository.JobApplicationRepository;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository repository;


    public JobApplication saveApplication(JobApplication application) {
        return repository.save(application);
    }


    public List<JobApplication> getAllApplications() {
        return repository.findAll();
    }

    
    public JobApplication getApplicationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application not found with id: " + id));
    }


    public JobApplication updateApplication(Long id, JobApplication application) {

        JobApplication existing = getApplicationById(id);
        existing.setCandidate(application.getCandidate());
        existing.setJob(application.getJob());
        existing.setCurrentStage(application.getCurrentStage());
        existing.setAppliedAt(application.getAppliedAt());

        return repository.save(existing);
    }

    
    public JobApplication patchApplication(Long id, JobApplication application) {

        JobApplication existing = getApplicationById(id);
        if (application.getCandidate() != null) {
    existing.setCandidate(application.getCandidate());
}

        if (application.getJob() != null) {
            existing.setJob(application.getJob());
        }

        if (application.getCurrentStage() != null) {
            existing.setCurrentStage(application.getCurrentStage());
        }

        if (application.getAppliedAt() != null) {
            existing.setAppliedAt(application.getAppliedAt());
        }

        return repository.save(existing);
    }


    public void deleteApplication(Long id) {
        repository.deleteById(id);
    }
}