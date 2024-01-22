package com.example.mybookstore.Controller;

import com.example.mybookstore.DataAccessObject.UtenteRepository;
import com.example.mybookstore.Model.Utente;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UtenteController {
    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping(value = "/")
    public String signIn(Utente utente) {
        return "signIn";
    }

    @PostMapping(value = "/validazioneSignIn")
    public String validazione(@Valid Utente utente, BindingResult result, HttpSession session) {
        if(result.hasErrors()) return "signIn";
        for (Utente u : utenteRepository.findAll()){
            if(u.getUsername().toLowerCase().equals(utente.getUsername().toLowerCase()))
                return "redirect:/";
        }


        utenteRepository.save(utente);
        session.setAttribute("loggato", utente);

        return "controlPanel";
    }

    @GetMapping(value = "/login")
    public String login(Utente utente) {
        return "login";
    }

    @PostMapping(value = "/validazioneLogIn")
    public String validateLogin(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                HttpSession session) {

        Utente loggato = utenteRepository.login(username, password);

        if(loggato == null)
            return "redirect:/login";
        else {
            session.setAttribute("loggato", loggato);

            return "controlPanel";
        }

    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.setAttribute("loggato", null);

        return "redirect:/";
    }

    @GetMapping(value = "/infoUtente")
    public String userInfo(Model model, HttpSession session) {
        if (session.getAttribute("loggato") == null) return "redirect:/login";

        model.addAttribute("utente", session.getAttribute("loggato"));
        return "infoUtente";
    }
}
