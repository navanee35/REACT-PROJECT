package com.navanee.demo.repository;

import com.navanee.demo.model.Guest;
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
public interface GuestRepository extends JpaRepository<Guest, Long> {


    @Query("SELECT g FROM Guest g WHERE g.name = :name")
    List<Guest> findByName(@Param("name") String name);


    @Query("SELECT g FROM Guest g WHERE g.name LIKE %:keyword%")
    List<Guest> searchByName(@Param("keyword") String keyword);

    @Query("SELECT g FROM Guest g WHERE g.event.id = :eventId")
    List<Guest> findGuestsByEvent(@Param("eventId") Long eventId);

    @Query("SELECT g FROM Guest g")
    Page<Guest> findAllGuests(Pageable pageable);

     @Transactional
    @Modifying
    @Query("UPDATE Guest g SET g.name = :name, g.event = :event WHERE g.name = :oldName")
    int updateGuestByName(@Param("oldName") String oldName, @Param("name") String name, @Param("event") Event event);

    @Transactional
    @Modifying
    @Query("DELETE FROM Guest g WHERE g.name = :name")
    int deleteGuestByName(@Param("name") String name);
}
