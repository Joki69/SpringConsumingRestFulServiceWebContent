package com.example.SpringConsumingRestFulServiceWebContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;


import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(PersonaResource.PERSONA)
public class PersonaResource {
    public static final String PERSONA = "/persona";
    @Autowired
    PersonaController personaController;

    @GetMapping
    public List<PersonaData> readAll() {
        return personaController.getAllPersona();
    }

    @GetMapping("{id}")
    public PersonaData getPersona(@PathVariable Integer id){
        return personaController.getPersonaById(id);
    }

    @GetMapping("{id}/historia")
    public Map<String,String> historia(@PathVariable Integer id){
        return Collections.singletonMap("historia",personaController.getPersonaById(id).getHistoria());
    }


    @PostMapping("")
    public void addPersona(@RequestBody PersonaData personaData){
        personaController.addPersona(personaData);
    }

    @DeleteMapping("{id}")
    public void deletePersona(@PathVariable Integer id){
        personaController.deletePersonaById(id);
    }

    @PutMapping("{id}")
    /*
    curl -i -X PUT http://localhost:8080/persona/2 -H "Content-Type: application/json" -d '{"personaID":2,"arcanaId":1,"name":"Pedro","historia":"Le gustan los tomates"}'
     */
    public PersonaData updatePersona(@PathVariable Integer id, @RequestBody PersonaData personaData) {
        PersonaData updatedPersona = personaController.updatePersonaById(id, personaData);
        return updatedPersona;

    }

    @PatchMapping("{id}")
    public PersonaData partialUpdatePersona(@PathVariable Integer id, @RequestBody JsonPatch patch) {
       PersonaData currentPersona =personaController.getPersonaById(id);
        PersonaData patchedPersona = personaController.applyPatch(patch, currentPersona);
        personaController.updatePersonaById(id, patchedPersona);
        return patchedPersona;
    }
}

