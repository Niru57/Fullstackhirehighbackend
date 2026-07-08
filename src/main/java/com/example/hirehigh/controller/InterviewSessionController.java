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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.hirehigh.entity.InterviewSession;
import com.example.hirehigh.service.InterviewSessionService;

@RestController
@RequestMapping("/hirehigh")
public class InterviewSessionController {

    @Autowired
    private InterviewSessionService service;

    // CREATE
    @PostMapping("/interviews")
    @ResponseStatus(HttpStatus.CREATED)
    public InterviewSession saveInterview(@RequestBody InterviewSession interview) {
        System.out.println("INSIDE SAVE INTERVIEW");

        return service.saveInterview(interview);
    }

    // GET ALL
    @GetMapping("/interviews")
    public List<InterviewSession> getAllInterviews() {
        return service.getAllInterviews();
    }

    // GET BY ID
    @GetMapping("/interviews/{id}")
    public InterviewSession getInterviewById(@PathVariable Long id) {
        return service.getInterviewById(id);
    }

    // UPDATE
    @PutMapping("/interviews/{id}")
    public InterviewSession updateInterview(@PathVariable Long id,
                                            @RequestBody InterviewSession interview) {
        return service.updateInterview(id, interview);
    }

    // PATCH
    @PatchMapping("/interviews/{id}")
    public InterviewSession patchInterview(@PathVariable Long id,
                                           @RequestBody InterviewSession interview) {
        return service.patchInterview(id, interview);
    }

    // DELETE
    @DeleteMapping("/interviews/{id}")
    public ResponseEntity<String> deleteInterview(@PathVariable Long id) {
        service.deleteInterview(id);
        return ResponseEntity.ok("Interview deleted successfully");
    }

}