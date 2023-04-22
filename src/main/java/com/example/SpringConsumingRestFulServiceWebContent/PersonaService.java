package com.example.SpringConsumingRestFulServiceWebContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import java.util.List;

@Service
public class PersonaService {
    @Autowired
    PersonaDAO repository;
    public List<Persona> findAll() {
        return repository.findAll();
    }

    public  Persona getPersonaById(Integer id){
        Optional<Persona> optionalPersona;
        optionalPersona = repository.findById(id);
        if(optionalPersona.isPresent()) return optionalPersona.get();
        else return null;
    }

    public Persona updatePersona(Persona persona){
        return repository.save(persona);
    }
    public void addPersona(Persona persona) {
        repository.save(persona);
    }
    public void deletePersonaById(Integer id) {
        repository.deleteById(id);
    }
}