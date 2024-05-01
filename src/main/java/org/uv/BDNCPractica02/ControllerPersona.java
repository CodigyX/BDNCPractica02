package org.uv.BDNCPractica02;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Codigy
 */
@RestController
@RequestMapping("/api/v1/")
public class ControllerPersona {
    @Autowired
    private RepositoryPersona repositoryPersona;    
    
    @GetMapping("/persona/")
    public List<Persona> list() {
        return repositoryPersona.findAll(); //Muestra todas las personas del collection
    }
    
    @GetMapping("persona/{id}")
    public Object get(@PathVariable String id) {
        Optional<Persona> repPersona=repositoryPersona.findById(id);
    if (repPersona.isPresent())//Condiciona si la persona existe
        return repPersona.get();//Regresa los datos de la persona
    else
        return null;   //Sino existe retorna nulo
    }
    
    @PutMapping("/persona/{id}")
    public ResponseEntity<?> updatePersona(@PathVariable String id, @RequestBody Persona personaActualizada) {
        Optional<Persona> personaExistente = repositoryPersona.findById(id);

        if (personaExistente.isPresent()) {
            Persona repPersona = personaExistente.get();

            // Actualizar campos con datos del collection
            repPersona.setNombre(personaActualizada.getNombre());
            repPersona.setDireccion(personaActualizada.getDireccion());
            repPersona.setTelefono(personaActualizada.getTelefono());

            // Guardar cambios y devolver el collection actualizado
            return ResponseEntity.ok(repositoryPersona.save(repPersona));  
        } else {
            // Devolver un mensaje que  no encuentra el ID
            return ResponseEntity.status(404).body("Persona con ID" + id +" no encontrada.");
        }
    }
    
    @PostMapping
    public Persona post(@RequestBody Persona addPersona) {
        // Guarda y escribe el empleado inmediatamente en la base de datos
        Persona personaSaved = repositoryPersona.insert(addPersona);  
        return personaSaved;  // Devuelve la persona recién creada
    }
    
    @DeleteMapping("/persona/{id}")
    public Persona deletePersona(@PathVariable String id) {
        Optional<Persona> personaExistente = repositoryPersona.findById(id);

        if (personaExistente.isPresent()) {
            Persona repPersona = personaExistente.get();
            repositoryPersona.deleteById(id);  // Eliminar el documento
            return repPersona;  // Devolver el objeto eliminado
        } else {
            // Devolver null si el documento no se encuentra
            return null;
        }
    }
}
