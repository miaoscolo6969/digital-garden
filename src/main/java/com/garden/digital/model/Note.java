package com.garden.digital.model;

import jakarta.persistence.*; // importiamo le regole del database
import lombok.Data;           // mi serve per skippare getter e setter

import java.time.LocalDateTime;


@Entity // - diventerà una tabella nel DB
@Table(name = "notes") // - chiamiamo la tabella "notes"
@Data // - genera in automatico il codice per leggere/scrivere nei campi

public class Note {

    @Id // id unico
    @GeneratedValue(strategy = GenerationType.IDENTITY) // si autoincrementa
    private Long id;

    @Column(nullable = false) // non può essere nulla
    private String title;

    @Column(columnDefinition = "TEXT")  // testo lungo per il contenuto
    private String content;

    private LocalDateTime createdAt;

    // questo metodo parte quando creo la nota per azziccare l'ora giusta
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
