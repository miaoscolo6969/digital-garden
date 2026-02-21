package com.garden.digital.repository;

import com.garden.digital.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository  extends JpaRepository<Note, Long> {
// Prendi tutte le funzioni standard dei database e applicale alla mia
// classe Note che usa un ID di tipo Long
    List<Note> findByTitleContainingIgnoreCase(String title);
}
