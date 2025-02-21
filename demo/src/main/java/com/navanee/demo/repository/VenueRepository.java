package com.navanee.demo.repository;

import com.navanee.demo.model.Venue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
    
    @Query("SELECT v FROM Venue v WHERE v.location = :location")
    List<Venue> findByLocation(@Param("location") String location);

    @Query("SELECT v FROM Venue v WHERE v.name LIKE %:keyword%")
    List<Venue> searchByName(@Param("keyword") String keyword);

    @Query("SELECT v FROM Venue v")
    Page<Venue> findAllVenues(Pageable pageable);
}
