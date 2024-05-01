package com.marvel.api.marverapi.persistence.integration.marvel.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.RequestParam;

public record GetComicsDto(
        @NotNull long characterId,
        @RequestParam(defaultValue = "0") long offset,
        @RequestParam(defaultValue = "10") long limit
) {}
