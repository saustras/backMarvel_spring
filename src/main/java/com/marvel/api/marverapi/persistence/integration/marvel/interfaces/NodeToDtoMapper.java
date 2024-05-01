package com.marvel.api.marverapi.persistence.integration.marvel.interfaces;

import com.fasterxml.jackson.databind.JsonNode;

@FunctionalInterface
public interface NodeToDtoMapper<T> {
    T map(JsonNode node);
}