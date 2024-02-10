package com.graphql.pet;

import com.graphql.person.Person;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
class PetsController {
    private final PetService petService;

    PetsController(PetService petService) {
        this.petService = petService;
    }

    @QueryMapping
    List<Pet> pets() {
        return petService.pets();
    }

    @QueryMapping
    Optional<Pet> favoritePet(@Argument String ownerId) {
        return petService.favoritePet(ownerId);
    }

    @SchemaMapping
    Map<String, String> owner(Pet pet) {
        return petService.owner(pet).orElse(null);
    }

    @QueryMapping
    List<Person> owners() {
        return petService.owners();
    }

    @QueryMapping
    List<Pet> petsByOwner(@Argument String ownerId) {
        return petService.petsByOwner(ownerId);
    }

    @QueryMapping
    Optional<Pet> pet(@Argument String id) {
        return petService.pet(id);
    }
}