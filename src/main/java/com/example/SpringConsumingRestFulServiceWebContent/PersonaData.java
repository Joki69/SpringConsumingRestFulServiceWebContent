package com.example.SpringConsumingRestFulServiceWebContent;

import lombok.Data;

@Data
public class PersonaData {
    int personaId;
    int arcanaId;
    String name;
    String historia;


    public PersonaData(int characterId, int arcanaId, String name, String historia) {
        this.personaId = characterId;
        this.arcanaId = arcanaId;
        this.name = name;
        this.historia = historia;

    }

    public PersonaData(Persona persona){
        this.personaId = persona.getPersonaId();
        this.arcanaId = persona.getArcanaId();
        this.name = persona.getName();
        this.historia = persona.getHistoria();
    }
}
