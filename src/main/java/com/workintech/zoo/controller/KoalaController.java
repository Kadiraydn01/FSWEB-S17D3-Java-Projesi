package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.AnimalValidation;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/koala")
@Slf4j
public class KoalaController {
    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init(){
    koalas = new HashMap<>();
    koalas.put(1,new Koala(1,"Susan",45,12,"Female"));
    log.info("Koala is added");
    }

@GetMapping("/")
public List<Koala> findAll(){
        return koalas.values().stream().toList();
    }
@GetMapping("/{id}")
    public Koala find(@PathVariable int  id){
    AnimalValidation.isIdNotValid(id);
    AnimalValidation.isKoalaNotExist(koalas,id);
        return koalas.get(id);
    }
@PostMapping("/{id}")
    public Koala save(@RequestBody Koala koala){
        AnimalValidation.isKoalaCredentialsValid(koala);
        koalas.put(koala.getId(),koala);
        return koalas.get(koala.getId());
    }
@PutMapping("/{id}")
    public Koala update(@PathVariable int id , @RequestBody Koala updatedKoala){
        if(koalas.containsKey(id)){
            koalas.put(id,updatedKoala);
            return updatedKoala;
        }else{
            throw new RuntimeException("Koala not found with this id");
      }
   }

@DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        koalas.remove(id);
    }
}
