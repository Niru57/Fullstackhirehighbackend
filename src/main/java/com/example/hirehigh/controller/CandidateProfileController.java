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

import com.example.hirehigh.entity.CandidateProfile;
import com.example.hirehigh.service.CandidateProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hirehigh")
public class CandidateProfileController {

    @Autowired
    private CandidateProfileService service;

    @PostMapping("/candidates")
    public ResponseEntity<CandidateProfile> save(
            @Valid @RequestBody CandidateProfile candidate) {

        CandidateProfile saved =
                service.saveCandidate(candidate);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/candidates")
    public List<CandidateProfile> getAll() {
        return service.getAllCandidates();
    }

    @GetMapping("/candidates/{id}")
    public CandidateProfile getById(@PathVariable Long id) {
        return service.getCandidateById(id);
    }

    @PutMapping("/candidates/{id}")
    public CandidateProfile update(
            @PathVariable Long id,
            @RequestBody CandidateProfile candidate) {

        return service.updateCandidate(id, candidate);
    }

    @PatchMapping("/candidates/{id}")
    public CandidateProfile patch(
            @PathVariable Long id,
            @RequestBody CandidateProfile candidate) {

        return service.patchCandidate(id, candidate);
    }

    @DeleteMapping("/candidates/{id}")
    public String delete(@PathVariable Long id) {

        service.deleteCandidate(id);

        return "Candidate deleted successfully";
    }
}
