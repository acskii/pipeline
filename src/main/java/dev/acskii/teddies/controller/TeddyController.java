package dev.acskii.teddies.controller;

import dev.acskii.teddies.model.Teddy;
import dev.acskii.teddies.model.dto.PostTeddy;
import dev.acskii.teddies.model.dto.UpdateTeddy;
import dev.acskii.teddies.service.TeddyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* Andrew :) */

@RestController
@RequestMapping("/api/teddy")
public class TeddyController {
    private final TeddyService service;

    /* Dependency Injection */
    public TeddyController(TeddyService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Teddy> getTeddies() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Teddy getTeddy(
            @PathVariable int id
    ) {
        return service.getById(id);
    }

    @PostMapping("")
    public void addTeddy(
            @RequestBody PostTeddy dto
    ) {
        service.createTeddy(dto.name(), dto.color());
    }

    @PutMapping("")
    public void updateTeddy(
            @RequestBody UpdateTeddy dto
    ) {
        service.updateTeddy(dto.id(), dto.name(), dto.color());
    }

    @DeleteMapping("/{id}")
    public void deleteTeddy(
            @PathVariable int id
    ) {
        service.deleteTeddy(id);
    }
}
