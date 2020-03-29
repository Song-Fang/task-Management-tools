package com.songfang.taskmtool.Repository;

import com.songfang.taskmtool.Domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog,Long> {

     Backlog findByProjectIdentifier(String projectIdentifier);

}
