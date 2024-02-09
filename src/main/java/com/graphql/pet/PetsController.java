package com.graphql.pet;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PetsController {

    @QueryMapping
    List<Pet> pets() {
        return List.of(
                new Pet("Max", "Brown"),
                new Pet("Charlie", "Black"));
    }
}