package com.example.hirehigh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "job_posting")
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Job title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Department is required")
    @Column(nullable = false)
    private String department;

    @NotBlank(message = "Description is required")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Hiring goal is required")
    @Min(value = 1, message = "Hiring goal must be at least 1")
    @Column(name = "hiring_goal")
    private Integer hiringGoal;

    @NotNull(message = "Current fills is required")
    @Min(value = 0, message = "Current fills cannot be negative")
    @Column(name = "current_fills")
    private Integer currentFills;

    @NotBlank(message = "Status is required")
    @Column(nullable = false)
    private String status;

    // Default Constructor
    public JobPosting() {
    }

    // Parameterized Constructor
    public JobPosting(Long id, String title, String department,
                      String description, Integer hiringGoal,
                      Integer currentFills, String status) {
        this.id = id;
        this.title = title;
        this.department = department;
        this.description = description;
        this.hiringGoal = hiringGoal;
        this.currentFills = currentFills;
        this.status = status;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHiringGoal() {
        return hiringGoal;
    }

    public void setHiringGoal(Integer hiringGoal) {
        this.hiringGoal = hiringGoal;
    }

    public Integer getCurrentFills() {
        return currentFills;
    }

    public void setCurrentFills(Integer currentFills) {
        this.currentFills = currentFills;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}