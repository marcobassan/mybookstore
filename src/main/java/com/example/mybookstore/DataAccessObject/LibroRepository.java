package com.example.mybookstore.DataAccessObject;

import com.example.mybookstore.Model.Libro;
import org.springframework.data.repository.CrudRepository;

public interface LibroRepository extends CrudRepository<Libro, Long> {

    Libro findById(long id);
}
