package com.example.mybookstore.DataAccessObject;

import com.example.mybookstore.Model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UtenteRepository extends CrudRepository<Utente, Long> {
    Utente findById(long id);

    @Query("select utente from Utente utente where username = :username and password = :password")
    public Utente login(String username, String password);
}
