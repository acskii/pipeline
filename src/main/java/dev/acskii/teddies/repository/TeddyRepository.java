package dev.acskii.teddies.repository;

import dev.acskii.teddies.model.Teddy;
import dev.acskii.teddies.model.common.TeddyColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/* Andrew :) */

public interface TeddyRepository extends JpaRepository<Teddy, Integer> {
    List<Teddy> findByColor(TeddyColor color);
    Optional<Teddy> findByName(String name);
}
