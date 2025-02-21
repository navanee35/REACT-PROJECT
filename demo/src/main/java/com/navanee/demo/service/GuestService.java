package com.navanee.demo.service;

import com.navanee.demo.model.Guest;
import com.navanee.demo.repository.GuestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Guest getGuestById(Long id) {
        return guestRepository.findById(id).orElseThrow(() -> new RuntimeException("Guest not found"));
    }

    public List<Guest> getGuestsByName(String name) {
        return guestRepository.findByName(name);
    }

    public List<Guest> searchGuestsByName(String keyword) {
        return guestRepository.searchByName(keyword);
    }

    public List<Guest> getGuestsByEvent(Long eventId) {
        return guestRepository.findGuestsByEvent(eventId);
    }

    public List<Guest> getAllGuests(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Guest> guestPage = guestRepository.findAllGuests(pageable);
        return guestPage.getContent(); // ✅ Return only the data, not metadata
    }

    public Guest updateGuest(Long id, Guest updatedGuest) {
        Guest guest = getGuestById(id);
        guest.setName(updatedGuest.getName());
        guest.setEvent(updatedGuest.getEvent());
        return guestRepository.save(guest);
    }

    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }
}
