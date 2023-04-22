package com.example.SpringConsumingRestFulServiceWebContent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "persona")
public class Persona {

    @Id
    @Column(name = "id_persona")
    int personaId;
    @Column(name = "id_arcana")
    int arcanaId;
    @Column(name = "nombre_persona")
    String name;
    @Column(name = "historia")
    String historia;



    public Persona() {
        super();
    }

    public Persona(int personaId, int arcanaId, String name, String historia) {
        this.personaId = personaId;
        this.arcanaId = arcanaId;
        this.name = name;
        this.historia = historia;

    }

    public Persona(PersonaData personaData) {
        this.personaId = personaData.getPersonaId();
        this.arcanaId = personaData.getArcanaId();
        this.name = personaData.getName();
        this.historia = personaData.getHistoria();

    }
}