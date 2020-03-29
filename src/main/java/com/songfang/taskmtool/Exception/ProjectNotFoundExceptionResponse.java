package com.songfang.taskmtool.Exception;

public class ProjectNotFoundExceptionResponse {
    //Field
    private String ProjectNotFound;

    //Constructor
    public ProjectNotFoundExceptionResponse(String projectNotFound) {
        ProjectNotFound = projectNotFound;
    }

    public String getProjectNotFound() {
        return ProjectNotFound;
    }

    public void setProjectNotFound(String projectNotFound) {
        ProjectNotFound = projectNotFound;
    }
}
