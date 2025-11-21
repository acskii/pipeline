package dev.acskii.teddies.service;

import dev.acskii.teddies.model.Teddy;
import dev.acskii.teddies.model.common.TeddyColor;
import dev.acskii.teddies.repository.TeddyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class TeddyServiceTest {
    @InjectMocks
    private TeddyService service;

    @Mock
    private TeddyRepository repository;

    @Test
    void createTeddy_ShouldSaveNewTeddy() {
        // When
        service.createTeddy("TestBear", TeddyColor.BROWN);

        // Then
        verify(repository).save(any(Teddy.class));
    }

    @Test
    void getAll_ShouldReturnAllTeddies() {
        // Given
        Teddy teddy1 = new Teddy("Bear1", TeddyColor.BROWN);
        Teddy teddy2 = new Teddy("Bear2", TeddyColor.ORANGE);
        List<Teddy> expectedTeddies = Arrays.asList(teddy1, teddy2);

        when(repository.findAll()).thenReturn(expectedTeddies);

        // When
        List<Teddy> result = service.getAll();

        // Then
        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void getByName_WhenTeddyExists_ShouldReturnTeddy() {
        // Given
        Teddy expectedTeddy = new Teddy("ExistingBear", TeddyColor.BROWN);
        when(repository.findByName("ExistingBear")).thenReturn(Optional.of(expectedTeddy));

        // When
        Teddy result = service.getByName("ExistingBear");

        // Then
        assertNotNull(result);
        assertEquals("ExistingBear", result.getName());
    }

    @Test
    void getByName_WhenTeddyNotExists_ShouldReturnNull() {
        // Given
        when(repository.findByName("UnknownBear")).thenReturn(Optional.empty());

        // When
        Teddy result = service.getByName("UnknownBear");

        // Then
        assertNull(result);
    }

    @Test
    void getByColor_ShouldReturnTeddiesByColor() {
        // Given
        Teddy brownTeddy = new Teddy("BrownBear", TeddyColor.BROWN);
        List<Teddy> brownTeddies = List.of(brownTeddy);

        when(repository.findByColor(TeddyColor.BROWN)).thenReturn(brownTeddies);

        // When
        List<Teddy> result = service.getByColor(TeddyColor.BROWN);

        // Then
        assertEquals(1, result.size());
        assertEquals(TeddyColor.BROWN, result.get(0).getColor());
    }

    @Test
    void getById_WhenTeddyExists_ShouldReturnTeddy() {
        // Given
        Teddy expectedTeddy = new Teddy("Bear1", TeddyColor.BROWN);
        expectedTeddy.setId(1);
        when(repository.findById(1)).thenReturn(Optional.of(expectedTeddy));

        // When
        Teddy result = service.getById(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void getById_WhenTeddyNotExists_ShouldReturnNull() {
        // Given
        when(repository.findById(999)).thenReturn(Optional.empty());

        // When
        Teddy result = service.getById(999);

        // Then
        assertNull(result);
    }

    @Test
    void updateTeddy_WhenTeddyExists_ShouldUpdateAndSave() {
        // Given
        Teddy existingTeddy = new Teddy("OldName", TeddyColor.BROWN);
        existingTeddy.setId(1);

        when(repository.findById(1)).thenReturn(Optional.of(existingTeddy));

        // When
        service.updateTeddy(1, "NewName", TeddyColor.ORANGE);

        // Then
        verify(repository).save(any(Teddy.class));
    }

    @Test
    void deleteTeddy_ShouldCallDeleteById() {
        // When
        service.deleteTeddy(1);

        // Then
        verify(repository).deleteById(1);
    }
}
