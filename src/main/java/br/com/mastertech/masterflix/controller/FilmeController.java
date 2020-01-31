package br.com.mastertech.masterflix.controller;

import br.com.mastertech.masterflix.model.Filme;
import br.com.mastertech.masterflix.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FilmeController {
    @Autowired
    private FilmeService service;

    @GetMapping
    public String home() {
        return "index";
    }

    @PostMapping("/cadastrar")
    public String cadastrarFilme(Filme filme) {
        service.cadastroFilme(filme);
        return "index";
    }

    @GetMapping("/filme")
    public ModelAndView listFilmes(String nome) {
        ModelAndView eng = new ModelAndView("registroFilme");
        Iterable<Filme> filme = service.listarFilmes(nome);
        eng.addObject("filmes", filme);
        return eng;
    }

    @GetMapping("/buscar")
    public String retornarFilme() {
        return "buscarFilme";
    }

    @GetMapping("/procurar")
    public String listadoFilme(@RequestParam("nome") String nome, Model model) {
        Filme filme = service.listaDeFilmes(nome);
        if (nome != null) {
            model.addAttribute("filme", filme);
            return "listaDeFilmes";
        } else {
            model.addAttribute("msg", "O filme " + nome + " não foi encontrado!! procure novamente");
            return "buscarFilme";
        }
    }

    @GetMapping("/filme/{nome}")
    public String listarFilmePorNome(@PathVariable("nome") String nome, Model model) {
        Filme filme = service.listaDeFilmes(nome);
        if (nome != null) {
            model.addAttribute("filme", filme);
            return "listaDeFilmes";
        } else {
            model.addAttribute("msg", "O filme " + nome + " não foi encontrado!! procure novamente");
            return "buscarEvento";
        }
    }
}