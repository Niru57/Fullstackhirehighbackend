package com.example.hirehigh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hirehigh.entity.CandidateProfile;
import com.example.hirehigh.exception.ResourceNotFoundException;
import com.example.hirehigh.repository.CandidateProfileRepository;

@Service
public class CandidateProfileService {

    @Autowired
    private CandidateProfileRepository repository;

    // CREATE
    public CandidateProfile saveCandidate(CandidateProfile candidate) {
        return repository.save(candidate);
    }

    // GET ALL
    public List<CandidateProfile> getAllCandidates() {
        return repository.findAll();
    }

    // GET BY ID
    public CandidateProfile getCandidateById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Candidate not found with id: " + id));
    }

    // PUT
    public CandidateProfile updateCandidate(Long id,
                                            CandidateProfile candidate) {

        CandidateProfile existing = getCandidateById(id);

        existing.setFullName(candidate.getFullName());
        existing.setEmail(candidate.getEmail());
        existing.setPhone(candidate.getPhone());
        existing.setSkills(candidate.getSkills());
        existing.setExperience(candidate.getExperience());

        return repository.save(existing);
    }

    // PATCH
    public CandidateProfile patchCandidate(Long id,
                                           CandidateProfile candidate) {

        CandidateProfile existing = getCandidateById(id);

        if (candidate.getFullName() != null) {
            existing.setFullName(candidate.getFullName());
        }

        if (candidate.getEmail() != null) {
            existing.setEmail(candidate.getEmail());
        }

        if (candidate.getPhone() != null) {
            existing.setPhone(candidate.getPhone());
        }

        if (candidate.getSkills() != null) {
            existing.setSkills(candidate.getSkills());
        }

        if (candidate.getExperience() != null) {
            existing.setExperience(candidate.getExperience());
        }

        return repository.save(existing);
    }

    // DELETE
    public void deleteCandidate(Long id) {
        repository.deleteById(id);
    }
}