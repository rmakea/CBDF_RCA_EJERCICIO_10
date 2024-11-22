package com.upiiz.superheroes.services;

import com.upiiz.superheroes.entities.HeroeEntity;
import com.upiiz.superheroes.repositories.HeroeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroeService {

    @Autowired
    private HeroeRepository heroeRepository;

    //GET DE TODOS LOS HEROES
    public List<HeroeEntity> getAllHeroes(){
        return heroeRepository.findAll();
    }
    //GET DE UN HEROE
    public HeroeEntity getHeroeById(Long id){
        return heroeRepository.findById(id).orElse(null);
    }
    //POST DE UN HEROE
    public HeroeEntity createHeroe(HeroeEntity heroe){
        return heroeRepository.save(heroe);
    }
    //PUT DE UN HEROE
    public HeroeEntity updateHeroe(HeroeEntity heroe){
        return  heroeRepository.save(heroe);
    }
    //DELETE DE UN HEROE
    public void deleteHeroe(Long id){
        heroeRepository.deleteById(id);
    }
}
