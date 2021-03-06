package com.songfang.taskmtool.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //CascadeType.ALL means that if we delete the project,
    // the backlog will be deleted as well.
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "project")
    @JsonIgnore
    private Backlog backlog;

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getprojectIdentifier() {
        return projectIdentifier;
    }

    public void setprojectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    @NotBlank(message = "project name is required")
    private String projectName;

    @NotBlank(message = "project identifier is required")
    @Size(min = 4,max = 5,message = "Please use 4 to 5 Characters")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;

    @NotBlank(message = "description is required")
    private String description;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date start_date;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date end_date;

    private Date created_At;
    private Date updated_At;

    public Project(){

    }


    @PrePersist
    public void onCreate(){
        this.created_At= new Date();
    }

    @PreUpdate
    public void onUpdate(){
        this.updated_At = new Date();
    }

}
