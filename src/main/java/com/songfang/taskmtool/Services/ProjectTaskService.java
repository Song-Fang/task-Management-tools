package com.songfang.taskmtool.Services;

import com.songfang.taskmtool.Domain.Backlog;
import com.songfang.taskmtool.Domain.Project;
import com.songfang.taskmtool.Domain.ProjectTask;
import com.songfang.taskmtool.Exception.ProjectNotFoundException;
import com.songfang.taskmtool.Repository.BacklogRepository;
import com.songfang.taskmtool.Repository.ProjectRepository;
import com.songfang.taskmtool.Repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projcetIdentifier, ProjectTask projectTask){

        try {
            Backlog backlog = backlogRepository.findByProjectIdentifier(projcetIdentifier);
            projectTask.setBacklog(backlog);
            Integer PTSequence = backlog.getPTSequence();
            PTSequence++;
            backlog.setPTSequence(PTSequence);
            projectTask.setProjectSequence(projcetIdentifier + "-" + PTSequence);
            projectTask.setProjectIdentifier(projcetIdentifier);


            if (projectTask.getPriority() == null) {
                projectTask.setPriority(3);
            }

            if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }


            return projectTaskRepository.save(projectTask);
        }catch(Exception e){
            throw new ProjectNotFoundException("Project Not Found!");
        }

    }

    public Iterable<ProjectTask> findAllProjectTasks(String projectIdentifier){

        Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
        if(project==null){
            throw new ProjectNotFoundException("Project does not exist!");
        }
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectIdentifier);
    }

    public ProjectTask findProjectTask(String projectIdentifier, String projectSequence){
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

        if(backlog==null){
            throw new ProjectNotFoundException("Project "+projectIdentifier+" does not exist!");
        }

        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectSequence);
        if(projectTask==null){
            throw new ProjectNotFoundException("ProjectTask "+projectSequence+" does not exist!");
        }
        if(!projectTask.getProjectIdentifier().equals(projectIdentifier)){
            throw new ProjectNotFoundException("ProjectTask "+projectSequence+"is not in the Project: "+projectIdentifier);
        }

        return projectTask;
    }

    public ProjectTask updateProjectTask(ProjectTask projectTask, String projectIdentifier, String projectSequence){
        ProjectTask currentProjectTask = findProjectTask(projectIdentifier,projectSequence);
        currentProjectTask = projectTask;
        return projectTaskRepository.save(currentProjectTask);
    }

    public void deleteProjectTask(String projectIdentifier, String projectSequence){
        ProjectTask projectTask = findProjectTask(projectIdentifier,projectSequence);
        projectTaskRepository.delete(projectTask);
    }


}
