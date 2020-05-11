package com.songfang.taskmtool.Controller;


import com.songfang.taskmtool.Domain.ProjectTask;
import com.songfang.taskmtool.Services.MapInvalidationErrors;

import com.songfang.taskmtool.Services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {
    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private MapInvalidationErrors mapInvalidationErrors;


    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addPTToBacklog(@Valid @RequestBody ProjectTask projectTask, BindingResult result,@PathVariable String backlog_id){
        ResponseEntity<?> errMap = mapInvalidationErrors.mapInvalidation(result);
        if(errMap!=null){
            return errMap;
        }

        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id,projectTask);
        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public ResponseEntity<?> getBacklogs(@PathVariable String backlog_id){
        Iterable<ProjectTask> projectTasks = projectTaskService.findAllProjectTasks(backlog_id);
        return new ResponseEntity<Iterable<ProjectTask>>(projectTasks,HttpStatus.OK);
    }

    @GetMapping("/{backlog_id}/{project_sequence}")
    public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id,@PathVariable String project_sequence){
        ProjectTask projectTask = projectTaskService.findProjectTask(backlog_id,project_sequence);
        return new ResponseEntity<ProjectTask>(projectTask,HttpStatus.OK);
    }

    @PatchMapping("/{backlog_id}/{project_sequence}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask,BindingResult result,@PathVariable String backlog_id, @PathVariable String project_sequence){
        ResponseEntity<?> errMap = mapInvalidationErrors.mapInvalidation(result);
        if(errMap!=null){
            return errMap;
        }

        ProjectTask projectTask1 = projectTaskService.updateProjectTask(projectTask,backlog_id,project_sequence);
        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.OK);
    }

    @DeleteMapping("/{backlog_id}/{project_sequence}")
    public ResponseEntity<?> deleteProject(@PathVariable String backlog_id,@PathVariable String project_sequence){
        projectTaskService.deleteProjectTask(backlog_id,project_sequence);
        return new ResponseEntity<String>("Project Task with ID '"+project_sequence+"' has been deleted!",HttpStatus.OK);
    }
}
