package com.graphql.pet;

import java.time.LocalDate;

public record Pet(String id, String name, String ownerId, LocalDate dateOfBirth) {
}