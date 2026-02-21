package com.garden.digital.controller;

import com.garden.digital.model.Note;
import com.garden.digital.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController // dice a spring che sto file gestisce la roba dal browser
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*") // permette al frontend di parlare al backend facilmente
public class NoteController {
    @Autowired // spring porta qui il repository
    private NoteRepository noteRepository;

    // se visito /api/notes vedo tutte le note salvate
    @GetMapping
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // se visiti /api/notes/add-test, creo una nota, richiedendo un json con titolo e content
    @PostMapping
    public Note createTestNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    // cancella una nota dal suo id
    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
    }


    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        long count = noteRepository.count();
        return Map.of(
                "totalNotes", count,
                "lastActivity", "Oggi"
        );
    }
    @GetMapping("/search")
    public List<Note> searchNotes(@RequestParam String query) {
        return noteRepository.findByTitleContainingIgnoreCase(query);
    }
}