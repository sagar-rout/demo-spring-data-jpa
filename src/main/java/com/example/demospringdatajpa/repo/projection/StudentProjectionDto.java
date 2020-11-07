package com.example.demospringdatajpa.repo.projection;

import java.util.UUID;

/**
 * class based DTO projections are faster than interface based dto projection
 */
public class StudentProjectionDto {

    private UUID id;
    private String name;

    public StudentProjectionDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
