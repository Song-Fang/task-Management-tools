package com.songfang.taskmtool.Services;

import com.songfang.taskmtool.Domain.Backlog;
import com.songfang.taskmtool.Domain.Project;
import com.songfang.taskmtool.Exception.ProjectIdException;
import com.songfang.taskmtool.Repository.BacklogRepository;
import com.songfang.taskmtool.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveOrUpdate(Project project){
        try {
            if(project.getId()==null){
                Backlog backlog = new Backlog();
                backlog.setProjectIdentifier(project.getprojectIdentifier());
                project.setBacklog(backlog);
                backlog.setProject(project);

            }

            if(project.getId()!=null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getprojectIdentifier()));
            }
            //Actually we didn't persist backlog at this time
            return projectRepository.save(project);
        }catch(Exception e){
            throw new ProjectIdException("Project ID"+project.getprojectIdentifier().toLowerCase());
        }

    }

    public Project findProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId);
        if(project==null){
            throw new ProjectIdException("Project is not existed!");
        }
        return project;
    }

    public Iterable<Project> findAllProject(){
        return projectRepository.findAll();
    }

    public void deleteProject(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId);
        if(project==null){
            throw new ProjectIdException("Project does not exist!");
        }
        projectRepository.delete(project);

    }





}
