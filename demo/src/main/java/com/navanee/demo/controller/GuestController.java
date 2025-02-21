package com.navanee.demo.controller;

import com.navanee.demo.model.Guest;
import com.navanee.demo.service.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    // 🔹 1️⃣ Create a guest
    @PostMapping
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
        return ResponseEntity.ok(guestService.createGuest(guest));
    }

    // 🔹 2️⃣ Get all guests (No pagination)
    @GetMapping("/all")
    public ResponseEntity<List<Guest>> getAllGuests() {
        return ResponseEntity.ok(guestService.getAllGuests());
    }

    // 🔹 3️⃣ Get guest by ID
    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable Long id) {
        return ResponseEntity.ok(guestService.getGuestById(id));
    }

    // 🔹 4️⃣ Get guests by name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Guest>> getGuestsByName(@PathVariable String name) {
        return ResponseEntity.ok(guestService.getGuestsByName(name));
    }

    // 🔹 5️⃣ Search guests by name
    @GetMapping("/search")
    public ResponseEntity<List<Guest>> searchGuestsByName(@RequestParam String keyword) {
        return ResponseEntity.ok(guestService.searchGuestsByName(keyword));
    }

    // 🔹 6️⃣ Get guests by event ID
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Guest>> getGuestsByEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(guestService.getGuestsByEvent(eventId));
    }

    // 🔹 7️⃣ Get all guests with pagination & sorting
    @GetMapping
    public ResponseEntity<List<Guest>> getAllGuests(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
    
        List<Guest> guests = guestService.getAllGuests(page, size, sortBy, sortDir);
        return ResponseEntity.ok(guests);
    }

    // 🔹 8️⃣ Update guest
    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable Long id, @RequestBody Guest guest) {
        return ResponseEntity.ok(guestService.updateGuest(id, guest));
    }

    // 🔹 9️⃣ Delete guest
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        guestService.deleteGuest(id);
        return ResponseEntity.noContent().build();
    }
}
