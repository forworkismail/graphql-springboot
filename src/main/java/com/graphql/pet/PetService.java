package com.graphql.pet;

import com.graphql.person.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PetService {
    List<Pet> pets() {
        return List.of(
                new Pet("Max", "1"),
                new Pet("Charlie", "2"),
                new Pet("Buddy", "3"),
                new Pet("Lucy", "1"));
    }

    List<Person> owners() {
        return List.of(
                new Person("1", "John", "Doe"),
                new Person("2", "Jane", "Doe"),
                new Person("3", "Alice", "Smith"));
    }

    public Map<String, String> owner(Pet pet) {
        return owners().stream()
                .filter(owner -> owner.id().equals(pet.ownerId()))
                .findFirst()
                .map(owner -> Map.of("firstName", owner.firstName(), "lastName", owner.lastName()))
                .orElse(Map.of());
    }

    public List<Pet> petsByOwner(String ownerId) {
        return pets().stream()
                .filter(pet -> pet.ownerId().equals(ownerId))
                .toList();
    }

    public Pet favoritePet(String ownerId) {
        return petsByOwner(ownerId).stream()
                .findFirst()
                .orElse(null);
    }


}