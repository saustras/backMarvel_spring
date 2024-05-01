package com.marvel.api.marverapi.persistence.integration.marvel.dto;
import jakarta.validation.constraints.NotNull;

public record GetCharactersDto (
        @NotNull String name,
        @NotNull int[] comics,
        @NotNull int[] series
){}
