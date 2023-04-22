package com.example.SpringConsumingRestFulServiceWebContent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;


import java.util.List;

@Controller
public class PersonaController {
    @Autowired
    PersonaService personaService;

    @Autowired
    public ObjectMapper objectMapper;

    public List<PersonaData> getAllPersona() {
        List<Persona> personas = personaService.findAll();
        List<PersonaData> personaDatos = personas.stream().map(PersonaData::new).toList();

        return personaDatos;

    }

    public PersonaData getPersonaById(Integer id){

        Persona persona = personaService.getPersonaById(id);
        return  new PersonaData(persona);
    }

    public void addPersona(PersonaData personaData){
        Persona persona = new Persona();
        persona.setPersonaId(personaData.getPersonaId());
        persona.setName(personaData.getName());
        persona.setArcanaId(personaData.getArcanaId());
        persona.setHistoria(personaData.getHistoria());
        //Añadimos el persona
        personaService.addPersona(persona);

    }

    public void deletePersonaById(Integer id){
        personaService.deletePersonaById(id);
    }

    public PersonaData updatePersonaById(Integer id,PersonaData personaData){
        //Busca el Persona por el ID
        Persona existingPersona = personaService.getPersonaById(id);

        if(existingPersona !=null) {
            //Update del Persona
            existingPersona.setName(personaData.getName());
            existingPersona.setHistoria(personaData.getHistoria());
            existingPersona.setArcanaId(personaData.getArcanaId());
            //Guarda el persona actualizado
            Persona updatedPersona = personaService.updatePersona(existingPersona);
            //Devuelve el persona actualizado
            return new PersonaData(updatedPersona);

        }
        else {
            throw new RuntimeException("Persona con el ID: " + id + " no encontrado.");
        }
    }

    public PersonaData applyPatch(JsonPatch patch, PersonaData currentPersona){
       try{
           JsonNode patched = patch.apply(objectMapper.convertValue(currentPersona, JsonNode.class));
           return objectMapper.treeToValue(patched, PersonaData.class);
       }
       catch (JsonPatchException | JsonProcessingException e){
           throw new RuntimeException("Fallo en la aplicación del parche", e);
       }
    }


}