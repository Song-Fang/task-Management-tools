package com.songfang.taskmtool.Controller;

import com.songfang.taskmtool.Domain.Project;
import com.songfang.taskmtool.Services.MapInvalidationErrors;
import com.songfang.taskmtool.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class Controller {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapInvalidationErrors mapInvalidationErrors;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){

        ResponseEntity<?> errMap = mapInvalidationErrors.mapInvalidation(result);
        if(errMap!=null){
            return errMap;
        }
        Project project1 =  projectService.saveOrUpdate(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){
        Project project = projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        Iterable<Project> projects = projectService.findAllProject();
        return new ResponseEntity<Iterable<Project>>(projects,HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProject(projectId);
        return new ResponseEntity<String>("Project with ID '"+projectId+"' has been deleted!",HttpStatus.OK);
    }

}
