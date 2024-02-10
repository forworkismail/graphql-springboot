package com.graphql.pet;

import com.graphql.person.Person;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Controller
class PetsController {
    private final PetService petService;

    PetsController(PetService petService) {
        this.petService = petService;
    }

    @QueryMapping
    Flux<Pet> pets() {
        return petService.pets();
    }

    @QueryMapping
    Mono<Pet> favoritePet(@Argument String ownerId) {
        return petService.favoritePet(ownerId);
    }

    @SchemaMapping
    Mono<Map<String, String>> owner(Pet pet) {
        return petService.owner(pet);
    }

    @QueryMapping
    Flux<Person> owners() {
        return petService.owners();
    }

    @QueryMapping
    Flux<Pet> petsByOwner(@Argument String ownerId) {
        return petService.petsByOwner(ownerId);
    }

    @QueryMapping
    Mono<Pet> pet(@Argument String id) {
        return petService.pet(id);
    }
}