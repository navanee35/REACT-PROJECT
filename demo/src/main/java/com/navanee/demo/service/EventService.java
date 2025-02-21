package com.navanee.demo.service;

import com.navanee.demo.model.Event;
import com.navanee.demo.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public List<Event> getEventsByName(String eventName) {
        return eventRepository.findByEventName(eventName);
    }

    public List<Event> searchEventsByName(String keyword) {
        return eventRepository.searchByEventName(keyword);
    }


    public List<Event> getAllEvents(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Event> eventPage = eventRepository.findAllEvents(pageable);
        return eventPage.getContent(); 
    }

    
    public Event updateEvent(Long id, Event updatedEvent) {
        Event event = getEventById(id);
        event.setEventName(updatedEvent.getEventName());
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
