package com.example.mybookstore.Controller;

import com.example.mybookstore.DataAccessObject.LibroRepository;
import com.example.mybookstore.Model.Libro;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;


@Controller
public class LibroController {
    @Autowired
    private LibroRepository libroRepository;

    @GetMapping(value = "controlPanel")
    public String controlPanel(HttpSession session){
        if(session.getAttribute("loggato") == null) return "redirect:/login";
        return "controlPanel";
    }

    @GetMapping(value = "/aggiungiLibro")
    public String aggiungiLibro(Libro libro, HttpSession session) {
        if(session.getAttribute("loggato") == null) return "redirect:/login";

        return "aggiungiLibro";
    }

    @PostMapping(value = "/aggiungiLibro")
    public String aggiungiLibro(@Valid Libro libro, BindingResult result) {
        if (result.hasErrors()) return "aggiungiLibro";

        for(Libro b : libroRepository.findAll()){
            if(Objects.equals(b.getTitolo().toLowerCase(), libro.getTitolo().toLowerCase()) &&
                    b.getAutore().toLowerCase().equals(libro.getAutore().toLowerCase()))
                return "redirect:/info";
        }

        libroRepository.save(libro);

        return "redirect:/controlPanel";
    }


    @GetMapping(value = "/info")
    public String info(Model model, HttpSession session) {
        if (session.getAttribute("loggato") == null) return "redirect:/login";

        model.addAttribute("Libri", libroRepository.findAll());
        return "info";
    }

}
