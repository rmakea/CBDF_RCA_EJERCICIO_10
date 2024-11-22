package com.upiiz.superheroes.controllers;

import com.upiiz.superheroes.entities.CustomResponse;
import com.upiiz.superheroes.entities.HeroeEntity;
import com.upiiz.superheroes.services.HeroeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/heroes")
@Tag(name = "Heroes")
public class HeroeController {

    @Autowired
    private HeroeService heroeService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<HeroeEntity>>> getHeroes() {
        try {
            List<HeroeEntity> heroes = heroeService.getAllHeroes();
            var selfLink = linkTo(methodOn(HeroeController.class).getHeroes()).withSelfRel();
            if (!heroes.isEmpty()) {
                return ResponseEntity.ok(new CustomResponse<>(1, "Héroes encontrados", heroes, List.of(selfLink)));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomResponse<>(0, "No se encontraron héroes", null, List.of(selfLink)));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse<>(0, "Error interno del servidor", null, null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<HeroeEntity>> getHeroeById(@PathVariable Long id) {
        try {
            Optional<HeroeEntity> heroe = Optional.ofNullable(heroeService.getHeroeById(id));
            var selfLink = linkTo(methodOn(HeroeController.class).getHeroeById(id)).withSelfRel();
            if (heroe.isPresent()) {
                return ResponseEntity.ok(new CustomResponse<>(1, "Héroe encontrado", heroe.get(), List.of(selfLink)));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomResponse<>(0, "Héroe no encontrado", null, List.of(selfLink)));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse<>(0, "Error interno del servidor", null, null));
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse<HeroeEntity>> createHeroe(@RequestBody HeroeEntity heroe) {
        try {
            HeroeEntity nuevoHeroe = heroeService.createHeroe(heroe);
            var selfLink = linkTo(methodOn(HeroeController.class).createHeroe(heroe)).withSelfRel();
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CustomResponse<>(1, "Héroe creado exitosamente", nuevoHeroe, List.of(selfLink)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse<>(0, "Error al crear el héroe", null, null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<HeroeEntity>> updateHeroe(@RequestBody HeroeEntity heroe, @PathVariable Long id) {
        try {
            heroe.setId(id);
            var selfLink = linkTo(methodOn(HeroeController.class).updateHeroe(heroe, id)).withSelfRel();
            if (Optional.ofNullable(heroeService.getHeroeById(id)).isPresent()) {
                HeroeEntity heroeActualizado = heroeService.updateHeroe(heroe);
                return ResponseEntity.ok(new CustomResponse<>(1, "Héroe actualizado exitosamente", heroeActualizado, List.of(selfLink)));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomResponse<>(0, "Héroe no encontrado", null, List.of(selfLink)));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse<>(0, "Error al actualizar el héroe", null, null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteHeroe(@PathVariable Long id) {
        try {
            var selfLink = linkTo(methodOn(HeroeController.class).deleteHeroe(id)).withSelfRel();
            if (Optional.ofNullable(heroeService.getHeroeById(id)).isPresent()) {
                heroeService.deleteHeroe(id);
                return ResponseEntity.ok(new CustomResponse<>(1, "Héroe eliminado exitosamente", null, List.of(selfLink)));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomResponse<>(0, "Héroe no encontrado", null, List.of(selfLink)));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse<>(0, "Error al eliminar el héroe", null, null));
        }
    }
}
