package com.marvel.api.marverapi.persistence.integration.marvel.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.RequestParam;

public record GetCharactersDto (
        @NotNull String name,
        @NotNull int[] comics,
        @NotNull int[] series
){}
