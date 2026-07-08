package com.example.hirehigh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hirehigh.entity.InterviewSession;
import com.example.hirehigh.exception.ResourceNotFoundException;
import com.example.hirehigh.repository.InterviewSessionRepository;

@Service
public class InterviewSessionService {

    @Autowired
    private InterviewSessionRepository repository;


    public InterviewSession saveInterview(InterviewSession interview) {
        return repository.save(interview);
    }

    
    public List<InterviewSession> getAllInterviews() {
        return repository.findAll();
    }


    public InterviewSession getInterviewById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Interview not found with id: " + id));
    }
    public InterviewSession updateInterview(Long id, InterviewSession interview) {

        InterviewSession interviewData = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Interview not found with id: " + id));

        interviewData.setApplicationId(interview.getApplicationId());
        interviewData.setInterviewerId(interview.getInterviewerId());
        interviewData.setScheduledAt(interview.getScheduledAt());
        interviewData.setFeedback(interview.getFeedback());
        interviewData.setRating(interview.getRating());

        return repository.save(interviewData);
    }


    public void deleteInterview(Long id) {

        repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Interview not found with id: " + id));

        repository.deleteById(id);
    }


    public InterviewSession patchInterview(Long id, InterviewSession interview) {

        InterviewSession interviewData = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Interview not found with id: " + id));

        if (interview.getApplicationId() != null) {
            interviewData.setApplicationId(interview.getApplicationId());
        }

        if (interview.getInterviewerId() != null) {
            interviewData.setInterviewerId(interview.getInterviewerId());
        }

        if (interview.getScheduledAt() != null) {
            interviewData.setScheduledAt(interview.getScheduledAt());
        }

        if (interview.getFeedback() != null) {
            interviewData.setFeedback(interview.getFeedback());
        }

        if (interview.getRating() != null) {
            interviewData.setRating(interview.getRating());
        }

        return repository.save(interviewData);
    }
}