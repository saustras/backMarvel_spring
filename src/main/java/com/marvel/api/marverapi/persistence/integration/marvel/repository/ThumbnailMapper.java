package com.marvel.api.marverapi.persistence.integration.marvel.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.ThunbnailDto;

public class ThumbnailMapper {

    public static ThunbnailDto toDto(JsonNode thumbnailNode) {
        if(thumbnailNode.isNull()) {
            throw new IllegalArgumentException("Root node is null");
        }
        return new ThunbnailDto(
                thumbnailNode.get("path").asText(),
                thumbnailNode.get("extension").asText()
        );
    }
}
