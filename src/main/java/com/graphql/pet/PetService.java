package com.graphql.pet;

import com.graphql.person.Person;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class PetService {
    Flux<Pet> pets() {
        return Flux.just(
                new Pet("Max", "1"),
                new Pet("Charlie", "2"),
                new Pet("Buddy", "3"),
                new Pet("Lucy", "1"));
    }

    Flux<Person> owners() {
        return Flux.just(
                new Person("1", "John", "Doe"),
                new Person("2", "Jane", "Doe"),
                new Person("3", "Alice", "Smith"));
    }

    public Mono<Map<String, String>> owner(Pet pet) {
        return owners()
                .filter(owner -> owner.id().equals(pet.ownerId()))
                .next() // Use next() to get the first element as Mono
                .map(owner -> Map.of("firstName", owner.firstName(), "lastName", owner.lastName()))
                .defaultIfEmpty(Map.of()); // Provide an empty map if no owners found
    }

    public Flux<Pet> petsByOwner(String ownerId) {
        return pets()
                .filter(pet -> pet.ownerId().equals(ownerId));
    }

    public Mono<Pet> favoritePet(String ownerId) {
        return petsByOwner(ownerId).next();
    }
}