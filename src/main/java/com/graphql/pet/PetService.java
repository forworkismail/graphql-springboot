package com.graphql.pet;

import com.graphql.person.Person;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PetService {
    List<Pet> pets() {
        return List.of(
                new Pet("1", "Max", "1", new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()),
                new Pet("2", "Charlie", "2", new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()),
                new Pet("3", "Buddy", "3", new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()),
                new Pet("4", "Lucy", "1", new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
    }

    Optional<Pet> pet(String id) {
        return pets().stream()
                .filter(pet -> pet.id().equals(id))
                .findFirst();
    }

    List<Person> owners() {
        return List.of(
                new Person("1", "John", "Doe"),
                new Person("2", "Jane", "Doe"),
                new Person("3", "Alice", "Smith"));
    }

    public Optional<Map<String, String>> owner(Pet pet) {
        return owners().stream()
                .filter(owner -> owner.id().equals(pet.ownerId()))
                .findFirst()
                .map(owner -> Map.of("firstName", owner.firstName(), "lastName", owner.lastName()));
    }

    public List<Pet> petsByOwner(String ownerId) {
        return pets().stream()
                .filter(pet -> pet.ownerId().equals(ownerId))
                .toList();
    }

    public Optional<Pet> favoritePet(String ownerId) {
        return petsByOwner(ownerId).stream().findFirst();
    }
}