package com.marvel.api.marverapi.persistence.integration.marvel.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.ThunbnailDto;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ThumbnailMapper {

    @NotNull
    @Contract("_ -> new")
    public static ThunbnailDto toDto(@NotNull JsonNode thumbnailNode) {
        if(thumbnailNode.isNull()) {
            throw new IllegalArgumentException("Root node is null");
        }
        return new ThunbnailDto(
                thumbnailNode.get("path").asText(),
                thumbnailNode.get("extension").asText()
        );
    }
}
