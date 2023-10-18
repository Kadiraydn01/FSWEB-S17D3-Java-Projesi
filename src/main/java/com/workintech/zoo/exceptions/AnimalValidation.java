package com.workintech.zoo.exceptions;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class AnimalValidation {

    public static void isIdNotValid(int id){
        if(id <=0){
            throw new ZooException("Id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }
    }
    public static void isKoalaNotExist(Map<Integer, Koala> koalas ,int id){
        if(!koalas.containsKey(id)){
            throw new ZooException("Koala with given id is not exist: "+ id ,HttpStatus.BAD_REQUEST);
        }
    }
    public static void isKangarooNotExist(Map<Integer, Kangaroo> kangaroos,int id){
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Kangaroo with given id is not exist: "+ id ,HttpStatus.BAD_REQUEST);
        }
    }
    public static void isKoalaCredentialsValid(Koala koala){
        if ((koala.getName() == null || koala.getName().isEmpty())){
            throw new ZooException("Koala credentials are not valid. Please check the name ",HttpStatus.BAD_REQUEST);
        }
    }
    public static void isKangarooCredentialsValid(Kangaroo kangaroo){
        if ((kangaroo.getName() == null || kangaroo.getName().isEmpty())){
            throw new ZooException("Kangaroo credentials are not valid. Please check the name ",HttpStatus.BAD_REQUEST);
        }
    }
}
