/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.repaso.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uv.repaso.repository.IPersonaRepository;
import org.uv.repaso.repository.Persona;


/**
 *
 * @author gimeba
 */
@RestController
@RequestMapping("/api/personas")

public class PersonaController {
    
    @Autowired
    IPersonaRepository personaRepository;
    
    @GetMapping("{id}")
    public Persona buscarByID(@PathVariable("id") Long id){
        Optional<Persona> persona = personaRepository.findById(id);
        return persona.get();
    }
    
    @GetMapping()
    public List<Persona> getAllPersonas(){
        return personaRepository.findAll();
    }
    @PostMapping()
    public ResponseEntity<Persona> createPersona(@Validated @RequestBody Persona persona) {

       Persona pers = personaRepository.save(persona);
       return  ResponseEntity.ok().body(pers);
    }
    @PutMapping("{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable(value = "id") Long personaId, @Validated 
                                                    @RequestBody Persona personaRequest){
        Optional<Persona> persona = personaRepository.findById(personaId);
        Persona pers = persona.get();
        pers.setNombre(personaRequest.getNombre());
        pers.setDirecion(personaRequest.getDirecion());
        pers.setTelefono(personaRequest.getTelefono());
        final Persona updatedPersona = personaRepository.save(pers);
        return ResponseEntity.ok(updatedPersona);

    }
    @DeleteMapping("{id}")
    public Boolean deletePersona(@PathVariable(value = "id") Long personaId){
        Optional<Persona> persona = personaRepository.findById(personaId);

        personaRepository.delete(persona.get());
        return Boolean.TRUE;
    }
}
