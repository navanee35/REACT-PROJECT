package com.navanee.demo.service;

import com.navanee.demo.model.Venue;
import com.navanee.demo.repository.VenueRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {
    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public Venue getVenueById(Long id) {
        return venueRepository.findById(id).orElseThrow(() -> new RuntimeException("Venue not found"));
    }

    public List<Venue> getVenuesByLocation(String location) {
        return venueRepository.findByLocation(location);
    }

    public List<Venue> searchVenuesByName(String keyword) {
        return venueRepository.searchByName(keyword);
    }

    public List<Venue> getAllVenues(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Venue> venuePage = venueRepository.findAllVenues(pageable);
        return venuePage.getContent(); 
    }

    public Venue updateVenue(Long id, Venue updatedVenue) {
        Venue venue = getVenueById(id);
        venue.setName(updatedVenue.getName());
        venue.setLocation(updatedVenue.getLocation());
        venue.setCapacity(updatedVenue.getCapacity());
        return venueRepository.save(venue);
    }

    public void deleteVenue(Long id) {
        venueRepository.deleteById(id);
    }
}
