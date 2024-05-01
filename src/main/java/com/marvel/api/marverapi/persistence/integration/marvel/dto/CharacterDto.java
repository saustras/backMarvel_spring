package com.marvel.api.marverapi.persistence.integration.marvel.dto;

public record CharacterDto (
        long id,
        String name,
        String description,
        String modified,
        String resourceURI

) {}
