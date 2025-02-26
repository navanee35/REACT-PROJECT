package com.navanee.demo.repository;

import com.navanee.demo.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.navanee.demo.model.Event;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


    @Query("SELECT t FROM Task t WHERE t.taskName = :taskName")
    List<Task> findByTaskName(@Param("taskName") String taskName);

    
    @Query("SELECT t FROM Task t WHERE t.taskName LIKE %:keyword%")
    List<Task> searchByTaskName(@Param("keyword") String keyword);

    @Query("SELECT t FROM Task t WHERE t.event.id = :eventId")
    List<Task> findTasksByEvent(@Param("eventId") Long eventId);

    @Query("SELECT t FROM Task t")
    Page<Task> findAllTasks(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Task t SET t.taskName = :taskName, t.event = :event WHERE t.event.id = :eventId")
    int updateTaskByEvent(@Param("eventId") Long eventId, @Param("taskName") String taskName, @Param("event") Event event);

    @Transactional
    @Modifying
    @Query("DELETE FROM Task t WHERE t.taskName = :taskName")
    int deleteTaskByTaskName(@Param("taskName") String taskName);

}
