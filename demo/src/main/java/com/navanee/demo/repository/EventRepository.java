package com.navanee.demo.repository;

import com.navanee.demo.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    

    @Query("SELECT e FROM Event e WHERE e.eventName = :eventName")
    List<Event> findByEventName(@Param("eventName") String eventName);


    @Query("SELECT e FROM Event e WHERE e.eventName LIKE %:keyword%")
    List<Event> searchByEventName(@Param("keyword") String keyword);

    @Query("SELECT e FROM Event e")
    Page<Event> findAllEvents(Pageable pageable);
}
