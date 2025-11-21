package dev.acskii.teddies.service;

import dev.acskii.teddies.model.Teddy;
import dev.acskii.teddies.model.common.TeddyColor;
import dev.acskii.teddies.repository.TeddyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/* Andrew :) */

@Service
public class TeddyService {
    private final TeddyRepository repository;

    /* Dependency Injection */
    public TeddyService(TeddyRepository repository) {
        this.repository = repository;
    }

    /* Create */
    public void createTeddy(String name, TeddyColor color) {
        Teddy teddy = new Teddy(name, color);
        repository.save(teddy);
    }

    /* Read */
    public List<Teddy> getAll() {
        return repository.findAll();
    }

    public Teddy getByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    public List<Teddy> getByColor(TeddyColor color) {
        return repository.findByColor(color);
    }

    public Teddy getById(int id) {
        return repository.findById(id).orElse(null);
    }

    /* Update */
    public void updateTeddy(int id, String name, TeddyColor color) {
        Teddy teddy = getById(id);

        if (!name.equals(teddy.getName())) teddy.setName(name);
        if (color != teddy.getColor()) teddy.setColor(color);

        repository.save(teddy);
    }

    /* Delete */
    public void deleteTeddy(int id) {
        repository.deleteById(id);
    }
}
