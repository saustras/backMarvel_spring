package com.marvel.api.marverapi.persistence.integration.marvel.dto;

public record ComicDto (
        Long id,
        String title,
        String description,
        String modified,
        String resourcerURI,
        ThunbnailDto thunbnail
) {
}
