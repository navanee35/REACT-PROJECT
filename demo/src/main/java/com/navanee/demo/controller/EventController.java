package com.navanee.demo.controller;

import com.navanee.demo.model.Event;
import com.navanee.demo.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @GetMapping("/name/{eventName}")
    public ResponseEntity<List<Event>> getEventsByName(@PathVariable String eventName) {
        return ResponseEntity.ok(eventService.getEventsByName(eventName));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Event>> searchEventsByName(@RequestParam String keyword) {
        return ResponseEntity.ok(eventService.searchEventsByName(keyword));
    }


    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
    
        List<Event> events = eventService.getAllEvents(page, size, sortBy, sortDir);
        return ResponseEntity.ok(events);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
