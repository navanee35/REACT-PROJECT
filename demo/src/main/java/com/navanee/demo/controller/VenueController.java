package com.navanee.demo.controller;

import com.navanee.demo.model.Venue;
import com.navanee.demo.service.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @PostMapping
    public ResponseEntity<Venue> createVenue(@RequestBody Venue venue) {
        return ResponseEntity.ok(venueService.createVenue(venue));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Venue>> getAllVenues() {
        return ResponseEntity.ok(venueService.getAllVenues());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenueById(@PathVariable Long id) {
        return ResponseEntity.ok(venueService.getVenueById(id));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Venue>> getVenuesByLocation(@PathVariable String location) {
        return ResponseEntity.ok(venueService.getVenuesByLocation(location));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Venue>> searchVenuesByName(@RequestParam String keyword) {
        return ResponseEntity.ok(venueService.searchVenuesByName(keyword));
    }

    @GetMapping
    public ResponseEntity<List<Venue>> getAllVenues(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
    
        List<Venue> venues = venueService.getAllVenues(page, size, sortBy, sortDir);
        return ResponseEntity.ok(venues);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venue> updateVenue(@PathVariable Long id, @RequestBody Venue venue) {
        return ResponseEntity.ok(venueService.updateVenue(id, venue));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenue(@PathVariable Long id) {
        venueService.deleteVenue(id);
        return ResponseEntity.noContent().build();
    }
}
