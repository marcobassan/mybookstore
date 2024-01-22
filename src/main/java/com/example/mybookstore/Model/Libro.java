package com.example.mybookstore.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Libri")
public class Libro {
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @NotNull(message = "Il campo risulta vuoto")

    String autore, titolo;
    Date pubblicazione;

    @NotNull(message = "Il campo risulta vuoto")

    double prezzo;

    public Libro(String autore, String titolo, Date pubblicazione, double prezzo) {
        this.autore = autore;
        this.titolo = titolo;
        this.pubblicazione = pubblicazione;
        this.prezzo = prezzo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Date getPubblicazione() {
        return pubblicazione;
    }

    public void setPubblicazione(Date pubblicazione) {
        this.pubblicazione = pubblicazione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public Libro(){}


}
