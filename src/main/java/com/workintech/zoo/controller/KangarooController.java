package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.AnimalValidation;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/kangaroo")
@Slf4j
public class KangarooController {
private Map<Integer, Kangaroo> kangaroos;
@PostConstruct
public void init(){
kangaroos = new HashMap<>();
kangaroos.put(1, new Kangaroo(1,"Alex",1.80,55,"Male",false));
log.info("Kangaroo is added");
    }
@GetMapping("/")
public List<Kangaroo> findAll(){
    return kangaroos.values().stream().toList();
    }
@GetMapping("/{id}")
public Kangaroo find(@PathVariable int id){
    AnimalValidation.isIdNotValid(id);
    AnimalValidation.isKangarooNotExist(kangaroos,id);
    return kangaroos.get(id);
    }
@PostMapping("/")
   public Kangaroo save(@RequestBody Kangaroo kangaroo){
    AnimalValidation.isKangarooNotExist(kangaroos, kangaroo.getId());
    AnimalValidation.isKangarooCredentialsValid(kangaroo);
     kangaroos.put(kangaroo.getId(), kangaroo);
      return kangaroos.get(kangaroo.getId());
    }
@PutMapping("/{id}")
    public Kangaroo update(@PathVariable int id, @RequestBody Kangaroo updatedKangoroo){
      if(kangaroos.containsKey(id)){
          kangaroos.put(id,updatedKangoroo);
          return updatedKangoroo;
      }else{
          throw new RuntimeException("Kangaroo not found with this id");
      }
    }
@DeleteMapping("/{id}")
public void delete(@PathVariable int id){
    kangaroos.remove(id);

}
}
