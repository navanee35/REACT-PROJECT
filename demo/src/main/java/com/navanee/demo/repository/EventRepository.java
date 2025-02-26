package com.navanee.demo.repository;

import com.navanee.demo.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    

    @Query("SELECT e FROM Event e WHERE e.eventName = :eventName")
    List<Event> findByEventName(@Param("eventName") String eventName);


    @Query("SELECT e FROM Event e WHERE e.eventName LIKE %:keyword%")
    List<Event> searchByEventName(@Param("keyword") String keyword);

    @Query("SELECT e FROM Event e")
    Page<Event> findAllEvents(Pageable pageable);

    @Transactional
@Modifying
@Query("UPDATE Event e SET e.eventName = :eventName WHERE LOWER(e.eventName) = LOWER(:currentEventName)")
int updateEventByEventName(String currentEventName, String eventName);

@Transactional
@Modifying
@Query("DELETE FROM Event e WHERE LOWER(e.eventName) = LOWER(:eventName)")
int deleteEventByEventName(String eventName);


}
