package com.graphql.pet;

import com.graphql.person.Person;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Service
public class PetService {
    Flux<Pet> pets() {
        return Flux.just(
                new Pet("1", "Max", "1", new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()),
                new Pet("2", "Charlie", "2", new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()),
                new Pet("3", "Buddy", "3", new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()),
                new Pet("4", "Lucy", "1", new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
    }

    Mono<Pet> pet(String id) {
        return pets()
                .filter(pet -> pet.id().equals(id))
                .next();
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