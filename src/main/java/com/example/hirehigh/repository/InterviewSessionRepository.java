package com.example.hirehigh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hirehigh.entity.InterviewSession;

@Repository
public interface InterviewSessionRepository extends JpaRepository<InterviewSession, Long> {

}
