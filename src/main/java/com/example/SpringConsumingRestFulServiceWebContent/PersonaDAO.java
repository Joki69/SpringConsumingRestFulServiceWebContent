package com.example.SpringConsumingRestFulServiceWebContent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaDAO extends JpaRepository<Persona,Integer> {
    List<Persona> findAll();
    List<Persona> findCharacterByName(String name);
}
