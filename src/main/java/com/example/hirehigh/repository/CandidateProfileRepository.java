package com.example.hirehigh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hirehigh.entity.CandidateProfile;

@Repository
public interface CandidateProfileRepository
        extends JpaRepository<CandidateProfile, Long> {

}