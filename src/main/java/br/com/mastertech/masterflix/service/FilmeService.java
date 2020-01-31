package br.com.mastertech.masterflix.service;

import br.com.mastertech.masterflix.model.Filme;
import br.com.mastertech.masterflix.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository repository;

    public Filme cadastroFilme(Filme filme) {
        return repository.save(filme);
    }

    public Iterable<Filme> listarFilmes(String nome) {
        Iterable<Filme> filmes = repository.findAll();
        return filmes;
    }

    public Filme listaDeFilmes(String nome) {
        Optional<Filme> filmes = repository.findByNome(nome);
        if (filmes.isPresent()) {
            return filmes.get();
        }
        return null;
    }
}