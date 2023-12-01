package com.example.demo.controller.general;

import static com.example.demo.commons.GlobalConstans.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Escuela;
import com.example.demo.serviceImpl.EscuelaServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_ESCUELA)
@CrossOrigin(origins = "http://localhost:4200")

public class EscuelaController {
	
	@Autowired
	private EscuelaServiceImpl escuelaServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Escuela>> listar() {
		try {
            List<Escuela> var = escuelaServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<Escuela> crear(@Valid @RequestBody Escuela escuela){
        try {
            Escuela _aut = escuelaServiceImpl.create(escuela);
            return new ResponseEntity<Escuela>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Escuela> getEscuelaById(@PathVariable("id") Long id){
		Optional<Escuela> carData = escuelaServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<Escuela>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Escuela> delete(@PathVariable("id") Long id){
		try {
			escuelaServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Escuela escuela){
		Optional<Escuela> carData = escuelaServiceImpl.read(id);
        if (carData.isPresent()) {
            Escuela dbEscuela = carData.get();
            dbEscuela.setEscuela(escuela.getEscuela());
            //!ManyToOne's
			dbEscuela.setFacultad(escuela.getFacultad());
            //!OneToMany's
			dbEscuela.setSemestres(escuela.getSemestres());
            return new ResponseEntity<Escuela>(escuelaServiceImpl.update(dbEscuela), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

