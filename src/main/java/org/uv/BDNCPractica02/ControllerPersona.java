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
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }
    
    @PostMapping
    public Persona post(@RequestBody Persona addPersona) {
        // Guarda y escribe el empleado inmediatamente en la base de datos
        Persona personaSaved = repositoryPersona.insert(addPersona);  
        return personaSaved;  // Devuelve el empleado recién creado
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
    
}
